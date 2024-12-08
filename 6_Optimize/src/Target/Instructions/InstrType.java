package Target.Instructions;

public enum InstrType {
    // ALU:
    addu,
    subu,
    addiu,
    lui,
    and,
    blt, //如果一个寄存器的值小于另一个寄存器的值，则跳转。
    bgt, //如果一个寄存器的值大于另一个寄存器的值，则跳转。
    ble, //如果一个寄存器的值小于等于另一个寄存器的值，则跳转。
    bge, //如果一个寄存器的值大于等于另一个寄存器的值，则跳转。
    beqz, //如果一个寄存器中的值为零，则跳转。
    bnez, //如果一个寄存器中的值不为零，则跳转
    blez,//如果一个寄存器的值小于等于零，则跳转。
    bgtz,//如果一个寄存器的值大于零，则跳转。
    bltz,//如果一个寄存器的值小于零，则跳转。
    bgez,// 如果一个寄存器的值大于等于零，则跳转。
    nor,
    or,
    slt,
    sltu,
    slti,
    seq, // rs == rt -> rd = 1
    sne, // rs != rt -> rd = 1
    sgt,
    sge,
    sle,
    sll,
    sra,
    // 乘除
    mult,
    div,
    mthi,
    mtlo,
    mfhi,
    mflo,
    // LW
    li,
    la,
    lw,
    sw,
    // 跳转指令：
    jal,
    jr,
    j,
    beq,
    bne,
    syscall,
    move,
    label,
    annotation

}
