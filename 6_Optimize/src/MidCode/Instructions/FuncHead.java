package MidCode.Instructions;

import MidCode.Optimize.DAGOptimizer.DAG;
import Target.Instructions.InstrType;
import Target.Instructions.Label;
import Target.Instructions.MipsCode;

import java.util.List;

public class FuncHead extends Instruction{
    private String name;

    public FuncHead(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "func_begin, " + name + "\n";
    }

    public List<MipsCode> toMipsCode(boolean isOptimized) {
        List<MipsCode> container = isOptimized ? optimizedMips : this.mipsCodes;

        // 生成标签
        container.add(new Label(InstrType.label,name));
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