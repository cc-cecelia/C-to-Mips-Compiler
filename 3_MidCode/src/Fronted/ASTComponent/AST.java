package Fronted.ASTComponent;

import java.io.IOException;

public interface AST {
    public void print() throws IOException;

    public void generateMidCode();
}
