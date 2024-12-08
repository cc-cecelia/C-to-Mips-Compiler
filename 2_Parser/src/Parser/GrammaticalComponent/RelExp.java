package Parser.GrammaticalComponent;

import Lexer.Token;
import Parser.Output;
import Parser.ParseType;

import java.io.IOException;

public class RelExp {
    private RelExp relExp;
    private Token op;
    private AddExp addExp;

    public RelExp(RelExp relExp, Token op, AddExp addExp) {
        this.relExp = relExp;
        this.op = op;
        this.addExp = addExp;
    }

    public void print() throws IOException {
        addExp.print();
        // <Rel>
        Output.component(ParseType.RelExp);
        if (op != null) {
            op.print();
            relExp.print();
        }
    }
}
