package Target.Instructions;

import Target.Operand;

public class Branch extends MipsCode {
    private Operand rs;
    private Operand rt;
    private Operand label;

    public Branch(InstrType type, Operand rs, Operand rt, Operand label) {
        super(type);
        this.rs = rs;
        this.rt = rt;
        this.label = label;
    }

    public Branch(InstrType type, Operand rs, Operand label) {
        super(type);
        this.rs = rs;
        this.label = label;
    }

    @Override
    public String toString() {
        if (rt == null) {
            return type + " " + rs + " " + label + "\n";
        } else {
            return type + " " + rs + " " + rt + " " + label + "\n";
        }
    }
}
