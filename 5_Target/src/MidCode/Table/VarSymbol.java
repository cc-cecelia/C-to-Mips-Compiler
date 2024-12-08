package MidCode.Table;

import Fronted.ASTComponent.AST;
import MidCode.Value.BoolValue;
import MidCode.Value.IntegerValue.IntegerValue;
import MidCode.Value.Value;
import Target.GRF.GRF;
import Target.GRF.Register;
import Target.Immediate;
import Target.Instructions.InstrType;
import Target.Instructions.Load;
import Target.Instructions.MipsCode;
import Target.Operand;
import Target.Symbol.MipsController;
import Target.Symbol.MipsSymbol;

import java.util.ArrayList;
import java.util.List;

public class VarSymbol extends Symbol{
    private boolean isConst;
    private int size1;
    private int size2;
    private boolean isParam;

    public VarSymbol(AST ASTComponent, String midName, MidType type,boolean isConst) {
        super(ASTComponent, midName, type);
        this.isConst = isConst;
        this.isGlobal = MidController.isGlobal();
    }

    public void setParam() {
        isParam = true;
    }

    public boolean isParam() {
        return isParam;
    }

    public boolean isConst() {
        return isConst;
    }

    public int getIntegerVal() {
        return ((IntegerValue)value).getDim0Value();
    }

    public int getIntegerVal(int i) {
        return ((IntegerValue)value).getDim1Value(i);
    }

    public int getIntegerVal(int i, int j) {
        return ((IntegerValue)value).getDim2Value(i,j);
    }

    public void setSize(int size) {
        this.size1 = size;
    }

    public void setSize(int size1,int size2) {
        this.size1 = size1;
        this.size2 = size2;
    }

    // 目前想不到用法，或许查询数组越界？但是感觉只有java才在编译时查询
    public int getSize1() {
        return size1;
    }

    public int getSize2() {
        return size2;
    }

    @Override
    public String toString() {
        return getMidName();
    }

    @Override
    public Value clone() {
        return null;
    }

    public List<MipsCode> toMipsCode() {
        return null;
    }
}
