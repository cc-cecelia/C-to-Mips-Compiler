package Fronted.Lexer;

public class MyCharacter{
    private MyCharacter(){}

    public static boolean isDigit(char ch) {
        return Character.isDigit(ch);
    }

    public static boolean isLetterOrUnderscore(char ch) {
        return Character.isLetter(ch) || ch == '_';
    }

    public static boolean isValidIdentifier(char ch) {
        return Character.isLetter(ch) || Character.isDigit(ch) || ch == '_';
    }

    public static boolean isForwardSlash(char ch) {
        return ch == '/';
    }
    public static boolean isEscape(char ch) {
        return ch == '\n' || ch == '\r' || ch == '\t';
    }

    public static boolean isBlank(char ch) {
        return ch == ' ';
    }

    public static boolean isNewLine(char ch) {
        return ch == '\n';
    }

    public static boolean isTab(char ch) {
        return ch == '\t';
    }

    public static boolean isEnter(char ch) {
        return ch == '\r';
    }
    
    public static boolean isQuotation(char ch) {
        return ch == '"';
    }

    public static boolean isAmpersand(char ch) {
        return ch == '&';
    }

    public static boolean isStar(char ch) {
        return ch == '*';
    }
}
