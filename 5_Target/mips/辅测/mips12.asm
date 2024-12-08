.data
str0:.asciiz "0\n"
str1:.asciiz ""
str2:.asciiz "\n"
str3:.asciiz ""
str4:.asciiz "\n"
str5:.asciiz "\n"
str6:.asciiz "\n"
str7:.asciiz "\n"
str8:.asciiz "19182620\n"
str9:.asciiz "19182620\n"
str10:.asciiz "19182620\n"
.text
jal main
li $v0, 10
syscall 
main: 
addi $sp, $sp, -244
sw $ra, 0($sp)
# int a_1
# int b_1
# int c_1
# int d_1
# int e_1
# int f_1
# int g_1
# g_1 = 1
li $t0, 1
sw $t0, 28($sp)
# int h_1
# int j_2
# int k_1
# int l_1
# int o_1
# o_1 = -1
li $t0, -1
sw $t0, 48($sp)
# int i_2
# i_2 = 2
li $t0, 2
sw $t0, 52($sp)
# int n_1
# int m_1
# int flag_1
# flag_1 = 0
li $t0, 0
sw $t0, 64($sp)
# n_1 = getint()
li $v0, 5
syscall 
sw $v0, 56($sp)
for1: 
# temp_8 = i_2
lw $t0, 52($sp)
sw $t0, 68($sp)
# temp_9 = n_1
lw $t0, 56($sp)
sw $t0, 72($sp)
lw $s0, 68($sp)
lw $s1, 72($sp)
bge $s0 $s1 for1END
for1STMT: 
# temp_10 = n_1
lw $t0, 56($sp)
sw $t0, 76($sp)
# temp_11 = i_2
lw $t0, 52($sp)
sw $t0, 80($sp)
# temp_12 = temp_10%temp_11
lw $t1, 76($sp)
lw $t2, 80($sp)
div $t1, $t2
mfhi $t1
sw $t1, 84($sp)
# m_1 = temp_12
lw $t0, 84($sp)
sw $t0, 60($sp)
if1: 
# temp_13 = m_1
lw $t0, 60($sp)
sw $t0, 88($sp)
lw $s0, 88($sp)
beqz $s0 if1stmt
j if1_END
if1stmt: 
# flag_1 = 1
li $t0, 1
sw $t0, 64($sp)
# print_str, 0\n
li $v0, 4
la $a0, str0
syscall 
if1_END: 
# temp_14 = i_2
lw $t0, 52($sp)
sw $t0, 92($sp)
# temp_15 = temp_14+1
lw $t1, 92($sp)
addi $t0, $t1, 1
sw $t0, 96($sp)
# i_2 = temp_15
lw $t0, 96($sp)
sw $t0, 52($sp)
for1CHANGE_VAL: 
j for1
for1END: 
# call jian
jal jian
# temp_16 = jian
move $t0, $v0
sw $t0, 100($sp)
# c_1 = temp_16
lw $t0, 100($sp)
sw $t0, 12($sp)
# print_str, 
li $v0, 4
la $a0, str1
syscall 
# temp_17 = c_1
lw $t0, 12($sp)
sw $t0, 104($sp)
# print_int, temp_17
li $v0, 1
lw $a0, 104($sp)
syscall 
# print_str, \n
li $v0, 4
la $a0, str2
syscall 
# temp_18 = c_1
lw $t0, 12($sp)
sw $t0, 108($sp)
# temp_19 = temp_18+1
lw $t1, 108($sp)
addi $t0, $t1, 1
sw $t0, 112($sp)
# d_1 = temp_19
lw $t0, 112($sp)
sw $t0, 16($sp)
# temp_20 = c_1
lw $t0, 12($sp)
sw $t0, 116($sp)
# temp_21 = temp_20*2
lw $t1, 116($sp)
li $t2, 2
mult $t1, $t2
mflo $t1
sw $t1, 120($sp)
# e_1 = temp_21
lw $t0, 120($sp)
sw $t0, 20($sp)
if2: 
# temp_22 = e_1
lw $t0, 20($sp)
sw $t0, 124($sp)
lw $s1, 124($sp)
li $s2, 5
blt $s1 $s2 if2stmt
j ELSE2stmt
if2stmt: 
# temp_23 = c_1
lw $t0, 12($sp)
sw $t0, 128($sp)
# temp_24 = temp_23%2
lw $t1, 128($sp)
li $t2, 2
div $t1, $t2
mfhi $t1
sw $t1, 132($sp)
# f_1 = temp_24
lw $t0, 132($sp)
sw $t0, 24($sp)
j if2_END
ELSE2stmt: 
# temp_25 = c_1
lw $t0, 12($sp)
sw $t0, 136($sp)
# temp_26 = temp_25/2
lw $t1, 136($sp)
li $t2, 2
div $t1, $t2
mflo $t1
sw $t1, 140($sp)
# f_1 = temp_26
lw $t0, 140($sp)
sw $t0, 24($sp)
if2_END: 
if3: 
# temp_27 = f_1
lw $t0, 24($sp)
sw $t0, 144($sp)
lw $s1, 144($sp)
bnez $s1 if3stmt
j if3_END
if3stmt: 
# temp_28 = g_1
lw $t0, 28($sp)
sw $t0, 148($sp)
# temp_29 = temp_28+1
lw $t1, 148($sp)
addi $t0, $t1, 1
sw $t0, 152($sp)
# g_1 = temp_29
lw $t0, 152($sp)
sw $t0, 28($sp)
if3_END: 
# temp_30 = i_2
lw $t0, 52($sp)
sw $t0, 156($sp)
# temp_31 = j_2
lw $t0, 36($sp)
sw $t0, 160($sp)
# temp_32 = temp_31+1
lw $t1, 160($sp)
addi $t0, $t1, 1
sw $t0, 164($sp)
# temp_33 = temp_30+temp_32
lw $t1, 156($sp)
lw $t2, 164($sp)
add $t0, $t1, $t2
sw $t0, 168($sp)
# o_1 = temp_33
lw $t0, 168($sp)
sw $t0, 48($sp)
for2: 
for3: 
for3STMT: 
j for3END
for3CHANGE_VAL: 
j for3
for3END: 
if4: 
# temp_34 = c_1
lw $t0, 12($sp)
sw $t0, 172($sp)
# temp_35 = d_1
lw $t0, 16($sp)
sw $t0, 176($sp)
lw $s2, 172($sp)
lw $s3, 176($sp)
beq $s2 $s3 if4stmt
j if4_END
if4stmt: 
if5: 
# temp_36 = d_1
lw $t0, 16($sp)
sw $t0, 180($sp)
# temp_37 = e_1
lw $t0, 20($sp)
sw $t0, 184($sp)
lw $s2, 180($sp)
lw $s3, 184($sp)
bge $s2 $s3 if5stmt
j if5_END
if5stmt: 
if6: 
# temp_38 = e_1
lw $t0, 20($sp)
sw $t0, 188($sp)
# temp_39 = f_1
lw $t0, 24($sp)
sw $t0, 192($sp)
lw $s2, 188($sp)
lw $s3, 192($sp)
ble $s2 $s3 if6stmt
j if6_END
if6stmt: 
if7: 
# temp_40 = f_1
lw $t0, 24($sp)
sw $t0, 196($sp)
# temp_41 = g_1
lw $t0, 28($sp)
sw $t0, 200($sp)
lw $s2, 196($sp)
lw $s3, 200($sp)
bne $s2 $s3 if7stmt
j if7_END
if7stmt: 
if8: 
# temp_42 = c_1
lw $t0, 12($sp)
sw $t0, 204($sp)
lw $s2, 204($sp)
li $s3, 1
bgt $s2 $s3 if8stmt
j if8_END
if8stmt: 
# a_1 = 1
li $t0, 1
sw $t0, 4($sp)
if8_END: 
if7_END: 
if6_END: 
if5_END: 
if4_END: 
# temp_43 = a_1
lw $t0, 4($sp)
sw $t0, 208($sp)
# temp_44 = b_1
lw $t0, 8($sp)
sw $t0, 212($sp)
# push temp_43
lw $a0, 208($sp)
# push temp_44
lw $a1, 212($sp)
# call keke
jal keke
# temp_45 = keke
move $t0, $v0
sw $t0, 216($sp)
# print_str, 
li $v0, 4
la $a0, str3
syscall 
# temp_46 = d_1
lw $t0, 16($sp)
sw $t0, 220($sp)
# print_int, temp_46
li $v0, 1
lw $a0, 220($sp)
syscall 
# print_str, \n
li $v0, 4
la $a0, str4
syscall 
# temp_47 = e_1
lw $t0, 20($sp)
sw $t0, 224($sp)
# print_int, temp_47
li $v0, 1
lw $a0, 224($sp)
syscall 
# print_str, \n
li $v0, 4
la $a0, str5
syscall 
# temp_48 = f_1
lw $t0, 24($sp)
sw $t0, 228($sp)
# print_int, temp_48
li $v0, 1
lw $a0, 228($sp)
syscall 
# print_str, \n
li $v0, 4
la $a0, str6
syscall 
# temp_49 = g_1
lw $t0, 28($sp)
sw $t0, 232($sp)
# print_int, temp_49
li $v0, 1
lw $a0, 232($sp)
syscall 
# print_str, \n
li $v0, 4
la $a0, str7
syscall 
# print_str, 19182620\n
li $v0, 4
la $a0, str8
syscall 
# print_str, 19182620\n
li $v0, 4
la $a0, str9
syscall 
# print_str, 19182620\n
li $v0, 4
la $a0, str10
syscall 
# ret 0
li $v0, 0
lw $ra, 0($sp)
addiu $sp, $sp, 244
jr $ra
de: 
# ret 
addiu $sp, $sp, 0
jr $ra
keke: 
addi $sp, $sp, -20
sw $a0, 0($sp)
sw $a1, 4($sp)
# temp_1 = i_1
lw $t0, 0($sp)
sw $t0, 8($sp)
# temp_2 = j_1
lw $t0, 4($sp)
sw $t0, 12($sp)
# temp_3 = temp_1+temp_2
lw $t1, 8($sp)
lw $t2, 12($sp)
add $t0, $t1, $t2
sw $t0, 16($sp)
# i_1 = temp_3
lw $t0, 16($sp)
sw $t0, 0($sp)
# ret 0
li $v0, 0
addiu $sp, $sp, 20
jr $ra
jian: 
addi $sp, $sp, -28
# int x_1
# int y_1
# int z_1
# x_1 = getint()
li $v0, 5
syscall 
sw $v0, 0($sp)
# y_1 = getint()
li $v0, 5
syscall 
sw $v0, 4($sp)
# temp_4 = x_1
lw $t0, 0($sp)
sw $t0, 12($sp)
# temp_5 = y_1
lw $t0, 4($sp)
sw $t0, 16($sp)
# temp_6 = temp_4-temp_5
lw $t1, 12($sp)
lw $t2, 16($sp)
sub $t0, $t1, $t2
sw $t0, 20($sp)
# z_1 = temp_6
lw $t0, 20($sp)
sw $t0, 8($sp)
# temp_7 = z_1
lw $t0, 8($sp)
sw $t0, 24($sp)
# ret temp_7
lw $v0, 24($sp)
addiu $sp, $sp, 28
jr $ra