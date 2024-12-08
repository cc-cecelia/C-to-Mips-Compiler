package Parser.GrammaticalComponent;

import Lexer.Token;
import Parser.Output;
import Parser.ParseType;

import java.io.IOException;

public class MainFuncDef {
    // MainFuncDef → 'int' 'main' '(' ')' Block // 存在main函数
    private Token intTK;
    private Token mainTK;
    private Token lParent;
    private Token rParent;
    private Block block;

    public MainFuncDef(Token intTK, Token mainTK, Token lParent, Token rParent, Block block) {
        this.intTK = intTK;
        this.mainTK = mainTK;
        this.lParent = lParent;
        this.rParent = rParent;
        this.block = block;
    }

    public void print() throws IOException {
        intTK.print();
        mainTK.print();
        lParent.print();
        rParent.print();
        block.print();
        Output.component(ParseType.MainFuncDef);
    }
}
