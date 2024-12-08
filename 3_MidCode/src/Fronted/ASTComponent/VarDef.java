package Fronted.ASTComponent;

import Fronted.Lexer.Token;
import IO.Output;
import Fronted.Parser.ParseType;
import MidCode.Generator;
import MidCode.IRModule;
import MidCode.Instructions.*;
import MidCode.Table.*;
import MidCode.Value.IntegerValue.IntegerType;
import MidCode.Value.IntegerValue.IntegerValue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class VarDef implements AST {
    // VarDef → Ident { '[' ConstExp ']' } // 包含普通变量、一维数组、二维数组定义
    //| Ident { '[' ConstExp ']' } '=' InitVal
    private Token ident;
    private List<Token> lBrack = new ArrayList<>();
    private List<Token> rBrack = new ArrayList<>();
    private Token assign;
    private List<ConstExp> constExps = new ArrayList<>();
    private InitVal initVal;
    private int rule;

    public VarDef(Token ident, List<Token> lBrack, List<Token> rBrack, List<ConstExp> constExps) {
        this.ident = ident;
        this.lBrack = lBrack;
        this.rBrack = rBrack;
        this.constExps = constExps;
        rule = 1;
    }

    public VarDef(Token ident, List<Token> lBrack, List<Token> rBrack, Token assign, List<ConstExp> constExps, InitVal initVal) {
        this.ident = ident;
        this.lBrack = lBrack;
        this.rBrack = rBrack;
        this.assign = assign;
        this.constExps = constExps;
        this.initVal = initVal;
        rule = 2;
    }

    public void print() throws IOException {
        ident.print();
        for (int i = 0; i < constExps.size(); i++) {
            lBrack.get(i).print();
            constExps.get(i).print();
            rBrack.get(i).print();
        }
        switch (rule) {
            case 1: {
                break;
            }
            case 2: {
                assign.print();
                initVal.print();
                break;
            }
            default:
                System.out.println("wrong!");
                break;
        }
        Output.component(ParseType.VarDef);
    }

    protected BType btype;
    @Override
    public void generateMidCode() {
        // 加入符号表
        String midName = Generator.generateTmpName(ident.context);
        MidType type = switch (lBrack.size()) {
            case 0 -> new MidType(SymbolType.VAR_0);
            case 1 -> new MidType(SymbolType.VAR_1);
            case 2 -> new MidType(SymbolType.VAR_2);
            default -> throw new IllegalStateException("还能几维？" + lBrack.size());
        };
        VarSymbol varSymbol = new VarSymbol(this, midName,type,false);
        MidController.addNewSymbol(ident.context,varSymbol);

        // 生成定义指令
        Instruction defInstr;
        int size1 = 0,size2 = 0;
        switch (varSymbol.getSymbolType().type()) {
            case VAR_0 -> defInstr = new Var0Def(varSymbol.isConst(), midName);
            case VAR_1 -> {
                size1 = constExps.get(0).getCalculable();
                defInstr = new Var1Def(varSymbol.isConst(), midName,size1);
            }
            case VAR_2 -> {
                size1 = constExps.get(0).getCalculable();
                size2 = constExps.get(1).getCalculable();
                defInstr = new Var2Def(varSymbol.isConst(), midName,size1,size2);
            }
            default -> throw new IllegalStateException("咋还能是函数呢？");
        }
        IRModule.addInstr(defInstr);

        // 如果有初值，则赋初值
        List<Instruction> assigns = new ArrayList<>();

        if (initVal != null) {
            switch (varSymbol.getSymbolType().type()) {
                case VAR_1 -> initVal.size2 = null;
                case VAR_2 -> initVal.size2 = size2;
            }
            initVal.generateMidCode();
            switch (varSymbol.getSymbolType().type()) {
                case VAR_0 -> assigns.add(new ValAssign(midName, initVal.val));
                case VAR_1 -> {
                    for (int i = 0; i < size1; i++) {
                        IntegerValue offset = new IntegerValue(IntegerType.DIM0,i,null);
                        if (i >= initVal.vals.size()) {
                            break;
                            //assigns.add(new MemCpy(midName,offset,new IntegerValue(IntegerType.DIM0,0,null)));
                        } else {
                            assigns.add(new MemCpy(midName,offset,initVal.vals.get(i)));
                        }
                    }
                }
                case VAR_2 -> {
                    for (int i = 0; i < size1; i++) {
                        if (i >= initVal.deepVals.size()) {
                            // 不赋值了！爬开！
                            break;
                        }
                        for (int j = 0; j < size2; j++) {
                            if (j >= initVal.deepVals.get(i).size()) {
                                break;
                            }
                            IntegerValue offset = new IntegerValue(IntegerType.DIM0,i * size2 + j,null);
                            assigns.add(new MemCpy(midName,offset,initVal.deepVals.get(i).get(j)));
                        }
                    }
                }
            }
            IRModule.addInstrs(assigns);
        }

    }
}