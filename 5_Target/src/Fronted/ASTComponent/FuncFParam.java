package Fronted.ASTComponent;

import Fronted.Lexer.Token;
import Fronted.Parser.ParseType;
import IO.Output;
import MidCode.Instructions.ParamDef;
import MidCode.MidGenerator;
import MidCode.Table.MidController;
import MidCode.Table.MidType;
import MidCode.Table.SymbolType;
import MidCode.Table.VarSymbol;
import MidCode.Value.Value;

import java.io.IOException;
import java.util.List;

public class FuncFParam implements AST {
    /*****************中间代码用**************/
    protected Value param;
    protected ParamDef instr;
    /****************************************/
    private BType BType;
    private Token ident;
    private Token lBrack;
    private Token rBrack;
    private List<Token> lBracks;
    private List<Token> rBracks;
    private List<ConstExp> constExps;

    public FuncFParam(BType BType, Token ident, Token lBrack, Token rBrack, List<Token> lBracks, List<Token> rBracks, List<ConstExp> constExps) {
        this.BType = BType;
        this.ident = ident;
        this.lBrack = lBrack;
        this.rBrack = rBrack;
        this.lBracks = lBracks;
        this.rBracks = rBracks;
        this.constExps = constExps;
    }

    public int getDim() {
        if (lBrack == null) {
            return 0;
        } else {
            return 1 + lBracks.size();
        }
    }

    public void print() throws IOException {
        BType.print();
        ident.print();
        if (lBrack != null) {
            lBrack.print();
            rBrack.print();
            if (!constExps.isEmpty()) {
                for (int i = 0; i < constExps.size(); i++) {
                    lBracks.get(i).print();
                    constExps.get(i).print();
                    rBracks.get(i).print();
                }
            }
        }
        Output.component(ParseType.FuncFParam);
    }

    @Override
    public void generateMidCode() {
        String midName = MidGenerator.generateTmpName(ident.context);
        MidType type;
        int dim = getDim();
        type = switch (dim) {
            case 0 -> new MidType(SymbolType.VAR_0);
            case 1 -> new MidType(SymbolType.VAR_1);
            case 2 -> new MidType(SymbolType.VAR_2);
            default -> throw new IllegalStateException("不应该出现的维数！");
        };
        VarSymbol symbol = new VarSymbol(this, midName, type, false);
        symbol.setParam();
        if (dim == 2) {
            symbol.setSize(0, constExps.get(0).getCalculable());
        }
        // 加入符号表
        MidController.addNewSymbol(ident.context, symbol);
        this.param = symbol;
        // 生成中间代码；
        instr = new ParamDef(ident.context, symbol);
    }

    @Override
    public Value getVal() {
        return null;
    }
}
