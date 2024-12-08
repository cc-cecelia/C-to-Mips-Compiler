package MidCode.Instructions;

public class PrintStr extends Print{
    private final String string;

    public PrintStr(String string) {
        this.string = string;
    }

    @Override
    public String toString() {
        return "print_str, " + string + "\n";
    }
}
