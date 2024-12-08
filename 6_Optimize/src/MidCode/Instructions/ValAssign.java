package MidCode.Instructions;

import MidCode.Optimize.DAGOptimizer.DAG;
import MidCode.Optimize.DAGOptimizer.DAGOp;
import MidCode.Optimize.DAGOptimizer.MidNode;
import MidCode.Optimize.DAGOptimizer.Node;
import MidCode.Table.FuncSymbol;
import MidCode.Table.Symbol;
import MidCode.Table.VarSymbol;
import MidCode.Value.IntegerValue.IntegerValue;
import MidCode.Value.SymbolValue;
import MidCode.Value.Value;
import Target.GRF.GRF;
import Target.GRF.Register;
import Target.Immediate;
import Target.Instructions.*;
import Target.Operand;
import Target.Symbol.MipsController;
import Target.Symbol.MipsSymbol;

import java.util.LinkedList;
import java.util.List;

public class ValAssign extends Instruction {
    private String LVal;
    private Symbol lSym;
    private Value RVal;
    private boolean isFromFunc;

    public ValAssign(String LVal, Symbol lSym, Value RVal) {
        this.LVal = LVal;
        this.RVal = RVal;
        this.lSym = lSym;

        if (RVal instanceof  FuncSymbol) {
            isFromFunc = true;
        }
        if (((VarSymbol)lSym).isConst()) {

        }
        if (RVal instanceof IntegerValue) {
            lSym.setValue(RVal);
        } else if (RVal instanceof VarSymbol) {
            if (((VarSymbol) RVal).isConst()) {
                lSym.setValue(((VarSymbol) RVal).getValue());
            } else {
                lSym.setValue(RVal);
            }
        } else if (RVal instanceof SymbolValue memRVal) {
            if (((VarSymbol)memRVal.getSymbol()).isConst()) {
                lSym.setValue(memRVal.getValue());
            }
        } else {
            lSym.setValue(RVal);
        }
    }

    public ValAssign(Symbol leftSym,Value RVal) {
        this.lSym = leftSym;
        this.RVal = RVal;
        this.LVal = leftSym.getMidName();
    }

    @Override
    public String toString() {
        //return "=, " + RVal.toString() + ", , " + LVal + "\n";
        return LVal + " = " + RVal.toString() + "\n";
    }

    public Register localToMips(boolean isOptimized) {
        List<MipsCode> container = isOptimized ? optimizedMips : this.mipsCodes;

        Register rReg = GRF.getTReg();
        rReg.setOccupied();
        if (RVal instanceof SymbolValue rVal && rVal.isParam()) {
            Operand rValBase = MipsController.getAddr(rVal.getSymbol());
            container.add(new Load(InstrType.lw, rReg, rValBase,GRF.getReg("sp"))); // rReg = A*
            if (rVal.getOffset() instanceof IntegerValue integer) {
                container.add(new Alu(InstrType.addiu,rReg,rReg,new Immediate(4 * integer.getDim0Value())));
                container.add(new Load(InstrType.lw,rReg,null,rReg));
            } else if (rVal.getOffset() instanceof VarSymbol offSym) {
                Register off = GRF.getTReg();
                off.setOccupied();
                Operand offAddr = MipsController.getAddr(offSym);
                if (offSym.isGlobal()) {
                    container.add(new Load(InstrType.la, off,null,offAddr));
                    container.add(new Load(InstrType.lw,off,null,off));
                } else {
                    container.add(new Load(InstrType.lw,off,offAddr,GRF.getReg("sp")));
                }
                container.add(new Alu(InstrType.sll,off,off,new Immediate(2)));
                container.add(new Alu(InstrType.addu,rReg,rReg,off));
                container.add(new Load(InstrType.lw,rReg,null,rReg));
                off.release();
            } else if (rVal.getOffset() == null) {
              // 传地址，之前穿过了
              //mipsCodes.add(new Load(InstrType.lw,rReg,rValBase,GRF.getReg("sp")));
            } else {
                throw new IllegalStateException("偏移量还能是 " + rVal.getOffset().getClass());
            }
            return rReg;
        }
        if (RVal instanceof SymbolValue rVal) {
            Operand rValBase = MipsController.getAddr(rVal.getSymbol()); // 数组[0]在sp的地址
            if (rVal.getOffset() instanceof IntegerValue integer) {
                int off = integer.getDim0Value();
                int totalOff = ((Immediate) rValBase).getNumber() + 4 * off;
                container.add(new Load(InstrType.lw, rReg, new Immediate(totalOff), GRF.getReg("sp")));
            } else if (rVal.getOffset() instanceof VarSymbol offSym) {
                Register off = GRF.getTReg();
                off.setOccupied();
                Operand offAddr = MipsController.getAddr(offSym);
                if (offSym.isGlobal()) {
                    container.add(new Load(InstrType.la, off,null,offAddr));
                    container.add(new Load(InstrType.lw,off,null,off));
                } else {
                    container.add(new Load(InstrType.lw,off,offAddr,GRF.getReg("sp")));
                }
                container.add(new Alu(InstrType.sll,off,off,new Immediate(2)));
                container.add(new Alu(InstrType.addiu,rReg,off,rValBase));
                container.add(new Alu(InstrType.addu,rReg,rReg,GRF.getReg("sp")));
                container.add(new Load(InstrType.lw,rReg,null,rReg));
                off.release();
            } else if (rVal.getOffset() == null) {
                // 传地址了老铁
                container.add(new Alu(InstrType.addiu,rReg,GRF.getReg("sp"),MipsController.getAddr(rVal.getSymbol())));
            }else {
                throw new IllegalStateException("偏移量还能是 " + rVal.getOffset().getClass());
            }
        } else if (RVal instanceof VarSymbol rVal) {
            // lw $t0 4($sp)
            container.add(new Load(InstrType.lw, rReg,
                    MipsController.getAddr(rVal),
                    GRF.getReg("sp")));
        } else {
            System.out.println(1);
            throw new IllegalStateException("不是数字，也不是变量，也不是函数，还能是啥" + RVal.getClass());
        }
        return rReg;
    }

    public Register globalToMips(boolean isOptimized) {
        List<MipsCode> container = isOptimized ? optimizedMips : this.mipsCodes;

        Register rReg = GRF.getSReg();
        rReg.setOccupied();
        if (RVal instanceof SymbolValue rVal) {
            Operand rValBase = MipsController.getAddr(rVal.getSymbol());
            if (rVal.getOffset() instanceof IntegerValue integer) {
                container.add(new Load(InstrType.la, rReg, null, rValBase));
                container.add(new Load(InstrType.lw,rReg,new Immediate(4*integer.getDim0Value()),rReg));
            } else if (rVal.getOffset() instanceof VarSymbol offSym) {
                Register off = GRF.getTReg();
                off.setOccupied();
                Operand offAddr = MipsController.getAddr(offSym);
                container.add(new Load(InstrType.la, rReg, null, rValBase));
                if (offSym.isGlobal()) {
                    container.add(new Load(InstrType.la, off, null, offAddr)); // off <= index的地址
                    container.add(new Load(InstrType.lw, off, null, off)); // off <= index
                    container.add(new Alu(InstrType.sll, off, off, new Immediate(2))); // off <= offset
                    container.add(new Alu(InstrType.addu, rReg, rReg, off)); // rreg <= leftVa
                    container.add(new Load(InstrType.lw, rReg, null, rReg));
                } else {
                    container.add(new Load(InstrType.lw, off,offAddr, GRF.getReg("sp"))); // off <= index
                    container.add(new Alu(InstrType.sll, off, off, new Immediate(2)));
                    container.add(new Alu(InstrType.addu,rReg,rReg,off));
                    container.add(new Load(InstrType.lw, rReg, null, rReg));
                }
                off.release();
            } else if (rVal.getOffset() == null) {
                container.add(new Load(InstrType.la,rReg,null,MipsController.getAddr(rVal.getSymbol())));
            }else {
                throw new IllegalStateException("偏移量还能是 " + rVal.getOffset().getClass());
            }
        } else if (RVal instanceof VarSymbol rVal) {
            Operand rValAddr = MipsController.getAddr(rVal);
            container.add(new Load(InstrType.la, rReg, null, rValAddr));
            container.add(new Load(InstrType.lw, rReg, null, rReg));
        } else {
            System.out.println(1);
            throw new IllegalStateException("不是数字，也不是变量，也不是函数，还能是啥" + RVal.getClass());
        }
        return rReg;
    }

    public List<MipsCode> toMipsCode(boolean isOptimized) {
        List<MipsCode> container = isOptimized ? optimizedMips : this.mipsCodes;

        container.add(new Annotation(this.toString()));
        // getRVal
        Register rReg;
        if (RVal instanceof IntegerValue) {
            // a = 1
            int integer = ((IntegerValue) RVal).getDim0Value();
            // li $t1 1
            rReg = GRF.getTReg();
            container.add(new Load(InstrType.li, rReg, null, new Immediate(integer)));
            // sw $t1, 0($sp)
        } else if (RVal instanceof SymbolValue val) {
            if (val.isGlobal()) {
                rReg = globalToMips(isOptimized);
            } else {
                rReg = localToMips(isOptimized);
            }
        } else if (RVal instanceof VarSymbol val) {
            if (val.isGlobal()) {
                rReg = globalToMips(isOptimized);
            } else {
                rReg = localToMips(isOptimized);
            }
        } else if (RVal instanceof FuncSymbol) {
            rReg = GRF.getTReg();
            container.add(new Move(InstrType.move, rReg, GRF.getReg("v0")));
        } else {
            throw new IllegalStateException("RVAl 还能是什么类型啊！" + RVal.getClass());
        }
        // getLVal
        MipsSymbol lVal = MipsController.getSym(lSym);
        if (lSym.isGlobal()) {
            Register s = GRF.getSReg();
            container.add(new Load(InstrType.la, s, null, lVal.getAddrLabel()));
            container.add(new Store(InstrType.sw, rReg, null, s));
        } else {
            int lValSpOffset = lVal.getSpOffset();
            container.add(new Store(InstrType.sw, rReg, new Immediate(lValSpOffset), GRF.getReg("sp")));
        }
        rReg.release();
        return container;
    }

    /*************************代码优化用********************/
    @Override
    public Instruction optimize() {
        return this;
    }

    public boolean isFromFunc() {
        return isFromFunc;
    }

    public boolean isAssignTmp() {
        return ((VarSymbol)lSym).isTmp();
    }

    public boolean isTmpAssign() {
        return RVal instanceof VarSymbol var && var.isTmp();
    }
    @Override
    public void DAGoptimize(DAG dag) {
        DAGOp op = DAG.getOp("=");
        if (RVal instanceof FuncSymbol) {
            Node i = dag.forcedCreateNewNode(RVal);
            LinkedList<Node> leaves = new LinkedList<>();
            leaves.add(i);
            MidNode oper = dag.searchMidNode(new MidNode(-1,leaves,op));
            oper.announceMother();
            oper.addUser(lSym);
            dag.update(lSym,oper);
        } else if (RVal instanceof SymbolValue) {
            dag.parse(((SymbolValue) RVal).getSymbol(),((SymbolValue) RVal).getOffset(), lSym,op);
        } else {
            dag.update(lSym,dag.searchNode(RVal));

            //dag.parse(RVal, null, lSym, op);
        }
    }

    public Symbol getlSym() {
        return lSym;
    }

    public Value getRVal() {
        return RVal;
    }

    @Override
    public Instruction reconstruct(Value tmp, Value newA) {
        // tmp = a; b = tmp;
        if (RVal instanceof VarSymbol var && var.equals(tmp)) {
            return new ValAssign(this.LVal,this.lSym,newA);
        } if (RVal instanceof SymbolValue var) {
            // tmp = a b = c[tmp]
            if (tmp instanceof IntegerValue || tmp instanceof VarSymbol) {
                if (var.getOffset() == null) {
                    return this;
                }
                if (var.getOffset().equals(tmp)) {
                    SymbolValue symbolValue = new SymbolValue(var.getSymbol(),newA,var.getDim());
                    return new ValAssign(this.LVal,this.lSym,symbolValue);
                } else {
                    return this;
                }
            } else {
                return this;
            }
        }
        // tmp =
        else {
            return this;
        }
    }

}
