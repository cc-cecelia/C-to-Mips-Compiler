package Target.Instructions;

import Target.Operand;

public class Store extends MipsCode{
    private Operand rt;
    private Operand offset;
    private Operand base;

    public Store(InstrType type, Operand rt, Operand offset, Operand base) {
        super(type);
        this.rt = rt;
        this.offset = offset;
        this.base = base;
    }

    @Override
    public String toString() {
        if (offset == null) {
            return type.toString() + " " + rt.toString() + ", " + 0 + "("+ base.toString() + ")\n";
        } else {
            return type.toString() + " " + rt.toString() + ", " + offset.toString() + "(" + base.toString() + ")\n";
        }
    }
}
