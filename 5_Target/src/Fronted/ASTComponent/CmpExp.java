package Fronted.ASTComponent;

import MidCode.IRModule;
import MidCode.Instructions.Calculate;
import MidCode.Instructions.Compare;
import MidCode.Instructions.Goto;
import MidCode.Instructions.Label;
import MidCode.MidGenerator;
import MidCode.Table.MidType;
import MidCode.Table.SymbolType;
import MidCode.Table.VarSymbol;
import MidCode.Value.IntegerValue.IntegerValue;
import MidCode.Value.Value;
import Target.Instructions.Jump;
import Target.Instructions.MipsCode;

import java.io.IOException;
import java.util.List;

public class CmpExp extends Value implements AST{
    private String ifName;
    private String targetName;
    private Value left;
    private CmpType cmpType;
    private Value right;
    private static boolean jump = true;
    private static boolean ctn = false;

    public CmpExp(Value left, CmpType op, Value right) {
        this.left = left;
        this.cmpType = op;
        this.right = right;
    }

    //   A    OR B来了 : 若A是真则跳到 IFBegin = target 否则继续执行到 Blabel;
    //                   若B是真的那就完事了，那就也不跳转了，
    // C OR D     来了 ：若C是真，则跳到IFBegin 否则继续执行到 D
    //                   若D是真，则跳到IFBegin 否则继续执行到B 但是不用跳转

    public void trueJump(String target) {
        if (cmpType == CmpType.OR) {
            if (left instanceof CmpExp) {
                ((CmpExp) left).trueJump(target);
            } else {
                ((AST)left).generateMidCode();
                IRModule.addInstr(new Compare(((AST)left).getVal(),new IntegerValue(0),CmpType.NEQ,target));
            }
//            if (left instanceof VarSymbol) {
//                IRModule.addInstr(new Compare(left, new IntegerValue(0), CmpType.NEQ, target));
//            }
            if (right instanceof CmpExp) {
                ((CmpExp) right).trueJump(target);
            } else {
                ((AST)right).generateMidCode();
                IRModule.addInstr(new Compare(((AST)right).getVal(),new IntegerValue(0),CmpType.NEQ,target));
            }
//            if (right instanceof VarSymbol) {
//                IRModule.addInstr(new Compare(right, new IntegerValue(0), CmpType.NEQ, target));
//            }
        } else if (cmpType == CmpType.AND) {
            String falseToGo = MidGenerator.generateTmpLabel();
            if (left instanceof CmpExp) {
                ((CmpExp) left).falseJump(falseToGo);
            } else {
                ((AST)left).generateMidCode();
                IRModule.addInstr(new Compare(((AST)left).getVal(),new IntegerValue(0),CmpType.EQL,falseToGo));
            }
//            if (left instanceof VarSymbol) {
//                IRModule.addInstr(new Compare(left, new IntegerValue(0), CmpType.EQL, falseToGo));
//            }
            if (right instanceof CmpExp) {
                ((CmpExp) right).falseJump(falseToGo);
            } else {
                ((AST)right).generateMidCode();
                IRModule.addInstr(new Compare(((AST)right).getVal(),new IntegerValue(0),CmpType.EQL,falseToGo));
            }
//            if (right instanceof VarSymbol) {
//                IRModule.addInstr(new Compare(right, new IntegerValue(0), CmpType.EQL, falseToGo));
//            }
            IRModule.addInstr(new Goto(target));
            IRModule.addInstr(new Label(falseToGo));
        } else {
            Value leftVal,rightVal;
            if (left instanceof CmpExp) {
                String tmpLeft = MidGenerator.generateTmpName(null);
                leftVal = ((CmpExp) left).generateMidCode(tmpLeft);
            } else {
                ((AST)left).generateMidCode();
                leftVal = ((AST)left).getVal();
                //leftVal = left;
            }
            if (right instanceof CmpExp) {
                String tmpRight = MidGenerator.generateTmpName(null);
                rightVal = ((CmpExp) right).generateMidCode(tmpRight);
            } else {
                ((AST)right).generateMidCode();
                rightVal = ((AST)right).getVal();
                //rightVal = right;
            }
            IRModule.addInstr(new Compare(leftVal, rightVal, cmpType, target));
        }
    }

    // E && F     来了 ：若E是假则跳到 target = Dlabel 否则继续执行到F
    // A&&B       来了 ： 若A是假则跳到Dlabel 否则继续执行B
    // G == H     来了 ： 若G==H是假的 则跳到Dlabel
    // G >= H      来了： 若G>=H是假的 则跳到Dlabel
    public void falseJump(String target) {
        if (cmpType == CmpType.AND) {
            if (left instanceof CmpExp cmpLeft) {
                cmpLeft.falseJump(target);
            }else {
                ((AST)left).generateMidCode();
                IRModule.addInstr(new Compare(((AST)left).getVal(),new IntegerValue(0),CmpType.EQL,target));
            }
//            if (left instanceof VarSymbol) {
//                IRModule.addInstr(new Compare(left, new IntegerValue(0), CmpType.EQL, target));
//            }
            if (right instanceof CmpExp) {
                ((CmpExp) right).falseJump(target);
            } else {
                ((AST)right).generateMidCode();
                IRModule.addInstr(new Compare(((AST)right).getVal(),new IntegerValue(0),CmpType.EQL,target));
            }
//            if (right instanceof VarSymbol) {
//                IRModule.addInstr(new Compare(right, new IntegerValue(0), CmpType.EQL, target));
//            }
        } else if (cmpType == CmpType.OR) {
            String trueToGo = MidGenerator.generateTmpLabel();
            if (left instanceof CmpExp cmpLeft) {
                cmpLeft.trueJump(trueToGo);
            } else {
                ((AST)left).generateMidCode();
                IRModule.addInstr(new Compare(((AST)left).getVal(),new IntegerValue(0),CmpType.NEQ,trueToGo));
            }
//            else {
//                IRModule.addInstr(new Compare(left, new IntegerValue(0), CmpType.NEQ, trueToGo));
//            }
            if (right instanceof CmpExp cmpRight) {
                cmpRight.trueJump(trueToGo);
            }else {
                ((AST)right).generateMidCode();
                IRModule.addInstr(new Compare(((AST)right).getVal(),new IntegerValue(0),CmpType.NEQ,trueToGo));
            }

//            else {
//                IRModule.addInstr(new Compare(right, new IntegerValue(0), CmpType.NEQ, trueToGo));
//            }
            IRModule.addInstr(new Goto(target));
            IRModule.addInstr(new Label(trueToGo));
        } else {
            Value leftVal,rightVal;
            if (left instanceof CmpExp) {
                String tmpLeft = MidGenerator.generateTmpName(null);
                leftVal = ((CmpExp) left).generateMidCode(tmpLeft);
            } else {
                ((AST)left).generateMidCode();
                leftVal = ((AST)left).getVal();
                //leftVal = left;
            }
            if (right instanceof CmpExp) {
                String tmpRight = MidGenerator.generateTmpName(null);
                rightVal = ((CmpExp) right).generateMidCode(tmpRight);
            } else {
                ((AST)right).generateMidCode();
                rightVal = ((AST)right).getVal();
                //rightVal = right;
            }
            // right gen
            IRModule.addInstr(new Compare(leftVal, rightVal, cmpType.getOpposite(), target));
        }
    }

    public VarSymbol generateMidCode(String res) {
        VarSymbol symbol = new VarSymbol(null,res,new MidType(SymbolType.VAR_0),false);
        Value leftVal,rightVal;

        if (left instanceof CmpExp) {
            String tmpLeft = MidGenerator.generateTmpName(null);
            leftVal = ((CmpExp) left).generateMidCode(tmpLeft);
        } else {
            ((AST)left).generateMidCode();
            leftVal = ((AST)left).getVal();
            //leftVal = left;
        }
        if (right instanceof CmpExp) {
            String tmpRight = MidGenerator.generateTmpName(null);
            rightVal = ((CmpExp) right).generateMidCode(tmpRight);
        } else {
            ((AST)right).generateMidCode();
            rightVal = ((AST)right).getVal();
            //rightVal = right;
        }
        IRModule.addInstr(new Calculate(symbol,cmpType.getOp(),leftVal,rightVal));
        return symbol;
    }

    @Override
    public String toString() {
        return left.toString() + " " + cmpType + " " + right.toString() + "\n";
    }

    @Override
    public Value clone() {
        return null;
    }

    @Override
    public List<MipsCode> toMipsCode() {
        return null;
    }

    @Override
    public void print() throws IOException {

    }

    @Override
    public void generateMidCode() {

    }

    @Override
    public Value getVal() {
        return null;
    }
}
