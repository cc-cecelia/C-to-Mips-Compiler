package MidCode;

import MidCode.Instructions.CallFunc;
import MidCode.Instructions.Instruction;
import MidCode.Optimize.CalOptimizer;
import MidCode.Optimize.DAGOptimizer.DAG;
import MidCode.Optimize.TmpOptimizer;
import MidCode.Table.FuncSymbol;
import Target.Instructions.MipsCode;

import java.util.ArrayList;
import java.util.List;

public class BasicBlock extends Instruction {
    public String name;
    public boolean isGoto;
    private ArrayList<Instruction> midCodes;
    private ArrayList<BasicBlock> motherBlocks;
    private BasicBlock trueDaughter;
    private BasicBlock falseDaughter;

    private boolean optimizeSwitch = true;

    private ArrayList<Instruction> optimized;
    private ArrayList<Instruction> dagOptimize;

    private DAG dag;

    private boolean isAdd = false;

    public BasicBlock(String name) {
        this.name = name;
        midCodes = new ArrayList<>();
        motherBlocks = new ArrayList<>();
        optimized = new ArrayList<>();
        dagOptimize = new ArrayList<>();
    }

    public BasicBlock(ArrayList<Instruction> instructions) {
        this.midCodes = instructions;
    }

    public void addInstr(Instruction instruction) {
        midCodes.add(instruction);
    }

    public void addInstrs(List<Instruction> instructionList) {
        midCodes.addAll(instructionList);
    }

    public void addMother(BasicBlock block) {
        if (motherBlocks.contains(block)) {
            return;
        }
        motherBlocks.add(block);
    }

    public void addDaughter(BasicBlock block) {
        trueDaughter = block;
    }

    public void setTrueDaughter(BasicBlock trueDaughter) {
        this.trueDaughter = trueDaughter;
    }

    public void setFalseDaughter(BasicBlock falseDaughter) {
        this.falseDaughter = falseDaughter;
    }



    public ArrayList<Instruction> getMidCodes() {
        return optimized;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        //sb.append("-------------basic block " + name + "-------------" + "\n");
        for (Instruction instruction : midCodes) {
            sb.append(instruction.toString());
        }
        //sb.append("-------------" + name + " block end" + "-------------" + "\n");
        return sb.toString();
    }

    public String op2String() {
        StringBuilder sb = new StringBuilder();
        //sb.append("-------------basic block " + name + "-------------" + "\n")
        for (Instruction instruction : optimized) {
            sb.append(instruction.toString());
        }
        //sb.append("-------------" + name + " block end" + "-------------" + "\n");
        return sb.toString();
    }

    @Override
    public List<MipsCode> toMipsCode(boolean isOptimized) {
        for (Instruction instruction : optimized) {
            optimizedMips.addAll(instruction.toMipsCode(isOptimized));
        }
        return optimizedMips;
    }

    // 块内优化
    public void addDAGInstr(Instruction instruction) {
        this.dagOptimize.add(instruction);
    }

    public void DAGOptimize() {
        this.dag = new DAG(this);
        this.dag.construct();
        this.dag.generateNewMidCode();
    }

    public boolean containsFuncCall(FuncSymbol funcSymbol) {
        for (Instruction instruction : midCodes) {
            if (instruction instanceof CallFunc call) {
                if (call.getCalFunc().equals(funcSymbol)) {
                    return true;
                }
            }
        }
        return false;
    }

    public Instruction optimize() {
        if (!optimizeSwitch) {
            return new BasicBlock(midCodes);
        }

        /**********删除多余中间变量变量*********/
        optimized = TmpOptimizer.deleteTmpVar(midCodes);
        /*************************************/

        /*********减弱计算强度****************/
        optimized = CalOptimizer.optimize(optimized);

        optimized = TmpOptimizer.deleteTmpVar(optimized);
        //optimized = TmpOptimizer.deleteTmpVar(optimized);

//        for (Instruction old : midCodes) {
//            optimized.add(old.optimize());
//        }
//        DAGOptimize();
        return new BasicBlock(optimized);
    }

    @Override
    public void DAGoptimize(DAG dag) {
//        Value left = cal.getOperand1();
//        Value right = cal.getOperand2();
//        String op = cal.getOp();
//        Value res = cal.getLVal();
        //dag.parse(operand1, operand2, lVal, op);
    }
}
