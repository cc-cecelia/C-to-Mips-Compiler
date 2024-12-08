package Fronted.ASTComponent;

import Fronted.Lexer.Token;
import IO.Output;
import Fronted.Parser.ParseType;

import java.io.IOException;

public class LOrExp implements AST {
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

    @Override
    public void generateMidCode() {

    }
}
