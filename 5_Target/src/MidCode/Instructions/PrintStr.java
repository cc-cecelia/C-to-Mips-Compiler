package MidCode.Instructions;

import Target.GRF.GRF;
import Target.Immediate;
import Target.Instructions.*;
import Target.Tag;

import java.util.List;

public class PrintStr extends Print{
    private final String string;
    private String address;

    public PrintStr(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }

    @Override
    public String toString() {
        return "print_str, " + string + "\n";
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<MipsCode> toMipsCode() {
        mipsCodes.add(new Annotation(this.toString()));

        mipsCodes.add(new Load(InstrType.li,GRF.getReg("v0"),null,new Immediate(4)));
        mipsCodes.add(new Load(InstrType.la, GRF.getReg("a0"),null,new Tag(address)));
        mipsCodes.add(new Syscall(InstrType.syscall));
        return mipsCodes;
    }
}
