package Parser.GrammaticalComponent;

import Lexer.Token;
import Parser.Output;
import Parser.ParseType;

import java.io.IOException;

public class MulExp {
    private UnaryExp unaryExp;
    private Token op;
    private MulExp mulExp;

    public MulExp(UnaryExp unaryExp, Token op, MulExp mulExp) {
        this.unaryExp = unaryExp;
        this.op = op;
        this.mulExp = mulExp;
    }

    public void print() throws IOException {
        unaryExp.print();
        // Mul
        Output.component(ParseType.MulExp);
        if (op != null) {
            op.print();
            mulExp.print();
        }
    }
}
