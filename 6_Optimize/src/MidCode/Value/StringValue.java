package MidCode.Value;

public class StringValue extends Value{
    private String string;

    public StringValue(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }

    @Override
    public String toString() {
        return null;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof StringValue str) {
            return str.string.equals(this.string);
        }
        return false;
    }
}
