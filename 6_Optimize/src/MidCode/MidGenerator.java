package MidCode;

import Fronted.ASTComponent.AST;
import MidCode.Instructions.Function;

import java.util.HashMap;

public class MidGenerator {
    public static AST CompUnit;
    public static HashMap<String, Integer> tmpName;
    public static String temp = "temp";
    static {
        tmpName = new HashMap<>();
        tmpName.put(temp,1);
    }
    public static int ifIndex;
    public static int tmpLabelIndex;
    public static String generateTmpName(String ident) {
        if (ident == null) { // 生成临时指令
            IRModule.getCurFunc().addTemp();
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

    public static String generateIfLabel() {
        return "if" + ++ifIndex;
    }

    private static int forIndex;

    public static String generateForLabel() {
        return "for" + ++forIndex;
    }

    public static String generateTmpLabel() {
        return "tmpLabel" + tmpLabelIndex++;
    }

    static int orLabel;
    public static String generateOrLabel() {
        return "OR" + ++orLabel;
    }

    static int blockLabel;
    public static String generateBlockLabel() {
        return "Block_" + blockLabel++;
    }
    public static void generateMidCode() {
        CompUnit.generateMidCode();
        setStack4Func();
    }

    public static void setStack4Func() {
        for (Function function : IRModule.functions.values()) {
            function.generateStackSize();
        }
    }

}
