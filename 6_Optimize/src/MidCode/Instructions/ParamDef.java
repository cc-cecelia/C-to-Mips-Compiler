package MidCode.Instructions;

import MidCode.Optimize.DAGOptimizer.DAG;
import MidCode.Table.Symbol;
import Target.Instructions.MipsCode;
import Target.Symbol.MipsController;
import Target.Symbol.MipsSymbol;

import java.util.List;

public class ParamDef extends Instruction {
    private String name;
    private Symbol symbol;

    public ParamDef(String name, Symbol symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        //return "param_int, " + symbol.getMidName() + ",_,_\n";
        return "param_int " + symbol.getMidName() + "\n";
    }

    public List<MipsCode> toMipsCode(boolean isOptimized) {
        List<MipsCode> container = isOptimized ? optimizedMips : this.mipsCodes;

        // 从 a0 a1 a2 a3上取
        MipsSymbol mipsSymbol = new MipsSymbol(symbol);
        MipsController.add(symbol.getMidName(), mipsSymbol);

        return container;
    }

    @Override
    public Instruction optimize() {
        return this;
    }

    @Override
    public void DAGoptimize(DAG dag) {
        dag.searchNode(symbol);
    }

    public String getName() {
        return symbol.getMidName();
    }

    public Symbol getSymbol() {
        return symbol;
    }
}
