package MidCode.Instructions;


import MidCode.Value.Value;

public class Input extends Instruction{
    private Value value;

    public Input(Value value) {
        this.value = value;
    }

    @Override
    public String toString() {
        //return "getint,_,_," + value.toString() + "\n";
        return value.toString() + " = " + "getint()\n";
    }
}
