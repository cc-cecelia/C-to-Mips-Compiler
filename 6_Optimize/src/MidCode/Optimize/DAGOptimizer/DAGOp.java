package MidCode.Optimize.DAGOptimizer;

import Fronted.ASTComponent.CmpType;

import java.util.HashMap;

public enum DAGOp {
    PLUS,
    MINU,
    MULT,
    DIV,
    MOD,
    SLL,
    SLR,
    LSS,
    LEQ,
    GRE,
    GEQ,
    NOT,
    EQL,
    NEQ,
    ASSIGN,
    MEMASS,
    GETINT,
    PRINT_INT,
    PRINT_STR,
    CALL,
    RET,
    LSSJUMP,
    LEQJUMP,
    GREJUMP,
    GEQJUMP,
    NOTJUMP,
    EQLJUMP,
    NEQJUMP,
    JUMP;

    public CmpType toCmpType() {
        return switch (this) {
            case LSSJUMP -> CmpType.LSS;
            case LEQJUMP -> CmpType.LEQ;
            case GREJUMP -> CmpType.GRE;
            case GEQJUMP -> CmpType.GEQ;
            case EQLJUMP -> CmpType.EQL;
            case NEQJUMP -> CmpType.NEQ;
            default -> null;
        };
    }
}
