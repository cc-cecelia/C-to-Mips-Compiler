package IO;

import Lexer.Token;
import Error.Error;
import Parser.ParseType;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Output {
    public static FileWriter fileWriter;
    public static FileWriter errorWriter;

    static {
        try {
            fileWriter = new FileWriter("error.txt");
        } catch (IOException e) {
            System.out.println("No error.txt exists");
        }
        try {
            errorWriter = new FileWriter("error.txt");
        } catch (IOException e) {
            System.out.println("No error.txt exists");
        }
    }

    public static void component(ParseType type) throws IOException {
        fileWriter.write("<" + type.toString() + ">\n");
    }

    public static void token(Token token) throws IOException {
        fileWriter.write(token.type.toString() + " " + token.context + "\n");
    }

    public static void error(Error error) throws IOException {
        errorWriter.write(error.toString() + "\n");
    }

    public static void close() {
        try {
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            errorWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
