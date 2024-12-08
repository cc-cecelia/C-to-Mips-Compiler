package MidCode.Table;

import Fronted.ASTComponent.AST;
import MidCode.Value.Value;
import Target.Operand;

import java.lang.reflect.Type;

public abstract class Symbol extends Value{
    private AST ASTComponent;
    private String midName;
    private MidType symbolType;

    protected Value value;

    protected boolean isGlobal;
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

    public boolean isGlobal() {
        return isGlobal;
    }

    @Override
    public SymbolType getDim() {
        return switch (symbolType.type()) {
            case VAR_0 ,Func -> SymbolType.VAR_0;
            case VAR_1 -> SymbolType.VAR_1;
            case VAR_2 -> SymbolType.VAR_2;
        };
    }

}
