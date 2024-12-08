package IO;

import Fronted.Lexer.Token;
import Fronted.Error.Err;
import Fronted.Parser.ParseType;
import MidCode.Instructions.Instruction;
import Target.Instructions.MipsCode;

import java.io.FileWriter;
import java.io.IOException;

public class Output {
    public static FileWriter fileWriter;
    public static FileWriter errorWriter;
    public static FileWriter midCodeWriter;
    public static FileWriter assemblerWriter;

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
            assemblerWriter = new FileWriter("mips.txt");
        } catch (IOException e) {
            System.out.println("No mips.asm");
        }
    }

    public static void component(ParseType type) throws IOException {
        fileWriter.write("<" + type.toString() + ">\n");
    }

    public static void token(Token token) throws IOException {
        fileWriter.write(token.type.toString() + " " + token.context + "\n");
    }

    public static void error(Err err) throws IOException {
        errorWriter.write(err.toString() + "\n");
    }

    public static void midCode(Instruction instruction) throws IOException{
        midCodeWriter.write(instruction.toString());
    }

    public static void assemble(MipsCode mipsCode) throws IOException {
        assemblerWriter.write(mipsCode.toString());
    }

    public static void assemble(MipsCode mipsCode, FileWriter writer) throws IOException {
        writer.write(mipsCode.toString());
    }

    public static void br() throws IOException {
        assemblerWriter.write("\n");
    }

    public static void str (String str) throws IOException {
        assemblerWriter.write(str);
    }

    public static void str (String str, FileWriter writer) throws IOException {
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
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
