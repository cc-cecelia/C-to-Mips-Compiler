package Fronted.ASTComponent;

import Fronted.Parser.ParseType;
import IO.Output;
import MidCode.Value.IntegerValue.IntegerValue;
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

    protected IntegerValue value;
    @Override
    public void generateMidCode() {
        addExp.generateMidCode();
        if (addExp.val instanceof IntegerValue) {
            this.value = (IntegerValue) addExp.val;
        } else {
            throw new IllegalStateException("constExp不能生成其他类型了！");
        }
    }

    @Override
    public Value getVal() {
        return null;
    }

    public int getCalculable() {
        return addExp.getCalculable();
    }
}
