package Fronted.Parser.SymbleTable;

import Fronted.ASTComponent.Exp;
import Fronted.Error.ErrHandler;
import Fronted.Lexer.LexType;
import Fronted.Lexer.Token;
import Fronted.Parser.SymbleTable.Func.CallingTable;
import Fronted.Parser.SymbleTable.Func.FuncParam;
import Fronted.Parser.SymbleTable.Func.SymFuncType;

import java.util.ArrayList;
import java.util.HashMap;

public class TablesController {
    private static TablesController controller = new TablesController();
    /*TEMP BEGIN*/
    public ArrayList<FuncParam> funcParams;
    public boolean isIgnoreTypeMatch = false;
    /*TEMP END*/
    /******************符号表管理***************************/
    private HashMap<Integer, SymbolTable> symbolTables;
    public int curLevel;
    public FuncSymbol curFunc;
    /******************************************************/
    /*****************循环管理******************************/
    private HashMap<Integer, Integer> loopTables;
    private Integer curLoop = null;
    private int loopIndex;
    /******************************************************/
    /***************函数调用管理****************************/
    private HashMap<Integer, CallingTable> callingFunc;
    private Integer examFunc = null;
    private int funcIndex;
    /******************************************************/


    private TablesController() {
        this.symbolTables = new HashMap<>();
        SymbolTable root = new SymbolTable(0, null,null);
        this.symbolTables.put(root.id, root);
        this.funcParams = new ArrayList<>();
        curLevel = 0;
        this.loopTables = new HashMap<>();
        this.callingFunc = new HashMap<>();
    }

    public static TablesController getInstance() {
        return controller;
    }

    public void enterNewScope(FuncSymbol funcSymbol, FuncSymbol isFuncScope) {
        SymbolTable symbolTable = new SymbolTable(curLevel, funcSymbol, isFuncScope);
        curLevel = symbolTable.id;
        this.symbolTables.put(curLevel, symbolTable);
        curFunc = funcSymbol;
    }

    public void exitScope() {
        curLevel = this.symbolTables.get(curLevel).getFatherId();
    }

    public void enterNewLoop() {
        loopTables.put(++loopIndex,curLoop);
        curLoop = loopIndex;
    }

    public void exitLoop() {
        if (curLoop == null) {
            throw new IllegalStateException("都不在循环里了还退 退什么退");
        }
        curLoop = loopTables.get(curLoop);
    }

    public boolean isInLoop() {
        return curLoop != null;
    }

    public void examNewFunc() {
        callingFunc.put(++funcIndex,new CallingTable(funcIndex,examFunc));
        examFunc = funcIndex;
    }

    public void examEnd() {
        examFunc = callingFunc.get(examFunc).getFatherId();
    }

    public boolean isExitFunc() {
        return this.symbolTables.get(curLevel).func != null;
        //return this.symbolTables.get(curLevel).funcBelonging != null;
    }
    public void addNewSymbol(Token token, Symbol symbol) {
        if (!ErrHandler.ignoreScope) {
            this.symbolTables.get(curLevel).put(token, symbol);
        }
    }

    public boolean isRedefined(Symbol symbol) {
        return this.symbolTables.get(curLevel).duplicate(symbol);
    }

    public boolean isEverExist(int level, Token token, Class<?> Class) {
        SymbolTable curTable = this.symbolTables.get(level);
        if (curTable.exist(token, Class)) {
            return true;
        } else {
            if (curTable.isRoot()) {
                return false;
            }
            return isEverExist(curTable.getFatherId(), token, Class);
        }
    }

    public int getScope(int level, Token token, Class<?> Class) {
        SymbolTable curTable = this.symbolTables.get(level);
        if (curTable.exist(token, Class)) {
            return curTable.id;
        } else {
            if (curTable.isRoot()) {
                return -1;
            }
            return getScope(curTable.getFatherId(), token, Class);
        }
    }

    public int checkFuncRParams(Token funcName) {
        for (SymbolTable symbolTable : this.symbolTables.values()) {
            if (symbolTable.exist(funcName, FuncSymbol.class)) {
                return symbolTable.checkFuncRParams(funcName, callingFunc.get(examFunc).getrParams());
            }
        }

        // 未定义函数 已报错
        return -1;
    }

    public FuncSymbol getFuncSym(int level) {
        SymbolTable curTable = this.symbolTables.get(level);
        if (curTable.funcBelonging != null) {
            return curTable.funcBelonging;
        } else {
            if (!curTable.isRoot()) {
                getFuncSym(curTable.getFatherId());
            } else {
                return null;
            }
        }
        return null;
    }

    public boolean checkVoidReturn(boolean isNull) {
        return this.symbolTables.get(curLevel).checkVoidReturn(isNull);
    }


    public boolean constReassign(Token ident) {
        int id = getScope(curLevel, ident, VarSymbol.class);
        if (id != -1) {
            return symbolTables.get(id).constReassign(ident);
        } else {
            // 未定义问题已经报错过
            return false;
        }
    }

    public void addParam(FuncParam tempParam) {
        this.funcParams.add(tempParam);
    }

    public FuncParam toParam(Exp exp) {
        ArrayList<Token> tokens = exp.toTokens();
        // ident( )
        // 1+234
        // a+123
        // a[]
        // a[][]
        FuncParam funcParam = null;
        int curPos = 0;
        while (curPos < tokens.size()) {
            Token curToken = tokens.get(curPos);
            if (curToken.type == LexType.INTCON) {
                funcParam = new FuncParam(0);
                break;
            } else if (curToken.type == LexType.IDENFR) {
                if (curPos == tokens.size() - 1) {
                    VarSymbol varSym = (VarSymbol) get(curLevel, curToken, VarSymbol.class);
                    if (varSym == null) {
                        return null;
                    }
                    funcParam = new FuncParam((varSym).getDim());
                    break;
                } else {
                    Token next = tokens.get(curPos + 1);
                    if (next.type == LexType.LPARENT) {
                        FuncSymbol funcSym = (FuncSymbol) get(curLevel, curToken, FuncSymbol.class);
                        if (funcSym == null) {
                            return null;
                        }
                        if (funcSym.funcType == SymFuncType.VOID) {
                            funcParam = new FuncParam(-1);
                        } else {
                            funcParam = new FuncParam(0);
                        }
                        break;
                    } else if (next.type == LexType.LBRACK) {
                        ArrayList<Token> sub = new ArrayList<>();
                        for (int i = curPos; i < tokens.size(); i++) {
                            sub.add(tokens.get(i));
                        }
                        int red = getRed(sub);

                        Symbol varSym = get(curLevel, curToken, VarSymbol.class);
                        if (varSym == null) {
                            return null;
                        }
                        funcParam = new FuncParam(((VarSymbol) varSym).getDim() - red);
                        break;
                    } else {
                        // 计算符号
                        VarSymbol varSym = (VarSymbol) get(curLevel, curToken, VarSymbol.class);
                        if (varSym == null) {
                            return null;
                        }
                        funcParam = new FuncParam(varSym.dim);
                        break;
                        //throw new RuntimeException("wrong format of Exp!");
                    }
                }
            } else if (curToken.type == LexType.PLUS || curToken.type == LexType.MINU || curToken.type == LexType.LPARENT) {
                curPos++;
                //TODO:不知道这发生了什么，但估计跟错误处理有关
            } else {
                //curPos++;
                throw new RuntimeException("wrong format of Exp!");
            }
        }
        return funcParam;
    }

    public void addRParams(Exp exp) {
        FuncParam param = toParam(exp);
        if (param == null) {
            // c 类错误
            isIgnoreTypeMatch = true;
            return;
        }
        callingFunc.get(examFunc).addParam(param);
        //this.funcRParams.add(param);
    }

    public Symbol get(int level, Token sym, Class<?> Class) {
        SymbolTable curTable = this.symbolTables.get(level);
        if (curTable.exist(sym, Class)) {
            return curTable.getSymbol(sym);
        } else {
            if (curTable.isRoot()) {
                return null;
            }
            return get(curTable.getFatherId(), sym, Class);
        }
    }


    public int getRed(ArrayList<Token> tokens) {
        Token ident = tokens.get(0);
        int red = 0;
        if (tokens.size() == 1) {
            return 0;
        }
        int pos = 1;
        int l = 0;
        int r = 0;
        while(pos < tokens.size()) {
            Token cur = tokens.get(pos);
            if (cur.type != LexType.LBRACK) {
                return red;
            }
            l++;
            ArrayList<Token> off = new ArrayList<>();
            pos++;
            while(l != r) {
                cur = tokens.get(pos);
                if (cur.type == LexType.RBRACK) {
                    r++;
                }
                if (cur.type == LexType.LBRACK) {
                    l++;
                }
                off.add(cur);
                pos++;
            }
            red++;
        }
        return red;
    }
}
