package MidCode.Instructions;

import MidCode.Optimize.DAGOptimizer.DAG;
import MidCode.Value.StringValue;
import MidCode.Value.Value;
import MidCode.Value.VoidValue;
import Target.GRF.GRF;
import Target.Immediate;
import Target.Instructions.*;
import Target.Tag;

import java.util.List;

public class PrintStr extends Print{
    private final String string;
    private String address;

    public PrintStr(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }

    @Override
    public String toString() {
        return "print_str, " + string + "\n";
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<MipsCode> toMipsCode(boolean isOptimized) {
        List<MipsCode> container = isOptimized ? optimizedMips : this.mipsCodes;

        container.add(new Annotation(this.toString()));

        container.add(new Load(InstrType.li,GRF.getReg("v0"),null,new Immediate(4)));
        container.add(new Load(InstrType.la, GRF.getReg("a0"),null,new Tag(address)));
        container.add(new Syscall(InstrType.syscall));
        return container;
    }


    @Override
    public Value getValue() {
        return new StringValue(string);
    }

    @Override
    public void DAGoptimize(DAG dag) {
        String op = "print_str";
        Value res = new VoidValue();
        dag.parse(getValue(),null,res, DAG.getOp(op));
    }
}
