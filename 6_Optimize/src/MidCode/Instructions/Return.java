package MidCode.Instructions;

import MidCode.IRModule;
import MidCode.Optimize.DAGOptimizer.DAG;
import MidCode.Table.VarSymbol;
import MidCode.Value.IntegerValue.IntegerValue;
import MidCode.Value.Value;
import Target.GRF.GRF;
import Target.Immediate;
import Target.Instructions.*;
import Target.Symbol.MipsController;

import java.util.List;

public class Return extends Instruction{
    private Value retVal;

    public Return(Value retVal) {
        this.retVal = retVal;
    }

    @Override
    public String toString() {
        if (retVal == null) {
            return "ret " + "\n";
        } else {
            return "ret " + retVal + "\n";
        }
    }

    public List<MipsCode> toMipsCode(boolean isOptimized) {
        List<MipsCode> container = isOptimized ? optimizedMips : this.mipsCodes;

        container.add(new Annotation(this.toString()));
        if (retVal instanceof IntegerValue ret) {
            container.add(new Load(InstrType.li,GRF.getReg("v0"),null,new Immediate(ret.getDim0Value())));
        } else if (retVal instanceof VarSymbol ret){
            container.add(new Load(InstrType.lw,GRF.getReg("v0"),MipsController.getAddr(ret),GRF.getReg("sp")));
        }
        int stackSize =  IRModule.getCurFunc().getStackSize() ;
        if (IRModule.getCurFunc().containSubFunc) {
            container.add(new Load(InstrType.lw, GRF.getReg("ra"), new Immediate(0), GRF.getReg("sp")));
        }
        container.add(new Alu(InstrType.addiu,GRF.getReg("sp"),GRF.getReg("sp"),new Immediate(stackSize)));
        container.add(new Jump(InstrType.jr, GRF.getReg("ra")));
        return container;
    }

    @Override
    public Instruction optimize() {
        return this;
    }

    @Override
    public void DAGoptimize(DAG dag) {
        String op = "ret";
        //Value res = new VoidValue();
        dag.parse(retVal,null,retVal,DAG.getOp(op));
    }

    public Value getRetVal() {
        return retVal;
    }

    @Override
    public Instruction reconstruct(Value tmp, Value newA) {
        // tmp = a; return tmp;
        if (retVal instanceof VarSymbol var && var.equals(tmp)) {
            return new Return(newA);
        } else {
            return this;
        }
    }

}
