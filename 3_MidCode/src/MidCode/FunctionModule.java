package MidCode;

import MidCode.Instructions.FuncHead;
import MidCode.Instructions.Instruction;
import MidCode.Table.Symbol;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FunctionModule {
    private static FunctionModule functionModule = new FunctionModule();

    private HashMap<String, List<Instruction>> functions = new HashMap<>();
    private String curFunc;

    private FunctionModule() {}

    public static FunctionModule getInstance() {
        return functionModule;
    }

    public void enterNewFunc(String funcName) {
        functions.put(funcName,new ArrayList<>());
    }

    public void addFuncInstr(Instruction instruction) {
        functions.get(curFunc).add(instruction);
    }
}
