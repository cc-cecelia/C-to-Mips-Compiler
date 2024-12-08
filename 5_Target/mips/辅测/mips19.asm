.data
# const int _a_1
space0: .space 4
# const int HelLo_World_1[5]
space1: .space 20
# const int a_r__r_1[4]
space2: .space 16
# const int _A_1
space3: .space 4
# const int B_1[1]
space4: .space 4
# const int __1
space5: .space 4
# const int c_1[1]
space6: .space 4
# const int ___1
space7: .space 4
# const int con_6_1[2]
space8: .space 8
# const int lll_1
space9: .space 4
# const int rrr_1
space10: .space 4
# const int awk__1
space11: .space 4
# const int const_num_1
space12: .space 4
# const int yyy_1[6]
space13: .space 24
# int var_1_1
space14: .space 4
# int var_2_1[100]
space15: .space 400
# int var_3_1[24]
space16: .space 96
# int varr_1_1
space17: .space 4
# int varr_2_1
space18: .space 4
# int varr_3_1[2]
space19: .space 8
# int varr_4_1[4995]
space20: .space 19980
# int varrr_1_1
space21: .space 4
# int varrr_2_1[2]
space22: .space 8
# int varrr_3_1[4]
space23: .space 16
# int varrrr_1_1
space24: .space 4
# int varrrr_2_1
space25: .space 4
# int varrrr_3_1[5]
space26: .space 20
# int varrrr_4_1[2]
space27: .space 8
str0:.asciiz ""
str1:.asciiz "\n"
str2:.asciiz ""
str3:.asciiz "\n"
str4:.asciiz ""
str5:.asciiz "\n"
str6:.asciiz ""
str7:.asciiz "\n"
str8:.asciiz ""
str9:.asciiz "\n"
str10:.asciiz ""
str11:.asciiz "\n"
str12:.asciiz ""
str13:.asciiz "\n"
str14:.asciiz ""
str15:.asciiz "\n"
str16:.asciiz ""
str17:.asciiz "\n"
.text
# _a_1 = 0
li $t0, 0
la $s0, space0
sw $t0, 0($s0)
# HelLo_World_1[0] = 1
li $t0, 1
la $t1, space1
sw $t0, 0($t1)
# HelLo_World_1[1] = 55
li $t0, 55
la $t1, space1
sw $t0, 4($t1)
# HelLo_World_1[2] = 0
li $t0, 0
la $t1, space1
sw $t0, 8($t1)
# HelLo_World_1[3] = 10
li $t0, 10
la $t1, space1
sw $t0, 12($t1)
# HelLo_World_1[4] = 9999
li $t0, 9999
la $t1, space1
sw $t0, 16($t1)
# a_r__r_1[0] = -1
li $t0, -1
la $t1, space2
sw $t0, 0($t1)
# a_r__r_1[1] = 0
li $t0, 0
la $t1, space2
sw $t0, 4($t1)
# a_r__r_1[2] = 6
li $t0, 6
la $t1, space2
sw $t0, 8($t1)
# a_r__r_1[3] = -555
li $t0, -555
la $t1, space2
sw $t0, 12($t1)
# _A_1 = 6
li $t0, 6
la $s0, space3
sw $t0, 0($s0)
# B_1[0] = 9966
li $t0, 9966
la $t1, space4
sw $t0, 0($t1)
# __1 = 12
li $t0, 12
la $s0, space5
sw $t0, 0($s0)
# c_1[0] = 555
li $t0, 555
la $t1, space6
sw $t0, 0($t1)
# ___1 = 4
li $t0, 4
la $s0, space7
sw $t0, 0($s0)
# con_6_1[0] = 0
li $t0, 0
la $t1, space8
sw $t0, 0($t1)
# con_6_1[1] = 6
li $t0, 6
la $t1, space8
sw $t0, 4($t1)
# lll_1 = 1
li $t0, 1
la $s0, space9
sw $t0, 0($s0)
# rrr_1 = 2
li $t0, 2
la $s0, space10
sw $t0, 0($s0)
# awk__1 = 11
li $t0, 11
la $s0, space11
sw $t0, 0($s0)
# const_num_1 = 5
li $t0, 5
la $s0, space12
sw $t0, 0($s0)
# yyy_1[0] = 1
li $t0, 1
la $t1, space13
sw $t0, 0($t1)
# yyy_1[1] = 0
li $t0, 0
la $t1, space13
sw $t0, 4($t1)
# yyy_1[2] = 2
li $t0, 2
la $t1, space13
sw $t0, 8($t1)
# yyy_1[3] = -1
li $t0, -1
la $t1, space13
sw $t0, 12($t1)
# yyy_1[4] = 3
li $t0, 3
la $t1, space13
sw $t0, 16($t1)
# yyy_1[5] = -4
li $t0, -4
la $t1, space13
sw $t0, 20($t1)
# varr_2_1 = 10
li $t0, 10
la $s0, space18
sw $t0, 0($s0)
# varrr_1_1 = 10
li $t0, 10
la $s0, space21
sw $t0, 0($s0)
# varrr_2_1[0] = 1
li $t0, 1
la $t1, space22
sw $t0, 0($t1)
# varrr_2_1[1] = 2
li $t0, 2
la $t1, space22
sw $t0, 4($t1)
# varrr_3_1[0] = 6
li $t0, 6
la $t1, space23
sw $t0, 0($t1)
# varrr_3_1[1] = 6
li $t0, 6
la $t1, space23
sw $t0, 4($t1)
# varrr_3_1[2] = -1
li $t0, -1
la $t1, space23
sw $t0, 8($t1)
# varrr_3_1[3] = 0
li $t0, 0
la $t1, space23
sw $t0, 12($t1)
# varrrr_1_1 = 666
li $t0, 666
la $s0, space24
sw $t0, 0($s0)
# varrrr_3_1[0] = 1
li $t0, 1
la $t1, space26
sw $t0, 0($t1)
# varrrr_3_1[1] = 5
li $t0, 5
la $t1, space26
sw $t0, 4($t1)
# varrrr_3_1[2] = 1
li $t0, 1
la $t1, space26
sw $t0, 8($t1)
# varrrr_3_1[3] = 96
li $t0, 96
la $t1, space26
sw $t0, 12($t1)
# varrrr_3_1[4] = -5
li $t0, -5
la $t1, space26
sw $t0, 16($t1)
# varrrr_4_1[0] = 555
li $t0, 555
la $t1, space27
sw $t0, 0($t1)
# varrrr_4_1[1] = -5
li $t0, -5
la $t1, space27
sw $t0, 4($t1)
jal main
li $v0, 10
syscall 
main: 
addi $sp, $sp, -196
sw $ra, 0($sp)
# int arr_1[6]
# temp_59 = _a_1
la $s0, space0
lw $s0, 0($s0)
sw $s0, 28($sp)
# temp_60 = 1*temp_59
li $t1, 1
lw $t2, 28($sp)
mult $t1, $t2
mflo $t1
sw $t1, 32($sp)
# temp_61 = 2*1
li $t1, 2
li $t2, 1
mult $t1, $t2
mflo $t1
sw $t1, 36($sp)
# temp_62 = temp_61+1
lw $t1, 36($sp)
addi $t0, $t1, 1
sw $t0, 40($sp)
# temp_63 = a_r__r_1[temp_62]
la $s0, space2
lw $t0, 40($sp)
sll $t0, $t0, 2
add $s0, $s0, $t0
lw $s0, 0($s0)
sw $s0, 44($sp)
# temp_64 = 123-temp_63
li $t1, 123
lw $t2, 44($sp)
sub $t0, $t1, $t2
sw $t0, 48($sp)
# temp_65 = varrrr_1_1
la $s0, space24
lw $s0, 0($s0)
sw $s0, 52($sp)
# temp_66 = 5555/temp_65
li $t1, 5555
lw $t2, 52($sp)
div $t1, $t2
mflo $t1
sw $t1, 56($sp)
# arr_1[0] = temp_60
lw $t0, 32($sp)
sw $t0, 4($sp)
# arr_1[1] = 16
li $t0, 16
sw $t0, 8($sp)
# arr_1[2] = 3
li $t0, 3
sw $t0, 12($sp)
# arr_1[3] = temp_64
lw $t0, 48($sp)
sw $t0, 16($sp)
# arr_1[4] = temp_66
lw $t0, 56($sp)
sw $t0, 20($sp)
# arr_1[5] = 0
li $t0, 0
sw $t0, 24($sp)
# int bv_1[4]
# temp_67 = 3*0
li $t1, 3
li $t2, 0
mult $t1, $t2
mflo $t1
sw $t1, 76($sp)
# temp_68 = temp_67+0
lw $t1, 76($sp)
addi $t0, $t1, 0
sw $t0, 80($sp)
# temp_69 = arr_1[temp_68]
lw $t1, 80($sp)
sll $t1, $t1, 2
addi $t0, $t1, 4
add $t0, $t0, $sp
lw $t0, 0($t0)
sw $t0, 84($sp)
# temp_70 = 6*temp_69
li $t1, 6
lw $t2, 84($sp)
mult $t1, $t2
mflo $t1
sw $t1, 88($sp)
# bv_1[0] = 0
li $t0, 0
sw $t0, 60($sp)
# bv_1[1] = temp_70
lw $t0, 88($sp)
sw $t0, 64($sp)
# bv_1[2] = 5555
li $t0, 5555
sw $t0, 68($sp)
# bv_1[3] = -27
li $t0, -27
sw $t0, 72($sp)
# int a_5
# int b_3
# a_5 = getint()
li $v0, 5
syscall 
sw $v0, 92($sp)
# temp_71 = varr_2_1
la $s0, space18
lw $s0, 0($s0)
sw $s0, 100($sp)
# push temp_71
lw $a0, 100($sp)
# call func1
jal func1
# temp_72 = func1
move $t0, $v0
sw $t0, 104($sp)
# temp_73 = 3*1
li $t1, 3
li $t2, 1
mult $t1, $t2
mflo $t1
sw $t1, 108($sp)
# temp_74 = temp_73+2
lw $t1, 108($sp)
addi $t0, $t1, 2
sw $t0, 112($sp)
# arr_1[temp_74] = getint()
li $v0, 5
syscall 
sw $v0, 116($sp)
# temp_76 = 3*1
li $t1, 3
li $t2, 1
mult $t1, $t2
mflo $t1
sw $t1, 116($sp)
# temp_77 = temp_76*4
lw $t1, 116($sp)
li $t2, 4
mult $t1, $t2
mflo $t1
sw $t1, 120($sp)
# temp_78 = &arr_1+temp_77
addi $t1, $sp, 4
lw $t2, 120($sp)
add $t0, $t1, $t2
sw $t0, 124($sp)
# temp_79 = temp_78
lw $t0, 124($sp)
sw $t0, 128($sp)
# temp_75 = 111
li $t0, 111
sw $t0, 132($sp)
# push temp_75
lw $a0, 132($sp)
# push temp_79
lw $a1, 128($sp)
# call func2
jal func2
# temp_80 = func2
move $t0, $v0
sw $t0, 136($sp)
# temp_81 = arr_1
addi $t0, $sp, 4
sw $t0, 140($sp)
# push temp_81
lw $a0, 140($sp)
# call func3
jal func3
# temp_82 = func3
move $t0, $v0
sw $t0, 144($sp)
# bv_1[1] = getint()
li $v0, 5
syscall 
sw $v0, 64($sp)
# temp_83 = a_5
lw $t0, 92($sp)
sw $t0, 148($sp)
# temp_84 = bv_1
addi $t0, $sp, 60
sw $t0, 152($sp)
# temp_85 = arr_1
addi $t0, $sp, 4
sw $t0, 156($sp)
# push temp_83
lw $a0, 148($sp)
# push temp_84
lw $a1, 152($sp)
# push temp_85
lw $a2, 156($sp)
# call func4
jal func4
# temp_86 = func4
move $t0, $v0
sw $t0, 160($sp)
# call func5
jal func5
# temp_87 = func5
move $t0, $v0
sw $t0, 164($sp)
# print_str, 
li $v0, 4
la $a0, str0
syscall 
# print_int, 1
li $v0, 1
li $a0, 1
syscall 
# print_str, \n
li $v0, 4
la $a0, str1
syscall 
# print_str, 
li $v0, 4
la $a0, str2
syscall 
# print_int, 1
li $v0, 1
li $a0, 1
syscall 
# print_str, \n
li $v0, 4
la $a0, str3
syscall 
# print_str, 
li $v0, 4
la $a0, str4
syscall 
# print_int, 1
li $v0, 1
li $a0, 1
syscall 
# print_str, \n
li $v0, 4
la $a0, str5
syscall 
# print_str, 
li $v0, 4
la $a0, str6
syscall 
# print_int, 1
li $v0, 1
li $a0, 1
syscall 
# print_str, \n
li $v0, 4
la $a0, str7
syscall 
# print_str, 
li $v0, 4
la $a0, str8
syscall 
# print_int, 1
li $v0, 1
li $a0, 1
syscall 
# print_str, \n
li $v0, 4
la $a0, str9
syscall 
# print_str, 
li $v0, 4
la $a0, str10
syscall 
# print_int, 1
li $v0, 1
li $a0, 1
syscall 
# print_str, \n
li $v0, 4
la $a0, str11
syscall 
# print_str, 
li $v0, 4
la $a0, str12
syscall 
# print_int, 1
li $v0, 1
li $a0, 1
syscall 
# print_str, \n
li $v0, 4
la $a0, str13
syscall 
# print_str, 
li $v0, 4
la $a0, str14
syscall 
# print_int, 1
li $v0, 1
li $a0, 1
syscall 
# print_str, \n
li $v0, 4
la $a0, str15
syscall 
# print_str, 
li $v0, 4
la $a0, str16
syscall 
# print_int, 1
li $v0, 1
li $a0, 1
syscall 
# print_str, \n
li $v0, 4
la $a0, str17
syscall 
# ret 0
li $v0, 0
lw $ra, 0($sp)
addiu $sp, $sp, 196
jr $ra
func1: 
addi $sp, $sp, -28
sw $a0, 0($sp)
# temp_1 = _a_1
la $s0, space0
lw $s0, 0($s0)
sw $s0, 4($sp)
# temp_2 = temp_1+1
lw $t1, 4($sp)
addi $t0, $t1, 1
sw $t0, 8($sp)
# temp_3 = temp_2+2
lw $t1, 8($sp)
addi $t0, $t1, 2
sw $t0, 12($sp)
# temp_4 = temp_3-1
lw $t1, 12($sp)
addi $t0, $t1, -1
sw $t0, 16($sp)
# int func1_var_1
# func1_var_1 = 1
li $t0, 1
sw $t0, 20($sp)
# int func1_var_2
# func1_var_2 = 2
li $t0, 2
sw $t0, 24($sp)
# ret 
addiu $sp, $sp, 28
jr $ra
func2: 
addi $sp, $sp, -48
sw $a0, 0($sp)
sw $a1, 4($sp)
# temp_5 = _A_1
la $s0, space3
lw $s0, 0($s0)
sw $s0, 8($sp)
# temp_6 = temp_5+5
lw $t1, 8($sp)
addi $t0, $t1, 5
sw $t0, 12($sp)
# temp_7 = temp_6-6
lw $t1, 12($sp)
addi $t0, $t1, -6
sw $t0, 16($sp)
# a_2 = temp_7
lw $t0, 16($sp)
sw $t0, 0($sp)
# temp_8 = _A_1
la $s0, space3
lw $s0, 0($s0)
sw $s0, 20($sp)
# temp_9 = temp_8-6
lw $t1, 20($sp)
addi $t0, $t1, -6
sw $t0, 24($sp)
# b_1[temp_9] = 46554646
li $t0, 46554646
lw $t1, 4($sp)
lw $t2, 24($sp)
sll $t2, $t2, 2
add $t1, $t1, $t2
sw $t0, 0($t1)
if1: 
# temp_10 = a_2
lw $t0, 0($sp)
sw $t0, 28($sp)
lw $s0, 28($sp)
li $s1, 5
beq $s0 $s1 if1stmt
j if1_END
if1stmt: 
if1_END: 
if2: 
# temp_11 = a_2
lw $t0, 0($sp)
sw $t0, 32($sp)
lw $s0, 32($sp)
li $s1, 10
blt $s0 $s1 if2stmt
j ELSE2stmt
if2stmt: 
# a_2 = 1000
li $t0, 1000
sw $t0, 0($sp)
j if2_END
ELSE2stmt: 
# temp_12 = _A_1
la $s0, space3
lw $s0, 0($s0)
sw $s0, 36($sp)
# temp_13 = temp_12-5
lw $t1, 36($sp)
addi $t0, $t1, -5
sw $t0, 40($sp)
# temp_14 = b_1[temp_13]
lw $t0, 4($sp)
lw $t1, 40($sp)
sll $t1, $t1, 2
add $t0, $t0, $t1
lw $t0, 0($t0)
sw $t0, 44($sp)
# a_2 = temp_14
lw $t0, 44($sp)
sw $t0, 0($sp)
if2_END: 
# ret 666
li $v0, 666
addiu $sp, $sp, 48
jr $ra
func3: 
addi $sp, $sp, -52
sw $a0, 0($sp)
# int i_1
# i_1 = 0
li $t0, 0
sw $t0, 4($sp)
if3: 
# temp_15 = __1
la $s0, space5
lw $s0, 0($s0)
sw $s0, 8($sp)
# temp_16 = temp_15-12
lw $t1, 8($sp)
addi $t0, $t1, -12
sw $t0, 12($sp)
# temp_17 = 3*temp_16
li $t1, 3
lw $t2, 12($sp)
mult $t1, $t2
mflo $t1
sw $t1, 16($sp)
# temp_18 = temp_17+0
lw $t1, 16($sp)
addi $t0, $t1, 0
sw $t0, 20($sp)
# temp_19 = a_3[temp_18]
lw $t0, 0($sp)
lw $t1, 20($sp)
sll $t1, $t1, 2
add $t0, $t0, $t1
lw $t0, 0($t0)
sw $t0, 24($sp)
lw $s0, 24($sp)
li $s1, 10
bgt $s0 $s1 if3stmt
j ELSE3stmt
if3stmt: 
# ret 
addiu $sp, $sp, 52
jr $ra
j if3_END
ELSE3stmt: 
if3_END: 
for1: 
# temp_20 = i_1
lw $t0, 4($sp)
sw $t0, 28($sp)
lw $s0, 28($sp)
li $s1, 100
bge $s0 $s1 for1END
for1STMT: 
if4: 
# temp_21 = i_1
lw $t0, 4($sp)
sw $t0, 32($sp)
lw $s0, 32($sp)
li $s1, 50
beq $s0 $s1 if4stmt
j ELSE4stmt
if4stmt: 
# temp_22 = 3*0
li $t1, 3
li $t2, 0
mult $t1, $t2
mflo $t1
sw $t1, 36($sp)
# temp_23 = temp_22+0
lw $t1, 36($sp)
addi $t0, $t1, 0
sw $t0, 40($sp)
# a_3[temp_23] = 1
li $t0, 1
lw $t1, 0($sp)
lw $t2, 40($sp)
sll $t2, $t2, 2
add $t1, $t1, $t2
sw $t0, 0($t1)
j for1END
# ret 
addiu $sp, $sp, 52
jr $ra
j if4_END
ELSE4stmt: 
# temp_24 = i_1
lw $t0, 4($sp)
sw $t0, 44($sp)
# temp_25 = temp_24+1
lw $t1, 44($sp)
addi $t0, $t1, 1
sw $t0, 48($sp)
# i_1 = temp_25
lw $t0, 48($sp)
sw $t0, 4($sp)
j for1CHANGE_VAL
if4_END: 
for1CHANGE_VAL: 
j for1
for1END: 
# ret 
addiu $sp, $sp, 52
jr $ra
func4: 
addi $sp, $sp, -100
sw $a0, 0($sp)
sw $a1, 4($sp)
sw $a2, 8($sp)
# temp_26 = a_4
lw $t0, 0($sp)
sw $t0, 12($sp)
# temp_27 = b_2[0]
lw $t0, 4($sp)
addi $t0, $t0, 0
lw $t0, 0($t0)
sw $t0, 16($sp)
# temp_28 = 3*0
li $t1, 3
li $t2, 0
mult $t1, $t2
mflo $t1
sw $t1, 20($sp)
# temp_29 = temp_28+1
lw $t1, 20($sp)
addi $t0, $t1, 1
sw $t0, 24($sp)
# temp_30 = c_2[temp_29]
lw $t0, 8($sp)
lw $t1, 24($sp)
sll $t1, $t1, 2
add $t0, $t0, $t1
lw $t0, 0($t0)
sw $t0, 28($sp)
# temp_31 = a_4
lw $t0, 0($sp)
sw $t0, 32($sp)
# temp_32 = b_2[1]
lw $t0, 4($sp)
addi $t0, $t0, 4
lw $t0, 0($t0)
sw $t0, 36($sp)
# temp_33 = temp_31+temp_32
lw $t1, 32($sp)
lw $t2, 36($sp)
add $t0, $t1, $t2
sw $t0, 40($sp)
# temp_34 = 3*0
li $t1, 3
li $t2, 0
mult $t1, $t2
mflo $t1
sw $t1, 44($sp)
# temp_35 = temp_34+0
lw $t1, 44($sp)
addi $t0, $t1, 0
sw $t0, 48($sp)
# temp_36 = c_2[temp_35]
lw $t0, 8($sp)
lw $t1, 48($sp)
sll $t1, $t1, 2
add $t0, $t0, $t1
lw $t0, 0($t0)
sw $t0, 52($sp)
# temp_37 = temp_33+temp_36
lw $t1, 40($sp)
lw $t2, 52($sp)
add $t0, $t1, $t2
sw $t0, 56($sp)
# temp_38 = a_4
lw $t0, 0($sp)
sw $t0, 60($sp)
# temp_39 = 1+temp_38
lw $t1, 60($sp)
addi $t0, $t1, 1
sw $t0, 64($sp)
# temp_40 = 0-temp_39
li $t1, 0
lw $t2, 64($sp)
sub $t0, $t1, $t2
sw $t0, 68($sp)
# temp_41 = a_4
lw $t0, 0($sp)
sw $t0, 72($sp)
# temp_42 = 1+temp_41
lw $t1, 72($sp)
addi $t0, $t1, 1
sw $t0, 76($sp)
# temp_43 = temp_42+44
lw $t1, 76($sp)
addi $t0, $t1, 44
sw $t0, 80($sp)
# temp_44 = temp_43+3
lw $t1, 80($sp)
addi $t0, $t1, 3
sw $t0, 84($sp)
# temp_45 = temp_40+temp_44
lw $t1, 68($sp)
lw $t2, 84($sp)
add $t0, $t1, $t2
sw $t0, 88($sp)
# temp_46 = a_4
lw $t0, 0($sp)
sw $t0, 92($sp)
# temp_47 = 6*temp_46
li $t1, 6
lw $t2, 92($sp)
mult $t1, $t2
mflo $t1
sw $t1, 96($sp)
# ret 999
li $v0, 999
addiu $sp, $sp, 100
jr $ra
func5: 
addi $sp, $sp, -44
if5: 
li $s0, 1
li $s1, 2
beq $s0 $s1 if5stmt
# temp_48 = _a_1
la $s0, space0
lw $s0, 0($s0)
sw $s0, 0($sp)
li $s0, 2
lw $s1, 0($sp)
beq $s0 $s1 tmpLabel0
li $s0, 6671
li $s1, 4
bne $s0 $s1 tmpLabel0
j if5stmt
tmpLabel0: 
j ELSE5stmt
if5stmt: 
# ret 1111
li $v0, 1111
addiu $sp, $sp, 44
jr $ra
j if5_END
ELSE5stmt: 
if6: 
# temp_49 = __1
la $s0, space5
lw $s0, 0($s0)
sw $s0, 4($sp)
# temp_50 = 0-temp_49
li $t1, 0
lw $t2, 4($sp)
sub $t0, $t1, $t2
sw $t0, 8($sp)
lw $s0, 8($sp)
bne $0 $s0 if6stmt
j ELSE6stmt
if6stmt: 
# ret 5464
li $v0, 5464
addiu $sp, $sp, 44
jr $ra
j if6_END
ELSE6stmt: 
if7: 
# temp_51 = ___1
la $s0, space7
lw $s0, 0($s0)
sw $s0, 12($sp)
# temp_52 = 12*1
li $t1, 12
li $t2, 1
mult $t1, $t2
mflo $t1
sw $t1, 16($sp)
# temp_53 = temp_52+1
lw $t1, 16($sp)
addi $t0, $t1, 1
sw $t0, 20($sp)
# temp_54 = var_3_1[temp_53]
la $s0, space16
lw $t0, 20($sp)
sll $t0, $t0, 2
add $s0, $s0, $t0
lw $s0, 0($s0)
sw $s0, 24($sp)
# temp_55 = 55*temp_54
li $t1, 55
lw $t2, 24($sp)
mult $t1, $t2
mflo $t1
sw $t1, 28($sp)
lw $s0, 12($sp)
lw $s1, 28($sp)
ble $s0 $s1 if7stmt
li $s0, 2
li $s1, 54564
bge $s0 $s1 if7stmt
li $s0, -1
li $s1, 6
beq $s0 $s1 if7stmt
j ELSE7stmt
if7stmt: 
# ret 4564665
li $v0, 4564665
addiu $sp, $sp, 44
jr $ra
j if7_END
ELSE7stmt: 
if8: 
li $s0, 1
bnez $s0 if8stmt
# temp_56 = varrrr_2_1
la $s0, space25
lw $s0, 0($s0)
sw $s0, 32($sp)
# temp_57 = 2+temp_56
lw $t1, 32($sp)
addi $t0, $t1, 2
sw $t0, 36($sp)
li $s0, 1
lw $s1, 36($sp)
blt $s0 $s1 if8stmt
# temp_58 = _a_1
la $s0, space0
lw $s0, 0($s0)
sw $s0, 40($sp)
li $s0, 8
lw $s1, 40($sp)
bgt $s0 $s1 if8stmt
j ELSE8stmt
if8stmt: 
# ret 5456
li $v0, 5456
addiu $sp, $sp, 44
jr $ra
j if8_END
ELSE8stmt: 
# ret 55555
li $v0, 55555
addiu $sp, $sp, 44
jr $ra
if8_END: 
if7_END: 
if6_END: 
if5_END: 
# ret -555
li $v0, -555
addiu $sp, $sp, 44
jr $ra
