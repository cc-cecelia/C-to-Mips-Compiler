package Fronted.ASTComponent;

import Fronted.Lexer.Token;
import IO.Output;
import Fronted.Parser.ParseType;
import MidCode.IRModule;
import MidCode.Instructions.Goto;
import MidCode.Value.BoolValue;
import MidCode.Value.IntegerValue.IntegerValue;
import MidCode.Value.Value;

import javax.management.openmbean.CompositeType;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LAndExp implements AST{
    private EqExp eqExp;
    private Token op;
    private LAndExp lAndExp;

    public LAndExp(EqExp eqExp, Token op, LAndExp lAndExp) {
        this.eqExp = eqExp;
        this.op = op;
        this.lAndExp = lAndExp;
    }

    public void print() throws IOException {
        eqExp.print();
        // <LAnd>
        Output.component(ParseType.LAndExp);
        if (op != null) {
            op.print();
            lAndExp.print();
        }
    }

    public void recombine(List<EqExp> eqExps) {
        eqExps.add(this.eqExp);
        if (op != null) {
            lAndExp.recombine(eqExps);
        }
    }

    protected Value val;



//    public void generateMidCode(String str) {
//        recombine(eqExps,ops);
//        if (ops.isEmpty()) {
//
//        } else {
//            if ()
//        }
//    }

    protected String target;
    protected String ifBlock;
    protected String elseBlock;
    protected String end;
    @Override
    public void generateMidCode() {
        List<EqExp> eqExps = new ArrayList<>();
        recombine(eqExps);
        for (int i = 0; i < eqExps.size(); i++) {
            eqExps.get(i).generateMidCode();
        }





//        recombine(eqExps,ops);
//        if (op == null) {
//            eqExp.generateMidCode();
//            this.val= eqExp.val;
//        } else {
//            eqExp.generateMidCode();
//            Value tmp1 = eqExp.val;
//            for (int i = 0; i < ops.size(); i++) {
//                EqExp eqExp = eqExps.get(i+1);
//                eqExp.generateMidCode();
//                Value eqVal = eqExp.val;
//                if (tmp1 instanceof IntegerValue intTmp && eqVal instanceof IntegerValue intEq) {
//                    int res;
//                    if (intTmp.getDim0Value() != 0 && intEq.getDim0Value() != 0) {
//                        res = 1;
//                    } else {
//                        res = 0;
//                    }
//                    tmp1 = new IntegerValue(IntegerType.DIM0,res,null);
//                } else {
//                    String tmp = MidGenerator.generateTmpName(null);
//                    VarSymbol tmpSym = new VarSymbol(this,tmp,new MidType(SymbolType.VAR_0),false, MidController.isGlobal());
//                    MidController.addNewSymbol(tmp,tmpSym);
//                    IRModule.addInstr(new Compare(tmpSym,ops.get(i).context,tmp1,eqVal));
//                    tmp1 = tmpSym;
//                }
//            }
//            this.val = tmp1;
//        }
    }

    @Override
    public Value getVal() {
        return val;
    }

    public Value recombine() {
        Value left = eqExp.recombine();
        if (this.op != null) {
            CmpType type = CmpType.AND;
            Value right = lAndExp.recombine();
            if (left instanceof IntegerValue intLeft && right instanceof IntegerValue intRight) {
                if (intLeft.getDim0Value() == 0 || intRight.getDim0Value() == 0) {
                    // 说明 (left && right == 0) || XX 那这个expr就 额 没用了嘛 就不要了
                    return new BoolValue(false);
                } else {
                    // 说明 (left && right == 1) || XX 他起决定性作用了 上一级的lor 都可以不要了
                    return new BoolValue(true);
                }
            }
            return new CmpExp(left,type,right);
        } else {
            if (left instanceof IntegerValue) {
                if (((IntegerValue) left).getDim0Value() == 0) {
                    return new BoolValue(false);
                } else {
                    return new BoolValue(true);
                }
            }
            return left;
        }
    }
}
