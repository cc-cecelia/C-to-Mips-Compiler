package MidCode.Instructions;

import MidCode.Value.Value;

public class PushParam extends Instruction{
    private Value param;
    private Function function;

    public PushParam(Value param) {
        this.param = param;
    }

    public void setFunction(Function function) {
        this.function = function;
    }

    @Override
    public String toString() {
        return "push " + param + "\n";
    }
}
