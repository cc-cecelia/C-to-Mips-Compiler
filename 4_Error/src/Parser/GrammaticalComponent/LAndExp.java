package Parser.GrammaticalComponent;

import Lexer.Token;
import IO.Output;
import Parser.ParseType;

import java.io.IOException;

public class LAndExp {
    private EqExp eqExp;
    private Token op;
    private LAndExp lAndExp;

    public LAndExp(EqExp eqExp, Token op, LAndExp lAndExp) {
        this.eqExp = eqExp;
        this.op = op;
        this.lAndExp = lAndExp;
    }

    public void print() throws IOException {
        eqExp.print();
        // <LAnd>
        Output.component(ParseType.LAndExp);
        if (op != null) {
            op.print();
            lAndExp.print();
        }
    }
}
