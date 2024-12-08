package Parser.GrammaticalComponent;

import Lexer.Token;
import Parser.Output;
import Parser.ParseType;

import java.io.IOException;

public class AddExp {
    private MulExp mulExp;
    private Token op;
    private AddExp addExp;

    public AddExp(MulExp mulExp, Token op, AddExp addExp) {
        this.mulExp = mulExp;
        this.op = op;
        this.addExp = addExp;
    }

    // AddExp -> MulExp [('+' | '-') AddExp]
    public void print() throws IOException {
        mulExp.print();
        // <AddExp>
        Output.component(ParseType.AddExp);
        if (op != null) {
            op.print();
            addExp.print();
        }
    }
}
