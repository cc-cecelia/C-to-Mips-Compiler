package Parser.GrammaticalComponent;

import Lexer.Token;
import IO.Output;
import Parser.ParseType;

import java.io.IOException;

public class EqExp {
    private RelExp relExp;
    private Token op;
    private EqExp eqExp;

    public EqExp(RelExp relExp, Token op, EqExp exp) {
        this.relExp = relExp;
        this.op = op;
        this.eqExp = exp;
    }

    public void print() throws IOException {
        relExp.print();
        //<Eq>
        Output.component(ParseType.EqExp);
        if (op != null) {
            op.print();
            eqExp.print();
        }
    }
}
