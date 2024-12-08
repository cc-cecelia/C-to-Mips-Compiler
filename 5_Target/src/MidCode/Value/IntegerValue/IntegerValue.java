package MidCode.Value.IntegerValue;

import MidCode.Value.Value;
import Target.GRF.GRF;
import Target.GRF.Register;
import Target.Immediate;
import Target.Instructions.InstrType;
import Target.Instructions.Load;
import Target.Instructions.MipsCode;

import java.util.ArrayList;
import java.util.List;

public class IntegerValue extends Value {
//    private IntegerValue dim0Value;
//    private IntegerValue[] dim1Value;
//    private IntegerValue[] dim2Value;
    private int dim0Value;
    private List<Integer> dim1Value;
    //private int[] dim1Value;
    private List<List<Integer>> dim2Value;
    //private int[][] dim2Value;

    private IntegerType type;
    private int size1;
    private int size2;

    public IntegerValue(IntegerType type,Integer i,Integer j) {
        switch (type) {
            case DIM0 -> dim0Value = i;
            case DIM1 -> {
                this.dim1Value = new ArrayList<>();
                this.size1 = i;
            }
            case DIM2 -> {
                this.dim2Value = new ArrayList<>();
                this.size1 = i;
                this.size2= j;
            }
        }
        this.type = type;
    }

    public IntegerValue(int i) {
        this.type = IntegerType.DIM0;
        this.dim0Value = i;
    }

    public int getDim1Size() {
        return this.dim1Value.size();
    }

    public int getDim2Size1() {
        return this.dim2Value.size();
    }
    public int getDim2Size2(int i) {
        return this.dim2Value.get(i).size();
    }

    public int getDim0Value() {
        return dim0Value;
    }

    public int getDim1Value(int pos) {
        return dim1Value.get(pos);
    }

    public int getDim2Value(int i, int j) {
        return dim2Value.get(i).get(j);
    }

    public void setDim1(List<Integer> src) {
        this.dim2Value.add(new ArrayList<>());
        this.dim2Value.get(this.dim2Value.size()-1).addAll(src);
    }

    public List<Integer> getDim1() {
        return dim1Value;
    }

    public void setDim1(Integer src) {
        this.dim1Value.add(src);
    }


    @Override
    public String toString() {
        return switch (type) {
            case DIM0 -> String.valueOf(dim0Value);
            case DIM1 -> dim1Value.toString();
            case DIM2 -> dim2Value.toString();
        };
    }

    @Override
    public Value clone() {
        IntegerValue cloned;
        switch (this.type) {
            case DIM0 -> cloned = new IntegerValue(type,this.dim0Value,null);
            case DIM1 -> {
                cloned = new IntegerValue(type,size1,null);
                cloned.dim1Value = this.dim1Value;
            }
            case DIM2 -> {
                cloned = new IntegerValue(type,size1,size2);
                cloned.dim2Value = this.dim2Value;
            }
            default -> throw new IllegalStateException(this.type.toString());
        }
        return cloned;
    }

    public List<MipsCode> toMipsCode() {
        List<MipsCode> mipsCodes = new ArrayList<>();
        Register reg = GRF.getTReg();
        mipsCodes.add(new Load(InstrType.li,reg,null,new Immediate(this.dim0Value)));
        reg.setOccupied();
        this.locate = reg;
        return mipsCodes;
    }
}
