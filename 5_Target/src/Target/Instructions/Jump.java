package Target.Instructions;

import Target.Operand;

public class Jump extends MipsCode{
    private Operand targetPos;

    public Jump(InstrType type, Operand targetPos) {
        super(type);
        this.targetPos = targetPos;
    }

    @Override
    public String toString() {
        return type.toString() + " " + targetPos.toString()+ "\n";
    }
}
