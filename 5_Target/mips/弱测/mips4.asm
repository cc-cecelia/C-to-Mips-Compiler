
.data
# int d_1

space0: .space 4
.text
# d_1 = 4

li $t0, 4
la $s0, space0
sw $t0, 0($s0)
jal main
li $v0, 10
syscall 
main: 
addi $sp, $sp, -12
sw $ra, 0($sp)
# temp_12 = 4

li $t0, 4
sw $t0, 4($sp)
# push temp_12

sw $a0, 4($sp)
# call hhh

sw $v0, 8($sp)
jal hhh
# temp_13 = hhh

move $t0, $v0
sw $t0, 12($sp)
# ret 0

li $v0, 0
lw $ra, 0($sp)
addiu $sp, $sp, 12
jr $ra
lll: 
addi $sp, $sp, -20
sw $a0, 0($sp)
sw $a1, 4($sp)
# temp_1 = i_1

lw $t0, 0($sp)
sw $t0, 8($sp)
# temp_2 = j_1

lw $t0, 4($sp)
sw $t0, 12($sp)
# temp_3 = temp_1/temp_2

lw $t1, 8($sp)
lw $t2, 12($sp)
div $t1, $t2
mflo $t1
sw $t1, 16($sp)
# ret temp_3

lw $v0, 16($sp)
addiu $sp, $sp, 20
jr $ra
hhh: 
addi $sp, $sp, -56
sw $ra, 0($sp)
sw $a0, 4($sp)
# int c_1

# temp_4 = i_2

lw $t1, 4($sp)
sw $t1, 12($sp)
# temp_5 = i_2

lw $t1, 4($sp)
sw $t1, 16($sp)
# temp_6 = temp_4*temp_5

lw $t2, 12($sp)
lw $t3, 16($sp)
mult $t2, $t3
mflo $t2
sw $t2, 20($sp)
# c_1 = temp_6

lw $t2, 20($sp)
sw $t2, 8($sp)
# temp_7 = c_1

lw $t2, 8($sp)
sw $t2, 24($sp)
# temp_8 = i_2

lw $t2, 4($sp)
sw $t2, 28($sp)
# push temp_8

sw $a0, 28($sp)
# push temp_7

sw $a1, 24($sp)
# call lll

sw $v0, 32($sp)
jal lll
lw $a0, 4($sp)
# temp_9 = lll

move $t2, $v0
sw $t2, 36($sp)
# temp_10 = i_2

lw $t2, 4($sp)
sw $t2, 40($sp)
# push temp_10

sw $a2, 40($sp)
# push temp_9

sw $a3, 36($sp)
# call lll

sw $v0, 44($sp)
jal lll
lw $a0, 4($sp)
# temp_11 = lll

move $t2, $v0
sw $t2, 48($sp)
# ret temp_11

lw $v0, 48($sp)
lw $ra, 0($sp)
addiu $sp, $sp, 56
jr $ra
