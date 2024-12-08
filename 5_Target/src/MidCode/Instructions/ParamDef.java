package MidCode.Instructions;

import MidCode.Table.Symbol;
import Target.GRF.GRF;
import Target.GRF.Register;
import Target.Immediate;
import Target.Instructions.InstrType;
import Target.Instructions.Load;
import Target.Instructions.MipsCode;
import Target.Instructions.Move;
import Target.Operand;
import Target.Symbol.MipsController;
import Target.Symbol.MipsSymbol;

import java.util.List;

public class ParamDef extends Instruction {
    private String name;
    private Symbol symbol;

    public ParamDef(String name, Symbol symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        //return "param_int, " + symbol.getMidName() + ",_,_\n";
        return "param_int " + symbol.getMidName() + "\n";
    }

    public List<MipsCode> toMipsCode() {
        // 从 a0 a1 a2 a3上取
        MipsSymbol mipsSymbol = new MipsSymbol(symbol);
        MipsController.add(symbol.getMidName(), mipsSymbol);

        return mipsCodes;
    }
    public String getName() {
        return symbol.getMidName();
    }

    public Symbol getSymbol() {
        return symbol;
    }
}
