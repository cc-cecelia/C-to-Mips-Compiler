package Parser.GrammaticalComponent;

import Lexer.Token;
import IO.Output;
import Parser.ParseType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FuncFParam {
    private Token intTK;
    private Token ident;
    private Token lBrack;
    private Token rBrack;
    private List<Token> lBracks = new ArrayList<>();
    private List<Token> rBracks = new ArrayList<>();
    private List<ConstExp> constExps = new ArrayList<>();

    public FuncFParam(Token intTK, Token ident, Token lBrack, Token rBrack, List<Token> lBracks, List<Token> rBracks, List<ConstExp> constExps) {
        this.intTK = intTK;
        this.ident = ident;
        this.lBrack = lBrack;
        this.rBrack = rBrack;
        this.lBracks = lBracks;
        this.rBracks = rBracks;
        this.constExps = constExps;
    }

    public int getDim() {
        if (lBrack == null) {
            return 0;
        } else {
            return 1 + lBracks.size();
        }
    }

    public void print() throws IOException {
        intTK.print();
        ident.print();
        if (lBrack != null) {
            lBrack.print();
            rBrack.print();
            if (!constExps.isEmpty()) {
                for (int i = 0; i < constExps.size(); i++) {
                    lBracks.get(i).print();
                    constExps.get(i).print();
                    rBracks.get(i).print();
                }
            }
        }
        Output.component(ParseType.FuncFParam);
    }
}
