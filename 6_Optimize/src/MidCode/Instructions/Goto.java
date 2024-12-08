package MidCode.Instructions;

import MidCode.Optimize.DAGOptimizer.DAG;
import MidCode.Value.StringValue;
import MidCode.Value.Value;
import MidCode.Value.VoidValue;
import Target.Instructions.InstrType;
import Target.Instructions.Jump;
import Target.Instructions.MipsCode;
import Target.Tag;

import java.util.List;

public class Goto extends Instruction{
    private String des;

    public Goto(String des) {
        this.des = des;
    }

    @Override
    public String toString() {
        return "j " + des + "\n";
    }

    @Override
    public List<MipsCode> toMipsCode(boolean isOptimized) {
        List<MipsCode> container = isOptimized ? optimizedMips : this.mipsCodes;

        container.add(new Jump(InstrType.j,new Tag(des)));
        return container;
    }

    public String getDes() {
        return des;
    }

    @Override
    public Instruction optimize() {
        return this;
    }

    @Override
    public void DAGoptimize(DAG dag) {
        String op = "j";
        Value left = new StringValue(des);
        Value res = new VoidValue();

        dag.parse(left,null,left,DAG.getOp(op));
    }
}
