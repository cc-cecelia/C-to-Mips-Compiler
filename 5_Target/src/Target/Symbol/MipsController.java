package Target.Symbol;

import MidCode.IRModule;
import MidCode.Table.Symbol;
import Target.Immediate;
import Target.Operand;

import java.util.HashMap;

public class MipsController {
    //private static ArrayList<MipsSymbol> symbols = new ArrayList<>();
    private static HashMap<String, MipsSymbol> symbols= new HashMap<>();

    public static void add(String name, MipsSymbol symbol) {
        symbols.put(name,symbol);
    }

    public static MipsSymbol getSym(Symbol sym) {
        if (symbols.get(sym.getMidName()) != null) {
            return symbols.get(sym.getMidName());
        } else {
            // 临时变量
            MipsSymbol tmp = new MipsSymbol(sym);
            add(sym.getMidName(),tmp);
            tmp.setSpOffset(IRModule.getCurFunc().getPos(4));
            return tmp;
        }
    }

    public static Operand getAddr(Symbol symbol) {
        MipsSymbol mipsSymbol = getSym(symbol);
        if (mipsSymbol.isGlobal()) {
            return mipsSymbol.getAddrLabel();
        } else {
            return new Immediate(mipsSymbol.getSpOffset());
        }
    }
}
