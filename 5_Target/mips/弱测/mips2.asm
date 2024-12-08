.data
.text
jal main
li $v0, 10
syscall 
main: 
addi $sp, $sp, -20
sw $ra, 0($sp)
# int a_2

# a_2 = 2

li $t0, 2
sw $t0, 4($sp)
# temp_3 = a_2

lw $t1, 4($sp)
sw $t1, 8($sp)
# temp_4 = temp_3

lw $t2, 8($sp)
sw $t2, 12($sp)
# push temp_4

lw $a0, 12($sp)
# call add

jal add
# temp_5 = add

move $t2, $v0
sw $t2, 16($sp)
# ret 0

li $v0, 0
lw $ra, 0($sp)
addiu $sp, $sp, 20
jr $ra
add: 
addi $sp, $sp, -12
sw $a0, 0($sp)
# temp_1 = a_1

lw $t3, 0($sp)
sw $t3, 4($sp)
# temp_2 = 1+temp_1

lw $t4, 4($sp)
addi $t3, $t4, 1
sw $t3, 8($sp)
# ret temp_2

lw $v0, 8($sp)
addiu $sp, $sp, 12
jr $ra