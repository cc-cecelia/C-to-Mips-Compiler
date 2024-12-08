package MidCode.Optimize;

import MidCode.BasicBlock;
import MidCode.Instructions.Calculate;
import MidCode.Instructions.CallFunc;
import MidCode.Instructions.Function;
import MidCode.Instructions.Instruction;

import java.util.ArrayList;
import java.util.HashMap;

public class FuncOptimizer {
    // 删除无用函数，没被调用过的

    public static void delUselessFunc(ArrayList<String> funcNames,HashMap<String, Function> functionHashMap) {
        for (int i = 0; i < funcNames.size();i++) {
            String curFuncName = funcNames.get(i);
            Function curFunc = functionHashMap.get(curFuncName);
            boolean curFlag= false;
            for (Function testFunc : functionHashMap.values()) {
                if (curFunc.getFuncSymbol().equals(testFunc.getFuncSymbol())) {
                    continue;
                }
                for (Instruction instruction : testFunc.getInstructions()) {
                    if (instruction instanceof CallFunc call && call.getCalFunc().equals(curFunc.getFuncSymbol())) {
                        curFlag = true;
                        break;
                    }
                }
                if (curFlag) {
                    break;
                }
            }
            if (!curFlag) {
                functionHashMap.remove(curFuncName,curFunc);
                funcNames.remove(curFuncName);
                i = -1;
                continue;
            }
        }

    }
}
