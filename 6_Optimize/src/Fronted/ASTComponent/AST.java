package Fronted.ASTComponent;

import MidCode.Value.Value;

import java.io.IOException;

public interface AST {
    public void print() throws IOException;

    public void generateMidCode();

    public Value getVal();
}
