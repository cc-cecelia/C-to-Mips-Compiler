package Parser.GrammaticalComponent;

import Lexer.Token;
import Parser.Output;
import Parser.ParseType;

import java.io.IOException;

public class UnaryOp {
    private Token token;

    public UnaryOp(Token token) {
        this.token = token;
    }

    public void print() throws IOException {
        token.print();
        Output.component(ParseType.UnaryOp);
    }
}
