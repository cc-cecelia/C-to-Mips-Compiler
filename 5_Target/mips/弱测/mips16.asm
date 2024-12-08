.data
# const int ZERO_1
space0: .space 4
# const int ONE_1
space1: .space 4
# int var2_1
space2: .space 4
# int var3_1
space3: .space 4
str0:.asciiz "\n"
str1:.asciiz "21373457\n"
str2:.asciiz "ERROR!\n"
str3:.asciiz "And success!\n"
str4:.asciiz "Or pass!\n"
str5:.asciiz "Test1 Success!"
.text
# ZERO_1 = 0
li $t0, 0
la $s0, space0
sw $t0, 0($s0)
# ONE_1 = 1
li $t0, 1
la $s0, space1
sw $t0, 0($s0)
# var2_1 = 2
li $t0, 2
la $s0, space2
sw $t0, 0($s0)
# var3_1 = 3
li $t0, 3
la $s0, space3
sw $t0, 0($s0)
jal main
li $v0, 10
syscall 
main: 
addi $sp, $sp, -132
sw $ra, 0($sp)
# print_str, 21373457\n
li $v0, 4
la $a0, str1
syscall 
if1: 
# temp_5 = ZERO_1
la $s0, space0
lw $s0, 0($s0)
sw $s0, 4($sp)
# temp_6 = var2_1
la $s0, space2
lw $s0, 0($s0)
sw $s0, 8($sp)
# temp_7 = temp_5+temp_6
lw $t1, 4($sp)
lw $t2, 8($sp)
add $t0, $t1, $t2
sw $t0, 12($sp)
# temp_8 = var3_1
la $s0, space3
lw $s0, 0($s0)
sw $s0, 16($sp)
# temp_9 = ONE_1
la $s0, space1
lw $s0, 0($s0)
sw $s0, 20($sp)
# temp_10 = temp_8-temp_9
lw $t1, 16($sp)
lw $t2, 20($sp)
sub $t0, $t1, $t2
sw $t0, 24($sp)
# temp_11 = ONE_1
la $s0, space1
lw $s0, 0($s0)
sw $s0, 28($sp)
lw $s0, 12($sp)
lw $s1, 24($sp)
bne $s0 $s1 tmpLabel0
lw $s0, 28($sp)
beqz $s0 tmpLabel0
j if1stmt
tmpLabel0: 
j if1_END
if1stmt: 
if2: 
# temp_12 = ZERO_1
la $s1, space0
lw $s1, 0($s1)
sw $s1, 32($sp)
# temp_13 = ZERO_1
la $s1, space0
lw $s1, 0($s1)
sw $s1, 36($sp)
# temp_14 = 0!temp_13
li $t1, 0
nor $t0, $t1, $0
sw $t0, 40($sp)
# temp_15 = ONE_1
la $s1, space1
lw $s1, 0($s1)
sw $s1, 44($sp)
# temp_16 = temp_15+1
lw $t1, 44($sp)
addi $t0, $t1, 1
sw $t0, 48($sp)
# temp_17 = var2_1
la $s1, space2
lw $s1, 0($s1)
sw $s1, 52($sp)
# temp_18 = temp_16+temp_17
lw $t1, 48($sp)
lw $t2, 52($sp)
add $t0, $t1, $t2
sw $t0, 56($sp)
lw $s1, 32($sp)
bnez $s1 if2stmt
lw $s2, 40($sp)
beqz $s2 tmpLabel1
lw $s3, 56($sp)
bgez $s3 tmpLabel1
j if2stmt
tmpLabel1: 
j ELSE2stmt
if2stmt: 
# print_str, ERROR!\n
li $v0, 4
la $a0, str2
syscall 
j if2_END
ELSE2stmt: 
# print_str, And success!\n
li $v0, 4
la $a0, str3
syscall 
if2_END: 
if1_END: 
if3: 
# temp_19 = var3_1
la $s4, space3
lw $s4, 0($s4)
sw $s4, 60($sp)
# temp_20 = var2_1
la $s4, space2
lw $s4, 0($s4)
sw $s4, 64($sp)
# temp_21 = temp_20-22
lw $t1, 64($sp)
addi $t0, $t1, -22
sw $t0, 68($sp)
lw $s4, 60($sp)
li $s5, 3
bne $s4 $s5 if3stmt
lw $s4, 68($sp)
li $s5, -20
beq $s4 $s5 if3stmt
j if3_END
if3stmt: 
if4: 
# temp_22 = ONE_1
la $s4, space1
lw $s4, 0($s4)
sw $s4, 72($sp)
# temp_23 = temp_22%2
lw $t1, 72($sp)
li $t2, 2
div $t1, $t2
mfhi $t1
sw $t1, 76($sp)
# temp_24 = temp_23+3
lw $t2, 76($sp)
addi $t1, $t2, 3
sw $t1, 80($sp)
# temp_25 = temp_24-8
lw $t2, 80($sp)
addi $t1, $t2, -8
sw $t1, 84($sp)
# temp_26 = var3_1
la $s4, space3
lw $s4, 0($s4)
sw $s4, 88($sp)
# temp_27 = temp_25+temp_26
lw $t2, 84($sp)
lw $t3, 88($sp)
add $t1, $t2, $t3
sw $t1, 92($sp)
# temp_28 = var2_1
la $s4, space2
lw $s4, 0($s4)
sw $s4, 96($sp)
# temp_29 = temp_27+temp_28
lw $t2, 92($sp)
lw $t3, 96($sp)
add $t1, $t2, $t3
sw $t1, 100($sp)
# temp_30 = ONE_1
la $s4, space1
lw $s4, 0($s4)
sw $s4, 104($sp)
lw $s4, 100($sp)
li $s5, 100
ble $s4 $s5 if4stmt
lw $s4, 104($sp)
bnez $s4 if4stmt
j if4_END
if4stmt: 
# print_str, Or pass!\n
li $v0, 4
la $a0, str4
syscall 
if4_END: 
if3_END: 
# print_str, Test1 Success!
li $v0, 4
la $a0, str5
syscall 
# call fun
jal fun
# temp_31 = fun
move $t1, $v0
sw $t1, 108($sp)
# call fun
jal fun
# temp_32 = fun
move $t1, $v0
sw $t1, 112($sp)
# call fun
jal fun
# temp_33 = fun
move $t1, $v0
sw $t1, 116($sp)
# call fun
jal fun
# temp_34 = fun
move $t1, $v0
sw $t1, 120($sp)
# call fun
jal fun
# temp_35 = fun
move $t1, $v0
sw $t1, 124($sp)
# call fun
jal fun
# temp_36 = fun
move $t1, $v0
sw $t1, 128($sp)
# ret 0
li $v0, 0
lw $ra, 0($sp)
addiu $sp, $sp, 132
jr $ra
fun: 
addi $sp, $sp, -24
# int i_1
# i_1 = 1
li $t1, 1
sw $t1, 0($sp)
# int yuming_1
# yuming_1 = 1
li $t1, 1
sw $t1, 4($sp)
for1: 
# temp_1 = yuming_1
lw $t1, 4($sp)
sw $t1, 8($sp)
lw $s5, 8($sp)
li $s6, 1000
bge $s5 $s6 for1END
for1STMT: 
# temp_2 = yuming_1
lw $t1, 4($sp)
sw $t1, 12($sp)
# temp_3 = temp_2*2
lw $t2, 12($sp)
li $t3, 2
mult $t2, $t3
mflo $t2
sw $t2, 16($sp)
# yuming_1 = temp_3
lw $t2, 16($sp)
sw $t2, 4($sp)
for1CHANGE_VAL: 
j for1
for1END: 
# print_str, \n
li $v0, 4
la $a0, str0
syscall 
# temp_4 = yuming_1
lw $t2, 4($sp)
sw $t2, 20($sp)
# print_int, temp_4
li $v0, 1
lw $a0, 20($sp)
syscall 
# ret 
addiu $sp, $sp, 24
jr $ra
