package Fronted.Parser.SymbleTable;

import Fronted.Lexer.Token;

public abstract class Symbol {
    protected Token token;

    public Symbol(Token token) {
        this.token = token;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Symbol) {
            if (!this.token.context.equals(((Symbol) obj).token.context)) {
                return false;
            }
            if (obj instanceof FuncSymbol) {
                return this instanceof FuncSymbol;
            } else {
                return this instanceof VarSymbol;
            }
        } else {
            return false;
        }
    }
}
