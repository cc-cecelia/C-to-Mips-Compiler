package Fronted.Parser.SymbleTable;

import Fronted.Lexer.Token;
import Fronted.Parser.SymbleTable.Func.FuncParam;
import Fronted.Parser.SymbleTable.Func.SymFuncType;

import java.util.ArrayList;
import java.util.HashMap;

public class SymbolTable {
    public static int index;
    protected int id;
    private int fatherId;
    protected FuncSymbol funcBelonging;
    protected FuncSymbol func;
    private HashMap<String, Symbol> directory;

    public SymbolTable(int fatherId, FuncSymbol funcBelonging, FuncSymbol func) {
        this.id = index;
        index++;
        this.fatherId = fatherId;
        this.funcBelonging = funcBelonging;
        this.directory = new HashMap<>();
        this.func = func;
    }

    public boolean exist(Token token, Class<?> Class) {
        if (directory.containsKey(token.context)) {
            return directory.get(token.toString()).getClass() == Class;
        } else {
            return false;
        }
    }

    public boolean duplicate(Symbol symbol) {
        return this.directory.containsKey(symbol.token.context);
//        if (this.directory.containsKey(symbol.token.context)) {
//            return this.directory.get(symbol.token.context).equals(symbol);
//        }
//        return false;
    }

    public void put(Token ident, Symbol symbol) {
        this.directory.put(ident.context, symbol);
    }

    public int getFatherId() {
        return fatherId;
    }

    public boolean isRoot() {
        return this.id == 0;
    }

    public int checkFuncRParams(Token token, ArrayList<FuncParam> rParams) {
        FuncSymbol func = (FuncSymbol) this.directory.get(token.context);
        if (!func.isNumberMatch(rParams.size())) {
            return 0;
        }
        if (!func.isTypeMatch(rParams)) {
            return 1;
        }
        return -1;
    }

    public boolean checkVoidReturn(Boolean isNull) {
        if (funcBelonging != null && funcBelonging.funcType == SymFuncType.VOID) {
            return !isNull;
        }
        // funcSym == null => int main
        return false;
    }

    public boolean constReassign(Token token) {
        Symbol symbol = this.directory.get(token.context);
        return ((VarSymbol) symbol).isConst;
    }

    public Symbol getSymbol(Token token) {
        return this.directory.get(token.context);
    }
}
