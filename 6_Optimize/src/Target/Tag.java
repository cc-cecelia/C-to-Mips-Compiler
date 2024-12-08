package Target;

public class Tag extends Operand{
    private String label;

    public Tag(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }
}
