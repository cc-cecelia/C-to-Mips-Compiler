.data
# int u_1
space0: .space 4
# int v_1
space1: .space 4
# int s_1
space2: .space 4
# int dim_2_1[9]
space3: .space 36
# int array_1[4]
space4: .space 16
str0:.asciiz "a = "
str1:.asciiz "\n"
str2:.asciiz "b = "
str3:.asciiz "\n"
str4:.asciiz "first: "
str5:.asciiz "first : "
str6:.asciiz ""
str7:.asciiz "\n"
str8:.asciiz ""
str9:.asciiz " is not 2\n"
str10:.asciiz "xxxx\n"
.text
# u_1 = 0
li $t0, 0
la $s0, space0
sw $t0, 0($s0)
# v_1 = 6
li $t0, 6
la $s0, space1
sw $t0, 0($s0)
# s_1 = 9
li $t0, 9
la $s0, space2
sw $t0, 0($s0)
# array_1[0] = 1
li $t0, 1
la $t1, space4
sw $t0, 0($t1)
# array_1[1] = 2
li $t0, 2
la $t1, space4
sw $t0, 4($t1)
# array_1[2] = 2
li $t0, 2
la $t1, space4
sw $t0, 8($t1)
# array_1[3] = 4
li $t0, 4
la $t1, space4
sw $t0, 12($t1)
jal main
li $v0, 10
syscall 
main: 
addi $sp, $sp, -444
sw $ra, 0($sp)
# int var_array_1[7]
# var_array_1[0] = 1
li $t0, 1
sw $t0, 4($sp)
# var_array_1[1] = 2
li $t0, 2
sw $t0, 8($sp)
# var_array_1[2] = 3
li $t0, 3
sw $t0, 12($sp)
# var_array_1[3] = 4
li $t0, 4
sw $t0, 16($sp)
# var_array_1[4] = 5
li $t0, 5
sw $t0, 20($sp)
# var_array_1[5] = 7
li $t0, 7
sw $t0, 24($sp)
# var_array_1[6] = 8
li $t0, 8
sw $t0, 28($sp)
# const int c_array_1[2]
# c_array_1[0] = 2
li $t0, 2
sw $t0, 32($sp)
# c_array_1[1] = 6
li $t0, 6
sw $t0, 36($sp)
# int var_AAA_1[1]
# var_AAA_1[0] = 1
li $t0, 1
sw $t0, 40($sp)
# int var_2dim_1[4]
# temp_21 = var_array_1[0]
lw $t0, 4($sp)
sw $t0, 60($sp)
# temp_22 = var_array_1[1]
lw $t0, 8($sp)
sw $t0, 64($sp)
# var_2dim_1[0] = 1
li $t0, 1
sw $t0, 44($sp)
# var_2dim_1[1] = 2
li $t0, 2
sw $t0, 48($sp)
# var_2dim_1[2] = temp_21
lw $t0, 60($sp)
sw $t0, 52($sp)
# var_2dim_1[3] = temp_22
lw $t0, 64($sp)
sw $t0, 56($sp)
# const int ONE_1
# ONE_1 = 1
li $t0, 1
sw $t0, 68($sp)
if1: 
beqz $0 tmpLabel0
li $s0, 1
beqz $s0 tmpLabel0
j if1stmt
tmpLabel0: 
j ELSE1stmt
if1stmt: 
# var_array_1[1] = 3
li $t0, 3
sw $t0, 8($sp)
j if1_END
ELSE1stmt: 
if2: 
li $s0, 1
bnez $s0 if2stmt
bnez $0 if2stmt
j if2_END
if2stmt: 
if3: 
# temp_23 = var_array_1[0]
lw $t0, 4($sp)
sw $t0, 72($sp)
# temp_24 = temp_23+99
lw $t1, 72($sp)
addi $t0, $t1, 99
sw $t0, 76($sp)
lw $s0, 76($sp)
li $s1, 99
blt $s0 $s1 if3stmt
j if3_END
if3stmt: 
# call add
jal add
# temp_25 = add
move $t0, $v0
sw $t0, 80($sp)
# temp_26 = 99/temp_25
li $t1, 99
lw $t2, 80($sp)
div $t1, $t2
mflo $t1
sw $t1, 84($sp)
# var_array_1[1] = temp_26
lw $t0, 84($sp)
sw $t0, 8($sp)
if3_END: 
if2_END: 
if1_END: 
# temp_27 = var_array_1[1]
lw $t0, 8($sp)
sw $t0, 88($sp)
# temp_28 = var_array_1[2]
lw $t0, 12($sp)
sw $t0, 92($sp)
# push temp_27
lw $a0, 88($sp)
# push temp_28
lw $a1, 92($sp)
# call add1
jal add1
# temp_29 = add1
move $t0, $v0
sw $t0, 96($sp)
# int input_n_1
# int input_m_1
# input_n_1 = getint()
li $v0, 5
syscall 
sw $v0, 100($sp)
# temp_30 = input_n_1
lw $t0, 100($sp)
sw $t0, 108($sp)
# temp_31 = 2*0
li $t1, 2
li $t2, 0
mult $t1, $t2
mflo $t1
sw $t1, 112($sp)
# temp_32 = temp_31+1
lw $t1, 112($sp)
addi $t0, $t1, 1
sw $t0, 116($sp)
# temp_33 = array_1[temp_32]
la $s0, space4
lw $t0, 116($sp)
sll $t0, $t0, 2
add $s0, $s0, $t0
lw $s0, 0($s0)
sw $s0, 120($sp)
# temp_34 = 0+temp_33
lw $t1, 120($sp)
addi $t0, $t1, 0
sw $t0, 124($sp)
# push temp_30
lw $a0, 108($sp)
# push temp_34
lw $a1, 124($sp)
# call add1
jal add1
# temp_35 = add1
move $t0, $v0
sw $t0, 128($sp)
# temp_36 = input_n_1
lw $t0, 100($sp)
sw $t0, 132($sp)
# temp_37 = 0-temp_36
li $t1, 0
lw $t2, 132($sp)
sub $t0, $t1, $t2
sw $t0, 136($sp)
# temp_38 = 2*0
li $t1, 2
li $t2, 0
mult $t1, $t2
mflo $t1
sw $t1, 140($sp)
# temp_39 = temp_38+1
lw $t1, 140($sp)
addi $t0, $t1, 1
sw $t0, 144($sp)
# temp_40 = array_1[temp_39]
la $s0, space4
lw $t0, 144($sp)
sll $t0, $t0, 2
add $s0, $s0, $t0
lw $s0, 0($s0)
sw $s0, 148($sp)
# push temp_37
lw $a0, 136($sp)
# push temp_40
lw $a1, 148($sp)
# call add1
jal add1
# temp_41 = add1
move $t0, $v0
sw $t0, 152($sp)
# temp_42 = input_n_1
lw $t0, 100($sp)
sw $t0, 156($sp)
# temp_43 = 1
li $t0, 1
sw $t0, 160($sp)
# push temp_42
lw $a0, 156($sp)
# push temp_43
lw $a1, 160($sp)
# call add1
jal add1
# temp_44 = add1
move $t0, $v0
sw $t0, 164($sp)
# temp_45 = 1
li $t0, 1
sw $t0, 168($sp)
# temp_46 = 2
li $t0, 2
sw $t0, 172($sp)
# temp_47 = 3
li $t0, 3
sw $t0, 176($sp)
# push temp_45
lw $a0, 168($sp)
# push temp_46
lw $a1, 172($sp)
# push temp_47
lw $a2, 176($sp)
# call addmmm
jal addmmm
# temp_48 = addmmm
move $t0, $v0
sw $t0, 180($sp)
# temp_49 = var_array_1[3]
lw $t0, 16($sp)
sw $t0, 184($sp)
# temp_50 = var_array_1[5]
lw $t0, 24($sp)
sw $t0, 188($sp)
# call add
jal add
# temp_51 = add
move $t0, $v0
sw $t0, 192($sp)
# temp_52 = temp_50*temp_51
lw $t1, 188($sp)
lw $t2, 192($sp)
mult $t1, $t2
mflo $t1
sw $t1, 196($sp)
# temp_53 = temp_49-temp_52
lw $t1, 184($sp)
lw $t2, 196($sp)
sub $t0, $t1, $t2
sw $t0, 200($sp)
# var_array_1[3] = temp_53
lw $t0, 200($sp)
sw $t0, 16($sp)
# int i_1
# int ui_1
# temp_54 = i_1
lw $t0, 204($sp)
sw $t0, 212($sp)
# temp_55 = 0+temp_54
lw $t1, 212($sp)
addi $t0, $t1, 0
sw $t0, 216($sp)
# temp_56 = 0-temp_55
li $t1, 0
lw $t2, 216($sp)
sub $t0, $t1, $t2
sw $t0, 220($sp)
# temp_57 = 0+temp_56
lw $t1, 220($sp)
addi $t0, $t1, 0
sw $t0, 224($sp)
# ui_1 = temp_57
lw $t0, 224($sp)
sw $t0, 208($sp)
# call add
jal add
# temp_58 = add
move $t0, $v0
sw $t0, 228($sp)
# i_1 = temp_58
lw $t0, 228($sp)
sw $t0, 204($sp)
# temp_59 = 1
li $t0, 1
sw $t0, 232($sp)
# temp_60 = 3
li $t0, 3
sw $t0, 236($sp)
# push temp_59
lw $a0, 232($sp)
# push temp_60
lw $a1, 236($sp)
# call add1
jal add1
# temp_61 = add1
move $t0, $v0
sw $t0, 240($sp)
# i_1 = temp_61
lw $t0, 240($sp)
sw $t0, 204($sp)
# temp_62 = i_1
lw $t0, 204($sp)
sw $t0, 244($sp)
# temp_63 = var_array_1[0]
lw $t0, 4($sp)
sw $t0, 248($sp)
# temp_64 = temp_62+temp_63
lw $t1, 244($sp)
lw $t2, 248($sp)
add $t0, $t1, $t2
sw $t0, 252($sp)
# i_1 = temp_64
lw $t0, 252($sp)
sw $t0, 204($sp)
# i_1 = 0
li $t0, 0
sw $t0, 204($sp)
if4: 
# temp_65 = i_1
lw $t0, 204($sp)
sw $t0, 256($sp)
lw $s0, 256($sp)
beqz $s0 if4stmt
j if4_END
if4stmt: 
if4_END: 
for1: 
li $s0, 1
beqz $s0 for1END
for1STMT: 
if5: 
# temp_66 = i_1
lw $t0, 204($sp)
sw $t0, 260($sp)
# temp_67 = var_array_1[temp_66]
lw $t1, 260($sp)
sll $t1, $t1, 2
addi $t0, $t1, 4
add $t0, $t0, $sp
lw $t0, 0($t0)
sw $t0, 264($sp)
lw $s0, 264($sp)
li $s1, 2
bne $s0 $s1 tmpLabel1
# temp_68 = var_array_1[0]
lw $t0, 4($sp)
sw $t0, 268($sp)
lw $s0, 268($sp)
blez $s0 tmpLabel1
j if5stmt
tmpLabel1: 
j ELSE5stmt
if5stmt: 
# print_str, 
li $v0, 4
la $a0, str6
syscall 
# temp_69 = i_1
lw $t0, 204($sp)
sw $t0, 272($sp)
# temp_70 = var_array_1[temp_69]
lw $t1, 272($sp)
sll $t1, $t1, 2
addi $t0, $t1, 4
add $t0, $t0, $sp
lw $t0, 0($t0)
sw $t0, 276($sp)
# print_int, temp_70
li $v0, 1
lw $a0, 276($sp)
syscall 
# print_str, \n
li $v0, 4
la $a0, str7
syscall 
j if5_END
ELSE5stmt: 
if5_END: 
if6: 
# temp_71 = i_1
lw $t0, 204($sp)
sw $t0, 280($sp)
# temp_72 = var_array_1[temp_71]
lw $t1, 280($sp)
sll $t1, $t1, 2
addi $t0, $t1, 4
add $t0, $t0, $sp
lw $t0, 0($t0)
sw $t0, 284($sp)
lw $s0, 284($sp)
li $s1, 2
bne $s0 $s1 if6stmt
j if6_END
if6stmt: 
# print_str, 
li $v0, 4
la $a0, str8
syscall 
# temp_73 = i_1
lw $t0, 204($sp)
sw $t0, 288($sp)
# temp_74 = var_array_1[temp_73]
lw $t1, 288($sp)
sll $t1, $t1, 2
addi $t0, $t1, 4
add $t0, $t0, $sp
lw $t0, 0($t0)
sw $t0, 292($sp)
# print_int, temp_74
li $v0, 1
lw $a0, 292($sp)
syscall 
# print_str,  is not 2\n
li $v0, 4
la $a0, str9
syscall 
if6_END: 
# print_str, xxxx\n
li $v0, 4
la $a0, str10
syscall 
# temp_75 = i_1
lw $t0, 204($sp)
sw $t0, 296($sp)
# temp_76 = temp_75+1
lw $t1, 296($sp)
addi $t0, $t1, 1
sw $t0, 300($sp)
# i_1 = temp_76
lw $t0, 300($sp)
sw $t0, 204($sp)
if7: 
# temp_77 = i_1
lw $t0, 204($sp)
sw $t0, 304($sp)
# temp_78 = var_array_1[temp_77]
lw $t1, 304($sp)
sll $t1, $t1, 2
addi $t0, $t1, 4
add $t0, $t0, $sp
lw $t0, 0($t0)
sw $t0, 308($sp)
# temp_79 = 0-temp_78
li $t1, 0
lw $t2, 308($sp)
sub $t0, $t1, $t2
sw $t0, 312($sp)
lw $s0, 312($sp)
bgez $s0 if7stmt
# temp_80 = var_array_1[0]
lw $t0, 4($sp)
sw $t0, 316($sp)
lw $s0, 316($sp)
bgtz $s0 tmpLabel2
li $s0, 1
beqz $s0 tmpLabel2
j if7stmt
tmpLabel2: 
j ELSE7stmt
if7stmt: 
# temp_81 = i_1
lw $t0, 204($sp)
sw $t0, 320($sp)
# temp_82 = i_1
lw $t0, 204($sp)
sw $t0, 324($sp)
# temp_83 = var_array_1[temp_82]
lw $t1, 324($sp)
sll $t1, $t1, 2
addi $t0, $t1, 4
add $t0, $t0, $sp
lw $t0, 0($t0)
sw $t0, 328($sp)
# temp_84 = temp_83*3
lw $t1, 328($sp)
li $t2, 3
mult $t1, $t2
mflo $t1
sw $t1, 332($sp)
# var_array_1[temp_81] = temp_84
lw $t0, 332($sp)
sw $t0, 324($sp)
j for1END
j if7_END
ELSE7stmt: 
j for1CHANGE_VAL
if7_END: 
for1CHANGE_VAL: 
j for1
for1END: 
# temp_85 = 2*0
li $t1, 2
li $t2, 0
mult $t1, $t2
mflo $t1
sw $t1, 336($sp)
# temp_86 = temp_85*4
lw $t1, 336($sp)
li $t2, 4
mult $t1, $t2
mflo $t1
sw $t1, 340($sp)
# temp_87 = &array_1+temp_86
la $t1, space4
lw $t2, 340($sp)
add $t0, $t1, $t2
sw $t0, 344($sp)
# temp_88 = temp_87
lw $t0, 344($sp)
sw $t0, 348($sp)
# temp_89 = 2*1
li $t1, 2
li $t2, 1
mult $t1, $t2
mflo $t1
sw $t1, 352($sp)
# temp_90 = temp_89*4
lw $t1, 352($sp)
li $t2, 4
mult $t1, $t2
mflo $t1
sw $t1, 356($sp)
# temp_91 = &array_1+temp_90
la $t1, space4
lw $t2, 356($sp)
add $t0, $t1, $t2
sw $t0, 360($sp)
# temp_92 = temp_91
lw $t0, 360($sp)
sw $t0, 364($sp)
# push temp_88
lw $a0, 348($sp)
# push temp_92
lw $a1, 364($sp)
# call printarr
jal printarr
# temp_93 = printarr
move $t0, $v0
sw $t0, 368($sp)
# temp_94 = array_1
la $s0, space4
sw $s0, 372($sp)
# push temp_94
lw $a0, 372($sp)
# call printarrrr
jal printarrrr
# temp_95 = printarrrr
move $t0, $v0
sw $t0, 376($sp)
# ret 0
li $v0, 0
lw $ra, 0($sp)
addiu $sp, $sp, 444
jr $ra
add_three: 
addi $sp, $sp, -32
sw $a0, 0($sp)
sw $a1, 4($sp)
sw $a2, 8($sp)
# temp_1 = a_1
lw $t0, 0($sp)
sw $t0, 12($sp)
# temp_2 = arr_1[0]
lw $t0, 4($sp)
addi $t0, $t0, 0
lw $t0, 0($t0)
sw $t0, 16($sp)
# temp_3 = temp_1+temp_2
lw $t1, 12($sp)
lw $t2, 16($sp)
add $t0, $t1, $t2
sw $t0, 20($sp)
# temp_4 = b_1
lw $t0, 8($sp)
sw $t0, 24($sp)
# temp_5 = temp_3+temp_4
lw $t1, 20($sp)
lw $t2, 24($sp)
add $t0, $t1, $t2
sw $t0, 28($sp)
# ret temp_5
lw $v0, 28($sp)
addiu $sp, $sp, 32
jr $ra
add1: 
addi $sp, $sp, -56
sw $a0, 0($sp)
sw $a1, 4($sp)
# int ans_1
# const int AAA_1
# AAA_1 = 3
li $t0, 3
sw $t0, 12($sp)
# const int BBB__1
# BBB__1 = 3
li $t0, 3
sw $t0, 16($sp)
# const int CCC_1
# CCC_1 = 9
li $t0, 9
sw $t0, 20($sp)
# const int a1_1
# a1_1 = 12
li $t0, 12
sw $t0, 24($sp)
# const int a2_1
# a2_1 = 22
li $t0, 22
sw $t0, 28($sp)
# temp_6 = a_2
lw $t0, 0($sp)
sw $t0, 32($sp)
# temp_7 = b_2
lw $t0, 4($sp)
sw $t0, 36($sp)
# temp_8 = temp_6+temp_7
lw $t1, 32($sp)
lw $t2, 36($sp)
add $t0, $t1, $t2
sw $t0, 40($sp)
# ans_1 = temp_8
lw $t0, 40($sp)
sw $t0, 8($sp)
# print_str, a = 
li $v0, 4
la $a0, str0
syscall 
# temp_9 = a_2
lw $t0, 0($sp)
sw $t0, 44($sp)
# print_int, temp_9
li $v0, 1
lw $a0, 44($sp)
syscall 
# print_str, \n
li $v0, 4
la $a0, str1
syscall 
# print_str, b = 
li $v0, 4
la $a0, str2
syscall 
# temp_10 = b_2
lw $t0, 4($sp)
sw $t0, 48($sp)
# print_int, temp_10
li $v0, 1
lw $a0, 48($sp)
syscall 
# print_str, \n
li $v0, 4
la $a0, str3
syscall 
# temp_11 = ans_1
lw $t0, 8($sp)
sw $t0, 52($sp)
# ret temp_11
lw $v0, 52($sp)
addiu $sp, $sp, 56
jr $ra
addmmm: 
addi $sp, $sp, -32
sw $a0, 0($sp)
sw $a1, 4($sp)
sw $a2, 8($sp)
# temp_12 = a_3
lw $t0, 0($sp)
sw $t0, 12($sp)
# temp_13 = b_3
lw $t0, 4($sp)
sw $t0, 16($sp)
# temp_14 = temp_12+temp_13
lw $t1, 12($sp)
lw $t2, 16($sp)
add $t0, $t1, $t2
sw $t0, 20($sp)
# temp_15 = c_1
lw $t0, 8($sp)
sw $t0, 24($sp)
# temp_16 = temp_14+temp_15
lw $t1, 20($sp)
lw $t2, 24($sp)
add $t0, $t1, $t2
sw $t0, 28($sp)
# ret temp_16
lw $v0, 28($sp)
addiu $sp, $sp, 32
jr $ra
add: 
# ret 99
li $v0, 99
addiu $sp, $sp, 0
jr $ra
printarrrr: 
addi $sp, $sp, -16
sw $a0, 0($sp)
# print_str, first: 
li $v0, 4
la $a0, str4
syscall 
# temp_17 = 2*0
li $t1, 2
li $t2, 0
mult $t1, $t2
mflo $t1
sw $t1, 4($sp)
# temp_18 = temp_17+0
lw $t1, 4($sp)
addi $t0, $t1, 0
sw $t0, 8($sp)
# temp_19 = arr_2[temp_18]
lw $t0, 0($sp)
lw $t1, 8($sp)
sll $t1, $t1, 2
add $t0, $t0, $t1
lw $t0, 0($t0)
sw $t0, 12($sp)
# print_int, temp_19
li $v0, 1
lw $a0, 12($sp)
syscall 
# ret 
addiu $sp, $sp, 16
jr $ra
printarr: 
addi $sp, $sp, -12
sw $a0, 0($sp)
sw $a1, 4($sp)
# print_str, first : 
li $v0, 4
la $a0, str5
syscall 
# temp_20 = arr_3[0]
lw $t0, 0($sp)
addi $t0, $t0, 0
lw $t0, 0($t0)
sw $t0, 8($sp)
# print_int, temp_20
li $v0, 1
lw $a0, 8($sp)
syscall 
# ret 
addiu $sp, $sp, 12
jr $ra