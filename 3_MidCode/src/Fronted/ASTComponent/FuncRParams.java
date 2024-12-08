package Fronted.ASTComponent;

import Fronted.Lexer.Token;
import IO.Output;
import Fronted.Parser.ParseType;
import MidCode.Generator;
import MidCode.IRModule;
import MidCode.Instructions.Instruction;
import MidCode.Instructions.PushParam;
import MidCode.Instructions.ValAssign;
import MidCode.Table.MidType;
import MidCode.Table.SymbolType;
import MidCode.Table.VarSymbol;
import MidCode.Value.Value;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

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
        Stack<Instruction> passParams = new Stack<>();
        Stack<Instruction> pushes = new Stack<>();
        passAndPush(passParams,pushes,exp);
        for (Exp exp : this.exps) {
            passAndPush(passParams,pushes,exp);
        }
        while (!passParams.isEmpty()) {
            IRModule.addInstr(passParams.pop());
        }
        while (!pushes.isEmpty()) {
            IRModule.addInstr(pushes.pop());
        }

    }

    public void passAndPush(Stack<Instruction> passParams, Stack<Instruction> pushes,Exp exp) {
        exp.generateMidCode();
        String tmpParamName = Generator.generateTmpName(null);
        VarSymbol tmpSym = new VarSymbol(null, tmpParamName, new MidType(SymbolType.VAR_0),false);
        //Value tmpParam = exp.val.clone();
        tmpSym.setValue(exp.val);
        // TODO:不确定tmpSym是不是要加入符号表。。
        // 传参
        passParams.add(new ValAssign(tmpParamName, exp.val));
        // 压栈
        pushes.add(new PushParam(tmpSym));
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
