package Parser.GrammaticalComponent;

import Lexer.Token;
import Parser.Output;
import Parser.ParseType;

import java.io.IOException;

public class PrimaryExp {
    private Token lParent;
    private Exp exp;
    private Token rParent;
    private LVal lVal;
    private Number number;
    private int rule;

    public PrimaryExp(Token lParent, Exp exp, Token rParent) {
        this.lParent = lParent;
        this.exp = exp;
        this.rParent = rParent;
        rule = 1;
    }

    public PrimaryExp(LVal lVal) {
        this.lVal = lVal;
        rule = 2;
    }

    public PrimaryExp(Number number) {
        this.number = number;
        rule = 3;
    }

    public void print() throws IOException {
        switch (rule) {
            case 1:
                lParent.print();
                exp.print();
                rParent.print();
                break;
            case 2:
                lVal.print();
                break;
            case 3:
                number.print();
                break;
            default:
                System.out.println("wrong!");
                break;
        }
        Output.component(ParseType.PrimaryExp);
    }
}
