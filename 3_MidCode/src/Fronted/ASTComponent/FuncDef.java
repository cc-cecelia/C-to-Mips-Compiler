package Fronted.ASTComponent;

import Fronted.Lexer.Token;
import IO.Output;
import Fronted.Parser.ParseType;
import MidCode.IRModule;
import MidCode.Instructions.FuncHead;
import MidCode.Instructions.Function;
import MidCode.Table.*;
import MidCode.Value.FuncValue.Params;
import MidCode.Value.Value;

import java.io.IOException;

public class FuncDef implements AST {
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

    @Override
    public void generateMidCode() {
        Function function = new Function();
        // 函数名加入根符号表
        MidType type = new MidType(SymbolType.Func);
        FuncSymbol funcSymbol = new FuncSymbol(this,ident.context,type);
        MidController.addNewSymbol(ident.context,funcSymbol);
        // 生成FuncDef定义
        function.setFuncDef(new FuncHead(ident.context));
        // 进入函数的内层符号表
        MidController.enterNewScope();
        funcSymbol.setTableNumber(MidController.curLevel);
        // 进入函数内部模块
        IRModule.enterFunc(ident.context,function);
        // 分析参数的数量和类型，并加入到函数符号中，同时生成参数定义中间代码
        Value params;
        if (funcFParams == null) {
            params = new Params(null,0);
        } else {
            funcFParams.generateMidCode();
            params = new Params(funcFParams.params, funcFParams.size);
        }
        funcSymbol.setValue(params);

        function.setParamDefs(funcFParams.paramsDef);

        // 分析block
        block.generateMidCode();
        // 退出函数
        IRModule.exitFunc();
        MidController.exitScope();
    }
}
