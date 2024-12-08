package Fronted.Parser.SymbleTable;

import Fronted.Lexer.Token;
import Fronted.Parser.SymbleTable.Func.FuncParam;
import Fronted.Parser.SymbleTable.Func.SymFuncType;

import java.util.ArrayList;
import java.util.List;

public class FuncSymbol extends Symbol{
    protected SymFuncType funcType;
    protected List<FuncParam> funcParams;


    public FuncSymbol(Token token, SymFuncType funcType) {
        super(token);
        this.funcType = funcType;
        this.funcParams = new ArrayList<>();
    }

    public void setFuncParams(List<FuncParam> funcParams) {
        this.funcParams.addAll(funcParams);
        funcParams.clear();
    }

    public boolean isNumberMatch(int num) {
        return num == funcParams.size();
    }

    public boolean isTypeMatch(ArrayList<FuncParam> funcRParams) {
        for (int i = 0; i < funcParams.size(); i++) {
            if (!funcParams.get(i).equals(funcRParams.get(i))) {
                return false;
            }
        }
        return true;
    }

    public SymFuncType getFuncType() {
        return funcType;
    }
}
