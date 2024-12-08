package Fronted.ASTComponent;

import Fronted.Lexer.Token;
import Fronted.Parser.ParseType;
import IO.Output;
import MidCode.IRModule;
import MidCode.Instructions.Calculate;
import MidCode.Instructions.CallFunc;
import MidCode.Instructions.ValAssign;
import MidCode.MidGenerator;
import MidCode.Table.*;
import MidCode.Value.IntegerValue.IntegerType;
import MidCode.Value.IntegerValue.IntegerValue;
import MidCode.Value.Value;

import java.io.IOException;
import java.util.ArrayList;

public class UnaryExp implements AST {
    UnaryExp unaryExp;
    private PrimaryExp primaryExp;
    private Token ident;
    private Token lParent;
    private FuncRParams funcRParams;
    private Token rParent;
    private UnaryOp unaryOp;
    private int rule;

    public UnaryExp(PrimaryExp primaryExp) {
        this.primaryExp = primaryExp;
        rule = 1;
    }

    public UnaryExp(Token ident, Token lParent, FuncRParams funcRParams, Token rParent) {
        this.ident = ident;
        this.lParent = lParent;
        this.funcRParams = funcRParams;
        this.rParent = rParent;
        this.rule = 2;
    }

    public UnaryExp(UnaryOp unaryOp, UnaryExp unaryExp) {
        this.unaryOp = unaryOp;
        this.unaryExp = unaryExp;
        rule = 3;
    }

    public void print() throws IOException {
        switch (rule) {
            case 1:
                primaryExp.print();
                break;
            case 2: {
                ident.print();
                lParent.print();
                if (funcRParams != null) {
                    funcRParams.print();
                }
                rParent.print();
                break;
            }
            case 3: {
                unaryOp.print();
                unaryExp.print();
                break;
            }
            default:
                System.out.println("wrong!");
                break;
        }
        Output.component(ParseType.UnaryExp);
    }

    protected Value val;
    @Override
    public void generateMidCode() {
        switch (rule) {
            case 1 -> {
                primaryExp.generateMidCode();
                this.val = primaryExp.val;
            }
            case 2 -> {
                // Ident '(' [FuncRParams] ')'
                if (funcRParams != null) {
                    funcRParams.generateMidCode();
                }
                FuncSymbol callFunc = (FuncSymbol) MidController.lookUp(MidController.curLevel,ident.context,new MidType(SymbolType.Func));
                if (callFunc == null) {
                    throw new RuntimeException("咋还能找不着呢！");
                }
                IRModule.addInstr(new CallFunc(callFunc));
                String retName = MidGenerator.generateTmpName(null);
                VarSymbol retVal = new VarSymbol(this,retName,new MidType(SymbolType.Func),false);
                callFunc.setRetVal(retVal);
                IRModule.addInstr(new ValAssign(retName,retVal,callFunc));
                this.val = retVal;
            }
            case 3 -> {
                // UnaryOp UnaryExp
                IntegerValue zero = new IntegerValue(0);
                //unaryExp.getCalculable();
                unaryExp.generateMidCode();
                if (unaryExp.val instanceof IntegerValue) {
                    int res = switch (unaryOp.getOp()) {
                        case "+" -> ((IntegerValue) unaryExp.val).getDim0Value();
                        case "-" -> - ((IntegerValue) unaryExp.val).getDim0Value();
                        case "!" -> ((IntegerValue) unaryExp.val).getDim0Value() == 0 ? 1 : 0;
                        default -> throw new IllegalStateException("Unexpected value: " + unaryOp.getOp());
                    };
                    this.val = new IntegerValue(IntegerType.DIM0,res,null);
                } else {
                    String tmp = MidGenerator.generateTmpName(null);
                    VarSymbol tmpSym = new VarSymbol(this,tmp,new MidType(SymbolType.VAR_0),false);
                    MidController.addNewSymbol(tmp,tmpSym);
                    IRModule.addInstr(new Calculate(tmpSym,unaryOp.getOp(),zero,unaryExp.val));
                    this.val = tmpSym;
                }
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
                return primaryExp.getCalculable();
            }
            case 3 -> {
                int unary = unaryExp.getCalculable();
                return switch (unaryOp.getOp()) {
                    case "+" -> unary;
                    case "-" -> -unary;
                    case "!" -> ~unary;
                    default -> throw new IllegalStateException("Unexpected Operation: " + unaryOp.getOp());
                };
            }
            default -> throw new IllegalStateException("不应该出现的计算方式! " + rule);
        }
    }

    public ArrayList<Token> toTokens() {
        ArrayList<Token> tokens = new ArrayList<>();
        switch (rule) {
            case 1:
                tokens.addAll(primaryExp.toTokens());
                break;
            case 2: {
                tokens.add(ident);
                tokens.add(lParent);
                if (funcRParams != null) {
                    tokens.addAll(funcRParams.toTokens());
                }
                tokens.add(rParent);
                break;
            }
            case 3: {
                tokens.addAll(unaryOp.toTokens());
                tokens.addAll(unaryExp.toTokens());
                break;
            }
            default:
                System.out.println("wrong!");
                break;
        }
        return tokens;
    }
}
