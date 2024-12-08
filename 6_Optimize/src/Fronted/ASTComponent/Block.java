package Fronted.ASTComponent;

import Fronted.Lexer.Token;
import Fronted.Parser.ParseType;
import IO.Output;
import MidCode.IRModule;
import MidCode.Instructions.Label;
import MidCode.MidGenerator;
import MidCode.Value.Value;

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
        // 可以新增两个标签，用于基本块分类
        //String blockLabel = MidGenerator.generateBlockLabel();
        //IRModule.addInstr(new Label(blockLabel));
        for(BlockItem blockItem : blockItems) {

            blockItem.generateMidCode();
        }
        //IRModule.addInstr(new Label(blockLabel + "end"));
    }

    @Override
    public Value getVal() {
        return null;
    }
}
