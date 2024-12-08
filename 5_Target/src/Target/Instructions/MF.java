package Target.Instructions;

import Target.GRF.Register;
import Target.Instructions.InstrType;
import Target.Instructions.MipsCode;

public class MF extends MipsCode {
    private Register register;

    public MF(InstrType type,Register register) {
        super(type);
        this.register = register;
    }

    @Override
    public String toString() {
        return type + " " + register.toString() + "\n";
    }
}
