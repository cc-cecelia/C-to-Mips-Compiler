package Parser.GrammaticalComponent;

import IO.Output;
import Lexer.Token;
import Parser.ParseType;

import java.io.IOException;
import java.util.ArrayList;

public class Exp {
    private AddExp addExp;

    public Exp(AddExp addExp) {
        this.addExp = addExp;
    }

    public void print() throws IOException {
        addExp.print();
        Output.component(ParseType.Exp);
    }

    public ArrayList<Token> toTokens() {
        return addExp.toTokens();
    }
}
