package MidCode.Instructions;

import Fronted.ASTComponent.CmpType;
import MidCode.Optimize.DAGOptimizer.DAG;
import MidCode.Table.Symbol;
import MidCode.Table.VarSymbol;
import MidCode.Value.IntegerValue.IntegerValue;
import MidCode.Value.StringValue;
import MidCode.Value.SymbolValue;
import MidCode.Value.Value;
import Target.GRF.GRF;
import Target.GRF.Register;
import Target.Immediate;
import Target.Instructions.*;
import Target.Operand;
import Target.Symbol.MipsController;
import Target.Tag;

import java.util.List;

public class Compare extends Instruction {
    private Value left;
    private Value right;
    private CmpType type;
    private String target;

    public Compare(Value left, Value right, CmpType type, String target) {
        this.left = left;
        this.right = right;
        this.type = type;
        this.target = target;
    }

    @Override
    public String toString() {
        return "cmp " + left + " " + right + "\n" +
                type + " " + target + "\n";

    }

    @Override
    public List<MipsCode> toMipsCode(boolean isOptimized) {
        //mipsCodes.add(new Annotation(this.toString()));
        List<MipsCode> container = isOptimized ? optimizedMips : this.mipsCodes;


        Register rs = GRF.getSReg();
        if (left instanceof IntegerValue intLeft) {
            if (((IntegerValue) left).getDim0Value() == 0) {
                rs = GRF.getReg("0");
            } else {
                rs.setOccupied();
                container.add(new Load(InstrType.li, rs, null, new Immediate(intLeft.getDim0Value())));
            }
        } else {
            rs.setOccupied();
            getOperand(left,rs,container);

//            Operand leftAddr = MipsController.getAddr((Symbol) left);
//
//            if (((Symbol) left).isGlobal()) {
//                container.add(new Load(InstrType.la, rs, null, leftAddr));
//                container.add(new Load(InstrType.lw,rs,null ,rs));
//            } else {
//                container.add(new Load(InstrType.lw, rs, leftAddr, GRF.getReg("sp")));
//            }
        }

        InstrType instrType = switch (type) {
            case NEQ -> InstrType.bne;
            case EQL -> InstrType.beq;
            case GRE -> InstrType.bgt;
            case GEQ -> InstrType.bge;
            case LSS -> InstrType.blt;
            case LEQ -> InstrType.ble;
            default -> throw new IllegalStateException("Unexpected value: " + type);
        };
        Register rt;
        if (right instanceof IntegerValue) {
            if (((IntegerValue) right).getDim0Value() == 0) {
                instrType = switch (instrType) {
                    case bne -> InstrType.bnez;
                    case beq -> InstrType.beqz;
                    case blt -> InstrType.bltz;
                    case ble -> InstrType.blez;
                    case bgt -> InstrType.bgtz;
                    case bge -> InstrType.bgez;
                    default -> throw new IllegalStateException("Unexpected value: " + instrType);
                };
                container.add(new Branch(instrType, rs, new Tag(target)));
                rs.release();
                return container;
            } else {
                rt = GRF.getSReg();
                container.add(new Load(InstrType.li, rt, null, new Immediate(((IntegerValue) right).getDim0Value())));
            }
        } else {
            rt = GRF.getSReg();
            rt.setOccupied();
            getOperand(right,rt,container);
//            Operand rightAddr = MipsController.getAddr((Symbol) right);
//            if (((Symbol) right).isGlobal()) {
//                container.add(new Load(InstrType.la, rt, null, rightAddr));
//                container.add(new Load(InstrType.lw,rt,null,rt));
//            } else {
//                container.add(new Load(InstrType.lw, rt, rightAddr, GRF.getReg("sp")));
//            }
        }

        container.add(new Branch(instrType, rs, rt, new Tag(target)));
        rs.release();
        rt.release();
        return container;
    }

    @Override
    public Instruction optimize() {
        return this;
    }

    @Override
    public void DAGoptimize(DAG dag) {
        String op = type.toString() + "_jump";
        Value res = new StringValue(target);

        dag.parse(left, right, res, DAG.getOp(op));
    }

    public String getTarget() {
        return target;
    }

    public String getType() {
        return type.toString();
    }

    public Value getLeft() {
        return left;
    }

    public Value getRight() {
        return right;
    }

    @Override
    public Instruction reconstruct(Value tmp, Value newA) {

        // tmp = a; x = b + tmp;
        Value newOp1 = this.left;
        Value newOp2 = this.right;
        boolean flag = false;
        if (left.equals(tmp)) {
            flag = true;
            newOp1 = newA;
        }
        if (right.equals(tmp)) {
            flag = true;
            newOp2 = newA;
        }

        if (flag) {
            return new Compare(newOp1,newOp2,this.type,this.target);
        } else {
            return this;
        }
    }

}
