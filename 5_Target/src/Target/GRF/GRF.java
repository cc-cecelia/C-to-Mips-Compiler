package Target.GRF;

import java.util.HashMap;

public class GRF {
    private static HashMap<String, Register> GRF;

    static {
        GRF = new HashMap<>();
        GRF.put("0", new Register("0",0));
        GRF.put("at", new Register("at",1));
        GRF.put("v0", new Register("v0",2));
        GRF.put("v1", new Register("v1",3));
        for (int i = 4; i < 8; i++) {
            GRF.put("a" + (i - 4), new Register("a" + (i - 4),i));
        }
        for (int i = 8; i < 16; i++) {
            GRF.put("t" + (i - 8), new Register("t" + (i - 8),i));
        }
        for (int i = 16; i < 24; i++) {
            GRF.put("s" + (i - 16), new Register("s" + (i - 16),i));
        }
        GRF.put("t8",new Register("t8",24));
        GRF.put("t9",new Register("t9",25));
        GRF.put("k0",new Register("k0",26));
        GRF.put("k1",new Register("k1",27));
        GRF.put("gp",new Register("gp",28));
        GRF.put("sp",new Register("sp",29));
        GRF.put("fp",new Register("fp",30));
        GRF.put("ra",new Register("ra",31));
        GRF.put("hi",new Register("hi",-1));
        GRF.put("lo",new Register("lo",-2));
    }

    public static Register getReg(String reg) {
        return GRF.get(reg);
    }

    public static Register getTReg() {
        // 从 t0, t1, t2, t3中间挑一个
        for (int i = 0; i < 10; i++) {
            String reg = "t" + i;
            if (!GRF.get(reg).occupied) {
                return GRF.get(reg);
            }
        }
        throw new IllegalStateException("怎么t0~t9全都occupied了？");
    }

    public static Register getAReg() {
        for (int i = 0; i < 4; i++) {
            String reg = "a" + i;
            if (!GRF.get(reg).occupied) {
                return GRF.get(reg);
            }
        }
        return null;
        //throw new RuntimeException("我还没设计要超过4个参数的！");
    }

    public static Register getSReg() {
        for (int i = 0; i < 7; i++) {
            String reg = "s" + i;
            if (!GRF.get(reg).occupied) {
                return GRF.get(reg);
            }
        }
        return null;
    }

}
