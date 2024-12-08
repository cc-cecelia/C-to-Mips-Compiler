package Fronted.Parser.SymbleTable.Func;

public class FuncParam {
    int dim;


    public FuncParam(int dim) {
        this.dim = dim;
    }

    @Override
    public boolean equals(Object obj) {
        return ((FuncParam)obj).dim == this.dim;
    }
}
