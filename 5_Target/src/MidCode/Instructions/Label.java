package MidCode.Instructions;

import Target.Instructions.InstrType;
import Target.Instructions.MipsCode;

import java.util.List;

public class Label extends Instruction{
    private String name;

    public Label(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name + ": \n";
    }

    @Override
    public List<MipsCode> toMipsCode() {
        mipsCodes.add(new Target.Instructions.Label(InstrType.label, name));
        return mipsCodes;
    }
}
