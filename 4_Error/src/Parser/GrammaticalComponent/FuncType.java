package Parser.GrammaticalComponent;

import Lexer.Token;
import IO.Output;
import Parser.ParseType;

import java.io.IOException;

public class FuncType {
    Token funcType;

    public FuncType(Token funcType) {
        this.funcType = funcType;
    }

    public void print() throws IOException {
        funcType.print();
        Output.component(ParseType.FuncType);
    }
}
