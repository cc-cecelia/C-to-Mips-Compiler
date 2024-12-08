package Parser.GrammaticalComponent;

import Lexer.Token;
import IO.Output;
import Parser.ParseType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LVal {
    private Token ident;
    private List<Token> lBracks = new ArrayList<>();
    private List<Token> rBracks = new ArrayList<>();
    private List<Exp> exps = new ArrayList<>();

    public LVal(Token ident, List<Token> lBracks, List<Token> rBracks, List<Exp> exps) {
        this.ident = ident;
        this.lBracks = lBracks;
        this.rBracks = rBracks;
        this.exps = exps;
    }

    public Token getIdent() {
        return ident;
    }
    public void print() throws IOException {
        ident.print();
        for (int i = 0; i < exps.size(); i++) {
            lBracks.get(i).print();
            exps.get(i).print();
            rBracks.get(i).print();
        }
        Output.component(ParseType.LVal);
    }

    public ArrayList<Token> toTokens() {
        ArrayList<Token> tokens = new ArrayList<>();
        tokens.add(ident);
        for (int i = 0; i < exps.size(); i++) {
            tokens.add(lBracks.get(i));
            tokens.addAll(exps.get(i).toTokens());
            tokens.add(rBracks.get(i));
        }
        return tokens;
    }
}
