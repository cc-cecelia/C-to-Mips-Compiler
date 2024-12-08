package MidCode.Instructions;

import MidCode.Optimize.DAGOptimizer.DAG;
import MidCode.Table.VarSymbol;
import MidCode.Value.IntegerValue.IntegerValue;
import MidCode.Value.SymbolValue;
import MidCode.Value.Value;
import Target.GRF.GRF;
import Target.GRF.Register;
import Target.Immediate;
import Target.Instructions.Alu;
import Target.Instructions.InstrType;
import Target.Instructions.Load;
import Target.Instructions.MipsCode;
import Target.Operand;
import Target.Symbol.MipsController;

import java.util.ArrayList;
import java.util.List;

public abstract class Instruction {

    public abstract String toString();
    protected List<MipsCode> mipsCodes = new ArrayList<>();
    protected List<MipsCode> optimizedMips = new ArrayList<>();

    public abstract List<MipsCode> toMipsCode(boolean isOptimized);
    public abstract Instruction optimize();
    public abstract void DAGoptimize(DAG dag);

    public Instruction reconstruct(Value tmp, Value newA) {
        return this;
    }

    public Instruction reassign(VarSymbol varSymbol) {
        return this;
    }

    protected Register getOperand(Value operand1, Register targetReg, List<MipsCode> container) {
        if (operand1 instanceof SymbolValue val) {
            Register offTmp = GRF.getTReg();
            Operand base = MipsController.getAddr(val.getSymbol());
            if (val.isGlobal()) {
                container.add(new Load(InstrType.la,targetReg,null,base));
                if (val.getOffset() instanceof VarSymbol varOff) {
                    Operand offAddr = MipsController.getAddr(varOff);
                    if (varOff.isGlobal()) {
                        container.add(new Load(InstrType.la, offTmp, null, offAddr));
                        container.add(new Load(InstrType.lw,offTmp,null,targetReg));
                    } else {
                        container.add(new Load(InstrType.lw,offTmp,offAddr,GRF.getReg("sp")));
                    }
                    container.add(new Alu(InstrType.sll,offTmp,offTmp,new Immediate(2)));
                    container.add(new Alu(InstrType.addu,targetReg,targetReg,offTmp));
                    container.add(new Load(InstrType.lw,targetReg,null,targetReg));
                } else if (val.getOffset() instanceof IntegerValue intOff) {
                    int totalOff = intOff.getDim0Value() * 4;
                    container.add(new Load(InstrType.lw,targetReg,new Immediate(totalOff),targetReg));
                }
            }
            else {
                Operand baseAddr = MipsController.getAddr(val.getSymbol());
                if (val.isParam()) {
                    container.add(new Load(InstrType.lw,targetReg,baseAddr,GRF.getReg("sp"))); // rs存的是数组首地址
                    if (val.getOffset() instanceof VarSymbol varOff) {
                        Register offReg = GRF.getTReg();
                        Operand offAddr = MipsController.getAddr(varOff);
                        if (varOff.isGlobal()) {
                            container.add(new Load(InstrType.la,offReg, null, offAddr));
                            container.add(new Load(InstrType.lw,offReg, null, offReg));
                            container.add(new Alu(InstrType.sll,offReg,offReg,new Immediate(2)));
                            container.add(new Alu(InstrType.addu,targetReg, targetReg, offReg));
                            container.add(new Load(InstrType.lw,targetReg, null ,targetReg));
                        } else {
                            container.add(new Load(InstrType.lw,offReg,offAddr,GRF.getReg("sp")));
                            container.add(new Alu(InstrType.sll,offReg,offReg,new Immediate(2)));
                            container.add(new Alu(InstrType.addu,targetReg, targetReg, offReg));
                            container.add(new Load(InstrType.lw,targetReg,null,targetReg));
                        }
                    }
                    else if (val.getOffset() instanceof IntegerValue intOff) {
                        int total = 4 * intOff.getDim0Value();
                        container.add(new Load(InstrType.lw,targetReg,new Immediate(total),targetReg));
                    }
                }
                else {
                    if (val.getOffset() instanceof VarSymbol varOff) {
                        Operand offAddr = MipsController.getAddr(varOff);
                        if (varOff.isGlobal()) {
                            container.add(new Load(InstrType.la,targetReg,null,offAddr));
                            container.add(new Load(InstrType.lw,targetReg,null, targetReg));
                        } else {
                            container.add(new Load( InstrType.lw, targetReg,offAddr,GRF.getReg("sp")));
                        }
                        container.add(new Alu(InstrType.sll,targetReg, targetReg,new Immediate(2))); // rs 存的是total偏移比如说16
                        container.add(new Alu(InstrType.addiu, targetReg, targetReg, baseAddr));
                        container.add(new Alu(InstrType.addu,targetReg,targetReg,GRF.getReg("sp")));
                        container.add(new Load(InstrType.lw,targetReg, null ,targetReg));
                    }
                    else if (val.getOffset() instanceof IntegerValue intOff) {
                        int totalSp= ((Immediate)baseAddr).getNumber() + 4 * intOff.getDim0Value();
                        container.add(new Load(InstrType.lw,targetReg,new Immediate(totalSp),GRF.getReg("sp")));
                    }
                }
            }
        } else if (operand1 instanceof VarSymbol) {
            if (((VarSymbol) operand1).isGlobal()) {
                container.add(new Load(InstrType.la, targetReg, null, MipsController.getAddr((VarSymbol) operand1)));
                container.add(new Load(InstrType.lw, targetReg, null, targetReg));
            } else {
                container.add(new Load(InstrType.lw, targetReg,
                        MipsController.getAddr(((VarSymbol) operand1)),
                        GRF.getReg("sp"))); // rs = op1
            }
        }
        return targetReg;
    }
}
