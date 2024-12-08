package MidCode.Table;

import Fronted.ASTComponent.AST;
import MidCode.Value.Value;

public abstract class Symbol extends Value{
    private AST ASTComponent;
    private String midName;
    private MidType symbolType;
    protected Value value;

    public Symbol(AST ASTComponent,String midName,MidType type) {
        this.ASTComponent = ASTComponent;
        this.midName = midName;
        this.symbolType = type;
    }

    public MidType getSymbolType() {
        return symbolType;
    }

    public void setValue(Value value) {
        this.value = value;
    }

    public AST getASTComponent() {
        return ASTComponent;
    }

    public String getMidName() {
        return midName;
    }

    public Value getValue() {
        return value;
    }
}
