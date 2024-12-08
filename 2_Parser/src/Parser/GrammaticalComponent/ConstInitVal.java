package Parser.GrammaticalComponent;

import Lexer.Token;
import Parser.Output;
import Parser.ParseType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ConstInitVal {
    //ConstInitVal → ConstExp
    //| '{' [ ConstInitVal { ',' ConstInitVal } ] '}'

    private ConstExp constExp;
    private Token leftBrace;
    private Token rightBrace;
    private List<Token> commas = new ArrayList<>();
    private List<ConstInitVal> constInitVals = new ArrayList<>();
    private int rule;

    public ConstInitVal(ConstExp constExp) {
        this.constExp = constExp;
        rule = 1;
    }

    public ConstInitVal(Token leftBrace, Token rightBrace, List<Token> commas, List<ConstInitVal> constInitVals) {
        this.leftBrace = leftBrace;
        this.rightBrace = rightBrace;
        this.commas = commas;
        this.constInitVals = constInitVals;
        rule = 2;
    }

    public void print() throws IOException {
        switch (rule) {
            case 1: {
                constExp.print();
                break;
            }
            case 2: {
                int i = 0;
                leftBrace.print();
                if (!constInitVals.isEmpty()) {
                    constInitVals.get(i++).print();
                }
                if (i != constInitVals.size()) {
                    for (; i < constInitVals.size(); i++) {
                        commas.get(i - 1).print();
                        constInitVals.get(i).print();
                    }
                }
                rightBrace.print();
                break;
            }
            default:
                System.out.println("wrong!");
        }
        Output.component(ParseType.ConstInitVal);
    }
}
