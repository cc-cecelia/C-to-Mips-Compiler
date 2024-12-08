package Fronted.ASTComponent;

import IO.Output;
import Fronted.Parser.ParseType;
import MidCode.Value.Value;

import java.io.IOException;

public class ConstExp implements AST {
    // ConstExp → AddExp 注：使用的Ident 必须是常量
    private AddExp addExp;

    public ConstExp(AddExp addExp) {
        this.addExp = addExp;
    }

    public void print() throws IOException {
        addExp.print();
        Output.component(ParseType.ConstExp);
    }

    protected Value value;
    @Override
    public void generateMidCode() {

    }

    public int getCalculable() {
        return addExp.getCalculable();
    }
}
