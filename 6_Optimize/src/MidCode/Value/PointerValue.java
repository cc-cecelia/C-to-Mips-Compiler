package MidCode.Value;

import MidCode.Table.VarSymbol;

public class PointerValue extends Value{
    private VarSymbol symbol;

    public PointerValue(VarSymbol symbol) {
        this.symbol = symbol;
    }

    public VarSymbol getSymbol() {
        return symbol;
    }

    public boolean isGlobal() {
        return symbol.isGlobal();
    }
    @Override
    public String toString() {
        return "&" + symbol;
    }


}
