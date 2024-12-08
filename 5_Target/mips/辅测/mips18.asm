.data
# int a_1[510]
space0: .space 2040
# int prime_1[510]
space1: .space 2040
# const int reflect1_1[16]
space2: .space 64
# const int reflect2_1[25]
space3: .space 100
str0:.asciiz ""
str1:.asciiz " "
str2:.asciiz "\n"
.text
# reflect1_1[0] = -711942876
li $t0, -711942876
la $t1, space2
sw $t0, 0($t1)
# reflect1_1[1] = -1060809599
li $t0, -1060809599
la $t1, space2
sw $t0, 4($t1)
# reflect1_1[2] = 1758839997
li $t0, 1758839997
la $t1, space2
sw $t0, 8($t1)
# reflect1_1[3] = 423174272
li $t0, 423174272
la $t1, space2
sw $t0, 12($t1)
# reflect1_1[4] = 1412407466
li $t0, 1412407466
la $t1, space2
sw $t0, 16($t1)
# reflect1_1[5] = 375872692
li $t0, 375872692
la $t1, space2
sw $t0, 20($t1)
# reflect1_1[6] = 1543568839
li $t0, 1543568839
la $t1, space2
sw $t0, 24($t1)
# reflect1_1[7] = 915987033
li $t0, 915987033
la $t1, space2
sw $t0, 28($t1)
# reflect1_1[8] = 698198080
li $t0, 698198080
la $t1, space2
sw $t0, 32($t1)
# reflect1_1[9] = -2143283456
li $t0, -2143283456
la $t1, space2
sw $t0, 36($t1)
# reflect1_1[10] = 2059223660
li $t0, 2059223660
la $t1, space2
sw $t0, 40($t1)
# reflect1_1[11] = -34179219
li $t0, -34179219
la $t1, space2
sw $t0, 44($t1)
# reflect1_1[12] = 378910912
li $t0, 378910912
la $t1, space2
sw $t0, 48($t1)
# reflect1_1[13] = 1498631475
li $t0, 1498631475
la $t1, space2
sw $t0, 52($t1)
# reflect1_1[14] = -1853883889
li $t0, -1853883889
la $t1, space2
sw $t0, 56($t1)
# reflect1_1[15] = 1640319187
li $t0, 1640319187
la $t1, space2
sw $t0, 60($t1)
# reflect2_1[0] = -1152785601
li $t0, -1152785601
la $t1, space3
sw $t0, 0($t1)
# reflect2_1[1] = 1891446969
li $t0, 1891446969
la $t1, space3
sw $t0, 4($t1)
# reflect2_1[2] = 938484211
li $t0, 938484211
la $t1, space3
sw $t0, 8($t1)
# reflect2_1[3] = -1596516698
li $t0, -1596516698
la $t1, space3
sw $t0, 12($t1)
# reflect2_1[4] = 1681072330
li $t0, 1681072330
la $t1, space3
sw $t0, 16($t1)
# reflect2_1[5] = 128222498
li $t0, 128222498
la $t1, space3
sw $t0, 20($t1)
# reflect2_1[6] = 1090169129
li $t0, 1090169129
la $t1, space3
sw $t0, 24($t1)
# reflect2_1[7] = -347746822
li $t0, -347746822
la $t1, space3
sw $t0, 28($t1)
# reflect2_1[8] = -326257601
li $t0, -326257601
la $t1, space3
sw $t0, 32($t1)
# reflect2_1[9] = -1366930863
li $t0, -1366930863
la $t1, space3
sw $t0, 36($t1)
# reflect2_1[10] = -823961496
li $t0, -823961496
la $t1, space3
sw $t0, 40($t1)
# reflect2_1[11] = 2103453081
li $t0, 2103453081
la $t1, space3
sw $t0, 44($t1)
# reflect2_1[12] = -402114823
li $t0, -402114823
la $t1, space3
sw $t0, 48($t1)
# reflect2_1[13] = 2139806715
li $t0, 2139806715
la $t1, space3
sw $t0, 52($t1)
# reflect2_1[14] = -732814375
li $t0, -732814375
la $t1, space3
sw $t0, 56($t1)
# reflect2_1[15] = 1302967469
li $t0, 1302967469
la $t1, space3
sw $t0, 60($t1)
# reflect2_1[16] = 1623817872
li $t0, 1623817872
la $t1, space3
sw $t0, 64($t1)
# reflect2_1[17] = 898372270
li $t0, 898372270
la $t1, space3
sw $t0, 68($t1)
# reflect2_1[18] = -812618050
li $t0, -812618050
la $t1, space3
sw $t0, 72($t1)
# reflect2_1[19] = -1403178881
li $t0, -1403178881
la $t1, space3
sw $t0, 76($t1)
# reflect2_1[20] = -1776328495
li $t0, -1776328495
la $t1, space3
sw $t0, 80($t1)
# reflect2_1[21] = 958769364
li $t0, 958769364
la $t1, space3
sw $t0, 84($t1)
# reflect2_1[22] = 47496017
li $t0, 47496017
la $t1, space3
sw $t0, 88($t1)
# reflect2_1[23] = -1384738865
li $t0, -1384738865
la $t1, space3
sw $t0, 92($t1)
# reflect2_1[24] = 1751940200
li $t0, 1751940200
la $t1, space3
sw $t0, 96($t1)
jal main
li $v0, 10
syscall 
main: 
addi $sp, $sp, -244
# int i_1
# i_1 = 2
li $t0, 2
sw $t0, 0($sp)
# int total_1
# total_1 = getint()
li $v0, 5
syscall 
sw $v0, 4($sp)
for1: 
# temp_1 = i_1
lw $t0, 0($sp)
sw $t0, 8($sp)
# temp_2 = total_1
lw $t0, 4($sp)
sw $t0, 12($sp)
lw $s0, 8($sp)
lw $s1, 12($sp)
bge $s0 $s1 for1END
for1STMT: 
# temp_3 = i_1
lw $t0, 0($sp)
sw $t0, 16($sp)
# a_1[temp_3] = 1
li $t0, 1
la $t1, space0
lw $t2, 16($sp)
sll $t2, $t2, 2
add $t1, $t1, $t2
sw $t0, 0($t1)
# temp_4 = i_1
lw $t0, 0($sp)
sw $t0, 20($sp)
# temp_5 = temp_4+1
lw $t1, 20($sp)
addi $t0, $t1, 1
sw $t0, 24($sp)
# i_1 = temp_5
lw $t0, 24($sp)
sw $t0, 0($sp)
for1CHANGE_VAL: 
j for1
for1END: 
# i_1 = 2
li $t0, 2
sw $t0, 0($sp)
# int con_1
# con_1 = 0
li $t0, 0
sw $t0, 28($sp)
for2: 
# temp_6 = i_1
lw $t0, 0($sp)
sw $t0, 32($sp)
# temp_7 = total_1
lw $t0, 4($sp)
sw $t0, 36($sp)
lw $s0, 32($sp)
lw $s1, 36($sp)
bge $s0 $s1 for2END
for2STMT: 
if1: 
# temp_8 = i_1
lw $t0, 0($sp)
sw $t0, 40($sp)
# temp_9 = a_1[temp_8]
la $s0, space0
lw $t0, 40($sp)
sll $t0, $t0, 2
add $s0, $s0, $t0
lw $s0, 0($s0)
sw $s0, 44($sp)
lw $s0, 44($sp)
beqz $s0 if1_END
if1stmt: 
# temp_10 = con_1
lw $t0, 28($sp)
sw $t0, 48($sp)
# temp_11 = i_1
lw $t0, 0($sp)
sw $t0, 52($sp)
# prime_1[temp_10] = temp_11
lw $t0, 52($sp)
la $t1, space1
lw $t2, 48($sp)
sll $t2, $t2, 2
add $t1, $t1, $t2
sw $t0, 0($t1)
# temp_12 = con_1
lw $t0, 28($sp)
sw $t0, 56($sp)
# temp_13 = temp_12+1
lw $t1, 56($sp)
addi $t0, $t1, 1
sw $t0, 60($sp)
# con_1 = temp_13
lw $t0, 60($sp)
sw $t0, 28($sp)
if1_END: 
# int j_1
# j_1 = 0
li $t0, 0
sw $t0, 64($sp)
for3: 
# temp_14 = j_1
lw $t0, 64($sp)
sw $t0, 68($sp)
# temp_15 = con_1
lw $t0, 28($sp)
sw $t0, 72($sp)
lw $s0, 68($sp)
lw $s1, 72($sp)
bge $s0 $s1 for3END
# temp_16 = i_1
lw $t0, 0($sp)
sw $t0, 76($sp)
# temp_17 = j_1
lw $t0, 64($sp)
sw $t0, 80($sp)
# temp_18 = prime_1[temp_17]
la $s0, space1
lw $t0, 80($sp)
sll $t0, $t0, 2
add $s0, $s0, $t0
lw $s0, 0($s0)
sw $s0, 84($sp)
# temp_19 = temp_16*temp_18
lw $t1, 76($sp)
lw $t2, 84($sp)
mult $t1, $t2
mflo $t1
sw $t1, 88($sp)
# temp_20 = total_1
lw $t0, 4($sp)
sw $t0, 92($sp)
lw $s0, 88($sp)
lw $s1, 92($sp)
bgt $s0 $s1 for3END
for3STMT: 
# temp_21 = i_1
lw $t0, 0($sp)
sw $t0, 96($sp)
# temp_22 = j_1
lw $t0, 64($sp)
sw $t0, 100($sp)
# temp_23 = prime_1[temp_22]
la $s0, space1
lw $t0, 100($sp)
sll $t0, $t0, 2
add $s0, $s0, $t0
lw $s0, 0($s0)
sw $s0, 104($sp)
# temp_24 = temp_21*temp_23
lw $t1, 96($sp)
lw $t2, 104($sp)
mult $t1, $t2
mflo $t1
sw $t1, 108($sp)
# a_1[temp_24] = 0
li $t0, 0
la $t1, space0
lw $t2, 108($sp)
sll $t2, $t2, 2
add $t1, $t1, $t2
sw $t0, 0($t1)
if2: 
# temp_25 = i_1
lw $t0, 0($sp)
sw $t0, 112($sp)
# temp_26 = j_1
lw $t0, 64($sp)
sw $t0, 116($sp)
# temp_27 = prime_1[temp_26]
la $s0, space1
lw $t0, 116($sp)
sll $t0, $t0, 2
add $s0, $s0, $t0
lw $s0, 0($s0)
sw $s0, 120($sp)
# temp_28 = temp_25%temp_27
lw $t1, 112($sp)
lw $t2, 120($sp)
div $t1, $t2
mfhi $t1
sw $t1, 124($sp)
lw $s0, 124($sp)
beqz $s0 if2stmt
j if2_END
if2stmt: 
j for3END
if2_END: 
# temp_29 = j_1
lw $t0, 64($sp)
sw $t0, 128($sp)
# temp_30 = temp_29+1
lw $t1, 128($sp)
addi $t0, $t1, 1
sw $t0, 132($sp)
# j_1 = temp_30
lw $t0, 132($sp)
sw $t0, 64($sp)
for3CHANGE_VAL: 
j for3
for3END: 
# temp_31 = i_1
lw $t0, 0($sp)
sw $t0, 136($sp)
# temp_32 = temp_31+1
lw $t1, 136($sp)
addi $t0, $t1, 1
sw $t0, 140($sp)
# i_1 = temp_32
lw $t0, 140($sp)
sw $t0, 0($sp)
for2CHANGE_VAL: 
j for2
for2END: 
# i_1 = 0
li $t0, 0
sw $t0, 0($sp)
for4: 
li $s0, 114514
beqz $s0 for4END
for4STMT: 
if3: 
# temp_33 = i_1
lw $t0, 0($sp)
sw $t0, 144($sp)
# temp_34 = a_1[temp_33]
la $s0, space0
lw $t0, 144($sp)
sll $t0, $t0, 2
add $s0, $s0, $t0
lw $s0, 0($s0)
sw $s0, 148($sp)
lw $s0, 148($sp)
beqz $s0 if3_END
if3stmt: 
# print_str, 
li $v0, 4
la $a0, str0
syscall 
# temp_35 = i_1
lw $t0, 0($sp)
sw $t0, 152($sp)
# print_int, temp_35
li $v0, 1
lw $a0, 152($sp)
syscall 
# print_str,  
li $v0, 4
la $a0, str1
syscall 
# temp_36 = i_1
lw $t0, 0($sp)
sw $t0, 156($sp)
# temp_37 = temp_36%2
lw $t1, 156($sp)
li $t2, 2
div $t1, $t2
mfhi $t1
sw $t1, 160($sp)
# temp_38 = i_1
lw $t0, 0($sp)
sw $t0, 164($sp)
# temp_39 = temp_38%16
lw $t1, 164($sp)
li $t2, 16
div $t1, $t2
mfhi $t1
sw $t1, 168($sp)
# temp_40 = reflect1_1[temp_39]
la $s0, space2
lw $t0, 168($sp)
sll $t0, $t0, 2
add $s0, $s0, $t0
lw $s0, 0($s0)
sw $s0, 172($sp)
# temp_41 = temp_37*temp_40
lw $t1, 160($sp)
lw $t2, 172($sp)
mult $t1, $t2
mflo $t1
sw $t1, 176($sp)
# temp_42 = i_1
lw $t0, 0($sp)
sw $t0, 180($sp)
# temp_43 = temp_42+1
lw $t1, 180($sp)
addi $t0, $t1, 1
sw $t0, 184($sp)
# temp_44 = temp_43%2
lw $t1, 184($sp)
li $t2, 2
div $t1, $t2
mfhi $t1
sw $t1, 188($sp)
# temp_45 = i_1
lw $t0, 0($sp)
sw $t0, 192($sp)
# temp_46 = temp_45%5
lw $t1, 192($sp)
li $t2, 5
div $t1, $t2
mfhi $t1
sw $t1, 196($sp)
# temp_47 = i_1
lw $t0, 0($sp)
sw $t0, 200($sp)
# temp_48 = temp_47%5
lw $t1, 200($sp)
li $t2, 5
div $t1, $t2
mfhi $t1
sw $t1, 204($sp)
# temp_49 = 5*temp_46
li $t1, 5
lw $t2, 196($sp)
mult $t1, $t2
mflo $t1
sw $t1, 208($sp)
# temp_50 = temp_49+temp_48
lw $t1, 208($sp)
lw $t2, 204($sp)
add $t0, $t1, $t2
sw $t0, 212($sp)
# temp_51 = reflect2_1[temp_50]
la $s0, space3
lw $t0, 212($sp)
sll $t0, $t0, 2
add $s0, $s0, $t0
lw $s0, 0($s0)
sw $s0, 216($sp)
# temp_52 = temp_44*temp_51
lw $t1, 188($sp)
lw $t2, 216($sp)
mult $t1, $t2
mflo $t1
sw $t1, 220($sp)
# temp_53 = temp_41+temp_52
lw $t1, 176($sp)
lw $t2, 220($sp)
add $t0, $t1, $t2
sw $t0, 224($sp)
# print_int, temp_53
li $v0, 1
lw $a0, 224($sp)
syscall 
# print_str, \n
li $v0, 4
la $a0, str2
syscall 
if3_END: 
# temp_54 = i_1
lw $t0, 0($sp)
sw $t0, 228($sp)
# temp_55 = temp_54+1
lw $t1, 228($sp)
addi $t0, $t1, 1
sw $t0, 232($sp)
# i_1 = temp_55
lw $t0, 232($sp)
sw $t0, 0($sp)
if4: 
# temp_56 = i_1
lw $t0, 0($sp)
sw $t0, 236($sp)
# temp_57 = total_1
lw $t0, 4($sp)
sw $t0, 240($sp)
lw $s0, 236($sp)
lw $s1, 240($sp)
blt $s0 $s1 if4stmt
j ELSE4stmt
if4stmt: 
j for4CHANGE_VAL
j if4_END
ELSE4stmt: 
j for4END
if4_END: 
j for4CHANGE_VAL
j for4END
j for4CHANGE_VAL
j for4END
j for4CHANGE_VAL
j for4END
j for4CHANGE_VAL
j for4END
for4CHANGE_VAL: 
j for4
for4END: 
# ret 0
li $v0, 0
addiu $sp, $sp, 244
jr $ra