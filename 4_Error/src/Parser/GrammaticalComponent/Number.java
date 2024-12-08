package Parser.GrammaticalComponent;

import Lexer.Token;
import IO.Output;
import Parser.ParseType;

import java.io.IOException;
import java.util.ArrayList;

public class Number {
    private Token intConst;

    public Number(Token intConst) {
        this.intConst = intConst;
    }

    public void print() throws IOException {
        intConst.print();
        Output.component(ParseType.Number);
    }

    public ArrayList<Token> toTokens(){
        ArrayList<Token> tokens = new ArrayList<>();
        tokens.add(intConst);
        return tokens;
    }
}
