package Fronted.ASTComponent;

import Fronted.Lexer.Token;
import IO.Output;
import Fronted.Parser.ParseType;

import java.io.IOException;

public class RelExp implements AST {
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

    @Override
    public void generateMidCode() {

    }
}
