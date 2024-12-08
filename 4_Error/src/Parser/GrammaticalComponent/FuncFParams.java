package Parser.GrammaticalComponent;

import Lexer.Token;
import IO.Output;
import Parser.ParseType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FuncFParams {
    private FuncFParam funcFParam;
    private List<FuncFParam> funcFParams = new ArrayList<>();
    private List <Token> commas = new ArrayList<>();

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
}
