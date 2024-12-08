.data
# const int a1_1
space0: .space 4
# const int a2_1
space1: .space 4
# const int a3_1
space2: .space 4
# int b1_1
space3: .space 4
# int b2_1
space4: .space 4
# int b3_1
space5: .space 4
str0:.asciiz "\n"
str1:.asciiz "Continue is err!\n"
str2:.asciiz "Break is err!And < is err!\n"
str3:.asciiz "+ is correct!\n"
str4:.asciiz "+ is err!\n"
str5:.asciiz "Break is err!\n"
str6:.asciiz "Continue is err!\n"
str7:.asciiz "a1+b1 is "
str8:.asciiz "\n"
str9:.asciiz "a2+b2 is "
str10:.asciiz "\n"
.text
# a1_1 = 1
li $t0, 1
la $s0, space0
sw $t0, 0($s0)
# a2_1 = 3
li $t0, 3
la $s0, space1
sw $t0, 0($s0)
# a3_1 = 8
li $t0, 8
la $s0, space2
sw $t0, 0($s0)
# b1_1 = 2
li $t0, 2
la $s0, space3
sw $t0, 0($s0)
# b2_1 = -5
li $t0, -5
la $s0, space4
sw $t0, 0($s0)
# b3_1 = 6
li $t0, 6
la $s0, space5
sw $t0, 0($s0)
jal main
li $v0, 10
syscall 
main: 
addi $sp, $sp, -84
# print_str, \n
li $v0, 4
la $a0, str0
syscall 
# int n_1
# n_1 = 10
li $t0, 10
sw $t0, 0($sp)
for1: 
# temp_1 = n_1
lw $t0, 0($sp)
sw $t0, 4($sp)
lw $s0, 4($sp)
beqz $s0 for1END
for1STMT: 
# temp_2 = n_1
lw $t0, 0($sp)
sw $t0, 8($sp)
# temp_3 = temp_2-1
lw $t1, 8($sp)
addi $t0, $t1, -1
sw $t0, 12($sp)
# n_1 = temp_3
lw $t0, 12($sp)
sw $t0, 0($sp)
if1: 
# temp_4 = n_1
lw $t0, 0($sp)
sw $t0, 16($sp)
# temp_5 = b3_1
la $s0, space5
lw $s0, 0($s0)
sw $s0, 20($sp)
lw $s0, 16($sp)
lw $s1, 20($sp)
blt $s0 $s1 if1stmt
j if1_END
if1stmt: 
j for1CHANGE_VAL
# print_str, Continue is err!\n
li $v0, 4
la $a0, str1
syscall 
if1_END: 
if2: 
# temp_6 = n_1
lw $t0, 0($sp)
sw $t0, 24($sp)
# temp_7 = a1_1
la $s0, space0
lw $s0, 0($s0)
sw $s0, 28($sp)
lw $s0, 24($sp)
lw $s1, 28($sp)
blt $s0 $s1 if2stmt
j if2_END
if2stmt: 
j for1END
# print_str, Break is err!And < is err!\n
li $v0, 4
la $a0, str2
syscall 
if2_END: 
if3: 
# temp_8 = n_1
lw $t0, 0($sp)
sw $t0, 32($sp)
# temp_9 = a2_1
la $s0, space1
lw $s0, 0($s0)
sw $s0, 36($sp)
lw $s0, 32($sp)
lw $s1, 36($sp)
beq $s0 $s1 if3stmt
j ELSE3stmt
if3stmt: 
# print_str, + is correct!\n
li $v0, 4
la $a0, str3
syscall 
j if3_END
ELSE3stmt: 
# print_str, + is err!\n
li $v0, 4
la $a0, str4
syscall 
if3_END: 
if4: 
# temp_10 = n_1
lw $t0, 0($sp)
sw $t0, 40($sp)
# temp_11 = b1_1
la $s0, space3
lw $s0, 0($s0)
sw $s0, 44($sp)
lw $s0, 40($sp)
lw $s1, 44($sp)
beq $s0 $s1 if4stmt
j if4_END
if4stmt: 
j for1END
if4_END: 
for1CHANGE_VAL: 
j for1
for1END: 
if5: 
# temp_12 = n_1
lw $t0, 0($sp)
sw $t0, 48($sp)
# temp_13 = b1_1
la $s0, space3
lw $s0, 0($s0)
sw $s0, 52($sp)
lw $s0, 48($sp)
lw $s1, 52($sp)
bne $s0 $s1 if5stmt
j if5_END
if5stmt: 
if6: 
# temp_14 = n_1
lw $t0, 0($sp)
sw $t0, 56($sp)
lw $s0, 56($sp)
beqz $s0 if6stmt
j ELSE6stmt
if6stmt: 
# print_str, Break is err!\n
li $v0, 4
la $a0, str5
syscall 
j if6_END
ELSE6stmt: 
# print_str, Continue is err!\n
li $v0, 4
la $a0, str6
syscall 
if6_END: 
if5_END: 
# print_str, a1+b1 is 
li $v0, 4
la $a0, str7
syscall 
# temp_15 = a1_1
la $s0, space0
lw $s0, 0($s0)
sw $s0, 60($sp)
# temp_16 = b1_1
la $s0, space3
lw $s0, 0($s0)
sw $s0, 64($sp)
# temp_17 = temp_15+temp_16
lw $t1, 60($sp)
lw $t2, 64($sp)
add $t0, $t1, $t2
sw $t0, 68($sp)
# print_int, temp_17
li $v0, 1
lw $a0, 68($sp)
syscall 
# print_str, \n
li $v0, 4
la $a0, str8
syscall 
# print_str, a2+b2 is 
li $v0, 4
la $a0, str9
syscall 
# temp_18 = a2_1
la $s0, space1
lw $s0, 0($s0)
sw $s0, 72($sp)
# temp_19 = b2_1
la $s0, space4
lw $s0, 0($s0)
sw $s0, 76($sp)
# temp_20 = temp_18+temp_19
lw $t1, 72($sp)
lw $t2, 76($sp)
add $t0, $t1, $t2
sw $t0, 80($sp)
# print_int, temp_20
li $v0, 1
lw $a0, 80($sp)
syscall 
# print_str, \n
li $v0, 4
la $a0, str10
syscall 
# ret 0
li $v0, 0
addiu $sp, $sp, 84
jr $ra