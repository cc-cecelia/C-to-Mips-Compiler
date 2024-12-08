.data
str0:.asciiz ""
str1:.asciiz "\n"
.text
jal main
li $v0, 10
syscall 
main: 
addi $sp, $sp, -32
# int a_1
# a_1 = 2
li $t0, 2
sw $t0, 0($sp)
# int b_1
# b_1 = 3
li $t0, 3
sw $t0, 4($sp)
if1: 
# temp_1 = a_1
lw $t0, 0($sp)
sw $t0, 8($sp)
# temp_2 = a_1
lw $t0, 0($sp)
sw $t0, 12($sp)
# temp_3 = b_1
lw $t0, 4($sp)
sw $t0, 16($sp)
# temp_4 = a_1
lw $t0, 0($sp)
sw $t0, 20($sp)
# temp_5 = a_1
lw $t0, 0($sp)
sw $t0, 24($sp)
lw $s0, 8($sp)
li $s1, 1
ble $s0 $s1 tmpLabel0
lw $s1, 12($sp)
li $s2, 4
bge $s1 $s2 tmpLabel0
j if1stmt
tmpLabel0: 
lw $s2, 16($sp)
li $s3, 2
bgt $s2 $s3 if1stmt
lw $s3, 20($sp)
li $s4, 2
ble $s3 $s4 tmpLabel1
lw $s4, 24($sp)
li $s5, 5
bge $s4 $s5 tmpLabel1
j if1stmt
tmpLabel1: 
j ELSE1stmt
if1stmt: 
# b_1 = 2
li $t0, 2
sw $t0, 4($sp)
j if1_END
ELSE1stmt: 
# b_1 = 1
li $t0, 1
sw $t0, 4($sp)
if1_END: 
# print_str, 
li $v0, 4
la $a0, str0
syscall 
# temp_6 = b_1
lw $t0, 4($sp)
sw $t0, 28($sp)
# print_int, temp_6
li $v0, 1
lw $a0, 28($sp)
syscall 
# print_str, \n
li $v0, 4
la $a0, str1
syscall 
# ret 0
li $v0, 0
addiu $sp, $sp, 32
jr $ra