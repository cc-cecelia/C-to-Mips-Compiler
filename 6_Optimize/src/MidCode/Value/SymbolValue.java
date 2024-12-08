package MidCode.Value;

import MidCode.Table.Symbol;
import MidCode.Table.SymbolType;
import MidCode.Table.VarSymbol;

public class SymbolValue extends Value {
    private Symbol symbol;
    private Value offset; // 可能是数字、可能是一个标识符
    private SymbolType dim;

    public SymbolValue(Symbol symbol, Value offset,SymbolType dim) {
        this.symbol = symbol;
        this.offset = offset;
        this.dim = dim;
    }

    public String getName() {
        return symbol.getMidName();
    }

    public Value getOffset() {
        return offset;
    }

    public boolean isArray() {
        return offset != null;
    }

    public boolean isParam() {
        return ((VarSymbol)symbol).isParam();
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public Value getValue() {
        //TODO
        return null;
    }

    @Override
    public String toString() {
        if (offset == null) {
            return symbol.toString();
        } else {
            return symbol + "[" + offset.toString() + "]";
        }
    }

    public boolean isGlobal() {
        return symbol.isGlobal();
    }

    @Override
    public SymbolType getDim() {
        return dim;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof SymbolValue val) {
            return val.symbol.equals(this.symbol) && val.offset.equals(this.offset);
        } else {
            return false;
        }
    }
}
