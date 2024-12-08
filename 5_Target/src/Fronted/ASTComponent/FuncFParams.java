package Fronted.ASTComponent;

import Fronted.Lexer.Token;
import Fronted.Parser.ParseType;
import IO.Output;
import MidCode.Instructions.ParamDef;
import MidCode.Value.Value;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FuncFParams implements AST {
    /******************中间代码用**********************/
    protected int size;
    protected List<Value> params = new ArrayList<>();
    protected List<ParamDef> paramsDef = new ArrayList<>();
    /*************************************************/
    private FuncFParam funcFParam;
    private List<FuncFParam> funcFParams;
    private List<Token> commas;
    public FuncFParams(FuncFParam funcFParam, List<FuncFParam> funcFParams, List<Token> commas) {
        this.funcFParam = funcFParam;
        this.funcFParams = funcFParams;
        this.commas = commas;
    }

    public void print() throws IOException {
        funcFParam.print();
        if (!funcFParams.isEmpty()) {
            for (int i = 0; i < funcFParams.size(); i++) {
                commas.get(i).print();
                funcFParams.get(i).print();
            }
        }
        Output.component(ParseType.FuncFParams);
    }

    @Override
    public void generateMidCode() {
        params = new ArrayList<>();
        /* 方便读写 BEGIN*/
        List<FuncFParam> _FuncFParams = new ArrayList<>();
        _FuncFParams.add(funcFParam);
        _FuncFParams.addAll(funcFParams);
        /* 方便读写 END*/
        for (FuncFParam param : _FuncFParams) {
            param.generateMidCode();
            params.add(param.param);
            paramsDef.add(param.instr);
        }
        this.size = params.size();
    }

    @Override
    public Value getVal() {
        return null;
    }
}
