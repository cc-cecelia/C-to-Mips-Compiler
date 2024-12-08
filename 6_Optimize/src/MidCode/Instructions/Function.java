package MidCode.Instructions;

import MidCode.BasicBlock;
import MidCode.Optimize.DAGOptimizer.DAG;
import MidCode.Table.FuncSymbol;
import Target.GRF.GRF;
import Target.Immediate;
import Target.Instructions.Alu;
import Target.Instructions.InstrType;
import Target.Instructions.MipsCode;
import Target.Instructions.Store;
import Target.Symbol.MipsController;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class Function extends Instruction {
    private static int globalBlockNum;
    /***********************************************************/
    protected boolean containSubFunc = false;
    private FuncSymbol funcSymbol;
    private Label funcDef;
    private ArrayList<ParamDef> paramDefs = new ArrayList<>();
    private List<Instruction> instructions;
    private int curStackPos = 0;
    /*****************优化用************************************/
    private LinkedHashMap<String, BasicBlock> basicBlocks;
    private ArrayList<BasicBlock> organized;
    private BasicBlock curBlock;
    private int blockNum;
    private int compareIndex;
    private int jumpIndex;
    private int stmtIndex;
    private BasicBlock rootBlock;
    private int tempVar = 0;
    private int stackSize = 0;
    private ArrayList<MipsCode> optimized = new ArrayList<>();

    public Function() {
        instructions = new ArrayList<>();
    }

    public void setFuncSymbol(FuncSymbol funcSymbol) {
        this.funcSymbol = funcSymbol;
    }

    public void setFuncDef(Label funcDef) {
        this.funcDef = funcDef;
    }

    public void setParamDefs(ArrayList<ParamDef> paramDefs) {
        this.paramDefs = paramDefs;
    }

    public void add(Instruction instruction) {
        this.instructions.add(instruction);
    }

    public void addAll(List<Instruction> instructions) {
        this.instructions.addAll(instructions);
    }

    public int getPos(int offset) {
        int origin = curStackPos;
        if (origin >= stackSize) {
            throw new RuntimeException("你都冲到别人的栈上了！" + offset);
        }
        curStackPos += offset;
        return origin;
    }

    public void addTemp() {
        tempVar++;
    }

    public FuncSymbol getFuncSymbol() {
        return funcSymbol;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(funcDef.toString());
        if (paramDefs != null) {
            for (ParamDef paramDef : paramDefs) {
                sb.append(paramDef.toString());
            }
        }
        for (Instruction instruction : instructions) {
            sb.append(instruction.toString());
        }
        sb.append("func_end\n");
        return sb.toString();
    }

    public void generateStackSize() {
        stackSize += 4 * paramDefs.size();
        // 局部变量我是要开栈的！
        stackSize += 4 * tempVar;
        // 超出4个的参数我要开栈的
        stackSize += paramDefs.size() > 4 ? (paramDefs.size() - 4) * 4 : 0;
        for (Instruction instruction : instructions) {
            // 局部变量 + 中间变量 size
            if (instruction instanceof Var0Def) {
                stackSize += 4;
            }
            if (instruction instanceof Var1Def var1Def) {
                stackSize += 4 * var1Def.getSize();
            }
            if (instruction instanceof Var2Def var2Def) {
                stackSize += 4 * var2Def.getSize();
            }
            if (instruction instanceof CallFunc leafFunc) {
                containSubFunc = true;
                int leafFuncParamCnt = leafFunc.getParamCnt();
                // 这里开栈空间是为了保护a0-a3，因为subCaller会覆盖a0-a3
                stackSize += leafFuncParamCnt * 4;
            }
        }
        if (containSubFunc) {
            stackSize += 4; // 存ra
        }
        funcSymbol.setStackSize(stackSize);
    }

    public int getStackSize() {
        return stackSize;
    }

    public int getParamSize() {
        return paramDefs.size();
    }

    public List<MipsCode> toMipsCode(boolean isOptimized) {
        List<MipsCode> container = isOptimized ? optimizedMips : this.mipsCodes;
        curStackPos = 0;
        container.addAll(funcDef.toMipsCode(isOptimized)); // FUNC:
        // 开栈
        if (stackSize != 0) {
            container.add(new Alu(InstrType.addiu, GRF.getReg("sp"), GRF.getReg("sp"), new Immediate(-stackSize)));
        }
        if (containSubFunc) {
            // 我自己的约定：把ra压在栈最下面，反正他是最后弹栈的
            container.add(new Store(InstrType.sw, GRF.getReg("ra"), new Immediate(getPos(4)), GRF.getReg("sp")));
        }
        // 从低地址往上压栈
        int i;
        for (i = 0; i < Math.min(4, paramDefs.size()); i++) {
            paramDefs.get(i).toMipsCode(isOptimized);
            int stackPos = getPos(4);
            MipsController.getSym(paramDefs.get(i).getSymbol()).setSpOffset(stackPos);
            container.add(new Store(InstrType.sw, GRF.getReg("a" + i), new Immediate(stackPos), GRF.getReg("sp")));
            GRF.getReg("a" + i).release();
        }
        // 万一参数超过了4个，从栈顶拿，因为人家给你送过去了
        // i = 4
        for (; i < paramDefs.size(); i++) {
            int pos = stackSize - 4 * (i + 1 - 4);
            MipsController.getSym(paramDefs.get(i).getSymbol()).setSpOffset(pos);
        }
        if (!isOptimized) {
            for (Instruction instruction : instructions) {
                container.addAll(instruction.toMipsCode(false));
            }
        } else {
            for (BasicBlock block : this.organized) {
                container.addAll(block.toMipsCode(true));
            }
        }

        return container;
    }



    /*****************代码优化用*******************/

    public void creatBasicBlock() {
        this.basicBlocks = new LinkedHashMap<>();
        this.organized = new ArrayList<>();
        curBlock = new BasicBlock(funcDef.getName());
        basicBlocks.put(funcDef.getName(),curBlock);
        rootBlock = curBlock;
        organized.add(rootBlock);
//        ArrayList<Instruction> instructions = (ArrayList<Instruction>) paramDefs.clone();
//        instructions.addAll(this.instructions);
        for (int i = 0; i < instructions.size(); i++) {
            Instruction instruction = instructions.get(i);
            if (instruction instanceof Label label) {
                String name = label.getName();
                BasicBlock newBlock;
                newBlock = basicBlocks.getOrDefault(name,new BasicBlock(name));
                basicBlocks.putIfAbsent(name,newBlock);

                organized.add(newBlock);
                curBlock.setTrueDaughter(newBlock);
                newBlock.addMother(curBlock);
                curBlock = newBlock;

            } else if (instruction instanceof Compare comp) {
                curBlock.addInstr(instruction);
                String trueLabel = comp.getTarget();
                BasicBlock targetBlock = basicBlocks.getOrDefault(trueLabel,new BasicBlock(trueLabel));
                basicBlocks.putIfAbsent(trueLabel,targetBlock);
                curBlock.setTrueDaughter(targetBlock);
                targetBlock.addMother(curBlock);
                BasicBlock falseBlock = new BasicBlock("stmt" + stmtIndex++);
                organized.add(falseBlock);
                curBlock.setFalseDaughter(falseBlock);
                falseBlock.addMother(curBlock);
                curBlock = falseBlock;
                continue;
            } else if (instruction instanceof Goto) {
                curBlock.addInstr(instruction);
                String des = ((Goto) instruction).getDes();
                BasicBlock targetBlock = basicBlocks.getOrDefault(des, new BasicBlock(des));
                basicBlocks.putIfAbsent(des,targetBlock);
                curBlock.addDaughter(targetBlock);
                targetBlock.addMother(curBlock);

                curBlock = new BasicBlock(String.valueOf(jumpIndex++));
                organized.add(curBlock);
                continue;
            } else if (instruction instanceof Return) {
                curBlock.addInstr(instruction);
                String exit = funcSymbol.getMidName() + "_Exit";
                if (basicBlocks.containsKey(exit)) {
                    curBlock.addDaughter(basicBlocks.get(exit));
                    basicBlocks.get(exit).addMother(curBlock);
                } else {
                    BasicBlock exitBlock = new BasicBlock(exit);
                    basicBlocks.put(exit,exitBlock);
                    curBlock.setTrueDaughter(exitBlock);
                    exitBlock.addMother(curBlock);
                }
                continue;
            }
            curBlock.addInstr(instruction);
        }
        //organize();
    }

    public void enterBasicBlock(BasicBlock mother, String label) {
        if (basicBlocks.containsKey(label)) {
            curBlock = basicBlocks.get(label);
            //curBlock.addMother(mother);
        } else {
            BasicBlock block = new BasicBlock(label);
            basicBlocks.put(label, block);
            curBlock = block;
            if (mother == null) {
                rootBlock = curBlock;
                return;
            }
            curBlock.addMother(mother);
            mother.addDaughter(curBlock);
        }
        blockNum++;
        globalBlockNum++;
    }


    public ArrayList<BasicBlock> getOrganized() {
        return organized;
    }

    public List<Instruction> getInstructions() {
        return instructions;
    }

    @Override
    public Instruction optimize() {
        creatBasicBlock();
        for (BasicBlock block : organized) {
            block.optimize();
        }
        return null;
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
