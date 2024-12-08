package MidCode.Instructions;


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

import java.util.List;

public class Input extends Instruction{
    private Value value;

    public Input(Value value) {
        this.value = value;
    }

    @Override
    public String toString() {
        //return "getint,_,_," + value.toString() + "\n";
        return value.toString() + " = " + "getint()\n";
    }

    public List<MipsCode> toMipsCode() {
        mipsCodes.add(new Annotation(this.toString()));

        mipsCodes.add(new Load(InstrType.li, GRF.getReg("v0"), null ,new Immediate(5)));
        mipsCodes.add(new Syscall(InstrType.syscall));
        if (value instanceof VarSymbol var){
            Operand addr = MipsController.getAddr(var);
            if (var.isGlobal()) {
                Register reg = GRF.getTReg();
                mipsCodes.add(new Load(InstrType.la,reg,null,addr));
                mipsCodes.add(new Store(InstrType.sw,GRF.getReg("v0"),null,reg));
            } else {
                mipsCodes.add(new Store(InstrType.sw,GRF.getReg("v0"),addr,GRF.getReg("sp")));
            }
        } else if (value instanceof SymbolValue var) {
            Register lReg = GRF.getSReg();
            lReg.setOccupied();
            Operand addr = MipsController.getAddr(var.getSymbol());
            if (var.isGlobal()) {
                mipsCodes.add(new Load(InstrType.la,lReg,null,addr));
                if (var.getOffset() instanceof IntegerValue intOff) {
                    mipsCodes.add(new Store(InstrType.sw,GRF.getReg("v0"),new Immediate(4* intOff.getDim0Value()),lReg));
                } else if (var.getOffset() instanceof VarSymbol varOff) {
                    Operand offAddr = MipsController.getAddr(varOff);
                    Register offReg = GRF.getTReg();
                    offReg.setOccupied();
                    if (varOff.isGlobal()) {
                        mipsCodes.add(new Load(InstrType.la,offReg,null,offAddr));
                        mipsCodes.add(new Load(InstrType.lw,offReg,null,offReg));
                        mipsCodes.add(new Alu(InstrType.sll,offReg,offReg,new Immediate(2)));
                        mipsCodes.add(new Alu(InstrType.addu,lReg,lReg,offReg));
                        mipsCodes.add(new Store(InstrType.sw,GRF.getReg("v0"),null,lReg));
                    } else {
                        mipsCodes.add(new Load(InstrType.lw,offReg,offAddr,GRF.getReg("sp")));
                        mipsCodes.add(new Alu(InstrType.sll,offReg,offReg,new Immediate(2)));
                        mipsCodes.add(new Alu(InstrType.addu,lReg,lReg,offReg));
                        mipsCodes.add(new Store(InstrType.sw,GRF.getReg("v0"),null,lReg));
                    }
                    offReg.release();
                } else {
                    throw new RuntimeException(String.valueOf(var.getOffset().getClass()));
                }
            } else {
                if (var.getOffset() instanceof IntegerValue intOff) {
                    int off = ((Immediate)addr).getNumber() + 4*intOff.getDim0Value();
                    //mipsCodes.add(new Alu(InstrType.addi,lReg,lReg,new Immediate(4*intOff.getDim0Value())));
                    mipsCodes.add(new Store(InstrType.sw,GRF.getReg("v0"),new Immediate(off),GRF.getReg("sp")));
                } else if (var.getOffset() instanceof VarSymbol varOff) {
                    Operand offAddr = MipsController.getAddr(varOff);
                    Register offReg = GRF.getTReg();
                    offReg.setOccupied();
                    if (varOff.isGlobal()) {
                        mipsCodes.add(new Load(InstrType.la,offReg,null,offAddr));
                        mipsCodes.add(new Load(InstrType.lw,offReg,null,offReg));
                        mipsCodes.add(new Alu(InstrType.sll,offReg,offReg,new Immediate(2)));
                        mipsCodes.add(new Alu(InstrType.addiu,lReg, offReg,addr)); // 在栈上的偏移
                        mipsCodes.add(new Alu(InstrType.addu,lReg,lReg,GRF.getReg("sp")));
                        mipsCodes.add(new Store(InstrType.sw,GRF.getReg("v0"),null ,lReg));
                    } else {
                        int totalOff = ((Immediate)addr).getNumber() + ((Immediate)offAddr).getNumber();
                        mipsCodes.add(new Store(InstrType.sw,GRF.getReg("v0"),new Immediate(totalOff),GRF.getReg("sp")));
                    }
                    offReg.release();
                } else {
                    throw new RuntimeException(String.valueOf(var.getOffset().getClass()));
                }
            }
            lReg.release();
        }
        return mipsCodes;
    }
}
