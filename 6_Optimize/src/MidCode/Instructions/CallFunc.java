package MidCode.Instructions;

import MidCode.Optimize.DAGOptimizer.DAG;
import MidCode.Table.FuncSymbol;
import MidCode.Table.VarSymbol;
import MidCode.Value.FuncValue.Params;
import MidCode.Value.Value;
import MidCode.Value.VoidValue;
import Target.GRF.GRF;
import Target.Immediate;
import Target.Instructions.*;
import Target.Tag;

import java.util.List;

public class CallFunc extends Instruction {
    private FuncSymbol calFunc;

    public CallFunc(FuncSymbol calFunc) {
        this.calFunc = calFunc;
    }

    @Override
    public String toString() {
        return "call " + calFunc.toString() + "\n";
    }


    public int getParamCnt() {
        return  ((Params)calFunc.getValue()).getSize();
    }

    public List<MipsCode> toMipsCode(boolean isOptimized) {
        List<MipsCode> container = isOptimized ? optimizedMips : this.mipsCodes;
        container.add(new Annotation(this.toString()));

        //jal f
        //int v0Pos = IRModule.getCurFunc().getPos(4);
        //mipsCodes.add(new Store(InstrType.sw,GRF.getReg("v0"),new Immediate(v0Pos),GRF.getReg("sp")));
        container.add(new Jump(InstrType.jal,new Tag(calFunc.getMidName())));
        //mipsCodes.add(new Alu(InstrType.addi,GRF.getReg("sp"),GRF.getReg("sp"),new Immediate(calFunc.getStackSize())));
        for (int i = 0; i < 4; i++) {
            GRF.getReg("a"+i).release();
        }
        for (int i = 0; i < 4; i++) {
            // 从栈底往外掏a0-a3 但目前掏了等于白掏，因为我已经存在栈上了
            container.add(new Load(InstrType.lw,GRF.getReg("a"+i),new Immediate(4*(i+1)),GRF.getReg("sp")));
            GRF.getReg("a"+i).release();
        }
        //mipsCodes.add(new Load(InstrType.lw,GRF.getReg("v0"),new Immediate(v0Pos),GRF.getReg("sp")));
        return container;
    }

    @Override
    public Instruction optimize() {
        return this;
    }

    @Override
    public void DAGoptimize(DAG dag) {
        String op = "call";
        Value res = new VoidValue();
        dag.parse(calFunc,null,calFunc,DAG.getOp(op));
    }

    public FuncSymbol getCalFunc() {
        return calFunc;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof CallFunc call) {
            return call.calFunc.equals(this.calFunc);
        }
        return false;
    }
}
