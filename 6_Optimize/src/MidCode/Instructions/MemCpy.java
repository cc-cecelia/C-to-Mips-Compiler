package MidCode.Instructions;

import MidCode.Optimize.DAGOptimizer.DAG;
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

    public MemCpy(Symbol leftSym,Value index,Value RVal){
        this.leftSym = leftSym;
        this.index = index;
        this.RVal = RVal;
        this.name = leftSym.getMidName();
    }
    @Override
    public String toString() {
        //return "=, " + name + ", " + index.toString() + ", " + RVal.toString() + "\n";
        return name + "[" + index.toString() + "] = " + RVal.toString() + "\n";
    }

    public List<MipsCode> globalToMips(boolean isOptimized) {
        List<MipsCode> container = isOptimized ? optimizedMips : this.mipsCodes;

        Register rVal = GRF.getTReg();
        rVal.setOccupied();
        if (RVal instanceof IntegerValue integerValue) {
            container.add(new Load(InstrType.li,rVal,null,new Immediate(integerValue.getDim0Value())));
        } else {
            getOperand(RVal, rVal, container);
        }
        /**************以上为获取了等式右边的值，接下来获取等式左边的地址***************/

        Register lReg;
        lReg = GRF.getTReg();
        lReg.setOccupied();
        MipsSymbol lSym = MipsController.getSym(leftSym);
        container.add(new Load(InstrType.la, lReg, null, lSym.getAddrLabel()));
        if (index instanceof VarSymbol offSym) {
            Register off = GRF.getTReg();
            off.setOccupied();
            Operand offAddr = MipsController.getAddr(offSym);
            if (offSym.isGlobal()) {
                container.add(new Load(InstrType.la, off, null, offAddr)); // off <= index的地址
                container.add(new Load(InstrType.lw, off, null, off)); // off <= index
                container.add(new Alu(InstrType.sll,off,off,new Immediate(2)));
                container.add(new Alu(InstrType.addu,lReg,lReg,off));
                container.add(new Store(InstrType.sw,rVal,null,lReg));
            } else {
                container.add(new Load(InstrType.lw,off,offAddr,GRF.getReg("sp")));
                container.add(new Alu(InstrType.sll,off,off,new Immediate(2)));
                container.add(new Alu(InstrType.addu,lReg,lReg,off));
                container.add(new Store(InstrType.sw,rVal,null,lReg));
            }
            off.release();
        } else if (index instanceof IntegerValue integer) {
            container.add(new Store(InstrType.sw,rVal,new Immediate(integer.getDim0Value() * 4),lReg));
        } else {
            throw new IllegalStateException("还能是啥！" + index.getClass());
        }
        rVal.release();
        lReg.release();
        return container;
    }

    public List<MipsCode> localToMips(boolean isOptimized) {
        List<MipsCode> container = isOptimized ? optimizedMips : this.mipsCodes;


        Register rVal = GRF.getTReg();
        rVal.setOccupied();
        if (RVal instanceof IntegerValue integerValue) {
            container.add(new Load(InstrType.li,rVal,null,new Immediate(integerValue.getDim0Value())));
        } else {
            getOperand(RVal, rVal, container);
        }
        /**************以上为获取了等式右边的值，接下来获取等式左边的地址***************/
        Register lReg;
        lReg = GRF.getTReg();
        lReg.setOccupied();
        Operand leftBaseAddr = MipsController.getAddr(leftSym);
        if (((VarSymbol)leftSym).isParam()) {
            container.add(new Load(InstrType.lw,lReg,leftBaseAddr,GRF.getReg("sp"))); // lreg = &a[0]
            if (index instanceof VarSymbol offSym) {
                Register offReg = GRF.getTReg();
                offReg.setOccupied();
                Operand offAddr = MipsController.getAddr(offSym);
                if (offSym.isGlobal()) {
                    container.add(new Load(InstrType.la,offReg,null,offAddr));
                    container.add(new Load(InstrType.lw, offReg, null, offAddr)); // offReg <= index
                    container.add(new Alu(InstrType.sll,offReg,offReg,new Immediate(2)));
                    container.add(new Alu(InstrType.addu, lReg,lReg,offReg)); // lreg <= &a[i]
                    container.add(new Store(InstrType.sw,rVal,null,lReg));
                } else {
                    container.add(new Load(InstrType.lw,offReg,offAddr,GRF.getReg("sp")));
                    //mipsCodes.add(new Load(InstrType.lw,offReg,null,offAddr));
                    container.add(new Alu(InstrType.sll,offReg,offReg,new Immediate(2)));
                    container.add(new Alu(InstrType.addu,lReg,lReg,offReg));
                    container.add(new Store(InstrType.sw,rVal,null,lReg));
                }
                offReg.release();
            } else if (index instanceof IntegerValue intOff) {
                int offByte = intOff.getDim0Value() * 4;
                container.add(new Alu(InstrType.addiu,lReg,lReg,new Immediate(offByte)));
                container.add(new Store(InstrType.sw,rVal,null,lReg));
            } else {
                throw new IllegalStateException("index的种类还能是"+index.getClass());
            }
            lReg.release();
            rVal.release();
            return container;
        }

        int totalOff;
        if (index instanceof VarSymbol offSym) {
            Operand offAddr = MipsController.getAddr(offSym);
            if (offSym.isGlobal()) {
                container.add(new Load(InstrType.la, lReg, null, offAddr)); // lreg <= index的地址
                container.add(new Load(InstrType.lw, lReg, null, lReg)); // lreg <= index
                container.add(new Alu(InstrType.sll, lReg, lReg, new Immediate(2))); // lreg <= offset
                container.add(new Alu(InstrType.addiu, lReg, lReg, leftBaseAddr)); // lreg <= offset + base
                container.add(new Alu(InstrType.addu,lReg,lReg,GRF.getReg("sp")));
                container.add(new Store(InstrType.sw,rVal,null,lReg));
            } else {
                container.add(new Load(InstrType.lw,lReg,offAddr,GRF.getReg("sp")));
                container.add(new Alu(InstrType.sll,lReg,lReg,new Immediate(2)));
                container.add(new Alu(InstrType.addiu,lReg,lReg,leftBaseAddr));
                container.add(new Alu(InstrType.addu,lReg,lReg,GRF.getReg("sp")));
                container.add(new Store(InstrType.sw,rVal,null,lReg));
                //totalOff = ((Immediate)offAddr).getNumber() + ((Immediate)leftBaseAddr).getNumber();
                //mipsCodes.add(new Store(InstrType.sw,rVal,new Immediate(totalOff),GRF.getReg("sp")));
            }
        } else if (index instanceof IntegerValue integer) {
            totalOff = ((Immediate) leftBaseAddr).getNumber() + 4 * integer.getDim0Value();
            container.add(new Store(InstrType.sw,rVal,new Immediate(totalOff),GRF.getReg("sp")));
        } else {
            throw new IllegalStateException("还能是啥！" + index.getClass());
        }
        rVal.release();
        lReg.release();
        return container;
    }

    public List<MipsCode> toMipsCode(boolean isOptimized) {
        List<MipsCode> container = isOptimized ? optimizedMips : this.mipsCodes;

        container.add(new Annotation(this.toString()));

        if (leftSym.isGlobal()) {
            return globalToMips(isOptimized);
        } else {
            return localToMips(isOptimized);
        }
    }



    /*******************代码优化用***********************/
    @Override
    public Instruction optimize() {
        return this;
    }

    @Override
    public void DAGoptimize(DAG dag) {
        String op = "[]=";
        dag.parse(index, getRVal(), leftSym, DAG.getOp(op));
    }

    public Symbol getLeftSym() {
        return leftSym;
    }

    public Value getIndex() {
        return index;
    }

    public Value getRVal() {
        return RVal;
    }

    @Override
    public Instruction reconstruct(Value tmp, Value newA) {
        // tmp = a; x[tmp] = tmp;
        Value newR = this.RVal;
        Value newIndex = this.index;
        boolean flag = false;
        if (RVal instanceof VarSymbol var && var.equals(tmp)) {
            newR = newA;
            flag = true;
        }
        if (index instanceof VarSymbol && index.equals(tmp)) {
            newIndex = newA;
            flag = true;
        }
        if (flag) {
            return new MemCpy(this.name,newIndex,newR,this.leftSym);
        } else {
            return this;
        }
    }

}
