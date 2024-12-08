.data
# int a_1
space0: .space 4
# int b_1[3]
space1: .space 12
.text
# b_1[0] = 0
li $t0, 0
la $t0, space1
sw $t0, 0($t0)
# b_1[1] = 1
li $t0, 1
la $t0, space1
sw $t0, 4($t0)
jal main
li $v0, 10
syscall 
main: 
addi $sp, $sp, -64
# int c_1
# c_1 = 0
li $t0, 0
sw $t0, 0($sp)
# int d_1[3]
# d_1[0] = 10
li $t0, 10
sw $t0, 4($sp)
# d_1[1] = 11
li $t1, 11
sw $t1, 8($sp)
# a_1 = getint()
li $v0, 5
syscall 
la $t2, space0
sw $v0, 0($t2)
# b_1[2] = getint()
li $v0, 5
syscall 
la $s0, space1
sw $v0, 8($s0)
# c_1 = getint()
li $v0, 5
syscall 
sw $v0, 0($sp)
# d_1[2] = getint()
li $v0, 5
syscall 
addi $s0, $s0, 8
sw $v0, 8($sp)
# temp_1 = a_1
la $s0, space0
lw $s0, 0($s0)
sw $s0, 16($sp)
# temp_2 = 1+temp_1
lw $t3, 16($sp)
addi $t2, $t3, 1
sw $t2, 20($sp)
# a_1 = temp_2
lw $t2, 20($sp)
la $s0, space0
sw $t2, 0($s0)
# temp_3 = c_1
lw $t2, 0($sp)
sw $t2, 24($sp)
# temp_4 = 1+temp_3
lw $t3, 24($sp)
addi $t2, $t3, 1
sw $t2, 28($sp)
# a_1 = temp_4
lw $t2, 28($sp)
la $s0, space0
sw $t2, 0($s0)
# temp_5 = a_1
la $s0, space0
lw $s0, 0($s0)
sw $s0, 32($sp)
# temp_6 = a_1
la $s0, space0
lw $s0, 0($s0)
sw $s0, 36($sp)
# b_1[temp_5] = temp_6
lw $t2, 36($sp)
la $t2, space1
lw $t3, 32($sp)
sll $t3, $t3, 2
add $t2, $t2, $t3
sw $t2, 0($t2)
# temp_7 = c_1
lw $t2, 0($sp)
sw $t2, 40($sp)
# temp_8 = a_1
la $s0, space0
lw $s0, 0($s0)
sw $s0, 44($sp)
# b_1[temp_7] = temp_8
lw $t2, 44($sp)
la $t2, space1
lw $t3, 40($sp)
sll $t3, $t3, 2
add $t2, $t2, $t3
sw $t2, 0($t2)
# temp_9 = a_1
la $s0, space0
lw $s0, 0($s0)
sw $s0, 48($sp)
# temp_10 = c_1
lw $t2, 0($sp)
sw $t2, 52($sp)
# b_1[temp_9] = temp_10
lw $t2, 52($sp)
la $t2, space1
lw $t3, 48($sp)
sll $t3, $t3, 2
add $t2, $t2, $t3
sw $t2, 0($t2)
# temp_11 = c_1
lw $t2, 0($sp)
sw $t2, 56($sp)
# temp_12 = c_1
lw $t2, 0($sp)
sw $t2, 60($sp)
# b_1[temp_11] = temp_12
lw $t2, 60($sp)
la $t2, space1
lw $t3, 56($sp)
sll $t3, $t3, 2
add $t2, $t2, $t3
sw $t2, 0($t2)
# ret 0
li $v0, 0
addiu $sp, $sp, 64
jr $ra
