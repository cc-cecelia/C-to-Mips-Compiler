package Fronted.ASTComponent;

import Fronted.Lexer.Token;
import Fronted.Parser.ParseType;
import IO.Output;
import MidCode.Value.Value;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InitVal implements AST {
    private Exp exp;
    private Token lBrace;
    private InitVal initVal;
    private List<Token> commas;
    private List<InitVal> initVals = new ArrayList<>();
    private Token rBrace;
    private int rule;

    public InitVal(Exp exp) {
        this.exp = exp;
        rule = 1;
    }

    public InitVal(Token lBrace, InitVal initVal, List<Token> commas, List<InitVal> initVals, Token rBrace) {
        this.lBrace = lBrace;
        this.initVal = initVal;
        this.commas = commas;
        this.initVals = initVals;
        this.rBrace = rBrace;
        rule = 2;
    }

    public void print() throws IOException {
        switch (rule) {
            case 1 -> exp.print();
            case 2 -> {
                lBrace.print();
                if (initVal != null) {
                    initVal.print();
                    if (!initVals.isEmpty()) {
                        for (int i = 0; i < initVals.size(); i++) {
                            commas.get(i).print();
                            initVals.get(i).print();
                        }
                    }
                }
                rBrace.print();
            }
        }
        Output.component(ParseType.InitVal);
    }

    // InitVal → Exp | '{' [ InitVal { ',' InitVal } ] '}'
    protected Value val;
    protected List<Value> vals;
    protected List<List<Value>> deepVals;
    protected Integer size1,size2;
    @Override
    public void generateMidCode() {
        switch (rule) {
            case 1 -> {
                // 普通变量
                exp.generateMidCode();
                val = exp.val;
            }
            case 2 -> {
                if (size2 == null) {
                    // 一维数组
                    vals = new ArrayList<>();
                    if (this.initVal != null) {
                        this.initVal.generateMidCode();
                        vals.add(initVal.val);
                        for (InitVal initVal : this.initVals) {
                            initVal.generateMidCode();
                            vals.add(initVal.exp.val);
                        }
                    }
                } else {
                    // 二维数组
                    deepVals = new ArrayList<>();
                    if (this.initVal != null) {
                        this.initVal.generateMidCode();
                        deepVals.add(new ArrayList<>());
                        deepVals.get(0).addAll(this.initVal.vals);
                        for (InitVal initVal : this.initVals) {
                            initVal.generateMidCode();
                            deepVals.add(new ArrayList<>());
                            deepVals.get(deepVals.size() - 1).addAll(initVal.vals);
                        }
                    }
                }
            }
        }
    }

    @Override
    public Value getVal() {
        return null;
    }
}
