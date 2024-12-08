import Lexer.Lexer;
import Parser.Parser;
import Error.ErrorHandler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import IO.Output;
import SymbleTable.TablesController;

public class Compiler {
    public static void main(String[] args) {
        Lexer lexer = Lexer.getInstance();
        Parser parser = Parser.getInstance();
        TablesController controller = TablesController.getInstance();
        ErrorHandler errorHandler = ErrorHandler.getInstance();
        errorHandler.controller = controller;
        controller.handler = errorHandler;
        parser.setLexer(lexer);
        parser.setController(controller);
        parser.setErrorHandler(errorHandler);
        try {
            lexer.src = new String(Files.readAllBytes(Paths.get("testfile.txt")));
        } catch (IOException e) {
            System.out.println("no such file!");
        }
        lexer.analysis();
        parser.parseProgram();
//        try {
//            parser.print();
//        } catch (IOException e) {
//            System.out.println("write fail!");
//        }
        try {
            errorHandler.print();
        } catch (IOException e) {
            System.out.println("write Fail!");
        }
        Output.close();

        // lexer.print(); LAB1-Lexer
    }
}