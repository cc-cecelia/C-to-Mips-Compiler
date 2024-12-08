.data
str0:.asciiz "flag is "
str1:.asciiz "\n"
.text
jal main
li $v0, 10
syscall 
main: 
addi $sp, $sp, -24
# int flag1_1
# flag1_1 = 1
li $t0, 1
sw $t0, 0($sp)
# int flag2_1
# flag2_1 = 1
li $t0, 1
sw $t0, 4($sp)
# int flag_1
if1: 
# temp_1 = flag2_1
lw $t0, 4($sp)
sw $t0, 12($sp)
# temp_2 = 0!temp_1
lw $t1, 12($sp)
seq $t0, $0, $t1
sw $t0, 16($sp)
lw $s0, 16($sp)
beqz $s0 ELSE1stmt
if1stmt: 
# flag_1 = 0
li $t0, 0
sw $t0, 8($sp)
j if1_END
ELSE1stmt: 
# flag_1 = 1
li $t0, 1
sw $t0, 8($sp)
if1_END: 
# print_str, flag is 
li $v0, 4
la $a0, str0
syscall 
# temp_3 = flag_1
lw $t0, 8($sp)
sw $t0, 20($sp)
# print_int, temp_3
li $v0, 1
lw $a0, 20($sp)
syscall 
# print_str, \n
li $v0, 4
la $a0, str1
syscall 
