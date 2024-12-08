package Fronted.Parser;

import Fronted.ASTComponent.Number;
import Fronted.ASTComponent.*;
import Fronted.Error.Err;
import Fronted.Error.ErrHandler;
import Fronted.Error.ErrType;
import Fronted.Lexer.LexType;
import Fronted.Lexer.Lexer;
import Fronted.Lexer.Token;
import Fronted.Parser.SymbleTable.Func.FuncParam;
import Fronted.Parser.SymbleTable.Func.SymFuncType;
import Fronted.Parser.SymbleTable.FuncSymbol;
import Fronted.Parser.SymbleTable.Symbol;
import Fronted.Parser.SymbleTable.TablesController;
import Fronted.Parser.SymbleTable.VarSymbol;

import java.io.IOException;
import java.util.ArrayList;

public class Parser {
    private static Parser parser = new Parser();
    public TablesController controller;
    Lexer lexer;
    private CompUnit compUnit;

    /* fUNC FLAG */
    private boolean isEnterFunc;


    private Parser() {
    }

    public static Parser getInstance() {
        return parser;
    }

    public void setLexer(Lexer lexer) {
        this.lexer = lexer;
    }

    public void setController(TablesController controller) {
        this.controller = controller;
    }

    public void error() {

    }

    public void match(LexType lexType) {
        if (lexer.now.type != lexType) {
            switch (lexType) {
                case SEMICN:
                    ErrHandler.add(new Err(ErrType.i, lexer.backTrack().lineNum));
                    lexer.fix(";");
                    break;
                case RPARENT:
                    ErrHandler.add(new Err(ErrType.j, lexer.backTrack().lineNum));
                    lexer.fix(")");
                    break;
                case RBRACK:
                    ErrHandler.add(new Err(ErrType.k, lexer.backTrack().lineNum));
                    lexer.fix("]");
                    break;
                case RBRACE:
                    lexer.fix("}");
                    break;
                default:
                    break;
                    //throw new RuntimeException("Invalid Fronted.Error At " + lexer.now.lineNum);
            }
        }
    }

    public CompUnit getCompUnit() {
        return compUnit;
    }

    public void parseProgram() {
        lexer.next();
        this.compUnit = CompUnit();
    }

    //CompUnit → {Decl} {FuncDef} MainFuncDef
    public CompUnit CompUnit() {
        ArrayList<Decl> decls = new ArrayList<>();
        ArrayList<FuncDef> funcDefs = new ArrayList<>();
        MainFuncDef mainFuncDef = null;


        while (lexer.now != null) {
            if (Judge.match(lexer.now, LexType.CONSTTK)) {
                decls.add(Decl());
            } else if (Judge.match(lexer.now, LexType.VOIDTK)) {
                funcDefs.add(FuncDef());
            } else { // now == int
                Token preRead = lexer.preRead();
                if (Judge.match(preRead, LexType.MAINTK)) {
                    mainFuncDef = MainFuncDef();
                } else if (Judge.match(preRead, LexType.IDENFR)) {
                    preRead = lexer.preRead(preRead);
                    if (Judge.match(preRead, LexType.ASSIGN) ||
                            Judge.match(preRead, LexType.SEMICN) ||
                            Judge.match(preRead, LexType.LBRACK) ||
                            Judge.match(preRead, LexType.COMMA)) {
                        decls.add(Decl());
                    } else if (Judge.match(preRead, LexType.LPARENT)) {
                        funcDefs.add(FuncDef());
                    } else {
                        error();
                    }
                } else {
                    error();
                }
            }
        }
        return new CompUnit(decls, funcDefs, mainFuncDef);
    }

    //  Decl → ConstDecl | VarDecl
    public Decl Decl() {
        ConstDecl constDecl;
        VarDecl varDecl;

        // Decl → ConstDecl
        if (Judge.match(lexer.now, LexType.CONSTTK)) {
            constDecl = ConstDecl();
            return new Decl(constDecl);
        }
        //  Decl → VarDecl
        else if (Judge.match(lexer.now, LexType.INTTK)) {
            varDecl = VarDecl();
            return new Decl(varDecl);
        }
        lexer.next();
        return null;
    }

    // ConstDecl → 'const' BType ConstDef { ',' ConstDef } ';'
    public ConstDecl ConstDecl() {
        Token constTK;
        BType bType;
        ConstDef constDef;
        ArrayList<ConstDef> constDefs = new ArrayList<>();
        ArrayList<Token> commas = new ArrayList<>();
        Token semiCon;

        match(LexType.CONSTTK);
        constTK = lexer.now;
        lexer.next();
        match(LexType.INTTK);
        bType = BType();
        constDef = ConstDef();
        // now == , || ;
        while (Judge.match(lexer.now, LexType.COMMA)) {
            commas.add(lexer.now);
            lexer.next();
            constDefs.add(ConstDef());
        }
        match(LexType.SEMICN); // i
        semiCon = lexer.now;
        lexer.next();
        return new ConstDecl(constTK, bType, constDef, commas, constDefs, semiCon);
    }

    public BType BType() {
        BType BType = new BType(lexer.now);
        lexer.next();
        return BType;
    }

    // ConstDef → Ident { '[' ConstExp ']' } '=' ConstInitVal
    public ConstDef ConstDef() {
        Token ident;
        ArrayList<Token> lBracks = new ArrayList<>();
        ArrayList<ConstExp> constExps = new ArrayList<>();
        ArrayList<Token> rBracks = new ArrayList<>();
        Token assign;
        ConstInitVal constInitVal;



        match(LexType.IDENFR);
        ident = lexer.now;

        VarSymbol tmp = new VarSymbol(ident,true,-1);
        if (ErrHandler.isRedefined(tmp)) {
            ErrHandler.add(new Err(ErrType.b, ident.lineNum));
        }
        lexer.next();
        // now = [ | =
        while (Judge.match(lexer.now, LexType.LBRACK)) {
            lBracks.add(lexer.now);
            lexer.next();
            constExps.add(ConstExp());
            match(LexType.RBRACK); //k
            rBracks.add(lexer.now);
            lexer.next();
        }

        Symbol symbol = new VarSymbol(ident, true, lBracks.size());
        controller.addNewSymbol(ident, symbol);

        match(LexType.ASSIGN);
        assign = lexer.now;
        lexer.next();
        constInitVal = ConstInitVal();
        return new ConstDef(ident, constExps, lBracks, rBracks, assign, constInitVal);
    }

    //ConstInitVal → ConstExp
    //             → '{' [ ConstInitVal { ',' ConstInitVal } ] '}'
    public ConstInitVal ConstInitVal() {
        ConstExp constExp;
        Token lBrace;
        Token rBrace;
        ArrayList<ConstInitVal> constInitVals = new ArrayList<>();
        ArrayList<Token> commas = new ArrayList<>();
        ConstInitVal constInitVal;

        if (Judge.match(lexer.now, LexType.LBRACE)) {
            lBrace = lexer.now;
            lexer.next();
            // now = constInit | }
            if (Judge.match(lexer.now, LexType.RBRACE)) {
                rBrace = lexer.now;
            } else {
                constInitVals.add(ConstInitVal());
                // now = , | }
                while (Judge.match(lexer.now, LexType.COMMA)) {
                    commas.add(lexer.now);
                    lexer.next();
                    constInitVals.add(ConstInitVal());
                }
                match(LexType.RBRACE);
                rBrace = lexer.now;
            }
            lexer.next();
            constInitVal = new ConstInitVal(lBrace, rBrace, commas, constInitVals);
        } else if (Judge.isExp(lexer.now)) {
            constExp = ConstExp();
            constInitVal = new ConstInitVal(constExp);
        } else {
            error();
            constInitVal = null;
        }
        return constInitVal;
    }

    // VarDecl → BType VarDef { ',' VarDef } ';'
    public VarDecl VarDecl() {
        VarDecl varDecl;
        BType bType;
        ArrayList<VarDef> varDefs = new ArrayList<>();
        ArrayList<Token> commas = new ArrayList<>();
        Token semiCn;

        match(LexType.INTTK);
        bType = BType();
        varDefs.add(VarDef());
        // now = , | ;
        while (Judge.match(lexer.now, LexType.COMMA)) {
            commas.add(lexer.now);
            lexer.next();
            varDefs.add(VarDef());
        }
        match(LexType.SEMICN); // i
        semiCn = lexer.now;
        varDecl = new VarDecl(bType, varDefs, commas, semiCn);

        lexer.next();
        return varDecl;
    }

    // VarDef → Ident { '[' ConstExp ']' } // 包含普通变量、一维数组、二维数组定义
    //        → Ident { '[' ConstExp ']' } '=' InitVal
    public VarDef VarDef() {
        VarDef varDef = null;
        Token ident;
        ArrayList<Token> lBracks = new ArrayList<>();
        ArrayList<ConstExp> constExps = new ArrayList<>();
        ArrayList<Token> rBracks = new ArrayList<>();
        Token assign;
        InitVal initVal;

        match(LexType.IDENFR);
        ident = lexer.now;
        VarSymbol tmp = new VarSymbol(ident,false,-1);
        boolean flag = ErrHandler.isRedefined(tmp);
        if (flag) {
            ErrHandler.add(new Err(ErrType.b, ident.lineNum));
        }
        // now = ident
        Token preRead = lexer.preRead();
        if (Judge.match(preRead, LexType.COMMA) || Judge.match(preRead, LexType.SEMICN)) {
            // VarDef -> Ident
            varDef = new VarDef(ident, lBracks, rBracks, constExps);
            lexer.next();
        } else if (Judge.match(preRead, LexType.ASSIGN)) {
            // VarDef -> ident = initVal
            lexer.next();
            // now = =
            assign = lexer.now;
            lexer.next();
            initVal = InitVal();
            varDef = new VarDef(ident, lBracks, rBracks, assign, constExps, initVal);
        } else if (Judge.match(preRead, LexType.LBRACK)) {
            // VarDef -> ident [ ConstExp ]
            // preRead = [  now = ident
            while (Judge.match(preRead, LexType.LBRACK)) {
                lexer.next();
                // now = [
                lBracks.add(lexer.now);
                lexer.next();
                constExps.add(ConstExp());
                match(LexType.RBRACK); // k
                rBracks.add(lexer.now);
                preRead = lexer.preRead();
            }
            // now = ]

            if (Judge.match(preRead,LexType.ASSIGN)) {
                lexer.next();
                assign = lexer.now;
                lexer.next();
                initVal = InitVal();
                varDef = new VarDef(ident, lBracks, rBracks, assign, constExps, initVal);
            } else {
                varDef = new VarDef(ident, lBracks, rBracks, constExps);
                lexer.next();
            }
//            if (Judge.match(preRead, LexType.COMMA) || Judge.match(preRead, LexType.SEMICN)) {
//                varDef = new VarDef(ident, lBracks, rBracks, constExps);
//                lexer.next();
//            } else if (Judge.match(preRead, LexType.ASSIGN)) {
//                lexer.next();
//                assign = lexer.now;
//                lexer.next();
//                initVal = InitVal();
//                varDef = new VarDef(ident, lBracks, rBracks, assign, constExps, initVal);
//            } else {
//                //match(LexType.SEMICN);
//                lexer.next();
//                error();
//                //varDef = null;
//            }
        } else {
            varDef = new VarDef(ident,lBracks,rBracks,constExps);
            lexer.next();
            error();
        }

        if (!flag) {
            Symbol symbol = new VarSymbol(ident, false, lBracks.size());
            controller.addNewSymbol(ident, symbol);
        }

        return varDef;
    }

    //InitVal → Exp
    //        → '{' [ InitVal { ',' InitVal } ] '}'
    public InitVal InitVal() {
        InitVal initVal;
        Exp exp;
        Token lBrace;
        InitVal _initVal;
        ArrayList<Token> commas = new ArrayList<>();
        ArrayList<InitVal> _initVals = new ArrayList<>();
        Token rBrace;

        if (Judge.isExp(lexer.now)) {
            // InitVal → Exp
            exp = Exp();
            initVal = new InitVal(exp);
        } else if (Judge.match(lexer.now, LexType.LBRACE)) {
            // InitVal → '{' [ InitVal { ',' InitVal } ] '}'
            lBrace = lexer.now;
            lexer.next();
            // now = initVal | }
            if (Judge.match(lexer.now, LexType.RBRACE)) {
                // InitVal -> {}
                _initVal = null;
                rBrace = lexer.now;
            } else {
                _initVal = InitVal();
                while (Judge.match(lexer.now, LexType.COMMA)) {
                    commas.add(lexer.now);
                    lexer.next();
                    _initVals.add(InitVal());
                }
                match(LexType.RBRACE);
                rBrace = lexer.now;
            }
            initVal = new InitVal(lBrace, _initVal, commas, _initVals, rBrace);
            lexer.next();
        } else {
            error();
            initVal = null;
        }
        return initVal;
    }

    //FuncDef → FuncType Ident '(' [FuncFParams] ')' Block
    public FuncDef FuncDef() {
        FuncDef funcDef;
        FuncType funcType;
        Token ident;
        Token lParent;
        FuncFParams funcFParams;
        Token rParent;
        Block block;

        FuncSymbol funcSymbol = null;

        SymFuncType type;
        if (lexer.now.type == LexType.VOIDTK) {
            type = SymFuncType.VOID;
        } else {
            type = SymFuncType.INT;
        }

        funcType = FuncType();
        match(LexType.IDENFR);
        ident = lexer.now;

        funcSymbol = new FuncSymbol(ident, type);

        if (ErrHandler.isRedefined(funcSymbol)) {
            ErrHandler.add(new Err(ErrType.b, ident.lineNum));
            ErrHandler.ignoreScope = true;
        } else {
            this.controller.addNewSymbol(ident, funcSymbol);
        }

        // 进入下一作用域
        if (!ErrHandler.ignoreScope) {
            controller.enterNewScope(funcSymbol, funcSymbol);
            isEnterFunc = true;
        }

        lexer.next();
        match(LexType.LPARENT);
        lParent = lexer.now;
        lexer.next();
        if (Judge.match(lexer.now, LexType.RPARENT)) {
            funcFParams = null;
            rParent = lexer.now;
        } else if (Judge.match(lexer.now, LexType.INTTK)) {
            funcFParams = FuncFParams();
            match(LexType.RPARENT); // j
            rParent = lexer.now;
        } else {
            match(LexType.RPARENT);
            funcFParams = null;
            rParent = lexer.now;
        }

        if (funcSymbol!= null) {
            funcSymbol.setFuncParams(controller.funcParams);
        }

        lexer.next();
        block = Block();

        if (funcSymbol!= null) {
            if (funcSymbol.getFuncType() == SymFuncType.INT && !block.isLastRet()) {
                ErrHandler.add(new Err(ErrType.g, block.getRBrace().lineNum));
            }
        }

        // 退出当前作用域
        if (!ErrHandler.ignoreScope) {
            controller.exitScope();
        }
        ErrHandler.ignoreScope = false;
        funcDef = new FuncDef(funcType, ident, lParent, funcFParams, rParent, block);
        return funcDef;
    }

    //MainFuncDef → 'int' 'main' '(' ')' Block
    public MainFuncDef MainFuncDef() {
        MainFuncDef mainFuncDef;
        Token intTK, mainTK, lParent, rParent;
        Block block;

        match(LexType.INTTK);
        intTK = lexer.now;
        lexer.next();
        match(LexType.MAINTK);
        mainTK = lexer.now;
        lexer.next();
        match(LexType.LPARENT);
        lParent = lexer.now;
        lexer.next();
        match(LexType.RPARENT);
        rParent = lexer.now;
        lexer.next();
        FuncSymbol funcSymbol = new FuncSymbol(mainTK, SymFuncType.INT);
        this.controller.addNewSymbol(mainTK, funcSymbol);
        controller.enterNewScope(funcSymbol,funcSymbol);
        isEnterFunc = true;

        block = Block();
        if (!block.isLastRet()) {
            ErrHandler.add(new Err(ErrType.g, block.getRBrace().lineNum));
        }

        controller.exitScope();
        ErrHandler.ignoreScope = false;
        mainFuncDef = new MainFuncDef(intTK, mainTK, lParent, rParent, block);
        return mainFuncDef;
    }

    public FuncType FuncType() {
        Token funcType;
        if (Judge.match(lexer.now, LexType.VOIDTK) || Judge.match(lexer.now, LexType.INTTK)) {
            funcType = lexer.now;
        } else {
            error();
            funcType = null;
        }
        lexer.next();
        return new FuncType(funcType);
    }

    // FuncFParams → FuncFParam { ',' FuncFParam }
    public FuncFParams FuncFParams() {
        FuncFParams funcFParams;
        FuncFParam _funcFParam;
        ArrayList<FuncFParam> _funcFParams = new ArrayList<>();
        ArrayList<Token> commas = new ArrayList<>();

        _funcFParam = FuncFParam();
        if (Judge.match(lexer.now, LexType.RPARENT)) {
            funcFParams = new FuncFParams(_funcFParam, _funcFParams, commas);
        } else if (Judge.match(lexer.now, LexType.COMMA)) {
            while (Judge.match(lexer.now, LexType.COMMA)) {
                commas.add(lexer.now);
                lexer.next();
                _funcFParams.add(FuncFParam());
            }
            funcFParams = new FuncFParams(_funcFParam, _funcFParams, commas);
        } else {
            error();
            funcFParams = null;
        }

        return funcFParams;
    }

    //  FuncFParam → BType Ident ['[' ']' { '[' ConstExp ']' }]
    public FuncFParam FuncFParam() {
        FuncFParam funcFParam;
        Token ident;
        BType bType;
        Token lBrack, rBrack;
        ArrayList<Token> lBracks = new ArrayList<>();
        ArrayList<ConstExp> constExps = new ArrayList<>();
        ArrayList<Token> rBracks = new ArrayList<>();

        FuncParam symbolParam;

        match(LexType.INTTK);
        bType = BType();
        match(LexType.IDENFR);
        ident = lexer.now;
        Token preRead = lexer.preRead();
        if (Judge.match(preRead, LexType.LBRACK)) {
            lexer.next();
            lBrack = lexer.now;
            lexer.next();
            match(LexType.RBRACK);
            rBrack = lexer.now;
            preRead = lexer.preRead();
            while (Judge.match(preRead, LexType.LBRACK)) {
                lexer.next();
                lBracks.add(lexer.now);
                lexer.next();
                constExps.add(ConstExp());
                match(LexType.RBRACK);
                rBracks.add(lexer.now);
                preRead = lexer.preRead();
            }
            funcFParam = new FuncFParam(bType, ident, lBrack, rBrack, lBracks, rBracks, constExps);
            symbolParam = new FuncParam(funcFParam.getDim());
        } else {
            funcFParam = new FuncFParam(bType, ident, null, null, lBracks, rBracks, constExps);
            symbolParam = new FuncParam(0);
        }

        VarSymbol tmp = new VarSymbol(ident,false, funcFParam.getDim());
        if (ErrHandler.isRedefined(tmp)) {
            ErrHandler.add(new Err(ErrType.b, ident.lineNum));
        } else {
            controller.addNewSymbol(ident, new VarSymbol(ident, false, funcFParam.getDim()));
        }

        controller.addParam(symbolParam);
        lexer.next();

        return funcFParam;
    }

    //  Block → '{' { BlockItem } '}'
    public Block Block() {
        Block block;
        Token lBrace, rBrace;
        ArrayList<BlockItem> blockItems = new ArrayList<>();

        match(LexType.LBRACE);
        lBrace = lexer.now;
        lexer.next();

        // 进入新的作用域
        if (!isEnterFunc && !ErrHandler.ignoreScope) {
            controller.enterNewScope(controller.getFuncSym(controller.curLevel),null);
        } else {
            // 如果不是第一个函数的block，那就说明有新的block了
            isEnterFunc = false; //
        }

        while (Judge.isBlockItem(lexer.now)) {
            blockItems.add(BlockItem());
        }
        match(LexType.RBRACE);
        rBrace = lexer.now;
        block = new Block(lBrace, rBrace, blockItems);
        lexer.next();

        // 退出作用域
        if (!controller.isExitFunc() && !ErrHandler.ignoreScope) {
            controller.exitScope();
        }
//
//        if (!isExitFunc && !errHandler.ignoreScope) {
//            controller.exitScope();
//        }

        return block;
    }

    // BlockItem → Decl | Stmt
    public BlockItem BlockItem() {
        BlockItem blockItem;
        Decl decl;
        Stmt stmt;

        if (Judge.isDecl(lexer.now)) {
            decl = Decl();
            blockItem = new BlockItem(decl);
        } else if (Judge.isStmt(lexer.now)) {
            stmt = Stmt();
            blockItem = new BlockItem(stmt);
        } else {
            error();
            blockItem = null;
        }

        return blockItem;
    }

    public Stmt Stmt() {
        Stmt stmt;
        switch (lexer.now.type) {
            case LBRACE:
                stmt = _Block();
                break;
            case IFTK:
                stmt = _If();
                break;
            case FORTK:
                stmt = _For();
                break;
            case BREAKTK, CONTINUETK:
                stmt = _CtrlFlow();
                break;
            case RETURNTK:
                stmt = _Return();
                break;
            case PRINTFTK:
                stmt = _Printf();
                break;
            default: {
                int judge = lexer.judge();
                switch (judge) {
                    case 0:
                        stmt = _Exp();
                        break;
                    case 1:
                        stmt = _LVal1();
                        break;
                    case 2:
                        stmt = _LVal2();
                        break;
                    default:
                        stmt = null;
                }
            }
        }
        return stmt;
    }

    public Stmt _Block() {
        Block block = Block();
        return new Stmt(block);
    }

    // 'if' '(' Cond ')' Stmt [ 'else' Stmt ] // j
    public Stmt _If() {
        Token ifTK, lParent, rParent, elseTK;
        Cond cond;
        ArrayList<Stmt> stmts = new ArrayList<>();

        ifTK = lexer.now;
        lexer.next();
        match(LexType.LPARENT);
        lParent = lexer.now;
        lexer.next();
        cond = Cond();
        match(LexType.RPARENT);
        rParent = lexer.now;
        lexer.next();
        stmts.add(Stmt());
        if (Judge.match(lexer.now, LexType.ELSETK)) {
            elseTK = lexer.now;
            lexer.next();
            stmts.add(Stmt());
        } else {
            elseTK = null;
        }
        return new Stmt(ifTK, lParent, rParent, elseTK, cond, stmts);
    }

    //  'for' '(' [ForStmt] ';' [Cond] ';' [ForStmt] ')' Stmt
    public Stmt _For() {
        Token forTK, lParent, rParent;
        ArrayList<Token> semiCns = new ArrayList<>();
        ForStmt[] forStmts = new ForStmt[2];
        Cond cond = null;
        Stmt stmt;

        //isInLoop = true;
        forTK = lexer.now;
        lexer.next();
        match(LexType.LPARENT);
        lParent = lexer.now;
        lexer.next();
        if (Judge.match(lexer.now, LexType.SEMICN)) {
            forStmts[0] = null;
            cond = _forBranch1(semiCns, forStmts);
        } else {
            forStmts[0] = ForStmt();
            match(LexType.SEMICN);
            cond = _forBranch1(semiCns, forStmts);
        }
        match(LexType.RPARENT);
        rParent = lexer.now;
        lexer.next();
        controller.enterNewLoop();
        stmt = Stmt();
        controller.exitLoop();
        //isInLoop = false;
        return new Stmt(forTK, lParent, forStmts, semiCns, cond, rParent, stmt);
    }

    private Cond _forBranch1(ArrayList<Token> semiCns, ForStmt[] forStmts) {
        Cond cond;
        semiCns.add(lexer.now);
        lexer.next();
        if (Judge.match(lexer.now, LexType.SEMICN)) {
            cond = null;
            _forBranch2(semiCns, forStmts);
        } else {
            cond = Cond();
            // now = ;
            match(LexType.SEMICN);
            _forBranch2(semiCns, forStmts);
        }
        return cond;
    }

    private void _forBranch2(ArrayList<Token> semiCns, ForStmt[] forStmts) {
        semiCns.add(lexer.now);
        lexer.next();
        if (Judge.match(lexer.now, LexType.RPARENT)) {
            forStmts[1] = null;
        } else {
            forStmts[1] = ForStmt();
        }
    }

    private Stmt _CtrlFlow() {
        Token ctrl;
        Token semiCn;

        ctrl = lexer.now;
        lexer.next();
        match(LexType.SEMICN);
        semiCn = lexer.now;
        lexer.next();

        if (!controller.isInLoop()) {
            ErrHandler.add(new Err(ErrType.m, ctrl.lineNum));
        }
//        if (!isInLoop) {
//            errHandler.add(new Err(ErrType.m, ctrl.lineNum));
//        }
        return new Stmt(ctrl, semiCn);
    }

    private Stmt _Return() {
        Token returnTK, semiCn;
        Exp exp;
        returnTK = lexer.now;
        lexer.next();
        if (Judge.match(lexer.now, LexType.SEMICN)) {
            exp = null;
            semiCn = lexer.now;
        } else if (Judge.isExp(lexer.now)) {
            exp = Exp();
            match(LexType.SEMICN);
            semiCn = lexer.now;
        } else {
            error();
            exp = null;
            semiCn = null;
        }
        lexer.next();


        if (ErrHandler.checkVoidReturn(exp == null)) {
            ErrHandler.add(new Err(ErrType.f, returnTK.lineNum));
        }

        return new Stmt(returnTK, exp, semiCn);
    }

    // 'printf''('FormatString{','Exp}')'';'
    public Stmt _Printf() {
        Token printf, lParent, rParent, semiCn;
        FormatString formatString;
        ArrayList<Exp> exps = new ArrayList<>();
        ArrayList<Token> commas = new ArrayList<>();

        printf = lexer.now;
        lexer.next();
        match(LexType.LPARENT);
        lParent = lexer.now;
        lexer.next();
        match(LexType.STRCON);
        formatString = FormatString();
        // now = , | )
        if (Judge.match(lexer.now, LexType.RPARENT)) {
            rParent = lexer.now;
        } else if (Judge.match(lexer.now, LexType.COMMA)) {
            while (Judge.match(lexer.now, LexType.COMMA)) {
                commas.add(lexer.now);
                lexer.next();
                exps.add(Exp());
            }
            match(LexType.RPARENT);
            rParent = lexer.now;
        } else {
            error();
            rParent = null;
            semiCn = null;
        }
        lexer.next();
        match(LexType.SEMICN);
        semiCn = lexer.now;
        lexer.next();
        if (!ErrHandler.checkTypeL(formatString.getFormatString(), exps.size())) {
            ErrHandler.add(new Err(ErrType.l, printf.lineNum));
        }
        return new Stmt(printf, lParent, formatString, commas, exps, rParent, semiCn);
    }

    public FormatString FormatString() {
        Token strToken = lexer.now;
        FormatString formatString = new FormatString(strToken);
        /* ERROR JUDGE BEGIN*/
        if (ErrHandler.checkTypeA(strToken)) {
            ErrHandler.add(new Err(ErrType.a, strToken.lineNum));
        }
        /* ERROR JUDGE END */
        lexer.next();
        return formatString;
    }

    public Stmt _Exp() {
        Exp exp;
        Token semiCn;
        if (Judge.match(lexer.now, LexType.SEMICN)) {
            exp = null;
            semiCn = lexer.now;
        } else if (Judge.isExp(lexer.now)) {
            exp = Exp();
            match(LexType.SEMICN);
            semiCn = lexer.now;
        } else {
            match(LexType.SEMICN);
            exp = null;
            semiCn = lexer.now;
            //semiCn = null;
        }
        lexer.next();
        return new Stmt(exp, semiCn);
    }

    // <Stmt>→<LVal>‘=’ <Exp>‘;’
    public Stmt _LVal1() {
        LVal lVal;
        Token assign, semiCn;
        Exp exp;

        lVal = LVal();

        if (ErrHandler.constReassign(lVal.getIdent())) {
            ErrHandler.add(new Err(ErrType.h, lVal.getIdent().lineNum));
        }

        match(LexType.ASSIGN);
        assign = lexer.now;
        lexer.next();
        exp = Exp();
        match(LexType.SEMICN);
        semiCn = lexer.now;
        lexer.next();

        return new Stmt(lVal, assign, exp, semiCn);
    }

    public Stmt _LVal2() {
        LVal lVal;
        Token assign, getInt, lParent, rParent, semiCn;

        lVal = LVal();

        if (ErrHandler.constReassign(lVal.getIdent())) {
            ErrHandler.add(new Err(ErrType.h, lVal.getIdent().lineNum));
        }
        match(LexType.ASSIGN);
        assign = lexer.now;
        lexer.next();
        match(LexType.GETINTTK);
        getInt = lexer.now;
        lexer.next();
        match(LexType.LPARENT);
        lParent = lexer.now;
        lexer.next();
        match(LexType.RPARENT);
        rParent = lexer.now;
        lexer.next();
        match(LexType.SEMICN);
        semiCn = lexer.now;
        lexer.next();

        return new Stmt(lVal, assign, getInt, lParent, rParent, semiCn);
    }

    // ForStmt → LVal '=' Exp
    public ForStmt ForStmt() {
        LVal lVal;
        Token assign;
        Exp exp;

        lVal = LVal();
        match(LexType.ASSIGN);
        assign = lexer.now;
        lexer.next();
        exp = Exp();

        return new ForStmt(lVal, assign, exp);
    }

    public Exp Exp() {
        AddExp addExp;

        addExp = AddExp();
        return new Exp(addExp);
    }

    public Cond Cond() {
        LOrExp lOrExp;

        lOrExp = LOrExp();

        return new Cond(lOrExp);
    }

    // LVal → Ident {'[' Exp ']'}
    public LVal LVal() {
        Token ident;
        ArrayList<Token> lBracks = new ArrayList<>();
        ArrayList<Exp> exps = new ArrayList<>();
        ArrayList<Token> rBracks = new ArrayList<>();

        match(LexType.IDENFR);
        ident = lexer.now;

        if (!ErrHandler.isEverExist(controller.curLevel, ident, VarSymbol.class)) {
            ErrHandler.add(new Err(ErrType.c, ident.lineNum));
        }

        lexer.next();
        if (Judge.match(lexer.now, LexType.LBRACK)) {
            while (Judge.match(lexer.now, LexType.LBRACK)) {
                lBracks.add(lexer.now);
                lexer.next();
                exps.add(Exp());
                match(LexType.RBRACK);
                rBracks.add(lexer.now);
                lexer.next();
            }
        }
        return new LVal(ident, lBracks, rBracks, exps);
    }

    // PrimaryExp → '(' Exp ')' | LVal | Number
    public PrimaryExp PrimaryExp() {
        PrimaryExp primaryExp;
        Token lParent, rParent;
        Exp exp;
        LVal lVal;
        Number number;

        switch (lexer.now.type) {
            case LPARENT: {
                lParent = lexer.now;
                lexer.next();
                exp = Exp();
                match(LexType.RPARENT);
                rParent = lexer.now;
                lexer.next();
                primaryExp = new PrimaryExp(lParent, exp, rParent);
                break;
            }
            case IDENFR: {
                lVal = LVal();
                primaryExp = new PrimaryExp(lVal);
                break;
            }
            case INTCON: {
                number = Number();
                primaryExp = new PrimaryExp(number);
                break;
            }
            default: {
                error();
                primaryExp = null;
                break;
            }
        }
        return primaryExp;
    }

    public Number Number() {
        Token intConst;
        match(LexType.INTCON);
        intConst = lexer.now;
        lexer.next();
        return new Number(intConst);
    }

    // UnaryExp → PrimaryExp |
    //          → Ident '(' [FuncRParams] ')'
    //          → UnaryOp UnaryExp
    public UnaryExp UnaryExp() {
        UnaryExp unaryExp;
        PrimaryExp primaryExp;
        Token ident, lParent, rParent;
        FuncRParams funcRParams;
        UnaryOp unaryOp;
        UnaryExp _unaryExp;

        if (Judge.isUnaryOp(lexer.now)) {
            unaryOp = UnaryOp();
            _unaryExp = UnaryExp();
            unaryExp = new UnaryExp(unaryOp, _unaryExp);
        } else if (Judge.match(lexer.now, LexType.INTCON) || Judge.match(lexer.now, LexType.LPARENT)) {
            primaryExp = PrimaryExp();
            unaryExp = new UnaryExp(primaryExp);
        } else if (Judge.match(lexer.now, LexType.IDENFR)) {
            Token preRead = lexer.preRead();
            if (Judge.match(preRead, LexType.LPARENT)) {
                controller.examNewFunc();
                ident = lexer.now;
                if (!ErrHandler.isEverExist(controller.curLevel, ident, FuncSymbol.class)) {
                    ErrHandler.add(new Err(ErrType.c, ident.lineNum));
                }
                lexer.next();
                match(LexType.LPARENT);
                lParent = lexer.now;
                lexer.next();
                ArrayList<FuncParam> params = new ArrayList<>();
                if (Judge.isExp(lexer.now)) {
                    funcRParams = FuncRParams();
                    match(LexType.RPARENT);
                    rParent = lexer.now;
                } else if (Judge.match(lexer.now, LexType.RPARENT)) {
                    funcRParams = null;
                    rParent = lexer.now;
                } else {
                    match(LexType.RPARENT);
                    funcRParams = null;
                    rParent = lexer.now;
                }
                /* ERROR TYPE D:CNT E:TYPE */
                switch (ErrHandler.checkFuncRParams(ident)) {
                    case 0 -> ErrHandler.add(new Err(ErrType.d, ident.lineNum));
                    case 1 -> ErrHandler.add(new Err(ErrType.e, ident.lineNum));
                }
                //controller.clear();
                controller.examEnd();
                lexer.next();
                unaryExp = new UnaryExp(ident, lParent, funcRParams, rParent);
            } else {
                primaryExp = PrimaryExp();
                unaryExp = new UnaryExp(primaryExp);
            }
        } else {
            error();
            unaryExp = null;
        }

        return unaryExp;
    }

    public UnaryOp UnaryOp() {
        Token op;
        if (Judge.match(lexer.now, LexType.PLUS) ||
                Judge.match(lexer.now, LexType.MINU) ||
                Judge.match(lexer.now, LexType.NOT)) {
            op = lexer.now;
        } else {
            error();
            op = null;
        }
        lexer.next();
        return new UnaryOp(op);
    }

    // FuncRParams → Exp { ',' Exp }
    public FuncRParams FuncRParams() {
        Exp exp;
        ArrayList<Token> commas = new ArrayList<>();
        ArrayList<Exp> exps = new ArrayList<>();

        exp = Exp();
        controller.addRParams(exp);
        if (Judge.match(lexer.now, LexType.COMMA)) {
            while (Judge.match(lexer.now, LexType.COMMA)) {
                commas.add(lexer.now);
                lexer.next();
                Exp _exp = Exp();
                exps.add(_exp);
                controller.addRParams(_exp);
            }
        }

        return new FuncRParams(exp, exps, commas);
    }

    // MulExp -> UnaryExp [('*' | '/' | '-') MulExp]
    public MulExp MulExp() {
        UnaryExp unaryExp;
        Token op;
        MulExp mulExp;

        unaryExp = UnaryExp();
        if (Judge.isTerm(lexer.now)) {
            op = lexer.now;
            lexer.next();
            mulExp = MulExp();
        } else {
            op = null;
            mulExp = null;
        }

        return new MulExp(unaryExp, op, mulExp);
    }

    // AddExp -> MulExp [('+' | '-') AddExp]
    public AddExp AddExp() {
        MulExp mulExp;
        Token op;
        AddExp addExp;

        mulExp = MulExp();
        if (Judge.isExpr(lexer.now)) {
            op = lexer.now;
            lexer.next();
            addExp = AddExp();
        } else {
            op = null;
            addExp = null;
        }
        return new AddExp(mulExp, op, addExp);
    }

    // RelExp -> addExp [('+' | '-') RelExp]
    public RelExp RelExp() {
        AddExp addExp;
        Token op;
        RelExp relExp;

        addExp = AddExp();
        if (Judge.isCompare(lexer.now)) {
            op = lexer.now;
            lexer.next();
            relExp = RelExp();
        } else {
            op = null;
            relExp = null;
        }

        return new RelExp(relExp, op, addExp);
    }

    // EqExp -> RelExp [('==' | '!=') EqExp]
    public EqExp EqExp() {
        RelExp relExp;
        Token op;
        EqExp eqExp;

        relExp = RelExp();
        if (Judge.isEqTK(lexer.now)) {
            op = lexer.now;
            lexer.next();
            eqExp = EqExp();
        } else {
            op = null;
            eqExp = null;
        }
        return new EqExp(relExp, op, eqExp);
    }

    // LAndExp -> EqExp [('==' | '!=') LAndExp]
    public LAndExp LAndExp() {
        EqExp eqExp;
        Token op;
        LAndExp lAndExp;

        eqExp = EqExp();
        if (Judge.match(lexer.now, LexType.AND)) {
            op = lexer.now;
            lexer.next();
            lAndExp = LAndExp();
        } else {
            op = null;
            lAndExp = null;
        }
        return new LAndExp(eqExp, op, lAndExp);
    }

    // LOrExp -> LAndExp [('||') LOrExp]
    public LOrExp LOrExp() {
        LAndExp lAndExp;
        Token op;
        LOrExp lOrExp;

        lAndExp = LAndExp();
        if (Judge.match(lexer.now, LexType.OR)) {
            op = lexer.now;
            lexer.next();
            lOrExp = LOrExp();
        } else {
            op = null;
            lOrExp = null;
        }
        return new LOrExp(lAndExp, op, lOrExp);
    }

    public ConstExp ConstExp() {
        AddExp addExp;
        addExp = AddExp();
        return new ConstExp(addExp);
    }

    public void print() throws IOException {
        compUnit.print();
    }

}
