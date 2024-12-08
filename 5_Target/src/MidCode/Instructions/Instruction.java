package MidCode.Instructions;

import Target.Instructions.MipsCode;

import java.util.ArrayList;
import java.util.List;

public abstract class Instruction {

    public abstract String toString();
    protected List<MipsCode> mipsCodes = new ArrayList<>();

    public abstract List<MipsCode> toMipsCode();
}
