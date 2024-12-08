package Parser.GrammaticalComponent;

import Parser.Output;
import Parser.ParseType;

import java.io.IOException;

public class ConstExp {
    // ConstExp → AddExp 注：使用的Ident 必须是常量
    private AddExp addExp;

    public ConstExp(AddExp addExp) {
        this.addExp = addExp;
    }

    public void print() throws IOException {
        addExp.print();
        Output.component(ParseType.ConstExp);
    }
}
