package Fronted.ASTComponent;

import Fronted.Lexer.Token;
import Fronted.Parser.ParseType;
import IO.Output;
import MidCode.Value.Value;

import java.io.IOException;
import java.util.ArrayList;

public class UnaryOp implements AST {
    private Token token;

    public UnaryOp(Token token) {
        this.token = token;
    }

    public void print() throws IOException {
        token.print();
        Output.component(ParseType.UnaryOp);
    }

    @Override
    public void generateMidCode() {

    }

    @Override
    public Value getVal() {
        return null;
    }

    public ArrayList<Token> toTokens() {
        ArrayList<Token> tokens = new ArrayList<>();
        tokens.add(token);
        return tokens;
    }

    public String getOp() {
        return token.context;
    }
}
