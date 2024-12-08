package Fronted.Error;

import Fronted.Lexer.Token;
import IO.Output;
import Fronted.Parser.SymbleTable.TablesController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ErrorHandler {
    private static ErrorHandler errorHandler = new ErrorHandler();
    protected List<Error> errors;
    public TablesController controller;

    /* ERROR DEALING FLAG */
    public boolean ignoreScope;
    public boolean ignoreError;

    private ErrorHandler() {
        this.errors = new ArrayList<>();
    }

    public static ErrorHandler getInstance() {
        return errorHandler;
    }

    public void add(Error error) {
        if (ignoreScope || ignoreError) {
            return;
        }
        if (errors.isEmpty()) {
            errors.add(error);
            return;
        }
//        if (errors.get(errors.size() - 1).equals(error)) {
//            // System.out.println("this line has already report the Fronted.Error");
//            return;
//        }
        errors.add(error);
    }


    public boolean checkTypeA(Token formatStr) {
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

    public boolean _NormalChar(char ch, Character next) {
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

    public boolean _FormatChar(char ch, Character next) {
        if (next == null) {
            return false;
        }
        String sb = String.valueOf(ch) + next;
        return sb.equals("%d");
    }

    public boolean checkTypeL(Token strCon, int expCnt) {
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

    public int checkFuncRParams(Token ident) {
        if (ignoreScope || ignoreError || controller.isIgnoreTypeMatch) {
            return -1;
        }
        return controller.checkFuncRParams(ident);
    }

    public boolean checkVoidReturn(boolean isNull) {
        if (ignoreScope || ignoreError) {
            return false;
        }
        return controller.checkVoidReturn(isNull);
    }

    public boolean constReassign(Token ident) {
        if (ignoreScope || ignoreError) {
            return false;
        }
        return controller.constReassign(ident);
    }

    public boolean isEverExist(int level, Token token, Class<?> Class) {
        if (ignoreScope || ignoreError) {
            return true;
        }
        return controller.isEverExist(level,token, Class);
    }

    public boolean isRedefined(Token token) {
        if (ignoreScope || ignoreError) {
            return false;
        }
        return controller.isRedefined(token);
    }
    public void print() throws IOException {
        for (Error error : this.errors) {
            Output.error(error);
        }
    }
}
