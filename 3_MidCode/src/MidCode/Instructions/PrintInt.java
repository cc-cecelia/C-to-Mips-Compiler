package MidCode.Instructions;

import MidCode.Value.Value;

public class PrintInt extends Print{
    private Value value;

    public PrintInt(Value value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "print_int, " + value.toString() + "\n";
    }
}
