package MidCode.Value.FuncValue;

import MidCode.Value.Value;
import Target.Instructions.MipsCode;

import java.util.ArrayList;
import java.util.List;

public class Params extends Value{
    private int size;
    private List<Value> params;

    public Params(List<Value> params,int size) {
        this.params = params;
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public List<Value> getParams() {
        return params;
    }

    @Override
    public String toString() {
        return null;
    }

    @Override
    public Value clone() {
        List<Value> clonedVal = new ArrayList<>();
        for (Value value : this.params) {
            clonedVal.add(value.clone());
        }
        return new Params(clonedVal,this.size);
    }

    public List<MipsCode> toMipsCode() {
        return null;
    }
}