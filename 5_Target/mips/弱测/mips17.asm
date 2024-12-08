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
addi $sp, $sp, -452
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
# temp_15 = 0*1
li $t1, 0
li $t2, 1
mult $t1, $t2
mflo $t1
sw $t1, 248($sp)
# temp_16 = temp_15+1
lw $t2, 248($sp)
addi $t1, $t2, 1
sw $t1, 252($sp)
# arrrr_ddd_1[temp_16] = 1
li $t1, 1
sw $t1, 472($sp)
# temp_17 = 0*0
li $t2, 0
li $t3, 0
mult $t2, $t3
mflo $t2
sw $t2, 256($sp)
# temp_18 = temp_17+1
lw $t3, 256($sp)
addi $t2, $t3, 1
sw $t2, 260($sp)
# arrrr_eee_1[temp_18] = 1
li $t2, 1
sw $t2, 496($sp)
# print_str, 21182621\n
li $v0, 4
la $a0, str3
syscall 
# call func1
jal func1
# temp_19 = func1
move $t2, $v0
sw $t2, 264($sp)
# temp_20 = 1
li $t2, 1
sw $t2, 268($sp)
# push temp_20
lw $a0, 268($sp)
# call func2
jal func2
# temp_21 = func2
move $t2, $v0
sw $t2, 272($sp)
# temp_23 = 0*0
li $t3, 0
li $t4, 0
mult $t3, $t4
mflo $t3
sw $t3, 276($sp)
# temp_24 = arrrr_d_1[temp_23]
lw $t4, 276($sp)
sll $t4, $t4, 2
addi $t3, $t4, 176
add $t3, $t3, $sp
lw $t3, 0($t3)
sw $t3, 280($sp)
# temp_25 = arrrr_d_1
addi $t3, $sp, 176
sw $t3, 284($sp)
# temp_22 = 1
li $t3, 1
sw $t3, 288($sp)
# push temp_22
lw $a0, 288($sp)
# push temp_24
lw $a1, 280($sp)
# push temp_25
lw $a2, 284($sp)
# call func3
jal func3
# temp_26 = func3
move $t3, $v0
sw $t3, 292($sp)
# call func4
jal func4
# temp_27 = func4
move $t3, $v0
sw $t3, 296($sp)
# temp_28 = 1
li $t3, 1
sw $t3, 300($sp)
# push temp_28
lw $a0, 300($sp)
# call func5
jal func5
# temp_29 = func5
move $t3, $v0
sw $t3, 304($sp)
# temp_31 = arr_d_1
addi $t3, $sp, 128
sw $t3, 308($sp)
# temp_30 = 1
li $t3, 1
sw $t3, 312($sp)
# push temp_30
lw $a0, 312($sp)
# push temp_31
lw $a1, 308($sp)
# call func6
jal func6
# temp_32 = func6
move $t3, $v0
sw $t3, 316($sp)
# print_str, 21182621\n
li $v0, 4
la $a0, str4
syscall 
# int i_1
# i_1 = 5
li $t3, 5
sw $t3, 320($sp)
# int j_1
# j_1 = 1
li $t3, 1
sw $t3, 324($sp)
for1: 
# temp_33 = j_1
lw $t3, 324($sp)
sw $t3, 328($sp)
lw $s0, 328($sp)
li $s1, 5
bgt $s0 $s1 for1END
for1STMT: 
for1CHANGE_VAL: 
# temp_34 = j_1
lw $t3, 324($sp)
sw $t3, 332($sp)
# temp_35 = temp_34+1
lw $t4, 332($sp)
addi $t3, $t4, 1
sw $t3, 336($sp)
# j_1 = temp_35
lw $t3, 336($sp)
sw $t3, 324($sp)
j for1
for1END: 
# j_1 = 1
li $t3, 1
sw $t3, 324($sp)
for2: 
# temp_36 = j_1
lw $t3, 324($sp)
sw $t3, 340($sp)
lw $s0, 340($sp)
li $s1, 5
bgt $s0 $s1 for2END
for2STMT: 
j for2CHANGE_VAL
for2CHANGE_VAL: 
# temp_37 = j_1
lw $t3, 324($sp)
sw $t3, 344($sp)
# temp_38 = temp_37+1
lw $t4, 344($sp)
addi $t3, $t4, 1
sw $t3, 348($sp)
# j_1 = temp_38
lw $t3, 348($sp)
sw $t3, 324($sp)
j for2
for2END: 
# j_1 = 1
li $t3, 1
sw $t3, 324($sp)
for3: 
for3STMT: 
j for3END
for3CHANGE_VAL: 
# temp_39 = j_1
lw $t3, 324($sp)
sw $t3, 352($sp)
# temp_40 = temp_39+1
lw $t4, 352($sp)
addi $t3, $t4, 1
sw $t3, 356($sp)
# j_1 = temp_40
lw $t3, 356($sp)
sw $t3, 324($sp)
j for3
for3END: 
# j_1 = 1
li $t3, 1
sw $t3, 324($sp)
for4: 
# temp_41 = j_1
lw $t3, 324($sp)
sw $t3, 360($sp)
lw $s0, 360($sp)
li $s1, 5
bgt $s0 $s1 for4END
for4STMT: 
# temp_42 = j_1
lw $t3, 324($sp)
sw $t3, 364($sp)
# temp_43 = temp_42+1
lw $t4, 364($sp)
addi $t3, $t4, 1
sw $t3, 368($sp)
# j_1 = temp_43
lw $t3, 368($sp)
sw $t3, 324($sp)
for4CHANGE_VAL: 
j for4
for4END: 
# j_1 = 1
li $t3, 1
sw $t3, 324($sp)
for5: 
for5STMT: 
j for5END
for5CHANGE_VAL: 
j for5
for5END: 
for6: 
# temp_44 = j_1
lw $t3, 324($sp)
sw $t3, 372($sp)
lw $s0, 372($sp)
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
# temp_45 = j_1
lw $t3, 324($sp)
sw $t3, 376($sp)
# temp_46 = temp_45+1
lw $t4, 376($sp)
addi $t3, $t4, 1
sw $t3, 380($sp)
# j_1 = temp_46
lw $t3, 380($sp)
sw $t3, 324($sp)
j for7
for7END: 
for8: 
for8STMT: 
j for8END
for8CHANGE_VAL: 
j for8
for8END: 
if1: 
# temp_47 = i_1
lw $t3, 320($sp)
sw $t3, 384($sp)
lw $s0, 384($sp)
li $s1, 3
beq $s0 $s1 if1stmt
j if1_END
if1stmt: 
if1_END: 
if2: 
# temp_48 = i_1
lw $t3, 320($sp)
sw $t3, 388($sp)
lw $s0, 388($sp)
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
# temp_49 = a_5
lw $t3, 8($sp)
sw $t3, 392($sp)
# print_int, temp_49
li $v0, 1
lw $a0, 392($sp)
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
li $t3, 0
sw $t3, 104($sp)
# temp_50 = d_1
lw $t3, 104($sp)
sw $t3, 396($sp)
# temp_51 = 0-temp_50
li $t4, 0
lw $t5, 396($sp)
sub $t3, $t4, $t5
sw $t3, 400($sp)
# temp_52 = 0+temp_51
lw $t4, 400($sp)
addi $t3, $t4, 0
sw $t3, 404($sp)
# temp_53 = 0-temp_52
li $t4, 0
lw $t5, 404($sp)
sub $t3, $t4, $t5
sw $t3, 408($sp)
# d_1 = temp_53
lw $t3, 408($sp)
sw $t3, 104($sp)
if3: 
# temp_54 = a_5
lw $t3, 8($sp)
sw $t3, 412($sp)
# temp_55 = 0!temp_54
li $t4, 0
nor $t3, $t4, $0
sw $t3, 416($sp)
if3stmt: 
if3_END: 
if4: 
if4stmt: 
if4_END: 
# int d_2
# d_2 = 10
li $t3, 10
sw $t3, 420($sp)
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
addiu $sp, $sp, 452
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
lw $t3, 0($sp)
sw $t3, 4($sp)
# temp_2 = temp_1-1
lw $t4, 4($sp)
addi $t3, $t4, -1
sw $t3, 8($sp)
# a_1 = temp_2
lw $t3, 8($sp)
sw $t3, 0($sp)
# ret 
addiu $sp, $sp, 12
jr $ra
func3: 
addi $sp, $sp, -32
sw $a0, 0($sp)
sw $a1, 4($sp)
sw $a2, 8($sp)
# temp_3 = arr_1[0]
lw $t3, 4($sp)
addi $t3, $t3, 0
lw $t3, 0($t3)
sw $t3, 12($sp)
# temp_4 = 0*0
li $t4, 0
li $t5, 0
mult $t4, $t5
mflo $t4
sw $t4, 16($sp)
# temp_5 = temp_4+0
lw $t5, 16($sp)
addi $t4, $t5, 0
sw $t4, 20($sp)
# temp_6 = arrrr_1[temp_5]
lw $t4, 8($sp)
lw $t5, 20($sp)
sll $t5, $t5, 2
add $t4, $t4, $t5
lw $t4, 0($t4)
sw $t4, 24($sp)
# temp_7 = temp_3+temp_6
lw $t5, 12($sp)
lw $t6, 24($sp)
add $t4, $t5, $t6
sw $t4, 28($sp)
# a_2 = temp_7
lw $t4, 28($sp)
sw $t4, 0($sp)
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
lw $t4, 0($sp)
sw $t4, 4($sp)
# temp_9 = temp_8+1
lw $t5, 4($sp)
addi $t4, $t5, 1
sw $t4, 8($sp)
# a_3 = temp_9
lw $t4, 8($sp)
sw $t4, 0($sp)
# temp_10 = a_3
lw $t4, 0($sp)
sw $t4, 12($sp)
# ret temp_10
lw $v0, 12($sp)
addiu $sp, $sp, 16
jr $ra
func6: 
addi $sp, $sp, -24
sw $a0, 0($sp)
sw $a1, 4($sp)
# temp_11 = a_4
lw $t4, 0($sp)
sw $t4, 8($sp)
# temp_12 = arr_2[0]
lw $t4, 4($sp)
addi $t4, $t4, 0
lw $t4, 0($t4)
sw $t4, 12($sp)
# temp_13 = temp_11+temp_12
lw $t5, 8($sp)
lw $t6, 12($sp)
add $t4, $t5, $t6
sw $t4, 16($sp)
# a_4 = temp_13
lw $t4, 16($sp)
sw $t4, 0($sp)
# temp_14 = a_4
lw $t4, 0($sp)
sw $t4, 20($sp)
# ret temp_14
lw $v0, 20($sp)
addiu $sp, $sp, 24
jr $ra
