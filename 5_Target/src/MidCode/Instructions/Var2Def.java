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

public class Var2Def extends VarDef {
    private int size1;
    private int size2;
    private int size;

    private int[][] initVal;

    public Var2Def(boolean isConst, String varName, Symbol symbol,int size1, int size2) {
        super(isConst, varName,symbol);
        this.size1 = size1;
        this.size2 = size2;
        this.initVal = new int[size1][size2];
        this.size = size1 * size2;
    }

    public int getSize() {
        return size;
    }
    @Override
    public String toString() {
        return super.toString() + "[" + size + "]\n";
    }

    public List<MipsCode> toMipsCode() {
        mipsCodes.add(new Annotation(this.toString()));

        MipsSymbol symbol = new MipsSymbol(this.symbol);
        MipsController.add(varName,symbol);
        if (this.symbol.isGlobal()) {
            String label = Assembler.generateSpaceLabel();
            symbol.setAddrLabel(new Tag(label));
            mipsCodes.add(new Space(label, size * 4));
        } else {
            symbol.setSpOffset(IRModule.getCurFunc().getPos(4* size));
        }
        return mipsCodes;
    }
}
