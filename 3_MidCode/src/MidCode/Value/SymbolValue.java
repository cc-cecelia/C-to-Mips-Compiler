package MidCode.Value;

import MidCode.Table.Symbol;

public class SymbolValue extends Value{
    private Symbol symbol;
    private Value offset; // 可能是数字、可能是一个标识符

    public SymbolValue(Symbol symbol, Value offset) {
        this.symbol = symbol;
        this.offset = offset;
    }

    public String getName() {
        return symbol.getMidName();
    }

    public Value getOffset() {
        return offset;
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
        return new SymbolValue(this.symbol,this.offset);
    }
}
