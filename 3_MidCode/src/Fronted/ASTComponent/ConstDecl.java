package Fronted.ASTComponent;

import Fronted.Lexer.Token;
import Fronted.Parser.ParseType;
import IO.Output;
import MidCode.Generator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ConstDecl implements AST {
    //  ConstDecl → 'const' BType ConstDef { ',' ConstDef } ';'
    private Token constTK;
    private BType type;
    private ConstDef constDef;
    private List<Token> commas;
    private List<ConstDef> constDefs = new ArrayList<>();

    private Token semiCn;

    public ConstDecl(Token constTK, BType type, ConstDef constDef, List<Token> commas, List<ConstDef> constDefs, Token semiCn) {
        this.constTK = constTK;
        this.type = type;
        this.commas = commas;
        this.constDef = constDef;
        this.constDefs = constDefs;
        this.semiCn = semiCn;
    }

    //  ConstDecl → 'const' BType ConstDef { ',' ConstDef } ';'
    public void print() throws IOException {
        constTK.print();
        type.print();
        constDef.print();
        for (int i = 0; i < constDefs.size(); i++) {
            commas.get(i).print();
            constDefs.get(i).print();
        }
        semiCn.print();
        Output.component(ParseType.ConstDecl);
    }

    @Override
    public void generateMidCode() {
        constDef.bType = this.type;
        constDef.generateMidCode();
        for (ConstDef constDef1 : constDefs) {
            constDef1.bType = this.type;
            constDef1.generateMidCode();
        }
    }
}
