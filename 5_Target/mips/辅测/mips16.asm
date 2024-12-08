.data
# int fib_matrix_1[4]
space0: .space 16
# const int __res_1[4]
space1: .space 16
# const int __useless_1[4]
space2: .space 16
str0:.asciiz "Error!\n"
str1:.asciiz ""
str2:.asciiz " "
str3:.asciiz "\n"
str4:.asciiz " "
str5:.asciiz "\n"
str6:.asciiz "a[n] = a[n - 1] + a[n - 2] (for different init values)\n"
str7:.asciiz "/* input = "
str8:.asciiz " */\n"
str9:.asciiz "when a[0] = "
str10:.asciiz ", a[1] = "
str11:.asciiz " ==> a["
str12:.asciiz "] = "
str13:.asciiz "\n"
str14:.asciiz "//////////////////////////////////////\n"
str15:.asciiz "/****************** END ******************/\n"
.text
# fib_matrix_1[0] = 1
li $t0, 1
la $t1, space0
sw $t0, 0($t1)
# fib_matrix_1[1] = 1
li $t0, 1
la $t1, space0
sw $t0, 4($t1)
# fib_matrix_1[2] = 1
li $t0, 1
la $t1, space0
sw $t0, 8($t1)
# fib_matrix_1[3] = 0
li $t0, 0
la $t1, space0
sw $t0, 12($t1)
# __res_1[0] = 5
li $t0, 5
la $t1, space1
sw $t0, 0($t1)
# __res_1[1] = 5
li $t0, 5
la $t1, space1
sw $t0, 4($t1)
# __res_1[2] = 2
li $t0, 2
la $t1, space1
sw $t0, 8($t1)
# __res_1[3] = 3
li $t0, 3
la $t1, space1
sw $t0, 12($t1)
# __useless_1[0] = 1
li $t0, 1
la $t1, space2
sw $t0, 0($t1)
# __useless_1[1] = 1
li $t0, 1
la $t1, space2
sw $t0, 4($t1)
# __useless_1[2] = 1
li $t0, 1
la $t1, space2
sw $t0, 8($t1)
# __useless_1[3] = 0
li $t0, 0
la $t1, space2
sw $t0, 12($t1)
jal main
li $v0, 10
syscall 
main: 
addi $sp, $sp, -332
sw $ra, 0($sp)
# const int down_1
# down_1 = 0
li $t0, 0
sw $t0, 4($sp)
# int n_3
# n_3 = getint()
li $v0, 5
syscall 
sw $v0, 8($sp)
# int tmp_1[2]
# tmp_1[0] = 0
li $t0, 0
sw $t0, 12($sp)
# tmp_1[1] = 0
li $t0, 0
sw $t0, 16($sp)
# int res_5[4]
# int vec_list_1[12]
# vec_list_1[0] = 1
li $t0, 1
sw $t0, 36($sp)
# vec_list_1[1] = 0
li $t0, 0
sw $t0, 40($sp)
# vec_list_1[2] = 1
li $t0, 1
sw $t0, 44($sp)
# vec_list_1[3] = 1
li $t0, 1
sw $t0, 48($sp)
# vec_list_1[4] = 2
li $t0, 2
sw $t0, 52($sp)
# vec_list_1[5] = 1
li $t0, 1
sw $t0, 56($sp)
# vec_list_1[6] = 3
li $t0, 3
sw $t0, 60($sp)
# vec_list_1[7] = 1
li $t0, 1
sw $t0, 64($sp)
# vec_list_1[8] = 4
li $t0, 4
sw $t0, 68($sp)
# vec_list_1[9] = 3
li $t0, 3
sw $t0, 72($sp)
# vec_list_1[10] = -1
li $t0, -1
sw $t0, 76($sp)
# vec_list_1[11] = 1
li $t0, 1
sw $t0, 80($sp)
# print_str, a[n] = a[n - 1] + a[n - 2] (for different init values)\n
li $v0, 4
la $a0, str6
syscall 
for1: 
# temp_162 = n_3
lw $t0, 8($sp)
sw $t0, 84($sp)
# temp_163 = down_1
lw $t0, 4($sp)
sw $t0, 88($sp)
# temp_164 = n_3
lw $t0, 8($sp)
sw $t0, 92($sp)
# temp_165 = down_1
lw $t0, 4($sp)
sw $t0, 96($sp)
lw $s0, 92($sp)
lw $s1, 96($sp)
ble $s0 $s1 for1END
for1STMT: 
# int p_1
# p_1 = getint()
li $v0, 5
syscall 
sw $v0, 100($sp)
# int ret_1
# temp_166 = p_1
lw $t0, 100($sp)
sw $t0, 108($sp)
# temp_167 = res_5
addi $t0, $sp, 20
sw $t0, 112($sp)
# push temp_166
lw $a0, 108($sp)
# push temp_167
lw $a1, 112($sp)
# call power
jal power
# temp_168 = power
move $t0, $v0
sw $t0, 116($sp)
# ret_1 = temp_168
lw $t0, 116($sp)
sw $t0, 104($sp)
# print_str, /* input = 
li $v0, 4
la $a0, str7
syscall 
# temp_169 = p_1
lw $t0, 100($sp)
sw $t0, 120($sp)
# print_int, temp_169
li $v0, 1
lw $a0, 120($sp)
syscall 
# print_str,  */\n
li $v0, 4
la $a0, str8
syscall 
if3: 
# temp_170 = ret_1
lw $t0, 104($sp)
sw $t0, 124($sp)
lw $s0, 124($sp)
beqz $s0 tmpLabel0
# call err
jal err
# temp_171 = err
move $t0, $v0
sw $t0, 128($sp)
lw $s0, 128($sp)
beqz $s0 tmpLabel0
j if3stmt
tmpLabel0: 
j if3_END
# temp_172 = ret_1
lw $t0, 104($sp)
sw $t0, 132($sp)
lw $s0, 132($sp)
beqz $s0 tmpLabel1
# call err
jal err
# temp_173 = err
move $t0, $v0
sw $t0, 136($sp)
lw $s0, 136($sp)
beqz $s0 tmpLabel1
j if3stmt
tmpLabel1: 
j if3_END
if3stmt: 
# temp_174 = n_3
lw $t0, 8($sp)
sw $t0, 140($sp)
# temp_175 = temp_174-1
lw $t1, 140($sp)
addi $t0, $t1, -1
sw $t0, 144($sp)
# n_3 = temp_175
lw $t0, 144($sp)
sw $t0, 8($sp)
j for1CHANGE_VAL
if3_END: 
if4: 
# temp_176 = ret_1
lw $t0, 104($sp)
sw $t0, 148($sp)
lw $s0, 148($sp)
bnez $s0 if4stmt
# temp_177 = res_5
addi $t0, $sp, 20
sw $t0, 152($sp)
# push temp_177
lw $a0, 152($sp)
# call out
jal out
# temp_178 = out
move $t0, $v0
sw $t0, 156($sp)
lw $s0, 156($sp)
bnez $s0 if4stmt
j if4_END
# temp_179 = ret_1
lw $t0, 104($sp)
sw $t0, 160($sp)
lw $s0, 160($sp)
bnez $s0 if4stmt
# temp_180 = res_5
addi $t0, $sp, 20
sw $t0, 164($sp)
# push temp_180
lw $a0, 164($sp)
# call out
jal out
# temp_181 = out
move $t0, $v0
sw $t0, 168($sp)
lw $s0, 168($sp)
bnez $s0 if4stmt
j if4_END
if4stmt: 
# int i_1
# i_1 = 0
li $t0, 0
sw $t0, 172($sp)
for2: 
# temp_182 = i_1
lw $t0, 172($sp)
sw $t0, 176($sp)
# temp_183 = i_1
lw $t0, 172($sp)
sw $t0, 180($sp)
lw $s0, 180($sp)
li $s1, 6
blt $s0 $s1 tmpLabel2
li $s0, 1
bnez $s0 tmpLabel2
j for2END
tmpLabel2: 
for2STMT: 
# temp_184 = tmp_1
addi $t0, $sp, 12
sw $t0, 184($sp)
# temp_185 = res_5
addi $t0, $sp, 20
sw $t0, 188($sp)
# temp_186 = i_1
lw $t0, 172($sp)
sw $t0, 192($sp)
# temp_187 = 2*temp_186
li $t1, 2
lw $t2, 192($sp)
mult $t1, $t2
mflo $t1
sw $t1, 196($sp)
# temp_188 = temp_187*4
lw $t1, 196($sp)
li $t2, 4
mult $t1, $t2
mflo $t1
sw $t1, 200($sp)
# temp_189 = &vec_list_1+temp_188
addi $t1, $sp, 36
lw $t2, 200($sp)
add $t0, $t1, $t2
sw $t0, 204($sp)
# temp_190 = temp_189
lw $t0, 204($sp)
sw $t0, 208($sp)
# push temp_184
lw $a0, 184($sp)
# push temp_185
lw $a1, 188($sp)
# push temp_190
lw $a2, 208($sp)
# call __vec_mul
jal __vec_mul
# temp_191 = __vec_mul
move $t0, $v0
sw $t0, 212($sp)
# print_str, when a[0] = 
li $v0, 4
la $a0, str9
syscall 
# temp_192 = i_1
lw $t0, 172($sp)
sw $t0, 216($sp)
# temp_193 = 2*temp_192
li $t1, 2
lw $t2, 216($sp)
mult $t1, $t2
mflo $t1
sw $t1, 220($sp)
# temp_194 = temp_193+1
lw $t1, 220($sp)
addi $t0, $t1, 1
sw $t0, 224($sp)
# temp_195 = vec_list_1[temp_194]
lw $t1, 224($sp)
sll $t1, $t1, 2
addi $t0, $t1, 36
add $t0, $t0, $sp
lw $t0, 0($t0)
sw $t0, 228($sp)
# print_int, temp_195
li $v0, 1
lw $a0, 228($sp)
syscall 
# print_str, , a[1] = 
li $v0, 4
la $a0, str10
syscall 
# temp_196 = i_1
lw $t0, 172($sp)
sw $t0, 232($sp)
# temp_197 = 2*temp_196
li $t1, 2
lw $t2, 232($sp)
mult $t1, $t2
mflo $t1
sw $t1, 236($sp)
# temp_198 = temp_197+0
lw $t1, 236($sp)
addi $t0, $t1, 0
sw $t0, 240($sp)
# temp_199 = vec_list_1[temp_198]
lw $t1, 240($sp)
sll $t1, $t1, 2
addi $t0, $t1, 36
add $t0, $t0, $sp
lw $t0, 0($t0)
sw $t0, 244($sp)
# print_int, temp_199
li $v0, 1
lw $a0, 244($sp)
syscall 
# print_str,  ==> a[
li $v0, 4
la $a0, str11
syscall 
# temp_200 = p_1
lw $t0, 100($sp)
sw $t0, 248($sp)
# print_int, temp_200
li $v0, 1
lw $a0, 248($sp)
syscall 
# print_str, ] = 
li $v0, 4
la $a0, str12
syscall 
# temp_201 = tmp_1[1]
lw $t0, 16($sp)
sw $t0, 252($sp)
# print_int, temp_201
li $v0, 1
lw $a0, 252($sp)
syscall 
# print_str, \n
li $v0, 4
la $a0, str13
syscall 
# temp_202 = i_1
lw $t0, 172($sp)
sw $t0, 256($sp)
# temp_203 = temp_202+1
lw $t1, 256($sp)
addi $t0, $t1, 1
sw $t0, 260($sp)
# i_1 = temp_203
lw $t0, 260($sp)
sw $t0, 172($sp)
if5: 
# temp_204 = i_1
lw $t0, 172($sp)
sw $t0, 264($sp)
# temp_205 = i_1
lw $t0, 172($sp)
sw $t0, 268($sp)
lw $s0, 268($sp)
li $s1, 6
bge $s0 $s1 if5stmt
j ELSE5stmt
# temp_206 = i_1
lw $t0, 172($sp)
sw $t0, 272($sp)
lw $s0, 272($sp)
li $s1, 6
bge $s0 $s1 if5stmt
j ELSE5stmt
if5stmt: 
j for2END
j if5_END
ELSE5stmt: 
j for2CHANGE_VAL
if5_END: 
for2CHANGE_VAL: 
j for2
for2END: 
if4_END: 
# print_str, //////////////////////////////////////\n
li $v0, 4
la $a0, str14
syscall 
# temp_207 = n_3
lw $t0, 8($sp)
sw $t0, 276($sp)
# temp_208 = 1-temp_207
li $t1, 1
lw $t2, 276($sp)
sub $t0, $t1, $t2
sw $t0, 280($sp)
# temp_209 = 0-temp_208
li $t1, 0
lw $t2, 280($sp)
sub $t0, $t1, $t2
sw $t0, 284($sp)
# temp_210 = 0+temp_209
lw $t1, 284($sp)
addi $t0, $t1, 0
sw $t0, 288($sp)
# temp_211 = 0-temp_210
li $t1, 0
lw $t2, 288($sp)
sub $t0, $t1, $t2
sw $t0, 292($sp)
# temp_212 = 0+temp_211
lw $t1, 292($sp)
addi $t0, $t1, 0
sw $t0, 296($sp)
# temp_213 = 0-temp_212
li $t1, 0
lw $t2, 296($sp)
sub $t0, $t1, $t2
sw $t0, 300($sp)
# n_3 = temp_213
lw $t0, 300($sp)
sw $t0, 8($sp)
for1CHANGE_VAL: 
j for1
for1END: 
# print_str, /****************** END ******************/\n
li $v0, 4
la $a0, str15
syscall 
# ret 0
li $v0, 0
lw $ra, 0($sp)
addiu $sp, $sp, 332
jr $ra
__vec_mul: 
addi $sp, $sp, -100
sw $a0, 0($sp)
sw $a1, 4($sp)
sw $a2, 8($sp)
# temp_1 = vec_1[0]
lw $t0, 8($sp)
addi $t0, $t0, 0
lw $t0, 0($t0)
sw $t0, 12($sp)
# temp_2 = 2*0
li $t1, 2
li $t2, 0
mult $t1, $t2
mflo $t1
sw $t1, 16($sp)
# temp_3 = temp_2+0
lw $t1, 16($sp)
addi $t0, $t1, 0
sw $t0, 20($sp)
# temp_4 = mat_1[temp_3]
lw $t0, 4($sp)
lw $t1, 20($sp)
sll $t1, $t1, 2
add $t0, $t0, $t1
lw $t0, 0($t0)
sw $t0, 24($sp)
# temp_5 = temp_1*temp_4
lw $t1, 12($sp)
lw $t2, 24($sp)
mult $t1, $t2
mflo $t1
sw $t1, 28($sp)
# temp_6 = vec_1[1]
lw $t0, 8($sp)
addi $t0, $t0, 4
lw $t0, 0($t0)
sw $t0, 32($sp)
# temp_7 = 2*0
li $t1, 2
li $t2, 0
mult $t1, $t2
mflo $t1
sw $t1, 36($sp)
# temp_8 = temp_7+1
lw $t1, 36($sp)
addi $t0, $t1, 1
sw $t0, 40($sp)
# temp_9 = mat_1[temp_8]
lw $t0, 4($sp)
lw $t1, 40($sp)
sll $t1, $t1, 2
add $t0, $t0, $t1
lw $t0, 0($t0)
sw $t0, 44($sp)
# temp_10 = temp_6*temp_9
lw $t1, 32($sp)
lw $t2, 44($sp)
mult $t1, $t2
mflo $t1
sw $t1, 48($sp)
# temp_11 = temp_5+temp_10
lw $t1, 28($sp)
lw $t2, 48($sp)
add $t0, $t1, $t2
sw $t0, 52($sp)
# res_1[0] = temp_11
lw $t0, 52($sp)
lw $t1, 0($sp)
addi $t1, $t1, 0
sw $t0, 0($t1)
# temp_12 = vec_1[0]
lw $t0, 8($sp)
addi $t0, $t0, 0
lw $t0, 0($t0)
sw $t0, 56($sp)
# temp_13 = 2*1
li $t1, 2
li $t2, 1
mult $t1, $t2
mflo $t1
sw $t1, 60($sp)
# temp_14 = temp_13+0
lw $t1, 60($sp)
addi $t0, $t1, 0
sw $t0, 64($sp)
# temp_15 = mat_1[temp_14]
lw $t0, 4($sp)
lw $t1, 64($sp)
sll $t1, $t1, 2
add $t0, $t0, $t1
lw $t0, 0($t0)
sw $t0, 68($sp)
# temp_16 = temp_12*temp_15
lw $t1, 56($sp)
lw $t2, 68($sp)
mult $t1, $t2
mflo $t1
sw $t1, 72($sp)
# temp_17 = vec_1[1]
lw $t0, 8($sp)
addi $t0, $t0, 4
lw $t0, 0($t0)
sw $t0, 76($sp)
# temp_18 = 2*1
li $t1, 2
li $t2, 1
mult $t1, $t2
mflo $t1
sw $t1, 80($sp)
# temp_19 = temp_18+1
lw $t1, 80($sp)
addi $t0, $t1, 1
sw $t0, 84($sp)
# temp_20 = mat_1[temp_19]
lw $t0, 4($sp)
lw $t1, 84($sp)
sll $t1, $t1, 2
add $t0, $t0, $t1
lw $t0, 0($t0)
sw $t0, 88($sp)
# temp_21 = temp_17*temp_20
lw $t1, 76($sp)
lw $t2, 88($sp)
mult $t1, $t2
mflo $t1
sw $t1, 92($sp)
# temp_22 = temp_16+temp_21
lw $t1, 72($sp)
lw $t2, 92($sp)
add $t0, $t1, $t2
sw $t0, 96($sp)
# res_1[1] = temp_22
lw $t0, 96($sp)
lw $t1, 0($sp)
addi $t1, $t1, 4
sw $t0, 0($t1)
# ret 
addiu $sp, $sp, 100
jr $ra
__mat_mul: 
addi $sp, $sp, -284
sw $a0, 0($sp)
sw $a1, 4($sp)
sw $a2, 8($sp)
# temp_23 = 2*0
li $t1, 2
li $t2, 0
mult $t1, $t2
mflo $t1
sw $t1, 12($sp)
# temp_24 = temp_23+0
lw $t1, 12($sp)
addi $t0, $t1, 0
sw $t0, 16($sp)
# temp_25 = 2*0
li $t1, 2
li $t2, 0
mult $t1, $t2
mflo $t1
sw $t1, 20($sp)
# temp_26 = temp_25+0
lw $t1, 20($sp)
addi $t0, $t1, 0
sw $t0, 24($sp)
# temp_27 = x_1[temp_26]
lw $t0, 4($sp)
lw $t1, 24($sp)
sll $t1, $t1, 2
add $t0, $t0, $t1
lw $t0, 0($t0)
sw $t0, 28($sp)
# temp_28 = 2*0
li $t1, 2
li $t2, 0
mult $t1, $t2
mflo $t1
sw $t1, 32($sp)
# temp_29 = temp_28+0
lw $t1, 32($sp)
addi $t0, $t1, 0
sw $t0, 36($sp)
# temp_30 = y_1[temp_29]
lw $t0, 8($sp)
lw $t1, 36($sp)
sll $t1, $t1, 2
add $t0, $t0, $t1
lw $t0, 0($t0)
sw $t0, 40($sp)
# temp_31 = temp_27*temp_30
lw $t1, 28($sp)
lw $t2, 40($sp)
mult $t1, $t2
mflo $t1
sw $t1, 44($sp)
# temp_32 = 2*0
li $t1, 2
li $t2, 0
mult $t1, $t2
mflo $t1
sw $t1, 48($sp)
# temp_33 = temp_32+1
lw $t1, 48($sp)
addi $t0, $t1, 1
sw $t0, 52($sp)
# temp_34 = x_1[temp_33]
lw $t0, 4($sp)
lw $t1, 52($sp)
sll $t1, $t1, 2
add $t0, $t0, $t1
lw $t0, 0($t0)
sw $t0, 56($sp)
# temp_35 = 2*1
li $t1, 2
li $t2, 1
mult $t1, $t2
mflo $t1
sw $t1, 60($sp)
# temp_36 = temp_35+0
lw $t1, 60($sp)
addi $t0, $t1, 0
sw $t0, 64($sp)
# temp_37 = y_1[temp_36]
lw $t0, 8($sp)
lw $t1, 64($sp)
sll $t1, $t1, 2
add $t0, $t0, $t1
lw $t0, 0($t0)
sw $t0, 68($sp)
# temp_38 = temp_34*temp_37
lw $t1, 56($sp)
lw $t2, 68($sp)
mult $t1, $t2
mflo $t1
sw $t1, 72($sp)
# temp_39 = temp_31+temp_38
lw $t1, 44($sp)
lw $t2, 72($sp)
add $t0, $t1, $t2
sw $t0, 76($sp)
# res_2[temp_24] = temp_39
lw $t0, 76($sp)
lw $t1, 0($sp)
lw $t2, 16($sp)
sll $t2, $t2, 2
add $t1, $t1, $t2
sw $t0, 0($t1)
# temp_40 = 2*0
li $t1, 2
li $t2, 0
mult $t1, $t2
mflo $t1
sw $t1, 80($sp)
# temp_41 = temp_40+1
lw $t1, 80($sp)
addi $t0, $t1, 1
sw $t0, 84($sp)
# temp_42 = 2*0
li $t1, 2
li $t2, 0
mult $t1, $t2
mflo $t1
sw $t1, 88($sp)
# temp_43 = temp_42+0
lw $t1, 88($sp)
addi $t0, $t1, 0
sw $t0, 92($sp)
# temp_44 = x_1[temp_43]
lw $t0, 4($sp)
lw $t1, 92($sp)
sll $t1, $t1, 2
add $t0, $t0, $t1
lw $t0, 0($t0)
sw $t0, 96($sp)
# temp_45 = 2*0
li $t1, 2
li $t2, 0
mult $t1, $t2
mflo $t1
sw $t1, 100($sp)
# temp_46 = temp_45+1
lw $t1, 100($sp)
addi $t0, $t1, 1
sw $t0, 104($sp)
# temp_47 = y_1[temp_46]
lw $t0, 8($sp)
lw $t1, 104($sp)
sll $t1, $t1, 2
add $t0, $t0, $t1
lw $t0, 0($t0)
sw $t0, 108($sp)
# temp_48 = temp_44*temp_47
lw $t1, 96($sp)
lw $t2, 108($sp)
mult $t1, $t2
mflo $t1
sw $t1, 112($sp)
# temp_49 = 2*0
li $t1, 2
li $t2, 0
mult $t1, $t2
mflo $t1
sw $t1, 116($sp)
# temp_50 = temp_49+1
lw $t1, 116($sp)
addi $t0, $t1, 1
sw $t0, 120($sp)
# temp_51 = x_1[temp_50]
lw $t0, 4($sp)
lw $t1, 120($sp)
sll $t1, $t1, 2
add $t0, $t0, $t1
lw $t0, 0($t0)
sw $t0, 124($sp)
# temp_52 = 2*1
li $t1, 2
li $t2, 1
mult $t1, $t2
mflo $t1
sw $t1, 128($sp)
# temp_53 = temp_52+1
lw $t1, 128($sp)
addi $t0, $t1, 1
sw $t0, 132($sp)
# temp_54 = y_1[temp_53]
lw $t0, 8($sp)
lw $t1, 132($sp)
sll $t1, $t1, 2
add $t0, $t0, $t1
lw $t0, 0($t0)
sw $t0, 136($sp)
# temp_55 = temp_51*temp_54
lw $t1, 124($sp)
lw $t2, 136($sp)
mult $t1, $t2
mflo $t1
sw $t1, 140($sp)
# temp_56 = temp_48+temp_55
lw $t1, 112($sp)
lw $t2, 140($sp)
add $t0, $t1, $t2
sw $t0, 144($sp)
# res_2[temp_41] = temp_56
lw $t0, 144($sp)
lw $t1, 0($sp)
lw $t2, 84($sp)
sll $t2, $t2, 2
add $t1, $t1, $t2
sw $t0, 0($t1)
# temp_57 = 2*1
li $t1, 2
li $t2, 1
mult $t1, $t2
mflo $t1
sw $t1, 148($sp)
# temp_58 = temp_57+0
lw $t1, 148($sp)
addi $t0, $t1, 0
sw $t0, 152($sp)
# temp_59 = 2*1
li $t1, 2
li $t2, 1
mult $t1, $t2
mflo $t1
sw $t1, 156($sp)
# temp_60 = temp_59+0
lw $t1, 156($sp)
addi $t0, $t1, 0
sw $t0, 160($sp)
# temp_61 = x_1[temp_60]
lw $t0, 4($sp)
lw $t1, 160($sp)
sll $t1, $t1, 2
add $t0, $t0, $t1
lw $t0, 0($t0)
sw $t0, 164($sp)
# temp_62 = 2*0
li $t1, 2
li $t2, 0
mult $t1, $t2
mflo $t1
sw $t1, 168($sp)
# temp_63 = temp_62+0
lw $t1, 168($sp)
addi $t0, $t1, 0
sw $t0, 172($sp)
# temp_64 = y_1[temp_63]
lw $t0, 8($sp)
lw $t1, 172($sp)
sll $t1, $t1, 2
add $t0, $t0, $t1
lw $t0, 0($t0)
sw $t0, 176($sp)
# temp_65 = temp_61*temp_64
lw $t1, 164($sp)
lw $t2, 176($sp)
mult $t1, $t2
mflo $t1
sw $t1, 180($sp)
# temp_66 = 2*1
li $t1, 2
li $t2, 1
mult $t1, $t2
mflo $t1
sw $t1, 184($sp)
# temp_67 = temp_66+1
lw $t1, 184($sp)
addi $t0, $t1, 1
sw $t0, 188($sp)
# temp_68 = x_1[temp_67]
lw $t0, 4($sp)
lw $t1, 188($sp)
sll $t1, $t1, 2
add $t0, $t0, $t1
lw $t0, 0($t0)
sw $t0, 192($sp)
# temp_69 = 2*1
li $t1, 2
li $t2, 1
mult $t1, $t2
mflo $t1
sw $t1, 196($sp)
# temp_70 = temp_69+0
lw $t1, 196($sp)
addi $t0, $t1, 0
sw $t0, 200($sp)
# temp_71 = y_1[temp_70]
lw $t0, 8($sp)
lw $t1, 200($sp)
sll $t1, $t1, 2
add $t0, $t0, $t1
lw $t0, 0($t0)
sw $t0, 204($sp)
# temp_72 = temp_68*temp_71
lw $t1, 192($sp)
lw $t2, 204($sp)
mult $t1, $t2
mflo $t1
sw $t1, 208($sp)
# temp_73 = temp_65+temp_72
lw $t1, 180($sp)
lw $t2, 208($sp)
add $t0, $t1, $t2
sw $t0, 212($sp)
# res_2[temp_58] = temp_73
lw $t0, 212($sp)
lw $t1, 0($sp)
lw $t2, 152($sp)
sll $t2, $t2, 2
add $t1, $t1, $t2
sw $t0, 0($t1)
# temp_74 = 2*1
li $t1, 2
li $t2, 1
mult $t1, $t2
mflo $t1
sw $t1, 216($sp)
# temp_75 = temp_74+1
lw $t1, 216($sp)
addi $t0, $t1, 1
sw $t0, 220($sp)
# temp_76 = 2*1
li $t1, 2
li $t2, 1
mult $t1, $t2
mflo $t1
sw $t1, 224($sp)
# temp_77 = temp_76+0
lw $t1, 224($sp)
addi $t0, $t1, 0
sw $t0, 228($sp)
# temp_78 = x_1[temp_77]
lw $t0, 4($sp)
lw $t1, 228($sp)
sll $t1, $t1, 2
add $t0, $t0, $t1
lw $t0, 0($t0)
sw $t0, 232($sp)
# temp_79 = 2*0
li $t1, 2
li $t2, 0
mult $t1, $t2
mflo $t1
sw $t1, 236($sp)
# temp_80 = temp_79+1
lw $t1, 236($sp)
addi $t0, $t1, 1
sw $t0, 240($sp)
# temp_81 = y_1[temp_80]
lw $t0, 8($sp)
lw $t1, 240($sp)
sll $t1, $t1, 2
add $t0, $t0, $t1
lw $t0, 0($t0)
sw $t0, 244($sp)
# temp_82 = temp_78*temp_81
lw $t1, 232($sp)
lw $t2, 244($sp)
mult $t1, $t2
mflo $t1
sw $t1, 248($sp)
# temp_83 = 2*1
li $t1, 2
li $t2, 1
mult $t1, $t2
mflo $t1
sw $t1, 252($sp)
# temp_84 = temp_83+1
lw $t1, 252($sp)
addi $t0, $t1, 1
sw $t0, 256($sp)
# temp_85 = x_1[temp_84]
lw $t0, 4($sp)
lw $t1, 256($sp)
sll $t1, $t1, 2
add $t0, $t0, $t1
lw $t0, 0($t0)
sw $t0, 260($sp)
# temp_86 = 2*1
li $t1, 2
li $t2, 1
mult $t1, $t2
mflo $t1
sw $t1, 264($sp)
# temp_87 = temp_86+1
lw $t1, 264($sp)
addi $t0, $t1, 1
sw $t0, 268($sp)
# temp_88 = y_1[temp_87]
lw $t0, 8($sp)
lw $t1, 268($sp)
sll $t1, $t1, 2
add $t0, $t0, $t1
lw $t0, 0($t0)
sw $t0, 272($sp)
# temp_89 = temp_85*temp_88
lw $t1, 260($sp)
lw $t2, 272($sp)
mult $t1, $t2
mflo $t1
sw $t1, 276($sp)
# temp_90 = temp_82+temp_89
lw $t1, 248($sp)
lw $t2, 276($sp)
add $t0, $t1, $t2
sw $t0, 280($sp)
# res_2[temp_75] = temp_90
lw $t0, 280($sp)
lw $t1, 0($sp)
lw $t2, 220($sp)
sll $t2, $t2, 2
add $t1, $t1, $t2
sw $t0, 0($t1)
# ret 
addiu $sp, $sp, 284
jr $ra
__power: 
addi $sp, $sp, -244
sw $ra, 0($sp)
sw $a0, 4($sp)
sw $a1, 8($sp)
sw $a2, 12($sp)
if1: 
# temp_91 = n_1
lw $t0, 4($sp)
sw $t0, 16($sp)
lw $s0, 16($sp)
li $s1, 1
beq $s0 $s1 if1stmt
j ELSE1stmt
# temp_92 = n_1
lw $t0, 4($sp)
sw $t0, 20($sp)
lw $s0, 20($sp)
li $s1, 1
beq $s0 $s1 if1stmt
j ELSE1stmt
if1stmt: 
# temp_93 = 2*0
li $t1, 2
li $t2, 0
mult $t1, $t2
mflo $t1
sw $t1, 24($sp)
# temp_94 = temp_93+0
lw $t1, 24($sp)
addi $t0, $t1, 0
sw $t0, 28($sp)
# temp_95 = 2*0
li $t1, 2
li $t2, 0
mult $t1, $t2
mflo $t1
sw $t1, 32($sp)
# temp_96 = temp_95+0
lw $t1, 32($sp)
addi $t0, $t1, 0
sw $t0, 36($sp)
# temp_97 = cur_1[temp_96]
lw $t0, 8($sp)
lw $t1, 36($sp)
sll $t1, $t1, 2
add $t0, $t0, $t1
lw $t0, 0($t0)
sw $t0, 40($sp)
# res_3[temp_94] = temp_97
lw $t0, 40($sp)
lw $t1, 12($sp)
lw $t2, 28($sp)
sll $t2, $t2, 2
add $t1, $t1, $t2
sw $t0, 0($t1)
# temp_98 = 2*0
li $t1, 2
li $t2, 0
mult $t1, $t2
mflo $t1
sw $t1, 44($sp)
# temp_99 = temp_98+1
lw $t1, 44($sp)
addi $t0, $t1, 1
sw $t0, 48($sp)
# temp_100 = 2*0
li $t1, 2
li $t2, 0
mult $t1, $t2
mflo $t1
sw $t1, 52($sp)
# temp_101 = temp_100+1
lw $t1, 52($sp)
addi $t0, $t1, 1
sw $t0, 56($sp)
# temp_102 = cur_1[temp_101]
lw $t0, 8($sp)
lw $t1, 56($sp)
sll $t1, $t1, 2
add $t0, $t0, $t1
lw $t0, 0($t0)
sw $t0, 60($sp)
# res_3[temp_99] = temp_102
lw $t0, 60($sp)
lw $t1, 12($sp)
lw $t2, 48($sp)
sll $t2, $t2, 2
add $t1, $t1, $t2
sw $t0, 0($t1)
# temp_103 = 2*1
li $t1, 2
li $t2, 1
mult $t1, $t2
mflo $t1
sw $t1, 64($sp)
# temp_104 = temp_103+0
lw $t1, 64($sp)
addi $t0, $t1, 0
sw $t0, 68($sp)
# temp_105 = 2*1
li $t1, 2
li $t2, 1
mult $t1, $t2
mflo $t1
sw $t1, 72($sp)
# temp_106 = temp_105+0
lw $t1, 72($sp)
addi $t0, $t1, 0
sw $t0, 76($sp)
# temp_107 = cur_1[temp_106]
lw $t0, 8($sp)
lw $t1, 76($sp)
sll $t1, $t1, 2
add $t0, $t0, $t1
lw $t0, 0($t0)
sw $t0, 80($sp)
# res_3[temp_104] = temp_107
lw $t0, 80($sp)
lw $t1, 12($sp)
lw $t2, 68($sp)
sll $t2, $t2, 2
add $t1, $t1, $t2
sw $t0, 0($t1)
# temp_108 = 2*1
li $t1, 2
li $t2, 1
mult $t1, $t2
mflo $t1
sw $t1, 84($sp)
# temp_109 = temp_108+1
lw $t1, 84($sp)
addi $t0, $t1, 1
sw $t0, 88($sp)
# temp_110 = 2*1
li $t1, 2
li $t2, 1
mult $t1, $t2
mflo $t1
sw $t1, 92($sp)
# temp_111 = temp_110+1
lw $t1, 92($sp)
addi $t0, $t1, 1
sw $t0, 96($sp)
# temp_112 = cur_1[temp_111]
lw $t0, 8($sp)
lw $t1, 96($sp)
sll $t1, $t1, 2
add $t0, $t0, $t1
lw $t0, 0($t0)
sw $t0, 100($sp)
# res_3[temp_109] = temp_112
lw $t0, 100($sp)
lw $t1, 12($sp)
lw $t2, 88($sp)
sll $t2, $t2, 2
add $t1, $t1, $t2
sw $t0, 0($t1)
# ret 0
li $v0, 0
lw $ra, 0($sp)
addiu $sp, $sp, 244
jr $ra
j if1_END
ELSE1stmt: 
# temp_113 = res_3
lw $t0, 12($sp)
sw $t0, 104($sp)
# temp_114 = cur_1
lw $t0, 8($sp)
sw $t0, 108($sp)
# temp_115 = fib_matrix_1
la $s0, space0
sw $s0, 112($sp)
# push temp_113
lw $a0, 104($sp)
# push temp_114
lw $a1, 108($sp)
# push temp_115
lw $a2, 112($sp)
# call __mat_mul
jal __mat_mul
lw $a0, 4($sp)
lw $a1, 8($sp)
lw $a2, 12($sp)
# temp_116 = __mat_mul
move $t0, $v0
sw $t0, 116($sp)
# temp_117 = 2*0
li $t1, 2
li $t2, 0
mult $t1, $t2
mflo $t1
sw $t1, 120($sp)
# temp_118 = temp_117+0
lw $t1, 120($sp)
addi $t0, $t1, 0
sw $t0, 124($sp)
# temp_119 = 2*0
li $t1, 2
li $t2, 0
mult $t1, $t2
mflo $t1
sw $t1, 128($sp)
# temp_120 = temp_119+0
lw $t1, 128($sp)
addi $t0, $t1, 0
sw $t0, 132($sp)
# temp_121 = res_3[temp_120]
lw $t0, 12($sp)
lw $t1, 132($sp)
sll $t1, $t1, 2
add $t0, $t0, $t1
lw $t0, 0($t0)
sw $t0, 136($sp)
# cur_1[temp_118] = temp_121
lw $t0, 136($sp)
lw $t1, 8($sp)
lw $t2, 124($sp)
sll $t2, $t2, 2
add $t1, $t1, $t2
sw $t0, 0($t1)
# temp_122 = 2*0
li $t1, 2
li $t2, 0
mult $t1, $t2
mflo $t1
sw $t1, 140($sp)
# temp_123 = temp_122+1
lw $t1, 140($sp)
addi $t0, $t1, 1
sw $t0, 144($sp)
# temp_124 = 2*0
li $t1, 2
li $t2, 0
mult $t1, $t2
mflo $t1
sw $t1, 148($sp)
# temp_125 = temp_124+1
lw $t1, 148($sp)
addi $t0, $t1, 1
sw $t0, 152($sp)
# temp_126 = res_3[temp_125]
lw $t0, 12($sp)
lw $t1, 152($sp)
sll $t1, $t1, 2
add $t0, $t0, $t1
lw $t0, 0($t0)
sw $t0, 156($sp)
# cur_1[temp_123] = temp_126
lw $t0, 156($sp)
lw $t1, 8($sp)
lw $t2, 144($sp)
sll $t2, $t2, 2
add $t1, $t1, $t2
sw $t0, 0($t1)
# temp_127 = 2*1
li $t1, 2
li $t2, 1
mult $t1, $t2
mflo $t1
sw $t1, 160($sp)
# temp_128 = temp_127+0
lw $t1, 160($sp)
addi $t0, $t1, 0
sw $t0, 164($sp)
# temp_129 = 2*1
li $t1, 2
li $t2, 1
mult $t1, $t2
mflo $t1
sw $t1, 168($sp)
# temp_130 = temp_129+0
lw $t1, 168($sp)
addi $t0, $t1, 0
sw $t0, 172($sp)
# temp_131 = res_3[temp_130]
lw $t0, 12($sp)
lw $t1, 172($sp)
sll $t1, $t1, 2
add $t0, $t0, $t1
lw $t0, 0($t0)
sw $t0, 176($sp)
# cur_1[temp_128] = temp_131
lw $t0, 176($sp)
lw $t1, 8($sp)
lw $t2, 164($sp)
sll $t2, $t2, 2
add $t1, $t1, $t2
sw $t0, 0($t1)
# temp_132 = 2*1
li $t1, 2
li $t2, 1
mult $t1, $t2
mflo $t1
sw $t1, 180($sp)
# temp_133 = temp_132+1
lw $t1, 180($sp)
addi $t0, $t1, 1
sw $t0, 184($sp)
# temp_134 = 2*1
li $t1, 2
li $t2, 1
mult $t1, $t2
mflo $t1
sw $t1, 188($sp)
# temp_135 = temp_134+1
lw $t1, 188($sp)
addi $t0, $t1, 1
sw $t0, 192($sp)
# temp_136 = res_3[temp_135]
lw $t0, 12($sp)
lw $t1, 192($sp)
sll $t1, $t1, 2
add $t0, $t0, $t1
lw $t0, 0($t0)
sw $t0, 196($sp)
# cur_1[temp_133] = temp_136
lw $t0, 196($sp)
lw $t1, 8($sp)
lw $t2, 184($sp)
sll $t2, $t2, 2
add $t1, $t1, $t2
sw $t0, 0($t1)
# temp_137 = n_1
lw $t0, 4($sp)
sw $t0, 200($sp)
# temp_138 = temp_137-1
lw $t1, 200($sp)
addi $t0, $t1, -1
sw $t0, 204($sp)
# temp_139 = cur_1
lw $t0, 8($sp)
sw $t0, 208($sp)
# temp_140 = res_3
lw $t0, 12($sp)
sw $t0, 212($sp)
# push temp_138
lw $a0, 204($sp)
# push temp_139
lw $a1, 208($sp)
# push temp_140
lw $a2, 212($sp)
# call __power
jal __power
lw $a0, 4($sp)
lw $a1, 8($sp)
lw $a2, 12($sp)
# temp_141 = __power
move $t0, $v0
sw $t0, 216($sp)
# ret temp_141
lw $v0, 216($sp)
lw $ra, 0($sp)
addiu $sp, $sp, 244
jr $ra
if1_END: 
# ret 0
li $v0, 0
lw $ra, 0($sp)
addiu $sp, $sp, 244
jr $ra
power: 
addi $sp, $sp, -68
sw $ra, 0($sp)
sw $a0, 4($sp)
sw $a1, 8($sp)
if2: 
# temp_142 = n_2
lw $t0, 4($sp)
sw $t0, 12($sp)
# temp_143 = n_2
lw $t0, 4($sp)
sw $t0, 16($sp)
lw $s0, 16($sp)
blez $s0 if2stmt
j if2_END
# temp_144 = n_2
lw $t0, 4($sp)
sw $t0, 20($sp)
lw $s0, 20($sp)
blez $s0 if2stmt
j if2_END
if2stmt: 
# ret -1
li $v0, -1
lw $ra, 0($sp)
addiu $sp, $sp, 68
jr $ra
if2_END: 
# int temp_145[4]
# temp_145[0] = 1
li $t0, 1
sw $t0, 24($sp)
# temp_145[1] = 1
li $t0, 1
sw $t0, 28($sp)
# temp_145[2] = 1
li $t0, 1
sw $t0, 32($sp)
# temp_145[3] = 0
li $t0, 0
sw $t0, 36($sp)
# temp_146 = n_2
lw $t0, 4($sp)
sw $t0, 40($sp)
# temp_147 = temp_145
addi $t0, $sp, 24
sw $t0, 44($sp)
# temp_148 = res_4
lw $t0, 8($sp)
sw $t0, 48($sp)
# push temp_146
lw $a0, 40($sp)
# push temp_147
lw $a1, 44($sp)
# push temp_148
lw $a2, 48($sp)
# call __power
jal __power
lw $a0, 4($sp)
lw $a1, 8($sp)
# temp_149 = __power
move $t0, $v0
sw $t0, 52($sp)
# ret temp_149
lw $v0, 52($sp)
lw $ra, 0($sp)
addiu $sp, $sp, 68
jr $ra
err:
# print_str, Error!\n
li $v0, 4
la $a0, str0
syscall 
# ret 1
li $v0, 1
addiu $sp, $sp, 0
jr $ra
out: 
addi $sp, $sp, -52
sw $a0, 0($sp)
# print_str, 
li $v0, 4
la $a0, str1
syscall 
# temp_150 = 2*0
li $t1, 2
li $t2, 0
mult $t1, $t2
mflo $t1
sw $t1, 4($sp)
# temp_151 = temp_150+0
lw $t1, 4($sp)
addi $t0, $t1, 0
sw $t0, 8($sp)
# temp_152 = r_1[temp_151]
lw $t0, 0($sp)
lw $t1, 8($sp)
sll $t1, $t1, 2
add $t0, $t0, $t1
lw $t0, 0($t0)
sw $t0, 12($sp)
# print_int, temp_152
li $v0, 1
lw $a0, 12($sp)
syscall 
# print_str,  
li $v0, 4
la $a0, str2
syscall 
# temp_153 = 2*0
li $t1, 2
li $t2, 0
mult $t1, $t2
mflo $t1
sw $t1, 16($sp)
# temp_154 = temp_153+1
lw $t1, 16($sp)
addi $t0, $t1, 1
sw $t0, 20($sp)
# temp_155 = r_1[temp_154]
lw $t0, 0($sp)
lw $t1, 20($sp)
sll $t1, $t1, 2
add $t0, $t0, $t1
lw $t0, 0($t0)
sw $t0, 24($sp)
# print_int, temp_155
li $v0, 1
lw $a0, 24($sp)
syscall 
# print_str, \n
li $v0, 4
la $a0, str3
syscall 
# temp_156 = 2*1
li $t1, 2
li $t2, 1
mult $t1, $t2
mflo $t1
sw $t1, 28($sp)
# temp_157 = temp_156+0
lw $t1, 28($sp)
addi $t0, $t1, 0
sw $t0, 32($sp)
# temp_158 = r_1[temp_157]
lw $t0, 0($sp)
lw $t1, 32($sp)
sll $t1, $t1, 2
add $t0, $t0, $t1
lw $t0, 0($t0)
sw $t0, 36($sp)
# print_int, temp_158
li $v0, 1
lw $a0, 36($sp)
syscall 
# print_str,  
li $v0, 4
la $a0, str4
syscall 
# temp_159 = 2*1
li $t1, 2
li $t2, 1
mult $t1, $t2
mflo $t1
sw $t1, 40($sp)
# temp_160 = temp_159+1
lw $t1, 40($sp)
addi $t0, $t1, 1
sw $t0, 44($sp)
# temp_161 = r_1[temp_160]
lw $t0, 0($sp)
lw $t1, 44($sp)
sll $t1, $t1, 2
add $t0, $t0, $t1
lw $t0, 0($t0)
sw $t0, 48($sp)
# print_int, temp_161
li $v0, 1
lw $a0, 48($sp)
syscall 
# print_str, \n
li $v0, 4
la $a0, str5
syscall 
# ret 1
li $v0, 1
addiu $sp, $sp, 52
jr $ra

