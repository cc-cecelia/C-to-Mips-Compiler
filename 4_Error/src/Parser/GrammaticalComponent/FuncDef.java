package Parser.GrammaticalComponent;

import Lexer.Token;
import IO.Output;
import Parser.ParseType;

import java.io.IOException;

public class FuncDef {
    // FuncDef → FuncType Ident '(' [FuncFParams] ')' Block
    private FuncType funcType;
    private Token ident;
    private Token lParent;
    private FuncFParams funcFParams;
    private Token rParent;
    private Block block;

    public FuncDef(FuncType funcType, Token ident, Token lParent, FuncFParams funcFParams, Token rParent, Block block) {
        this.funcType = funcType;
        this.ident = ident;
        this.lParent = lParent;
        this.funcFParams = funcFParams;
        this.rParent = rParent;
        this.block = block;
    }

    public void print() throws IOException {
        funcType.print();
        ident.print();
        lParent.print();
        if (funcFParams!=null) {
            funcFParams.print();
        }
        rParent.print();
        block.print();
        Output.component(ParseType.FuncDef);
    }
}
