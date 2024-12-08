.data
# int d_1

space0: .space 4
str0:.asciiz ""
str1:.asciiz ", "
str2:.asciiz ", "
str3:.asciiz "\n"
.text
# d_1 = 4

li $t0, 4
la $s0, space0
sw $t0, 0($s0)
jal main
li $v0, 10
syscall 
main: 
addi $sp, $sp, -60
sw $ra, 0($sp)
# int i_2

# i_2 = 2

li $t0, 2
sw $t0, 4($sp)
# int j_1

# j_1 = 5

li $t0, 5
sw $t0, 8($sp)
# i_2 = getint()

li $v0, 5
syscall 
# j_1 = getint()

li $v0, 5
syscall 
# temp_17 = 3

li $t0, 3
sw $t0, 12($sp)
# push temp_17

lw $a0, 12($sp)
# call hhh

jal hhh
# temp_18 = hhh

move $t0, $v0
sw $t0, 16($sp)
# temp_19 = temp_18

lw $t0, 16($sp)
sw $t0, 20($sp)
# push temp_19

lw $a1, 20($sp)
# call hhh

jal hhh
# temp_20 = hhh

move $t0, $v0
sw $t0, 24($sp)
# temp_21 = -10199+temp_20

lw $t1, 24($sp)
addi $t0, $t1, -10199
sw $t0, 28($sp)
# temp_22 = temp_21-41440

lw $t1, 28($sp)
addi $t0, $t1, -41440
sw $t0, 32($sp)
# j_1 = temp_22

lw $t0, 32($sp)
sw $t0, 8($sp)
# int k_1

# k_1 = 5

li $t0, 5
sw $t0, 36($sp)
# int n_1

# n_1 = 10

li $t0, 10
sw $t0, 40($sp)
# print_str, 

li $v0, 4
la $a0, str0
syscall 
# temp_23 = i_2

lw $t0, 4($sp)
sw $t0, 44($sp)
# print_int, temp_23

li $v0, 1
lw $a0, 44($sp)
syscall 
# print_str, , 

li $v0, 4
la $a0, str1
syscall 
# temp_24 = j_1

lw $t0, 8($sp)
sw $t0, 48($sp)
# print_int, temp_24

li $v0, 1
lw $a0, 48($sp)
syscall 
# print_str, , 

li $v0, 4
la $a0, str2
syscall 
# temp_25 = k_1

lw $t0, 36($sp)
sw $t0, 52($sp)
# print_int, temp_25

li $v0, 1
lw $a0, 52($sp)
syscall 
# print_str, \n

li $v0, 4
la $a0, str3
syscall 
# ret 0

li $v0, 0
lw $ra, 0($sp)
addiu $sp, $sp, 60
jr $ra
hhh: 
addi $sp, $sp, -80
sw $ra, 0($sp)
sw $a0, 4($sp)
# int c_1

# temp_1 = i_1

lw $t0, 4($sp)
sw $t0, 12($sp)
# temp_2 = i_1

lw $t0, 4($sp)
sw $t0, 16($sp)
# temp_3 = temp_1*temp_2

lw $t1, 12($sp)
lw $t2, 16($sp)
mult $t1, $t2
mflo $t1
sw $t1, 20($sp)
# c_1 = temp_3

lw $t1, 20($sp)
sw $t1, 8($sp)
# temp_4 = c_1

lw $t1, 8($sp)
sw $t1, 24($sp)
# temp_5 = c_1

lw $t1, 8($sp)
sw $t1, 28($sp)
# temp_6 = temp_4*temp_5

lw $t2, 24($sp)
lw $t3, 28($sp)
mult $t2, $t3
mflo $t2
sw $t2, 32($sp)
# temp_7 = temp_6%10

lw $t3, 32($sp)
li $t4, 10
div $t3, $t4
mfhi $t3
sw $t3, 36($sp)
# c_1 = temp_7

lw $t3, 36($sp)
sw $t3, 8($sp)
# temp_8 = i_1

lw $t3, 4($sp)
sw $t3, 40($sp)
# temp_9 = 1-temp_8

li $t4, 1
lw $t5, 40($sp)
sub $t3, $t4, $t5
sw $t3, 44($sp)
# temp_10 = temp_9

lw $t3, 44($sp)
sw $t3, 48($sp)
# push temp_10

lw $a0, 48($sp)
# call hhh

jal hhh
# temp_11 = hhh

move $t3, $v0
sw $t3, 52($sp)
# temp_12 = i_1

lw $t3, 4($sp)
sw $t3, 56($sp)
# temp_13 = 2-temp_12

li $t4, 2
lw $t5, 56($sp)
sub $t3, $t4, $t5
sw $t3, 60($sp)
# temp_14 = temp_13

lw $t3, 60($sp)
sw $t3, 64($sp)
# push temp_14

lw $a2, 64($sp)
# call hhh

jal hhh
# temp_15 = hhh

move $t3, $v0
sw $t3, 68($sp)
# temp_16 = temp_15+temp_11

lw $t4, 68($sp)
lw $t5, 52($sp)
add $t3, $t4, $t5
sw $t3, 72($sp)
# ret temp_16

lw $v0, 72($sp)
lw $ra, 0($sp)
addiu $sp, $sp, 80
jr $ra