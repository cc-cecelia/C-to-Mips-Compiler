package MidCode.Value;

import Target.Instructions.MipsCode;

import java.util.List;

public class BoolValue extends Value{
    private boolean aBoolean;

    public BoolValue(boolean aBoolean) {
        this.aBoolean = aBoolean;
    }

    public boolean getRes() {
        return aBoolean;
    }
    @Override
    public String toString() {
        return null;
    }

    @Override
    public Value clone() {
        return null;
    }

    @Override
    public List<MipsCode> toMipsCode() {
        return null;
    }
}
