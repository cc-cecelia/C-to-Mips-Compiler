package MidCode.Table;

import Fronted.ASTComponent.FuncDef;

import java.util.HashMap;

public class MidSymbolTable {
    protected static int index = 0;
    protected int id;
    protected int fatherId;
    private HashMap<String, Symbol> symDirectory;


    public MidSymbolTable(int fatherId) {
        this.id = index;
        index++;
        this.fatherId = fatherId;
        this.symDirectory = new HashMap<>();
    }

    public boolean exist(String token, MidType type) {
        if (symDirectory.containsKey(token)) {
            return symDirectory.get(token).getSymbolType().equals(type);
        } else {
            return false;
        }
    }

    public boolean duplicate(String token) {
        return this.symDirectory.containsKey(token);
    }

    public void put(String ident, Symbol symbol) {
        this.symDirectory.put(ident, symbol);
    }

    public int getFatherId() {
        return fatherId;
    }

    public boolean isRoot() {
        return this.id == 0;
    }

    public Symbol getSymbol(String token, MidType type) {
        //String ident = token.split("_")[0];
        Symbol symbol = this.symDirectory.getOrDefault(token,null);
        if (symbol == null) {
            return null;
        } else {
            if (symbol.getSymbolType().equals(type)) {
                return symbol;
            } else {
                return null;
            }
        }
    }

//    public int checkFuncRParams(Token token, ArrayList<FuncParam> rParams) {
//        FuncSymbol func = (FuncSymbol) this.directory.get(token.context);
//        if (!func.isNumberMatch(rParams.size())) {
//            return 0;
//        }
//        if (!func.isTypeMatch(rParams)) {
//            return 1;
//        }
//        return -1;
//    }

//    public boolean checkVoidReturn(Boolean isNull) {
//        if (funcSym != null && funcSym.funcType == SymFuncType.VOID) {
//            return !isNull;
//        }
//        // funcSym == null => int main
//        return false;
//    }

//    public boolean constReassign(Token token) {
//        Symbol symbol = this.directory.get(token.context);
//        return ((VarSymbol) symbol).isConst;
//    }

}
