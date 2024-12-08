package Fronted.ASTComponent;

import Fronted.Lexer.Token;
import Fronted.Parser.ParseType;
import IO.Output;
import MidCode.IRModule;
import MidCode.Instructions.Function;
import MidCode.Instructions.Label;
import MidCode.Instructions.Return;
import MidCode.Table.FuncSymbol;
import MidCode.Table.MidController;
import MidCode.Table.MidType;
import MidCode.Table.SymbolType;
import MidCode.Value.FuncValue.Params;
import MidCode.Value.Value;

import java.io.IOException;
import java.util.ArrayList;

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
        if (funcFParams != null) {
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
        FuncSymbol funcSymbol = new FuncSymbol(this, ident.context, type);
        MidController.addNewSymbol(ident.context, funcSymbol);
        // 生成FuncDef定义
        function.setFuncDef(new Label(ident.context));
        function.setFuncSymbol(funcSymbol);
        // 进入函数的内层符号表
        MidController.enterNewScope();
        funcSymbol.setTableNumber(MidController.curLevel);
        // 进入函数内部模块
        IRModule.enterFunc(ident.context, function);
        // 分析参数的数量和类型，并加入到函数符号中，同时生成参数定义中间代码
        Params params;
        if (funcFParams == null) {
            params = new Params(null, 0);
        } else {
            funcFParams.generateMidCode();
            params = new Params(funcFParams.params, funcFParams.size);
        }
        funcSymbol.setValue(params);
        if (funcFParams == null) {
            function.setParamDefs(new ArrayList<>());
        } else {
            function.setParamDefs(funcFParams.paramsDef);
        }

        // 分析block
        block.generateMidCode();
        if (!block.isLastRet()) {
            IRModule.addInstr(new Return(null));
        }
        // 退出函数
        IRModule.exitFunc();
        MidController.exitScope();
    }

    @Override
    public Value getVal() {
        return null;
    }
}
