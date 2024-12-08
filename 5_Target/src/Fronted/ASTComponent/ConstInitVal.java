package Fronted.ASTComponent;

import Fronted.Lexer.Token;
import IO.Output;
import Fronted.Parser.ParseType;
import MidCode.Table.*;
import MidCode.Value.IntegerValue.IntegerType;
import MidCode.Value.IntegerValue.IntegerValue;
import MidCode.Value.Value;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ConstInitVal implements AST {
    //ConstInitVal → ConstExp
    //| '{' [ ConstInitVal { ',' ConstInitVal } ] '}'

    private ConstExp constExp;
    private Token leftBrace;
    private Token rightBrace;
    private List<Token> commas = new ArrayList<>();
    private List<ConstInitVal> constInitVals = new ArrayList<>();
    private int rule;

    public ConstInitVal(ConstExp constExp) {
        this.constExp = constExp;
        rule = 1;
    }

    public ConstInitVal(Token leftBrace, Token rightBrace, List<Token> commas, List<ConstInitVal> constInitVals) {
        this.leftBrace = leftBrace;
        this.rightBrace = rightBrace;
        this.commas = commas;
        this.constInitVals = constInitVals;
        rule = 2;
    }

    public void print() throws IOException {
        switch (rule) {
            case 1: {
                constExp.print();
                break;
            }
            case 2: {
                int i = 0;
                leftBrace.print();
                if (!constInitVals.isEmpty()) {
                    constInitVals.get(i++).print();
                }
                if (i != constInitVals.size()) {
                    for (; i < constInitVals.size(); i++) {
                        commas.get(i - 1).print();
                        constInitVals.get(i).print();
                    }
                }
                rightBrace.print();
                break;
            }
            default:
                System.out.println("wrong!");
        }
        Output.component(ParseType.ConstInitVal);
    }

    protected IntegerValue initVal;
    protected Symbol symbol;
    protected int size1;
    protected int size2;
    @Override
    public void generateMidCode() {
        switch (symbol.getSymbolType().type()) {
            case VAR_0 -> initVal = new IntegerValue(IntegerType.DIM0, constExp.getCalculable(), null);
            case VAR_1 -> {
                initVal = new IntegerValue(IntegerType.DIM1,size1,null);
                for (ConstInitVal constInitVal : this.constInitVals) {
                    constInitVal.symbol = new VarSymbol(null,null,new MidType(SymbolType.VAR_0),true);
                    constInitVal.generateMidCode();
                    initVal.setDim1(constInitVal.initVal.getDim0Value());
                }
            }
            case VAR_2 -> {
                initVal = new IntegerValue(IntegerType.DIM2,size1,size2);
                for (ConstInitVal constInitVal : this.constInitVals) {
                    constInitVal.symbol = new VarSymbol(null,null,new MidType(SymbolType.VAR_1),true);
                    constInitVal.generateMidCode();
                    initVal.setDim1(constInitVal.initVal.getDim1());
                }
            }
            default -> throw new IllegalStateException("可不能是函数！");
        }
    }

    @Override
    public Value getVal() {
        return null;
    }
}
