package MidCode.Instructions;

import MidCode.Table.Symbol;
import MidCode.Table.VarSymbol;
import MidCode.Value.IntegerValue.IntegerValue;
import MidCode.Value.Value;
import Target.GRF.GRF;
import Target.GRF.Register;
import Target.Immediate;
import Target.Instructions.*;
import Target.Operand;
import Target.Symbol.MipsController;
import Target.Symbol.MipsSymbol;

import java.util.List;

public class MemCpy extends Instruction {
    private String name;
    private Symbol leftSym;
    private Value index;
    private Value RVal;

    public MemCpy(String name, Value index, Value RVal, Symbol leftSym) {
        this.name = name;
        this.index = index;
        this.RVal = RVal;
        this.leftSym = leftSym;
    }

    @Override
    public String toString() {
        //return "=, " + name + ", " + index.toString() + ", " + RVal.toString() + "\n";
        return name + "[" + index.toString() + "] = " + RVal.toString() + "\n";
    }

    public List<MipsCode> globalToMips() {
        Register rVal = GRF.getTReg();
        rVal.setOccupied();
        if (RVal instanceof VarSymbol var) {
            Operand rValAddr = MipsController.getAddr(var);
            if (var.isGlobal()) {
                mipsCodes.add(new Load(InstrType.la,rVal,null,rValAddr));
                mipsCodes.add(new Load(InstrType.lw,rVal,null,rVal));
            } else {
                mipsCodes.add(new Load(InstrType.lw, rVal, rValAddr, GRF.getReg("sp")));
            }
        } else if (RVal instanceof IntegerValue integer) {
            mipsCodes.add(new Load(InstrType.li, rVal, null, new Immediate(integer.getDim0Value())));
        } else {
            throw new IllegalStateException("还能是啥！" + index.getClass());
        }
        /**************以上为获取了等式右边的值，接下来获取等式左边的地址***************/

        Register lReg;
        lReg = GRF.getTReg();
        lReg.setOccupied();
        MipsSymbol lSym = MipsController.getSym(leftSym);
        mipsCodes.add(new Load(InstrType.la, lReg, null, lSym.getAddrLabel()));
        if (index instanceof VarSymbol offSym) {
            Register off = GRF.getTReg();
            off.setOccupied();
            Operand offAddr = MipsController.getAddr(offSym);
            if (offSym.isGlobal()) {
                mipsCodes.add(new Load(InstrType.la, off, null, offAddr)); // off <= index的地址
                mipsCodes.add(new Load(InstrType.lw, off, null, off)); // off <= index
                mipsCodes.add(new Alu(InstrType.sll,off,off,new Immediate(2)));
                mipsCodes.add(new Alu(InstrType.addu,lReg,lReg,off));
                mipsCodes.add(new Store(InstrType.sw,rVal,null,lReg));
            } else {
                mipsCodes.add(new Load(InstrType.lw,off,offAddr,GRF.getReg("sp")));
                mipsCodes.add(new Alu(InstrType.sll,off,off,new Immediate(2)));
                mipsCodes.add(new Alu(InstrType.addu,lReg,lReg,off));
                mipsCodes.add(new Store(InstrType.sw,rVal,null,lReg));
            }
            off.release();
        } else if (index instanceof IntegerValue integer) {
            mipsCodes.add(new Store(InstrType.sw,rVal,new Immediate(integer.getDim0Value() * 4),lReg));
        } else {
            throw new IllegalStateException("还能是啥！" + index.getClass());
        }
        rVal.release();
        lReg.release();
        return mipsCodes;
    }

    public List<MipsCode> localToMips() {

        Register rVal = GRF.getTReg();
        rVal.setOccupied();
        if (RVal instanceof VarSymbol var) {
            Operand rValAddr = MipsController.getAddr(var);
            if (var.isGlobal()) {
                mipsCodes.add(new Load(InstrType.la, rVal, null, rValAddr));
                mipsCodes.add(new Load(InstrType.lw, rVal, null, rVal));
            } else {
                mipsCodes.add(new Load(InstrType.lw, rVal, rValAddr, GRF.getReg("sp")));
            }
        } else if (RVal instanceof IntegerValue integer) {
            mipsCodes.add(new Load(InstrType.li, rVal, null, new Immediate(integer.getDim0Value())));
        } else {
            throw new IllegalStateException("还能是啥！" + index.getClass());
        }
        /**************以上为获取了等式右边的值，接下来获取等式左边的地址***************/
        Register lReg;
        lReg = GRF.getTReg();
        lReg.setOccupied();
        Operand leftBaseAddr = MipsController.getAddr(leftSym);
        if (((VarSymbol)leftSym).isParam()) {
            mipsCodes.add(new Load(InstrType.lw,lReg,leftBaseAddr,GRF.getReg("sp"))); // lreg = &a[0]
            if (index instanceof VarSymbol offSym) {
                Register offReg = GRF.getTReg();
                offReg.setOccupied();
                Operand offAddr = MipsController.getAddr(offSym);
                if (offSym.isGlobal()) {
                    mipsCodes.add(new Load(InstrType.la,offReg,null,offAddr));
                    mipsCodes.add(new Load(InstrType.lw, offReg, null, offAddr)); // offReg <= index
                    mipsCodes.add(new Alu(InstrType.sll,offReg,offReg,new Immediate(2)));
                    mipsCodes.add(new Alu(InstrType.addu, lReg,lReg,offReg)); // lreg <= &a[i]
                    mipsCodes.add(new Store(InstrType.sw,rVal,null,lReg));
                } else {
                    mipsCodes.add(new Load(InstrType.lw,offReg,offAddr,GRF.getReg("sp")));
                    //mipsCodes.add(new Load(InstrType.lw,offReg,null,offAddr));
                    mipsCodes.add(new Alu(InstrType.sll,offReg,offReg,new Immediate(2)));
                    mipsCodes.add(new Alu(InstrType.addu,lReg,lReg,offReg));
                    mipsCodes.add(new Store(InstrType.sw,rVal,null,lReg));
                }
                offReg.release();
            } else if (index instanceof IntegerValue intOff) {
                int offByte = intOff.getDim0Value() * 4;
                mipsCodes.add(new Alu(InstrType.addiu,lReg,lReg,new Immediate(offByte)));
                mipsCodes.add(new Store(InstrType.sw,rVal,null,lReg));
            } else {
                throw new IllegalStateException("index的种类还能是"+index.getClass());
            }
            lReg.release();
            rVal.release();
            return mipsCodes;
        }

        int totalOff;
        if (index instanceof VarSymbol offSym) {
            Operand offAddr = MipsController.getAddr(offSym);
            if (offSym.isGlobal()) {
                mipsCodes.add(new Load(InstrType.la, lReg, null, offAddr)); // lreg <= index的地址
                mipsCodes.add(new Load(InstrType.lw, lReg, null, lReg)); // lreg <= index
                mipsCodes.add(new Alu(InstrType.sll, lReg, lReg, new Immediate(2))); // lreg <= offset
                mipsCodes.add(new Alu(InstrType.addiu, lReg, lReg, leftBaseAddr)); // lreg <= offset + base
                mipsCodes.add(new Alu(InstrType.addu,lReg,lReg,GRF.getReg("sp")));
                mipsCodes.add(new Store(InstrType.sw,rVal,null,lReg));
            } else {
                mipsCodes.add(new Load(InstrType.lw,lReg,offAddr,GRF.getReg("sp")));
                mipsCodes.add(new Alu(InstrType.sll,lReg,lReg,new Immediate(2)));
                mipsCodes.add(new Alu(InstrType.addiu,lReg,lReg,leftBaseAddr));
                mipsCodes.add(new Alu(InstrType.addu,lReg,lReg,GRF.getReg("sp")));
                mipsCodes.add(new Store(InstrType.sw,rVal,null,lReg));
                //totalOff = ((Immediate)offAddr).getNumber() + ((Immediate)leftBaseAddr).getNumber();
                //mipsCodes.add(new Store(InstrType.sw,rVal,new Immediate(totalOff),GRF.getReg("sp")));
            }
        } else if (index instanceof IntegerValue integer) {
            totalOff = ((Immediate) leftBaseAddr).getNumber() + 4 * integer.getDim0Value();
            mipsCodes.add(new Store(InstrType.sw,rVal,new Immediate(totalOff),GRF.getReg("sp")));
        } else {
            throw new IllegalStateException("还能是啥！" + index.getClass());
        }
        rVal.release();
        lReg.release();
        return mipsCodes;
    }

    public List<MipsCode> toMipsCode() {
        mipsCodes.add(new Annotation(this.toString()));

        if (leftSym.isGlobal()) {
            return globalToMips();
        } else {
            return localToMips();
        }
    }
}
