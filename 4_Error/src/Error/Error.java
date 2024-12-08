package Error;

public class Error {
    protected ErrorType type;
    protected int errLine;


    public Error(ErrorType type, int errLine) {
        this.type = type;
        this.errLine = errLine;
    }

    @Override
    public boolean equals(Object o) {
        return ((Error) o).errLine == this.errLine;
    }

    @Override
    public String toString() {
        return errLine + " " + type.toString();
    }
}
