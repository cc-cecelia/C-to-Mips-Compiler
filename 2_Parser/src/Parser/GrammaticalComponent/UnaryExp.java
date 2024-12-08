package Parser.GrammaticalComponent;

import Lexer.Token;
import Parser.Output;
import Parser.ParseType;

import java.io.IOException;

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
}
