package Target.Instructions;

public class Label extends MipsCode{
    private String labelName;

    public Label(InstrType type, String labelName) {
        super(type);
        this.labelName = labelName;
    }

    @Override
    public String toString() {
        return labelName + ": \n";
    }
}
