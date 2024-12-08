package Parser.GrammaticalComponent;

import IO.Output;
import Lexer.Token;
import Parser.ParseType;

import java.io.IOException;
import java.util.ArrayList;

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

    public ArrayList<Token> toTokens() {
        ArrayList<Token> tokens = new ArrayList<>();
        tokens.addAll(mulExp.toTokens());
        if (op != null) {
            tokens.add(op);
            tokens.addAll(addExp.toTokens());
        }
        return tokens;
    }
}
