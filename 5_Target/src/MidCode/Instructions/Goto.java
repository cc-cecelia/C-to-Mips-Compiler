package MidCode.Instructions;

import Target.Instructions.InstrType;
import Target.Instructions.Jump;
import Target.Instructions.MipsCode;
import Target.Tag;

import java.util.List;

public class Goto extends Instruction{
    private String des;

    public Goto(String des) {
        this.des = des;
    }

    @Override
    public String toString() {
        return "j " + des + "\n";
    }

    @Override
    public List<MipsCode> toMipsCode() {
        mipsCodes.add(new Jump(InstrType.j,new Tag(des)));
        return mipsCodes;
    }
}
