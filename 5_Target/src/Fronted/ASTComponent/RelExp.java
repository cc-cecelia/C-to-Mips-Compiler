package Fronted.ASTComponent;

import Fronted.Lexer.Token;
import Fronted.Parser.ParseType;
import IO.Output;
import MidCode.Value.IntegerValue.IntegerValue;
import MidCode.Value.Value;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class RelExp implements AST {
    protected Value val;
    /*********************重构语法树内容*****************************/
    protected LinkedList<AddExp> addExps = new LinkedList<>();
    protected LinkedList<Token> ops = new LinkedList<>();
    /***************************************************************/
    protected boolean falseJump;

    private RelExp relExp;
    private Token op;
    private AddExp addExp;
    public RelExp(RelExp relExp, Token op, AddExp addExp) {
        this.relExp = relExp;
        this.op = op;
        this.addExp = addExp;
    }

    public void print() throws IOException {
        addExp.print();
        // <Rel>
        Output.component(ParseType.RelExp);
        if (op != null) {
            op.print();
            relExp.print();
        }
    }

    public void recombine(List<AddExp> addExps, List<Token> ops) {
        addExps.add(this.addExp);
        if (op != null) {
            ops.add(op);
            relExp.recombine(addExps, ops);
        }
    }

    public Value reCom() {
        recombine(addExps,ops);
        if (addExps.size() == 1) {
            return addExp;
        }
        CmpExp cmpExp;
        AddExp left = addExps.pop();
        //left.generateMidCode();
        AddExp right = addExps.pop();
        //right.generateMidCode();
        cmpExp = new CmpExp(left,getType(ops.pop()),right);
        //cmpExp = simplify(left.val,right.val,ops.pop());
        while (!addExps.isEmpty()) {
            right = addExps.pop();
            //right.generateMidCode();
            cmpExp = new CmpExp(cmpExp,getType(ops.pop()),right);
            //cmpExp = simplify(cmpExp,right.val,ops.pop());
        }
        return cmpExp;
    }

    public Value recombine() {
        recombine(addExps,ops);
        if (addExps.size() == 1) {
            addExp.generateMidCode();
            return addExp.val;
        }
        Value cmpExp;
        AddExp left = addExps.pop();
        left.generateMidCode();
        AddExp right = addExps.pop();
        right.generateMidCode();
        cmpExp = simplify(left.val,right.val,ops.pop());
        while (!addExps.isEmpty()) {
            right = addExps.pop();
            right.generateMidCode();
            cmpExp = simplify(cmpExp,right.val,ops.pop());
        }
        return cmpExp;
//        addExp.generateMidCode();
//        Value left = addExp.val;
//        if (this.op != null) {
//            CmpType type = switch (op.context) {
//                case ">" -> CmpType.GRE;
//                case "<" -> CmpType.LSS;
//                case ">=" -> CmpType.GEQ;
//                case "<=" -> CmpType.LEQ;
//                default -> throw new IllegalStateException("Unexpected value: " + op.context);
//            };
//            Value right = relExp.recombine();
//
//            if (left instanceof IntegerValue intLeft && right instanceof IntegerValue intRight) {
//                int res = switch (op.context) {
//                    case ">" -> intLeft.getDim0Value() > intRight.getDim0Value() ? 1 : 0;
//                    case "<" -> intLeft.getDim0Value() < intRight.getDim0Value() ? 1 : 0;
//                    case ">=" -> intLeft.getDim0Value() >= intRight.getDim0Value() ? 1 : 0;
//                    case "<=" -> intLeft.getDim0Value() <= intRight.getDim0Value() ? 1 : 0;
//                    default -> throw new IllegalStateException(op.context);
//                };
//                return new IntegerValue(res);
//            } else {
//                return new CmpExp(left, type, right);
//            }
//        } else {
//            return left;
//        }
    }

    public CmpType getType (Token op) {
        return switch (op.context) {
            case ">" -> CmpType.GRE;
            case "<" -> CmpType.LSS;
            case ">=" -> CmpType.GEQ;
            case "<=" -> CmpType.LEQ;
            default -> throw new IllegalStateException("Unexpected value: " + op.context);
        };
    }

    public Value simplify(Value left, Value right,Token op) {
        if (left instanceof IntegerValue intLeft && right instanceof IntegerValue intRight) {
            int res = switch (op.context) {
                case ">" -> intLeft.getDim0Value() > intRight.getDim0Value() ? 1 : 0;
                case "<" -> intLeft.getDim0Value() < intRight.getDim0Value() ? 1 : 0;
                case ">=" -> intLeft.getDim0Value() >= intRight.getDim0Value() ? 1 : 0;
                case "<=" -> intLeft.getDim0Value() <= intRight.getDim0Value() ? 1 : 0;
                default -> throw new IllegalStateException(op.context);
            };
            return new IntegerValue(res);
        } else {
            return new CmpExp(left, getType(op), right);
        }
    }
    @Override
    public void generateMidCode() {
    }

    @Override
    public Value getVal() {
        return null;
    }
}
