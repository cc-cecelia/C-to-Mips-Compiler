.data
# int a_1[6]
space0: .space 24
str0:.asciiz ""
str1:.asciiz "\n"
.text
# a_1[0] = -1
li $t0, -1
la $t1, space0
sw $t0, 0($t1)
# a_1[1] = 2
li $t0, 2
la $t1, space0
sw $t0, 4($t1)
jal main
li $v0, 10
syscall 
main: 
addi $sp, $sp, -28
sw $ra, 0($sp)
# temp_6 = 2*0
li $t1, 2
li $t2, 0
mult $t1, $t2
mflo $t1
sw $t1, 4($sp)
# temp_7 = temp_6*4
lw $t1, 4($sp)
li $t2, 4
mult $t1, $t2
mflo $t1
sw $t1, 8($sp)
# temp_8 = &a_1+temp_7
la $t1, space0
lw $t2, 8($sp)
add $t0, $t1, $t2
sw $t0, 12($sp)
# temp_9 = temp_8
lw $t0, 12($sp)
sw $t0, 16($sp)
# push temp_9
lw $a0, 16($sp)
# call print
jal print
# temp_10 = print
move $t0, $v0
sw $t0, 20($sp)
# ret 0
li $v0, 0
lw $ra, 0($sp)
addiu $sp, $sp, 28
jr $ra
print: 
addi $sp, $sp, -28
sw $a0, 0($sp)
# int i_1
# i_1 = 0
li $t0, 0
sw $t0, 4($sp)
for1: 
# temp_1 = i_1
lw $t0, 4($sp)
sw $t0, 8($sp)
lw $s0, 8($sp)
li $s1, 3
bge $s0 $s1 for1END
for1STMT: 
# print_str, 
li $v0, 4
la $a0, str0
syscall 
# temp_2 = i_1
lw $t0, 4($sp)
sw $t0, 12($sp)
# temp_3 = a_2[temp_2]
lw $t0, 0($sp)
lw $t1, 12($sp)
sll $t1, $t1, 2
add $t0, $t0, $t1
lw $t0, 0($t0)
sw $t0, 16($sp)
# print_int, temp_3
li $v0, 1
lw $a0, 16($sp)
syscall 
# print_str, \n
li $v0, 4
la $a0, str1
syscall 
for1CHANGE_VAL: 
# temp_4 = i_1
lw $t0, 4($sp)
sw $t0, 20($sp)
# temp_5 = temp_4+1
lw $t1, 20($sp)
addi $t0, $t1, 1
sw $t0, 24($sp)
# i_1 = temp_5
lw $t0, 24($sp)
sw $t0, 4($sp)
j for1
for1END: 