package MidCode.Instructions;

import Target.Instructions.InstrType;
import Target.Instructions.Label;
import Target.Instructions.MipsCode;

import java.util.List;

public class FuncHead extends Instruction{
    private String name;

    public FuncHead(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "func_begin, " + name + "\n";
    }

    public List<MipsCode> toMipsCode() {
        // 生成标签
        mipsCodes.add(new Label(InstrType.label,name));
        return mipsCodes;
    }
}
