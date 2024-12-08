package Fronted.ASTComponent;

import IO.Output;
import Fronted.Lexer.Token;
import Fronted.Parser.ParseType;
import MidCode.MidGenerator;
import MidCode.IRModule;
import MidCode.Instructions.Calculate;
import MidCode.Table.MidController;
import MidCode.Table.MidType;
import MidCode.Table.SymbolType;
import MidCode.Table.VarSymbol;
import MidCode.Value.IntegerValue.IntegerType;
import MidCode.Value.IntegerValue.IntegerValue;
import MidCode.Value.Value;
import Target.Instructions.MipsCode;
import com.sun.jdi.ArrayReference;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AddExp extends Value implements AST {
    private MulExp mulExp;
    private Token op;
    private AddExp addExp;
    private ArrayList<Token> ops = new ArrayList<>();
    private ArrayList<MulExp> mulExps = new ArrayList<>();

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

    public void add(List<MulExp> mulExps , List<Token> ops) {
        mulExps.add(this.mulExp);
        if (op != null) {
            ops.add(op);
            addExp.add(mulExps, ops);
        }
    }

    @Override
    public void generateMidCode() {
        add(mulExps,ops);
        if (ops.isEmpty()) {
            mulExp.generateMidCode();
            this.val = mulExp.val;
        } else {
            mulExp.generateMidCode();
            Value tmp1 = mulExp.val;
            for (int i = 0; i < ops.size(); i++) {
                MulExp mulExp = mulExps.get(i+1);
                mulExp.generateMidCode();
                Value mul = mulExp.val;
                if (tmp1 instanceof IntegerValue && mul instanceof IntegerValue) {
                    int res = switch (ops.get(i).context) {
                        case "+" -> ((IntegerValue) tmp1).getDim0Value() + ((IntegerValue) mul).getDim0Value();
                        case "-" -> ((IntegerValue) tmp1).getDim0Value() - ((IntegerValue) mul).getDim0Value();
                        default -> throw new IllegalStateException("Unexpected value: " + op.context);
                    };
                    tmp1 = new IntegerValue(IntegerType.DIM0,res,null);
                } else {
                    String tmp = MidGenerator.generateTmpName(null);
                    VarSymbol tmpSym = new VarSymbol(this,tmp,new MidType(SymbolType.VAR_0),false);
                    MidController.addNewSymbol(tmp,tmpSym);
                    IRModule.addInstr(new Calculate(tmpSym,ops.get(i).context,tmp1,mul));
                    tmp1 = tmpSym;
                }
            }
            this.val = tmp1;
        }
    }

    @Override
    public Value getVal() {
        return val;
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

    @Override
    public String toString() {
        return null;
    }

    @Override
    public Value clone() {
        return null;
    }

    @Override
    public List<MipsCode> toMipsCode() {
        return null;
    }
}
