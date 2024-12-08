.data
# int a_1
space0: .space 4
str0:.asciiz "res is "
str1:.asciiz "\n"
.text
# a_1 = 10
li $t0, 10
la $s0, space0
sw $t0, 0($s0)
jal main
li $v0, 10
syscall 
main: 
addi $sp, $sp, -40
sw $ra, 0($sp)
# int c_1
# temp_13 = a_1
la $s0, space0
lw $s0, 0($s0)
sw $s0, 8($sp)
# temp_14 = temp_13+2
lw $t1, 8($sp)
addi $t0, $t1, 2
sw $t0, 12($sp)
# c_1 = temp_14
lw $t0, 12($sp)
sw $t0, 4($sp)
# int res_1
# temp_15 = a_1
la $s0, space0
lw $s0, 0($s0)
sw $s0, 20($sp)
# temp_16 = c_1
lw $t0, 4($sp)
sw $t0, 24($sp)
# push temp_15
lw $a0, 20($sp)
# push temp_16
lw $a1, 24($sp)
# call add
jal add
# temp_17 = add
move $t0, $v0
sw $t0, 32($sp)
# res_1 = temp_17
lw $t0, 32($sp)
sw $t0, 16($sp)
# print_str, res is 
li $v0, 4
la $a0, str0
syscall 
# temp_18 = res_1
lw $t0, 16($sp)
sw $t0, 36($sp)
# print_int, temp_18
li $v0, 1
lw $a0, 36($sp)
syscall 
# print_str, \n
li $v0, 4
la $a0, str1
syscall 
# ret 0
li $v0, 0
lw $ra, 0($sp)
addiu $sp, $sp, 40
jr $ra
div: 
addi $sp, $sp, -12
sw $a0, 0($sp)
# temp_1 = a_2
lw $t0, 0($sp)
sw $t0, 4($sp)
# temp_2 = 10%temp_1
li $t1, 10
lw $t2, 4($sp)
div $t1, $t2
mfhi $t1
sw $t1, 8($sp)
# ret temp_2
lw $v0, 8($sp)
addiu $sp, $sp, 12
jr $ra
mul: 
addi $sp, $sp, -36
sw $ra, 0($sp)
sw $a0, 4($sp)
sw $a1, 8($sp)
# temp_3 = a_3
lw $t1, 4($sp)
sw $t1, 12($sp)
# push temp_3
lw $a0, 12($sp)
# call div
jal div
lw $a0, 4($sp)
lw $a1, 8($sp)
# temp_4 = div
move $t1, $v0
sw $t1, 20($sp)
# push temp_4
lw $a0, 20($sp)
# call div
jal div
lw $a0, 4($sp)
lw $a1, 8($sp)
# temp_5 = div
move $t1, $v0
sw $t1, 28($sp)
# temp_6 = b_1
lw $t1, 8($sp)
sw $t1, 32($sp)
# temp_7 = temp_5+temp_6
lw $t2, 28($sp)
lw $t3, 32($sp)
add $t1, $t2, $t3
sw $t1, 36($sp)
# ret temp_7
lw $v0, 36($sp)
lw $ra, 0($sp)
addiu $sp, $sp, 36
jr $ra
add: 
addi $sp, $sp, -36
sw $ra, 0($sp)
sw $a0, 4($sp)
sw $a1, 8($sp)
# temp_8 = a_4
lw $t1, 4($sp)
sw $t1, 12($sp)
# temp_9 = b_2
lw $t1, 8($sp)
sw $t1, 16($sp)
# temp_10 = b_2
lw $t1, 8($sp)
sw $t1, 20($sp)
# push temp_9
lw $a0, 16($sp)
# push temp_10
lw $a1, 20($sp)
# call mul
jal mul
lw $a0, 4($sp)
lw $a1, 8($sp)
# temp_11 = mul
move $t1, $v0
sw $t1, 28($sp)
# temp_12 = temp_8+temp_11
lw $t2, 12($sp)
lw $t3, 28($sp)
add $t1, $t2, $t3
sw $t1, 32($sp)
# ret temp_12
lw $v0, 32($sp)
lw $ra, 0($sp)
addiu $sp, $sp, 36
jr $ra
