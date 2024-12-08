package Fronted.ASTComponent;

import Fronted.Lexer.Token;
import Fronted.Parser.ParseType;
import IO.Output;
import MidCode.IRModule;
import MidCode.Instructions.Instruction;
import MidCode.Instructions.PushParam;
import MidCode.Instructions.ValAssign;
import MidCode.MidGenerator;
import MidCode.Table.MidType;
import MidCode.Table.SymbolType;
import MidCode.Table.VarSymbol;
import MidCode.Value.IntegerValue.IntegerValue;
import MidCode.Value.Value;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FuncRParams implements AST {
    private Exp exp;
    private List<Exp> exps;
    private List<Token> commas;

    public FuncRParams(Exp exp, List<Exp> exps, List<Token> commas) {
        this.exp = exp;
        this.exps = exps;
        this.commas = commas;
    }

    public int getFuncRParamsCnt() {
        if (exp == null) {
            return 0;
        }
        return 1 + exps.size();
    }

    public void print() throws IOException {
        exp.print();
        for (int i = 0; i < exps.size(); i++) {
            commas.get(i).print();
            exps.get(i).print();
        }
        Output.component(ParseType.FuncRParams);
    }


    @Override
    public void generateMidCode() {
        /* 传参 + 压栈
         * call(a,b) =>
         * tmp1 = a;
         * tmp2 = b;
         * push a;
         * push b;
         * functionCall call
         */
        int ordinal = 0;
        int cnt = 1;
        List<Instruction> passParams = new ArrayList<>();
        List<Instruction> pushes = new ArrayList<>();
        passAndPush(passParams,pushes,exp,0);
        for (Exp exp : this.exps) {
            if (++cnt > 4) {
                ++ordinal;
            }
            passAndPush(passParams,pushes,exp,ordinal);
        }
        for(Instruction instruction : passParams) {
            IRModule.addInstr(instruction);
        }
        for (Instruction instruction:pushes) {
            IRModule.addInstr(instruction);
        }
    }

    protected ArrayList<Value> params = new ArrayList<>();

    @Override
    public Value getVal() {
        return null;
    }

    public void passAndPush(List<Instruction> passParams, List<Instruction> pushes,Exp exp,int cnt) {
        exp.generateMidCode();
        params.add(exp.val);
        if (exp.val instanceof IntegerValue) {
            String tmpParamName = MidGenerator.generateTmpName(null);
            VarSymbol tmpSym = new VarSymbol(null, tmpParamName, new MidType(SymbolType.VAR_0), false);
            tmpSym.setValue(exp.val);
            // 传参
            passParams.add(new ValAssign(tmpParamName, tmpSym, exp.val));
            // 压栈
            pushes.add(new PushParam(tmpSym,cnt));
        } else {
            pushes.add(new PushParam(exp.val,cnt));
        }
    }

    public ArrayList<Token> toTokens() {
        ArrayList<Token> tokens = new ArrayList<>();
        tokens.addAll(exp.toTokens());
        for (int i = 0; i < exps.size(); i++) {
            tokens.add(commas.get(i));
            tokens.addAll(exps.get(i).toTokens());
        }
        return tokens;
    }
}
