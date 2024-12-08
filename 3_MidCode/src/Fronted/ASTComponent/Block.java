package Fronted.ASTComponent;

import Fronted.Lexer.Token;
import IO.Output;
import Fronted.Parser.ParseType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Block implements AST {
    private Token lBrace;
    private Token rBrace;
    private List<BlockItem> blockItems = new ArrayList<>();

    public Block(Token lBrace, Token rBrace, List<BlockItem> blockItems) {
        this.lBrace = lBrace;
        this.rBrace = rBrace;
        this.blockItems = blockItems;
    }

    public boolean isLastRet() {
        if (this.blockItems.isEmpty()) {
            return false;
        }
        return this.blockItems.get(blockItems.size()-1).isRet();
    }

    public Token getRBrace() {
        return rBrace;
    }

    public void print() throws IOException {
        lBrace.print();
        for (BlockItem blockItem : this.blockItems) {
            blockItem.print();
        }
        rBrace.print();
        Output.component(ParseType.Block);
    }

    @Override
    public void generateMidCode() {
        for(BlockItem blockItem : blockItems) {
            blockItem.generateMidCode();
        }
    }
}
