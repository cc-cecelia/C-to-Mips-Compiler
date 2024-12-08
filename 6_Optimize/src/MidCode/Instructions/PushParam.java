package MidCode.Instructions;

import MidCode.Optimize.DAGOptimizer.DAG;
import MidCode.Table.VarSymbol;
import MidCode.Value.Value;
import Target.GRF.GRF;
import Target.GRF.Register;
import Target.Immediate;
import Target.Instructions.*;
import Target.Symbol.MipsController;
import Target.Symbol.MipsSymbol;

import java.util.List;

public class PushParam extends Instruction{
    private Value param;
    private Function function;
    private int ordinal;

    public PushParam(Value param, int ordinal) {
        this.param = param;
        this.ordinal = ordinal;
    }

    public void setFunction(Function function) {
        this.function = function;
    }

    @Override
    public String toString() {
        return "push " + param + "\n";
    }

    public List<MipsCode> toMipsCode(boolean isOptimized) {
        List<MipsCode> container = isOptimized ? optimizedMips : this.mipsCodes;

        container.add(new Annotation(this.toString()));

        Register aReg = GRF.getAReg();
        MipsSymbol sym = MipsController.getSym((VarSymbol)param);
        if (aReg!=null) {
            aReg.setOccupied();
            container.add(new Load(InstrType.lw,aReg,MipsController.getAddr((VarSymbol)param),GRF.getReg("sp")));
        } else {
            // 直接冲到调用者栈上去存
            Register aTmp = GRF.getTReg();
            container.add(new Load(InstrType.lw,aTmp,MipsController.getAddr((VarSymbol)param),GRF.getReg("sp")));
            int pos = -ordinal*4;
            container.add(new Store(InstrType.sw,aTmp,new Immediate(pos),GRF.getReg("sp")));
        }
        return container;
    }

    @Override
    public Instruction optimize() {
        return this;
    }

    @Override
    public void DAGoptimize(DAG dag) {
//        Value left = cal.getOperand1();
//        Value right = cal.getOperand2();
//        String op = cal.getOp();
//        Value res = cal.getLVal();
        //dag.parse(operand1, operand2, lVal, op);
    }
}
