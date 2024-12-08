package Target.Instructions;

public class Word extends MipsCode{
    private String label;
    private int res;

    public Word(String label, int res) {
        super(null);
        this.label = label;
        this.res = res;
    }

    @Override
    public String toString() {
        return label + ": .word " + res + "\n";
    }
}
