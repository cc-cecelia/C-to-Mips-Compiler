package Parser.GrammaticalComponent;

import Lexer.Token;
import IO.Output;
import Parser.ParseType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FuncRParams {
    private Exp exp;
    private List<Exp> exps;
    private List<Token> commas;

    public FuncRParams(Exp exp, List<Exp> exps, List<Token> commas) {
        this.exp = exp;
        this.exps = exps;
        this.commas = commas;
    }

    public int getFuncRParamsCnt() {
        if (exp == null) {
            return 0;
        }
        return 1 + exps.size();
    }

    public void print() throws IOException {
        exp.print();
        for (int i = 0; i < exps.size(); i++) {
            commas.get(i).print();
            exps.get(i).print();
        }
        Output.component(ParseType.FuncRParams);
    }

    public ArrayList<Token> toTokens() {
        ArrayList<Token> tokens = new ArrayList<>();
        tokens.addAll(exp.toTokens());
        for (int i = 0; i < exps.size(); i++) {
            tokens.add(commas.get(i));
            tokens.addAll(exps.get(i).toTokens());
        }
        return tokens;
    }
}
