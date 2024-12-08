package MidCode.Instructions;

import MidCode.IRModule;
import MidCode.Table.VarSymbol;
import MidCode.Value.IntegerValue.IntegerValue;
import MidCode.Value.Value;
import Target.GRF.GRF;
import Target.Immediate;
import Target.Instructions.*;
import Target.Symbol.MipsController;

import java.util.List;

public class Return extends Instruction{
    private Value retVal;

    public Return(Value retVal) {
        this.retVal = retVal;
    }

    @Override
    public String toString() {
        if (retVal == null) {
            return "ret " + "\n";
        } else {
            return "ret " + retVal + "\n";
        }
    }

    public List<MipsCode> toMipsCode() {
        mipsCodes.add(new Annotation(this.toString()));
        if (retVal instanceof IntegerValue ret) {
            mipsCodes.add(new Load(InstrType.li,GRF.getReg("v0"),null,new Immediate(ret.getDim0Value())));
        } else if (retVal instanceof VarSymbol ret){
            mipsCodes.add(new Load(InstrType.lw,GRF.getReg("v0"),MipsController.getAddr(ret),GRF.getReg("sp")));
        }
        int stackSize =  IRModule.getCurFunc().getStackSize() ;
        if (IRModule.getCurFunc().containSubFunc) {
            mipsCodes.add(new Load(InstrType.lw, GRF.getReg("ra"), new Immediate(0), GRF.getReg("sp")));
        }
        //TODO
        mipsCodes.add(new Alu(InstrType.addiu,GRF.getReg("sp"),GRF.getReg("sp"),new Immediate(stackSize)));
        mipsCodes.add(new Jump(InstrType.jr, GRF.getReg("ra")));
        return mipsCodes;
    }
}
