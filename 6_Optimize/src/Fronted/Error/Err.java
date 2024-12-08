package Fronted.Error;

public class Err {
    protected ErrType type;
    protected int errLine;


    public Err(ErrType type, int errLine) {
        this.type = type;
        this.errLine = errLine;
    }

    @Override
    public boolean equals(Object o) {
        return ((Err) o).errLine == this.errLine;
    }

    @Override
    public String toString() {
        return errLine + " " + type.toString();
    }
}
