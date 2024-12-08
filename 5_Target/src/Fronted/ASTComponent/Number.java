package Fronted.ASTComponent;

import Fronted.Lexer.Token;
import Fronted.Parser.ParseType;
import IO.Output;
import MidCode.Value.IntegerValue.IntegerValue;
import MidCode.Value.Value;

import java.io.IOException;
import java.util.ArrayList;

public class Number implements AST {
    protected Value val;
    private Token intConst;

    public Number(Token intConst) {
        this.intConst = intConst;
    }

    public void print() throws IOException {
        intConst.print();
        Output.component(ParseType.Number);
    }

    @Override
    public void generateMidCode() {
        this.val = new IntegerValue(intConst.number);
    }

    @Override
    public Value getVal() {
        return val;
    }

    public int getCalculable() {
        return intConst.number;
    }

    public ArrayList<Token> toTokens() {
        ArrayList<Token> tokens = new ArrayList<>();
        tokens.add(intConst);
        return tokens;
    }
}
