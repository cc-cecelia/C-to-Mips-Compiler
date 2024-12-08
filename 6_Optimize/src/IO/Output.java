package IO;

import Fronted.Error.Err;
import Fronted.Lexer.Token;
import Fronted.Parser.ParseType;
import MidCode.BasicBlock;
import MidCode.Instructions.Instruction;
import Target.Instructions.MipsCode;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Output {
    public static FileWriter fileWriter;
    public static FileWriter errorWriter;
    public static FileWriter midCodeWriter;

    public static FileWriter midCodeOptimizedWriter;
    public static FileWriter assemblerWriter;
    public static FileWriter assemblerOptimizedWriter;

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
        try {
            assemblerWriter = new FileWriter("mips_old.txt");
        } catch (IOException e) {
            System.out.println("No mips.asm");
        }
        try {
            midCodeOptimizedWriter = new FileWriter("midcode_optimized.txt");
        } catch (IOException e) {
            System.out.println("no midcode_optimized.txt");
        }
        try {
            assemblerOptimizedWriter = new FileWriter("mips.txt");
        } catch (IOException e) {
            System.out.println("no mips_optimized.txt");
        }


    }

    public static void component(ParseType type) throws IOException {
        fileWriter.write("<" + type.toString() + ">\n");
    }

    public static void token(Token token) throws IOException {
        fileWriter.write(token.type.toString() + " " + token.context + "\n");
    }

    public static void error(Err error) throws IOException {
        errorWriter.write(error.toString() + "\n");
    }

    public static void midCode(Instruction instruction) throws IOException {
        midCodeWriter.write(instruction.toString());
    }

    public static void midCodeOptimized(Instruction instruction) throws IOException {
        if (instruction instanceof BasicBlock blk) {
            midCodeOptimizedWriter.write(blk.op2String());
        } else {
            midCodeOptimizedWriter.write(instruction.toString());
        }
    }

    public static void str(String str) throws IOException {
        midCodeOptimizedWriter.write(str);
    }

    public static void assemble(MipsCode mipsCode) throws IOException {
        assemblerWriter.write(mipsCode.toString());
    }

    public static void assembleOptimized(MipsCode mipsCode) throws IOException {
        assemblerOptimizedWriter.write(mipsCode.toString());
    }

    public static void assemble(MipsCode mipsCode, FileWriter writer) throws IOException {
        writer.write(mipsCode.toString());
    }

    public static void br() throws IOException {
        assemblerWriter.write("\n");
    }

    public static void str(String str,boolean isOp) throws IOException {
        if (isOp) {
            assemblerOptimizedWriter.write(str);
        } else {
            assemblerWriter.write(str);
        }
    }

    public static void str(String str, FileWriter writer) throws IOException {
        writer.write(str);
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
        try {
            assemblerWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            midCodeOptimizedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            assemblerOptimizedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
