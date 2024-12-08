package MidCode.Optimize;

import MidCode.Instructions.Calculate;
import MidCode.Instructions.Instruction;
import MidCode.Instructions.ValAssign;
import MidCode.Value.IntegerValue.IntegerValue;

import java.util.ArrayList;

public class CalOptimizer {

    public static ArrayList<Instruction> optimize(ArrayList<Instruction> midCodes) {
        ArrayList<Instruction> optimized = (ArrayList<Instruction>) midCodes.clone();
        for(int i = 0; i < optimized.size(); i++) {
            Instruction instruction  = optimized.get(i);
            if (instruction instanceof Calculate cal) {
                Instruction newInstr = reduceStrength(cal);
                if (!cal.equals(newInstr)) {
                    optimized.set(i,newInstr);
                }
            }
        }
        return optimized;
    }

//    public static Instruction optimize(Calculate old) {
//        // 先看能不能常量传播吧
//        Instruction o1 = calculable(old);
//        if (o1 instanceof Calculate){
//            // 如果不能常量传播 那就约简计算难度
//            return reduceStrength((Calculate) o1);
//        }
//        return o1;
//    }

    public static Instruction reduceStrength(Calculate old) {
        return switch (old.getOp()) {
            case "*" -> mulOptimize(old);
            case "/" -> divOptimize(old);
            default -> old;
        };
    }

//    // 常量传播
//    public static Instruction calculable(Calculate old) {
//        Value operand1 = getVal(old.getOperand1());
//        Value operand2 = getVal(old.getOperand2());
//        if (operand1 instanceof IntegerValue op1) {
//            if (operand2 instanceof IntegerValue op2) {
//                return new ValAssign(old.getLVal().getMidName(), old.getLVal(), new IntegerValue(getRes(old.getOp(),op1.getDim0Value(),op2.getDim0Value() )));
//            } else {
//                return old;
//            }
//        }
//        return old;
//    }
//
//    public static Value getVal(Value value) {
//        if (value instanceof VarSymbol) {
//            return ((VarSymbol) value).getValue();
//        } else if (value instanceof SymbolValue) {
//            return ((SymbolValue) value).getSymbol().getValue();
//        } else if (value instanceof IntegerValue) {
//            return value;
//        }
//        return null;
//    }

    public static Instruction mulOptimize(Calculate old) {
        Calculate cal = old;
        if (old.getOperand1() instanceof IntegerValue imm) {
            cal = new Calculate(old.getLVal(), old.getOp(), old.getOperand2(),old.getOperand1());
        }
        if (cal.getOperand2() instanceof IntegerValue imm) {
            int operand2 = imm.getDim0Value();
            boolean is2Power = isPowerOfTwo(operand2);
            if (is2Power) {
                int left = (int) (Math.log(operand2) / Math.log(2));
                cal =  new Calculate(cal.getLVal(), "<<",cal.getOperand1(),new IntegerValue(left));
            }
        }
        if(cal.getOperand1() instanceof IntegerValue int1 && cal.getOperand2() instanceof IntegerValue int2) {
            return new ValAssign(cal.getLVal(),new IntegerValue(getRes(cal.getOp(),int1.getDim0Value(),int2.getDim0Value())));
        }
        return cal;
    }

    public static boolean isPowerOfTwo(int n) {
        String str = Integer.toBinaryString(n);
        if(n < 1)
            return false;
        else return str.lastIndexOf("1") == 0;
    }

    public static Instruction divOptimize(Calculate old) {
        if (old.getOperand2() instanceof IntegerValue imm) {
            int operand2 = imm.getDim0Value();
            if (isPowerOfTwo(operand2)) {
                int right = (int) (Math.log(operand2) / Math.log(2));
                return new Calculate(old.getLVal(),">>",old.getOperand1(),new IntegerValue(right));
            }
        }
        return old;
    }

    public static int getRes(String op,int op1,int op2) {
        return switch (op) {
            case "+" -> op1 + op2;
            case "-" -> op1 - op2;
            case ">>" -> op1 >> op2;
            case "<<" -> op1 << op2;
            case "*" -> op1 * op2;
            case "/" -> op1 / op2;
            case "%" -> op1 % op2;
            case "!" -> op1 == 0 ? 0 : 1;
            case "==" -> op1 == op2 ? 1 : 0;
            case ">=" -> op1 >= op2 ? 1:0;
            case "<=" -> op1 <= op2 ? 1:0;
            case ">" -> op1 > op2 ? 1 : 0;
            case "<" -> op1 < op2 ? 1 : 0;
            default -> throw  new IllegalStateException("不能有别的操作符了" + op);
        };
    }
}
