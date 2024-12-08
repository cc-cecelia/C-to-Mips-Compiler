package Target.Instructions;

public abstract class MipsCode {
    protected InstrType type;

    public MipsCode(InstrType type) {
        this.type = type;
    }

    public abstract String toString();
}
