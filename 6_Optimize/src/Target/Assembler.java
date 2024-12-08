package Target;


import IO.Output;
import MidCode.IRModule;
import MidCode.Instructions.Instruction;
import MidCode.Instructions.PrintStr;
import MidCode.Instructions.VarDef;
import Target.GRF.GRF;
import Target.Instructions.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Assembler {
    private static List<MipsCode> data = new ArrayList<>();
    private static List<MipsCode> text = new ArrayList<>();
    private static int space = 0;
    public static String generateSpaceLabel() {
        return "space" + space++;
    }
    private static int str = 0;
    public static String generateStrLabel() {
        return "str" + str++;
    }


    public static void generateMipsCode(boolean isOptimize) {

        Instruction midCode;
        // data段
        while ((midCode = IRModule.getGlobalInstr()) != null) {
            if (midCode instanceof VarDef) {
                data.addAll(midCode.toMipsCode(isOptimize));
            } else {
                text.addAll(midCode.toMipsCode(isOptimize));
            }
        }
        generateStr();
        // text段
        text.add(new Jump(InstrType.jal, new Tag("main")));
        text.add(new Load(InstrType.li, GRF.getReg("v0"),null,new Immediate(10)));
        text.add(new Syscall(InstrType.syscall));
        while ((midCode = IRModule.getInstr()) != null) {
            text.addAll(midCode.toMipsCode(isOptimize));
        }
    }

    public static void clear() {
        text.clear();
        data.clear();
        space = 0;
        str = 0;
    }

    public static void generateStr() {
        // str0: .asciiz "ssss"
        PrintStr s;
        while ((s = IRModule.getPrinted()) != null) {
            String addr = generateStrLabel();
            data.add(new Asciiz(addr, s.getString()));
            s.setAddress(addr);
        }
    }

    public static void printOld() throws IOException {
        Output.str(".data\n",false);
        for (MipsCode mipsCode : data) {
            Output.assemble(mipsCode);
        }
        Output.str(".text\n",false);
        for (MipsCode mipsCode : text) {
            Output.assemble(mipsCode);
        }
    }

    public static void printOptimize() throws IOException {
        Output.str(".data\n",true);
        for (MipsCode mipsCode : data) {
            Output.assembleOptimized(mipsCode);
        }
        Output.str(".text\n",true);
        for (MipsCode mipsCode : text) {
            Output.assembleOptimized(mipsCode);
        }
    }
    public static void printOld(FileWriter file) throws IOException {
        Output.str(".data\n",file);
        for (MipsCode mipsCode : data) {
            Output.assemble(mipsCode,file);
        }
        Output.str(".text\n",file);
        for (MipsCode mipsCode : text) {
            Output.assemble(mipsCode,file);
        }
    }
}