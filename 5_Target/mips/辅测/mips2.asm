.data
# const int a1_1
space0: .space 4
# const int a2_1
space1: .space 4
# const int a3_1
space2: .space 4
# const int a4_1
space3: .space 4
# const int a5_1
space4: .space 4
# const int a6_1
space5: .space 4
# const int b_1[2]
space6: .space 8
# const int c_1[6]
space7: .space 24
str0:.asciiz "21373119"
str1:.asciiz ""
str2:.asciiz "\n"
str3:.asciiz ""
str4:.asciiz ""
.text
# a1_1 = 1
li $t0, 1
la $s0, space0
sw $t0, 0($s0)
# a2_1 = 2
li $t0, 2
la $s0, space1
sw $t0, 0($s0)
# a3_1 = 3
li $t0, 3
la $s0, space2
sw $t0, 0($s0)
# a4_1 = 2
li $t0, 2
la $s0, space3
sw $t0, 0($s0)
# a5_1 = 3
li $t0, 3
la $s0, space4
sw $t0, 0($s0)
# a6_1 = 4
li $t0, 4
la $s0, space5
sw $t0, 0($s0)
# b_1[0] = 0
li $t0, 0
la $t1, space6
sw $t0, 0($t1)
# b_1[1] = 1
li $t0, 1
la $t1, space6
sw $t0, 4($t1)
# c_1[0] = 0
li $t0, 0
la $t1, space7
sw $t0, 0($t1)
# c_1[1] = 0
li $t0, 0
la $t1, space7
sw $t0, 4($t1)
# c_1[2] = 1
li $t0, 1
la $t1, space7
sw $t0, 8($t1)
# c_1[3] = 0
li $t0, 0
la $t1, space7
sw $t0, 12($t1)
# c_1[4] = 0
li $t0, 0
la $t1, space7
sw $t0, 16($t1)
# c_1[5] = 2
li $t0, 2
la $t1, space7
sw $t0, 20($t1)
jal main
li $v0, 10
syscall 
main: 
addi $sp, $sp, -648
sw $ra, 0($sp)
# print_str, 21373119
li $v0, 4
la $a0, str0
syscall 
# int a_5
# a_5 = 1
li $t0, 1
sw $t0, 4($sp)
# int xm_1
# xm_1 = 0
li $t0, 0
sw $t0, 8($sp)
# int xmm_1
# xmm_1 = 0
li $t0, 0
sw $t0, 12($sp)
# int yxm_1[2]
# yxm_1[0] = 1
li $t0, 1
sw $t0, 16($sp)
# yxm_1[1] = 0
li $t0, 0
sw $t0, 20($sp)
# int yxmm_1[20]
# int yxmmm_1
# int b1_1[2]
# b1_1[0] = 1
li $t0, 1
sw $t0, 108($sp)
# b1_1[1] = 2
li $t0, 2
sw $t0, 112($sp)
# int c1_1[6]
# c1_1[0] = 1
li $t0, 1
sw $t0, 116($sp)
# c1_1[1] = 2
li $t0, 2
sw $t0, 120($sp)
# c1_1[2] = 3
li $t0, 3
sw $t0, 124($sp)
# c1_1[3] = 4
li $t0, 4
sw $t0, 128($sp)
# c1_1[4] = 5
li $t0, 5
sw $t0, 132($sp)
# c1_1[5] = 6
li $t0, 6
sw $t0, 136($sp)
# int a_6
# a_6 = 1
li $t0, 1
sw $t0, 140($sp)
# print_str, 
li $v0, 4
la $a0, str1
syscall 
# temp_6 = a_6
lw $t0, 140($sp)
sw $t0, 144($sp)
# print_int, temp_6
li $v0, 1
lw $a0, 144($sp)
syscall 
# print_str, \n
li $v0, 4
la $a0, str2
syscall 
# int x_2
# x_2 = 12
li $t0, 12
sw $t0, 148($sp)
# int y_1
# y_1 = 10
li $t0, 10
sw $t0, 152($sp)
# temp_7 = x_2
lw $t0, 148($sp)
sw $t0, 156($sp)
# temp_8 = y_1
lw $t0, 152($sp)
sw $t0, 160($sp)
# temp_9 = temp_7*temp_8
lw $t1, 156($sp)
lw $t2, 160($sp)
mult $t1, $t2
mflo $t1
sw $t1, 164($sp)
# a_5 = temp_9
lw $t0, 164($sp)
sw $t0, 4($sp)
# temp_10 = x_2
lw $t0, 148($sp)
sw $t0, 168($sp)
# temp_11 = y_1
lw $t0, 152($sp)
sw $t0, 172($sp)
# temp_12 = temp_10/temp_11
lw $t1, 168($sp)
lw $t2, 172($sp)
div $t1, $t2
mflo $t1
sw $t1, 176($sp)
# a_5 = temp_12
lw $t0, 176($sp)
sw $t0, 4($sp)
# temp_13 = x_2
lw $t0, 148($sp)
sw $t0, 180($sp)
# temp_14 = y_1
lw $t0, 152($sp)
sw $t0, 184($sp)
# temp_15 = temp_13%temp_14
lw $t1, 180($sp)
lw $t2, 184($sp)
div $t1, $t2
mfhi $t1
sw $t1, 188($sp)
# a_5 = temp_15
lw $t0, 188($sp)
sw $t0, 4($sp)
# temp_16 = x_2
lw $t0, 148($sp)
sw $t0, 192($sp)
# temp_17 = y_1
lw $t0, 152($sp)
sw $t0, 196($sp)
# temp_18 = temp_16+temp_17
lw $t1, 192($sp)
lw $t2, 196($sp)
add $t0, $t1, $t2
sw $t0, 200($sp)
# a_5 = temp_18
lw $t0, 200($sp)
sw $t0, 4($sp)
# temp_19 = x_2
lw $t0, 148($sp)
sw $t0, 204($sp)
# temp_20 = y_1
lw $t0, 152($sp)
sw $t0, 208($sp)
# temp_21 = temp_19+temp_20
lw $t1, 204($sp)
lw $t2, 208($sp)
add $t0, $t1, $t2
sw $t0, 212($sp)
# temp_22 = temp_21+2
lw $t1, 212($sp)
addi $t0, $t1, 2
sw $t0, 216($sp)
# a_5 = temp_22
lw $t0, 216($sp)
sw $t0, 4($sp)
# temp_23 = x_2
lw $t0, 148($sp)
sw $t0, 220($sp)
# temp_24 = y_1
lw $t0, 152($sp)
sw $t0, 224($sp)
# temp_25 = temp_23-temp_24
lw $t1, 220($sp)
lw $t2, 224($sp)
sub $t0, $t1, $t2
sw $t0, 228($sp)
# a_5 = temp_25
lw $t0, 228($sp)
sw $t0, 4($sp)
# temp_26 = x_2
lw $t0, 148($sp)
sw $t0, 232($sp)
# temp_27 = y_1
lw $t0, 152($sp)
sw $t0, 236($sp)
# temp_28 = temp_26*temp_27
lw $t1, 232($sp)
lw $t2, 236($sp)
mult $t1, $t2
mflo $t1
sw $t1, 240($sp)
# temp_29 = x_2
lw $t0, 148($sp)
sw $t0, 244($sp)
# temp_30 = temp_28*temp_29
lw $t1, 240($sp)
lw $t2, 244($sp)
mult $t1, $t2
mflo $t1
sw $t1, 248($sp)
# temp_31 = temp_30/2
lw $t1, 248($sp)
li $t2, 2
div $t1, $t2
mflo $t1
sw $t1, 252($sp)
# a_5 = temp_31
lw $t0, 252($sp)
sw $t0, 4($sp)
# temp_32 = x_2
lw $t0, 148($sp)
sw $t0, 256($sp)
# temp_33 = y_1
lw $t0, 152($sp)
sw $t0, 260($sp)
# temp_34 = temp_32+temp_33
lw $t1, 256($sp)
lw $t2, 260($sp)
add $t0, $t1, $t2
sw $t0, 264($sp)
# temp_35 = x_2
lw $t0, 148($sp)
sw $t0, 268($sp)
# temp_36 = temp_34/temp_35
lw $t1, 264($sp)
lw $t2, 268($sp)
div $t1, $t2
mflo $t1
sw $t1, 272($sp)
# temp_37 = temp_36*2
lw $t1, 272($sp)
li $t2, 2
mult $t1, $t2
mflo $t1
sw $t1, 276($sp)
# a_5 = temp_37
lw $t0, 276($sp)
sw $t0, 4($sp)
# temp_38 = x_2
lw $t0, 148($sp)
sw $t0, 280($sp)
# temp_39 = y_1
lw $t0, 152($sp)
sw $t0, 284($sp)
# temp_40 = temp_38-temp_39
lw $t1, 280($sp)
lw $t2, 284($sp)
sub $t0, $t1, $t2
sw $t0, 288($sp)
# temp_41 = x_2
lw $t0, 148($sp)
sw $t0, 292($sp)
# temp_42 = temp_40*temp_41
lw $t1, 288($sp)
lw $t2, 292($sp)
mult $t1, $t2
mflo $t1
sw $t1, 296($sp)
# temp_43 = x_2
lw $t0, 148($sp)
sw $t0, 300($sp)
# temp_44 = temp_43%7
lw $t1, 300($sp)
li $t2, 7
div $t1, $t2
mfhi $t1
sw $t1, 304($sp)
# temp_45 = temp_42+temp_44
lw $t1, 296($sp)
lw $t2, 304($sp)
add $t0, $t1, $t2
sw $t0, 308($sp)
# a_5 = temp_45
lw $t0, 308($sp)
sw $t0, 4($sp)
# a_5 = 0
li $t0, 0
sw $t0, 4($sp)
# a_5 = -1
li $t0, -1
sw $t0, 4($sp)
# a_5 = -1
li $t0, -1
sw $t0, 4($sp)
# a_5 = 1
li $t0, 1
sw $t0, 4($sp)
# temp_46 = a_5
lw $t0, 4($sp)
sw $t0, 312($sp)
# temp_47 = a_5
lw $t0, 4($sp)
sw $t0, 316($sp)
# push temp_46
lw $a0, 312($sp)
# push temp_47
lw $a1, 316($sp)
# call test1
jal test1
# temp_48 = test1
move $t0, $v0
sw $t0, 320($sp)
# call test2
jal test2
# temp_49 = test2
move $t0, $v0
sw $t0, 324($sp)
# b1_1[1] = 1
li $t0, 1
sw $t0, 112($sp)
# temp_50 = 3*0
li $t1, 3
li $t2, 0
mult $t1, $t2
mflo $t1
sw $t1, 328($sp)
# temp_51 = temp_50+0
lw $t1, 328($sp)
addi $t0, $t1, 0
sw $t0, 332($sp)
# c1_1[temp_51] = 0
li $t0, 0
sw $t0, 448($sp)
# temp_52 = 3*0
li $t1, 3
li $t2, 0
mult $t1, $t2
mflo $t1
sw $t1, 336($sp)
# temp_53 = temp_52+0
lw $t1, 336($sp)
addi $t0, $t1, 0
sw $t0, 340($sp)
# temp_54 = c1_1[temp_53]
lw $t1, 340($sp)
sll $t1, $t1, 2
addi $t0, $t1, 116
add $t0, $t0, $sp
lw $t0, 0($t0)
sw $t0, 344($sp)
# a_5 = temp_54
lw $t0, 344($sp)
sw $t0, 4($sp)
# temp_55 = b1_1[1]
lw $t0, 112($sp)
sw $t0, 348($sp)
# a_5 = temp_55
lw $t0, 348($sp)
sw $t0, 4($sp)
# a_5 = getint()
li $v0, 5
syscall 
sw $v0, 4($sp)
# temp_56 = a_5
lw $t0, 4($sp)
sw $t0, 352($sp)
# temp_57 = b1_1
addi $t0, $sp, 108
sw $t0, 356($sp)
# temp_58 = c1_1
addi $t0, $sp, 116
sw $t0, 360($sp)
# push temp_56
lw $a0, 352($sp)
# push temp_57
lw $a1, 356($sp)
# push temp_58
lw $a2, 360($sp)
# call test5
jal test5
# temp_59 = test5
move $t0, $v0
sw $t0, 364($sp)
# temp_60 = b1_1
addi $t0, $sp, 108
sw $t0, 368($sp)
# temp_61 = c1_1
addi $t0, $sp, 116
sw $t0, 372($sp)
# push temp_60
lw $a0, 368($sp)
# push temp_61
lw $a1, 372($sp)
# call test3
jal test3
# temp_62 = test3
move $t0, $v0
sw $t0, 376($sp)
# a_5 = temp_62
lw $t0, 376($sp)
sw $t0, 4($sp)
# temp_63 = 3*0
li $t1, 3
li $t2, 0
mult $t1, $t2
mflo $t1
sw $t1, 380($sp)
# temp_64 = temp_63*4
lw $t1, 380($sp)
li $t2, 4
mult $t1, $t2
mflo $t1
sw $t1, 384($sp)
# temp_65 = &c1_1+temp_64
addi $t1, $sp, 116
lw $t2, 384($sp)
add $t0, $t1, $t2
sw $t0, 388($sp)
# temp_66 = temp_65
lw $t0, 388($sp)
sw $t0, 392($sp)
# temp_67 = c1_1
addi $t0, $sp, 116
sw $t0, 396($sp)
# push temp_66
lw $a0, 392($sp)
# push temp_67
lw $a1, 396($sp)
# call test3
jal test3
# temp_68 = test3
move $t0, $v0
sw $t0, 400($sp)
# a_5 = temp_68
lw $t0, 400($sp)
sw $t0, 4($sp)
# temp_69 = a_5
lw $t0, 4($sp)
sw $t0, 404($sp)
# temp_70 = b1_1[1]
lw $t0, 112($sp)
sw $t0, 408($sp)
# temp_71 = 3*0
li $t1, 3
li $t2, 0
mult $t1, $t2
mflo $t1
sw $t1, 412($sp)
# temp_72 = temp_71+0
lw $t1, 412($sp)
addi $t0, $t1, 0
sw $t0, 416($sp)
# temp_73 = c1_1[temp_72]
lw $t1, 416($sp)
sll $t1, $t1, 2
addi $t0, $t1, 116
add $t0, $t0, $sp
lw $t0, 0($t0)
sw $t0, 420($sp)
# push temp_69
lw $a0, 404($sp)
# push temp_70
lw $a1, 408($sp)
# push temp_73
lw $a2, 420($sp)
# call test4
jal test4
# temp_74 = test4
move $t0, $v0
sw $t0, 424($sp)
# temp_75 = a_5
lw $t0, 4($sp)
sw $t0, 428($sp)
# push temp_75
lw $a0, 428($sp)
# call test6
jal test6
# temp_76 = test6
move $t0, $v0
sw $t0, 432($sp)
# temp_77 = 3*1
li $t1, 3
li $t2, 1
mult $t1, $t2
mflo $t1
sw $t1, 436($sp)
# temp_78 = temp_77+1
lw $t1, 436($sp)
addi $t0, $t1, 1
sw $t0, 440($sp)
# c1_1[temp_78] = 1
li $t0, 1
sw $t0, 556($sp)
if1: 
# temp_79 = x_2
lw $t0, 148($sp)
sw $t0, 444($sp)
# temp_80 = y_1
lw $t0, 152($sp)
sw $t0, 448($sp)
# temp_81 = x_2
lw $t0, 148($sp)
sw $t0, 452($sp)
# temp_82 = y_1
lw $t0, 152($sp)
sw $t0, 456($sp)
lw $s0, 444($sp)
lw $s1, 448($sp)
bgt $s0 $s1 if1stmt
lw $s0, 452($sp)
lw $s1, 456($sp)
blt $s0 $s1 if1stmt
j if1_END
if1stmt: 
# a_5 = 1
li $t0, 1
sw $t0, 4($sp)
if1_END: 
if2: 
# temp_83 = x_2
lw $t0, 148($sp)
sw $t0, 460($sp)
# temp_84 = y_1
lw $t0, 152($sp)
sw $t0, 464($sp)
lw $s0, 460($sp)
lw $s1, 464($sp)
bge $s0 $s1 if2stmt
j if2_END
if2stmt: 
# a_5 = 2
li $t0, 2
sw $t0, 4($sp)
if2_END: 
if3: 
# temp_85 = x_2
lw $t0, 148($sp)
sw $t0, 468($sp)
# temp_86 = y_1
lw $t0, 152($sp)
sw $t0, 472($sp)
lw $s0, 468($sp)
lw $s1, 472($sp)
ble $s0 $s1 if3stmt
j if3_END
if3stmt: 
# a_5 = 3
li $t0, 3
sw $t0, 4($sp)
if3_END: 
if4: 
# temp_87 = x_2
lw $t0, 148($sp)
sw $t0, 476($sp)
# temp_88 = y_1
lw $t0, 152($sp)
sw $t0, 480($sp)
# temp_89 = temp_88+1
lw $t1, 480($sp)
addi $t0, $t1, 1
sw $t0, 484($sp)
# temp_90 = x_2
lw $t0, 148($sp)
sw $t0, 488($sp)
# temp_91 = 0!temp_90
li $t1, 0
nor $t0, $t1, $0
sw $t0, 492($sp)
lw $s0, 476($sp)
lw $s1, 484($sp)
bgt $s0 $s1 if4stmt
lw $s0, 492($sp)
bnez $s0 if4stmt
j if4_END
if4stmt: 
# a_5 = 4
li $t0, 4
sw $t0, 4($sp)
if4_END: 
if5: 
# temp_92 = a_5
lw $t0, 4($sp)
sw $t0, 496($sp)
# temp_93 = b1_1[1]
lw $t0, 112($sp)
sw $t0, 500($sp)
lw $s1, 496($sp)
bnez $s1 tmpLabel0
lw $s2, 500($sp)
li $s3, 1
bne $s2 $s3 tmpLabel0
j if5stmt
tmpLabel0: 
j ELSE5stmt
if5stmt: 
# a_5 = 5
li $t0, 5
sw $t0, 4($sp)
j if5_END
ELSE5stmt: 
# a_5 = 6
li $t0, 6
sw $t0, 4($sp)
if5_END: 
if6: 
# temp_94 = a_5
lw $t0, 4($sp)
sw $t0, 504($sp)
# temp_95 = a_5
lw $t0, 4($sp)
sw $t0, 508($sp)
lw $s2, 504($sp)
li $s3, 1
beq $s2 $s3 if6stmt
lw $s2, 508($sp)
bnez $s2 if6stmt
j if6_END
if6stmt: 
# a_5 = 7
li $t0, 7
sw $t0, 4($sp)
if6_END: 
if7: 
if7stmt: 
if7_END: 
# xm_1 = 0
li $t0, 0
sw $t0, 8($sp)
for1: 
# temp_96 = xm_1
lw $t0, 8($sp)
sw $t0, 512($sp)
lw $s3, 512($sp)
li $s4, 1
bge $s3 $s4 for1END
for1STMT: 
if8: 
if8stmt: 
if8_END: 
j for1CHANGE_VAL
for1CHANGE_VAL: 
# temp_97 = xm_1
lw $t0, 8($sp)
sw $t0, 516($sp)
# temp_98 = temp_97+1
lw $t1, 516($sp)
addi $t0, $t1, 1
sw $t0, 520($sp)
# xm_1 = temp_98
lw $t0, 520($sp)
sw $t0, 8($sp)
j for1
for1END: 
# xm_1 = 0
li $t0, 0
sw $t0, 8($sp)
for2: 
# temp_99 = xm_1
lw $t0, 8($sp)
sw $t0, 524($sp)
lw $s3, 524($sp)
li $s4, 1
bge $s3 $s4 for2END
for2STMT: 
if9: 
if9stmt: 
if9_END: 
j for2CHANGE_VAL
for2CHANGE_VAL: 
# temp_100 = xm_1
lw $t0, 8($sp)
sw $t0, 528($sp)
# temp_101 = temp_100+1
lw $t1, 528($sp)
addi $t0, $t1, 1
sw $t0, 532($sp)
# xm_1 = temp_101
lw $t0, 532($sp)
sw $t0, 8($sp)
j for2
for2END: 
for3: 
# temp_102 = xm_1
lw $t0, 8($sp)
sw $t0, 536($sp)
lw $s3, 536($sp)
li $s4, 2
bge $s3 $s4 for3END
for3STMT: 
if10: 
# temp_103 = x_2
lw $t0, 148($sp)
sw $t0, 540($sp)
# temp_104 = y_1
lw $t0, 152($sp)
sw $t0, 544($sp)
# temp_105 = x_2
lw $t0, 148($sp)
sw $t0, 548($sp)
# temp_106 = y_1
lw $t0, 152($sp)
sw $t0, 552($sp)
lw $s3, 540($sp)
lw $s4, 544($sp)
bgt $s3 $s4 if10stmt
lw $s3, 548($sp)
lw $s4, 552($sp)
blt $s3 $s4 if10stmt
j if10_END
if10stmt: 
# xm_1 = 1
li $t0, 1
sw $t0, 8($sp)
if10_END: 
j for3END
for3CHANGE_VAL: 
# temp_107 = xm_1
lw $t0, 8($sp)
sw $t0, 556($sp)
# temp_108 = temp_107+1
lw $t1, 556($sp)
addi $t0, $t1, 1
sw $t0, 560($sp)
# xm_1 = temp_108
lw $t0, 560($sp)
sw $t0, 8($sp)
j for3
for3END: 
# xm_1 = 1
li $t0, 1
sw $t0, 8($sp)
for4: 
for4STMT: 
# xmm_1 = 2
li $t0, 2
sw $t0, 12($sp)
j for4END
for4CHANGE_VAL: 
# temp_109 = xm_1
lw $t0, 8($sp)
sw $t0, 564($sp)
# temp_110 = temp_109+1
lw $t1, 564($sp)
addi $t0, $t1, 1
sw $t0, 568($sp)
# xm_1 = temp_110
lw $t0, 568($sp)
sw $t0, 8($sp)
j for4
for4END: 
# xm_1 = 1
li $t0, 1
sw $t0, 8($sp)
for5: 
# temp_111 = xm_1
lw $t0, 8($sp)
sw $t0, 572($sp)
lw $s3, 572($sp)
li $s4, 1
bge $s3 $s4 for5END
for5STMT: 
# xmm_1 = 2
li $t0, 2
sw $t0, 12($sp)
# b1_1[1] = 1
li $t0, 1
sw $t0, 112($sp)
j for5END
for5CHANGE_VAL: 
j for5
for5END: 
for6: 
for6STMT: 
j for6END
for6CHANGE_VAL: 
# temp_112 = xm_1
lw $t0, 8($sp)
sw $t0, 576($sp)
# temp_113 = temp_112+1
lw $t1, 576($sp)
addi $t0, $t1, 1
sw $t0, 580($sp)
# xm_1 = temp_113
lw $t0, 580($sp)
sw $t0, 8($sp)
j for6
for6END: 
for7: 
# temp_114 = xm_1
lw $t0, 8($sp)
sw $t0, 584($sp)
lw $s3, 584($sp)
li $s4, 2
bge $s3 $s4 for7END
for7STMT: 
j for7END
for7CHANGE_VAL: 
j for7
for7END: 
# xm_1 = 1
li $t0, 1
sw $t0, 8($sp)
for8: 
for8STMT: 
j for8END
for8CHANGE_VAL: 
j for8
for8END: 
for9: 
for9STMT: 
j for9END
for9CHANGE_VAL: 
j for9
for9END: 
# print_str, 
li $v0, 4
la $a0, str3
syscall 
# temp_115 = a_5
lw $t0, 4($sp)
sw $t0, 588($sp)
# print_int, temp_115
li $v0, 1
lw $a0, 588($sp)
syscall 
# print_str, 
li $v0, 4
la $a0, str4
syscall 
# temp_116 = a_5
lw $t0, 4($sp)
sw $t0, 592($sp)
# print_int, temp_116
li $v0, 1
lw $a0, 592($sp)
syscall 
# ret 0
li $v0, 0
lw $ra, 0($sp)
addiu $sp, $sp, 648
jr $ra
test1: 
addi $sp, $sp, -8
sw $a0, 0($sp)
sw $a1, 4($sp)
# ret 
addiu $sp, $sp, 8
jr $ra
test2: 
# ret 
addiu $sp, $sp, 0
jr $ra
test3: 
addi $sp, $sp, -8
sw $a0, 0($sp)
sw $a1, 4($sp)
# ret 1
li $v0, 1
addiu $sp, $sp, 8
jr $ra
test4: 
addi $sp, $sp, -32
sw $a0, 0($sp)
sw $a1, 4($sp)
sw $a2, 8($sp)
# temp_1 = a_3
lw $t0, 0($sp)
sw $t0, 12($sp)
# temp_2 = b_4
lw $t0, 4($sp)
sw $t0, 16($sp)
# temp_3 = temp_1+temp_2
lw $t1, 12($sp)
lw $t2, 16($sp)
add $t0, $t1, $t2
sw $t0, 20($sp)
# temp_4 = c_2
lw $t0, 8($sp)
sw $t0, 24($sp)
# temp_5 = temp_3*temp_4
lw $t1, 20($sp)
lw $t2, 24($sp)
mult $t1, $t2
mflo $t1
sw $t1, 28($sp)
# ret temp_5
lw $v0, 28($sp)
addiu $sp, $sp, 32
jr $ra
test5: 
addi $sp, $sp, -12
sw $a0, 0($sp)
sw $a1, 4($sp)
sw $a2, 8($sp)
# ret 
addiu $sp, $sp, 12
jr $ra
test6: 
addi $sp, $sp, -4
sw $a0, 0($sp)
# ret 
addiu $sp, $sp, 4
jr $ra