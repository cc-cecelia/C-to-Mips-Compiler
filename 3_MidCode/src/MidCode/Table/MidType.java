package MidCode.Table;

public record MidType(SymbolType type) {

    @Override
    public boolean equals(Object obj) {
        MidType objType = (MidType) obj;
        return objType.type.toString().substring(0,3).equals(this.type.toString().substring(0,3));
        //return objType.toString().substring(0, 3).equals(type.toString().substring(0, 3));
    }
}
