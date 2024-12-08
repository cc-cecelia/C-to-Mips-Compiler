package MidCode;

import IO.Output;
import MidCode.Instructions.Function;
import MidCode.Instructions.Instruction;
import MidCode.Instructions.PrintStr;
import MidCode.Table.FuncSymbol;
import Target.Assembler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class IRModule {
    private static List<Instruction> globals = new ArrayList<>();
    private static ArrayList<String> funcNames = new ArrayList<>();
    protected static LinkedHashMap<String, Function> functions = new LinkedHashMap<>();
    static {
        functions.put("main",null);
        funcNames.add("main");
    }
    private static String curFunc;
    private static List<PrintStr> printed = new ArrayList<>();
    // 代码声称 ADDED
    private static int curInstr = 0;
    private static int funcIndex = 0;


    public static void enterFunc(String name, Function function) {
        curFunc = name;
        if (curFunc.equals("main")) {
            functions.replace("main",function);
        } else {
            functions.put(curFunc, function);
            funcNames.add(name);
        }
    }

    public static void exitFunc() {
        curFunc = null;
    }

    public static Function getSubFunc(FuncSymbol symbol) {
        return functions.get(symbol.getMidName());
    }

    public static void addInstr(Instruction instruction) {
        if (instruction instanceof PrintStr) {
            printed.add(((PrintStr) instruction));
        }
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
            Output.midCode(instruction);
        }

        for (Function function : functions.values()) {
            Output.midCode(function);
            //Output.br();
        }
    }



    private static int printIndex;
    public static PrintStr getPrinted() {
        if (printIndex < printed.size()) {
            return printed.get(printIndex++);
        } else {
            return null;
        }
    }

    // 代码生成
    public static Instruction getGlobalInstr() {
        if (curInstr < globals.size()) {
            return globals.get(curInstr++);
        } else {
            return null;
        }
    }

    public static Instruction getInstr() {
        if (funcIndex != functions.size()) {
            curFunc = funcNames.get(funcIndex);
            return functions.get(funcNames.get(funcIndex++));
        } else {
            return null;
        }
    }

    public static Function getCurFunc() {
        return functions.get(curFunc);
    }
}