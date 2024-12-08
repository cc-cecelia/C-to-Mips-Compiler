package MidCode.Table;

import Fronted.ASTComponent.AST;
import MidCode.Instructions.Function;
import MidCode.Value.FuncValue.Params;
import MidCode.Value.Value;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FuncSymbol extends Symbol{
    private int tableNumber;
    private VarSymbol retVal;
    private int stackSize;// 局部变量 + 中间变量

    private HashMap<FuncSymbol,Integer> extraParamAddr;
    public FuncSymbol(AST ASTComponent, String midName, MidType type) {
        super(ASTComponent, midName, type);
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public void setStackSize(int stackSize) {
        this.stackSize = stackSize;
    }

    public int getStackSize() {
        return stackSize;
    }

    public void setRealParamsStack() {
        // TODO:不知道要不要传入实参
    }

    /*******************错误处理*********************/
    public boolean isParamCntMatch(int num) {
        Params params = (Params) getValue();
        return params.getSize() == num;
    }

    public boolean isParamTypeMatch(ArrayList<Value> rParams) {
        List<Value> params = ((Params) getValue()).getParams();
        for (int i = 0; i < rParams.size(); i++) {
            Value fParam = params.get(i);
            Value rParam = rParams.get(i);
            if (fParam.getDim() != rParam.getDim()) {
                return false;
            }
        }
        return true;
    }

    public void setRetVal(VarSymbol retVal) {
        this.retVal = retVal;
    }

    public VarSymbol getRetVal() {
        return retVal;
    }

    @Override
    public String toString() {
        return getMidName();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof FuncSymbol) {
            return getMidName().equals(((FuncSymbol) obj).getMidName());
        } else {
            return false;
        }
    }
}
