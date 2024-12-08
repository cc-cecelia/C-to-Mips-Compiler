package Fronted.ASTComponent;

import Fronted.Lexer.Token;
import IO.Output;
import Fronted.Parser.ParseType;
import MidCode.IRModule;
import MidCode.Instructions.MemCpy;
import MidCode.Instructions.ValAssign;
import MidCode.MidGenerator;
import MidCode.Table.MidType;
import MidCode.Table.Symbol;
import MidCode.Table.SymbolType;
import MidCode.Table.VarSymbol;
import MidCode.Value.SymbolValue;
import MidCode.Value.Value;

import java.io.IOException;

public class ForStmt implements AST {
    private LVal lVal;
    private Token assign;
    private Exp exp;

    public ForStmt(LVal lVal, Token assign, Exp exp) {
        this.lVal = lVal;
        this.assign = assign;
        this.exp = exp;
    }

    public void print() throws IOException {
        lVal.print();
        assign.print();
        exp.print();
        Output.component(ParseType.ForStmt);
    }

    @Override
    public void generateMidCode() {
        //ForStmt → LVal '=' Exp // 存在即可
        lVal.generateMidCode();
        exp.generateMidCode();
        if (lVal.val instanceof VarSymbol) {
            IRModule.addInstr(new ValAssign(((VarSymbol) lVal.val).getMidName(), (VarSymbol) lVal.val, exp.val));
        } else if (lVal.val instanceof SymbolValue symVal) {
            IRModule.addInstr(new MemCpy(symVal.getName(), symVal.getOffset(), exp.val, symVal.getSymbol()));
        }
    }

    @Override
    public Value getVal() {
        return null;
    }
}
