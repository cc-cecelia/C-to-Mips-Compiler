.data
str0:.asciiz ""
str1:.asciiz "\n"
.text
jal main
li $v0, 10
syscall 
main: 
addi $sp, $sp, -28
# int i_1
# i_1 = 0
li $t0, 0
sw $t0, 0($sp)
for1: 
# temp_1 = i_1
lw $t0, 0($sp)
sw $t0, 4($sp)
lw $s0, 4($sp)
li $s1, 5
bge $s0 $s1 for1END
for1STMT: 
if1: 
# temp_2 = i_1
lw $t0, 0($sp)
sw $t0, 8($sp)
lw $s1, 8($sp)
li $s2, 3
beq $s1 $s2 if1stmt
j ELSE1stmt
if1stmt: 
j for1END
j if1_END
ELSE1stmt: 
if2: 
# temp_3 = i_1
lw $t0, 0($sp)
sw $t0, 12($sp)
lw $s2, 12($sp)
li $s3, 1
beq $s2 $s3 if2stmt
j if2_END
if2stmt: 
j for1CHANGE_VAL
if2_END: 
if1_END: 
# print_str, 
li $v0, 4
la $a0, str0
syscall 
# temp_4 = i_1
lw $t0, 0($sp)
sw $t0, 16($sp)
# print_int, temp_4
li $v0, 1
lw $a0, 16($sp)
syscall 
# print_str, \n
li $v0, 4
la $a0, str1
syscall 
for1CHANGE_VAL: 
# temp_5 = i_1
lw $t0, 0($sp)
sw $t0, 20($sp)
# temp_6 = temp_5+1
lw $t1, 20($sp)
addi $t0, $t1, 1
sw $t0, 24($sp)
# i_1 = temp_6
lw $t0, 24($sp)
sw $t0, 0($sp)
j for1
for1END: 
# ret 0
li $v0, 0
addiu $sp, $sp, 28
jr $ra
