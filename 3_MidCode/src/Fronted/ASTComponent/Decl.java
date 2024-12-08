package Fronted.ASTComponent;


import java.io.IOException;

public class Decl implements AST {
    // Decl → ConstDecl | VarDecl
    private ConstDecl constDecl;
    private VarDecl varDecl;
    private int rule;

    public Decl(ConstDecl constDecl) {
        this.constDecl = constDecl;
        rule = 1;
    }

    public Decl(VarDecl varDecl) {
        this.varDecl = varDecl;
        rule = 2;
    }

    public void print() throws IOException {
        switch (rule) {
            case 1:
                constDecl.print();
                break;
            case 2:
                varDecl.print();
                break;
            default:
                System.out.println("wrong!");
                break;
        }
        //IO.Output.component(ParseType.Decl);
    }

    @Override
    public void generateMidCode() {
        switch (rule) {
            case 1 -> constDecl.generateMidCode();
            case 2 -> varDecl.generateMidCode();
            default -> throw new IllegalStateException("哪有其他情况啊 "+ rule);
        }
    }
}
