package MidCode.Instructions;

import MidCode.Value.Value;

public class ValAssign extends Instruction{
    private String LVal;
    private Value RVal;

    public ValAssign(String LVal, Value RVal) {
        this.LVal = LVal;
        this.RVal = RVal;
    }

    @Override
    public String toString() {
        //return "=, " + RVal.toString() + ", , " + LVal + "\n";
        return LVal + " = " + RVal.toString() + "\n";
    }
}
