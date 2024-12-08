package MidCode.Instructions;

import MidCode.Table.Symbol;

public class ParamDef extends Instruction{
    private String name;
    private Symbol symbol;

    public ParamDef(String name, Symbol symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        //return "param_int, " + symbol.getMidName() + ",_,_\n";
        return "param_int " + symbol.getMidName()  + "\n";
    }
}
