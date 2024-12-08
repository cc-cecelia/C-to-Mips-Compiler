package Fronted.ASTComponent;

import Fronted.Lexer.Token;
import Fronted.Parser.ParseType;
import IO.Output;
import MidCode.Value.Value;

import java.io.IOException;
import java.util.List;

public class VarDecl implements AST{
    //  VarDecl → BType VarDef { ',' VarDef } ';'
    private BType type;
    private List<VarDef> varDefs;
    private List<Token> commas;
    private Token semiCon;

    public VarDecl(BType type, List<VarDef> varDefs, List<Token> commas, Token semiCon) {
        this.type = type;
        this.varDefs = varDefs;
        this.commas = commas;
        this.semiCon = semiCon;
    }

    public void print() throws IOException {
        type.print();
        int i = 0;
        varDefs.get(i++).print();
        for (; i < varDefs.size(); i++) {
            commas.get(i-1).print();
            varDefs.get(i).print();
        }
        semiCon.print();
        Output.component(ParseType.VarDecl);
    }

    @Override
    public void generateMidCode() {
        for (VarDef varDef : varDefs) {
            varDef.btype = this.type;
            varDef.generateMidCode();
        }
    }

    @Override
    public Value getVal() {
        return null;
    }

}
