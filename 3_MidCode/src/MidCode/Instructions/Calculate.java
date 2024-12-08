package MidCode.Instructions;

import MidCode.Value.Value;

public class Calculate extends Instruction{
    public Value lVal;
    public char op;
    public Value operand1;
    public Value operand2;

    public Calculate(Value lVal, char op, Value operand1, Value operand2) {
        this.lVal = lVal;
        this.op = op;
        this.operand1 = operand1;
        this.operand2 = operand2;
    }

    @Override
    public String toString() {
        // return op + ", " + operand1.toString() + ", " + operand2.toString() + ", " + lVal.toString() + "\n";
        if (operand1 == null) {
            return lVal.toString() + " = " + op + operand2.toString() + "\n";
        } else {
            return lVal.toString() + " = " + operand1.toString() + op + operand2.toString() + "\n";
        }
    }
}
