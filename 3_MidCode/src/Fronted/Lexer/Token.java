package Fronted.Lexer;

import IO.Output;

import java.io.IOException;

public class Token {
    public final String context;
    public final LexType type;
    public final int lineNum;
    public final Integer number;

    public Token(String context, LexType type, int lineNum, Integer number) {
        this.context = context;
        this.type = type;
        this.lineNum = lineNum;
        this.number = number;
    }

    public void print() throws IOException {
        Output.token(this);
    }

    @Override
    public String toString() {
        return context;
    }
}
