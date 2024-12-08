package MidCode.Instructions;

import MidCode.Optimize.DAGOptimizer.DAG;
import MidCode.Optimize.DAGOptimizer.DAGOp;
import MidCode.Value.Value;
import MidCode.Value.VoidValue;

public abstract class Print extends Instruction{
    @Override
    public Instruction optimize() {
        return this;
    }



    public abstract Value getValue();
}
