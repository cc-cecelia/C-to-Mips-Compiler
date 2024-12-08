package MidCode.Instructions;

import MidCode.Optimize.DAGOptimizer.DAG;
import MidCode.Table.Symbol;

public abstract class VarDef extends Instruction{
    private boolean isConst;
    protected String varName;
    protected Symbol symbol;

    public VarDef(boolean isConst, String varName,Symbol symbol) {
        this.isConst = isConst;
        this.varName = varName;
        this.symbol = symbol;
    }

    public String toString() {
        if (isConst) {
            return "const int " + varName;
        } else {
            return "int " + varName;
        }
    }

    /********************************代码优化用*******************************/
    @Override
    public Instruction optimize() {
        return this;
    }

    @Override
    public void DAGoptimize(DAG dag) {
       dag.searchNode(symbol);
    }

    public Symbol getSymbol() {
        return symbol;
    }
}
