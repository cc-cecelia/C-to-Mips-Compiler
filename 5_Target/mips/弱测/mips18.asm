.data
# int a_1[4]
space0: .space 16
str0:.asciiz ""
str1:.asciiz "\n"
.text
# a_1[0] = 0
li $t0, 0
la $t1, space0
sw $t0, 0($t1)
# a_1[1] = 1
li $t0, 1
la $t1, space0
sw $t0, 4($t1)
# a_1[2] = 11
li $t0, 11
la $t1, space0
sw $t0, 8($t1)
# a_1[3] = 12
li $t0, 12
la $t1, space0
sw $t0, 12($t1)
jal main
li $v0, 10
syscall 
main: 
addi $sp, $sp, -44
sw $ra, 0($sp)
# int b_1[4]
# b_1[0] = 3
li $t0, 3
sw $t0, 4($sp)
# b_1[1] = 4
li $t0, 4
sw $t0, 8($sp)
# print_str, 
li $v0, 4
la $a0, str0
syscall 
# temp_2 = 2*1
li $t1, 2
li $t2, 1
mult $t1, $t2
mflo $t1
sw $t1, 20($sp)
# temp_3 = temp_2*4
lw $t1, 20($sp)
li $t2, 4
mult $t1, $t2
mflo $t1
sw $t1, 24($sp)
# temp_4 = &a_1+temp_3
la $t1, space0
lw $t2, 24($sp)
add $t0, $t1, $t2
sw $t0, 28($sp)
# temp_5 = temp_4
lw $t0, 28($sp)
sw $t0, 32($sp)
# push temp_5
lw $a0, 32($sp)
# call test
jal test
# temp_6 = test
move $t0, $v0
sw $t0, 36($sp)
# print_int, temp_6
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
addiu $sp, $sp, 44
jr $ra
test: 
addi $sp, $sp, -8
sw $a0, 0($sp)
# temp_1 = a_2[1]
lw $t0, 0($sp)
addi $t0, $t0, 4
lw $t0, 0($t0)
sw $t0, 4($sp)
# ret temp_1
lw $v0, 4($sp)
addiu $sp, $sp, 8
jr $ra

