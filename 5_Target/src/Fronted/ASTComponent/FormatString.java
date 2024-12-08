package Fronted.ASTComponent;

import Fronted.Lexer.Token;
import MidCode.Value.Value;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FormatString implements AST{
    private Token formatString;

    public FormatString(Token formatString) {
        this.formatString = formatString;
    }

    public Token getFormatString() {
        return formatString;
    }

    @Override
    public void print() throws IOException {
        formatString.print();
    }
    @Override
    public void generateMidCode() {

    }

    @Override
    public Value getVal() {
        return null;
    }
}
