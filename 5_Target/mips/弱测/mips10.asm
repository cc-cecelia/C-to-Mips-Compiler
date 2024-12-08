
.data
# int a_1
space0: .space 4
str0:.asciiz "e is "
str1:.asciiz "\n"
str2:.asciiz "c is "
str3:.asciiz "\n"
str4:.asciiz "d is "
str5:.asciiz "\n"
str6:.asciiz "a is "
str7:.asciiz "\n"
str8:.asciiz "b is "
str9:.asciiz "\n"
str10:.asciiz "res is "
str11:.asciiz "\n"
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
# temp_18 = a_1
la $s0, space0
lw $s0, 0($s0)
sw $s0, 8($sp)
# temp_19 = temp_18+2
lw $t1, 8($sp)
addi $t0, $t1, 2
sw $t0, 12($sp)
# c_1 = temp_19
lw $t0, 12($sp)
sw $t0, 4($sp)
# int res_1
# temp_20 = a_1
la $s0, space0
lw $s0, 0($s0)
sw $s0, 20($sp)
# temp_21 = c_1
lw $t0, 4($sp)
sw $t0, 24($sp)
# push temp_20
lw $a0, 20($sp)
# push temp_21
lw $a1, 24($sp)
# call add
jal add
# temp_22 = add
move $t0, $v0
sw $t0, 28($sp)
# res_1 = temp_22
lw $t0, 28($sp)
sw $t0, 16($sp)
# print_str, res is 
li $v0, 4
la $a0, str10
syscall 
# temp_23 = res_1
lw $t0, 16($sp)
sw $t0, 32($sp)
# print_int, temp_23
li $v0, 1
lw $a0, 32($sp)
syscall 
# print_str, \n
li $v0, 4
la $a0, str11
syscall 
# ret 0
li $v0, 0
lw $ra, 0($sp)
addiu $sp, $sp, 40
jr $ra
div: 
addi $sp, $sp, -16
sw $a0, 0($sp)
# print_str, e is 
li $v0, 4
la $a0, str0
syscall 
# temp_1 = a_2
lw $t0, 0($sp)
sw $t0, 4($sp)
# print_int, temp_1
li $v0, 1
lw $a0, 4($sp)
syscall 
# print_str, \n
li $v0, 4
la $a0, str1
syscall 
# temp_2 = a_2
lw $t0, 0($sp)
sw $t0, 8($sp)
# temp_3 = 10%temp_2
li $t1, 10
lw $t2, 8($sp)
div $t1, $t2
mfhi $t1
sw $t1, 12($sp)
# ret temp_3
lw $v0, 12($sp)
addiu $sp, $sp, 16
jr $ra
mul: 
addi $sp, $sp, -44
sw $ra, 0($sp)
sw $a0, 4($sp)
sw $a1, 8($sp)
# print_str, c is 
li $v0, 4
la $a0, str2
syscall 
# temp_4 = a_3
lw $t1, 4($sp)
sw $t1, 12($sp)
# print_int, temp_4
li $v0, 1
lw $a0, 12($sp)
syscall 
# print_str, \n
li $v0, 4
la $a0, str3
syscall 
# print_str, d is 
li $v0, 4
la $a0, str4
syscall 
# temp_5 = b_1
lw $t1, 8($sp)
sw $t1, 16($sp)
# print_int, temp_5
li $v0, 1
lw $a0, 16($sp)
syscall 
# print_str, \n
li $v0, 4
la $a0, str5
syscall 
# temp_6 = a_3
lw $t1, 4($sp)
sw $t1, 20($sp)
# push temp_6
lw $a0, 20($sp)
# call div
jal div
lw $a0, 4($sp)
lw $a1, 8($sp)
# temp_7 = div
move $t1, $v0
sw $t1, 24($sp)
# push temp_7
lw $a0, 24($sp)
# call div
jal div
lw $a0, 4($sp)
lw $a1, 8($sp)
# temp_8 = div
move $t1, $v0
sw $t1, 28($sp)
# temp_9 = b_1
lw $t1, 8($sp)
sw $t1, 32($sp)
# temp_10 = temp_8+temp_9
lw $t2, 28($sp)
lw $t3, 32($sp)
add $t1, $t2, $t3
sw $t1, 36($sp)
# ret temp_10
lw $v0, 36($sp)
lw $ra, 0($sp)
addiu $sp, $sp, 44
jr $ra
add: 
addi $sp, $sp, -44
sw $ra, 0($sp)
sw $a0, 4($sp)
sw $a1, 8($sp)
# print_str, a is 
li $v0, 4
la $a0, str6
syscall 
# temp_11 = a_4
lw $t1, 4($sp)
sw $t1, 12($sp)
# print_int, temp_11
li $v0, 1
lw $a0, 12($sp)
syscall 
# print_str, \n
li $v0, 4
la $a0, str7
syscall 
# print_str, b is 
li $v0, 4
la $a0, str8
syscall 
# temp_12 = b_2
lw $t1, 8($sp)
sw $t1, 16($sp)
# print_int, temp_12
li $v0, 1
lw $a0, 16($sp)
syscall 
# print_str, \n
li $v0, 4
la $a0, str9
syscall 
# temp_13 = a_4
lw $t1, 4($sp)
sw $t1, 20($sp)
# temp_14 = b_2
lw $t1, 8($sp)
sw $t1, 24($sp)
# temp_15 = b_2
lw $t1, 8($sp)
sw $t1, 28($sp)
# push temp_14
lw $a0, 24($sp)
# push temp_15
lw $a1, 28($sp)
# call mul
jal mul
lw $a0, 4($sp)
lw $a1, 8($sp)
# temp_16 = mul
move $t1, $v0
sw $t1, 32($sp)
# temp_17 = temp_13+temp_16
lw $t2, 20($sp)
lw $t3, 32($sp)
add $t1, $t2, $t3
sw $t1, 36($sp)
# ret temp_17
lw $v0, 36($sp)
lw $ra, 0($sp)
addiu $sp, $sp, 44
jr $ra
