.data
# int a_1
space0: .space 4
str0:.asciiz ""
str1:.asciiz "\n"
.text
# a_1 = 10
li $t0, 10
la $s0, space0
sw $t0, 0($s0)
jal main
li $v0, 10
syscall 
main: 
addi $sp, $sp, -4
# print_str, 
li $v0, 4
la $a0, str0
syscall 
# temp_1 = a_1
la $s0, space0
lw $s0, 0($s0)
sw $s0, 0($sp)
# print_int, temp_1
li $v0, 1
lw $a0, 0($sp)
syscall 
# print_str, \n
li $v0, 4
la $a0, str1
syscall 
# ret 0
li $v0, 0
addiu $sp, $sp, 4
jr $ra

