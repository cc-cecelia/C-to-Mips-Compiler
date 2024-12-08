package MidCode.Optimize.DAGOptimizer;

import MidCode.Instructions.*;
import MidCode.Table.FuncSymbol;
import MidCode.Table.Symbol;
import MidCode.Table.VarSymbol;
import MidCode.Value.StringValue;
import MidCode.Value.Value;

import java.util.LinkedList;

public class MidNode extends Node {
    protected LinkedList<Node> leaves;
    private DAGOp op;

    private int pos = 0;

    public MidNode(int id, LinkedList<Node> leaves, DAGOp op) {
        super(id);
        this.leaves = leaves;
        this.op = op;
    }

    public void announceMother() {
        for (Node node : leaves) {
            node.addMother(this);
        }
    }

    public boolean isAllMomInQueue() {
        return notInQueue.isEmpty();
    }

    public Node pop() {
        if (pos == leaves.size()) {
            return null;
        }
        return leaves.get(pos++);
    }

    public Instruction generateNewMidCode() {
        return
        switch (op) {
            case PLUS,MINU,MULT,DIV,MOD,SLL,SLR,LSS,LEQ,GRE,GEQ,EQL,NEQ,NOT-> new Calculate((VarSymbol) this.toValue(),op.toString(),leaves.get(0).toValue(),leaves.get(1).toValue());
            case ASSIGN -> new ValAssign((VarSymbol)this.toValue(),leaves.get(0).toValue());
            case MEMASS -> new MemCpy((Symbol) this.toValue(),leaves.get(0).toValue(),leaves.get(1).toValue());
            case CALL -> new CallFunc((FuncSymbol) leaves.get(0).toValue());
            case LSSJUMP,LEQJUMP,GREJUMP,GEQJUMP,EQLJUMP,NEQJUMP,NOTJUMP -> new Compare(leaves.get(0).toValue(),leaves.get(1).toValue(),op.toCmpType(),((StringValue)this.toValue()).getString());
            case RET -> new Return(leaves.get(0).toValue());
            case PRINT_INT -> new PrintInt(leaves.get(0).toValue());
            case PRINT_STR -> new PrintStr(leaves.get(0).toValue().toString());
            case GETINT -> new Input(leaves.get(0).toValue());
            case JUMP -> new Goto(leaves.get(0).toValue().toString());
        };
    }


    @Override
    public boolean equals(Object obj) {
        if (obj instanceof MidNode midNode) {
            if (midNode.op.equals(this.op)) {
                if (midNode.leaves.size() == this.leaves.size()) {
                    for (int i = 0; i < this.leaves.size(); i++) {
                        if (!midNode.leaves.get(i).equals(this.leaves.get(i))) {
                            return false;
                        }
                    }
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }
}
