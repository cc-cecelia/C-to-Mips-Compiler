package Target.Instructions;

public class Syscall extends MipsCode{
    public Syscall(InstrType type) {
        super(type);
    }

    @Override
    public String toString() {
        return "syscall " + "\n";
    }
}
