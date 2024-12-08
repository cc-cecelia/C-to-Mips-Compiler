package MidCode.Instructions;

import MidCode.Value.Value;

public class MemCpy extends Instruction {
    private String name;
    private Value index;
    private Value RVal;

    public MemCpy(String name, Value index, Value RVal) {
        this.name = name;
        this.index = index;
        this.RVal = RVal;
    }

    @Override
    public String toString() {
        //return "=, " + name + ", " + index.toString() + ", " + RVal.toString() + "\n";
        return name + "[" + index.toString()+ "] = " + RVal.toString() + "\n";
    }
}
