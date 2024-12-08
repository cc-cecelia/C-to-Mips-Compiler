package MidCode.Instructions;

public abstract class VarDef extends Instruction{
    private boolean isConst;
    private String varName;

    public VarDef(boolean isConst, String varName) {
        this.isConst = isConst;
        this.varName = varName;
    }

    public String toString() {
        if (isConst) {
            return "const int " + varName;
        } else {
            return "int " + varName;
        }
    }
}
