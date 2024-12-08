package Fronted.ASTComponent;

import Fronted.Lexer.Token;
import Fronted.Parser.ParseType;
import IO.Output;
import MidCode.Value.Value;

import java.io.IOException;
import java.util.ArrayList;

public class Exp implements AST {
    private AddExp addExp;

    public Exp(AddExp addExp) {
        this.addExp = addExp;
    }

    public void print() throws IOException {
        addExp.print();
        Output.component(ParseType.Exp);
    }

    protected Value val;
    @Override
    public void generateMidCode() {
        addExp.generateMidCode();
        this.val = addExp.val;
    }

    @Override
    public Value getVal() {
        return val;
    }

    public int getCalculable() {
        return addExp.getCalculable();
    }

    public ArrayList<Token> toTokens() {
        return addExp.toTokens();
    }
}
