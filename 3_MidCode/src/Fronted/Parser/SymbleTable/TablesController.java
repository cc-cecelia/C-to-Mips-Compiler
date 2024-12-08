package Fronted.Parser.SymbleTable;

import Fronted.Lexer.LexType;
import Fronted.Lexer.Token;
import Fronted.ASTComponent.Exp;
import Fronted.Parser.SymbleTable.Func.FuncParam;
import Fronted.Parser.SymbleTable.Func.SymFuncType;

import java.util.ArrayList;
import java.util.HashMap;

import Fronted.Error.ErrorHandler;

public class TablesController {
    private HashMap<Integer, SymbolTable> symbolTables;
    public int curLevel;
    private static TablesController controller = new TablesController();
    public ErrorHandler handler;

    /*TEMP BEGIN*/
    public ArrayList<FuncParam> funcParams;
    public ArrayList<FuncParam> funcRParams;
    public boolean isIgnoreTypeMatch = false;
    /*TEMP END*/

    private TablesController() {
        this.symbolTables = new HashMap<>();
        SymbolTable root = new SymbolTable(0,null);
        this.symbolTables.put(root.id, root);
        this.funcParams = new ArrayList<>();
        this.funcRParams = new ArrayList<>();
    }

    public static TablesController getInstance() {
        return controller;
    }

    public void enterNewScope(FuncSymbol funcSymbol) {
        SymbolTable symbolTable = new SymbolTable(curLevel, funcSymbol);
        curLevel = symbolTable.id;
        this.symbolTables.put(curLevel,symbolTable);
    }

    public void exitScope() {
        curLevel = this.symbolTables.get(curLevel).getFatherId();
    }

    public void addNewSymbol(Token token, Symbol symbol) {
        if (!handler.ignoreScope) {
            this.symbolTables.get(curLevel).put(token, symbol);
        }
    }

    public boolean isRedefined(Token token) {
        return this.symbolTables.get(curLevel).duplicate(token);
    }

    public boolean isEverExist(int level, Token token, Class<?> Class) {
        SymbolTable curTable = this.symbolTables.get(level);
        if (curTable.exist(token, Class)) {
            return true;
        } else {
            if (curTable.isRoot()) {
                return false;
            }
            return isEverExist(curTable.getFatherId(),token, Class);
        }
    }

    public int getScope(int level, Token token, Class<?> Class) {
        SymbolTable curTable = this.symbolTables.get(level);
        if (curTable.exist(token,Class)) {
            return curTable.id;
        } else {
            if (curTable.isRoot()) {
                return -1;
            }
            return getScope(curTable.getFatherId(),token, Class);
        }
    }

    public int checkFuncRParams(Token funcName) {
        for (SymbolTable symbolTable : this.symbolTables.values()) {
            if (symbolTable.exist(funcName, FuncSymbol.class)) {
                return symbolTable.checkFuncRParams(funcName,funcRParams);
            }
        }

        // 未定义函数 已报错
        return -1;
    }

    public FuncSymbol getFuncSym(int level) {
        SymbolTable curTable = this.symbolTables.get(level);
        if (curTable.funcSym != null) {
            return curTable.funcSym;
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
                    VarSymbol varSym = (VarSymbol)get(curLevel,curToken,VarSymbol.class);
                    if (varSym == null) {
                        return null;
                    }
                    funcParam = new FuncParam((varSym).getDim());
                    break;
                } else {
                    Token next = tokens.get(curPos + 1);
                    if (next.type == LexType.LPARENT) {
                        FuncSymbol funcSym = (FuncSymbol) get(curLevel,curToken,FuncSymbol.class);
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
                        int dimReduction = 0;
                        for (Token token : tokens) {
                            if (token.type == LexType.LBRACK) {
                                dimReduction++;
                            }
                        }
                        Symbol varSym = get(curLevel,curToken, VarSymbol.class);
                        if (varSym == null) {
                            return null;
                        }
                        funcParam = new FuncParam(((VarSymbol)varSym).getDim() - dimReduction);
                        break;
                    } else {
                        // 计算符号
                        VarSymbol varSym = (VarSymbol)get(curLevel,curToken, VarSymbol.class);
                        if (varSym == null) {
                            return null;
                        }
                        funcParam = new FuncParam(varSym.dim);
                        break;
                        //throw new RuntimeException("wrong format of Exp!");
                    }
                }
            } else {
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
        this.funcRParams.add(param);
    }

    public Symbol get(int level,Token sym, Class<?> Class) {
        SymbolTable curTable = this.symbolTables.get(level);
        if (curTable.exist(sym,Class)) {
            return curTable.getSymbol(sym);
        } else {
            if (curTable.isRoot()) {
                return null;
            }
            return get(curTable.getFatherId(),sym,Class);
        }
    }

    public void clear() {
        this.funcRParams.clear();
        this.isIgnoreTypeMatch = false;
    }
}
