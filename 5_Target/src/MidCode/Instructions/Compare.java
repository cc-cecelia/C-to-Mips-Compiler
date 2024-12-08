package MidCode.Instructions;

import Fronted.ASTComponent.CmpExp;
import Fronted.ASTComponent.CmpType;
import MidCode.Table.Symbol;
import MidCode.Value.IntegerValue.IntegerValue;
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
    public List<MipsCode> toMipsCode() {
        //mipsCodes.add(new Annotation(this.toString()));
        Register rs = GRF.getSReg();
        if (left instanceof IntegerValue intLeft) {
            if (((IntegerValue) left).getDim0Value() == 0) {
                rs = GRF.getReg("0");
            } else {
                rs.setOccupied();
                mipsCodes.add(new Load(InstrType.li,rs,null,new Immediate(intLeft.getDim0Value())));
            }
        } else {
            rs.setOccupied();
            Operand leftAddr = MipsController.getAddr((Symbol) left);

            if (((Symbol) left).isGlobal()) {
                mipsCodes.add(new Load(InstrType.la, rs, null, leftAddr));
            } else {
                mipsCodes.add(new Load(InstrType.lw, rs, leftAddr, GRF.getReg("sp")));
            }
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
                mipsCodes.add(new Branch(instrType, rs, new Tag(target)));
                rs.release();
                return mipsCodes;
            } else {
                rt = GRF.getSReg();
                mipsCodes.add(new Load(InstrType.li, rt, null, new Immediate(((IntegerValue) right).getDim0Value())));
            }
        } else {
            rt = GRF.getSReg();
            Operand rightAddr = MipsController.getAddr((Symbol) right);
            if (((Symbol) right).isGlobal()) {
                mipsCodes.add(new Load(InstrType.la, rt, null, rightAddr));
            } else {
                mipsCodes.add(new Load(InstrType.lw, rt, rightAddr, GRF.getReg("sp")));
            }
        }

        mipsCodes.add(new Branch(instrType, rs, rt, new Tag(target)));
        rs.release();
        rt.release();
        return mipsCodes;
    }
}
