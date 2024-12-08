package Fronted.ASTComponent;

import Fronted.Parser.ParseType;
import IO.Output;
import MidCode.IRModule;
import MidCode.Instructions.Compare;
import MidCode.Instructions.Goto;
import MidCode.Value.IntegerValue.IntegerValue;
import MidCode.Value.Value;

import java.io.IOException;

public class Cond implements AST {
    /*********IF-ELSE 用的变量**************/
    protected String ifBlock;
    protected String elseBlock;
    protected String end;
    protected boolean ignoreStmt1;
    protected boolean ignoreStmt2;
    /********************************/
    /*********for 用的变量 ***********/
    protected boolean ignoreLoop;
    protected boolean isTautology;
    protected String forBlock;
    protected String basicBlock;


    protected int type;

    private LOrExp lOrExp;
    public Cond(LOrExp lOrExp) {
        this.lOrExp = lOrExp;
    }

    public void print() throws IOException {
        lOrExp.print();
        Output.component(ParseType.Cond);
    }

    @Override
    public void generateMidCode() {

        switch (type) {
            case 1 -> ifCond();
            case 2 -> forCond();
            default -> throw new IllegalStateException(String.valueOf(type));
        }
    }

    @Override
    public Value getVal() {
        return null;
    }

    private void ifCond() {
        Value val = lOrExp.recombine();
        if (val instanceof CmpExp) {
            ((CmpExp) val).trueJump(ifBlock);
            if (elseBlock != null) {
                IRModule.addInstr(new Goto(elseBlock));
            } else {
                IRModule.addInstr(new Goto(end));
            }
        } else {
            ((AST)val).generateMidCode();
            Value value = ((AST)val).getVal();
            if (value == null) {
                throw new IllegalStateException("val是什么东西，居然没办法生成一个值！" + val.getClass());
            }
            if (elseBlock != null) {
                IRModule.addInstr(new Compare(value, new IntegerValue(0),CmpType.EQL,elseBlock));
                //IRModule.addInstr(new Compare(val, new IntegerValue(1), CmpType.NEQ, elseBlock));
            } else {
                IRModule.addInstr(new Compare(value, new IntegerValue(0),CmpType.EQL,end));
                //IRModule.addInstr(new Compare(val, new IntegerValue(1), CmpType.NEQ, end));
            }
        }
//
//        if (val instanceof BoolValue) {
//            if (((BoolValue) val).getRes()) {
//                // 只翻block1 不翻block2
//                ignoreStmt2 = true;
//            } else {
//                // 只翻block2 不翻block1
//                ignoreStmt1 = true;
//            }
//        } else if (val instanceof VarSymbol) {
//            if (elseBlock != null) {
//                IRModule.addInstr(new Compare(val, new IntegerValue(0),CmpType.EQL,elseBlock));
//                //IRModule.addInstr(new Compare(val, new IntegerValue(1), CmpType.NEQ, elseBlock));
//            } else {
//                IRModule.addInstr(new Compare(val, new IntegerValue(0),CmpType.EQL,end));
//                //IRModule.addInstr(new Compare(val, new IntegerValue(1), CmpType.NEQ, end));
//            }
//        } else if (val instanceof CmpExp) {
//            ((CmpExp) val).trueJump(ifBlock);
//            if (elseBlock != null) {
//                IRModule.addInstr(new Goto(elseBlock));
//            } else {
//                IRModule.addInstr(new Goto(end));
//            }
//        }
    }

    public void forCond() {
        Value val = lOrExp.recombine();
        if (val instanceof CmpExp) {
            ((CmpExp) val).falseJump(basicBlock);
        } else {
            ((AST)val).generateMidCode();
            Value value = ((AST) val).getVal();
            IRModule.addInstr(new Compare(value, new IntegerValue(0),CmpType.EQL,basicBlock));
        }



//        if (val instanceof BoolValue) {
//            if (((BoolValue) val).getRes()) {
//                isTautology = true;
//            } else {
//                ignoreLoop = true;
//            }
//        }else if (val instanceof VarSymbol) {
//            IRModule.addInstr(new Compare(val, new IntegerValue(0),CmpType.EQL,basicBlock));
//            //IRModule.addInstr(new Compare(val,new IntegerValue(1),CmpType.NEQ,basicBlock));
//        } else if (val instanceof CmpExp) {
//            ((CmpExp) val).falseJump(basicBlock);
//        }
    }
}
