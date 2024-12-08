package Target.Instructions;

import Target.GRF.Register;

public class MD extends MipsCode{
    private Register rs;
    private Register rt;

    public MD(InstrType type,Register rs, Register rt) {
        super(type);
        this.rs = rs;
        this.rt = rt;
    }

    @Override
    public String toString() {
        return type.toString() + " " + rs.toString() + ", " + rt.toString() + "\n";

    }

}
