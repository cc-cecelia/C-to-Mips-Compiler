package Fronted.Parser;

import Fronted.Lexer.LexType;
import Fronted.Lexer.Token;

public class Judge {
    public static boolean match(Token token, LexType lexType) {
        return token.type == lexType;
    }

    public static boolean isBlockItem(Token token) {
        return isDecl(token) || isStmt(token);
    }

    public static boolean isDecl(Token token) {
        return token.type == LexType.CONSTTK || token.type == LexType.INTTK;
    }

    public static boolean isStmt(Token token) {
        return token.type == LexType.IDENFR || token.type == LexType.LBRACE ||
                token.type == LexType.IFTK || token.type == LexType.FORTK ||
                token.type == LexType.BREAKTK || token.type == LexType.CONTINUETK ||
                token.type == LexType.RETURNTK || token.type == LexType.PRINTFTK ||
                token.type == LexType.SEMICN || isExp(token);
    }

    public static boolean isExp(Token token) {
        return token.type == LexType.PLUS || token.type == LexType.MINU ||
                token.type == LexType.NOT || token.type == LexType.LPARENT ||
                token.type == LexType.IDENFR || token.type == LexType.INTCON;
    }

    public static boolean isTerm(Token token) {
        return token.type == LexType.MULT || token.type == LexType.DIV || token.type == LexType.MOD;
    }

    public static boolean isExpr(Token token) {
        return token.type == LexType.PLUS || token.type == LexType.MINU;
    }

    public static boolean isUnaryOp(Token token) {
        return token.type == LexType.PLUS || token.type == LexType.MINU || token.type == LexType.NOT;
    }

    public static boolean isCompare(Token token) {
        return token.type == LexType.LSS || token.type == LexType.GRE ||
                token.type == LexType.LEQ || token.type == LexType.GEQ;
    }

    public static boolean isEqTK(Token token) {
        return token.type == LexType.EQL || token.type == LexType.NEQ;
    }
    public static boolean isFuncType(Token token) {
        return token.type == LexType.VOIDTK || token.type == LexType.INTTK;
    }
}
