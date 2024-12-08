package MidCode.Instructions;

import MidCode.Table.FuncSymbol;
import MidCode.Table.Symbol;
import MidCode.Table.VarSymbol;
import MidCode.Value.IntegerValue.IntegerValue;
import MidCode.Value.SymbolValue;
import MidCode.Value.Value;
import Target.GRF.GRF;
import Target.GRF.Register;
import Target.Immediate;
import Target.Instructions.*;
import Target.Operand;
import Target.Symbol.MipsController;
import Target.Symbol.MipsSymbol;

import java.util.List;

public class ValAssign extends Instruction {
    private String LVal;
    private Symbol lSym;
    private Value RVal;

    public ValAssign(String LVal, Symbol lSym, Value RVal) {
        this.LVal = LVal;
        this.RVal = RVal;
        this.lSym = lSym;
    }

    @Override
    public String toString() {
        //return "=, " + RVal.toString() + ", , " + LVal + "\n";
        return LVal + " = " + RVal.toString() + "\n";
    }

    public Register localToMips() {
        Register rReg = GRF.getTReg();
        rReg.setOccupied();
        if (RVal instanceof SymbolValue rVal && rVal.isParam()) {
            Operand rValBase = MipsController.getAddr(rVal.getSymbol());
            mipsCodes.add(new Load(InstrType.lw, rReg, rValBase,GRF.getReg("sp"))); // rReg = A*
            if (rVal.getOffset() instanceof IntegerValue integer) {
                mipsCodes.add(new Alu(InstrType.addiu,rReg,rReg,new Immediate(4 * integer.getDim0Value())));
                mipsCodes.add(new Load(InstrType.lw,rReg,null,rReg));
            } else if (rVal.getOffset() instanceof VarSymbol offSym) {
                Register off = GRF.getTReg();
                off.setOccupied();
                Operand offAddr = MipsController.getAddr(offSym);
                if (offSym.isGlobal()) {
                    mipsCodes.add(new Load(InstrType.la, off,null,offAddr));
                    mipsCodes.add(new Load(InstrType.lw,off,null,off));
                } else {
                    mipsCodes.add(new Load(InstrType.lw,off,offAddr,GRF.getReg("sp")));
                }
                mipsCodes.add(new Alu(InstrType.sll,off,off,new Immediate(2)));
                mipsCodes.add(new Alu(InstrType.addu,rReg,rReg,off));
                mipsCodes.add(new Load(InstrType.lw,rReg,null,rReg));
                off.release();
            } else if (rVal.getOffset() == null) {
              // 传地址，之前穿过了
              //mipsCodes.add(new Load(InstrType.lw,rReg,rValBase,GRF.getReg("sp")));
            } else {
                throw new IllegalStateException("偏移量还能是 " + rVal.getOffset().getClass());
            }
            return rReg;
        }
        if (RVal instanceof SymbolValue rVal) {
            Operand rValBase = MipsController.getAddr(rVal.getSymbol()); // 数组[0]在sp的地址
            if (rVal.getOffset() instanceof IntegerValue integer) {
                int off = integer.getDim0Value();
                int totalOff = ((Immediate) rValBase).getNumber() + 4 * off;
                mipsCodes.add(new Load(InstrType.lw, rReg, new Immediate(totalOff), GRF.getReg("sp")));
            } else if (rVal.getOffset() instanceof VarSymbol offSym) {
                Register off = GRF.getTReg();
                off.setOccupied();
                Operand offAddr = MipsController.getAddr(offSym);
                if (offSym.isGlobal()) {
                    mipsCodes.add(new Load(InstrType.la, off,null,offAddr));
                    mipsCodes.add(new Load(InstrType.lw,off,null,off));
                } else {
                    mipsCodes.add(new Load(InstrType.lw,off,offAddr,GRF.getReg("sp")));
                }
                mipsCodes.add(new Alu(InstrType.sll,off,off,new Immediate(2)));
                mipsCodes.add(new Alu(InstrType.addiu,rReg,off,rValBase));
                mipsCodes.add(new Alu(InstrType.addu,rReg,rReg,GRF.getReg("sp")));
                mipsCodes.add(new Load(InstrType.lw,rReg,null,rReg));
                off.release();
            } else if (rVal.getOffset() == null) {
                // 传地址了老铁
                mipsCodes.add(new Alu(InstrType.addiu,rReg,GRF.getReg("sp"),MipsController.getAddr(rVal.getSymbol())));
            }else {
                throw new IllegalStateException("偏移量还能是 " + rVal.getOffset().getClass());
            }
        } else if (RVal instanceof VarSymbol rVal) {
            // lw $t0 4($sp)
            mipsCodes.add(new Load(InstrType.lw, rReg,
                    MipsController.getAddr(rVal),
                    GRF.getReg("sp")));
        } else {
            System.out.println(1);
            throw new IllegalStateException("不是数字，也不是变量，也不是函数，还能是啥" + RVal.getClass());
        }
        return rReg;
    }

    public Register globalToMips() {
        Register rReg = GRF.getSReg();
        rReg.setOccupied();
        if (RVal instanceof SymbolValue rVal) {
            Operand rValBase = MipsController.getAddr(rVal.getSymbol());
            if (rVal.getOffset() instanceof IntegerValue integer) {
                mipsCodes.add(new Load(InstrType.la, rReg, null, rValBase));
                mipsCodes.add(new Load(InstrType.lw,rReg,new Immediate(4*integer.getDim0Value()),rReg));
            } else if (rVal.getOffset() instanceof VarSymbol offSym) {
                Register off = GRF.getTReg();
                off.setOccupied();
                Operand offAddr = MipsController.getAddr(offSym);
                mipsCodes.add(new Load(InstrType.la, rReg, null, rValBase));
                if (offSym.isGlobal()) {
                    mipsCodes.add(new Load(InstrType.la, off, null, offAddr)); // off <= index的地址
                    mipsCodes.add(new Load(InstrType.lw, off, null, off)); // off <= index
                    mipsCodes.add(new Alu(InstrType.sll, off, off, new Immediate(2))); // off <= offset
                    mipsCodes.add(new Alu(InstrType.addu, rReg, rReg, off)); // rreg <= leftVa
                    mipsCodes.add(new Load(InstrType.lw, rReg, null, rReg));
                } else {
                    mipsCodes.add(new Load(InstrType.lw, off,offAddr, GRF.getReg("sp"))); // off <= index
                    mipsCodes.add(new Alu(InstrType.sll, off, off, new Immediate(2)));
                    mipsCodes.add(new Alu(InstrType.addu,rReg,rReg,off));
                    mipsCodes.add(new Load(InstrType.lw, rReg, null, rReg));
                }
                off.release();
            } else if (rVal.getOffset() == null) {
                mipsCodes.add(new Load(InstrType.la,rReg,null,MipsController.getAddr(rVal.getSymbol())));
            }else {
                throw new IllegalStateException("偏移量还能是 " + rVal.getOffset().getClass());
            }
        } else if (RVal instanceof VarSymbol rVal) {
            Operand rValAddr = MipsController.getAddr(rVal);
            mipsCodes.add(new Load(InstrType.la, rReg, null, rValAddr));
            mipsCodes.add(new Load(InstrType.lw, rReg, null, rReg));
        } else {
            System.out.println(1);
            throw new IllegalStateException("不是数字，也不是变量，也不是函数，还能是啥" + RVal.getClass());
        }
        return rReg;
    }

    public List<MipsCode> toMipsCode() {
        mipsCodes.add(new Annotation(this.toString()));
        // getRVal
        Register rReg;
        if (RVal instanceof IntegerValue) {
            // a = 1
            int integer = ((IntegerValue) RVal).getDim0Value();
            // li $t1 1
            rReg = GRF.getTReg();
            mipsCodes.add(new Load(InstrType.li, rReg, null, new Immediate(integer)));
            // sw $t1, 0($sp)
        } else if (RVal instanceof SymbolValue val) {
            if (val.isGlobal()) {
                rReg = globalToMips();
            } else {
                rReg = localToMips();
            }
        } else if (RVal instanceof VarSymbol val) {
            if (val.isGlobal()) {
                rReg = globalToMips();
            } else {
                rReg = localToMips();
            }
        } else if (RVal instanceof FuncSymbol) {
            rReg = GRF.getTReg();
            mipsCodes.add(new Move(InstrType.move, rReg, GRF.getReg("v0")));
        } else {
            throw new IllegalStateException("RVAl 还能是什么类型啊！" + RVal.getClass());
        }
        // getLVal
        MipsSymbol lVal = MipsController.getSym(lSym);
        if (lSym.isGlobal()) {
            Register s = GRF.getSReg();
            mipsCodes.add(new Load(InstrType.la, s, null, lVal.getAddrLabel()));
            mipsCodes.add(new Store(InstrType.sw, rReg, null, s));
        } else {
            int lValSpOffset = lVal.getSpOffset();
            mipsCodes.add(new Store(InstrType.sw, rReg, new Immediate(lValSpOffset), GRF.getReg("sp")));
        }
        rReg.release();
        return mipsCodes;
    }
}
