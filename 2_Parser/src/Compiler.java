import Lexer.Lexer;
import Parser.Output;
import Parser.Parser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Compiler {
    public static void main(String[] args) {
        Lexer lexer = Lexer.getInstance();
        Parser parser = Parser.getInstance();
        parser.setLexer(lexer);
        try {
            lexer.src = new String(Files.readAllBytes(Paths.get("testfile.txt")));
        } catch (IOException e) {
            System.out.println("no such file!");
        }
        lexer.analysis();
        parser.parseProgram();
        try {
            parser.print();
        } catch (IOException e) {
            System.out.println("write fail!");
        }
        Output.close();

        // lexer.print(); LAB1-Lexer
    }
}