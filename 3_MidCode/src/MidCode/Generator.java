package MidCode;

import Fronted.ASTComponent.AST;
import MidCode.Instructions.Instruction;

import java.util.HashMap;

public class Generator {
    public static AST CompUnit;
    public static HashMap<String, Integer> tmpName;
    public static String temp = "temp";
    static {
        tmpName = new HashMap<>();
        // TODO:记得想一个所有临时指令都叫的名字
        tmpName.put(temp,1);
    }

    public static String generateTmpName(String ident) {
        if (ident == null) { // 生成临时指令
            String tmp = temp + "_" + tmpName.get(temp);
            tmpName.compute(temp,(key,value) -> value + 1);
            return tmp;
        }

        if (!tmpName.containsKey(ident)) {
            tmpName.put(ident,1);
        }
        String tmp = ident + "_" + tmpName.get(ident);
        tmpName.compute(ident,(key,value) -> value + 1);
        return tmp;
    }

    public static void generateMidCode() {
        CompUnit.generateMidCode();
    }
}
