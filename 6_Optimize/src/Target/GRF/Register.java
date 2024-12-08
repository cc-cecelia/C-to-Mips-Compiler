package Target.GRF;

import Target.Operand;

public class Register extends Operand {
    protected final String name;
    protected final int id;
    protected boolean occupied = false;

    public Register(String name,int id) {
        this.name = name;
        this.id = id;
    }

    public void setOccupied() {
        this.occupied = true;
        //System.out.println(name + "is occupied");
    }

    public void release() {
        this.occupied = false;
        //System.out.println(name + "is free");
    }

    @Override
    public String toString() {
        return "$" + name;
    }
}
