package Target.Symbol;

import MidCode.Table.Symbol;
import Target.GRF.Register;
import Target.Immediate;
import Target.Operand;
import Target.Tag;

import java.util.LinkedList;

public class MipsSymbol {
    private String name;
    private Tag addrLabel;
    private Integer spOffset; // 在本栈的偏移
    private LinkedList<Register> store = new LinkedList<>();
    private boolean isInReg;
    private Symbol symbol;

    public MipsSymbol(Symbol symbol) {
        this.name = symbol.getMidName();
        this.symbol = symbol;
    }

    public void setAddrLabel(Tag addrLabel) {
        this.addrLabel = addrLabel;
    }

    public void setSpOffset(int spOffset) {
        this.spOffset = spOffset;
    }

    public Integer getSpOffset() {
        return spOffset;
    }

    public Operand getUser() {
        if (isInReg) {
            return store.getLast();
        } else {
            if (spOffset == null) {
                return null;
            }
            return new Immediate(spOffset);
        }
    }

    public Tag getAddrLabel() {
        return addrLabel;
    }

    public boolean isGlobal(){
        return symbol.isGlobal();
    }
}
