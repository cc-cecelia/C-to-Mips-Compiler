package Target;

public class Immediate extends Operand {
    private int number;

    public Immediate(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return String.valueOf(number);
    }
}
