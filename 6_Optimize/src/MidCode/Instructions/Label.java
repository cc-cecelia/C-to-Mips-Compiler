package MidCode.Instructions;

import MidCode.Optimize.DAGOptimizer.DAG;
import Target.Instructions.InstrType;
import Target.Instructions.MipsCode;

import java.util.List;

public class Label extends Instruction{
    private String name;

    public Label(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name + ": \n";
    }

    @Override
    public List<MipsCode> toMipsCode(boolean isOptimized) {
        List<MipsCode> container = isOptimized ? optimizedMips : this.mipsCodes;

        container.add(new Target.Instructions.Label(InstrType.label, name));
        return container;
    }

    public String getName() {
        return name;
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
