package MidCode.Value;

import MidCode.Table.Symbol;
import MidCode.Table.SymbolType;
import MidCode.Table.VarSymbol;
import Target.Instructions.MipsCode;

import java.util.List;

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


    @Override
    public String toString() {
        if (offset == null) {
            return symbol.toString();
        } else {
            return symbol + "[" + offset.toString() + "]";
        }
    }

    @Override
    public Value clone() {
        return null;
    }

    public List<MipsCode> toMipsCode() {
        return null;
    }

    public boolean isGlobal() {
        return symbol.isGlobal();
    }

    @Override
    public SymbolType getDim() {
        return dim;
    }
}
