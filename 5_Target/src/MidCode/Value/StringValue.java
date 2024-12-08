package MidCode.Value;

import Target.Instructions.MipsCode;

import java.util.List;

public class StringValue extends Value{
    private String ifLabel;
    private String ifBlockBegin;

    public void setIfLabel(String ifLabel) {
        this.ifLabel = ifLabel;
    }

    public void setIfBlockBegin(String ifBlockBegin) {
        this.ifBlockBegin = ifBlockBegin;
    }

    public String getIfLabel() {
        return ifLabel;
    }

    public String getIfBlockBegin() {
        return ifBlockBegin;
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
