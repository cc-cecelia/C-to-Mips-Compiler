package Fronted.Error;

import Fronted.Lexer.Token;
import Fronted.Parser.SymbleTable.Symbol;
import Fronted.Parser.SymbleTable.TablesController;
import IO.Output;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ErrHandler {
    public static boolean hasErr = false;
    protected static List<Err> errs;
    static {
        errs = new ArrayList<>();
    }
    public static TablesController controller;

    /* ERROR DEALING FLAG */
    public static boolean ignoreScope;
    public static boolean ignoreError;



    public static void add(Err err) {
        if (ignoreScope || ignoreError) {
            return;
        }
        //        if (errors.get(errors.size() - 1).equals(error)) {
//            // System.out.println("this line has already report the Fronted.Error");
//            return;
//        }
        errs.add(err);
        hasErr = true;
    }


    public static boolean checkTypeA(Token formatStr) {
        String str = formatStr.context.substring(1,formatStr.context.length()-1);
        int curPos = 0;
        while (curPos < str.length() ) {
            char ch = str.charAt(curPos);
            Character next = curPos+1 >= str.length() ? null : str.charAt(curPos+1);
            if (_NormalChar(ch, next)) {
                curPos++;
            } else if (_FormatChar(ch, next)) {
                curPos += 2;
            } else {
                return true;
            }
        }
        return false;
    }

    public static boolean _NormalChar(char ch, Character next) {
        if (ch == 32 || ch == 33 || (ch >= 40 && ch <= 91) || (ch >= 93 && ch <= 126)) {
            return true;
        }
        if (ch == '\\') {
            if (next == null) {
                 return false;
            }
            return next == 'n';
        }
        return false;
    }

    public static boolean _FormatChar(char ch, Character next) {
        if (next == null) {
            return false;
        }
        String sb = String.valueOf(ch) + next;
        return sb.equals("%d");
    }

    public static boolean checkTypeL(Token strCon, int expCnt) {
        if (ignoreScope || ignoreError) {
            return true;
        }
        Pattern pattern = Pattern.compile("%d");
        Matcher matcher = pattern.matcher(strCon.context);
        int cnt = 0;
        while (matcher.find()) {
            cnt++;
        }
        return (cnt == expCnt);
    }

    public static int checkFuncRParams(Token ident) {
        if (ignoreScope || ignoreError || controller.isIgnoreTypeMatch) {
            return -1;
        }
        return controller.checkFuncRParams(ident);
    }

    public static boolean checkVoidReturn(boolean isNull) {
        if (ignoreScope || ignoreError) {
            return false;
        }
        return controller.checkVoidReturn(isNull);
    }

    public static boolean constReassign(Token ident) {
        if (ignoreScope || ignoreError) {
            return false;
        }
        return controller.constReassign(ident);
    }

    public static boolean isEverExist(int level, Token token, Class<?> Class) {
        if (ignoreScope || ignoreError) {
            return true;
        }
        return controller.isEverExist(level,token, Class);
    }

    public static boolean isRedefined(Symbol symbol) {
        if (ignoreScope || ignoreError) {
            return false;
        }
        return controller.isRedefined(symbol);
    }
    public static void print() throws IOException {
        for (Err err : errs) {
            Output.error(err);
        }
    }
}
