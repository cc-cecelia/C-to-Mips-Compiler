.data
str0:.asciiz "! is ok\n"
str1:.asciiz "! has problem\n"
str2:.asciiz "< is ok\n"
str3:.asciiz "< has problem\n"
str4:.asciiz "> is ok\n"
str5:.asciiz "> has problem\n"
str6:.asciiz "<= is ok\n"
str7:.asciiz "<= has problem\n"
str8:.asciiz ">= is ok\n"
str9:.asciiz ">= has problem\n"
str10:.asciiz "== != is ok\n"
str11:.asciiz "== != has problem\n"
str12:.asciiz "or is ok\n"
str13:.asciiz "or has problem\n"
str14:.asciiz "the priority of and/or is ok\n"
str15:.asciiz "the priority of and/or has problem\n"
str16:.asciiz ""
str17:.asciiz " "
str18:.asciiz "\n"
.text
jal main
li $v0, 10
syscall 
main: 
addi $sp, $sp, -692
# int a_1
# a_1 = 5
li $t0, 5
sw $t0, 0($sp)
# int b_1
# b_1 = 6
li $t0, 6
sw $t0, 4($sp)
# int s1_1[6]
# s1_1[0] = 0
li $t0, 0
sw $t0, 8($sp)
# s1_1[1] = 1
li $t0, 1
sw $t0, 12($sp)
# s1_1[2] = 2
li $t0, 2
sw $t0, 16($sp)
# s1_1[3] = 3
li $t0, 3
sw $t0, 20($sp)
# s1_1[4] = 6
li $t0, 6
sw $t0, 24($sp)
# s1_1[5] = 12
li $t0, 12
sw $t0, 28($sp)
# int s2_1[6]
# s2_1[0] = 10
li $t0, 10
sw $t0, 32($sp)
# s2_1[1] = 11
li $t0, 11
sw $t0, 36($sp)
# s2_1[2] = 12
li $t0, 12
sw $t0, 40($sp)
# s2_1[3] = 13
li $t0, 13
sw $t0, 44($sp)
# s2_1[4] = 14
li $t0, 14
sw $t0, 48($sp)
# s2_1[5] = 15
li $t0, 15
sw $t0, 52($sp)
if1: 
# temp_1 = s1_1[0]
lw $t0, 8($sp)
sw $t0, 56($sp)
# temp_2 = 0!temp_1
lw $t1, 56($sp)
seq $t0, $0, $t1
sw $t0, 60($sp)
lw $s0, 60($sp)
li $s1, 1
bne $s0 $s1 tmpLabel0
# temp_3 = s1_1[0]
lw $t0, 8($sp)
sw $t0, 64($sp)
# temp_4 = temp_3*2
lw $t1, 64($sp)
li $t2, 2
mult $t1, $t2
mflo $t1
sw $t1, 68($sp)
# temp_5 = 0!temp_4
lw $t1, 68($sp)
seq $t0, $0, $t1
sw $t0, 72($sp)
lw $s0, 72($sp)
beqz $s0 tmpLabel0
li $s0, 1
beqz $s0 tmpLabel0
j if1stmt
tmpLabel0: 
j ELSE1stmt
if1stmt: 
# print_str, ! is ok\n
li $v0, 4
la $a0, str0
syscall 
j if1_END
ELSE1stmt: 
# print_str, ! has problem\n
li $v0, 4
la $a0, str1
syscall 
if1_END: 
if2: 
# temp_6 = a_1
lw $t0, 0($sp)
sw $t0, 76($sp)
# temp_7 = b_1
lw $t0, 4($sp)
sw $t0, 80($sp)
# temp_8 = temp_6+temp_7
lw $t1, 76($sp)
lw $t2, 80($sp)
add $t0, $t1, $t2
sw $t0, 84($sp)
# temp_9 = s1_1[5]
lw $t0, 28($sp)
sw $t0, 88($sp)
lw $s0, 84($sp)
lw $s1, 88($sp)
bge $s0 $s1 tmpLabel1
# temp_11 = s1_1[3]
lw $t0, 20($sp)
sw $t0, 92($sp)
# temp_12 = s1_1[0]
lw $t0, 8($sp)
sw $t0, 96($sp)
# temp_13 = temp_11-temp_12
lw $t1, 92($sp)
lw $t2, 96($sp)
sub $t0, $t1, $t2
sw $t0, 100($sp)
# temp_14 = 3*1
li $t1, 3
li $t2, 1
mult $t1, $t2
mflo $t1
sw $t1, 104($sp)
# temp_15 = temp_14+2
lw $t1, 104($sp)
addi $t0, $t1, 2
sw $t0, 108($sp)
# temp_16 = s2_1[temp_15]
lw $t1, 108($sp)
sll $t1, $t1, 2
addi $t0, $t1, 32
add $t0, $t0, $sp
lw $t0, 0($t0)
sw $t0, 112($sp)
# temp_17 = a_1
lw $t0, 0($sp)
sw $t0, 116($sp)
# temp_18 = temp_16/temp_17
lw $t1, 112($sp)
lw $t2, 116($sp)
div $t1, $t2
mflo $t1
sw $t1, 120($sp)
# temp_10 = temp_13<temp_18
lw $t1, 100($sp)
lw $t2, 120($sp)
lw $s0, 124($sp)
bnez $s0 tmpLabel1
# temp_21 = b_1
lw $t0, 4($sp)
sw $t0, 128($sp)
# temp_22 = a_1
lw $t0, 0($sp)
sw $t0, 132($sp)
# temp_23 = b_1
lw $t0, 4($sp)
sw $t0, 136($sp)
# temp_24 = temp_22+temp_23
lw $t1, 132($sp)
lw $t2, 136($sp)
add $t0, $t1, $t2
sw $t0, 140($sp)
# temp_20 = temp_21<temp_24
lw $t1, 128($sp)
lw $t2, 140($sp)
# temp_25 = s1_1[0]
lw $t0, 8($sp)
sw $t0, 148($sp)
# temp_19 = temp_20<temp_25
lw $t1, 144($sp)
lw $t2, 148($sp)
lw $s0, 152($sp)
bnez $s0 tmpLabel1
# temp_29 = b_1
lw $t0, 4($sp)
sw $t0, 156($sp)
# temp_30 = a_1
lw $t0, 0($sp)
sw $t0, 160($sp)
# temp_31 = b_1
lw $t0, 4($sp)
sw $t0, 164($sp)
# temp_32 = temp_30+temp_31
lw $t1, 160($sp)
lw $t2, 164($sp)
add $t0, $t1, $t2
sw $t0, 168($sp)
# temp_28 = temp_29<temp_32
lw $t1, 156($sp)
lw $t2, 168($sp)
# temp_33 = s1_1[2]
lw $t0, 16($sp)
sw $t0, 176($sp)
# temp_27 = temp_28<temp_33
lw $t1, 172($sp)
lw $t2, 176($sp)
# temp_34 = s1_1[1]
lw $t0, 12($sp)
sw $t0, 184($sp)
# temp_26 = temp_27>temp_34
lw $t1, 180($sp)
lw $t2, 184($sp)
lw $s0, 188($sp)
bne $0 $s0 tmpLabel1
j if2stmt
tmpLabel1: 
j ELSE2stmt
if2stmt: 
# print_str, < is ok\n
li $v0, 4
la $a0, str2
syscall 
j if2_END
ELSE2stmt: 
# print_str, < has problem\n
li $v0, 4
la $a0, str3
syscall 
if2_END: 
if3: 
# temp_36 = a_1
lw $t0, 0($sp)
sw $t0, 192($sp)
# temp_37 = b_1
lw $t0, 4($sp)
sw $t0, 196($sp)
# temp_38 = temp_36*temp_37
lw $t1, 192($sp)
lw $t2, 196($sp)
mult $t1, $t2
mflo $t1
sw $t1, 200($sp)
# temp_39 = s1_1[2]
lw $t0, 16($sp)
sw $t0, 204($sp)
# temp_40 = 3*1
li $t1, 3
li $t2, 1
mult $t1, $t2
mflo $t1
sw $t1, 208($sp)
# temp_41 = temp_40+2
lw $t1, 208($sp)
addi $t0, $t1, 2
sw $t0, 212($sp)
# temp_42 = s2_1[temp_41]
lw $t1, 212($sp)
sll $t1, $t1, 2
addi $t0, $t1, 32
add $t0, $t0, $sp
lw $t0, 0($t0)
sw $t0, 216($sp)
# temp_43 = temp_39*temp_42
lw $t1, 204($sp)
lw $t2, 216($sp)
mult $t1, $t2
mflo $t1
sw $t1, 220($sp)
# temp_35 = temp_38>temp_43
lw $t1, 200($sp)
lw $t2, 220($sp)
lw $s0, 224($sp)
bnez $s0 tmpLabel2
# temp_44 = 3*0
li $t1, 3
li $t2, 0
mult $t1, $t2
mflo $t1
sw $t1, 228($sp)
# temp_45 = temp_44+1
lw $t1, 228($sp)
addi $t0, $t1, 1
sw $t0, 232($sp)
# temp_46 = s2_1[temp_45]
lw $t1, 232($sp)
sll $t1, $t1, 2
addi $t0, $t1, 32
add $t0, $t0, $sp
lw $t0, 0($t0)
sw $t0, 236($sp)
# temp_47 = s1_1[2]
lw $t0, 16($sp)
sw $t0, 240($sp)
# temp_48 = temp_46-temp_47
lw $t1, 236($sp)
lw $t2, 240($sp)
sub $t0, $t1, $t2
sw $t0, 244($sp)
# temp_49 = a_1
lw $t0, 0($sp)
sw $t0, 248($sp)
# temp_50 = temp_48%temp_49
lw $t1, 244($sp)
lw $t2, 248($sp)
div $t1, $t2
mfhi $t1
sw $t1, 252($sp)
# temp_51 = 3*1
li $t1, 3
li $t2, 1
mult $t1, $t2
mflo $t1
sw $t1, 256($sp)
# temp_52 = temp_51+2
lw $t1, 256($sp)
addi $t0, $t1, 2
sw $t0, 260($sp)
# temp_53 = s2_1[temp_52]
lw $t1, 260($sp)
sll $t1, $t1, 2
addi $t0, $t1, 32
add $t0, $t0, $sp
lw $t0, 0($t0)
sw $t0, 264($sp)
# temp_54 = a_1
lw $t0, 0($sp)
sw $t0, 268($sp)
# temp_55 = temp_53/temp_54
lw $t1, 264($sp)
lw $t2, 268($sp)
div $t1, $t2
mflo $t1
sw $t1, 272($sp)
lw $s0, 252($sp)
lw $s1, 272($sp)
ble $s0 $s1 tmpLabel2
# temp_58 = s1_1[5]
lw $t0, 28($sp)
sw $t0, 276($sp)
# temp_59 = a_1
lw $t0, 0($sp)
sw $t0, 280($sp)
# temp_60 = b_1
lw $t0, 4($sp)
sw $t0, 284($sp)
# temp_61 = temp_59+temp_60
lw $t1, 280($sp)
lw $t2, 284($sp)
add $t0, $t1, $t2
sw $t0, 288($sp)
# temp_62 = temp_61+2
lw $t1, 288($sp)
addi $t0, $t1, 2
sw $t0, 292($sp)
# temp_57 = temp_58>temp_62
lw $t1, 276($sp)
lw $t2, 292($sp)
# temp_56 = temp_57>0
lw $t1, 296($sp)
li $t2, 0
lw $s0, 300($sp)
bnez $s0 tmpLabel2
# temp_65 = s1_1[5]
lw $t0, 28($sp)
sw $t0, 304($sp)
# temp_66 = a_1
lw $t0, 0($sp)
sw $t0, 308($sp)
# temp_67 = b_1
lw $t0, 4($sp)
sw $t0, 312($sp)
# temp_68 = temp_66+temp_67
lw $t1, 308($sp)
lw $t2, 312($sp)
add $t0, $t1, $t2
sw $t0, 316($sp)
# temp_64 = temp_65>temp_68
lw $t1, 304($sp)
lw $t2, 316($sp)
# temp_69 = s1_1[1]
lw $t0, 12($sp)
sw $t0, 324($sp)
# temp_63 = temp_64<temp_69
lw $t1, 320($sp)
lw $t2, 324($sp)
lw $s0, 328($sp)
bnez $s0 tmpLabel2
j if3stmt
tmpLabel2: 
j ELSE3stmt
if3stmt: 
# print_str, > is ok\n
li $v0, 4
la $a0, str4
syscall 
j if3_END
ELSE3stmt: 
# print_str, > has problem\n
li $v0, 4
la $a0, str5
syscall 
if3_END: 
if4: 
# temp_70 = a_1
lw $t0, 0($sp)
sw $t0, 332($sp)
# temp_71 = b_1
lw $t0, 4($sp)
sw $t0, 336($sp)
lw $s0, 332($sp)
lw $s1, 336($sp)
bgt $s0 $s1 tmpLabel3
# temp_72 = a_1
lw $t0, 0($sp)
sw $t0, 340($sp)
# temp_73 = b_1
lw $t0, 4($sp)
sw $t0, 344($sp)
# temp_74 = s1_1[1]
lw $t0, 12($sp)
sw $t0, 348($sp)
# temp_75 = temp_73+temp_74
lw $t1, 344($sp)
lw $t2, 348($sp)
add $t0, $t1, $t2
sw $t0, 352($sp)
lw $s0, 340($sp)
lw $s1, 352($sp)
bgt $s0 $s1 tmpLabel3
# temp_78 = a_1
lw $t0, 0($sp)
sw $t0, 356($sp)
# temp_79 = b_1
lw $t0, 4($sp)
sw $t0, 360($sp)
# temp_77 = temp_78<=temp_79
lw $t1, 356($sp)
lw $t2, 360($sp)
# temp_80 = s1_1[0]
lw $t0, 8($sp)
sw $t0, 368($sp)
# temp_76 = temp_77<=temp_80
lw $t1, 364($sp)
lw $t2, 368($sp)
lw $s0, 372($sp)
bnez $s0 tmpLabel3
# temp_83 = a_1
lw $t0, 0($sp)
sw $t0, 376($sp)
# temp_84 = b_1
lw $t0, 4($sp)
sw $t0, 380($sp)
# temp_82 = temp_83<=temp_84
lw $t1, 376($sp)
lw $t2, 380($sp)
# temp_85 = s1_1[2]
lw $t0, 16($sp)
sw $t0, 388($sp)
# temp_81 = temp_82>=temp_85
lw $t1, 384($sp)
lw $t2, 388($sp)
lw $s0, 392($sp)
li $s1, 1
bge $s0 $s1 tmpLabel3
j if4stmt
tmpLabel3: 
j ELSE4stmt
if4stmt: 
# print_str, <= is ok\n
li $v0, 4
la $a0, str6
syscall 
j if4_END
ELSE4stmt: 
# print_str, <= has problem\n
li $v0, 4
la $a0, str7
syscall 
if4_END: 
if5: 
# temp_86 = b_1
lw $t0, 4($sp)
sw $t0, 396($sp)
# temp_87 = a_1
lw $t0, 0($sp)
sw $t0, 400($sp)
# temp_88 = temp_87+1
lw $t1, 400($sp)
addi $t0, $t1, 1
sw $t0, 404($sp)
lw $s0, 396($sp)
lw $s1, 404($sp)
blt $s0 $s1 tmpLabel4
# temp_90 = b_1
lw $t0, 4($sp)
sw $t0, 408($sp)
# temp_91 = s1_1[4]
lw $t0, 24($sp)
sw $t0, 412($sp)
# temp_92 = s1_1[1]
lw $t0, 12($sp)
sw $t0, 416($sp)
# temp_93 = temp_91+temp_92
lw $t1, 412($sp)
lw $t2, 416($sp)
add $t0, $t1, $t2
sw $t0, 420($sp)
# temp_89 = temp_90>=temp_93
lw $t1, 408($sp)
lw $t2, 420($sp)
lw $s0, 424($sp)
li $s1, 1
beq $s0 $s1 tmpLabel4
# temp_96 = a_1
lw $t0, 0($sp)
sw $t0, 428($sp)
# temp_97 = b_1
lw $t0, 4($sp)
sw $t0, 432($sp)
# temp_95 = temp_96<=temp_97
lw $t1, 428($sp)
lw $t2, 432($sp)
# temp_98 = s1_1[1]
lw $t0, 12($sp)
sw $t0, 440($sp)
# temp_99 = temp_98+1
lw $t1, 440($sp)
addi $t0, $t1, 1
sw $t0, 444($sp)
# temp_94 = temp_95>=temp_99
lw $t1, 436($sp)
lw $t2, 444($sp)
lw $s0, 448($sp)
bnez $s0 tmpLabel4
# temp_103 = a_1
lw $t0, 0($sp)
sw $t0, 452($sp)
# temp_104 = b_1
lw $t0, 4($sp)
sw $t0, 456($sp)
# temp_102 = temp_103>=temp_104
lw $t1, 452($sp)
lw $t2, 456($sp)
# temp_105 = s1_1[0]
lw $t0, 8($sp)
sw $t0, 464($sp)
# temp_101 = temp_102>=temp_105
lw $t1, 460($sp)
lw $t2, 464($sp)
# temp_106 = s1_1[0]
lw $t0, 8($sp)
sw $t0, 472($sp)
# temp_100 = temp_101<=temp_106
lw $t1, 468($sp)
lw $t2, 472($sp)
lw $s0, 476($sp)
li $s1, 1
beq $s0 $s1 tmpLabel4
j if5stmt
tmpLabel4: 
j ELSE5stmt
if5stmt: 
# print_str, >= is ok\n
li $v0, 4
la $a0, str8
syscall 
j if5_END
ELSE5stmt: 
# print_str, >= has problem\n
li $v0, 4
la $a0, str9
syscall 
if5_END: 
if6: 
# temp_107 = a_1
lw $t0, 0($sp)
sw $t0, 480($sp)
# temp_108 = b_1
lw $t0, 4($sp)
sw $t0, 484($sp)
lw $s0, 480($sp)
lw $s1, 484($sp)
beq $s0 $s1 tmpLabel5
# temp_110 = a_1
lw $t0, 0($sp)
sw $t0, 488($sp)
# temp_111 = b_1
lw $t0, 4($sp)
sw $t0, 492($sp)
# temp_109 = temp_110==temp_111
lw $t1, 492($sp)
lw $s0, 496($sp)
bnez $s0 tmpLabel5
# temp_114 = a_1
lw $t0, 0($sp)
sw $t0, 500($sp)
# temp_115 = b_1
lw $t0, 4($sp)
sw $t0, 504($sp)
# temp_113 = temp_114>temp_115
lw $t1, 500($sp)
lw $t2, 504($sp)
# temp_117 = a_1
lw $t0, 0($sp)
sw $t0, 512($sp)
# temp_118 = b_1
lw $t0, 4($sp)
sw $t0, 516($sp)
# temp_116 = temp_117<temp_118
lw $t1, 512($sp)
lw $t2, 516($sp)
# temp_112 = temp_113==temp_116
lw $t1, 520($sp)
lw $s0, 524($sp)
li $s1, 1
beq $s0 $s1 tmpLabel5
# temp_122 = a_1
lw $t0, 0($sp)
sw $t0, 528($sp)
# temp_123 = b_1
lw $t0, 4($sp)
sw $t0, 532($sp)
# temp_121 = temp_122>=temp_123
lw $t1, 528($sp)
lw $t2, 532($sp)
# temp_124 = s1_1[2]
lw $t0, 16($sp)
sw $t0, 540($sp)
# temp_120 = temp_121==temp_124
lw $t1, 540($sp)
# temp_125 = s1_1[0]
lw $t0, 8($sp)
sw $t0, 548($sp)
# temp_119 = temp_120!=temp_125
lw $t1, 544($sp)
lw $t2, 548($sp)
# temp_126 = s1_1[4]
lw $t0, 24($sp)
sw $t0, 556($sp)
lw $s0, 552($sp)
lw $s1, 556($sp)
beq $s0 $s1 tmpLabel5
j if6stmt
tmpLabel5: 
j ELSE6stmt
if6stmt: 
# print_str, == != is ok\n
li $v0, 4
la $a0, str10
syscall 
j if6_END
ELSE6stmt: 
# print_str, == != has problem\n
li $v0, 4
la $a0, str11
syscall 
if6_END: 
if7: 
# temp_127 = s1_1[3]
lw $t0, 20($sp)
sw $t0, 560($sp)
# temp_128 = 3*0
li $t1, 3
li $t2, 0
mult $t1, $t2
mflo $t1
sw $t1, 564($sp)
# temp_129 = temp_128+1
lw $t1, 564($sp)
addi $t0, $t1, 1
sw $t0, 568($sp)
# temp_130 = s2_1[temp_129]
lw $t1, 568($sp)
sll $t1, $t1, 2
addi $t0, $t1, 32
add $t0, $t0, $sp
lw $t0, 0($t0)
sw $t0, 572($sp)
lw $s0, 560($sp)
lw $s1, 572($sp)
beq $s0 $s1 if7stmt
# temp_131 = a_1
lw $t0, 0($sp)
sw $t0, 576($sp)
# temp_132 = b_1
lw $t0, 4($sp)
sw $t0, 580($sp)
lw $s0, 576($sp)
lw $s1, 580($sp)
bge $s0 $s1 if7stmt
# temp_133 = a_1
lw $t0, 0($sp)
sw $t0, 584($sp)
# temp_134 = temp_133+3
lw $t1, 584($sp)
addi $t0, $t1, 3
sw $t0, 588($sp)
# temp_135 = b_1
lw $t0, 4($sp)
sw $t0, 592($sp)
# temp_136 = temp_135*3
lw $t1, 592($sp)
li $t2, 3
mult $t1, $t2
mflo $t1
sw $t1, 596($sp)
lw $s0, 588($sp)
lw $s1, 596($sp)
blt $s0 $s1 if7stmt
j ELSE7stmt
if7stmt: 
# print_str, or is ok\n
li $v0, 4
la $a0, str12
syscall 
j if7_END
ELSE7stmt: 
# print_str, or has problem\n
li $v0, 4
la $a0, str13
syscall 
if7_END: 
if8: 
# temp_137 = a_1
lw $t0, 0($sp)
sw $t0, 600($sp)
# temp_138 = temp_137/2
lw $t1, 600($sp)
li $t2, 2
div $t1, $t2
mflo $t1
sw $t1, 604($sp)
# temp_139 = b_1
lw $t0, 4($sp)
sw $t0, 608($sp)
# temp_140 = temp_139-3
lw $t1, 608($sp)
addi $t0, $t1, -3
sw $t0, 612($sp)
lw $s0, 604($sp)
lw $s1, 612($sp)
blt $s0 $s1 if8stmt
# temp_141 = s1_1[0]
lw $t0, 8($sp)
sw $t0, 616($sp)
# temp_142 = temp_141+1
lw $t1, 616($sp)
addi $t0, $t1, 1
sw $t0, 620($sp)
# temp_143 = s1_1[5]
lw $t0, 28($sp)
sw $t0, 624($sp)
lw $s0, 620($sp)
lw $s1, 624($sp)
ble $s0 $s1 tmpLabel6
# temp_144 = b_1
lw $t0, 4($sp)
sw $t0, 628($sp)
# temp_145 = s1_1[5]
lw $t0, 28($sp)
sw $t0, 632($sp)
lw $s0, 628($sp)
lw $s1, 632($sp)
ble $s0 $s1 tmpLabel6
j if8stmt
tmpLabel6: 
j ELSE8stmt
if8stmt: 
# print_str, the priority of and/or is ok\n
li $v0, 4
la $a0, str14
syscall 
j if8_END
ELSE8stmt: 
# print_str, the priority of and/or has problem\n
li $v0, 4
la $a0, str15
syscall 
if8_END: 
if9: 
# temp_146 = a_1
lw $t0, 0($sp)
sw $t0, 636($sp)
# temp_147 = b_1
lw $t0, 4($sp)
sw $t0, 640($sp)
lw $s0, 636($sp)
lw $s1, 640($sp)
blt $s0 $s1 if9stmt
# temp_148 = a_1
lw $t0, 0($sp)
sw $t0, 644($sp)
# temp_149 = 0+temp_148
lw $t1, 644($sp)
addi $t0, $t1, 0
sw $t0, 648($sp)
lw $s0, 648($sp)
bnez $s0 if9stmt
j if9_END
if9stmt: 
# temp_150 = a_1
lw $t0, 0($sp)
sw $t0, 652($sp)
# temp_151 = temp_150+1
lw $t1, 652($sp)
addi $t0, $t1, 1
sw $t0, 656($sp)
# a_1 = temp_151
lw $t0, 656($sp)
sw $t0, 0($sp)
if10: 
# temp_152 = s1_1[0]
lw $t0, 8($sp)
sw $t0, 660($sp)
# temp_153 = s1_1[1]
lw $t0, 12($sp)
sw $t0, 664($sp)
lw $s0, 660($sp)
lw $s1, 664($sp)
bge $s0 $s1 tmpLabel7
# temp_154 = b_1
lw $t0, 4($sp)
sw $t0, 668($sp)
# temp_155 = 0+temp_154
lw $t1, 668($sp)
addi $t0, $t1, 0
sw $t0, 672($sp)
lw $s0, 672($sp)
beqz $s0 tmpLabel7
j if10stmt
tmpLabel7: 
j if10_END
if10stmt: 
# temp_156 = b_1
lw $t0, 4($sp)
sw $t0, 676($sp)
# temp_157 = temp_156+1
lw $t1, 676($sp)
addi $t0, $t1, 1
sw $t0, 680($sp)
# b_1 = temp_157
lw $t0, 680($sp)
sw $t0, 4($sp)
# print_str, 
li $v0, 4
la $a0, str16
syscall 
# temp_158 = a_1
lw $t0, 0($sp)
sw $t0, 684($sp)
# print_int, temp_158
li $v0, 1
lw $a0, 684($sp)
syscall 
# print_str,  
li $v0, 4
la $a0, str17
syscall 
# temp_159 = b_1
lw $t0, 4($sp)
sw $t0, 688($sp)
# print_int, temp_159
li $v0, 1
lw $a0, 688($sp)
syscall 
# print_str, \n
li $v0, 4
la $a0, str18
syscall 
if10_END: 
if9_END: 
# ret 0
li $v0, 0
addiu $sp, $sp, 692
jr $ra