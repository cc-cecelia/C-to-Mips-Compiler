package SymbleTable.Func;

import Lexer.LexType;
import Parser.GrammaticalComponent.Exp;
import Parser.GrammaticalComponent.FuncRParams;

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
