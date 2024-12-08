package IO;

import Fronted.Lexer.Token;
import Fronted.Error.Error;
import Fronted.Parser.ParseType;
import MidCode.Instructions.Instruction;

import java.io.FileWriter;
import java.io.IOException;

public class Output {
    public static FileWriter fileWriter;
    public static FileWriter errorWriter;
    public static FileWriter midCodeWriter;

    static {
        try {
            fileWriter = new FileWriter("output.txt");
        } catch (IOException e) {
            System.out.println("No output.txt exists");
        }
        try {
            errorWriter = new FileWriter("error.txt");
        } catch (IOException e) {
            System.out.println("No error.txt exists");
        }
        try {
            midCodeWriter = new FileWriter("midcode.txt");
        } catch (IOException e) {
            System.out.println("No midcode.txt");
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

    public static void instruction(Instruction instruction) throws IOException{
        midCodeWriter.write(instruction.toString());
    }

    public static void br() throws IOException {
        midCodeWriter.write("\n");
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
        try {
            midCodeWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
