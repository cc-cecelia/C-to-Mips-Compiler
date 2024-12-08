package MidCode.Instructions;

import MidCode.Table.FuncSymbol;
import Target.GRF.GRF;
import Target.Immediate;
import Target.Instructions.*;
import Target.Symbol.MipsController;

import java.util.ArrayList;
import java.util.List;

public class Function extends Instruction{
    private FuncSymbol funcSymbol;
    private Label funcDef;
    private List<ParamDef> paramDefs = new ArrayList<>();
    private List<Instruction> instructions;

    protected boolean containSubFunc = false;
    private int curStackPos=0;

    private int extraParamPos;

    public Function() {
        instructions = new ArrayList<>();
    }

    public void setFuncSymbol(FuncSymbol funcSymbol) {
        this.funcSymbol = funcSymbol;
    }

    public void setFuncDef(Label funcDef) {
        this.funcDef = funcDef;
    }

    public void setParamDefs(List<ParamDef> paramDefs) {
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

    private int tempVar=0;
    public void addTemp() {
        tempVar++;
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

    private int stackSize=0;

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

    public List<MipsCode> toMipsCode() {
        mipsCodes.addAll(funcDef.toMipsCode()); // FUNC:
        // 开栈
        if (stackSize != 0) {
            mipsCodes.add(new Alu(InstrType.addiu, GRF.getReg("sp"), GRF.getReg("sp"), new Immediate(-stackSize)));
        }
        if (containSubFunc) {
            // 我自己的约定：把ra压在栈最下面，反正他是最后弹栈的
            mipsCodes.add(new Store(InstrType.sw, GRF.getReg("ra"), new Immediate(getPos(4)), GRF.getReg("sp")));
        }
        // 从低地址往上压栈
        int i;
        for (i = 0; i < Math.min(4,paramDefs.size()); i++) {
            paramDefs.get(i).toMipsCode();
            int stackPos = getPos(4);
            MipsController.getSym(paramDefs.get(i).getSymbol()).setSpOffset(stackPos);
            mipsCodes.add(new Store(InstrType.sw,GRF.getReg("a" + i),new Immediate(stackPos),GRF.getReg("sp")));
            GRF.getReg("a" + i).release();
        }
        // 万一参数超过了4个，从栈顶拿，因为人家给你送过去了
        // i = 4
        for (; i < paramDefs.size(); i++) {
            int pos = stackSize - 4 * (i + 1 -4);
            MipsController.getSym(paramDefs.get(i).getSymbol()).setSpOffset(pos);
        }

        for (Instruction instruction : instructions) {
            mipsCodes.addAll(instruction.toMipsCode());
        }
        return mipsCodes;
    }



}
