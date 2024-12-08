package Target.Instructions;

import Target.Operand;

public class Move extends MipsCode{

    private Operand rd;
    private Operand rs;

    public Move(InstrType type, Operand rd, Operand rs) {
        super(type);
        this.rd = rd;
        this.rs = rs;
    }

    @Override
    public String toString() {
        return type.toString() + " " + rd.toString() + ", " + rs.toString() + "\n";
    }
}
