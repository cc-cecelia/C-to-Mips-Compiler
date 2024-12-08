package Parser.GrammaticalComponent;

import Lexer.Token;
import IO.Output;
import Parser.ParseType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class VarDef {
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
}
