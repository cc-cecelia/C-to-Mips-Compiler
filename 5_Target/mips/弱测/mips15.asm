.data
str0:.asciiz ""
str1:.asciiz "\n"
.text
jal main
li $v0, 10
syscall 
main: 
addi $sp, $sp, -48
# int i_1
# int j_1
# i_1 = 0
li $t0, 0
sw $t0, 0($sp)
for1: 
# temp_1 = i_1
lw $t0, 0($sp)
sw $t0, 8($sp)
lw $s0, 8($sp)
li $s1, 5
bge $s0 $s1 for1END
for1STMT: 
# j_1 = 0
li $t0, 0
sw $t0, 4($sp)
for2: 
# temp_2 = j_1
lw $t0, 4($sp)
sw $t0, 12($sp)
lw $s1, 12($sp)
li $s2, 3
bge $s1 $s2 for2END
for2STMT: 
# print_str, 
li $v0, 4
la $a0, str0
syscall 
# temp_3 = i_1
lw $t0, 0($sp)
sw $t0, 16($sp)
# temp_4 = j_1
lw $t0, 4($sp)
sw $t0, 20($sp)
# temp_5 = temp_3*temp_4
lw $t1, 16($sp)
lw $t2, 20($sp)
mult $t1, $t2
mflo $t1
sw $t1, 24($sp)
# print_int, temp_5
li $v0, 1
lw $a0, 24($sp)
syscall 
# print_str, \n
li $v0, 4
la $a0, str1
syscall 
for2CHANGE_VAL: 
# temp_6 = j_1
lw $t1, 4($sp)
sw $t1, 28($sp)
# temp_7 = temp_6+1
lw $t2, 28($sp)
addi $t1, $t2, 1
sw $t1, 32($sp)
# j_1 = temp_7
lw $t1, 32($sp)
sw $t1, 4($sp)
j for2
for2END: 
if1: 
# temp_8 = i_1
lw $t1, 0($sp)
sw $t1, 36($sp)
lw $s2, 36($sp)
li $s3, 3
bge $s2 $s3 if1stmt
j if1_END
if1stmt: 
j for1END
if1_END: 
for1CHANGE_VAL: 
# temp_9 = i_1
lw $t1, 0($sp)
sw $t1, 40($sp)
# temp_10 = temp_9+1
lw $t2, 40($sp)
addi $t1, $t2, 1
sw $t1, 44($sp)
# i_1 = temp_10
lw $t1, 44($sp)
sw $t1, 0($sp)
j for1
for1END: 
# ret 0
li $v0, 0
addiu $sp, $sp, 48
jr $ra
