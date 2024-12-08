package MidCode.Instructions;

import MidCode.IRModule;
import MidCode.Table.Symbol;
import Target.Assembler;
import Target.Instructions.Annotation;
import Target.Instructions.MipsCode;
import Target.Instructions.Space;
import Target.Symbol.MipsController;
import Target.Symbol.MipsSymbol;
import Target.Tag;

import java.util.List;

public class Var1Def extends VarDef{
    private int size;
    private int[] initVal;
    private int buffer;

    public Var1Def(boolean isConst, String varName, Symbol symbol,int size)  {
        super(isConst, varName,symbol);
        this.size = size;
        this.initVal = new int[size];
        this.buffer = size;
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        return super.toString() +  "[" + size + "]\n";
    }

    public List<MipsCode> toMipsCode(boolean isOptimized) {
        List<MipsCode> container = isOptimized ? optimizedMips : this.mipsCodes;

        container.add(new Annotation(this.toString()));

        MipsSymbol symbol = new MipsSymbol(this.symbol);
        MipsController.add(varName,symbol);
        if (this.symbol.isGlobal()) {
            String label = Assembler.generateSpaceLabel();
            symbol.setAddrLabel(new Tag(label));
            container.add(new Space(label, size * 4));
        } else {
            symbol.setSpOffset(IRModule.getCurFunc().getPos(size * 4));
        }
        return container;
    }
}
