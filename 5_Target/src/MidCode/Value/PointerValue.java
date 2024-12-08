package MidCode.Value;

import MidCode.Table.SymbolType;
import MidCode.Table.VarSymbol;
import Target.Instructions.MipsCode;

import java.util.List;

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

    @Override
    public Value clone() {
        return null;
    }

    @Override
    public List<MipsCode> toMipsCode() {
        return null;
    }


}
