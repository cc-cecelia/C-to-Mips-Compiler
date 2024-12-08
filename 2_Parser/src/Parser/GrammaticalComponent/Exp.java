package Parser.GrammaticalComponent;

import Parser.Output;
import Parser.ParseType;

import java.io.IOException;

public class Exp {
    private AddExp addExp;

    public Exp(AddExp addExp) {
        this.addExp = addExp;
    }

    public void print() throws IOException {
        addExp.print();
        Output.component(ParseType.Exp);
    }
}
