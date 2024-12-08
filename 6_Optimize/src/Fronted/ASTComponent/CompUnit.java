package Fronted.ASTComponent;

import Fronted.Parser.ParseType;
import IO.Output;
import MidCode.Value.Value;

import java.io.IOException;
import java.util.List;

public class CompUnit implements AST {
    // CompUnit → {Decl} {FuncDef} MainFuncDef
    private List<Decl> decls;
    private List<FuncDef> funcDefs;
    private MainFuncDef mainFuncDef;

    public CompUnit(List<Decl> decls, List<FuncDef> funcDefs, MainFuncDef mainFuncDef) {
        this.decls = decls;
        this.funcDefs = funcDefs;
        this.mainFuncDef = mainFuncDef;
    }

    @Override
    public void print() throws IOException {
        for (Decl decl : decls) {
            decl.print();
        }
        for (FuncDef funcDef : funcDefs) {
            funcDef.print();
        }
        mainFuncDef.print();
        Output.component(ParseType.CompUnit);
    }

    @Override
    public void generateMidCode() {
        for (Decl decl : decls) {
            decl.generateMidCode();
        }
        for (FuncDef funcDef : funcDefs) {
            funcDef.generateMidCode();
        }
        mainFuncDef.generateMidCode();
    }

    @Override
    public Value getVal() {
        return null;
    }
}
