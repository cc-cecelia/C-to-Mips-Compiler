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
    protected FuncSymbol funcSym;
    private HashMap<String, Symbol> directory;

    public SymbolTable(int fatherId, FuncSymbol funcSym) {
        this.id = index;
        index++;
        this.fatherId = fatherId;
        this.funcSym = funcSym;
        this.directory = new HashMap<>();
    }

    public boolean exist(Token token, Class<?> Class) {
        if (directory.containsKey(token.context)) {
            return directory.get(token.toString()).getClass() == Class;
        } else {
            return false;
        }
    }

    public boolean duplicate(Token token) {
        return this.directory.containsKey(token.context);
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
        if (funcSym != null && funcSym.funcType == SymFuncType.VOID) {
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
