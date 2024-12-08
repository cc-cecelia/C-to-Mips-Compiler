package MidCode.Instructions;

public class Var2Def extends VarDef {
    private int size1;
    private int size2;
    private int size;

    private int [][] initVal;

    public Var2Def(boolean isConst, String varName,int size1,int size2) {
        super(isConst, varName);
        this.size1 = size1;
        this.size2 = size2;
        this.initVal = new int[size1][size2];
        this.size = size1 * size2;
    }


    @Override
    public String toString() {
        return super.toString() + "[" + size + "]\n";
    }
}
