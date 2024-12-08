package MidCode.Instructions;

import MidCode.Table.VarSymbol;
import MidCode.Value.IntegerValue.IntegerValue;
import MidCode.Value.SymbolValue;
import MidCode.Value.Value;
import Target.GRF.GRF;
import Target.Immediate;
import Target.Instructions.*;
import Target.Symbol.MipsController;
import Target.Symbol.MipsSymbol;

import java.util.List;

public class PrintInt extends Print{
    private Value value;

    public PrintInt(Value value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "print_int, " + value.toString() + "\n";
    }

    public List<MipsCode> toMipsCode() {
        mipsCodes.add(new Annotation(this.toString()));

        mipsCodes.add(new Load(InstrType.li, GRF.getReg("v0"),null, new Immediate(1)));
        if (value instanceof IntegerValue integer) {
            mipsCodes.add(new Load(InstrType.li,GRF.getReg("a0"),null,new Immediate(integer.getDim0Value())));
        } else if (value instanceof VarSymbol symbol) {
            MipsSymbol mipsSymbol = MipsController.getSym(symbol);
            if (mipsSymbol.getUser() instanceof  Immediate) {
                mipsCodes.add(new Load(InstrType.lw, GRF.getReg("a0"),new Immediate(mipsSymbol.getSpOffset()),GRF.getReg("sp")));
            } else {
                mipsCodes.add(new Move(InstrType.move, GRF.getReg("a0"),mipsSymbol.getUser()));
            }
        }
        mipsCodes.add(new Syscall(InstrType.syscall));
        return mipsCodes;
    }
}
