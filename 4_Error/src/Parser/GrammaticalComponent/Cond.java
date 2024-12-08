package Parser.GrammaticalComponent;

import IO.Output;
import Parser.ParseType;

import java.io.IOException;

public class Cond {
    private LOrExp lOrExp;

    public Cond(LOrExp lOrExp) {
        this.lOrExp = lOrExp;
    }

    public void print() throws IOException {
        lOrExp.print();
        Output.component(ParseType.Cond);
    }
}
