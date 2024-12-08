package Target.Instructions;

public class Annotation extends MipsCode {
    private String str;

    public Annotation(String str) {
        super(InstrType.annotation);
        this.str = str;
    }

    @Override
    public String toString() {
        return "# " + str ;
    }
}
