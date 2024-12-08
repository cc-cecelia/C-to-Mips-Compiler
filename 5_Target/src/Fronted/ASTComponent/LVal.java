package Fronted.ASTComponent;

import Fronted.Lexer.Token;
import IO.Output;
import Fronted.Parser.ParseType;
import MidCode.MidGenerator;
import MidCode.IRModule;
import MidCode.Instructions.Calculate;
import MidCode.Table.*;
import MidCode.Value.IntegerValue.IntegerType;
import MidCode.Value.IntegerValue.IntegerValue;
import MidCode.Value.PointerValue;
import MidCode.Value.SymbolValue;
import MidCode.Value.Value;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LVal implements AST {
    private Token ident;
    private List<Token> lBracks = new ArrayList<>();
    private List<Token> rBracks = new ArrayList<>();
    private List<Exp> exps = new ArrayList<>();

    public LVal(Token ident, List<Token> lBracks, List<Token> rBracks, List<Exp> exps) {
        this.ident = ident;
        this.lBracks = lBracks;
        this.rBracks = rBracks;
        this.exps = exps;
    }

    public Token getIdent() {
        return ident;
    }
    public void print() throws IOException {
        ident.print();
        for (int i = 0; i < exps.size(); i++) {
            lBracks.get(i).print();
            exps.get(i).print();
            rBracks.get(i).print();
        }
        Output.component(ParseType.LVal);
    }

    protected Value val;
    public void generateMidCode() {
        VarSymbol symbol = (VarSymbol) MidController.lookUp(MidController.curLevel,ident.context,new MidType(SymbolType.VAR_0));
        if (symbol == null) {
            throw new IllegalStateException("怎么找不找呢");
        }
        switch (symbol.getSymbolType().type()) {
            case VAR_0 -> {
                if (symbol.isConst()) {
                    val = symbol.getValue();
                } else {
                    val = symbol;
                }
            }
            case VAR_1 -> {
                if (!exps.isEmpty()) {
                    exps.get(0).generateMidCode();
                    if (symbol.isConst() && exps.get(0).val instanceof IntegerValue intOff) {
                        val = new IntegerValue(symbol.getIntegerVal(intOff.getDim0Value()));
                    } else {
                        val = new SymbolValue(symbol, exps.get(0).val, SymbolType.VAR_0);
                    }
                } else {
                    //val = new PointerValue(symbol);
                    val = new SymbolValue(symbol,null,SymbolType.VAR_1);
                }
            }
            case VAR_2 -> {
                if (exps.isEmpty()) {
                    val = new SymbolValue(symbol,null,SymbolType.VAR_2);
                } else if (exps.size() == 1) {
                    exps.get(0).generateMidCode();
                    Value off1 = exps.get(0).val;// 一维偏移
                    int size2 = symbol.getSize2();
                    String tmp1 = MidGenerator.generateTmpName(null);
                    VarSymbol tmp1Sym = new VarSymbol(null, tmp1, new MidType(SymbolType.VAR_0), false);
                    Calculate mul = new Calculate(tmp1Sym, "*", new IntegerValue(IntegerType.DIM0, size2, null), off1);
                    IRModule.addInstr(mul);
                    String tmp2 = MidGenerator.generateTmpName(null);
                    VarSymbol tmp2Sym = new VarSymbol(null,tmp2, new MidType(SymbolType.VAR_0),false);
                    IRModule.addInstr(new Calculate(tmp2Sym,"*",tmp1Sym,new IntegerValue(4)));
                    String tmp3 = MidGenerator.generateTmpName(null);
                    VarSymbol tmp3Sym = new VarSymbol(null,tmp3, new MidType(SymbolType.VAR_1),false);
                    IRModule.addInstr(new Calculate(tmp3Sym,"+",new PointerValue(symbol),tmp2Sym));
                    val = tmp3Sym;
                    //val = new SymbolValue(symbol, tmp1Sym);
                } else {
                    exps.get(0).generateMidCode();
                    Value off1 = exps.get(0).val;// 一维偏移
                    exps.get(1).generateMidCode(); // 二维偏移
                    Value off2 = exps.get(1).val;
                    // 重新计算位置，所以此时要新增计算地址的指令

                    // array[5][6] -> array[3][4] => 6*3+4 我需要获取第二维的size
                    int size2 = symbol.getSize2();
                    // Dim2Size * Dim1Offset = 6*3;
                    String tmp1 = MidGenerator.generateTmpName(null);
                    VarSymbol tmp1Sym = new VarSymbol(null, tmp1, new MidType(SymbolType.VAR_0), false);
                    Calculate mul = new Calculate(tmp1Sym, "*", new IntegerValue(IntegerType.DIM0, size2, null), off1);
                    IRModule.addInstr(mul);
                    // Dim2Offset + 上一步结果 = 6*3+4
                    String tmp2 = MidGenerator.generateTmpName(null);
                    VarSymbol tmp2Sym = new VarSymbol(null, tmp2, new MidType(SymbolType.VAR_0), false);
                    Calculate add = new Calculate(tmp2Sym, "+", tmp1Sym, off2);
                    IRModule.addInstr(add);
                    val = new SymbolValue(symbol, tmp2Sym,SymbolType.VAR_0);
                }
            }
        }
    }

    @Override
    public Value getVal() {
        return val;
    }

    public int getCalculable() {
        VarSymbol symbol = (VarSymbol) MidController.lookUp(MidController.curLevel,ident.context,new MidType(SymbolType.VAR_0));
        if (symbol == null) {
            throw new IllegalStateException("怎么找不着呢？");
        }
        if (symbol.getSymbolType().equals(new MidType(SymbolType.VAR_0))) {
            return symbol.getIntegerVal();
        } else if (symbol.getSymbolType().equals(new MidType(SymbolType.VAR_1))) {
            return symbol.getIntegerVal(exps.get(0).getCalculable());
        } else if (symbol.getSymbolType().equals(new MidType(SymbolType.VAR_2))) {
            return symbol.getIntegerVal(exps.get(0).getCalculable(),exps.get(1).getCalculable());
        } else {
            throw new IllegalStateException("怎么成函数了！");
        }
    }

    public ArrayList<Token> toTokens() {
        ArrayList<Token> tokens = new ArrayList<>();
        tokens.add(ident);
        for (int i = 0; i < exps.size(); i++) {
            tokens.add(lBracks.get(i));
            tokens.addAll(exps.get(i).toTokens());
            tokens.add(rBracks.get(i));
        }
        return tokens;
    }
}
