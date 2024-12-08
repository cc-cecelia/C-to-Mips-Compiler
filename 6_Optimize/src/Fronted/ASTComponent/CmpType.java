package Fronted.ASTComponent;

public enum CmpType {
    OR("||"),
    AND("&&"),
    EQL("=="),
    NEQ("!="),
    GEQ(">="),
    LEQ("<="),
    GRE(">"),
    LSS("<");

    //OR, AND, EQL, NEQ, GEQ, LEQ, GRE, LSS;
    private final String sym;

    CmpType(String sym) {
        this.sym = sym;
    }

    public String getOp() {
        return this.sym;
    }

    public CmpType getOpposite() {
        return switch (this) {
            case LSS -> GEQ;
            case LEQ -> GRE;
            case GEQ -> LSS;
            case GRE -> LEQ;
            case EQL -> NEQ;
            case NEQ -> EQL;
            case AND -> OR;
            case OR -> AND;
        };
    }
}
