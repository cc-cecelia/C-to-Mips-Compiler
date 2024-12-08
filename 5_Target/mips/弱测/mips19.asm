.data
# int a_global_1
space0: .space 4
# const int b_global_1
space1: .space 4
str0:.asciiz "21182621\n"
str1:.asciiz "21182621\n"
str2:.asciiz "21182621\n"
str3:.asciiz "21182621\n"
str4:.asciiz "21182621\n"
str5:.asciiz ""
str6:.asciiz "\n"
str7:.asciiz "21182621\n"
str8:.asciiz "21182621\n"
str9:.asciiz "21182621\n"
str10:.asciiz "21182621\n"
.text
# b_global_1 = 1
li $t0, 1
la $s0, space1
sw $t0, 0($s0)
jal main
li $v0, 10
syscall 
main: 
addi $sp, $sp, -460
sw $ra, 0($sp)
# int gett_1
# gett_1 = getint()
li $v0, 5
syscall 
sw $v0, 4($sp)
# const int a_5
# a_5 = 0
li $t0, 0
sw $t0, 8($sp)
# const int aa_1
# aa_1 = 0
li $t0, 0
sw $t0, 12($sp)
# const int bb_1
# bb_1 = 1
li $t0, 1
sw $t0, 16($sp)
# const int aaa_1
# aaa_1 = 0
li $t0, 0
sw $t0, 20($sp)
# const int bbb_1
# bbb_1 = 1
li $t0, 1
sw $t0, 24($sp)
# const int ccc_1
# ccc_1 = 2
li $t0, 2
sw $t0, 28($sp)
# const int arr_a_1[2]
# arr_a_1[0] = 1
li $t0, 1
sw $t0, 32($sp)
# arr_a_1[1] = 2
li $t0, 2
sw $t0, 36($sp)
# const int arr_aa_1[2]
# arr_aa_1[0] = 1
li $t0, 1
sw $t0, 40($sp)
# arr_aa_1[1] = 2
li $t0, 2
sw $t0, 44($sp)
# const int arr_bb_1[3]
# arr_bb_1[0] = 3
li $t0, 3
sw $t0, 48($sp)
# arr_bb_1[1] = 4
li $t0, 4
sw $t0, 52($sp)
# arr_bb_1[2] = 5
li $t0, 5
sw $t0, 56($sp)
# const int arrrr_a_1[4]
# arrrr_a_1[0] = 1
li $t0, 1
sw $t0, 60($sp)
# arrrr_a_1[1] = 2
li $t0, 2
sw $t0, 64($sp)
# arrrr_a_1[2] = 1
li $t0, 1
sw $t0, 68($sp)
# arrrr_a_1[3] = 2
li $t0, 2
sw $t0, 72($sp)
# const int arrrr_aa_1[4]
# arrrr_aa_1[0] = 1
li $t0, 1
sw $t0, 76($sp)
# arrrr_aa_1[1] = 2
li $t0, 2
sw $t0, 80($sp)
# arrrr_aa_1[2] = 1
li $t0, 1
sw $t0, 84($sp)
# arrrr_aa_1[3] = 2
li $t0, 2
sw $t0, 88($sp)
# const int arrrr_bb_1[3]
# arrrr_bb_1[0] = 1
li $t0, 1
sw $t0, 92($sp)
# arrrr_bb_1[1] = 2
li $t0, 2
sw $t0, 96($sp)
# arrrr_bb_1[2] = 3
li $t0, 3
sw $t0, 100($sp)
# print_str, 21182621\n
li $v0, 4
la $a0, str2
syscall 
# int d_1
# d_1 = 0
li $t0, 0
sw $t0, 104($sp)
# int dd_1
# dd_1 = 0
li $t0, 0
sw $t0, 108($sp)
# int ee_1
# ee_1 = 1
li $t0, 1
sw $t0, 112($sp)
# int ff_1
# ff_1 = 2
li $t0, 2
sw $t0, 116($sp)
# int ddd_1
# int eee_1
# eee_1 = 1
li $t0, 1
sw $t0, 124($sp)
# ddd_1 = 0
li $t0, 0
sw $t0, 120($sp)
# int arr_d_1[2]
# arr_d_1[0] = 1
li $t0, 1
sw $t0, 128($sp)
# arr_d_1[1] = 2
li $t0, 2
sw $t0, 132($sp)
# int arr_dd_1[2]
# arr_dd_1[0] = 1
li $t0, 1
sw $t0, 136($sp)
# arr_dd_1[1] = 2
li $t0, 2
sw $t0, 140($sp)
# int arr_ee_1[3]
# arr_ee_1[0] = 3
li $t0, 3
sw $t0, 144($sp)
# arr_ee_1[1] = 4
li $t0, 4
sw $t0, 148($sp)
# arr_ee_1[2] = 5
li $t0, 5
sw $t0, 152($sp)
# int arr_ddd_1[2]
# int arr_eee_1[3]
# arr_eee_1[0] = 3
li $t0, 3
sw $t0, 164($sp)
# arr_eee_1[1] = 4
li $t0, 4
sw $t0, 168($sp)
# arr_eee_1[2] = 5
li $t0, 5
sw $t0, 172($sp)
# arr_ddd_1[1] = 1
li $t0, 1
sw $t0, 160($sp)
# arr_eee_1[1] = 3
li $t0, 3
sw $t0, 168($sp)
# int arrrr_d_1[4]
# arrrr_d_1[0] = 1
li $t0, 1
sw $t0, 176($sp)
# arrrr_d_1[1] = 2
li $t0, 2
sw $t0, 180($sp)
# arrrr_d_1[2] = 1
li $t0, 1
sw $t0, 184($sp)
# arrrr_d_1[3] = 2
li $t0, 2
sw $t0, 188($sp)
# int arrrr_dd_1[4]
# arrrr_dd_1[0] = 1
li $t0, 1
sw $t0, 192($sp)
# arrrr_dd_1[1] = 2
li $t0, 2
sw $t0, 196($sp)
# arrrr_dd_1[2] = 1
li $t0, 1
sw $t0, 200($sp)
# arrrr_dd_1[3] = 2
li $t0, 2
sw $t0, 204($sp)
# int arrrr_ee_1[3]
# arrrr_ee_1[0] = 1
li $t0, 1
sw $t0, 208($sp)
# arrrr_ee_1[1] = 2
li $t0, 2
sw $t0, 212($sp)
# arrrr_ee_1[2] = 3
li $t0, 3
sw $t0, 216($sp)
# int arrrr_ddd_1[4]
# arrrr_ddd_1[0] = 1
li $t0, 1
sw $t0, 220($sp)
# arrrr_ddd_1[1] = 2
li $t0, 2
sw $t0, 224($sp)
# arrrr_ddd_1[2] = 1
li $t0, 1
sw $t0, 228($sp)
# arrrr_ddd_1[3] = 2
li $t0, 2
sw $t0, 232($sp)
# int arrrr_eee_1[3]
# arrrr_eee_1[0] = 1
li $t0, 1
sw $t0, 236($sp)
# arrrr_eee_1[1] = 2
li $t0, 2
sw $t0, 240($sp)
# arrrr_eee_1[2] = 3
li $t0, 3
sw $t0, 244($sp)
# temp_15 = 2*1
li $t1, 2
li $t2, 1
mult $t1, $t2
mflo $t1
sw $t1, 248($sp)
# temp_16 = temp_15+1
lw $t1, 248($sp)
addi $t0, $t1, 1
sw $t0, 252($sp)
# arrrr_ddd_1[temp_16] = 1
li $t0, 1
sw $t0, 472($sp)
# temp_17 = 3*0
li $t1, 3
li $t2, 0
mult $t1, $t2
mflo $t1
sw $t1, 256($sp)
# temp_18 = temp_17+1
lw $t1, 256($sp)
addi $t0, $t1, 1
sw $t0, 260($sp)
# arrrr_eee_1[temp_18] = 1
li $t0, 1
sw $t0, 496($sp)
# print_str, 21182621\n
li $v0, 4
la $a0, str3
syscall 
# call func1
jal func1
# temp_19 = func1
move $t0, $v0
sw $t0, 264($sp)
# temp_20 = 1
li $t0, 1
sw $t0, 268($sp)
# push temp_20
lw $a0, 268($sp)
# call func2
jal func2
# temp_21 = func2
move $t0, $v0
sw $t0, 272($sp)
# temp_23 = 2*0
li $t1, 2
li $t2, 0
mult $t1, $t2
mflo $t1
sw $t1, 276($sp)
# temp_24 = temp_23*4
lw $t1, 276($sp)
li $t2, 4
mult $t1, $t2
mflo $t1
sw $t1, 280($sp)
# temp_25 = &arrrr_d_1+temp_24
addi $t1, $sp, 176
lw $t2, 280($sp)
add $t0, $t1, $t2
sw $t0, 284($sp)
# temp_26 = temp_25
lw $t0, 284($sp)
sw $t0, 288($sp)
# temp_27 = arrrr_d_1
addi $t0, $sp, 176
sw $t0, 292($sp)
# temp_22 = 1
li $t0, 1
sw $t0, 296($sp)
# push temp_22
lw $a0, 296($sp)
# push temp_26
lw $a1, 288($sp)
# push temp_27
lw $a2, 292($sp)
# call func3
jal func3
# temp_28 = func3
move $t0, $v0
sw $t0, 300($sp)
# call func4
jal func4
# temp_29 = func4
move $t0, $v0
sw $t0, 304($sp)
# temp_30 = 1
li $t0, 1
sw $t0, 308($sp)
# push temp_30
lw $a0, 308($sp)
# call func5
jal func5
# temp_31 = func5
move $t0, $v0
sw $t0, 312($sp)
# temp_33 = arr_d_1
addi $t0, $sp, 128
sw $t0, 316($sp)
# temp_32 = 1
li $t0, 1
sw $t0, 320($sp)
# push temp_32
lw $a0, 320($sp)
# push temp_33
lw $a1, 316($sp)
# call func6
jal func6
# temp_34 = func6
move $t0, $v0
sw $t0, 324($sp)
# print_str, 21182621\n
li $v0, 4
la $a0, str4
syscall 
# int i_1
# i_1 = 5
li $t0, 5
sw $t0, 328($sp)
# int j_1
# j_1 = 1
li $t0, 1
sw $t0, 332($sp)
for1: 
# temp_35 = j_1
lw $t0, 332($sp)
sw $t0, 336($sp)
lw $s0, 336($sp)
li $s1, 5
bgt $s0 $s1 for1END
for1STMT: 
for1CHANGE_VAL: 
# temp_36 = j_1
lw $t0, 332($sp)
sw $t0, 340($sp)
# temp_37 = temp_36+1
lw $t1, 340($sp)
addi $t0, $t1, 1
sw $t0, 344($sp)
# j_1 = temp_37
lw $t0, 344($sp)
sw $t0, 332($sp)
j for1
for1END: 
# j_1 = 1
li $t0, 1
sw $t0, 332($sp)
for2: 
# temp_38 = j_1
lw $t0, 332($sp)
sw $t0, 348($sp)
lw $s0, 348($sp)
li $s1, 5
bgt $s0 $s1 for2END
for2STMT: 
j for2CHANGE_VAL
for2CHANGE_VAL: 
# temp_39 = j_1
lw $t0, 332($sp)
sw $t0, 352($sp)
# temp_40 = temp_39+1
lw $t1, 352($sp)
addi $t0, $t1, 1
sw $t0, 356($sp)
# j_1 = temp_40
lw $t0, 356($sp)
sw $t0, 332($sp)
j for2
for2END: 
# j_1 = 1
li $t0, 1
sw $t0, 332($sp)
for3: 
for3STMT: 
j for3END
for3CHANGE_VAL: 
# temp_41 = j_1
lw $t0, 332($sp)
sw $t0, 360($sp)
# temp_42 = temp_41+1
lw $t1, 360($sp)
addi $t0, $t1, 1
sw $t0, 364($sp)
# j_1 = temp_42
lw $t0, 364($sp)
sw $t0, 332($sp)
j for3
for3END: 
# j_1 = 1
li $t0, 1
sw $t0, 332($sp)
for4: 
# temp_43 = j_1
lw $t0, 332($sp)
sw $t0, 368($sp)
lw $s0, 368($sp)
li $s1, 5
bgt $s0 $s1 for4END
for4STMT: 
# temp_44 = j_1
lw $t0, 332($sp)
sw $t0, 372($sp)
# temp_45 = temp_44+1
lw $t1, 372($sp)
addi $t0, $t1, 1
sw $t0, 376($sp)
# j_1 = temp_45
lw $t0, 376($sp)
sw $t0, 332($sp)
for4CHANGE_VAL: 
j for4
for4END: 
# j_1 = 1
li $t0, 1
sw $t0, 332($sp)
for5: 
for5STMT: 
j for5END
for5CHANGE_VAL: 
j for5
for5END: 
for6: 
# temp_46 = j_1
lw $t0, 332($sp)
sw $t0, 380($sp)
lw $s0, 380($sp)
li $s1, 5
bgt $s0 $s1 for6END
for6STMT: 
j for6END
for6CHANGE_VAL: 
j for6
for6END: 
for7: 
for7STMT: 
j for7END
for7CHANGE_VAL: 
# temp_47 = j_1
lw $t0, 332($sp)
sw $t0, 384($sp)
# temp_48 = temp_47+1
lw $t1, 384($sp)
addi $t0, $t1, 1
sw $t0, 388($sp)
# j_1 = temp_48
lw $t0, 388($sp)
sw $t0, 332($sp)
j for7
for7END: 
for8: 
for8STMT: 
j for8END
for8CHANGE_VAL: 
j for8
for8END: 
if1: 
# temp_49 = i_1
lw $t0, 328($sp)
sw $t0, 392($sp)
lw $s0, 392($sp)
li $s1, 3
beq $s0 $s1 if1stmt
j if1_END
if1stmt: 
if1_END: 
if2: 
# temp_50 = i_1
lw $t0, 328($sp)
sw $t0, 396($sp)
lw $s0, 396($sp)
li $s1, 2
beq $s0 $s1 if2stmt
j ELSE2stmt
if2stmt: 
j if2_END
ELSE2stmt: 
if2_END: 
# print_str, 
li $v0, 4
la $a0, str5
syscall 
# temp_51 = a_5
lw $t0, 8($sp)
sw $t0, 400($sp)
# print_int, temp_51
li $v0, 1
lw $a0, 400($sp)
syscall 
# print_str, \n
li $v0, 4
la $a0, str6
syscall 
# print_str, 21182621\n
li $v0, 4
la $a0, str7
syscall 
# d_1 = 0
li $t0, 0
sw $t0, 104($sp)
# temp_52 = d_1
lw $t0, 104($sp)
sw $t0, 404($sp)
# temp_53 = 0-temp_52
li $t1, 0
lw $t2, 404($sp)
sub $t0, $t1, $t2
sw $t0, 408($sp)
# temp_54 = 0+temp_53
lw $t1, 408($sp)
addi $t0, $t1, 0
sw $t0, 412($sp)
# temp_55 = 0-temp_54
li $t1, 0
lw $t2, 412($sp)
sub $t0, $t1, $t2
sw $t0, 416($sp)
# d_1 = temp_55
lw $t0, 416($sp)
sw $t0, 104($sp)
if3: 
# temp_56 = a_5
lw $t0, 8($sp)
sw $t0, 420($sp)
# temp_57 = 0!temp_56
li $t1, 0
nor $t0, $t1, $0
sw $t0, 424($sp)
if3stmt: 
if3_END: 
if4: 
if4stmt: 
if4_END: 
# int d_2
# d_2 = 10
li $t0, 10
sw $t0, 428($sp)
# print_str, 21182621\n
li $v0, 4
la $a0, str8
syscall 
# print_str, 21182621\n
li $v0, 4
la $a0, str9
syscall 
# print_str, 21182621\n
li $v0, 4
la $a0, str10
syscall 
# ret 0
li $v0, 0
lw $ra, 0($sp)
addiu $sp, $sp, 460
jr $ra
func1: 
# print_str, 21182621\n
li $v0, 4
la $a0, str0
syscall 
func2: 
addi $sp, $sp, -12
sw $a0, 0($sp)
# temp_1 = a_1
lw $t0, 0($sp)
sw $t0, 4($sp)
# temp_2 = temp_1-1
lw $t1, 4($sp)
addi $t0, $t1, -1
sw $t0, 8($sp)
# a_1 = temp_2
lw $t0, 8($sp)
sw $t0, 0($sp)
# ret 
addiu $sp, $sp, 12
jr $ra
func3: 
addi $sp, $sp, -32
sw $a0, 0($sp)
sw $a1, 4($sp)
sw $a2, 8($sp)
# temp_3 = arr_1[0]
lw $t0, 4($sp)
addi $t0, $t0, 0
lw $t0, 0($t0)
sw $t0, 12($sp)
# temp_4 = 0*0
li $t1, 0
li $t2, 0
mult $t1, $t2
mflo $t1
sw $t1, 16($sp)
# temp_5 = temp_4+0
lw $t1, 16($sp)
addi $t0, $t1, 0
sw $t0, 20($sp)
# temp_6 = arrrr_1[temp_5]
lw $t0, 8($sp)
lw $t1, 20($sp)
sll $t1, $t1, 2
add $t0, $t0, $t1
lw $t0, 0($t0)
sw $t0, 24($sp)
# temp_7 = temp_3+temp_6
lw $t1, 12($sp)
lw $t2, 24($sp)
add $t0, $t1, $t2
sw $t0, 28($sp)
# a_2 = temp_7
lw $t0, 28($sp)
sw $t0, 0($sp)
# ret 
addiu $sp, $sp, 32
jr $ra
func4: 
# print_str, 21182621\n
li $v0, 4
la $a0, str1
syscall 
# ret 0
li $v0, 0
addiu $sp, $sp, 0
jr $ra
func5: 
addi $sp, $sp, -16
sw $a0, 0($sp)
# temp_8 = a_3
lw $t0, 0($sp)
sw $t0, 4($sp)
# temp_9 = temp_8+1
lw $t1, 4($sp)
addi $t0, $t1, 1
sw $t0, 8($sp)
# a_3 = temp_9
lw $t0, 8($sp)
sw $t0, 0($sp)
# temp_10 = a_3
lw $t0, 0($sp)
sw $t0, 12($sp)
# ret temp_10
lw $v0, 12($sp)
addiu $sp, $sp, 16
jr $ra
func6: 
addi $sp, $sp, -24
sw $a0, 0($sp)
sw $a1, 4($sp)
# temp_11 = a_4
lw $t0, 0($sp)
sw $t0, 8($sp)
# temp_12 = arr_2[0]
lw $t0, 4($sp)
addi $t0, $t0, 0
lw $t0, 0($t0)
sw $t0, 12($sp)
# temp_13 = temp_11+temp_12
lw $t1, 8($sp)
lw $t2, 12($sp)
add $t0, $t1, $t2
sw $t0, 16($sp)
# a_4 = temp_13
lw $t0, 16($sp)
sw $t0, 0($sp)
# temp_14 = a_4
lw $t0, 0($sp)
sw $t0, 20($sp)
# ret temp_14
lw $v0, 20($sp)
addiu $sp, $sp, 24
jr $ra