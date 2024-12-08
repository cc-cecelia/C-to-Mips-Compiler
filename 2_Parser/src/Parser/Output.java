package Parser;

import Lexer.Token;

import java.io.FileWriter;
import java.io.IOException;

public class Output {
    public static FileWriter fileWriter;

    static {
        try {
            fileWriter = new FileWriter("output.txt");
        } catch (IOException e) {
            System.out.println("No output.txt exists");
        }
    }

    public static void component(ParseType type) throws IOException {
        fileWriter.write("<" + type.toString() + ">\n");
    }

    public static void token(Token token) throws IOException {
        fileWriter.write(token.type.toString() + " " + token.context + "\n");
    }

    public static void close() {
        try {
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
