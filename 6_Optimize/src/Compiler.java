import Fronted.Error.ErrHandler;
import Fronted.Lexer.Lexer;
import Fronted.Parser.Parser;
import Fronted.Parser.SymbleTable.TablesController;
import IO.Output;
import MidCode.IRModule;
import MidCode.MidGenerator;
import Target.Assembler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Compiler {
    public static void main(String[] args) throws IOException {
        Lexer lexer = Lexer.getInstance();
        Parser parser = Parser.getInstance();
        TablesController controller = TablesController.getInstance();
        ErrHandler.controller = controller;
        parser.setLexer(lexer);
        parser.setController(controller);
        try {
            lexer.src = new String(Files.readAllBytes(Paths.get("testfile.txt")));
            //lexer.src = new String(Files.readAllBytes(Paths.get("D:\\C_Compiler\\Target\\辅助测试\\A\\testfile.txt")));
            //lexer.src = new String(Files.readAllBytes(Paths.get("D:\\C_Compiler\\混合辅助测试库\\testfile.txt")));
        } catch (IOException e) {
            System.out.println("no such file!");
        }
        lexer.analysis();
        parser.parseProgram();
        MidGenerator.CompUnit = parser.getCompUnit();

        if (ErrHandler.hasErr) {
            ErrHandler.print(); //LAB3-Fronted.error
            Output.close();
            return;
        }
        MidGenerator.generateMidCode();
        //parser.print();
        //IRModule.print();
        IRModule.optimize();
        //IRModule.printOptimized();
        Assembler.generateMipsCode(false);
        //Assembler.printOld();
        IRModule.clear();
        Assembler.clear();
        Assembler.generateMipsCode(true);
        Assembler.printOptimize();

        Output.close();

        // lexer.print(); LAB1-Fronted.Lexer
        // parser.print(); LAB2-Fronted.parser
    }
}