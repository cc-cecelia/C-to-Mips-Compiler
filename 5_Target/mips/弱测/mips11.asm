.data
str0:.asciiz ""
.text
jal main
li $v0, 10
syscall 
main: 
addi $sp, $sp, -8
# int b_1
# b_1 = -6
li $t0, -6
sw $t0, 0($sp)
# print_str, 
li $v0, 4
la $a0, str0
syscall 
# temp_1 = b_1
lw $t0, 0($sp)
sw $t0, 4($sp)
# print_int, temp_1
li $v0, 1
lw $a0, 4($sp)
syscall 
# ret 0
li $v0, 0
addiu $sp, $sp, 8
jr $ra