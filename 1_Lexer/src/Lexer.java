
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class Lexer {
    private static Lexer lexer = new Lexer();
    HashMap<String, LexType> reserves;
    String src;
    int curPos = -1;
    int linePos = 1;
    boolean errFlag = false;

    private ArrayList<Integer> errs = new ArrayList<>();
    private Queue<Token> tokens = new LinkedList<>();

    private Lexer() {
        this.reserves = new HashMap<>();
        reserves.put("main", LexType.MAINTK);
        reserves.put("const", LexType.CONSTTK);
        reserves.put("int", LexType.INTTK);
        reserves.put("break", LexType.BREAKTK);
        reserves.put("continue", LexType.CONTINUETK);
        reserves.put("if", LexType.IFTK);
        reserves.put("else", LexType.ELSETK);
        reserves.put("for", LexType.FORTK);
        reserves.put("getint", LexType.GETINTTK);
        reserves.put("printf", LexType.PRINTFTK);
        reserves.put("return", LexType.RETURNTK);
        reserves.put("void", LexType.VOIDTK);
        reserves.put("+", LexType.PLUS);
        reserves.put("-", LexType.MINU);
        reserves.put("*", LexType.MULT);
        reserves.put("/", LexType.DIV);
        reserves.put("%", LexType.MOD);
        reserves.put(";", LexType.SEMICN);
        reserves.put(",", LexType.COMMA);
        reserves.put("!", LexType.NOT);
        reserves.put("&&", LexType.AND);
        reserves.put("||", LexType.OR);
        reserves.put("<", LexType.LSS);
        reserves.put("<=", LexType.LEQ);
        reserves.put(">", LexType.GRE);
        reserves.put(">=", LexType.GEQ);
        reserves.put("==", LexType.EQL);
        reserves.put("!=", LexType.NEQ);
        reserves.put("=", LexType.ASSIGN);
        reserves.put("(", LexType.LPARENT);
        reserves.put(")", LexType.RPARENT);
        reserves.put("[", LexType.LBRACK);
        reserves.put("]", LexType.RBRACK);
        reserves.put("{", LexType.LBRACE);
        reserves.put("}", LexType.RBRACE);
    }

    public static Lexer getInstance() {
        return lexer;
    }

    public void next() {
        char c;
        while (!isEndOfSrc()) {
            errFlag = false;
            StringBuilder tokenVal = new StringBuilder();
            Token token;
            c = getChar();
            tokenVal.append(c);
            // 空格
            if (MyCharacter.isBlank(c)) {
                continue;
            }
            // 转义字符
            else if (MyCharacter.isEscape(c)) {
                escape(c);
                continue;
            }
            // 字母、下划线
            else if (MyCharacter.isLetterOrUnderscore(c)) {
                token = identifier(tokenVal);
            }
            // 数字
            else if (MyCharacter.isDigit(c)) {
                token = number(tokenVal);
            }
            // 字符串
            else if (MyCharacter.isQuotation(c)) {
                token = string(tokenVal);
            }
            // 其他
            else {
                token = operation(tokenVal);
            }
            if (!errFlag) {
                tokens.add(token);
            }
        }
    }

    public boolean isEndOfSrc() {
        return curPos == src.length() - 1;
    }

    public char getChar() {
        return src.charAt(++curPos);
    }

    public char unGetChar() {
        return src.charAt(curPos--);
    }

    public Token identifier(StringBuilder tokenVal) {
        char c;
        while (!isEndOfSrc() && MyCharacter.isValidIdentifier((c = getChar()))) {
            tokenVal.append(c);
        }
        c = unGetChar();
        LexType type = this.reserves.getOrDefault(tokenVal.toString(), LexType.IDENFR);
        return new Token(tokenVal.toString(), type, linePos, null);
    }

    public Token number(StringBuilder tokenVal) {
        char c;
        while (!isEndOfSrc() && MyCharacter.isDigit(c = getChar())) {
            tokenVal.append(c);
        }
        c = unGetChar();
        int num = Integer.parseInt(tokenVal.toString());
        return new Token(tokenVal.toString(), LexType.INTCON, linePos, num);
    }

    public Token string(StringBuilder tokenVal) {
        char c;
        while (!isEndOfSrc()) {
            tokenVal.append(c = getChar());
            // ERROR!
            if (MyCharacter.isAmpersand(c)) {
                errs.add(linePos);
                errFlag = true;
            } else if (MyCharacter.isQuotation(c)) {
                break;
            }
        }
        return new Token(tokenVal.toString(), LexType.STRCON, linePos, null);
    }

    public void escape(char c) {
        // \n
        if (MyCharacter.isNewLine(c)) {
            linePos++;
        }
        // \t
        else if (MyCharacter.isTab(c)) {
        }
        // \r
        else if (MyCharacter.isEnter(c)) {
        }
    }

    public Token operation(StringBuilder tokenVal) {
        char c = tokenVal.charAt(0);
        // 单目运算符
        if (c == '+' || c == '-' || c == '*' || c == '%'
                || c == ';' || c == ',' || c == '(' || c == ')' || c == '[' || c == ']' || c == '{' || c == '}') {
        }
        // 双目运算符
        else {
            switch (c) {
                case '<':
                case '>':
                case '=':
                case '!':
                    if (!isEndOfSrc() && (c = getChar()) == '=') {
                        tokenVal.append(c);
                    } else {
                        c = unGetChar();
                    }
                    break;
                case '&':
                    if (!isEndOfSrc() && (c = getChar()) == '&') {
                        tokenVal.append(c);
                    } else {
                        c = unGetChar();
                        errs.add(linePos);
                    }
                    break;
                case '|':
                    if (!isEndOfSrc() && (c = getChar()) == '|') {
                        tokenVal.append(c);
                    } else {
                        c = unGetChar();
                        errs.add(linePos);
                    }
                    break;
                case '/':
                    c = getChar(); // 第二个字符
                    // 单行注释
                    if (!isEndOfSrc() && MyCharacter.isForwardSlash(c)) {
                        return singleLineNote(tokenVal, c);
                    }
                    // 跨行注释
                    else if (!isEndOfSrc() && MyCharacter.isStar(c)) {
                        return crossLineNote(tokenVal, c);
                    }
                    else {
                        c = unGetChar();
                    }
                    break;
                default:
                    errs.add(linePos);
                    errFlag = true;
                    break;
            }
        }
        return new Token(tokenVal.toString(), this.reserves.get(tokenVal.toString()), linePos, null);
    }

    public Token singleLineNote(StringBuilder tokenVal, char c) {
        //tokenVal.append(c);
        while (!isEndOfSrc()) {
            tokenVal.append(c);
            if (MyCharacter.isNewLine(c = getChar())) {
                break;
            }
        }
        return new Token(tokenVal.toString(), LexType.NOTE, linePos++, null);
    }

    public Token crossLineNote(StringBuilder tokenVal, char c) {
        while (!isEndOfSrc()) {
            tokenVal.append(c);
            while (!isEndOfSrc() && !MyCharacter.isStar(c = getChar())) {
                tokenVal.append(c);
                if (MyCharacter.isNewLine(c)) {
                    linePos++;
                }
            }
            while (!isEndOfSrc() && MyCharacter.isStar(c)) {
                tokenVal.append(c);
                c = getChar();
            }
            if (MyCharacter.isForwardSlash(c)) {
                tokenVal.append(c);
                return new Token(tokenVal.toString(), LexType.NOTE, linePos, null);
            }
        }
        errFlag = true;
        return null;
    }

    public void print() {
        try {
            FileWriter fileWriter = new FileWriter("output.txt");
            for (Token token : tokens) {
                if (token.type == LexType.NOTE) {
                    continue;
                }
                fileWriter.write(token.type.toString() + " " + token.context + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("output fail!");
        }
    }

    public void print(int dir,int i) {
        String prefix = (char) dir + "/";
        try {
            FileWriter fileWriter = new FileWriter(prefix + "output" + (i+1) + ".txt");
            for (Token token : tokens) {
                if (token.type == LexType.NOTE) {
                    continue;
                }
                fileWriter.write(token.type.toString() + " " + token.context + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("output fail!");
        }
    }
}
