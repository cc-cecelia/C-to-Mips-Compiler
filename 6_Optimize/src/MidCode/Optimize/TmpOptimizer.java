package MidCode.Optimize;

import MidCode.Instructions.Calculate;
import MidCode.Instructions.Instruction;
import MidCode.Instructions.ValAssign;
import MidCode.Table.VarSymbol;
import MidCode.Value.IntegerValue.IntegerValue;

import java.util.ArrayList;
import java.util.HashSet;

public class TmpOptimizer {

    public static ArrayList<Instruction> deleteTmpVar(ArrayList<Instruction> midCodes) {
        ArrayList<Instruction> optimized = (ArrayList<Instruction>) midCodes.clone();
        for (int i = 1; i < optimized.size(); i++) {
            Instruction item1 = optimized.get(i - 1);
            Instruction item2 = optimized.get(i);

            // tmp = a; x = b + tmp;
            // tmp = 1; x = b + 1;
            if (item1 instanceof ValAssign ass1 && !ass1.isFromFunc() && ass1.isAssignTmp()) {
                    Instruction new2 = item2.reconstruct(ass1.getlSym(),ass1.getRVal());
                    if (!new2.equals(item2)) {
                        optimized.set(i, new2);
                        optimized.remove(item1);
                        i = 0;
                    }

                continue;
            }

            // tmp = a + b; c = tmp => c = a + b

            if (item2 instanceof ValAssign ass2 && ass2.isTmpAssign()){
                Instruction new1 = item1.reassign((VarSymbol) ass2.getlSym());
                if (!new1.equals(item1)) {
                    optimized.set(i-1,new1);
                    optimized.remove(item2);
                    i = 0;
                }
            }
        }
        return optimized;
    }
}
