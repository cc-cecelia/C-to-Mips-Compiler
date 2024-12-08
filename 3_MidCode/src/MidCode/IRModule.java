package MidCode;

import IO.Output;
import MidCode.Instructions.Function;
import MidCode.Instructions.Instruction;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class IRModule {
    private static List<Instruction> globals = new ArrayList<>();
    private static LinkedHashMap<String, Function> functions = new LinkedHashMap<>();
    private static String curFunc;

    public static void enterFunc(String name, Function function) {
        curFunc = name;
        functions.put(curFunc,function);
    }

    public static void exitFunc() {
        curFunc = null;
    }

    public static void addInstr(Instruction instruction) {
        if (curFunc == null) {
            globals.add(instruction);
        } else {
            functions.get(curFunc).add(instruction);
        }
    }

    public static void addInstrs(List<Instruction> instructionList) {
        if (curFunc == null) {
            globals.addAll(instructionList);
        } else {
            functions.get(curFunc).addAll(instructionList);
        }
    }

    public static void print() throws IOException {
        for (Instruction instruction : globals) {
            Output.instruction(instruction);
        }

        for (Function function : functions.values()) {
            Output.instruction(function);
            Output.br();
        }
    }

}
