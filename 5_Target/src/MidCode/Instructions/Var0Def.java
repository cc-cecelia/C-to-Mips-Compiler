package MidCode.Instructions;

import MidCode.IRModule;
import MidCode.Table.Symbol;
import Target.GRF.GRF;
import Target.Immediate;
import Target.Instructions.*;
import Target.Assembler;
import Target.Symbol.MipsController;
import Target.Symbol.MipsSymbol;
import Target.Tag;

import java.util.List;

public class Var0Def extends VarDef {
    private int initVal;

    public Var0Def(boolean isConst, String varName, Symbol symbol) {
        super(isConst, varName,symbol);
    }

    @Override
    public String toString() {
        return super.toString() + "\n";
    }

    @Override
    public List<MipsCode> toMipsCode() {
        mipsCodes.add(new Annotation(this.toString()));

        if (symbol.isGlobal()) {
            // 生成 spaceLabel: .space 4
            String label = Assembler.generateSpaceLabel();
            MipsSymbol symbol = new MipsSymbol(this.symbol);
            symbol.setAddrLabel(new Tag(label));
            MipsController.add(varName,symbol);
            mipsCodes.add(new Space(label, 4));
        } else {
            // 压栈sp
            MipsSymbol symbol = new MipsSymbol(this.symbol);
            MipsController.add(varName,symbol);
            symbol.setSpOffset(IRModule.getCurFunc().getPos(4));
        }
        return mipsCodes;
    }
}
