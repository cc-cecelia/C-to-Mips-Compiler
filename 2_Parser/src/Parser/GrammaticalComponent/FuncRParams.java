package Parser.GrammaticalComponent;

import Lexer.Token;
import Parser.Output;
import Parser.ParseType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FuncRParams {
    private Exp exp;
    private List<Exp> exps = new ArrayList<>();
    private List<Token> commas = new ArrayList<>();

    public FuncRParams(Exp exp, List<Exp> exps, List<Token> commas) {
        this.exp = exp;
        this.exps = exps;
        this.commas = commas;
    }

    public void print() throws IOException {
        exp.print();
        for (int i = 0; i < exps.size(); i++) {
            commas.get(i).print();
            exps.get(i).print();
        }
        Output.component(ParseType.FuncRParams);
    }
}
