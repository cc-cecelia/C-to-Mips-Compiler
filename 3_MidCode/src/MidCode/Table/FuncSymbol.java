package MidCode.Table;

import Fronted.ASTComponent.AST;
import MidCode.Value.Value;

import java.util.Stack;

public class FuncSymbol extends Symbol{
    private int tableNumber;
    public FuncSymbol(AST ASTComponent, String midName, MidType type) {
        super(ASTComponent, midName, type);
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public void setRealParamsStack() {
        // TODO:不知道要不要传入实参
    }

    @Override
    public String toString() {
        return getMidName();
    }

    @Override
    public Value clone() {
        FuncSymbol cloned = new FuncSymbol(getASTComponent(),getMidName(),getSymbolType());
        cloned.tableNumber = this.tableNumber;
        return cloned;
    }
}
