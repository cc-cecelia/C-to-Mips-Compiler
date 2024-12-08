package Fronted.Parser.SymbleTable.Func;

import java.util.ArrayList;

public class CallingTable {
    protected int id;
    protected Integer fatherId;
    protected ArrayList<FuncParam> rParams;

    public CallingTable(int id, Integer fatherId) {
        this.id = id;
        this.fatherId = fatherId;
        rParams = new ArrayList<>();
    }

    public void addParam(FuncParam param) {
        rParams.add(param);
    }

    public Integer getFatherId() {
        return fatherId;
    }

    public ArrayList<FuncParam> getrParams() {
        return rParams;
    }
}
