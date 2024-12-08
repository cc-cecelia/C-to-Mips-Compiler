package Target.Instructions;

import Target.Operand;

public class Load extends MipsCode{
    // lw $t3, 0($sp)
    // li $t2, 1
    private Operand rt;
    private Operand offset;
    private Operand base;

    public Load(InstrType type, Operand rt, Operand offset, Operand base) {
        super(type);
        this.rt = rt;
        this.offset = offset;
        this.base = base;
    }

    @Override
    public String toString() {
        if (type.equals(InstrType.li) || type.equals(InstrType.la)) {
            return type + " " + rt.toString() + ", " + base.toString() + "\n";
        }
        if (offset == null) {
            return type + " " + rt.toString() + ", " + 0 + "("+ base.toString() + ")\n";
        } else {
            return type + " " + rt.toString() + ", " + offset.toString() + "(" + base.toString() + ")\n";
        }
    }
}
