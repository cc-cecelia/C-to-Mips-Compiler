package MidCode.Instructions;

import MidCode.Value.Value;

public class Return extends Instruction{
    private Value retVal;

    public Return(Value retVal) {
        this.retVal = retVal;
    }

    @Override
    public String toString() {
        return "ret " + retVal.toString() + "\n";
    }
}
