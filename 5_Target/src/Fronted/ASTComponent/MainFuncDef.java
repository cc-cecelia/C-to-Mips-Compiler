package Fronted.ASTComponent;

import Fronted.Lexer.Token;
import Fronted.Parser.ParseType;
import IO.Output;
import MidCode.IRModule;
import MidCode.Instructions.Function;
import MidCode.Instructions.Label;
import MidCode.Table.FuncSymbol;
import MidCode.Table.MidController;
import MidCode.Table.MidType;
import MidCode.Table.SymbolType;
import MidCode.Value.FuncValue.Params;
import MidCode.Value.Value;

import java.io.IOException;
import java.util.ArrayList;

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
        FuncSymbol funcSymbol = new FuncSymbol(this, "main", type);
        MidController.addNewSymbol("main", funcSymbol);
        // 生成FuncDef定义
        mainFunc.setFuncSymbol(funcSymbol);
        mainFunc.setFuncDef(new Label("main"));
        // 进入函数的内层符号表
        MidController.enterNewScope();
        funcSymbol.setTableNumber(MidController.curLevel);
        // 进入函数内部模块
        IRModule.enterFunc("main", mainFunc);
        funcSymbol.setValue(new Params(null, 0));
        mainFunc.setParamDefs(new ArrayList<>());
        // 分析block
        block.generateMidCode();
        // 退出函数
        IRModule.exitFunc();
        MidController.exitScope();
    }

    @Override
    public Value getVal() {
        return null;
    }
}
