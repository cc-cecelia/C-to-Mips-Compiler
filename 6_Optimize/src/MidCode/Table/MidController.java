package MidCode.Table;


import java.util.HashMap;

public class MidController {
    public static int curLevel = 0;
    protected static HashMap<Integer, MidSymbolTable> symbolTables;
    static  {
        symbolTables = new HashMap<>();
        MidSymbolTable root = new MidSymbolTable(-1);
        symbolTables.put(root.id, root);
    }

    public static String loopLevel = null;
    protected static HashMap<String, String[]> loopTables;
    static {
        loopTables = new HashMap<>();
    }

    public static void enterNewLoop(String cur,String end, String change) {
        String[] loopPara = {end,change,loopLevel};
        loopTables.put(cur,loopPara);
        loopLevel = cur;
    }

    public static void exitLoop() {
        loopLevel = loopTables.get(loopLevel)[2];
    }

    public static String getCurLoopEnd() {
        return loopTables.get(loopLevel)[0];
    }

    public static String getCurLoopChange() {
        return loopTables.get(loopLevel)[1];
    }

    public static void enterNewScope() {
        MidSymbolTable symbolTable = new MidSymbolTable(curLevel);
        curLevel = symbolTable.id;
        symbolTables.put(curLevel, symbolTable);
    }

    public static void exitScope() {
        curLevel = symbolTables.get(curLevel).getFatherId();
    }

    public static boolean isDefined(String token) {
        return symbolTables.get(curLevel).duplicate(token);
    }

    public static void addNewSymbol(String token, Symbol symbol) {
        if (!isDefined(token)) {
            symbolTables.get(curLevel).put(token, symbol);
        }
    }
    public static Symbol lookUp(int level, String ident, MidType type) {
        MidSymbolTable curTable = symbolTables.get(level);
        Symbol symbol = curTable.getSymbol(ident, type);
        if (symbol != null) {
            return symbol;
        } else {
            if (curTable.isRoot()) {
                return null;
            } else {
                return lookUp(curTable.getFatherId(), ident, type);
            }
        }
    }

    public static boolean isGlobal() {
        return curLevel == 0;
    }
}
