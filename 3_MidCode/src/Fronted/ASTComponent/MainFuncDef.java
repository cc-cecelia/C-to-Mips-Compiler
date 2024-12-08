package Fronted.ASTComponent;

import Fronted.Lexer.Token;
import IO.Output;
import Fronted.Parser.ParseType;
import MidCode.IRModule;
import MidCode.Instructions.FuncHead;
import MidCode.Instructions.Function;
import MidCode.Table.*;

import java.io.IOException;

public class MainFuncDef implements AST {
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

    @Override
    public void generateMidCode() {
        Function mainFunc = new Function();
        // 函数名加入根符号表
        MidType type = new MidType(SymbolType.Func);
        FuncSymbol funcSymbol = new FuncSymbol(this,"main",type);
        MidController.addNewSymbol("main",funcSymbol);
        // 生成FuncDef定义
        mainFunc.setFuncDef(new FuncHead("main"));
        // 进入函数的内层符号表
        MidController.enterNewScope();
        funcSymbol.setTableNumber(MidController.curLevel);
        // 进入函数内部模块
        IRModule.enterFunc("main",mainFunc);
        // 分析block
        block.generateMidCode();
        // 退出函数
        IRModule.exitFunc();
        MidController.exitScope();
    }
}
