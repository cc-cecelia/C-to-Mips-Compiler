.data
str0:.asciiz ""
str1:.asciiz "\n"
.text
jal main
li $v0, 10
syscall 
main: 
addi $sp, $sp, -20
# int i_1
# i_1 = 0
li $t0, 0
sw $t0, 0($sp)
for1: 
# temp_1 = i_1
lw $t0, 0($sp)
sw $t0, 4($sp)
lw $s0, 4($sp)
li $s1, 5
bge $s0 $s1 for1END
for1STMT: 
# print_str, 
li $v0, 4
la $a0, str0
syscall 
# temp_2 = i_1
lw $t0, 0($sp)
sw $t0, 8($sp)
# print_int, temp_2
li $v0, 1
lw $a0, 8($sp)
syscall 
# print_str, \n
li $v0, 4
la $a0, str1
syscall 
for1CHANGE_VAL: 
# temp_3 = i_1
lw $t0, 0($sp)
sw $t0, 12($sp)
# temp_4 = temp_3+1
lw $t1, 12($sp)
addi $t0, $t1, 1
sw $t0, 16($sp)
# i_1 = temp_4
lw $t0, 16($sp)
sw $t0, 0($sp)
j for1
for1END: 
# ret 0
li $v0, 0
addiu $sp, $sp, 20
jr $ra
