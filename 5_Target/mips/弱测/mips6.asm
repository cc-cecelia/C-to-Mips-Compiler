.data
# int test_1

space0: .space 4
str0:.asciiz "Hello World\n"
str1:.asciiz ""
.text
jal main
li $v0, 10
syscall 
main: 
addi $sp, $sp, -4
# print_str, Hello World\n

li $v0, 4
la $a0, str0
syscall 
# test_1 = getint()

li $v0, 5
syscall 
la $t0, space0
sw $v0, 0($t0)
# print_str, 

li $v0, 4
la $a0, str1
syscall 
# temp_1 = test_1

la $s0, space0
lw $s0, 0($s0)
sw $s0, 0($sp)
# print_int, temp_1

li $v0, 1
lw $a0, 0($sp)
syscall 
# ret 0

li $v0, 0
addiu $sp, $sp, 4
jr $ra
