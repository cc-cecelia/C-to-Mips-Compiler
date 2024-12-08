.data
# int a_1

space0: .space 4
str0:.asciiz "ssss"
.text
# a_1 = 2

li $t0, 2
la $s0, space0
sw $t0, 0($s0)
jal main
li $v0, 10
syscall 
main: 
addi $sp, $sp, -12
sw $ra, 0($sp)
# temp_3 = 1

li $t0, 1
sw $t0, 4($sp)
# push temp_3

lw $a0, 4($sp)
# call add

jal add
# temp_4 = add

move $t0, $v0
sw $t0, 8($sp)
# print_str, ssss

li $v0, 4
la $a0, str0
syscall 
# ret 0

li $v0, 0
lw $ra, 0($sp)
addiu $sp, $sp, 12
jr $ra
add: 
addi $sp, $sp, -12
sw $a0, 0($sp)
# temp_1 = a_2

lw $t0, 0($sp)
sw $t0, 4($sp)
# temp_2 = 1+temp_1

lw $t1, 4($sp)
addi $t0, $t1, 1
sw $t0, 8($sp)
# ret temp_2

lw $v0, 8($sp)
addiu $sp, $sp, 12
jr $ra

