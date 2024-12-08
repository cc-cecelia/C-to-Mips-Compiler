package Lexer;

import Parser.Judge;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class Lexer {
    private static Lexer lexer = new Lexer();
    public Token now;
    HashMap<String, LexType> reserves;
    public String src;
    int curPos = -1;
    int linePos = 1;
    boolean errFlag = false;
    private ArrayList<Integer> errs = new ArrayList<>();
    private LinkedList<Token> tokens = new LinkedList<>();
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

    private int curToken;

    public void clear() {
        this.now = null;
        this.src = null;
        this.curPos = -1;
        this.linePos = -1;
        this.errFlag = false;
        this.errs.clear();
        this.tokens.clear();
        this.curToken = 0;
    }
    public void next() {
        if (curToken < tokens.size()) {
            this.now = tokens.get(curToken++);
            while (curToken < tokens.size() && Judge.match(this.now,LexType.NOTE)) {
                this.now = tokens.get(curToken++);
            }
        } else {
            this.now = null;
        }
    }

    public Token preRead() {
        return this.tokens.get(this.tokens.indexOf(this.now) + 1);
    }

    public Token preRead(Token pre) {
        int index = this.tokens.indexOf(pre);
        return this.tokens.get(index + 1);
    }

    // index为现在的token下标
    // assign为下一次分号出现前的等号的下标(都仅限于当前行)
    // if : !assign > index -> exp;
    // else if  assign.next == getInt -> lval = getInt()
    // else : lval = exp
    public int judge() {
        int assign = -1;
        for (int i = curToken -1; i < tokens.size(); i++) {
            if (Judge.match(tokens.get(i),LexType.SEMICN)) {
                break;
            }
            if (Judge.match(tokens.get(i),LexType.ASSIGN)) {
                assign = i;
                break;
            }
        }
        if (assign <= curToken-1) {
            return 0;
        } else {
            if (Judge.match(tokens.get(assign+1),LexType.GETINTTK)) {
                return 2;
            } else {
                return 1;
            }
        }
    }
    public void analysis() {
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
                tokens.offer(token);
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
                    } else {
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

//    public void print() {
//        try {
//            FileWriter fileWriter = new FileWriter("output.txt");
//            for (Token token : tokens) {
//                if (token.type == LexType.NOTE) {
//                    continue;
//                }
//                fileWriter.write(token.type.toString() + " " + token.context + "\n");
//            }
//            fileWriter.close();
//        } catch (IOException e) {
//            System.out.println("output fail!");
//        }
//    }
}
