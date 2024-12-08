package MidCode.Instructions;

import MidCode.Value.Value;

public class CallFunc extends Instruction {
    private Value calFunc;

    public CallFunc(Value calFunc) {
        this.calFunc = calFunc;
    }

    @Override
    public String toString() {
        return "call " + calFunc.toString() + "\n";
    }
}
