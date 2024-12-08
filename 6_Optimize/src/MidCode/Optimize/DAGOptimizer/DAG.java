package MidCode.Optimize.DAGOptimizer;

import MidCode.BasicBlock;
import MidCode.Instructions.*;
import MidCode.Value.Value;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;

public class DAG {
    private BasicBlock basicBlock;

    private int number;
    private LinkedHashMap<Value, Node> DAGTable; // 变量-节点表格
    //private HashMap<Integer, MidNode> midNodes; // 中间节点表
    private ArrayList<MidNode> midNodes;
    static HashMap<String,DAGOp> map = new HashMap<>();
    static {
            map.put("+", DAGOp.PLUS);
            map.put("-", DAGOp.MINU);
            map.put("*", DAGOp.MULT);
            map.put("/", DAGOp.DIV);
            map.put("%", DAGOp.MOD);
            map.put("<<", DAGOp.SLL);
            map.put(">>", DAGOp.SLR);
            map.put("<", DAGOp.LSS);
            map.put("<=", DAGOp.LEQ);
            map.put(">", DAGOp.GRE);
            map.put(">=", DAGOp.GEQ);
            map.put("!", DAGOp.NOT);
            map.put("==", DAGOp.EQL);
            map.put("!=", DAGOp.NEQ);
            map.put("=", DAGOp.ASSIGN);
            map.put("[]=", DAGOp.MEMASS);
            map.put("getint", DAGOp.GETINT);
            map.put("print_int", DAGOp.PRINT_INT);
            map.put("print_str",DAGOp.PRINT_STR);
            map.put("call", DAGOp.CALL);
            map.put("ret", DAGOp.RET);
            map.put("<_jump", DAGOp.LSSJUMP);
            map.put("<=_jump", DAGOp.LEQJUMP);
            map.put(">_jump", DAGOp.GREJUMP);
            map.put(">=_jump", DAGOp.GEQJUMP);
            map.put("!_jump", DAGOp.NOTJUMP);
            map.put("==_jump", DAGOp.EQLJUMP);
            map.put("!=_jump", DAGOp.NEQJUMP);
            map.put("j", DAGOp.JUMP);
    }

    private LinkedList<MidNode> outputQueue;
    public static DAGOp getOp(String string) {
        return map.getOrDefault(string,null);
    }

    public DAG(BasicBlock basicBlock) {
        this.basicBlock = basicBlock;
        DAGTable = new LinkedHashMap<>();
        midNodes = new ArrayList<>();
        outputQueue = new LinkedList<>();
        //midNodes=new HashMap<>();
    }

    public void getOutputQueue() {
        MidNode midNode;
        while ((midNode = pickQualifiedNode())!= null) {
            outputQueue.add(midNode);
            traversal(midNode);
        }
    }

    public void traversal(MidNode midNode) {
        Node left = midNode.pop();
        if (left == null || left instanceof VarNode) {
            return;
        }
        if (((MidNode)left).isAllMomInQueue()) {
            midNodes.remove(left);
            outputQueue.add((MidNode) left);
            traversal(midNode);
        }
    }

    public MidNode pickQualifiedNode() {
        for (MidNode midNode : midNodes) {
            if (midNode.isAllMomInQueue()) {
                midNodes.remove(midNode);
                return midNode;
            }
        }
        return null;
    }

    public void construct() {
        for (Instruction instruction : basicBlock.getMidCodes()) {
            if (instruction instanceof Label ||instruction instanceof VarDef||instruction instanceof ParamDef) {
                basicBlock.addDAGInstr(instruction);
            }
            instruction.DAGoptimize(this);
        }
        getOutputQueue();
    }

    public void generateNewMidCode(){
        while (!outputQueue.isEmpty()) {
            MidNode midNode = outputQueue.pollLast();
            basicBlock.addDAGInstr(midNode.generateNewMidCode());
        }
    }


    public void parse(Value left, Value right, Value res, DAGOp op) {
        Node i = searchNode(left);
        Node j = right == null ? null : searchNode(right);

        LinkedList<Node> leaves = new LinkedList<>();
        leaves.add(i);
        if (j != null) {
            leaves.add(j);
        }

        MidNode oper = searchMidNode(new MidNode(-1,leaves,op));
        oper.vars.add(res);
        oper.announceMother();
        update(res, oper);
    }

    // a = func()
    // a = getInt()
    public Node forcedCreateNewNode(Value value) {
        VarNode varNode = new VarNode(++number);
        DAGTable.put(value, varNode);
        return varNode;
    }


    public Node searchNode(Value value) {
        if (DAGTable.containsKey(value)) {
            return DAGTable.get(value);
        } else {
            VarNode varNode = new VarNode(++number);
            DAGTable.put(value,varNode);
            //DAGTable.put(value, ++number);
            //return number;
            return varNode;
        }
    }

    public MidNode searchMidNode(MidNode midNode) {
        if (!midNodes.contains(midNode)) {
            midNode.id = ++number;
            midNodes.add(midNode);
        }
        return midNode;
    }

    public void update(Value res, Node newNum) {
        if (DAGTable.containsKey(res)) {
            DAGTable.compute(res, (key, value) -> newNum);
        } else {
            DAGTable.put(res, newNum);
        }
    }

}
