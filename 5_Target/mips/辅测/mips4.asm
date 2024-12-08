.data
# int next_1[8]
space0: .space 32
str0:.asciiz ""
str1:.asciiz "\n"
.text
# next_1[0] = 1
li $t0, 1
la $t1, space0
sw $t0, 0($t1)
# next_1[1] = 0
li $t0, 0
la $t1, space0
sw $t0, 4($t1)
# next_1[2] = 0
li $t0, 0
la $t1, space0
sw $t0, 8($t1)
# next_1[3] = -1
li $t0, -1
la $t1, space0
sw $t0, 12($t1)
# next_1[4] = -1
li $t0, -1
la $t1, space0
sw $t0, 16($t1)
# next_1[5] = 0
li $t0, 0
la $t1, space0
sw $t0, 20($t1)
# next_1[6] = 0
li $t0, 0
la $t1, space0
sw $t0, 24($t1)
# next_1[7] = 1
li $t0, 1
la $t1, space0
sw $t0, 28($t1)
jal main
li $v0, 10
syscall 
main: 
addi $sp, $sp, -40
sw $ra, 0($sp)
# temp_15 = next_1
la $s0, space0
sw $s0, 4($sp)
# push temp_15
lw $a0, 4($sp)
# call printArr2
jal printArr2
# temp_16 = printArr2
move $t0, $v0
sw $t0, 8($sp)
# temp_17 = 2*0
li $t1, 2
li $t2, 0
mult $t1, $t2
mflo $t1
sw $t1, 12($sp)
# temp_18 = temp_17*4
lw $t1, 12($sp)
li $t2, 4
mult $t1, $t2
mflo $t1
sw $t1, 16($sp)
# temp_19 = &next_1+temp_18
la $t1, space0
lw $t2, 16($sp)
add $t0, $t1, $t2
sw $t0, 20($sp)
# temp_20 = temp_19
lw $t0, 20($sp)
sw $t0, 24($sp)
# push temp_20
lw $a0, 24($sp)
# call printArr
jal printArr
# temp_21 = printArr
move $t0, $v0
sw $t0, 28($sp)
# ret 0
li $v0, 0
lw $ra, 0($sp)
addiu $sp, $sp, 40
jr $ra
printArr: 
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
li $s1, 2
bge $s0 $s1 for1END
for1STMT: 
# print_str, 
li $v0, 4
la $a0, str0
syscall 
# temp_2 = i_1
lw $t0, 4($sp)
sw $t0, 12($sp)
# temp_3 = a_1[temp_2]
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
for1CHANGE_VAL: 
j for1
for1END: 
# print_str, \n
li $v0, 4
la $a0, str1
syscall 
printArr2: 
addi $sp, $sp, -52
sw $ra, 0($sp)
sw $a0, 4($sp)
# int i_2
# i_2 = 0
li $t0, 0
sw $t0, 8($sp)
for2: 
# temp_6 = i_2
lw $t0, 8($sp)
sw $t0, 12($sp)
lw $s0, 12($sp)
li $s1, 4
bge $s0 $s1 for2END
for2STMT: 
# temp_7 = i_2
lw $t0, 8($sp)
sw $t0, 16($sp)
# temp_8 = 0*temp_7
li $t1, 0
lw $t2, 16($sp)
mult $t1, $t2
mflo $t1
sw $t1, 20($sp)
# temp_9 = temp_8*4
lw $t1, 20($sp)
li $t2, 4
mult $t1, $t2
mflo $t1
sw $t1, 24($sp)
# temp_10 = &a_2+temp_9
addi $t1, $sp, 4
lw $t2, 24($sp)
add $t0, $t1, $t2
sw $t0, 28($sp)
# temp_11 = temp_10
lw $t0, 28($sp)
sw $t0, 32($sp)
# push temp_11
lw $a0, 32($sp)
# call printArr
jal printArr
lw $a0, 4($sp)
# temp_12 = printArr
move $t0, $v0
sw $t0, 36($sp)
# temp_13 = i_2
lw $t0, 8($sp)
sw $t0, 40($sp)
# temp_14 = temp_13+1
lw $t1, 40($sp)
addi $t0, $t1, 1
sw $t0, 44($sp)
# i_2 = temp_14
lw $t0, 44($sp)
sw $t0, 8($sp)
for2CHANGE_VAL: 
j for2
for2END: 
# ret 
lw $ra, 0($sp)
addiu $sp, $sp, 52
jr $ra