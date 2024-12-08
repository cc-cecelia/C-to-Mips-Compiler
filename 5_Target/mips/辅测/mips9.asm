.data
# int next_1[8]
space0: .space 32
# const int len_1
space1: .space 4
# const int next1_1[8]
space2: .space 32
# const int xxx_1[2]
space3: .space 8
# int ans_1
space4: .space 4
# int arr_1[2]
space5: .space 8
str0:.asciiz ""
str1:.asciiz "\n"
str2:.asciiz "Hello "
str3:.asciiz "\n"
str4:.asciiz ""
str5:.asciiz "\n"
str6:.asciiz ""
str7:.asciiz "\n"
str8:.asciiz "judgeB 2,3 = "
str9:.asciiz "\n"
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
# len_1 = 3
li $t0, 3
la $s0, space1
sw $t0, 0($s0)
# next1_1[0] = 1
li $t0, 1
la $t1, space2
sw $t0, 0($t1)
# next1_1[1] = 0
li $t0, 0
la $t1, space2
sw $t0, 4($t1)
# next1_1[2] = 0
li $t0, 0
la $t1, space2
sw $t0, 8($t1)
# next1_1[3] = -1
li $t0, -1
la $t1, space2
sw $t0, 12($t1)
# next1_1[4] = -1
li $t0, -1
la $t1, space2
sw $t0, 16($t1)
# next1_1[5] = 0
li $t0, 0
la $t1, space2
sw $t0, 20($t1)
# next1_1[6] = 0
li $t0, 0
la $t1, space2
sw $t0, 24($t1)
# next1_1[7] = 1
li $t0, 1
la $t1, space2
sw $t0, 28($t1)
# xxx_1[0] = 1
li $t0, 1
la $t1, space3
sw $t0, 0($t1)
# xxx_1[1] = 2
li $t0, 2
la $t1, space3
sw $t0, 4($t1)
# ans_1 = 0
li $t0, 0
la $s0, space4
sw $t0, 0($s0)
# arr_1[0] = 3
li $t0, 3
la $t1, space5
sw $t0, 0($t1)
# arr_1[1] = 1
li $t0, 1
la $t1, space5
sw $t0, 4($t1)
jal main
li $v0, 10
syscall 
main: 
addi $sp, $sp, -208
sw $ra, 0($sp)
# int ans_3
# ans_3 = 3
li $t0, 3
sw $t0, 4($sp)
# int i_4
# i_4 = 0
li $t0, 0
sw $t0, 8($sp)
# int y_1
# y_1 = 1
li $t0, 1
sw $t0, 12($sp)
if8: 
# temp_55 = y_1
lw $t0, 12($sp)
sw $t0, 16($sp)
# temp_56 = 0!temp_55
li $t1, 0
nor $t0, $t1, $0
sw $t0, 20($sp)
lw $s0, 20($sp)
beqz $s0 if8_END
if8stmt: 
# y_1 = 0
li $t0, 0
sw $t0, 12($sp)
if8_END: 
# y_1 = 1
li $t0, 1
sw $t0, 12($sp)
# y_1 = -1
li $t0, -1
sw $t0, 12($sp)
# int xx_1
# temp_57 = i_4
lw $t0, 8($sp)
sw $t0, 28($sp)
# temp_58 = temp_57+1
lw $t1, 28($sp)
addi $t0, $t1, 1
sw $t0, 32($sp)
# temp_59 = 18/temp_58
li $t1, 18
lw $t2, 32($sp)
div $t1, $t2
mflo $t1
sw $t1, 36($sp)
# temp_60 = temp_59-14
lw $t1, 36($sp)
addi $t0, $t1, -14
sw $t0, 40($sp)
# temp_61 = 2*0
li $t1, 2
li $t2, 0
mult $t1, $t2
mflo $t1
sw $t1, 44($sp)
# temp_62 = temp_61+0
lw $t1, 44($sp)
addi $t0, $t1, 0
sw $t0, 48($sp)
# temp_63 = next_1[temp_62]
la $s1, space0
lw $t0, 48($sp)
sll $t0, $t0, 2
add $s1, $s1, $t0
lw $s1, 0($s1)
sw $s1, 52($sp)
# temp_64 = temp_60+temp_63
lw $t1, 40($sp)
lw $t2, 52($sp)
add $t0, $t1, $t2
sw $t0, 56($sp)
# xx_1 = temp_64
lw $t0, 56($sp)
sw $t0, 24($sp)
for6: 
# temp_65 = i_4
lw $t0, 8($sp)
sw $t0, 60($sp)
lw $s1, 60($sp)
li $s2, 5
bgt $s1 $s2 for6END
for6STMT: 
if9: 
# temp_66 = i_4
lw $t0, 8($sp)
sw $t0, 64($sp)
lw $s1, 64($sp)
li $s2, 3
beq $s1 $s2 if9stmt
j if9_END
if9stmt: 
# temp_67 = i_4
lw $t0, 8($sp)
sw $t0, 68($sp)
# temp_68 = temp_67+1
lw $t1, 68($sp)
addi $t0, $t1, 1
sw $t0, 72($sp)
# i_4 = temp_68
lw $t0, 72($sp)
sw $t0, 8($sp)
j for6CHANGE_VAL
if9_END: 
if10: 
# temp_69 = i_4
lw $t0, 8($sp)
sw $t0, 76($sp)
lw $s1, 76($sp)
li $s2, 5
beq $s1 $s2 if10stmt
j ELSE10stmt
if10stmt: 
j for6END
j if10_END
ELSE10stmt: 
# int j_2
# temp_70 = i_4
lw $t0, 8($sp)
sw $t0, 84($sp)
# j_2 = temp_70
lw $t0, 84($sp)
sw $t0, 80($sp)
if10_END: 
# temp_71 = i_4
lw $t0, 8($sp)
sw $t0, 88($sp)
# temp_72 = temp_71+1
lw $t1, 88($sp)
addi $t0, $t1, 1
sw $t0, 92($sp)
# i_4 = temp_72
lw $t0, 92($sp)
sw $t0, 8($sp)
for6CHANGE_VAL: 
j for6
for6END: 
# ans_3 = getint()
li $v0, 5
syscall 
sw $v0, 4($sp)
# print_str, 
li $v0, 4
la $a0, str4
syscall 
# temp_73 = ans_3
lw $t0, 4($sp)
sw $t0, 96($sp)
# push temp_73
lw $a0, 96($sp)
# call add
jal add
# temp_74 = add
move $t0, $v0
sw $t0, 100($sp)
# print_int, temp_74
li $v0, 1
lw $a0, 100($sp)
syscall 
# print_str, \n
li $v0, 4
la $a0, str5
syscall 
# temp_75 = 1
li $t0, 1
sw $t0, 104($sp)
# temp_76 = 999
li $t0, 999
sw $t0, 108($sp)
# push temp_75
lw $a0, 104($sp)
# push temp_76
lw $a1, 108($sp)
# call getDif3N
jal getDif3N
# temp_77 = getDif3N
move $t0, $v0
sw $t0, 112($sp)
# ans_3 = temp_77
lw $t0, 112($sp)
sw $t0, 4($sp)
# print_str, 
li $v0, 4
la $a0, str6
syscall 
# temp_78 = ans_3
lw $t0, 4($sp)
sw $t0, 116($sp)
# print_int, temp_78
li $v0, 1
lw $a0, 116($sp)
syscall 
# print_str, \n
li $v0, 4
la $a0, str7
syscall 
# temp_79 = 2
li $t0, 2
sw $t0, 120($sp)
# temp_80 = 3
li $t0, 3
sw $t0, 124($sp)
# push temp_79
lw $a0, 120($sp)
# push temp_80
lw $a1, 124($sp)
# call judgeB
jal judgeB
# temp_81 = judgeB
move $t0, $v0
sw $t0, 128($sp)
# ans_3 = temp_81
lw $t0, 128($sp)
sw $t0, 4($sp)
# print_str, judgeB 2,3 = 
li $v0, 4
la $a0, str8
syscall 
# temp_82 = ans_3
lw $t0, 4($sp)
sw $t0, 132($sp)
# print_int, temp_82
li $v0, 1
lw $a0, 132($sp)
syscall 
# print_str, \n
li $v0, 4
la $a0, str9
syscall 
# call printHello
jal printHello
# temp_83 = printHello
move $t0, $v0
sw $t0, 136($sp)
# temp_84 = next_1
la $s1, space0
sw $s1, 140($sp)
# push temp_84
lw $a0, 140($sp)
# call printArr2
jal printArr2
# temp_85 = printArr2
move $t0, $v0
sw $t0, 144($sp)
# temp_86 = 2*0
li $t1, 2
li $t2, 0
mult $t1, $t2
mflo $t1
sw $t1, 148($sp)
# temp_87 = temp_86*4
lw $t1, 148($sp)
li $t2, 4
mult $t1, $t2
mflo $t1
sw $t1, 152($sp)
# temp_88 = &next_1+temp_87
la $t1, space0
lw $t2, 152($sp)
add $t0, $t1, $t2
sw $t0, 156($sp)
# temp_89 = temp_88
lw $t0, 156($sp)
sw $t0, 160($sp)
# push temp_89
lw $a0, 160($sp)
# call printArr
jal printArr
# temp_90 = printArr
move $t0, $v0
sw $t0, 164($sp)
# temp_91 = arr_1
la $s1, space5
sw $s1, 168($sp)
# push temp_91
lw $a0, 168($sp)
# call printArr
jal printArr
# temp_92 = printArr
move $t0, $v0
sw $t0, 172($sp)
# ret 0
li $v0, 0
lw $ra, 0($sp)
addiu $sp, $sp, 208
jr $ra
getDif3N: 
addi $sp, $sp, -104
sw $a0, 0($sp)
sw $a1, 4($sp)
# int i_1
# i_1 = 1
li $t0, 1
sw $t0, 8($sp)
# int j_1
# j_1 = 1
li $t0, 1
sw $t0, 12($sp)
# int k_1
# k_1 = 1
li $t0, 1
sw $t0, 16($sp)
# int cnt_1
# cnt_1 = 0
li $t0, 0
sw $t0, 20($sp)
# int arr1_1[2]
# arr1_1[0] = 1
li $t0, 1
sw $t0, 24($sp)
# arr1_1[1] = 2
li $t0, 2
sw $t0, 28($sp)
for1: 
# temp_1 = i_1
lw $t0, 8($sp)
sw $t0, 32($sp)
lw $s1, 32($sp)
li $s2, 3
bgt $s1 $s2 for1END
for1STMT: 
# j_1 = 1
li $t0, 1
sw $t0, 12($sp)
for2: 
# temp_2 = j_1
lw $t0, 12($sp)
sw $t0, 36($sp)
lw $s1, 36($sp)
li $s2, 3
bgt $s1 $s2 for2END
for2STMT: 
# k_1 = 1
li $t0, 1
sw $t0, 16($sp)
for3: 
# temp_3 = k_1
lw $t0, 16($sp)
sw $t0, 40($sp)
lw $s1, 40($sp)
li $s2, 3
bgt $s1 $s2 for3END
for3STMT: 
if1: 
# temp_4 = i_1
lw $t0, 8($sp)
sw $t0, 44($sp)
# temp_5 = j_1
lw $t0, 12($sp)
sw $t0, 48($sp)
# temp_6 = i_1
lw $t0, 8($sp)
sw $t0, 52($sp)
# temp_7 = k_1
lw $t0, 16($sp)
sw $t0, 56($sp)
# temp_8 = j_1
lw $t0, 12($sp)
sw $t0, 60($sp)
# temp_9 = k_1
lw $t0, 16($sp)
sw $t0, 64($sp)
lw $s1, 44($sp)
lw $s2, 48($sp)
beq $s1 $s2 tmpLabel0
lw $s1, 52($sp)
lw $s2, 56($sp)
beq $s1 $s2 tmpLabel0
lw $s1, 60($sp)
lw $s2, 64($sp)
beq $s1 $s2 tmpLabel0
j if1stmt
tmpLabel0: 
j if1_END
if1stmt: 
# temp_10 = cnt_1
lw $t0, 20($sp)
sw $t0, 68($sp)
# temp_11 = temp_10+1
lw $t1, 68($sp)
addi $t0, $t1, 1
sw $t0, 72($sp)
# cnt_1 = temp_11
lw $t0, 72($sp)
sw $t0, 20($sp)
if1_END: 
# temp_12 = k_1
lw $t0, 16($sp)
sw $t0, 76($sp)
# temp_13 = temp_12+1
lw $t1, 76($sp)
addi $t0, $t1, 1
sw $t0, 80($sp)
# k_1 = temp_13
lw $t0, 80($sp)
sw $t0, 16($sp)
for3CHANGE_VAL: 
j for3
for3END: 
# temp_14 = j_1
lw $t0, 12($sp)
sw $t0, 84($sp)
# temp_15 = temp_14+1
lw $t1, 84($sp)
addi $t0, $t1, 1
sw $t0, 88($sp)
# j_1 = temp_15
lw $t0, 88($sp)
sw $t0, 12($sp)
for2CHANGE_VAL: 
j for2
for2END: 
# temp_16 = i_1
lw $t0, 8($sp)
sw $t0, 92($sp)
# temp_17 = temp_16+1
lw $t1, 92($sp)
addi $t0, $t1, 1
sw $t0, 96($sp)
# i_1 = temp_17
lw $t0, 96($sp)
sw $t0, 8($sp)
for1CHANGE_VAL: 
j for1
for1END: 
# temp_18 = cnt_1
lw $t0, 20($sp)
sw $t0, 100($sp)
# ret temp_18
lw $v0, 100($sp)
addiu $sp, $sp, 104
jr $ra
judgeB: 
addi $sp, $sp, -80
sw $a0, 0($sp)
sw $a1, 4($sp)
if2: 
# temp_19 = a_1
lw $t0, 0($sp)
sw $t0, 8($sp)
# temp_20 = b_1
lw $t0, 4($sp)
sw $t0, 12($sp)
lw $s1, 8($sp)
lw $s2, 12($sp)
ble $s1 $s2 if2stmt
j ELSE2stmt
if2stmt: 
if3: 
# temp_21 = a_1
lw $t0, 0($sp)
sw $t0, 16($sp)
# temp_22 = b_1
lw $t0, 4($sp)
sw $t0, 20($sp)
lw $s1, 16($sp)
lw $s2, 20($sp)
blt $s1 $s2 if3stmt
j ELSE3stmt
if3stmt: 
# temp_23 = a_1
lw $t0, 0($sp)
sw $t0, 24($sp)
# temp_24 = b_1
lw $t0, 4($sp)
sw $t0, 28($sp)
# temp_25 = temp_23-temp_24
lw $t1, 24($sp)
lw $t2, 28($sp)
sub $t0, $t1, $t2
sw $t0, 32($sp)
# ret temp_25
lw $v0, 32($sp)
addiu $sp, $sp, 80
jr $ra
j if3_END
ELSE3stmt: 
if4: 
# temp_26 = a_1
lw $t0, 0($sp)
sw $t0, 36($sp)
# temp_27 = b_1
lw $t0, 4($sp)
sw $t0, 40($sp)
lw $s1, 36($sp)
lw $s2, 40($sp)
beq $s1 $s2 if4stmt
j if4_END
if4stmt: 
# ret 0
li $v0, 0
addiu $sp, $sp, 80
jr $ra
if4_END: 
if3_END: 
j if2_END
ELSE2stmt: 
if5: 
# temp_28 = a_1
lw $t0, 0($sp)
sw $t0, 44($sp)
# temp_29 = b_1
lw $t0, 4($sp)
sw $t0, 48($sp)
lw $s1, 44($sp)
lw $s2, 48($sp)
bge $s1 $s2 if5stmt
j if5_END
if5stmt: 
if6: 
# temp_30 = a_1
lw $t0, 0($sp)
sw $t0, 52($sp)
# temp_31 = b_1
lw $t0, 4($sp)
sw $t0, 56($sp)
lw $s1, 52($sp)
lw $s2, 56($sp)
bgt $s1 $s2 if6stmt
j ELSE6stmt
if6stmt: 
# temp_32 = a_1
lw $t0, 0($sp)
sw $t0, 60($sp)
# temp_33 = b_1
lw $t0, 4($sp)
sw $t0, 64($sp)
# temp_34 = temp_32-temp_33
lw $t1, 60($sp)
lw $t2, 64($sp)
sub $t0, $t1, $t2
sw $t0, 68($sp)
# ret temp_34
lw $v0, 68($sp)
addiu $sp, $sp, 80
jr $ra
j if6_END
ELSE6stmt: 
if7: 
# temp_35 = a_1
lw $t0, 0($sp)
sw $t0, 72($sp)
# temp_36 = b_1
lw $t0, 4($sp)
sw $t0, 76($sp)
lw $s1, 72($sp)
lw $s2, 76($sp)
beq $s1 $s2 if7stmt
j if7_END
if7stmt: 
# ret 0
li $v0, 0
addiu $sp, $sp, 80
jr $ra
if7_END: 
if6_END: 
if5_END: 
if2_END: 
# ret 0
li $v0, 0
addiu $sp, $sp, 80
jr $ra
printArr: 
addi $sp, $sp, -28
sw $a0, 0($sp)
# int i_2
# i_2 = 0
li $t0, 0
sw $t0, 4($sp)
for4: 
# temp_37 = i_2
lw $t0, 4($sp)
sw $t0, 8($sp)
lw $s1, 8($sp)
li $s2, 2
bge $s1 $s2 for4END
for4STMT: 
# print_str, 
li $v0, 4
la $a0, str0
syscall 
# temp_38 = i_2
lw $t0, 4($sp)
sw $t0, 12($sp)
# temp_39 = a_2[temp_38]
lw $t0, 0($sp)
lw $t1, 12($sp)
sll $t1, $t1, 2
add $t0, $t0, $t1
lw $t0, 0($t0)
sw $t0, 16($sp)
# print_int, temp_39
li $v0, 1
lw $a0, 16($sp)
syscall 
# temp_40 = i_2
lw $t0, 4($sp)
sw $t0, 20($sp)
# temp_41 = temp_40+1
lw $t1, 20($sp)
addi $t0, $t1, 1
sw $t0, 24($sp)
# i_2 = temp_41
lw $t0, 24($sp)
sw $t0, 4($sp)
for4CHANGE_VAL: 
j for4
for4END: 
# print_str, \n
li $v0, 4
la $a0, str1
syscall 
# ret 
addiu $sp, $sp, 28
jr $ra
printArr2: 
addi $sp, $sp, -52
sw $ra, 0($sp)
sw $a0, 4($sp)
# int i_3
# i_3 = 0
li $t0, 0
sw $t0, 8($sp)
for5: 
# temp_42 = i_3
lw $t0, 8($sp)
sw $t0, 12($sp)
lw $s1, 12($sp)
li $s2, 4
bge $s1 $s2 for5END
for5STMT: 
# temp_43 = i_3
lw $t0, 8($sp)
sw $t0, 16($sp)
# temp_44 = 2*temp_43
li $t1, 2
lw $t2, 16($sp)
mult $t1, $t2
mflo $t1
sw $t1, 20($sp)
# temp_45 = temp_44*4
lw $t1, 20($sp)
li $t2, 4
mult $t1, $t2
mflo $t1
sw $t1, 24($sp)
# temp_46 = &a_3+temp_45
lw $t1, 4($sp)
lw $t2, 24($sp)
add $t0, $t1, $t2
sw $t0, 28($sp)
# temp_47 = temp_46
lw $t0, 28($sp)
sw $t0, 32($sp)
# push temp_47
lw $a0, 32($sp)
# call printArr
jal printArr
lw $a0, 4($sp)
# temp_48 = printArr
move $t0, $v0
sw $t0, 36($sp)
# temp_49 = i_3
lw $t0, 8($sp)
sw $t0, 40($sp)
# temp_50 = temp_49+1
lw $t1, 40($sp)
addi $t0, $t1, 1
sw $t0, 44($sp)
# i_3 = temp_50
lw $t0, 44($sp)
sw $t0, 8($sp)
for5CHANGE_VAL: 
j for5
for5END: 
# ret 
lw $ra, 0($sp)
addiu $sp, $sp, 52
jr $ra
printHello: 
addi $sp, $sp, -8
# int name_1
# name_1 = getint()
li $v0, 5
syscall 
sw $v0, 0($sp)
# print_str, Hello 
li $v0, 4
la $a0, str2
syscall 
# temp_51 = name_1
lw $t0, 0($sp)
sw $t0, 4($sp)
# print_int, temp_51
li $v0, 1
lw $a0, 4($sp)
syscall 
# print_str, \n
li $v0, 4
la $a0, str3
syscall 
# ret 
addiu $sp, $sp, 8
jr $ra
add: 
addi $sp, $sp, -20
sw $a0, 0($sp)
# int ans_2
# temp_52 = n_1
lw $t0, 0($sp)
sw $t0, 8($sp)
# temp_53 = temp_52+3
lw $t1, 8($sp)
addi $t0, $t1, 3
sw $t0, 12($sp)
# ans_2 = temp_53
lw $t0, 12($sp)
sw $t0, 4($sp)
# temp_54 = ans_2
lw $t0, 4($sp)
sw $t0, 16($sp)
# ret temp_54
lw $v0, 16($sp)
addiu $sp, $sp, 20
jr $ra
opp: 
# ret 
addiu $sp, $sp, 0
jr $ra