package Fronted.ASTComponent;

import IO.Output;
import Fronted.Parser.ParseType;

import java.io.IOException;

public class Cond implements AST {
    private LOrExp lOrExp;

    public Cond(LOrExp lOrExp) {
        this.lOrExp = lOrExp;
    }

    public void print() throws IOException {
        lOrExp.print();
        Output.component(ParseType.Cond);
    }

    @Override
    public void generateMidCode() {

    }
}
