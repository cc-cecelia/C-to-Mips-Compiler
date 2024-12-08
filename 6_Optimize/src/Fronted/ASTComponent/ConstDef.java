package Fronted.ASTComponent;

import Fronted.Lexer.Token;
import Fronted.Parser.ParseType;
import IO.Output;
import MidCode.IRModule;
import MidCode.Instructions.*;
import MidCode.MidGenerator;
import MidCode.Table.MidController;
import MidCode.Table.MidType;
import MidCode.Table.SymbolType;
import MidCode.Table.VarSymbol;
import MidCode.Value.IntegerValue.IntegerValue;
import MidCode.Value.Value;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static MidCode.Table.SymbolType.VAR_0;

public class ConstDef implements AST {
    //ConstDef → Ident { '[' ConstExp ']' } '=' ConstInitVal
    private Token ident;
    private List<ConstExp> constExps;
    private List<Token> leftBracks;
    private List<Token> rightBracks;
    private Token assign;
    private ConstInitVal constInitVal;

    public ConstDef(Token ident, List<ConstExp> constExps, List<Token> leftBracks, List<Token> rightBracks, Token assign, ConstInitVal constInitVal) {
        this.ident = ident;
        this.constExps = constExps;
        this.leftBracks = leftBracks;
        this.rightBracks = rightBracks;
        this.assign = assign;
        this.constInitVal = constInitVal;
    }

    public void print() throws IOException {
        ident.print();
        for (int i = 0; i < constExps.size(); i++) {
            leftBracks.get(i).print();
            constExps.get(i).print();
            rightBracks.get(i).print();
        }
        if (assign != null) {
            assign.print();
        }
        constInitVal.print();
        Output.component(ParseType.ConstDef);
    }

    // MID CODE
    protected BType bType; // ↓继承 可能以后如果有double 等新的 BTYPE 会用到， 但目前认为只有一种变量类型就是INT
    protected IntegerValue initVal; // ↑综合 来自ConstInitVal
    @Override
    public void generateMidCode() {
        MidType type = switch (leftBracks.size()) {
            case 0 -> new MidType(VAR_0);
            case 1 -> new MidType(SymbolType.VAR_1);
            case 2 -> new MidType(SymbolType.VAR_2);
            default -> throw new IllegalStateException("Unexpected value: " + leftBracks.size());
        };

        // 加入符号表
        String midName = MidGenerator.generateTmpName(ident.context);
        VarSymbol symbol = new VarSymbol(this, midName,type,true);
        MidController.addNewSymbol(ident.context,symbol);

        // 定义指令
        Instruction defineInstr;
        int size1 = 0;
        int size2 = 0;
        switch (type.type()) {
            case VAR_0 -> {
                // 普通变量
                defineInstr = new Var0Def(true,midName,symbol);
            }
            case VAR_1 -> {
                // 数组SIZE
                size1 = constExps.get(0).getCalculable();
                defineInstr = new Var1Def(true,midName,symbol,size1);
                symbol.setSize(size1);
            }
            case VAR_2 ->  {
                // 数组SIZE
                size1 = constExps.get(0).getCalculable();
                size2 = constExps.get(1).getCalculable();
                defineInstr = new Var2Def(true,midName,symbol,size1,size2);
                symbol.setSize(size1,size2);
            }
            default -> throw new IllegalStateException("不应该出现的类型！" + type.type());
        }
        IRModule.addInstr(defineInstr);

        // 赋值指令
        ArrayList<Instruction> assigns = new ArrayList<>();
        switch (type.type()) {
            case VAR_0 -> {
                // 普通变量
                constInitVal.symbol = symbol;
                constInitVal.generateMidCode();
                initVal = constInitVal.initVal;
                symbol.setValue(initVal);
                assigns.add(new ValAssign(midName,symbol, initVal));
            }
            case VAR_1 -> {
                // 数组初值
                constInitVal.symbol = symbol;
                constInitVal.size1 = size1;
                constInitVal.generateMidCode();
                initVal = constInitVal.initVal;
                for (int i = 0; i < initVal.getDim1Size(); i++) {
                    IntegerValue offset = new IntegerValue(i);
                    IntegerValue rVal = new IntegerValue(initVal.getDim1Value(i));
                    assigns.add(new MemCpy(midName,offset, rVal, symbol));
                }
                symbol.setValue(initVal);
            }
            case VAR_2 ->  {
                // 数组SIZE
                // 数组初值
                constInitVal.symbol = symbol;
                constInitVal.size1 = size1;
                constInitVal.size2 = size2;
                constInitVal.generateMidCode();
                initVal = constInitVal.initVal;
                for (int i = 0; i < initVal.getDim2Size1(); i++) {
                    for (int j = 0; j < initVal.getDim2Size2(i); j++) {
                        IntegerValue offset = new IntegerValue(i*size2 + j);
                        IntegerValue rVal = new IntegerValue(initVal.getDim2Value(i,j));
                        assigns.add(new MemCpy(midName,offset, rVal,symbol));
                    }
                }
                symbol.setValue(initVal);
            }
            default -> throw new IllegalStateException("不应该出现的类型！" + type.type());
        }

        IRModule.addInstrs(assigns);
    }

    @Override
    public Value getVal() {
        return null;
    }
}
