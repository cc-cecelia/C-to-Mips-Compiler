package Fronted.ASTComponent;

import IO.Output;
import Fronted.Lexer.Token;
import Fronted.Parser.ParseType;
import MidCode.Generator;
import MidCode.IRModule;
import MidCode.Instructions.Calculate;
import MidCode.Instructions.Instruction;
import MidCode.Table.MidController;
import MidCode.Table.MidType;
import MidCode.Table.SymbolType;
import MidCode.Table.VarSymbol;
import MidCode.Value.IntegerValue.IntegerType;
import MidCode.Value.IntegerValue.IntegerValue;
import MidCode.Value.Value;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class AddExp implements AST{
    private MulExp mulExp;
    private Token op;
    private AddExp addExp;

    public AddExp(MulExp mulExp, Token op, AddExp addExp) {
        this.mulExp = mulExp;
        this.op = op;
        this.addExp = addExp;
    }


    public ArrayList<Token> toTokens() {
        ArrayList<Token> tokens = new ArrayList<>();
        tokens.addAll(mulExp.toTokens());
        if (op != null) {
            tokens.add(op);
            tokens.addAll(addExp.toTokens());
        }
        return tokens;
    }

    // AddExp -> MulExp [('+' | '-') AddExp]
    public void print() throws IOException {
        mulExp.print();
        // <AddExp>
        Output.component(ParseType.AddExp);
        if (op != null) {
            op.print();
            addExp.print();
        }
    }

    protected Value val;
    @Override
    public void generateMidCode() {
        mulExp.generateMidCode();
        Value mul = mulExp.val; // -> add.val
        if (op != null) {
            addExp.generateMidCode();
            Value add = addExp.val;
            if (mul instanceof IntegerValue && add instanceof IntegerValue) {
                int res = switch (op.context) {
                    case "+" -> ((IntegerValue) mul).getDim0Value() + ((IntegerValue) add).getDim0Value();
                    case "-" -> ((IntegerValue) mul).getDim0Value() - ((IntegerValue) add).getDim0Value();
                    default -> throw new IllegalStateException("Unexpected value: " + op.context);
                };
                this.val = new IntegerValue(IntegerType.DIM0,res,null);
            } else {
                String tmp = Generator.generateTmpName(null);
                VarSymbol tmpSym = new VarSymbol(null, tmp, new MidType(SymbolType.VAR_0), false);
                MidController.addNewSymbol(tmp, tmpSym);
                IRModule.addInstr(new Calculate(tmpSym, op.context.charAt(0), add, mul));
                this.val = tmpSym;
            }
        } else {
            this.val = mul;
        }
    }

    public int getCalculable() {
        if (op == null) {
            return mulExp.getCalculable();
        } else {
            int mul = mulExp.getCalculable();
            int add = addExp.getCalculable();
            return switch (op.context) {
                case "+" -> mul + add;
                case "-" -> mul - add;
                default -> throw new IllegalStateException("Unexpected value: " + op.context);
            };
        }
    }
}
