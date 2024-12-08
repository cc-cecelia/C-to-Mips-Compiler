package Parser.GrammaticalComponent;


import java.io.IOException;

public class Decl {
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
        //Output.component(ParseType.Decl);
    }
}
