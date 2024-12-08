package Target.Instructions;

public class Space extends MipsCode {
    private String label;
    private int length;

    public Space(String label, int length) {
        super(null);
        this.label = label;
        this.length = length;
    }

    @Override
    public String toString() {
        return label + ": .space " + length + "\n";
    }
}
