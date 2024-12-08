package MidCode.Instructions;

import MidCode.Table.VarSymbol;
import MidCode.Value.IntegerValue.IntegerValue;
import MidCode.Value.PointerValue;
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

    public List<MipsCode> toMipsCode() {
        mipsCodes.add(new Annotation(this.toString()));
        InstrType type;
        Register rd = GRF.getTReg();
        rd.setOccupied();
        if (isR_I()) {
            //Register rs = (Register) MipsController.getSym(((VarSymbol)operand1).getMidName()).getUser();
            Register rs = GRF.getTReg();
            if (((VarSymbol) operand1).isGlobal()) {
                mipsCodes.add(new Load(InstrType.la, rs, null, MipsController.getAddr((VarSymbol) operand1)));
                mipsCodes.add(new Load(InstrType.lw, rs, null, rs));
            } else {
                mipsCodes.add(new Load(InstrType.lw, rs,
                        MipsController.getAddr(((VarSymbol) operand1)),
                        GRF.getReg("sp"))); // rs = op1
            }
            int imm = switch (op) {
                case "+" -> ((IntegerValue) operand2).getDim0Value();
                case "-" -> -((IntegerValue) operand2).getDim0Value();
                default -> throw new IllegalStateException("Unexpected value: " + op);
            };
            mipsCodes.add(new Alu(InstrType.addiu, rd, rs, new Immediate(imm)));
            Operand lValAddr = MipsController.getAddr(lVal);
            save(rd, lValAddr);
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
                case "<=" ->InstrType.sle;
                case "!=" ->InstrType.sne;
                default -> throw new IllegalStateException("Unexpected value: " + op);
            };
            Register rs = GRF.getTReg();
            if (type == InstrType.seq) {
                rs = GRF.getReg("0"); // 若rt = 0 -> 1 若rt != 0 -> 0
            } else {
                if (operand1 instanceof IntegerValue integer) {
                    mipsCodes.add(new Load(InstrType.li, rs, null, new Immediate(integer.getDim0Value())));
                } else if (operand1 instanceof PointerValue pointer) {
                    if (pointer.isGlobal()) {
                        mipsCodes.add(new Load(InstrType.la, rs, null, MipsController.getAddr(pointer.getSymbol())));
                    } else {
                        if (pointer.getSymbol().isParam()) {
                            mipsCodes.add(new Load(InstrType.lw, rs, MipsController.getAddr(pointer.getSymbol()), GRF.getReg("sp")));
                        } else {
                            mipsCodes.add(new Alu(InstrType.addiu, rs, GRF.getReg("sp"), MipsController.getAddr(pointer.getSymbol())));
                        }
                        //mipsCodes.add(new Load(InstrType.li,rs,null,MipsController.getAddr(pointer.getSymbol())));
                        //mipsCodes.add(new Load(InstrType.lw,rs,MipsController.getAddr(pointer.getSymbol()),GRF.getReg("sp")));
                    }
                } else if (operand1 instanceof VarSymbol varSymbol) {
                    if (varSymbol.isGlobal()) {
                        mipsCodes.add(new Load(InstrType.la, rs, null, MipsController.getAddr(varSymbol)));
                        mipsCodes.add(new Load(InstrType.lw, rs, null, rs));
                    } else {
                        mipsCodes.add(new Load(InstrType.lw, rs, MipsController.getAddr(((VarSymbol) operand1)), GRF.getReg("sp")));
                    }
                } else {
                    throw new IllegalStateException("不是varsymbol，也不是整数，还能是啥" + operand1.getClass());
                }
                rs.setOccupied();
            }
            Register rt;

            rt = GRF.getTReg();
            if (operand2 instanceof IntegerValue integer) {
                mipsCodes.add(new Load(InstrType.li, rt, null, new Immediate(integer.getDim0Value())));
            } else if (operand2 instanceof VarSymbol var2) {
                if (var2.isGlobal()) {
                    mipsCodes.add(new Load(InstrType.la, rt, null, MipsController.getAddr(var2)));
                    mipsCodes.add(new Load(InstrType.lw, rt, null, rt));
                } else {
                    mipsCodes.add(new Load(InstrType.lw, rt, MipsController.getAddr(((VarSymbol) operand2)), GRF.getReg("sp")));
                }
            }
            rt.setOccupied();

            Operand lValAddr = MipsController.getAddr(lVal);
            if (op.equals("+") || op.equals("-") || op.equals("!")) {
                mipsCodes.add(new Alu(type, rd, rs, rt));
                rs.release();
                rt.release();
                save(rd, lValAddr);
                rd.release();
            } else if (op.equals("*") || op.equals("/") || op.equals("%")) {
                mipsCodes.add(new MD(type, rs, rt));
                rs.release();
                rt.release();
                Register tmpResult = GRF.getTReg();
                tmpResult.setOccupied();
                switch (op) {
                    case "*", "/" -> {
                        mipsCodes.add(new MF(InstrType.mflo, tmpResult));
                        save(tmpResult, lValAddr);
                    }
                    case "%" -> {
                        mipsCodes.add(new MF(InstrType.mfhi, tmpResult));
                        save(tmpResult, lValAddr);
                    }
                }
                tmpResult.release();
            }
            rs.release();
            rt.release();
            rd.release();
        }

        return mipsCodes;
    }

    public void save(Register src, Operand offset) {
        if (lVal.isGlobal()) {
            Register t = GRF.getTReg();
            mipsCodes.add(new Load(InstrType.la, t, null, offset));
            mipsCodes.add(new Store(InstrType.sw, src, null, t));
        } else {
            mipsCodes.add(new Store(InstrType.sw, src, offset, GRF.getReg("sp")));
        }
    }

    public boolean isR_I() {
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
}
