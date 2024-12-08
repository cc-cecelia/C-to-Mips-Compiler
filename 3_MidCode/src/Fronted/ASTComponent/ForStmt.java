package Fronted.ASTComponent;

import Fronted.Lexer.Token;
import IO.Output;
import Fronted.Parser.ParseType;

import java.io.IOException;

public class ForStmt implements AST {
    private LVal lVal;
    private Token assign;
    private Exp exp;

    public ForStmt(LVal lVal, Token assign, Exp exp) {
        this.lVal = lVal;
        this.assign = assign;
        this.exp = exp;
    }

    public void print() throws IOException {
        lVal.print();
        assign.print();
        exp.print();
        Output.component(ParseType.ForStmt);
    }

    @Override
    public void generateMidCode() {

    }
}
