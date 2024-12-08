.data
# const int c_common_1
space0: .space 4
# const int c_array_1[3]
space1: .space 12
# const int c_matrix_1[9]
space2: .space 36
# int v_common_1
space3: .space 4
# int v_array_1[3]
space4: .space 12
# int v_matrix_1[9]
space5: .space 36
# int v_commonInit_1
space6: .space 4
# int v_arrayInit_1[3]
space7: .space 12
# int v_matrixInit_1[9]
space8: .space 36
str0:.asciiz "\nf2() flag : "
str1:.asciiz "\nflag = "
str2:.asciiz " : c1 = "
str3:.asciiz ", c2 = "
.text
# c_common_1 = 10
li $t0, 10
la $s0, space0
sw $t0, 0($s0)
# c_array_1[0] = 1
li $t0, 1
la $t1, space1
sw $t0, 0($t1)
# c_array_1[1] = 2
li $t0, 2
la $t1, space1
sw $t0, 4($t1)
# c_array_1[2] = 3
li $t0, 3
la $t1, space1
sw $t0, 8($t1)
# c_matrix_1[0] = 1
li $t0, 1
la $t1, space2
sw $t0, 0($t1)
# c_matrix_1[1] = 2
li $t0, 2
la $t1, space2
sw $t0, 4($t1)
# c_matrix_1[2] = 3
li $t0, 3
la $t1, space2
sw $t0, 8($t1)
# c_matrix_1[3] = 1
li $t0, 1
la $t1, space2
sw $t0, 12($t1)
# c_matrix_1[4] = 2
li $t0, 2
la $t1, space2
sw $t0, 16($t1)
# c_matrix_1[5] = 3
li $t0, 3
la $t1, space2
sw $t0, 20($t1)
# c_matrix_1[6] = 1
li $t0, 1
la $t1, space2
sw $t0, 24($t1)
# c_matrix_1[7] = 2
li $t0, 2
la $t1, space2
sw $t0, 28($t1)
# c_matrix_1[8] = 3
li $t0, 3
la $t1, space2
sw $t0, 32($t1)
# v_commonInit_1 = 10
li $t0, 10
la $s0, space6
sw $t0, 0($s0)
# v_arrayInit_1[0] = 1
li $t0, 1
la $t1, space7
sw $t0, 0($t1)
# v_arrayInit_1[1] = 2
li $t0, 2
la $t1, space7
sw $t0, 4($t1)
# v_arrayInit_1[2] = 3
li $t0, 3
la $t1, space7
sw $t0, 8($t1)
# v_matrixInit_1[0] = 1
li $t0, 1
la $t1, space8
sw $t0, 0($t1)
# v_matrixInit_1[1] = 2
li $t0, 2
la $t1, space8
sw $t0, 4($t1)
# v_matrixInit_1[2] = 3
li $t0, 3
la $t1, space8
sw $t0, 8($t1)
# v_matrixInit_1[3] = 1
li $t0, 1
la $t1, space8
sw $t0, 12($t1)
# v_matrixInit_1[4] = 2
li $t0, 2
la $t1, space8
sw $t0, 16($t1)
# v_matrixInit_1[5] = 3
li $t0, 3
la $t1, space8
sw $t0, 20($t1)
# v_matrixInit_1[6] = 1
li $t0, 1
la $t1, space8
sw $t0, 24($t1)
# v_matrixInit_1[7] = 2
li $t0, 2
la $t1, space8
sw $t0, 28($t1)
# v_matrixInit_1[8] = 3
li $t0, 3
la $t1, space8
sw $t0, 32($t1)
jal main
li $v0, 10
syscall 
main: 
addi $sp, $sp, -168
sw $ra, 0($sp)
# int a_3
# int b_3
# int flag1_1
# int flag2_1
# int flag_2
# int i_1
# a_3 = getint()
li $v0, 5
syscall 
sw $v0, 4($sp)
# b_3 = getint()
li $v0, 5
syscall 
sw $v0, 8($sp)
# i_1 = 0
li $t0, 0
sw $t0, 24($sp)
if5: 
# temp_50 = a_3
lw $t0, 4($sp)
sw $t0, 28($sp)
# temp_51 = b_3
lw $t0, 8($sp)
sw $t0, 32($sp)
lw $s0, 28($sp)
lw $s1, 32($sp)
bne $s0 $s1 if5stmt
j if5_END
if5stmt: 
for1: 
# temp_52 = i_1
lw $t0, 24($sp)
sw $t0, 36($sp)
lw $s0, 36($sp)
li $s1, 3
bge $s0 $s1 for1END
for1STMT: 
# int c1_1
# int c2_1
# temp_53 = a_3
lw $t0, 4($sp)
sw $t0, 48($sp)
# temp_54 = b_3
lw $t0, 8($sp)
sw $t0, 52($sp)
# temp_55 = temp_53+temp_54
lw $t1, 48($sp)
lw $t2, 52($sp)
add $t0, $t1, $t2
sw $t0, 56($sp)
# c1_1 = temp_55
lw $t0, 56($sp)
sw $t0, 40($sp)
# temp_56 = a_3
lw $t0, 4($sp)
sw $t0, 60($sp)
# temp_57 = b_3
lw $t0, 8($sp)
sw $t0, 64($sp)
# temp_58 = temp_56-temp_57
lw $t1, 60($sp)
lw $t2, 64($sp)
sub $t0, $t1, $t2
sw $t0, 68($sp)
# c2_1 = temp_58
lw $t0, 68($sp)
sw $t0, 44($sp)
# temp_59 = c1_1
lw $t0, 40($sp)
sw $t0, 72($sp)
# temp_60 = c2_1
lw $t0, 44($sp)
sw $t0, 76($sp)
# push temp_59
lw $a0, 72($sp)
# push temp_60
lw $a1, 76($sp)
# call f2
jal f2
# temp_61 = f2
move $t0, $v0
sw $t0, 80($sp)
# flag1_1 = temp_61
lw $t0, 80($sp)
sw $t0, 12($sp)
# temp_62 = c2_1
lw $t0, 44($sp)
sw $t0, 84($sp)
# temp_63 = c1_1
lw $t0, 40($sp)
sw $t0, 88($sp)
# push temp_62
lw $a0, 84($sp)
# push temp_63
lw $a1, 88($sp)
# call f2
jal f2
# temp_64 = f2
move $t0, $v0
sw $t0, 92($sp)
# flag2_1 = temp_64
lw $t0, 92($sp)
sw $t0, 16($sp)
if6: 
# temp_65 = flag1_1
lw $t0, 12($sp)
sw $t0, 96($sp)
# temp_66 = flag2_1
lw $t0, 16($sp)
sw $t0, 100($sp)
# temp_67 = 0!temp_66
lw $t1, 100($sp)
seq $t0, $0, $t1
sw $t0, 104($sp)
lw $s0, 96($sp)
beqz $s0 if6stmt
lw $s1, 104($sp)
bnez $s1 if6stmt
j ELSE6stmt
if6stmt: 
# flag_2 = 0
li $t0, 0
sw $t0, 20($sp)
j if6_END
ELSE6stmt: 
# flag_2 = 1
li $t0, 1
sw $t0, 20($sp)
if6_END: 
# print_str, \nflag = 
li $v0, 4
la $a0, str1
syscall 
# temp_68 = flag_2
lw $t0, 20($sp)
sw $t0, 108($sp)
# print_int, temp_68
li $v0, 1
lw $a0, 108($sp)
syscall 
# print_str,  : c1 = 
li $v0, 4
la $a0, str2
syscall 
# temp_69 = c1_1
lw $t0, 40($sp)
sw $t0, 112($sp)
# print_int, temp_69
li $v0, 1
lw $a0, 112($sp)
syscall 
# print_str, , c2 = 
li $v0, 4
la $a0, str3
syscall 
# temp_70 = c2_1
lw $t0, 44($sp)
sw $t0, 116($sp)
# print_int, temp_70
li $v0, 1
lw $a0, 116($sp)
syscall 
# temp_71 = i_1
lw $t0, 24($sp)
sw $t0, 120($sp)
# temp_72 = temp_71+1
lw $t1, 120($sp)
addi $t0, $t1, 1
sw $t0, 124($sp)
# i_1 = temp_72
lw $t0, 124($sp)
sw $t0, 24($sp)
# temp_73 = a_3
lw $t0, 4($sp)
sw $t0, 128($sp)
# temp_74 = temp_73+5
lw $t1, 128($sp)
addi $t0, $t1, 5
sw $t0, 132($sp)
# a_3 = temp_74
lw $t0, 132($sp)
sw $t0, 4($sp)
# temp_75 = b_3
lw $t0, 8($sp)
sw $t0, 136($sp)
# temp_76 = temp_75+5
lw $t1, 136($sp)
addi $t0, $t1, 5
sw $t0, 140($sp)
# b_3 = temp_76
lw $t0, 140($sp)
sw $t0, 8($sp)
if7: 
# temp_77 = i_1
lw $t0, 24($sp)
sw $t0, 144($sp)
lw $s2, 144($sp)
li $s3, 10
bge $s2 $s3 if7stmt
j if7_END
if7stmt: 
j for1CHANGE_VAL
if7_END: 
if8: 
# temp_78 = i_1
lw $t0, 24($sp)
sw $t0, 148($sp)
lw $s2, 148($sp)
bltz $s2 if8stmt
j if8_END
if8stmt: 
j for1END
if8_END: 
for1CHANGE_VAL: 
j for1
for1END: 
if5_END: 
# ret 0
li $v0, 0
lw $ra, 0($sp)
addiu $sp, $sp, 168
jr $ra
print: 
addi $sp, $sp, -8
sw $a0, 0($sp)
# print_str, \nf2() flag : 
li $v0, 4
la $a0, str0
syscall 
# temp_1 = output_1
lw $t0, 0($sp)
sw $t0, 4($sp)
# print_int, temp_1
li $v0, 1
lw $a0, 4($sp)
syscall 
# ret 
addiu $sp, $sp, 8
jr $ra
f0: 
# ret 1
li $v0, 1
addiu $sp, $sp, 0
jr $ra
f1: 
addi $sp, $sp, -40
sw $a0, 0($sp)
sw $a1, 4($sp)
sw $a2, 8($sp)
# temp_2 = a_1[0]
lw $t0, 0($sp)
addi $t0, $t0, 0
lw $t0, 0($t0)
sw $t0, 12($sp)
# temp_3 = 3*0
li $t1, 3
li $t2, 0
mult $t1, $t2
mflo $t1
sw $t1, 16($sp)
# temp_4 = temp_3+0
lw $t1, 16($sp)
addi $t0, $t1, 0
sw $t0, 20($sp)
# temp_5 = b_1[temp_4]
lw $t0, 4($sp)
lw $t1, 20($sp)
sll $t1, $t1, 2
add $t0, $t0, $t1
lw $t0, 0($t0)
sw $t0, 24($sp)
# temp_6 = temp_2+temp_5
lw $t1, 12($sp)
lw $t2, 24($sp)
add $t0, $t1, $t2
sw $t0, 28($sp)
# temp_7 = c_1[0]
lw $t0, 8($sp)
addi $t0, $t0, 0
lw $t0, 0($t0)
sw $t0, 32($sp)
# temp_8 = temp_6+temp_7
lw $t1, 28($sp)
lw $t2, 32($sp)
add $t0, $t1, $t2
sw $t0, 36($sp)
# ret temp_8
lw $v0, 36($sp)
addiu $sp, $sp, 40
jr $ra
f2: 
addi $sp, $sp, -256
sw $ra, 0($sp)
sw $a0, 4($sp)
sw $a1, 8($sp)
# int c_2
# int flag_1
# int a1_1[2]
# a1_1[0] = 1
li $t0, 1
sw $t0, 20($sp)
# a1_1[1] = 2
li $t0, 2
sw $t0, 24($sp)
# int a2_1[9]
# a2_1[0] = 1
li $t0, 1
sw $t0, 28($sp)
# a2_1[1] = 2
li $t0, 2
sw $t0, 32($sp)
# a2_1[2] = 3
li $t0, 3
sw $t0, 36($sp)
# a2_1[3] = 1
li $t0, 1
sw $t0, 40($sp)
# a2_1[4] = 2
li $t0, 2
sw $t0, 44($sp)
# a2_1[5] = 3
li $t0, 3
sw $t0, 48($sp)
# a2_1[6] = 1
li $t0, 1
sw $t0, 52($sp)
# a2_1[7] = 2
li $t0, 2
sw $t0, 56($sp)
# a2_1[8] = 3
li $t0, 3
sw $t0, 60($sp)
# temp_9 = a_2
lw $t0, 4($sp)
sw $t0, 64($sp)
# temp_10 = b_2
lw $t0, 8($sp)
sw $t0, 68($sp)
# temp_11 = temp_9*temp_10
lw $t1, 64($sp)
lw $t2, 68($sp)
mult $t1, $t2
mflo $t1
sw $t1, 72($sp)
# temp_12 = 0-temp_11
li $t1, 0
lw $t2, 72($sp)
sub $t0, $t1, $t2
sw $t0, 76($sp)
# temp_13 = a_2
lw $t0, 4($sp)
sw $t0, 80($sp)
# temp_14 = b_2
lw $t0, 8($sp)
sw $t0, 84($sp)
# temp_15 = temp_13/temp_14
lw $t1, 80($sp)
lw $t2, 84($sp)
div $t1, $t2
mflo $t1
sw $t1, 88($sp)
# temp_16 = temp_12+temp_15
lw $t1, 76($sp)
lw $t2, 88($sp)
add $t0, $t1, $t2
sw $t0, 92($sp)
# temp_17 = a_2
lw $t0, 4($sp)
sw $t0, 96($sp)
# temp_18 = b_2
lw $t0, 8($sp)
sw $t0, 100($sp)
# temp_19 = temp_17%temp_18
lw $t1, 96($sp)
lw $t2, 100($sp)
div $t1, $t2
mfhi $t1
sw $t1, 104($sp)
# temp_20 = temp_16+temp_19
lw $t1, 92($sp)
lw $t2, 104($sp)
add $t0, $t1, $t2
sw $t0, 108($sp)
# temp_21 = temp_20-1
lw $t1, 108($sp)
addi $t0, $t1, -1
sw $t0, 112($sp)
# temp_22 = a1_1
addi $t0, $sp, 20
sw $t0, 116($sp)
# temp_23 = a2_1
addi $t0, $sp, 28
sw $t0, 120($sp)
# temp_24 = 3*0
li $t1, 3
li $t2, 0
mult $t1, $t2
mflo $t1
sw $t1, 124($sp)
# temp_25 = temp_24*4
lw $t1, 124($sp)
li $t2, 4
mult $t1, $t2
mflo $t1
sw $t1, 128($sp)
# temp_26 = &a2_1+temp_25
addi $t1, $sp, 28
lw $t2, 128($sp)
add $t0, $t1, $t2
sw $t0, 132($sp)
# temp_27 = temp_26
lw $t0, 132($sp)
sw $t0, 136($sp)
# push temp_22
lw $a0, 116($sp)
# push temp_23
lw $a1, 120($sp)
# push temp_27
lw $a2, 136($sp)
# call f1
jal f1
lw $a0, 4($sp)
lw $a1, 8($sp)
# temp_28 = f1
move $t0, $v0
sw $t0, 140($sp)
# temp_29 = temp_21+temp_28
lw $t1, 112($sp)
lw $t2, 140($sp)
add $t0, $t1, $t2
sw $t0, 144($sp)
# call f0
jal f0
lw $a0, 4($sp)
lw $a1, 8($sp)
# temp_30 = f0
move $t0, $v0
sw $t0, 148($sp)
# temp_31 = 0+temp_30
lw $t1, 148($sp)
addi $t0, $t1, 0
sw $t0, 152($sp)
# temp_32 = temp_29-temp_31
lw $t1, 144($sp)
lw $t2, 152($sp)
sub $t0, $t1, $t2
sw $t0, 156($sp)
# c_2 = temp_32
lw $t0, 156($sp)
sw $t0, 12($sp)
if1: 
# temp_33 = a_2
lw $t0, 4($sp)
sw $t0, 160($sp)
# temp_34 = b_2
lw $t0, 8($sp)
sw $t0, 164($sp)
lw $s3, 160($sp)
li $s4, 10
ble $s3 $s4 tmpLabel0
lw $s3, 164($sp)
li $s4, 10
ble $s3 $s4 tmpLabel0
j if1stmt
tmpLabel0: 
j if1_END
if1stmt: 
# temp_35 = 1
li $t0, 1
sw $t0, 168($sp)
# push temp_35
lw $a0, 168($sp)
# call print
jal print
lw $a0, 4($sp)
lw $a1, 8($sp)
# temp_36 = print
move $t0, $v0
sw $t0, 172($sp)
# flag_1 = 0
li $t0, 0
sw $t0, 16($sp)
if1_END: 
if2: 
# temp_37 = a_2
lw $t0, 4($sp)
sw $t0, 176($sp)
# temp_38 = b_2
lw $t0, 8($sp)
sw $t0, 180($sp)
lw $s3, 176($sp)
li $s4, 10
ble $s3 $s4 tmpLabel1
lw $s3, 180($sp)
li $s4, 10
bgt $s3 $s4 tmpLabel1
j if2stmt
tmpLabel1: 
j if2_END
if2stmt: 
# temp_39 = 2
li $t0, 2
sw $t0, 184($sp)
# push temp_39
lw $a0, 184($sp)
# call print
jal print
lw $a0, 4($sp)
lw $a1, 8($sp)
# temp_40 = print
move $t0, $v0
sw $t0, 188($sp)
# flag_1 = 0
li $t0, 0
sw $t0, 16($sp)
if2_END: 
if3: 
# temp_41 = a_2
lw $t0, 4($sp)
sw $t0, 192($sp)
# temp_42 = b_2
lw $t0, 8($sp)
sw $t0, 196($sp)
lw $s3, 192($sp)
li $s4, 10
bgt $s3 $s4 tmpLabel2
lw $s3, 196($sp)
li $s4, 10
bgt $s3 $s4 tmpLabel2
j if3stmt
tmpLabel2: 
j if3_END
if3stmt: 
# temp_43 = 3
li $t0, 3
sw $t0, 200($sp)
# push temp_43
lw $a0, 200($sp)
# call print
jal print
lw $a0, 4($sp)
lw $a1, 8($sp)
# temp_44 = print
move $t0, $v0
sw $t0, 204($sp)
# flag_1 = 1
li $t0, 1
sw $t0, 16($sp)
if3_END: 
if4: 
# temp_45 = a_2
lw $t0, 4($sp)
sw $t0, 208($sp)
# temp_46 = b_2
lw $t0, 8($sp)
sw $t0, 212($sp)
lw $s3, 208($sp)
li $s4, 10
bgt $s3 $s4 tmpLabel3
lw $s3, 212($sp)
li $s4, 10
ble $s3 $s4 tmpLabel3
j if4stmt
tmpLabel3: 
j if4_END
if4stmt: 
# temp_47 = 4
li $t0, 4
sw $t0, 216($sp)
# push temp_47
lw $a0, 216($sp)
# call print
jal print
lw $a0, 4($sp)
lw $a1, 8($sp)
# temp_48 = print
move $t0, $v0
sw $t0, 220($sp)
# flag_1 = 1
li $t0, 1
sw $t0, 16($sp)
if4_END: 
# temp_49 = flag_1
lw $t0, 16($sp)
sw $t0, 224($sp)
# ret temp_49
lw $v0, 224($sp)
lw $ra, 0($sp)
addiu $sp, $sp, 256
jr $ra