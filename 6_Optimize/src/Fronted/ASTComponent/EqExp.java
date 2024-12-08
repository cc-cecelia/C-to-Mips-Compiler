package Fronted.ASTComponent;

import Fronted.Lexer.Token;
import Fronted.Parser.ParseType;
import IO.Output;
import MidCode.IRModule;
import MidCode.Instructions.Compare;
import MidCode.MidGenerator;
import MidCode.Table.MidController;
import MidCode.Table.MidType;
import MidCode.Table.SymbolType;
import MidCode.Table.VarSymbol;
import MidCode.Value.IntegerValue.IntegerValue;
import MidCode.Value.Value;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class EqExp implements AST {
    private RelExp relExp;
    private Token op;
    private EqExp eqExp;

    public EqExp(RelExp relExp, Token op, EqExp exp) {
        this.relExp = relExp;
        this.op = op;
        this.eqExp = exp;
    }

    public void print() throws IOException {
        relExp.print();
        //<Eq>
        Output.component(ParseType.EqExp);
        if (op != null) {
            op.print();
            eqExp.print();
        }
    }

    protected Value val;
    protected LinkedList<RelExp> relExps = new LinkedList<>();
    protected LinkedList<Token> ops = new LinkedList<>();

    public void recombine(List<RelExp> relExps , List<Token> ops) {
        relExps.add(this.relExp);
        if (op != null) {
            ops.add(op);
            eqExp.recombine(relExps, ops);
        }
    }

    public Value recombine() {
        recombine(relExps,ops);
        if (relExps.size() == 1) {
            return relExp.reCom();
            //return relExp.recombine();
        }
        Value cmpExp;
        RelExp left = relExps.pop();
        RelExp right = relExps.pop();
        cmpExp = new CmpExp(left.reCom(),getType(ops.pop()),right.reCom());
        //cmpExp = simplify(left.recombine(),right.recombine(),ops.pop());
        while(!relExps.isEmpty()) {
            right = relExps.pop();
            cmpExp = new CmpExp(cmpExp,getType(ops.pop()),right.reCom());
            //cmpExp = simplify(cmpExp,right.recombine(),ops.pop());
        }
        return cmpExp;
//
//        Value left = relExp.recombine();
//        if (this.op != null) {
//            CmpType type = switch (op.context) {
//                case "==" -> CmpType.EQL;
//                case "!=" -> CmpType.NEQ;
//                default -> throw new IllegalStateException("Unexpected value: " + op.context);
//            };
//            Value right = eqExp.recombine();
//            if (left instanceof IntegerValue intLeft && right instanceof IntegerValue intRight) {
//                int res = switch (type) {
//                    case EQL -> intLeft.getDim0Value() == intRight.getDim0Value() ? 1 : 0;
//                    case NEQ -> intLeft.getDim0Value() != intRight.getDim0Value() ? 1 : 0;
//                    default -> throw new IllegalStateException("Unexpected value: " + type);
//                };
//                return new IntegerValue(res);
//            }
//            return new CmpExp(left,type,right);
//        } else {
//            return left;
//        }
    }

    public CmpType getType(Token op) {
        return switch (op.context) {
            case "==" -> CmpType.EQL;
            case "!=" -> CmpType.NEQ;
            default -> throw new IllegalStateException("Unexpected value: " + op.context);
        };
    }
    public Value simplify(Value left, Value right, Token op) {
        if (left instanceof IntegerValue intLeft && right instanceof IntegerValue intRight) {
            int res = switch (op.context) {
                case "==" -> intLeft.getDim0Value() == intRight.getDim0Value() ? 1 : 0;
                case "!=" -> intLeft.getDim0Value() != intRight.getDim0Value() ? 1 : 0;
                default -> throw new IllegalStateException("Unexpected value: " + op.context);
            };
            return new IntegerValue(res);
        } else {
            return new CmpExp(left,getType(op),right);
        }
    }

    protected String target;
    protected String end;
    @Override
    public void generateMidCode() {
        recombine(relExps,ops);
        relExp.generateMidCode();
        val = relExp.val;
        if (relExps.size() == 1) {
            relExp.falseJump = true;
            relExp.generateMidCode();

            return;
        }

        for (int i = 0; i < relExps.size(); i++) {
            String tmp = MidGenerator.generateTmpName(null);
            VarSymbol symbol = new VarSymbol(null,tmp,new MidType(SymbolType.VAR_0),false);
            MidController.addNewSymbol(tmp,symbol);
            relExps.get(i+1).generateMidCode();
            CmpType type = op.context.equals("==") ? CmpType.EQL : CmpType.NEQ;
            if (i != relExps.size() -1) {
                IRModule.addInstr(new Compare(val, relExp.val, type.getOpposite() ,target));
            }
            // 最后一个Eqexp了 直接接下一个ORlabel了，不用再跳转了
        }
    }

    @Override
    public Value getVal() {
        return val;
    }
}
