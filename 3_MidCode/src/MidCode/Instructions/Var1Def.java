package MidCode.Instructions;

public class Var1Def extends VarDef{
    private int size;
    private int[] initVal;
    private int buffer;

    public Var1Def(boolean isConst, String varName,int size) {
        super(isConst, varName);
        this.size = size;
        this.initVal = new int[size];
        this.buffer = size;
    }

    @Override
    public String toString() {
        return super.toString() +  "[" + size + "]\n";
    }
}
