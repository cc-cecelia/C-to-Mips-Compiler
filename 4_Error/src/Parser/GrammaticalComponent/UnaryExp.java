package Parser.GrammaticalComponent;

import Lexer.Token;
import IO.Output;
import Parser.ParseType;

import java.io.IOException;
import java.util.ArrayList;

public class UnaryExp {
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
