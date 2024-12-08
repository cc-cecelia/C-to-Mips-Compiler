package Parser.GrammaticalComponent;

import Lexer.Token;
import IO.Output;
import Parser.ParseType;

import java.io.IOException;

public class LOrExp {
    private LAndExp lAndExp;
    private Token op;
    private LOrExp lOrExp;

    public LOrExp(LAndExp lAndExp, Token op, LOrExp lOrExp) {
        this.lAndExp = lAndExp;
        this.op = op;
        this.lOrExp = lOrExp;
    }

    public void print() throws IOException {
        lAndExp.print();
        Output.component(ParseType.LOrExp);
        if (op != null) {
            op.print();
            lOrExp.print();
        }
    }
}
