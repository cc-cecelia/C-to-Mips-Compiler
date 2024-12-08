package Parser.GrammaticalComponent;

import Lexer.Token;
import IO.Output;
import Parser.ParseType;

import java.io.IOException;
import java.util.ArrayList;

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

    public ArrayList<Token> toTokens() {
        ArrayList<Token> tokens = new ArrayList<>();
        tokens.addAll(unaryExp.toTokens());
        if (op != null) {
            tokens.add(op);
            tokens.addAll(mulExp.toTokens());
        }
        return tokens;
    }
}
