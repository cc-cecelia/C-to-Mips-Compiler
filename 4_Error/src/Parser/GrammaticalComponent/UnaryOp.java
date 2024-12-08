package Parser.GrammaticalComponent;

import Lexer.Token;
import IO.Output;
import Parser.ParseType;

import java.io.IOException;
import java.util.ArrayList;

public class UnaryOp {
    private Token token;

    public UnaryOp(Token token) {
        this.token = token;
    }

    public void print() throws IOException {
        token.print();
        Output.component(ParseType.UnaryOp);
    }

    public ArrayList<Token> toTokens() {
        ArrayList<Token> tokens = new ArrayList<>();
        tokens.add(token);
        return tokens;
    }
}
