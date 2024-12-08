package Parser.GrammaticalComponent;

import Lexer.Token;
import Parser.Output;
import Parser.ParseType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ConstDecl {
    //  ConstDecl → 'const' BType ConstDef { ',' ConstDef } ';'
    private Token constTK;
    private Token intTK;
    private ConstDef constDef;
    private List<Token> commas;
    private List<ConstDef> constDefs = new ArrayList<>();

    private Token semiCn;

    public ConstDecl(Token constTK, Token intTK,ConstDef constDef, List<Token> commas,List<ConstDef> constDefs,Token semiCn) {
        this.constTK = constTK;
        this.intTK = intTK;
        this.commas = commas;
        this.constDef = constDef;
        this.constDefs = constDefs;
        this.semiCn = semiCn;
    }

    //  ConstDecl → 'const' BType ConstDef { ',' ConstDef } ';'
    public void print() throws IOException {
        constTK.print();
        intTK.print();
        constDef.print();
        for (int i = 0; i < constDefs.size(); i++) {
            commas.get(i).print();
            constDefs.get(i).print();
        }
        semiCn.print();
        Output.component(ParseType.ConstDecl);
    }
}
