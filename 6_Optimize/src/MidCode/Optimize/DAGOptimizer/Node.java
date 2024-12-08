package MidCode.Optimize.DAGOptimizer;

import MidCode.Value.Value;

import java.util.ArrayList;
import java.util.LinkedList;

public abstract class Node {
    /**********代码导出用*************/
    protected LinkedList<Node> notInQueue;

    protected ArrayList<Node> mothers;
    protected int id;
    protected ArrayList<Value> vars; // 在这个节点上的变量

    public Node(int id) {
        this.id = id;
        this.vars = new ArrayList<>();
        this.mothers = new ArrayList<>();
        this.notInQueue = new LinkedList<>();
    }

    public void addMother(Node node) {
        mothers.add(node);
        notInQueue.add(node);
    }

    public void motherInQueue(Node mother) {
        boolean success = notInQueue.remove(mother);
        assert (success) : "没你这个妈！";
    }

    public void addUser(Value value) {
        vars.add(value);
    }

    public Value toValue() {
        return vars.get(0);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Node node) {
            return this.id == node.id;
        }
        return false;
    }
}
