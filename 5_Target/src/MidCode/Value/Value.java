package MidCode.Value;

import MidCode.Table.SymbolType;
import Target.Instructions.MipsCode;
import Target.Operand;

import java.util.List;

public abstract class Value {
    public abstract String toString();
    public abstract Value clone();

    public abstract List<MipsCode> toMipsCode();
    protected Operand locate;


    public SymbolType getDim() {
        return SymbolType.VAR_0;
    }
}
