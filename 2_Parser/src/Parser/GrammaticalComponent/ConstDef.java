package Parser.GrammaticalComponent;

import Lexer.Token;
import Parser.Output;
import Parser.ParseType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ConstDef {
    //ConstDef → Ident { '[' ConstExp ']' } '=' ConstInitVal
    private Token ident;
    private List<ConstExp> constExps;
    private List<Token> leftBracks;
    private List<Token> rightBracks;
    private Token assign;
    private ConstInitVal constInitVal;

    public ConstDef(Token ident, List<ConstExp> constExps, List<Token> leftBracks, List<Token> rightBracks, Token assign, ConstInitVal constInitVal) {
        this.ident = ident;
        this.constExps = constExps;
        this.leftBracks = leftBracks;
        this.rightBracks = rightBracks;
        this.assign = assign;
        this.constInitVal = constInitVal;
    }

    public void print() throws IOException {
        ident.print();
        for (int i = 0; i < constExps.size(); i++) {
            leftBracks.get(i).print();
            constExps.get(i).print();
            rightBracks.get(i).print();
        }
        if (assign != null) {
            assign.print();
        }
        constInitVal.print();
        Output.component(ParseType.ConstDef);
    }
}
