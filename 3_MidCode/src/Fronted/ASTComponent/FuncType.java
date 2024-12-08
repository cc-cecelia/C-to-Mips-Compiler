package Fronted.ASTComponent;

import Fronted.Lexer.Token;
import IO.Output;
import Fronted.Parser.ParseType;

import java.io.IOException;

public class FuncType implements AST {
    Token funcType;

    public FuncType(Token funcType) {
        this.funcType = funcType;
    }

    public void print() throws IOException {
        funcType.print();
        Output.component(ParseType.FuncType);
    }

    @Override
    public void generateMidCode() {

    }
}
