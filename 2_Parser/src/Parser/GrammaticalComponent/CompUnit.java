package Parser.GrammaticalComponent;

import Parser.Output;
import Parser.ParseType;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

public class CompUnit {
    // CompUnit → {Decl} {FuncDef} MainFuncDef
    private List<Decl> decls;
    private List<FuncDef> funcDefs;
    private MainFuncDef mainFuncDef;

    public CompUnit(List<Decl> decls, List<FuncDef> funcDefs, MainFuncDef mainFuncDef) {
        this.decls = decls;
        this.funcDefs = funcDefs;
        this.mainFuncDef = mainFuncDef;
    }

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
}
