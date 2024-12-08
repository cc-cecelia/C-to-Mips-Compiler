package Fronted.ASTComponent;

import MidCode.Value.Value;

import java.io.IOException;

public class BlockItem implements AST {
    private Decl decl;
    private Stmt stmt;
    private int rule;

    public BlockItem(Decl decl) {
        this.decl = decl;
        rule = 1;
    }

    public BlockItem(Stmt stmt) {
        this.stmt = stmt;
        rule = 2;
    }

    public boolean isRet() {
        return  (rule == 2 && stmt.getRule() == 7);
    }

    public void print() throws IOException {
        switch (rule) {
            case 1:
                decl.print();
                break;
            case 2:
                stmt.print();
                break;
            default:
                System.out.println("wrong!");
                break;
        }
        //IO.Output.component(ParseType.BlockItem);
    }

    @Override
    public void generateMidCode() {
        switch (rule) {
            case 1 -> decl.generateMidCode();
            case 2 -> stmt.generateMidCode();
            default -> throw new IllegalStateException("不应该出现情况："+rule);
        }
    }

    @Override
    public Value getVal() {
        return null;
    }
}
