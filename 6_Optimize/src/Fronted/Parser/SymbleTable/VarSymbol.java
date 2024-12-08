package Fronted.Parser.SymbleTable;

import Fronted.Lexer.Token;

public class VarSymbol extends Symbol{
    protected boolean isConst;
    protected int dim;

    public VarSymbol(Token token, boolean isConst, int dim) {
        super(token);
        this.isConst = isConst;
        this.dim = dim;
    }

    public int getDim() {
        return dim;
    }
}
