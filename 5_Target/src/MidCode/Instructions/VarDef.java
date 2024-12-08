package MidCode.Instructions;

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

}
