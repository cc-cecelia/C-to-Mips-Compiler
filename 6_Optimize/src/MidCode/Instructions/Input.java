package MidCode.Instructions;


import MidCode.Optimize.DAGOptimizer.DAG;
import MidCode.Optimize.DAGOptimizer.MidNode;
import MidCode.Optimize.DAGOptimizer.Node;
import MidCode.Table.FuncSymbol;
import MidCode.Table.MidType;
import MidCode.Table.SymbolType;
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

import java.util.LinkedList;
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

    public List<MipsCode> toMipsCode(boolean isOptimized) {
        List<MipsCode> container = isOptimized ? optimizedMips : this.mipsCodes;

        container.add(new Annotation(this.toString()));

        container.add(new Load(InstrType.li, GRF.getReg("v0"), null ,new Immediate(5)));
        container.add(new Syscall(InstrType.syscall));
        if (value instanceof VarSymbol var){
            Operand addr = MipsController.getAddr(var);
            if (var.isGlobal()) {
                Register reg = GRF.getTReg();
                container.add(new Load(InstrType.la,reg,null,addr));
                container.add(new Store(InstrType.sw,GRF.getReg("v0"),null,reg));
            } else {
                container.add(new Store(InstrType.sw,GRF.getReg("v0"),addr,GRF.getReg("sp")));
            }
        } else if (value instanceof SymbolValue var) {
            Register lReg = GRF.getSReg();
            lReg.setOccupied();
            Operand addr = MipsController.getAddr(var.getSymbol());
            if (var.isGlobal()) {
                container.add(new Load(InstrType.la,lReg,null,addr));
                if (var.getOffset() instanceof IntegerValue intOff) {
                    container.add(new Store(InstrType.sw,GRF.getReg("v0"),new Immediate(4* intOff.getDim0Value()),lReg));
                } else if (var.getOffset() instanceof VarSymbol varOff) {
                    Operand offAddr = MipsController.getAddr(varOff);
                    Register offReg = GRF.getTReg();
                    offReg.setOccupied();
                    if (varOff.isGlobal()) {
                        container.add(new Load(InstrType.la,offReg,null,offAddr));
                        container.add(new Load(InstrType.lw,offReg,null,offReg));
                        container.add(new Alu(InstrType.sll,offReg,offReg,new Immediate(2)));
                        container.add(new Alu(InstrType.addu,lReg,lReg,offReg));
                        container.add(new Store(InstrType.sw,GRF.getReg("v0"),null,lReg));
                    } else {
                        container.add(new Load(InstrType.lw,offReg,offAddr,GRF.getReg("sp")));
                        container.add(new Alu(InstrType.sll,offReg,offReg,new Immediate(2)));
                        container.add(new Alu(InstrType.addu,lReg,lReg,offReg));
                        container.add(new Store(InstrType.sw,GRF.getReg("v0"),null,lReg));
                    }
                    offReg.release();
                } else {
                    throw new RuntimeException(String.valueOf(var.getOffset().getClass()));
                }
            } else {
                if (var.isParam()) {
                    //TODO:如果是参数数组的赋值！
                    container.add(new Load(InstrType.lw,lReg,addr,GRF.getReg("sp"))); // lreg = &a[0]
                    if (var.getOffset() instanceof VarSymbol offSym) {
                        Register offReg = GRF.getTReg();
                        offReg.setOccupied();
                        Operand offAddr = MipsController.getAddr(offSym);
                        if (offSym.isGlobal()) {
                            container.add(new Load(InstrType.la,offReg,null,offAddr));
                            container.add(new Load(InstrType.lw, offReg, null, offAddr)); // offReg <= index
                            container.add(new Alu(InstrType.sll,offReg,offReg,new Immediate(2)));
                            container.add(new Alu(InstrType.addu, lReg,lReg,offReg)); // lreg <= &a[i]
                            container.add(new Store(InstrType.sw, GRF.getReg("v0"), null, lReg));
                        } else {
                            container.add(new Load(InstrType.lw,offReg,offAddr,GRF.getReg("sp")));
                            //mipsCodes.add(new Load(InstrType.lw,offReg,null,offAddr));
                            container.add(new Alu(InstrType.sll,offReg,offReg,new Immediate(2)));
                            container.add(new Alu(InstrType.addu,lReg,lReg,offReg));
                            container.add(new Store(InstrType.sw, GRF.getReg("v0"), null, lReg));
                        }
                        offReg.release();
                    } else if (var.getOffset() instanceof IntegerValue intOff) {
                        int offByte = intOff.getDim0Value() * 4;
                        container.add(new Alu(InstrType.addiu,lReg,lReg,new Immediate(offByte)));
                        container.add(new Store(InstrType.sw, GRF.getReg("v0"), null, lReg));                    } else {
                        throw new IllegalStateException("index的种类还能是"+var.getOffset().getClass());
                    }
                } else {
                    if (var.getOffset() instanceof IntegerValue intOff) {
                        int off = ((Immediate) addr).getNumber() + 4 * intOff.getDim0Value();
                        //mipsCodes.add(new Alu(InstrType.addi,lReg,lReg,new Immediate(4*intOff.getDim0Value())));
                        container.add(new Store(InstrType.sw, GRF.getReg("v0"), new Immediate(off), GRF.getReg("sp")));
                    } else if (var.getOffset() instanceof VarSymbol varOff) {
                        Operand offAddr = MipsController.getAddr(varOff);
                        Register offReg = GRF.getTReg();
                        offReg.setOccupied();
                        if (varOff.isGlobal()) {
                            container.add(new Load(InstrType.la, offReg, null, offAddr));
                            container.add(new Load(InstrType.lw, offReg, null, offReg));
                            container.add(new Alu(InstrType.sll, offReg, offReg, new Immediate(2)));
                            container.add(new Alu(InstrType.addiu, lReg, offReg, addr)); // 在栈上的偏移
                            container.add(new Alu(InstrType.addu, lReg, lReg, GRF.getReg("sp")));
                            container.add(new Store(InstrType.sw, GRF.getReg("v0"), null, lReg));
                        } else {
                            int totalOff = ((Immediate) addr).getNumber() + ((Immediate) offAddr).getNumber();
                            container.add(new Store(InstrType.sw, GRF.getReg("v0"), new Immediate(totalOff), GRF.getReg("sp")));
                        }
                        offReg.release();
                    } else {
                        throw new RuntimeException(String.valueOf(var.getOffset().getClass()));
                    }
                }
            }
            lReg.release();
        }
        return container;
    }

    /***********************代码优化用**********************/
    @Override
    public Instruction optimize() {
        return this;
    }

    @Override
    public void DAGoptimize(DAG dag) {
        Value left = new FuncSymbol(null,"getint",new MidType(SymbolType.Func));
        String op = "=";

        Node i = dag.forcedCreateNewNode(left);
        LinkedList<Node> leaves = new LinkedList<>();
        leaves.add(i);
        MidNode oper = dag.searchMidNode(new MidNode(-1,leaves,DAG.getOp(op)));
        oper.announceMother();
        oper.addUser(value);
        dag.update(value,oper);
    }

    public Value getValue() {
        return value;
    }

    @Override
    public Instruction reassign(VarSymbol varSymbol) {
        // tmp = getInt() ; c = tmp
        if (this.value.equals(varSymbol)) {
            return new Input(varSymbol);
        } else {
            return this;
        }
    }
}
