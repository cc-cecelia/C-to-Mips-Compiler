package MidCode.Instructions;

public class FuncHead extends Instruction{
    private String name;

    public FuncHead(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "func_begin, " + name + "\n";
    }
}
