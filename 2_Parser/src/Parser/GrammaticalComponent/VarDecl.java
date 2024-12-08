package Parser.GrammaticalComponent;

import Lexer.Token;
import Parser.Output;
import Parser.ParseType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class VarDecl {
    //  VarDecl → BType VarDef { ',' VarDef } ';'
    private Token intTK;
    private List<VarDef> varDefs = new ArrayList<>();
    private List<Token> commas = new ArrayList<>();
    private Token semiCon;

    public VarDecl(Token intTK, List<VarDef> varDefs, List<Token> commas, Token semiCon) {
        this.intTK = intTK;
        this.varDefs = varDefs;
        this.commas = commas;
        this.semiCon = semiCon;
    }

    public void print() throws IOException {
        intTK.print();
        int i = 0;
        varDefs.get(i++).print();
        for (; i < varDefs.size(); i++) {
            commas.get(i-1).print();
            varDefs.get(i).print();
        }
        semiCon.print();
        Output.component(ParseType.VarDecl);
    }
}
