package Fronted.ASTComponent;

import Fronted.Lexer.Token;
import IO.Output;
import Fronted.Parser.ParseType;
import MidCode.IRModule;
import MidCode.Instructions.ValAssign;
import MidCode.MidGenerator;
import MidCode.Table.MidController;
import MidCode.Table.MidType;
import MidCode.Table.SymbolType;
import MidCode.Table.VarSymbol;
import MidCode.Value.IntegerValue.IntegerValue;
import MidCode.Value.SymbolValue;
import MidCode.Value.Value;

import java.io.IOException;
import java.util.ArrayList;

public class PrimaryExp implements AST {
    private Token lParent;
    private Exp exp;
    private Token rParent;
    private LVal lVal;
    private Number number;
    private int rule;

    public PrimaryExp(Token lParent, Exp exp, Token rParent) {
        this.lParent = lParent;
        this.exp = exp;
        this.rParent = rParent;
        rule = 1;
    }

    public PrimaryExp(LVal lVal) {
        this.lVal = lVal;
        rule = 2;
    }

    public PrimaryExp(Number number) {
        this.number = number;
        rule = 3;
    }

    public void print() throws IOException {
        switch (rule) {
            case 1:
                lParent.print();
                exp.print();
                rParent.print();
                break;
            case 2:
                lVal.print();
                break;
            case 3:
                number.print();
                break;
            default:
                System.out.println("wrong!");
                break;
        }
        Output.component(ParseType.PrimaryExp);
    }

    protected Value val;
    @Override
    public void generateMidCode() {
        switch (rule) {
            case 1 -> {
                exp.generateMidCode();
                this.val = exp.val;
            }
            case 2 -> {
                lVal.generateMidCode();
                if (lVal.val instanceof IntegerValue) {
                    this.val = lVal.val;
                } else {
                    String tmp = MidGenerator.generateTmpName(null);
                    VarSymbol tmpSym = new VarSymbol(null, tmp, new MidType(lVal.val.getDim()), false);
                    MidController.addNewSymbol(tmp, tmpSym);
                    IRModule.addInstr(new ValAssign(tmpSym.getMidName(), tmpSym, lVal.val));
                    this.val = tmpSym;
                }
            }
            case 3 -> {
                number.generateMidCode();
                this.val = number.val;
            }
        }
    }

    @Override
    public Value getVal() {
        return val;
    }

    public int getCalculable() {
        switch (rule) {
            case 1 -> {
                return exp.getCalculable();
            }
            case 2-> {
                return lVal.getCalculable();
            }
            case 3 -> {
                return number.getCalculable();
            }
            default -> throw new IllegalStateException("不应该出现的情况 " + rule);
        }
    }


    public ArrayList<Token> toTokens () {
        ArrayList<Token> tokens = new ArrayList<>();
        switch (rule) {
            case 1:
                tokens.add(lParent);
                tokens.addAll(exp.toTokens());
                tokens.add(rParent);
                break;
            case 2:
                tokens.addAll(lVal.toTokens());
                break;
            case 3:
                tokens.addAll(number.toTokens());
                break;
            default:
                System.out.println("wrong!");
                break;
        }
        return tokens;
    }
}
