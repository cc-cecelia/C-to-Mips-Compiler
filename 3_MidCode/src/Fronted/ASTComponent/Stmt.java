package Fronted.ASTComponent;

import Fronted.Lexer.Token;
import Fronted.Parser.ParseType;
import IO.Output;
import MidCode.IRModule;
import MidCode.Instructions.*;
import MidCode.Value.SymbolValue;
import MidCode.Value.Value;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Stmt implements AST {
    private int rule;
    private LVal lVal;
    private Token assign;
    private List<Exp> exps = new ArrayList<>();
    private Token semiCn;
    private Block block;
    private Token ifTK;
    private Token lParent;
    private Token rParent;
    private Token elseTK;
    private Cond cond;
    private List<Stmt> stmts = new ArrayList<>();
    private Stmt stmt;
    private Token forTK;
    private Token ctrl;
    private Token printTK;
    private Token returnTK;
    private Token getIntTK;
    private ForStmt[] forStmts = new ForStmt[2];
    private List<Token> commas = new ArrayList<>();
    private FormatString formatString;
    private ArrayList<Token> semiCns = new ArrayList<>();
    private Exp exp;


    public Stmt(LVal lVal, Token assign, Exp exp, Token semiCn) {
        this.lVal = lVal;
        this.assign = assign;
        this.exps.add(exp);
        this.semiCn = semiCn;
        rule = 1;
    }

    public Stmt(Exp exp, Token semiCn) {
        this.exp = exp;
        this.semiCn = semiCn;
        rule = 2;
    }

    public Stmt(Block block) {
        this.block = block;
        rule = 3;
    }

    public Stmt(Token ifTK, Token lParent, Token rParent, Token elseTK, Cond cond, List<Stmt> stmts) {
        this.ifTK = ifTK;
        this.lParent = lParent;
        this.rParent = rParent;
        this.elseTK = elseTK;
        this.cond = cond;
        this.stmts = stmts;
        rule = 4;
    }

    public Stmt(Token forTK, Token lParent, ForStmt[] forStmts, ArrayList<Token> semiCns, Cond cond, Token rParent, Stmt stmt) {
        this.forTK = forTK;
        this.lParent = lParent;
        this.forStmts = forStmts;
        this.semiCns = semiCns;
        this.cond = cond;
        this.rParent = rParent;
        this.stmt = stmt;
        rule = 5;
    }

    public Stmt(Token ctrl, Token semiCn) {
        this.semiCn = semiCn;
        this.ctrl = ctrl;
        rule = 6;
    }

    public Stmt(Token returnTK, Exp exp, Token semiCn) {
        this.exp = exp;
        this.semiCn = semiCn;
        this.returnTK = returnTK;
        rule = 7;
    }

    public Stmt(LVal lVal, Token assign, Token getIntTK, Token lParent, Token rParent, Token semiCn) {
        this.lVal = lVal;
        this.assign = assign;
        this.semiCn = semiCn;
        this.lParent = lParent;
        this.rParent = rParent;
        this.getIntTK = getIntTK;
        rule = 8;
    }

    public Stmt(Token printTK, Token lParent, FormatString formatString, List<Token> commas, List<Exp> exps, Token rParent, Token semiCn) {
        this.exps = exps;
        this.semiCn = semiCn;
        this.lParent = lParent;
        this.rParent = rParent;
        this.printTK = printTK;
        this.commas = commas;
        this.formatString = formatString;
        rule = 9;
    }

    public int getRule() {
        return rule;
    }

    public void print() throws IOException {
        switch (rule) {
            case 1: {
                lVal.print();
                assign.print();
                exps.get(0).print();
                semiCn.print();
                break;
            }
            case 2: {
                if (exp != null) {
                    exp.print();
                }
                semiCn.print();
                break;
            }
            case 3:
                block.print();
                break;
            case 4: {
                ifTK.print();
                lParent.print();
                cond.print();
                rParent.print();
                stmts.get(0).print();
                if (elseTK != null) {
                    elseTK.print();
                    stmts.get(1).print();
                }
                break;
            }
            case 5: {
                forTK.print();
                lParent.print();
                if (forStmts[0] != null) {
                    forStmts[0].print();
                }
                semiCns.get(0).print();
                if (cond != null) {
                    cond.print();
                }
                semiCns.get(1).print();
                if (forStmts[1] != null) {
                    forStmts[1].print();
                }
                rParent.print();
                stmt.print();
                break;
            }
            case 6: {
                ctrl.print();
                semiCn.print();
                break;
            }
            case 7: {
                returnTK.print();
                if (exp != null) {
                    exp.print();
                }
                semiCn.print();
                break;
            }
            case 8: {
                lVal.print();
                assign.print();
                getIntTK.print();
                lParent.print();
                rParent.print();
                semiCn.print();
                break;
            }
            case 9: {
                printTK.print();
                lParent.print();
                formatString.print();
                for (int i = 0; i < exps.size(); i++) {
                    commas.get(i).print();
                    exps.get(i).print();
                }
                rParent.print();
                semiCn.print();
                break;
            }
            default:
                System.out.println("wrong!");
                break;
        }
        Output.component(ParseType.Stmt);
    }

    // 哈哈 累死我算了
    @Override
    public void generateMidCode() {
        switch (rule) {
            case 1 -> {
                // LVal '=' Exp ';'
                lVal.generateMidCode();
                SymbolValue leftVal = this.lVal.val;
                Value expVal;
                exps.get(0).generateMidCode();
                expVal = exps.get(0).val;
                IRModule.addInstr(new MemCpy(leftVal.getName(), leftVal.getOffset(), expVal));
            }
            case 2 -> {
                // [Exp] ';'
                if (exp != null) {
                    exp.generateMidCode();
                }
            }
            case 3 -> {
                // Block
                block.generateMidCode();
            }
            case 4 -> {
                // 'if' '(' Cond ')' Stmt [ 'else' Stmt ]
                // TODO：条件语句
                cond.generateMidCode();
                stmts.get(0).generateMidCode();
                if (elseTK != null) {
                    stmts.get(1).generateMidCode();
                }
            }
            case 5 -> {
                //  'for' '(' [ForStmt] ';' [Cond] ';' [ForStmt] ')' Stmt
                // TODO：for循环语句

            }
            case 6 -> {
                //  'break' ';' | 'continue' ';'
                // TODO break continue
            }
            case 7 -> {
                //  'return' [Exp] ';'
                if (exp != null) {
                    exp.generateMidCode();
                    IRModule.addInstr(new Return(exp.val));
                } else {
                    IRModule.addInstr(new Return(null));
                }
            }
            case 8 -> {
                // LVal '=' 'getint''('')'';'
                lVal.generateMidCode();
                IRModule.addInstr(new Input(lVal.val));
            }
            case 9 -> {
                // 'printf''('FormatString{','Exp}')'';'
                Pattern pattern = Pattern.compile("%d");
                String str = formatString.getFormatString().context.split("\"")[1];
                Matcher matcher = pattern.matcher(str);
                int startIndex = 0;
                int i = 0;
                while (matcher.find()) {
                    int endIndex = matcher.start();
                    IRModule.addInstr(new PrintStr(str.substring(startIndex,endIndex)));
                    exps.get(i).generateMidCode();
                    IRModule.addInstr(new PrintInt(exps.get(i++).val));
                    startIndex = matcher.end();
                }
                if (startIndex < str.length()) {
                    IRModule.addInstr(new PrintStr(str.substring(startIndex)));
                }
            }
            default -> throw new IllegalStateException("不应该出现的情况！" + rule);
        }
    }
}
