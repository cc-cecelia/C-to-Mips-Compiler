package Target.Instructions;

public class Asciiz extends MipsCode{
    private String strLabel;
    private String string;

    public Asciiz(String strLabel, String string) {
        super(null);
        this.strLabel = strLabel;
        this.string = string;
    }

    @Override
    public String toString() {
        return strLabel + ":" + ".asciiz \"" + string + "\"\n";
    }
}
