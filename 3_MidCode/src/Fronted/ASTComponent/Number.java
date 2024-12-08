package Fronted.ASTComponent;

import Fronted.Lexer.Token;
import IO.Output;
import Fronted.Parser.ParseType;
import MidCode.Value.IntegerValue.IntegerType;
import MidCode.Value.IntegerValue.IntegerValue;
import MidCode.Value.Value;

import java.io.IOException;
import java.util.ArrayList;

public class Number implements AST {
    private Token intConst;

    public Number(Token intConst) {
        this.intConst = intConst;
    }

    public void print() throws IOException {
        intConst.print();
        Output.component(ParseType.Number);
    }


    protected Value val;
    @Override
    public void generateMidCode() {
        this.val = new IntegerValue(IntegerType.DIM0, intConst.number, null);
    }

    public int getCalculable() {
        return intConst.number;
    }

    public ArrayList<Token> toTokens(){
        ArrayList<Token> tokens = new ArrayList<>();
        tokens.add(intConst);
        return tokens;
    }
}
