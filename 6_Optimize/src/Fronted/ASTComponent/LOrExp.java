package Fronted.ASTComponent;

import Fronted.Lexer.Token;
import Fronted.Parser.ParseType;
import IO.Output;
import MidCode.IRModule;
import MidCode.Instructions.Goto;
import MidCode.Instructions.Label;
import MidCode.MidGenerator;
import MidCode.Value.BoolValue;
import MidCode.Value.Value;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LOrExp implements AST {
    protected Value val;
    private LAndExp lAndExp;
    private Token op;
    private LOrExp lOrExp;

    public LOrExp(LAndExp lAndExp, Token op, LOrExp lOrExp) {
        this.lAndExp = lAndExp;
        this.op = op;
        this.lOrExp = lOrExp;
    }

    public void print() throws IOException {
        lAndExp.print();
        Output.component(ParseType.LOrExp);
        if (op != null) {
            op.print();
            lOrExp.print();
        }
    }

    public void recombine(List<LAndExp> lAndExps) {
        lAndExps.add(this.lAndExp);
        if (op != null) {
            lOrExp.recombine(lAndExps);
        }
    }

    public Value recombine() {
        Value left = lAndExp.recombine();
        if (op != null) {
            Value right = lOrExp.recombine();
            if (left instanceof BoolValue) {
                if (((BoolValue) left).getRes()) {
                    // 说明 哥们 你这个全都是真的了 那这个分支我就不要了
                    return new BoolValue(true);
                } else  {
                    // 说明这个left我可以不要了 因为是假的
                    left = null;
                }
            }
            if (right instanceof BoolValue) {
                if (((BoolValue) right).getRes()) {
                    return new BoolValue(true);
                } else {
                    if (left == null) {
                        // 已经有一个假的了 新的这个还是假的
                        return new BoolValue(false);
                    } else {
                        // left 是个变量还不咋地
                        return left;
                    }
                }
            }
            return new CmpExp(left,CmpType.OR,right);
        } else {
            if (left instanceof BoolValue){
                if (((BoolValue) left).getRes()) {
                    return new BoolValue(true);
                } else {
                    return new BoolValue(false);
                }
            }
            return left;
        }
    }

    protected String ifBlock;
    protected String elseBlock;
    protected String end;
    boolean hasElse;
    @Override
    public void generateMidCode() {
        List<LAndExp> lAndExps = new ArrayList<>();
        recombine(lAndExps);
       for (int i = 0; i < lAndExps.size(); i++) {
           String target = MidGenerator.generateOrLabel();
            lAndExps.get(i+1).target =  target;
            lAndExps.get(i+1).generateMidCode();
            IRModule.addInstr(new Label(target));
        }
        // 到这都没跳转啊 那你就是假的！那你要不然就去ifend 要不然就去elseblock
        if (hasElse) {
            IRModule.addInstr(new Goto(elseBlock));
        } else {
            IRModule.addInstr(new Goto(end));
        }
    }

    @Override
    public Value getVal() {
        return null;
    }
}
