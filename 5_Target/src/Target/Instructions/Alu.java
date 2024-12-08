package Target.Instructions;

import Target.Operand;

public class Alu extends MipsCode {
    private Operand rd;
    private Operand rs;
    private Operand rt;

    public Alu(InstrType instrType, Operand rd, Operand rs, Operand rt) {
        super(instrType);
        this.rd = rd;
        this.rs = rs;
        this.rt = rt;
    }

    @Override
    public String toString() {
        return type.toString() + " " + rd.toString() + ", " + rs.toString() + ", " + rt.toString() + "\n";
    }
}
