package Fronted.ASTComponent;

import Fronted.Lexer.Token;

import java.io.IOException;

public class BType implements AST {
    private Token key;

    public BType(Token key) {
        this.key = key;
    }

    public void print() throws IOException {
        key.print();
    }

    @Override
    public void generateMidCode() {

    }
}
