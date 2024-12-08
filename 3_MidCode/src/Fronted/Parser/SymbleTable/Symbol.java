package Fronted.Parser.SymbleTable;

import Fronted.Lexer.Token;

public abstract class Symbol {
    protected Token token;

    public Symbol(Token token) {
        this.token = token;
    }
}
