package Parser.GrammaticalComponent;

import Lexer.Token;
import IO.Output;
import Parser.ParseType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InitVal {
    private Exp exp;
    private Token lBrace;
    private InitVal initVal;
    private List<Token> commas;
    private List<InitVal> initVals = new ArrayList<>();
    private Token rBrace;
    private int rule;

    public InitVal(Exp exp) {
        this.exp = exp;
        rule = 1;
    }

    public InitVal(Token lBrace, InitVal initVal, List<Token> commas, List<InitVal> initVals, Token rBrace) {
        this.lBrace = lBrace;
        this.initVal = initVal;
        this.commas = commas;
        this.initVals = initVals;
        this.rBrace = rBrace;
        rule = 2;
    }

    public void print() throws IOException {
        switch (rule) {
            case 1 -> exp.print();
            case 2 -> {
                lBrace.print();
                if (initVal != null) {
                    initVal.print();
                    if (!initVals.isEmpty()) {
                        for (int i = 0; i < initVals.size(); i++) {
                            commas.get(i).print();
                            initVals.get(i).print();
                        }
                    }
                }
                rBrace.print();
            }
        }
        Output.component(ParseType.InitVal);
    }
}
