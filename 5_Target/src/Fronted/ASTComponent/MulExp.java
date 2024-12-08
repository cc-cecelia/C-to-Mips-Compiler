package Fronted.ASTComponent;

import Fronted.Lexer.Token;
import Fronted.Parser.ParseType;
import IO.Output;
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MulExp implements AST {
    protected Value val;
    private UnaryExp unaryExp;
    private Token op;
    private MulExp mulExp;
    private ArrayList<Token> ops = new ArrayList<>();
    private ArrayList<UnaryExp> unaryExps = new ArrayList<>();
    public MulExp(UnaryExp unaryExp, Token op, MulExp mulExp) {
        this.unaryExp = unaryExp;
        this.op = op;
        this.mulExp = mulExp;
    }

    public void print() throws IOException {
        unaryExp.print();
        // Mul
        Output.component(ParseType.MulExp);
        if (op != null) {
            op.print();
            mulExp.print();
        }
    }

    public void add(List<UnaryExp> unaryExps, List<Token> ops) {
        unaryExps.add(this.unaryExp);
        if (op != null) {
            ops.add(op);
            mulExp.add(unaryExps, ops);
        }
    }

    @Override
    public void generateMidCode() {
        add(unaryExps, ops);
        if (ops.isEmpty()) {
            unaryExp.generateMidCode();
            this.val = unaryExp.val;
        } else {
            unaryExp.generateMidCode();
            Value tmp1 = unaryExp.val;
            for (int i = 0; i < ops.size(); i++) {
                UnaryExp unaryExp = unaryExps.get(i+1);
                unaryExp.generateMidCode();
                Value unary = unaryExp.val;
                if (tmp1 instanceof IntegerValue && unary instanceof IntegerValue) {
                    int res = switch (ops.get(i).context) {
                        case "*" -> ((IntegerValue) tmp1).getDim0Value() * ((IntegerValue) unary).getDim0Value();
                        case "/" -> ((IntegerValue) tmp1).getDim0Value() / ((IntegerValue) unary).getDim0Value();
                        case "%" -> ((IntegerValue) tmp1).getDim0Value() % ((IntegerValue) unary).getDim0Value();
                        default -> throw new IllegalStateException("Unexpected value: " + op.context);
                    };
                    tmp1 = new IntegerValue(IntegerType.DIM0,res,null);
                } else {
                    String tmp = MidGenerator.generateTmpName(null);
                    VarSymbol tmpSym = new VarSymbol(this,tmp,new MidType(SymbolType.VAR_0),false);
                    MidController.addNewSymbol(tmp,tmpSym);
                    IRModule.addInstr(new Calculate(tmpSym,ops.get(i).context,tmp1,unary));
                    tmp1 = tmpSym;
                }
            }
            this.val = tmp1;
        }
//        unaryExp.generateMidCode();
//        Value unary = unaryExp.val;
//        if (op != null) {
//            mulExp.generateMidCode();
//            Value mul = mulExp.val;
//            if (unary instanceof IntegerValue && mul instanceof IntegerValue) {
//                int res = switch (op.context) {
//                    case "*" -> ((IntegerValue) unary).getDim0Value() * ((IntegerValue) mul).getDim0Value();
//                    case "/" -> ((IntegerValue) unary).getDim0Value() / ((IntegerValue) mul).getDim0Value();
//                    case "%" -> ((IntegerValue) unary).getDim0Value() % ((IntegerValue) mul).getDim0Value();
//                    default -> throw new IllegalStateException("Unexpected value: " + op.context);
//                };
//                this.val = new IntegerValue(IntegerType.DIM0, res, null);
//            } else {
//                String tmp = Generator.generateTmpName(null);
//                VarSymbol tmpSym = new VarSymbol(this, tmp, new MidType(SymbolType.VAR_0), false);
//                MidController.addNewSymbol(tmp, tmpSym);
//                IRModule.addInstr(new Calculate(tmpSym, op.context.charAt(0), mul, unary));
//                this.val = tmpSym;
//            }
//        } else {
//            this.val = unary;
//        }
    }

    @Override
    public Value getVal() {
        return val;
    }

    //MulExp → UnaryExp | MulExp ('*' | '/' | '%') UnaryExp
    public int getCalculable() {
        add(unaryExps, ops);
        if (ops.isEmpty()) {
            return unaryExp.getCalculable();
        } else {
            int sum = unaryExps.get(0).getCalculable();
            for (int i = 0; i < ops.size(); i++) {
                sum = switch (ops.get(i).context) {
                    case "*" -> sum * unaryExps.get(i + 1).getCalculable();
                    case "/" -> sum / unaryExps.get(i + 1).getCalculable();
                    case "%" -> sum % unaryExps.get(i + 1).getCalculable();
                    default -> throw new IllegalStateException("Unexpected value: " + op.context);
                };
            }
            return sum;
        }
    }

    public ArrayList<Token> toTokens() {
        ArrayList<Token> tokens = new ArrayList<>();
        tokens.addAll(unaryExp.toTokens());
        if (op != null) {
            tokens.add(op);
            tokens.addAll(mulExp.toTokens());
        }
        return tokens;
    }
}
