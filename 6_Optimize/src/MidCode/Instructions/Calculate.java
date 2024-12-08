package MidCode.Instructions;

import MidCode.Optimize.CalOptimizer;
import MidCode.Optimize.DAGOptimizer.DAG;
import MidCode.Table.VarSymbol;
import MidCode.Value.IntegerValue.IntegerValue;
import MidCode.Value.PointerValue;
import MidCode.Value.SymbolValue;
import MidCode.Value.Value;
import Target.GRF.GRF;
import Target.GRF.Register;
import Target.Immediate;
import Target.Instructions.*;
import Target.Operand;
import Target.Symbol.MipsController;

import java.util.List;

public class Calculate extends Instruction {
    public VarSymbol lVal;
    public String op;
    public Value operand1;
    public Value operand2;

    public Calculate(VarSymbol lVal, String op, Value operand1, Value operand2) {
        this.lVal = lVal;
        this.op = op;
        this.operand1 = operand1;
        this.operand2 = operand2;
    }

    @Override
    public String toString() {
        // return op + ", " + operand1.toString() + ", " + operand2.toString() + ", " + lVal.toString() + "\n";
        if (operand1 == null) {
            return lVal.toString() + " = " + op + operand2.toString() + "\n";
        } else {
            return lVal.toString() + " = " + operand1.toString() + op + operand2.toString() + "\n";
        }
    }

    public List<MipsCode> toMipsCode(boolean isOptimized) {
        List<MipsCode> container = isOptimized ? optimizedMips : this.mipsCodes;
        container.add(new Annotation(this.toString()));
        InstrType type;
        Register rd = GRF.getTReg();
        rd.setOccupied();
        if (isR_I()) {
            Register rs = GRF.getTReg();
            rs.setOccupied();
            getOperand(operand1,rs,container);
            type = switch (op) {
                case "+", "-" -> InstrType.addiu;
                case "<<" -> InstrType.sll;
                case ">>" -> InstrType.sra;
                default -> throw new IllegalStateException("Unexpected value: " + op);
            };
            int imm = switch (op) {
                case "+","<<",">>" -> ((IntegerValue) operand2).getDim0Value();
                case "-" -> -((IntegerValue) operand2).getDim0Value();
                default -> throw new IllegalStateException("Unexpected value: " + op);
            };
            container.add(new Alu(type, rd, rs, new Immediate(imm)));
            Operand lValAddr = MipsController.getAddr(lVal);
            save(rd, lValAddr, container);
            rd.release();
            rs.release();
        } else {
            type = switch (op) {
                case "+" -> InstrType.addu;
                case "-" -> InstrType.subu;
                case "*" -> InstrType.mult;
                case "/", "%" -> InstrType.div;
                case "!", "==" -> InstrType.seq;
                case ">" -> InstrType.sgt;
                case "<" -> InstrType.slt;
                case ">=" -> InstrType.sge;
                case "<=" -> InstrType.sle;
                case "!=" -> InstrType.sne;
                case "<<" -> InstrType.sll;
                case ">>" -> InstrType.sra;
                default -> throw new IllegalStateException("Unexpected value: " + op);
            };
            Register rs = GRF.getTReg();
            //rs.setOccupied();
            if (type == InstrType.seq) {
                rs = GRF.getReg("0"); // 若rt = 0 -> 1 若rt != 0 -> 0
            } else {
                rs.setOccupied();
                if (operand1 instanceof IntegerValue integer) {
                    container.add(new Load(InstrType.li, rs, null, new Immediate(integer.getDim0Value())));
                } else if (operand1 instanceof PointerValue pointer) {
                    if (pointer.isGlobal()) {
                        container.add(new Load(InstrType.la, rs, null, MipsController.getAddr(pointer.getSymbol())));
                    } else {
                        if (pointer.getSymbol().isParam()) {
                            container.add(new Load(InstrType.lw, rs, MipsController.getAddr(pointer.getSymbol()), GRF.getReg("sp")));
                        } else {
                            container.add(new Alu(InstrType.addiu, rs, GRF.getReg("sp"), MipsController.getAddr(pointer.getSymbol())));
                        }
                        //mipsCodes.add(new Load(InstrType.li,rs,null,MipsController.getAddr(pointer.getSymbol())));
                        //mipsCodes.add(new Load(InstrType.lw,rs,MipsController.getAddr(pointer.getSymbol()),GRF.getReg("sp")));
                    }
                } else {
                    getOperand(operand1,rs,container);
                }
//                else if (operand1 instanceof VarSymbol varSymbol) {
//                    if (varSymbol.isGlobal()) {
//                        container.add(new Load(InstrType.la, rs, null, MipsController.getAddr(varSymbol)));
//                        container.add(new Load(InstrType.lw, rs, null, rs));
//                    } else {
//                        container.add(new Load(InstrType.lw, rs, MipsController.getAddr(((VarSymbol) operand1)), GRF.getReg("sp")));
//                    }
//                } else {
//                    throw new IllegalStateException("不是varsymbol，也不是整数，还能是啥" + operand1.getClass());
//                }
            }
            Register rt = GRF.getTReg();
            rt.setOccupied();
            if (operand2 instanceof IntegerValue integer) {
                container.add(new Load(InstrType.li, rt, null, new Immediate(integer.getDim0Value())));
            } else {
                getOperand(operand2,rt,container);
            }

//
//            else if (operand2 instanceof VarSymbol var2) {
//                if (var2.isGlobal()) {
//                    container.add(new Load(InstrType.la, rt, null, MipsController.getAddr(var2)));
//                    container.add(new Load(InstrType.lw, rt, null, rt));
//                } else {
//                    container.add(new Load(InstrType.lw, rt, MipsController.getAddr(((VarSymbol) operand2)), GRF.getReg("sp")));
//                }
//            }

            Operand lValAddr = MipsController.getAddr(lVal);
            if (op.equals("+") || op.equals("-") || op.equals("!")) {
                container.add(new Alu(type, rd, rs, rt));
                rs.release();
                rt.release();
                save(rd, lValAddr, container);
                rd.release();
            } else if (op.equals("*") || op.equals("/") || op.equals("%")) {
                container.add(new MD(type, rs, rt));
                rs.release();
                rt.release();
                Register tmpResult = GRF.getTReg();
                tmpResult.setOccupied();
                switch (op) {
                    case "*", "/" -> {
                        container.add(new MF(InstrType.mflo, tmpResult));
                        save(tmpResult, lValAddr, container);
                    }
                    case "%" -> {
                        container.add(new MF(InstrType.mfhi, tmpResult));
                        save(tmpResult, lValAddr, container);
                    }
                }
                tmpResult.release();
            } else {
                rs.release();
                rt.release();
                rd.release();
            }
            rs.release();
            rt.release();
            rd.release();
        }
        return container;
    }


    public void save(Register src, Operand offset, List<MipsCode> container) {
        if (lVal.isGlobal()) {
            Register t = GRF.getTReg();
            container.add(new Load(InstrType.la, t, null, offset));
            container.add(new Store(InstrType.sw, src, null, t));
        } else {
            container.add(new Store(InstrType.sw, src, offset, GRF.getReg("sp")));
        }
    }

    public boolean isR_I() {
        if (operand1 instanceof PointerValue || operand2 instanceof PointerValue) {
            return false;
        }
        if (op.equals("<<") || op.equals(">>")) {
            return true;
        }
        if (!(op.equals("+") || op.equals("-"))) {
            return false;
        }
        if (operand1 instanceof IntegerValue) {
            if (op.equals("+")) {
                Value tmp = operand1;
                operand1 = operand2;
                operand2 = tmp;
            }
        }
        return operand2 instanceof IntegerValue;
    }

    /*****************优化相关***************************/

    public VarSymbol getLVal() {
        return lVal;
    }

    public Value getOperand1() {
        return operand1;
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

    public Value getOperand2() {
        return operand2;
    }

    public void setOperand2(Value operand2) {
        this.operand2 = operand2;
    }

    public Instruction optimize() {
        return this;
    }


    public Instruction reconstruct(Value tmp, Value newA) {
        // tmp = a; x = b + tmp;
        Value newOp1 = this.operand1;
        Value newOp2 = this.operand2;
        boolean flag = false;
        if (operand1.equals(tmp)) {
            flag = true;
            newOp1 = newA;
        }
        if (operand1 instanceof SymbolValue arr) {
            if (tmp instanceof IntegerValue || tmp instanceof VarSymbol) {
                if (arr.getOffset() != null && arr.getOffset().equals(tmp)) {
                    flag = true;
                    newOp1 = new SymbolValue(arr.getSymbol(),newA,arr.getDim());
                }
            }
        }
        if (operand2.equals(tmp)) {
            flag = true;
            newOp2 = newA;
        }
        if (operand2 instanceof SymbolValue arr) {
            if (tmp instanceof IntegerValue || tmp instanceof VarSymbol) {
                if (arr.getOffset() != null && arr.getOffset().equals(tmp)) {
                    flag = true;
                    newOp2 = new SymbolValue(arr.getSymbol(),newA,arr.getDim());
                }
            }
        }
        if (flag) {
            if (newOp1 instanceof IntegerValue int1 && newOp2 instanceof IntegerValue int2) {
                int res = CalOptimizer.getRes(op,int1.getDim0Value(),int2.getDim0Value());
                return new ValAssign(this.lVal,new IntegerValue(res));
            }
            return new Calculate(this.lVal, this.op, newOp1, newOp2);
        } else {
            return this;
        }
    }

    public Instruction reassign(VarSymbol newLeft) {
        // tmp = a + b; c = tmp
        if (operand1.equals(newLeft) || operand2.equals(newLeft)) {
            return this;
        }
        if (this.lVal.equals(newLeft)) {
            return new Calculate(newLeft, this.op, this.operand1, this.operand2);
        } else {
            return this;
        }
    }


    @Override
    public void DAGoptimize(DAG dag) {
        dag.parse(operand1, operand2, lVal, DAG.getOp(op));
    }
}
