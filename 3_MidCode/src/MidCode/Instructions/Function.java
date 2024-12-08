package MidCode.Instructions;

import java.util.ArrayList;
import java.util.List;

public class Function extends Instruction{
    private FuncHead funcDef;
    private List<ParamDef> paramDefs;
    private List<Instruction> instructions;

    public Function() {
        instructions = new ArrayList<>();
    }

    public void setFuncDef(FuncHead funcDef) {
        this.funcDef = funcDef;
    }

    public void setParamDefs(List<ParamDef> paramDefs) {
        this.paramDefs = paramDefs;
    }

    public void add(Instruction instruction) {
        this.instructions.add(instruction);
    }

    public void addAll(List<Instruction> instructions) {
        this.instructions.addAll(instructions);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(funcDef.toString());
        if (paramDefs != null) {
            for (ParamDef paramDef : paramDefs) {
                sb.append(paramDef.toString());
            }
        }
        for (Instruction instruction : instructions) {
            sb.append(instruction.toString());
        }
        sb.append("func_end\n");
        return sb.toString();
    }
}
