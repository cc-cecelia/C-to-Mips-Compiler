import Fronted.Error.ErrorHandler;
import Fronted.Lexer.Lexer;
import Fronted.Parser.Parser;
import Fronted.Parser.SymbleTable.TablesController;
import IO.Output;
import MidCode.Generator;
import MidCode.IRModule;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Compiler {
    public static void main(String[] args) throws IOException {
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
        Generator.CompUnit = parser.getCompUnit();
        Generator.generateMidCode();
        parser.print();
        IRModule.print();

        Output.close();

        // lexer.print(); LAB1-Fronted.Lexer
        // parser.print(); LAB2-Fronted.parser
        // errorHandler.print(); LAB3-Fronted.error
    }
}