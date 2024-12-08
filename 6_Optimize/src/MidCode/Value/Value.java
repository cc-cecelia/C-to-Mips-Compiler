package MidCode.Value;

import MidCode.Table.SymbolType;
import Target.Instructions.MipsCode;
import Target.Operand;

import java.util.List;

public abstract class Value {
    public abstract String toString();

    public SymbolType getDim() {
        return SymbolType.VAR_0;
    }

    @Override
    public boolean equals(Object obj) {
        return false;
    }
}
