package MidCode.Instructions;

import MidCode.Optimize.DAGOptimizer.DAG;
import MidCode.Table.VarSymbol;
import MidCode.Value.IntegerValue.IntegerValue;
import MidCode.Value.Value;
import MidCode.Value.VoidValue;
import Target.GRF.GRF;
import Target.Immediate;
import Target.Instructions.*;

import java.util.List;

public class PrintInt extends Print{
    private Value value;

    public PrintInt(Value value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "print_int, " + value.toString() + "\n";
    }

    public List<MipsCode> toMipsCode(boolean isOptimized) {
        List<MipsCode> container = isOptimized ? optimizedMips : this.mipsCodes;

        container.add(new Annotation(this.toString()));

        container.add(new Load(InstrType.li, GRF.getReg("v0"),null, new Immediate(1)));




        if (value instanceof IntegerValue integer) {
            container.add(new Load(InstrType.li,GRF.getReg("a0"),null,new Immediate(integer.getDim0Value())));
        } else {
            getOperand(value,GRF.getReg("a0"),container);
        }


//        else if (value instanceof VarSymbol symbol) {
//            Operand addr = MipsController.getAddr(symbol);
//            if (symbol.isGlobal()) {
//                container.add(new Load(InstrType.la,GRF.getReg("a0"),null,addr));
//                container.add(new Load(InstrType.lw,GRF.getReg("a0"),null,GRF.getReg("a0")));
//            } else {
//                container.add(new Load(InstrType.lw, GRF.getReg("a0"), addr, GRF.getReg("sp")));
//            }
//        }
        container.add(new Syscall(InstrType.syscall));
        return container;
    }

    public Value getValue() {
        return value;
    }

    @Override
    public void DAGoptimize(DAG dag) {
        String op = "print_int";
        Value res = new VoidValue();
        dag.parse(value,null,res, DAG.getOp(op));
    }

    @Override
    public Instruction reconstruct(Value tmp, Value newA) {
        if (value instanceof VarSymbol var && var.equals(tmp)) {
            return new PrintInt(newA);
        } else {
            return this;
        }
    }

}
