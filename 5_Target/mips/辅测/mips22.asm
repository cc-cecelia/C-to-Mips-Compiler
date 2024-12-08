.data
# const int a0_1
space0: .space 4
str0:.asciiz "21371064"
str1:.asciiz "\n"
str2:.asciiz "heihei"
str3:.asciiz "\n"
str4:.asciiz ""
str5:.asciiz "\n"
str6:.asciiz "AC\n"
str7:.asciiz "WA\n"
str8:.asciiz "TLE\n"
str9:.asciiz "RE\n"
str10:.asciiz "PE\n"
str11:.asciiz "AK!!\n"
.text
# a0_1 = 0
li $t0, 0
la $s0, space0
sw $t0, 0($s0)
jal main
li $v0, 10
syscall 
main: 
addi $sp, $sp, -256
sw $ra, 0($sp)
# print_str, 21371064
li $v0, 4
la $a0, str0
syscall 
# print_str, \n
li $v0, 4
la $a0, str1
syscall 
# print_str, heihei
li $v0, 4
la $a0, str2
syscall 
# print_int, 0
li $v0, 1
li $a0, 0
syscall 
# print_str, \n
li $v0, 4
la $a0, str3
syscall 
# print_str, 
li $v0, 4
la $a0, str4
syscall 
# print_int, 8
li $v0, 1
li $a0, 8
syscall 
# print_str, \n
li $v0, 4
la $a0, str5
syscall 
# print_str, AC\n
li $v0, 4
la $a0, str6
syscall 
# print_str, WA\n
li $v0, 4
la $a0, str7
syscall 
# print_str, TLE\n
li $v0, 4
la $a0, str8
syscall 
# print_str, RE\n
li $v0, 4
la $a0, str9
syscall 
# print_str, PE\n
li $v0, 4
la $a0, str10
syscall 
# print_str, AK!!\n
li $v0, 4
la $a0, str11
syscall 
# int a1_1
# a1_1 = 1
li $t0, 1
sw $t0, 4($sp)
if1: 
# temp_4 = a1_1
lw $t0, 4($sp)
sw $t0, 8($sp)
lw $s0, 8($sp)
blt $0 $s0 if1stmt
j if1_END
if1stmt: 
# a1_1 = 1
li $t0, 1
sw $t0, 4($sp)
if1_END: 
if2: 
# temp_5 = a1_1
lw $t0, 4($sp)
sw $t0, 12($sp)
lw $s0, 12($sp)
bgt $0 $s0 if2stmt
j ELSE2stmt
if2stmt: 
# a1_1 = 1
li $t0, 1
sw $t0, 4($sp)
j if2_END
ELSE2stmt: 
# a1_1 = 1
li $t0, 1
sw $t0, 4($sp)
if2_END: 
if3: 
# temp_6 = a1_1
lw $t0, 4($sp)
sw $t0, 16($sp)
lw $s0, 16($sp)
beq $0 $s0 if3stmt
j if3_END
if3stmt: 
# a1_1 = 12
li $t0, 12
sw $t0, 4($sp)
if3_END: 
if4: 
# temp_7 = a1_1
lw $t0, 4($sp)
sw $t0, 20($sp)
# temp_8 = 0!temp_7
lw $t1, 20($sp)
seq $t0, $0, $t1
sw $t0, 24($sp)
lw $s0, 24($sp)
beqz $s0 if4_END
if4stmt: 
# a1_1 = 4
li $t0, 4
sw $t0, 4($sp)
if4_END: 
if5: 
# temp_9 = a1_1
lw $t0, 4($sp)
sw $t0, 28($sp)
lw $s0, 28($sp)
bge $0 $s0 if5stmt
j if5_END
if5stmt: 
# a1_1 = 9
li $t0, 9
sw $t0, 4($sp)
if5_END: 
if6: 
# temp_10 = a1_1
lw $t0, 4($sp)
sw $t0, 32($sp)
lw $s0, 32($sp)
ble $0 $s0 if6stmt
j if6_END
if6stmt: 
# a1_1 = 10
li $t0, 10
sw $t0, 4($sp)
if6_END: 
for1: 
# temp_11 = a1_1
lw $t0, 4($sp)
sw $t0, 36($sp)
lw $s0, 36($sp)
blt $0 $s0 for1END
for1STMT: 
if7: 
# temp_12 = a1_1
lw $t0, 4($sp)
sw $t0, 40($sp)
lw $s0, 40($sp)
li $s1, 4
beq $s0 $s1 if7stmt
j if7_END
if7stmt: 
# a1_1 = 5
li $t0, 5
sw $t0, 4($sp)
if7_END: 
j for1END
j for1CHANGE_VAL
for1CHANGE_VAL: 
j for1
for1END: 
# a1_1 = 0
li $t0, 0
sw $t0, 4($sp)
# temp_13 = a1_1
lw $t0, 4($sp)
sw $t0, 44($sp)
# temp_14 = temp_13%6
lw $t1, 44($sp)
li $t2, 6
div $t1, $t2
mfhi $t1
sw $t1, 48($sp)
# a1_1 = temp_14
lw $t0, 48($sp)
sw $t0, 4($sp)
# a1_1 = 2
li $t0, 2
sw $t0, 4($sp)
if8: 
# temp_15 = a1_1
lw $t0, 4($sp)
sw $t0, 52($sp)
lw $s0, 52($sp)
li $s1, 2
beq $s0 $s1 if8stmt
j if8_END
if8stmt: 
# a1_1 = 3
li $t0, 3
sw $t0, 4($sp)
# a1_1 = 4
li $t0, 4
sw $t0, 4($sp)
if8_END: 
for2: 
# temp_16 = a1_1
lw $t0, 4($sp)
sw $t0, 56($sp)
lw $s0, 56($sp)
li $s1, 4
bne $s0 $s1 for2END
for2STMT: 
# temp_17 = a1_1
lw $t0, 4($sp)
sw $t0, 60($sp)
# temp_18 = temp_17+1
lw $t1, 60($sp)
addi $t0, $t1, 1
sw $t0, 64($sp)
# a1_1 = temp_18
lw $t0, 64($sp)
sw $t0, 4($sp)
j for2CHANGE_VAL
for2CHANGE_VAL: 
j for2
for2END: 
if9: 
# temp_19 = a1_1
lw $t0, 4($sp)
sw $t0, 68($sp)
lw $s0, 68($sp)
li $s1, 5
bne $s0 $s1 tmpLabel0
# temp_20 = a1_1
lw $t0, 4($sp)
sw $t0, 72($sp)
lw $s0, 72($sp)
li $s1, 4
bne $s0 $s1 tmpLabel0
j if9stmt
tmpLabel0: 
j if9_END
if9stmt: 
# a1_1 = 6
li $t0, 6
sw $t0, 4($sp)
if9_END: 
if10: 
# temp_21 = a1_1
lw $t0, 4($sp)
sw $t0, 76($sp)
lw $s0, 76($sp)
li $s1, 6
bne $s0 $s1 if10stmt
# temp_22 = a1_1
lw $t0, 4($sp)
sw $t0, 80($sp)
lw $s0, 80($sp)
li $s1, 3
beq $s0 $s1 if10stmt
j if10_END
if10stmt: 
# a1_1 = 0
li $t0, 0
sw $t0, 4($sp)
if10_END: 
# int a2_1[2]
# int a3_1[2]
# a3_1[0] = 1
li $t0, 1
sw $t0, 92($sp)
# a3_1[1] = 2
li $t0, 2
sw $t0, 96($sp)
# int a4_1[4]
# a4_1[0] = 1
li $t0, 1
sw $t0, 100($sp)
# a4_1[1] = 2
li $t0, 2
sw $t0, 104($sp)
# a4_1[2] = 3
li $t0, 3
sw $t0, 108($sp)
# a4_1[3] = 4
li $t0, 4
sw $t0, 112($sp)
# int a5_1[3]
# a5_1[0] = 1
li $t0, 1
sw $t0, 116($sp)
# a5_1[1] = 2
li $t0, 2
sw $t0, 120($sp)
# a5_1[2] = 3
li $t0, 3
sw $t0, 124($sp)
# int a6_1[4]
# a6_1[0] = 1
li $t0, 1
sw $t0, 128($sp)
# a6_1[1] = 2
li $t0, 2
sw $t0, 132($sp)
# a6_1[2] = 1
li $t0, 1
sw $t0, 136($sp)
# a6_1[3] = 2
li $t0, 2
sw $t0, 140($sp)
# int C_1[2]
# C_1[0] = -1
li $t0, -1
sw $t0, 144($sp)
# C_1[1] = 9
li $t0, 9
sw $t0, 148($sp)
# temp_23 = a1_1
lw $t0, 4($sp)
sw $t0, 152($sp)
# push temp_23
lw $a0, 152($sp)
# call fd
jal fd
# temp_24 = fd
move $t0, $v0
sw $t0, 156($sp)
# C_1[0] = temp_24
lw $t0, 156($sp)
sw $t0, 144($sp)
# temp_25 = C_1[1]
lw $t0, 148($sp)
sw $t0, 160($sp)
# temp_26 = a2_1
addi $t0, $sp, 84
sw $t0, 164($sp)
# temp_27 = a4_1
addi $t0, $sp, 100
sw $t0, 168($sp)
# push temp_25
lw $a0, 160($sp)
# push temp_26
lw $a1, 164($sp)
# push temp_27
lw $a2, 168($sp)
# call fk
jal fk
# temp_28 = fk
move $t0, $v0
sw $t0, 172($sp)
# C_1[1] = temp_28
lw $t0, 172($sp)
sw $t0, 148($sp)
# int n_1
# n_1 = 4
li $t0, 4
sw $t0, 176($sp)
# n_1 = getint()
li $v0, 5
syscall 
sw $v0, 176($sp)
# call f
jal f
# temp_29 = f
move $t0, $v0
sw $t0, 180($sp)
# temp_30 = C_1[1]
lw $t0, 148($sp)
sw $t0, 184($sp)
# temp_31 = 2*1
li $t1, 2
li $t2, 1
mult $t1, $t2
mflo $t1
sw $t1, 188($sp)
# temp_32 = temp_31+0
lw $t1, 188($sp)
addi $t0, $t1, 0
sw $t0, 192($sp)
# temp_33 = a4_1[temp_32]
lw $t1, 192($sp)
sll $t1, $t1, 2
addi $t0, $t1, 100
add $t0, $t0, $sp
lw $t0, 0($t0)
sw $t0, 196($sp)
# temp_34 = 2*1
li $t1, 2
li $t2, 1
mult $t1, $t2
mflo $t1
sw $t1, 200($sp)
# temp_35 = temp_34+1
lw $t1, 200($sp)
addi $t0, $t1, 1
sw $t0, 204($sp)
# temp_36 = a6_1[temp_35]
lw $t1, 204($sp)
sll $t1, $t1, 2
addi $t0, $t1, 128
add $t0, $t0, $sp
lw $t0, 0($t0)
sw $t0, 208($sp)
# push temp_30
lw $a0, 184($sp)
# push temp_33
lw $a1, 196($sp)
# push temp_36
lw $a2, 208($sp)
# call ff
jal ff
# temp_37 = ff
move $t0, $v0
sw $t0, 212($sp)
# temp_38 = 3
li $t0, 3
sw $t0, 216($sp)
# push temp_38
lw $a0, 216($sp)
# call fd
jal fd
# temp_39 = fd
move $t0, $v0
sw $t0, 220($sp)
# n_1 = temp_39
lw $t0, 220($sp)
sw $t0, 176($sp)
# ret 0
li $v0, 0
lw $ra, 0($sp)
addiu $sp, $sp, 256
jr $ra
f: 
# ret 
addiu $sp, $sp, 0
jr $ra
ff: 
addi $sp, $sp, -20
sw $a0, 0($sp)
sw $a1, 4($sp)
sw $a2, 8($sp)
# temp_1 = c_1
lw $t0, 8($sp)
sw $t0, 12($sp)
# temp_2 = temp_1+1
lw $t1, 12($sp)
addi $t0, $t1, 1
sw $t0, 16($sp)
# c_1 = temp_2
lw $t0, 16($sp)
sw $t0, 8($sp)
# ret 
addiu $sp, $sp, 20
jr $ra
fd: 
addi $sp, $sp, -8
sw $a0, 0($sp)
# temp_3 = a_2
lw $t0, 0($sp)
sw $t0, 4($sp)
# ret temp_3
lw $v0, 4($sp)
addiu $sp, $sp, 8
jr $ra
fk: 
addi $sp, $sp, -12
sw $a0, 0($sp)
sw $a1, 4($sp)
sw $a2, 8($sp)
# ret 1
li $v0, 1
addiu $sp, $sp, 12
jr $ra