.data
# const int _1_1
space0: .space 4
# const int _3_1
space1: .space 4
# const int ______1
space2: .space 4
# const int _2_1
space3: .space 4
# const int _10_1
space4: .space 4
# const int aaa_1[2]
space5: .space 8
# const int A_1[12]
space6: .space 48
# int _3cg_1
space7: .space 4
# int hdc33ec_dyu_1
space8: .space 4
# int cdh3yi_23_1
space9: .space 4
# int duhih2_23c_1
space10: .space 4
# int _23879_1[4]
space11: .space 16
# int dijhw_1[6]
space12: .space 24
# int cdwuh_1[2]
space13: .space 8
# int wuei_1
space14: .space 4
# int _3u22_1[4]
space15: .space 16
# int jdfo__1
space16: .space 4
str0:.asciiz "   !!! ()*+,-./:;<>=@?^_`{}|~[]  called func: ___\n"
str1:.asciiz "//called func: ____, _arg = "
str2:.asciiz "\n"
str3:.asciiz "called func: __, array[0] = "
str4:.asciiz "\n\n\n"
str5:.asciiz "called func: Main, array[0][0] = "
str6:.asciiz ", array[0][1] = "
str7:.asciiz "\n\n"
str8:.asciiz ""
str9:.asciiz "\n"
str10:.asciiz ""
str11:.asciiz "\n"
str12:.asciiz ""
str13:.asciiz "\n"
str14:.asciiz "temp: "
str15:.asciiz "\n"
str16:.asciiz ""
str17:.asciiz " "
str18:.asciiz " "
str19:.asciiz " "
str20:.asciiz " "
str21:.asciiz " "
str22:.asciiz " "
str23:.asciiz " "
str24:.asciiz " "
str25:.asciiz " "
str26:.asciiz "\n"
str27:.asciiz " "
str28:.asciiz " "
str29:.asciiz " "
str30:.asciiz " "
str31:.asciiz " "
str32:.asciiz " "
str33:.asciiz " "
str34:.asciiz " "
str35:.asciiz " "
str36:.asciiz "\n"
str37:.asciiz " "
str38:.asciiz " "
str39:.asciiz " "
str40:.asciiz " "
str41:.asciiz " "
str42:.asciiz " "
str43:.asciiz " "
str44:.asciiz " "
str45:.asciiz " "
str46:.asciiz "\n"
str47:.asciiz " "
str48:.asciiz " "
str49:.asciiz " "
str50:.asciiz " "
str51:.asciiz " "
str52:.asciiz " "
str53:.asciiz " "
str54:.asciiz " "
str55:.asciiz " "
str56:.asciiz "\n"
.text
# _1_1 = 4
li $t0, 4
la $s0, space0
sw $t0, 0($s0)
# _3_1 = 64
li $t0, 64
la $s0, space1
sw $t0, 0($s0)
# ______1 = -49331
li $t0, -49331
la $s0, space2
sw $t0, 0($s0)
# _2_1 = 24
li $t0, 24
la $s0, space3
sw $t0, 0($s0)
# _10_1 = 1040
li $t0, 1040
la $s0, space4
sw $t0, 0($s0)
# aaa_1[0] = 10086
li $t0, 10086
la $t1, space5
sw $t0, 0($t1)
# aaa_1[1] = 16
li $t0, 16
la $t1, space5
sw $t0, 4($t1)
# A_1[0] = 212132
li $t0, 212132
la $t1, space6
sw $t0, 0($t1)
# A_1[1] = 76
li $t0, 76
la $t1, space6
sw $t0, 4($t1)
# A_1[2] = 308
li $t0, 308
la $t1, space6
sw $t0, 8($t1)
# A_1[3] = 26
li $t0, 26
la $t1, space6
sw $t0, 12($t1)
# A_1[4] = 57048
li $t0, 57048
la $t1, space6
sw $t0, 16($t1)
# A_1[5] = 3194880
li $t0, 3194880
la $t1, space6
sw $t0, 20($t1)
# A_1[6] = 8792
li $t0, 8792
la $t1, space6
sw $t0, 24($t1)
# A_1[7] = -687331
li $t0, -687331
la $t1, space6
sw $t0, 28($t1)
# A_1[8] = 908
li $t0, 908
la $t1, space6
sw $t0, 32($t1)
# A_1[9] = 1040
li $t0, 1040
la $t1, space6
sw $t0, 36($t1)
# A_1[10] = 136
li $t0, 136
la $t1, space6
sw $t0, 40($t1)
# A_1[11] = 10104
li $t0, 10104
la $t1, space6
sw $t0, 44($t1)
# _3cg_1 = 4491858
li $t0, 4491858
la $s0, space7
sw $t0, 0($s0)
# hdc33ec_dyu_1 = -597
li $t0, -597
la $s0, space8
sw $t0, 0($s0)
# duhih2_23c_1 = 1983
li $t0, 1983
la $s0, space10
sw $t0, 0($s0)
# _23879_1[0] = 2323
li $t0, 2323
la $t1, space11
sw $t0, 0($t1)
# _23879_1[1] = 9254
li $t0, 9254
la $t1, space11
sw $t0, 4($t1)
# _23879_1[2] = 0
li $t0, 0
la $t1, space11
sw $t0, 8($t1)
# _23879_1[3] = -1269906
li $t0, -1269906
la $t1, space11
sw $t0, 12($t1)
# _3u22_1[0] = 287
li $t0, 287
la $t1, space15
sw $t0, 0($t1)
# _3u22_1[1] = -98662
li $t0, -98662
la $t1, space15
sw $t0, 4($t1)
# _3u22_1[2] = 287
li $t0, 287
la $t1, space15
sw $t0, 8($t1)
# _3u22_1[3] = 11505
li $t0, 11505
la $t1, space15
sw $t0, 12($t1)
jal main
li $v0, 10
syscall 
main: 
addi $sp, $sp, -892
sw $ra, 0($sp)
# int ___2_0_3_7_3_3_8_1___X___J___h______1
# int _for____1
# int _Int_1
# int _2233_1
# int a10086___1
# const int __array_1[8]
# __array_1[0] = 1
li $t0, 1
sw $t0, 24($sp)
# __array_1[1] = 2
li $t0, 2
sw $t0, 28($sp)
# __array_1[2] = 3
li $t0, 3
sw $t0, 32($sp)
# __array_1[3] = 4
li $t0, 4
sw $t0, 36($sp)
# __array_1[4] = 5
li $t0, 5
sw $t0, 40($sp)
# __array_1[5] = 6
li $t0, 6
sw $t0, 44($sp)
# __array_1[6] = 7
li $t0, 7
sw $t0, 48($sp)
# __array_1[7] = 8
li $t0, 8
sw $t0, 52($sp)
# const int aaaaa_1
# aaaaa_1 = 10
li $t0, 10
sw $t0, 56($sp)
# const int bbbbb_1
# bbbbb_1 = 20
li $t0, 20
sw $t0, 60($sp)
# int _0_1
# _0_1 = 10
li $t0, 10
sw $t0, 64($sp)
# int _main_1
# _main_1 = 0
li $t0, 0
sw $t0, 68($sp)
# int Main_1
# Main_1 = 1
li $t0, 1
sw $t0, 72($sp)
# ___2_0_3_7_3_3_8_1___X___J___h______1 = getint()
li $v0, 5
syscall 
sw $v0, 4($sp)
# _for____1 = getint()
li $v0, 5
syscall 
sw $v0, 8($sp)
# _Int_1 = getint()
li $v0, 5
syscall 
sw $v0, 12($sp)
# _2233_1 = getint()
li $v0, 5
syscall 
sw $v0, 16($sp)
# a10086___1 = getint()
li $v0, 5
syscall 
sw $v0, 20($sp)
if3: 
# temp_74 = ___2_0_3_7_3_3_8_1___X___J___h______1
lw $t0, 4($sp)
sw $t0, 76($sp)
lw $s0, 76($sp)
li $s1, 20373381
beq $s0 $s1 if3stmt
j if3_END
if3stmt: 
# temp_75 = Main_1
lw $t0, 72($sp)
sw $t0, 80($sp)
# temp_76 = temp_75+1
lw $t1, 80($sp)
addi $t0, $t1, 1
sw $t0, 84($sp)
# Main_1 = temp_76
lw $t0, 84($sp)
sw $t0, 72($sp)
# temp_77 = _main_1
lw $t0, 68($sp)
sw $t0, 88($sp)
# temp_78 = temp_77+1332
lw $t1, 88($sp)
addi $t0, $t1, 1332
sw $t0, 92($sp)
# _main_1 = temp_78
lw $t0, 92($sp)
sw $t0, 68($sp)
if3_END: 
if4: 
# temp_79 = _for____1
lw $t0, 8($sp)
sw $t0, 96($sp)
lw $s0, 96($sp)
li $s1, 200
bgt $s0 $s1 if4stmt
j if4_END
if4stmt: 
# temp_80 = Main_1
lw $t0, 72($sp)
sw $t0, 100($sp)
# temp_81 = temp_80+1
lw $t1, 100($sp)
addi $t0, $t1, 1
sw $t0, 104($sp)
# Main_1 = temp_81
lw $t0, 104($sp)
sw $t0, 72($sp)
if4_END: 
if5: 
# temp_82 = _Int_1
lw $t0, 12($sp)
sw $t0, 108($sp)
lw $s0, 108($sp)
li $s1, 111
bge $s0 $s1 if5stmt
j if5_END
if5stmt: 
if6: 
# temp_83 = _2233_1
lw $t0, 16($sp)
sw $t0, 112($sp)
lw $s0, 112($sp)
li $s1, 2234
bne $s0 $s1 if6stmt
j ELSE6stmt
if6stmt: 
# temp_84 = Main_1
lw $t0, 72($sp)
sw $t0, 116($sp)
# temp_85 = temp_84+1
lw $t1, 116($sp)
addi $t0, $t1, 1
sw $t0, 120($sp)
# Main_1 = temp_85
lw $t0, 120($sp)
sw $t0, 72($sp)
j if6_END
ELSE6stmt: 
# temp_86 = Main_1
lw $t0, 72($sp)
sw $t0, 124($sp)
# temp_87 = temp_86+2729
lw $t1, 124($sp)
addi $t0, $t1, 2729
sw $t0, 128($sp)
# Main_1 = temp_87
lw $t0, 128($sp)
sw $t0, 72($sp)
if6_END: 
if5_END: 
# int j_1
# j_1 = 0
li $t0, 0
sw $t0, 132($sp)
# int sum_1
# sum_1 = 0
li $t0, 0
sw $t0, 136($sp)
for2: 
li $s0, 1
beqz $s0 for2END
for2STMT: 
# temp_88 = sum_1
lw $t0, 136($sp)
sw $t0, 140($sp)
# temp_89 = j_1
lw $t0, 132($sp)
sw $t0, 144($sp)
# temp_90 = aaa_1[temp_89]
la $s0, space5
lw $t0, 144($sp)
sll $t0, $t0, 2
add $s0, $s0, $t0
lw $s0, 0($s0)
sw $s0, 148($sp)
# temp_91 = temp_88+temp_90
lw $t1, 140($sp)
lw $t2, 148($sp)
add $t0, $t1, $t2
sw $t0, 152($sp)
# sum_1 = temp_91
lw $t0, 152($sp)
sw $t0, 136($sp)
# temp_92 = j_1
lw $t0, 132($sp)
sw $t0, 156($sp)
# temp_93 = temp_92+1
lw $t1, 156($sp)
addi $t0, $t1, 1
sw $t0, 160($sp)
# j_1 = temp_93
lw $t0, 160($sp)
sw $t0, 132($sp)
if7: 
# temp_94 = j_1
lw $t0, 132($sp)
sw $t0, 164($sp)
lw $s0, 164($sp)
li $s1, 2
bge $s0 $s1 if7stmt
j if7_END
if7stmt: 
j for2END
if7_END: 
for2CHANGE_VAL: 
j for2
for2END: 
# int k_1
# k_1 = 0
li $t0, 0
sw $t0, 168($sp)
# int array_1[12]
for3: 
li $s0, 1
li $s1, 2
bgt $s0 $s1 for3END
for3STMT: 
# temp_95 = k_1
lw $t0, 168($sp)
sw $t0, 220($sp)
# array_1[temp_95] = 0
li $t0, 0
sw $t0, 392($sp)
if8: 
# temp_96 = k_1
lw $t0, 168($sp)
sw $t0, 224($sp)
lw $s0, 224($sp)
li $s1, 5
beq $s0 $s1 if8stmt
j if8_END
if8stmt: 
# temp_97 = k_1
lw $t0, 168($sp)
sw $t0, 228($sp)
# temp_98 = temp_97+1
lw $t1, 228($sp)
addi $t0, $t1, 1
sw $t0, 232($sp)
# k_1 = temp_98
lw $t0, 232($sp)
sw $t0, 168($sp)
j for3CHANGE_VAL
if8_END: 
if9: 
# temp_99 = k_1
lw $t0, 168($sp)
sw $t0, 236($sp)
lw $s0, 236($sp)
li $s1, 10
bgt $s0 $s1 if9stmt
j if9_END
if9stmt: 
j for3END
if9_END: 
# temp_100 = k_1
lw $t0, 168($sp)
sw $t0, 240($sp)
# temp_101 = k_1
lw $t0, 168($sp)
sw $t0, 244($sp)
# array_1[temp_100] = temp_101
lw $t0, 244($sp)
sw $t0, 412($sp)
# temp_102 = k_1
lw $t0, 168($sp)
sw $t0, 248($sp)
# temp_103 = temp_102+1
lw $t1, 248($sp)
addi $t0, $t1, 1
sw $t0, 252($sp)
# k_1 = temp_103
lw $t0, 252($sp)
sw $t0, 168($sp)
# temp_104 = sum_1
lw $t0, 136($sp)
sw $t0, 256($sp)
# temp_105 = k_1
lw $t0, 168($sp)
sw $t0, 260($sp)
# temp_106 = k_1
lw $t0, 168($sp)
sw $t0, 264($sp)
# temp_107 = temp_105*temp_106
lw $t1, 260($sp)
lw $t2, 264($sp)
mult $t1, $t2
mflo $t1
sw $t1, 268($sp)
# temp_108 = temp_104+temp_107
lw $t1, 256($sp)
lw $t2, 268($sp)
add $t0, $t1, $t2
sw $t0, 272($sp)
# sum_1 = temp_108
lw $t0, 272($sp)
sw $t0, 136($sp)
# temp_109 = Main_1
lw $t0, 72($sp)
sw $t0, 276($sp)
# temp_110 = temp_109+1
lw $t1, 276($sp)
addi $t0, $t1, 1
sw $t0, 280($sp)
# Main_1 = temp_110
lw $t0, 280($sp)
sw $t0, 72($sp)
for3CHANGE_VAL: 
j for3
for3END: 
# int temp_111
# temp_111 = 0
li $t0, 0
sw $t0, 284($sp)
if10: 
li $s0, 4
li $s1, 5
blt $s0 $s1 if10stmt
li $s0, 10086
li $s1, 10
blt $s0 $s1 if10stmt
j if10_END
if10stmt: 
# temp_112 = temp_111
lw $t0, 284($sp)
sw $t0, 288($sp)
# temp_113 = temp_112+1
lw $t1, 288($sp)
addi $t0, $t1, 1
sw $t0, 292($sp)
# temp_111 = temp_113
lw $t0, 292($sp)
sw $t0, 284($sp)
if10_END: 
if11: 
li $s0, 24
bgez $s0 tmpLabel0
li $s0, 10086
li $s1, 10
ble $s0 $s1 tmpLabel0
j if11stmt
tmpLabel0: 
j if11_END
if11stmt: 
# temp_114 = temp_111
lw $t0, 284($sp)
sw $t0, 296($sp)
# temp_115 = temp_114+2
lw $t1, 296($sp)
addi $t0, $t1, 2
sw $t0, 300($sp)
# temp_111 = temp_115
lw $t0, 300($sp)
sw $t0, 284($sp)
if11_END: 
# temp_116 = 2*0
li $t1, 2
li $t2, 0
mult $t1, $t2
mflo $t1
sw $t1, 304($sp)
# temp_117 = temp_116+0
lw $t1, 304($sp)
addi $t0, $t1, 0
sw $t0, 308($sp)
# dijhw_1[temp_117] = getint()
li $v0, 5
syscall 
la $s0, space12
lw $t0, 308($sp)
sll $t0, $t0, 2
add $s0, $s0, $t0
sw $v0, 0($s0)
# temp_118 = 2*1
li $t1, 2
li $t2, 1
mult $t1, $t2
mflo $t1
sw $t1, 312($sp)
# temp_119 = temp_118+0
lw $t1, 312($sp)
addi $t0, $t1, 0
sw $t0, 316($sp)
# dijhw_1[temp_119] = getint()
li $v0, 5
syscall 
la $s0, space12
lw $t0, 316($sp)
sll $t0, $t0, 2
add $s0, $s0, $t0
sw $v0, 0($s0)
# temp_120 = 2*2
li $t1, 2
li $t2, 2
mult $t1, $t2
mflo $t1
sw $t1, 320($sp)
# temp_121 = temp_120+0
lw $t1, 320($sp)
addi $t0, $t1, 0
sw $t0, 324($sp)
# dijhw_1[temp_121] = getint()
li $v0, 5
syscall 
la $s0, space12
lw $t0, 324($sp)
sll $t0, $t0, 2
add $s0, $s0, $t0
sw $v0, 0($s0)
# cdwuh_1[0] = getint()
li $v0, 5
syscall 
la $s0, space13
sw $v0, 0($s0)
# cdwuh_1[1] = getint()
li $v0, 5
syscall 
la $s0, space13
sw $v0, 4($s0)
# wuei_1 = getint()
li $v0, 5
syscall 
la $t0, space14
sw $v0, 0($t0)
# temp_122 = wuei_1
la $s0, space14
lw $s0, 0($s0)
sw $s0, 328($sp)
# temp_123 = cdwuh_1[0]
la $s0, space13
lw $s0, 0($s0)
sw $s0, 332($sp)
# temp_124 = cdwuh_1[1]
la $s0, space13
lw $s0, 4($s0)
sw $s0, 336($sp)
# temp_125 = temp_123*temp_124
lw $t1, 332($sp)
lw $t2, 336($sp)
mult $t1, $t2
mflo $t1
sw $t1, 340($sp)
# temp_126 = temp_122+temp_125
lw $t1, 328($sp)
lw $t2, 340($sp)
add $t0, $t1, $t2
sw $t0, 344($sp)
# temp_127 = 2*0
li $t1, 2
li $t2, 0
mult $t1, $t2
mflo $t1
sw $t1, 348($sp)
# temp_128 = temp_127+1
lw $t1, 348($sp)
addi $t0, $t1, 1
sw $t0, 352($sp)
# temp_129 = dijhw_1[temp_128]
la $s0, space12
lw $t0, 352($sp)
sll $t0, $t0, 2
add $s0, $s0, $t0
lw $s0, 0($s0)
sw $s0, 356($sp)
# temp_130 = temp_129/2
lw $t1, 356($sp)
li $t2, 2
div $t1, $t2
mflo $t1
sw $t1, 360($sp)
# temp_131 = temp_126+temp_130
lw $t1, 344($sp)
lw $t2, 360($sp)
add $t0, $t1, $t2
sw $t0, 364($sp)
# wuei_1 = temp_131
lw $t0, 364($sp)
la $s0, space14
sw $t0, 0($s0)
# temp_132 = 2*2
li $t1, 2
li $t2, 2
mult $t1, $t2
mflo $t1
sw $t1, 368($sp)
# temp_133 = temp_132+1
lw $t1, 368($sp)
addi $t0, $t1, 1
sw $t0, 372($sp)
# temp_134 = 2*2
li $t1, 2
li $t2, 2
mult $t1, $t2
mflo $t1
sw $t1, 376($sp)
# temp_135 = temp_134+0
lw $t1, 376($sp)
addi $t0, $t1, 0
sw $t0, 380($sp)
# temp_136 = dijhw_1[temp_135]
la $s0, space12
lw $t0, 380($sp)
sll $t0, $t0, 2
add $s0, $s0, $t0
lw $s0, 0($s0)
sw $s0, 384($sp)
# temp_137 = 2*1
li $t1, 2
li $t2, 1
mult $t1, $t2
mflo $t1
sw $t1, 388($sp)
# temp_138 = temp_137+0
lw $t1, 388($sp)
addi $t0, $t1, 0
sw $t0, 392($sp)
# temp_139 = dijhw_1[temp_138]
la $s0, space12
lw $t0, 392($sp)
sll $t0, $t0, 2
add $s0, $s0, $t0
lw $s0, 0($s0)
sw $s0, 396($sp)
# temp_140 = temp_136*temp_139
lw $t1, 384($sp)
lw $t2, 396($sp)
mult $t1, $t2
mflo $t1
sw $t1, 400($sp)
# dijhw_1[temp_133] = temp_140
lw $t0, 400($sp)
la $t1, space12
lw $t2, 372($sp)
sll $t2, $t2, 2
add $t1, $t1, $t2
sw $t0, 0($t1)
# temp_141 = sum_1
lw $t0, 136($sp)
sw $t0, 404($sp)
# temp_142 = sum_1
lw $t0, 136($sp)
sw $t0, 408($sp)
# temp_143 = temp_141*temp_142
lw $t1, 404($sp)
lw $t2, 408($sp)
mult $t1, $t2
mflo $t1
sw $t1, 412($sp)
# temp_144 = 10
li $t0, 10
sw $t0, 416($sp)
# temp_145 = 20
li $t0, 20
sw $t0, 420($sp)
# temp_146 = 3
li $t0, 3
sw $t0, 424($sp)
# push temp_144
lw $a0, 416($sp)
# push temp_145
lw $a1, 420($sp)
# push temp_146
lw $a2, 424($sp)
# call _add
jal _add
# temp_147 = _add
move $t0, $v0
sw $t0, 428($sp)
# temp_148 = temp_143+temp_147
lw $t1, 412($sp)
lw $t2, 428($sp)
add $t0, $t1, $t2
sw $t0, 432($sp)
# jdfo__1 = temp_148
lw $t0, 432($sp)
la $s0, space16
sw $t0, 0($s0)
# temp_149 = sum_1
lw $t0, 136($sp)
sw $t0, 436($sp)
# call _get_id
jal _get_id
# temp_150 = _get_id
move $t0, $v0
sw $t0, 440($sp)
# temp_151 = temp_149+temp_150
lw $t1, 436($sp)
lw $t2, 440($sp)
add $t0, $t1, $t2
sw $t0, 444($sp)
# temp_153 = ___2_0_3_7_3_3_8_1___X___J___h______1
lw $t0, 4($sp)
sw $t0, 448($sp)
# temp_152 = 123243
li $t0, 123243
sw $t0, 452($sp)
# push temp_152
lw $a0, 452($sp)
# push temp_153
lw $a1, 448($sp)
# call _add_fake
jal _add_fake
# temp_154 = _add_fake
move $t0, $v0
sw $t0, 456($sp)
# temp_155 = temp_151+temp_154
lw $t1, 444($sp)
lw $t2, 456($sp)
add $t0, $t1, $t2
sw $t0, 460($sp)
# sum_1 = temp_155
lw $t0, 460($sp)
sw $t0, 136($sp)
# call ___
jal ___
# temp_156 = ___
move $t0, $v0
sw $t0, 464($sp)
# temp_157 = _3u22_1
la $s0, space15
sw $s0, 468($sp)
# push temp_157
lw $a0, 468($sp)
# call _Main
jal _Main
# temp_158 = _Main
move $t0, $v0
sw $t0, 472($sp)
# temp_159 = _23879_1
la $s0, space11
sw $s0, 476($sp)
# temp_160 = 3432
li $t0, 3432
sw $t0, 480($sp)
# push temp_159
lw $a0, 476($sp)
# push temp_160
lw $a1, 480($sp)
# call __
jal __
# temp_161 = __
move $t0, $v0
sw $t0, 484($sp)
# print_str, temp: 
li $v0, 4
la $a0, str14
syscall 
# temp_162 = temp_111
lw $t0, 284($sp)
sw $t0, 488($sp)
# print_int, temp_162
li $v0, 1
lw $a0, 488($sp)
syscall 
# print_str, \n
li $v0, 4
la $a0, str15
syscall 
# print_str, 
li $v0, 4
la $a0, str16
syscall 
# temp_163 = array_1
addi $t0, $sp, 172
sw $t0, 492($sp)
# temp_164 = 12
li $t0, 12
sw $t0, 496($sp)
# push temp_163
lw $a0, 492($sp)
# push temp_164
lw $a1, 496($sp)
# call __my_Ha_s__h
jal __my_Ha_s__h
# temp_165 = __my_Ha_s__h
move $t0, $v0
sw $t0, 500($sp)
# print_int, temp_165
li $v0, 1
lw $a0, 500($sp)
syscall 
# print_str,  
li $v0, 4
la $a0, str17
syscall 
# temp_166 = sum_1
lw $t0, 136($sp)
sw $t0, 504($sp)
# print_int, temp_166
li $v0, 1
lw $a0, 504($sp)
syscall 
# print_str,  
li $v0, 4
la $a0, str18
syscall 
# print_int, 1
li $v0, 1
li $a0, 1
syscall 
# print_str,  
li $v0, 4
la $a0, str19
syscall 
# print_int, 64
li $v0, 1
li $a0, 64
syscall 
# print_str,  
li $v0, 4
la $a0, str20
syscall 
# print_int, 24
li $v0, 1
li $a0, 24
syscall 
# print_str,  
li $v0, 4
la $a0, str21
syscall 
# print_int, 1040
li $v0, 1
li $a0, 1040
syscall 
# print_str,  
li $v0, 4
la $a0, str22
syscall 
# print_int, 10086
li $v0, 1
li $a0, 10086
syscall 
# print_str,  
li $v0, 4
la $a0, str23
syscall 
# print_int, 16
li $v0, 1
li $a0, 16
syscall 
# print_str,  
li $v0, 4
la $a0, str24
syscall 
# temp_167 = 3*0
li $t1, 3
li $t2, 0
mult $t1, $t2
mflo $t1
sw $t1, 508($sp)
# temp_168 = temp_167+0
lw $t1, 508($sp)
addi $t0, $t1, 0
sw $t0, 512($sp)
# temp_169 = A_1[temp_168]
la $s0, space6
lw $t0, 512($sp)
sll $t0, $t0, 2
add $s0, $s0, $t0
lw $s0, 0($s0)
sw $s0, 516($sp)
# print_int, temp_169
li $v0, 1
lw $a0, 516($sp)
syscall 
# print_str,  
li $v0, 4
la $a0, str25
syscall 
# temp_170 = 3*0
li $t1, 3
li $t2, 0
mult $t1, $t2
mflo $t1
sw $t1, 520($sp)
# temp_171 = temp_170+1
lw $t1, 520($sp)
addi $t0, $t1, 1
sw $t0, 524($sp)
# temp_172 = A_1[temp_171]
la $s0, space6
lw $t0, 524($sp)
sll $t0, $t0, 2
add $s0, $s0, $t0
lw $s0, 0($s0)
sw $s0, 528($sp)
# print_int, temp_172
li $v0, 1
lw $a0, 528($sp)
syscall 
# print_str, \n
li $v0, 4
la $a0, str26
syscall 
# temp_173 = 3*0
li $t1, 3
li $t2, 0
mult $t1, $t2
mflo $t1
sw $t1, 532($sp)
# temp_174 = temp_173+2
lw $t1, 532($sp)
addi $t0, $t1, 2
sw $t0, 536($sp)
# temp_175 = A_1[temp_174]
la $s0, space6
lw $t0, 536($sp)
sll $t0, $t0, 2
add $s0, $s0, $t0
lw $s0, 0($s0)
sw $s0, 540($sp)
# print_int, temp_175
li $v0, 1
lw $a0, 540($sp)
syscall 
# print_str,  
li $v0, 4
la $a0, str27
syscall 
# temp_176 = 3*1
li $t1, 3
li $t2, 1
mult $t1, $t2
mflo $t1
sw $t1, 544($sp)
# temp_177 = temp_176+0
lw $t1, 544($sp)
addi $t0, $t1, 0
sw $t0, 548($sp)
# temp_178 = A_1[temp_177]
la $s0, space6
lw $t0, 548($sp)
sll $t0, $t0, 2
add $s0, $s0, $t0
lw $s0, 0($s0)
sw $s0, 552($sp)
# print_int, temp_178
li $v0, 1
lw $a0, 552($sp)
syscall 
# print_str,  
li $v0, 4
la $a0, str28
syscall 
# temp_179 = 3*1
li $t1, 3
li $t2, 1
mult $t1, $t2
mflo $t1
sw $t1, 556($sp)
# temp_180 = temp_179+1
lw $t1, 556($sp)
addi $t0, $t1, 1
sw $t0, 560($sp)
# temp_181 = A_1[temp_180]
la $s0, space6
lw $t0, 560($sp)
sll $t0, $t0, 2
add $s0, $s0, $t0
lw $s0, 0($s0)
sw $s0, 564($sp)
# print_int, temp_181
li $v0, 1
lw $a0, 564($sp)
syscall 
# print_str,  
li $v0, 4
la $a0, str29
syscall 
# temp_182 = 3*1
li $t1, 3
li $t2, 1
mult $t1, $t2
mflo $t1
sw $t1, 568($sp)
# temp_183 = temp_182+2
lw $t1, 568($sp)
addi $t0, $t1, 2
sw $t0, 572($sp)
# temp_184 = A_1[temp_183]
la $s0, space6
lw $t0, 572($sp)
sll $t0, $t0, 2
add $s0, $s0, $t0
lw $s0, 0($s0)
sw $s0, 576($sp)
# print_int, temp_184
li $v0, 1
lw $a0, 576($sp)
syscall 
# print_str,  
li $v0, 4
la $a0, str30
syscall 
# temp_185 = 3*2
li $t1, 3
li $t2, 2
mult $t1, $t2
mflo $t1
sw $t1, 580($sp)
# temp_186 = temp_185+0
lw $t1, 580($sp)
addi $t0, $t1, 0
sw $t0, 584($sp)
# temp_187 = A_1[temp_186]
la $s0, space6
lw $t0, 584($sp)
sll $t0, $t0, 2
add $s0, $s0, $t0
lw $s0, 0($s0)
sw $s0, 588($sp)
# print_int, temp_187
li $v0, 1
lw $a0, 588($sp)
syscall 
# print_str,  
li $v0, 4
la $a0, str31
syscall 
# temp_188 = 3*2
li $t1, 3
li $t2, 2
mult $t1, $t2
mflo $t1
sw $t1, 592($sp)
# temp_189 = temp_188+1
lw $t1, 592($sp)
addi $t0, $t1, 1
sw $t0, 596($sp)
# temp_190 = A_1[temp_189]
la $s0, space6
lw $t0, 596($sp)
sll $t0, $t0, 2
add $s0, $s0, $t0
lw $s0, 0($s0)
sw $s0, 600($sp)
# print_int, temp_190
li $v0, 1
lw $a0, 600($sp)
syscall 
# print_str,  
li $v0, 4
la $a0, str32
syscall 
# temp_191 = 3*2
li $t1, 3
li $t2, 2
mult $t1, $t2
mflo $t1
sw $t1, 604($sp)
# temp_192 = temp_191+2
lw $t1, 604($sp)
addi $t0, $t1, 2
sw $t0, 608($sp)
# temp_193 = A_1[temp_192]
la $s0, space6
lw $t0, 608($sp)
sll $t0, $t0, 2
add $s0, $s0, $t0
lw $s0, 0($s0)
sw $s0, 612($sp)
# print_int, temp_193
li $v0, 1
lw $a0, 612($sp)
syscall 
# print_str,  
li $v0, 4
la $a0, str33
syscall 
# temp_194 = 3*3
li $t1, 3
li $t2, 3
mult $t1, $t2
mflo $t1
sw $t1, 616($sp)
# temp_195 = temp_194+0
lw $t1, 616($sp)
addi $t0, $t1, 0
sw $t0, 620($sp)
# temp_196 = A_1[temp_195]
la $s0, space6
lw $t0, 620($sp)
sll $t0, $t0, 2
add $s0, $s0, $t0
lw $s0, 0($s0)
sw $s0, 624($sp)
# print_int, temp_196
li $v0, 1
lw $a0, 624($sp)
syscall 
# print_str,  
li $v0, 4
la $a0, str34
syscall 
# temp_197 = 3*3
li $t1, 3
li $t2, 3
mult $t1, $t2
mflo $t1
sw $t1, 628($sp)
# temp_198 = temp_197+1
lw $t1, 628($sp)
addi $t0, $t1, 1
sw $t0, 632($sp)
# temp_199 = A_1[temp_198]
la $s0, space6
lw $t0, 632($sp)
sll $t0, $t0, 2
add $s0, $s0, $t0
lw $s0, 0($s0)
sw $s0, 636($sp)
# print_int, temp_199
li $v0, 1
lw $a0, 636($sp)
syscall 
# print_str,  
li $v0, 4
la $a0, str35
syscall 
# temp_200 = 3*3
li $t1, 3
li $t2, 3
mult $t1, $t2
mflo $t1
sw $t1, 640($sp)
# temp_201 = temp_200+2
lw $t1, 640($sp)
addi $t0, $t1, 2
sw $t0, 644($sp)
# temp_202 = A_1[temp_201]
la $s0, space6
lw $t0, 644($sp)
sll $t0, $t0, 2
add $s0, $s0, $t0
lw $s0, 0($s0)
sw $s0, 648($sp)
# print_int, temp_202
li $v0, 1
lw $a0, 648($sp)
syscall 
# print_str, \n
li $v0, 4
la $a0, str36
syscall 
# temp_203 = _3cg_1
la $s0, space7
lw $s0, 0($s0)
sw $s0, 652($sp)
# print_int, temp_203
li $v0, 1
lw $a0, 652($sp)
syscall 
# print_str,  
li $v0, 4
la $a0, str37
syscall 
# temp_204 = hdc33ec_dyu_1
la $s0, space8
lw $s0, 0($s0)
sw $s0, 656($sp)
# print_int, temp_204
li $v0, 1
lw $a0, 656($sp)
syscall 
# print_str,  
li $v0, 4
la $a0, str38
syscall 
# temp_205 = cdh3yi_23_1
la $s0, space9
lw $s0, 0($s0)
sw $s0, 660($sp)
# print_int, temp_205
li $v0, 1
lw $a0, 660($sp)
syscall 
# print_str,  
li $v0, 4
la $a0, str39
syscall 
# temp_206 = duhih2_23c_1
la $s0, space10
lw $s0, 0($s0)
sw $s0, 664($sp)
# print_int, temp_206
li $v0, 1
lw $a0, 664($sp)
syscall 
# print_str,  
li $v0, 4
la $a0, str40
syscall 
# temp_207 = 2*0
li $t1, 2
li $t2, 0
mult $t1, $t2
mflo $t1
sw $t1, 668($sp)
# temp_208 = temp_207+0
lw $t1, 668($sp)
addi $t0, $t1, 0
sw $t0, 672($sp)
# temp_209 = dijhw_1[temp_208]
la $s0, space12
lw $t0, 672($sp)
sll $t0, $t0, 2
add $s0, $s0, $t0
lw $s0, 0($s0)
sw $s0, 676($sp)
# print_int, temp_209
li $v0, 1
lw $a0, 676($sp)
syscall 
# print_str,  
li $v0, 4
la $a0, str41
syscall 
# temp_210 = 2*0
li $t1, 2
li $t2, 0
mult $t1, $t2
mflo $t1
sw $t1, 680($sp)
# temp_211 = temp_210+1
lw $t1, 680($sp)
addi $t0, $t1, 1
sw $t0, 684($sp)
# temp_212 = dijhw_1[temp_211]
la $s0, space12
lw $t0, 684($sp)
sll $t0, $t0, 2
add $s0, $s0, $t0
lw $s0, 0($s0)
sw $s0, 688($sp)
# print_int, temp_212
li $v0, 1
lw $a0, 688($sp)
syscall 
# print_str,  
li $v0, 4
la $a0, str42
syscall 
# temp_213 = 2*1
li $t1, 2
li $t2, 1
mult $t1, $t2
mflo $t1
sw $t1, 692($sp)
# temp_214 = temp_213+0
lw $t1, 692($sp)
addi $t0, $t1, 0
sw $t0, 696($sp)
# temp_215 = dijhw_1[temp_214]
la $s0, space12
lw $t0, 696($sp)
sll $t0, $t0, 2
add $s0, $s0, $t0
lw $s0, 0($s0)
sw $s0, 700($sp)
# print_int, temp_215
li $v0, 1
lw $a0, 700($sp)
syscall 
# print_str,  
li $v0, 4
la $a0, str43
syscall 
# temp_216 = 2*1
li $t1, 2
li $t2, 1
mult $t1, $t2
mflo $t1
sw $t1, 704($sp)
# temp_217 = temp_216+1
lw $t1, 704($sp)
addi $t0, $t1, 1
sw $t0, 708($sp)
# temp_218 = dijhw_1[temp_217]
la $s0, space12
lw $t0, 708($sp)
sll $t0, $t0, 2
add $s0, $s0, $t0
lw $s0, 0($s0)
sw $s0, 712($sp)
# print_int, temp_218
li $v0, 1
lw $a0, 712($sp)
syscall 
# print_str,  
li $v0, 4
la $a0, str44
syscall 
# temp_219 = 2*2
li $t1, 2
li $t2, 2
mult $t1, $t2
mflo $t1
sw $t1, 716($sp)
# temp_220 = temp_219+0
lw $t1, 716($sp)
addi $t0, $t1, 0
sw $t0, 720($sp)
# temp_221 = dijhw_1[temp_220]
la $s0, space12
lw $t0, 720($sp)
sll $t0, $t0, 2
add $s0, $s0, $t0
lw $s0, 0($s0)
sw $s0, 724($sp)
# print_int, temp_221
li $v0, 1
lw $a0, 724($sp)
syscall 
# print_str,  
li $v0, 4
la $a0, str45
syscall 
# temp_222 = 2*2
li $t1, 2
li $t2, 2
mult $t1, $t2
mflo $t1
sw $t1, 728($sp)
# temp_223 = temp_222+1
lw $t1, 728($sp)
addi $t0, $t1, 1
sw $t0, 732($sp)
# temp_224 = dijhw_1[temp_223]
la $s0, space12
lw $t0, 732($sp)
sll $t0, $t0, 2
add $s0, $s0, $t0
lw $s0, 0($s0)
sw $s0, 736($sp)
# print_int, temp_224
li $v0, 1
lw $a0, 736($sp)
syscall 
# print_str, \n
li $v0, 4
la $a0, str46
syscall 
# temp_225 = cdwuh_1[0]
la $s0, space13
lw $s0, 0($s0)
sw $s0, 740($sp)
# print_int, temp_225
li $v0, 1
lw $a0, 740($sp)
syscall 
# print_str,  
li $v0, 4
la $a0, str47
syscall 
# temp_226 = cdwuh_1[1]
la $s0, space13
lw $s0, 4($s0)
sw $s0, 744($sp)
# print_int, temp_226
li $v0, 1
lw $a0, 744($sp)
syscall 
# print_str,  
li $v0, 4
la $a0, str48
syscall 
# temp_227 = wuei_1
la $s0, space14
lw $s0, 0($s0)
sw $s0, 748($sp)
# print_int, temp_227
li $v0, 1
lw $a0, 748($sp)
syscall 
# print_str,  
li $v0, 4
la $a0, str49
syscall 
# temp_228 = jdfo__1
la $s0, space16
lw $s0, 0($s0)
sw $s0, 752($sp)
# print_int, temp_228
li $v0, 1
lw $a0, 752($sp)
syscall 
# print_str,  
li $v0, 4
la $a0, str50
syscall 
# temp_229 = cdwuh_1
la $s0, space13
sw $s0, 756($sp)
# temp_230 = 2
li $t0, 2
sw $t0, 760($sp)
# push temp_229
lw $a0, 756($sp)
# push temp_230
lw $a1, 760($sp)
# call __my_Ha_s__h
jal __my_Ha_s__h
# temp_231 = __my_Ha_s__h
move $t0, $v0
sw $t0, 764($sp)
# push temp_231
lw $a0, 764($sp)
# call _M__a_G_I___c
jal _M__a_G_I___c
# temp_232 = _M__a_G_I___c
move $t0, $v0
sw $t0, 768($sp)
# print_int, temp_232
li $v0, 1
lw $a0, 768($sp)
syscall 
# print_str,  
li $v0, 4
la $a0, str51
syscall 
# temp_233 = 2*0
li $t1, 2
li $t2, 0
mult $t1, $t2
mflo $t1
sw $t1, 772($sp)
# temp_234 = temp_233*4
lw $t1, 772($sp)
li $t2, 4
mult $t1, $t2
mflo $t1
sw $t1, 776($sp)
# temp_235 = &_3u22_1+temp_234
la $t1, space15
lw $t2, 776($sp)
add $t0, $t1, $t2
sw $t0, 780($sp)
# temp_236 = temp_235
lw $t0, 780($sp)
sw $t0, 784($sp)
# temp_237 = 2
li $t0, 2
sw $t0, 788($sp)
# push temp_236
lw $a0, 784($sp)
# push temp_237
lw $a1, 788($sp)
# call __my_Ha_s__h
jal __my_Ha_s__h
# temp_238 = __my_Ha_s__h
move $t0, $v0
sw $t0, 792($sp)
# print_int, temp_238
li $v0, 1
lw $a0, 792($sp)
syscall 
# print_str,  
li $v0, 4
la $a0, str52
syscall 
# temp_239 = _main_1
lw $t0, 68($sp)
sw $t0, 796($sp)
# print_int, temp_239
li $v0, 1
lw $a0, 796($sp)
syscall 
# print_str,  
li $v0, 4
la $a0, str53
syscall 
# temp_240 = _0_1
lw $t0, 64($sp)
sw $t0, 800($sp)
# print_int, temp_240
li $v0, 1
lw $a0, 800($sp)
syscall 
# print_str,  
li $v0, 4
la $a0, str54
syscall 
# temp_241 = a10086___1
lw $t0, 20($sp)
sw $t0, 804($sp)
# print_int, temp_241
li $v0, 1
lw $a0, 804($sp)
syscall 
# print_str,  
li $v0, 4
la $a0, str55
syscall 
# temp_242 = 4*0
li $t1, 4
li $t2, 0
mult $t1, $t2
mflo $t1
sw $t1, 808($sp)
# temp_243 = temp_242+2
lw $t1, 808($sp)
addi $t0, $t1, 2
sw $t0, 812($sp)
# temp_244 = __array_1[temp_243]
lw $t1, 812($sp)
sll $t1, $t1, 2
addi $t0, $t1, 24
add $t0, $t0, $sp
lw $t0, 0($t0)
sw $t0, 816($sp)
# print_int, temp_244
li $v0, 1
lw $a0, 816($sp)
syscall 
# print_str, \n
li $v0, 4
la $a0, str56
syscall 
# temp_245 = Main_1
lw $t0, 72($sp)
sw $t0, 820($sp)
# push temp_245
lw $a0, 820($sp)
# call ____
jal ____
# temp_246 = ____
move $t0, $v0
sw $t0, 824($sp)
# ret 0
li $v0, 0
lw $ra, 0($sp)
addiu $sp, $sp, 892
jr $ra
__my_Ha_s__h: 
addi $sp, $sp, -116
sw $a0, 0($sp)
sw $a1, 4($sp)
# int ___1
# int I_1
# temp_1 = len_1
lw $t0, 4($sp)
sw $t0, 16($sp)
# ___1 = temp_1
lw $t0, 16($sp)
sw $t0, 8($sp)
# I_1 = 0
li $t0, 0
sw $t0, 12($sp)
for1: 
# temp_2 = I_1
lw $t0, 12($sp)
sw $t0, 20($sp)
# temp_3 = len_1
lw $t0, 4($sp)
sw $t0, 24($sp)
lw $s0, 20($sp)
lw $s1, 24($sp)
bge $s0 $s1 for1END
for1STMT: 
# temp_4 = ___1
lw $t0, 8($sp)
sw $t0, 28($sp)
# temp_5 = temp_4+98326464
lw $t1, 28($sp)
addi $t0, $t1, 98326464
sw $t0, 32($sp)
# ___1 = temp_5
lw $t0, 32($sp)
sw $t0, 8($sp)
# temp_6 = ___1
lw $t0, 8($sp)
sw $t0, 36($sp)
# temp_7 = temp_6*20373381
lw $t1, 36($sp)
li $t2, 20373381
mult $t1, $t2
mflo $t1
sw $t1, 40($sp)
# ___1 = temp_7
lw $t0, 40($sp)
sw $t0, 8($sp)
# temp_8 = ___1
lw $t0, 8($sp)
sw $t0, 44($sp)
# temp_9 = temp_8+23667832
lw $t1, 44($sp)
addi $t0, $t1, 23667832
sw $t0, 48($sp)
# ___1 = temp_9
lw $t0, 48($sp)
sw $t0, 8($sp)
# temp_10 = ___1
lw $t0, 8($sp)
sw $t0, 52($sp)
# temp_11 = temp_10/133
lw $t1, 52($sp)
li $t2, 133
div $t1, $t2
mflo $t1
sw $t1, 56($sp)
# ___1 = temp_11
lw $t0, 56($sp)
sw $t0, 8($sp)
# temp_12 = ___1
lw $t0, 8($sp)
sw $t0, 60($sp)
# temp_13 = temp_12+1347887899
lw $t1, 60($sp)
addi $t0, $t1, 1347887899
sw $t0, 64($sp)
# ___1 = temp_13
lw $t0, 64($sp)
sw $t0, 8($sp)
# temp_14 = ___1
lw $t0, 8($sp)
sw $t0, 68($sp)
# temp_15 = temp_14%99998099
lw $t1, 68($sp)
li $t2, 99998099
div $t1, $t2
mfhi $t1
sw $t1, 72($sp)
# ___1 = temp_15
lw $t0, 72($sp)
sw $t0, 8($sp)
# temp_16 = ___1
lw $t0, 8($sp)
sw $t0, 76($sp)
# temp_17 = temp_16*123671861
lw $t1, 76($sp)
li $t2, 123671861
mult $t1, $t2
mflo $t1
sw $t1, 80($sp)
# temp_18 = temp_17-236234
lw $t1, 80($sp)
addi $t0, $t1, -236234
sw $t0, 84($sp)
# temp_19 = ___1
lw $t0, 8($sp)
sw $t0, 88($sp)
# temp_20 = temp_19+135632
lw $t1, 88($sp)
addi $t0, $t1, 135632
sw $t0, 92($sp)
# temp_21 = temp_18*temp_20
lw $t1, 84($sp)
lw $t2, 92($sp)
mult $t1, $t2
mflo $t1
sw $t1, 96($sp)
# temp_22 = temp_21/7
lw $t1, 96($sp)
li $t2, 7
div $t1, $t2
mflo $t1
sw $t1, 100($sp)
# ___1 = temp_22
lw $t0, 100($sp)
sw $t0, 8($sp)
# temp_23 = I_1
lw $t0, 12($sp)
sw $t0, 104($sp)
# temp_24 = temp_23+1
lw $t1, 104($sp)
addi $t0, $t1, 1
sw $t0, 108($sp)
# I_1 = temp_24
lw $t0, 108($sp)
sw $t0, 12($sp)
for1CHANGE_VAL: 
j for1
for1END: 
# temp_25 = ___1
lw $t0, 8($sp)
sw $t0, 112($sp)
# ret temp_25
lw $v0, 112($sp)
addiu $sp, $sp, 116
jr $ra
___: 
# print_str,    !!! ()*+,-./:;<>=@?^_`{}|~[]  called func: ___\n
li $v0, 4
la $a0, str0
syscall 
# ret 
addiu $sp, $sp, 0
jr $ra
____: 
addi $sp, $sp, -20
sw $a0, 0($sp)
# print_str, //called func: ____, _arg = 
li $v0, 4
la $a0, str1
syscall 
# temp_26 = _arg_1
lw $t0, 0($sp)
sw $t0, 4($sp)
# temp_27 = temp_26*2
lw $t1, 4($sp)
li $t2, 2
mult $t1, $t2
mflo $t1
sw $t1, 8($sp)
# temp_28 = temp_27*3
lw $t1, 8($sp)
li $t2, 3
mult $t1, $t2
mflo $t1
sw $t1, 12($sp)
# temp_29 = temp_28/6
lw $t1, 12($sp)
li $t2, 6
div $t1, $t2
mflo $t1
sw $t1, 16($sp)
# print_int, temp_29
li $v0, 1
lw $a0, 16($sp)
syscall 
# print_str, \n
li $v0, 4
la $a0, str2
syscall 
# ret 
addiu $sp, $sp, 20
jr $ra
__: 
addi $sp, $sp, -12
sw $a0, 0($sp)
sw $a1, 4($sp)
# print_str, called func: __, array[0] = 
li $v0, 4
la $a0, str3
syscall 
# temp_30 = _arr_Ay_1[0]
lw $t0, 0($sp)
addi $t0, $t0, 0
lw $t0, 0($t0)
sw $t0, 8($sp)
# print_int, temp_30
li $v0, 1
lw $a0, 8($sp)
syscall 
# print_str, \n\n\n
li $v0, 4
la $a0, str4
syscall 
# ret 
addiu $sp, $sp, 12
jr $ra
_Main: 
addi $sp, $sp, -28
sw $a0, 0($sp)
# print_str, called func: Main, array[0][0] = 
li $v0, 4
la $a0, str5
syscall 
# temp_31 = 2*0
li $t1, 2
li $t2, 0
mult $t1, $t2
mflo $t1
sw $t1, 4($sp)
# temp_32 = temp_31+0
lw $t1, 4($sp)
addi $t0, $t1, 0
sw $t0, 8($sp)
# temp_33 = _arr_Ay_2[temp_32]
lw $t0, 0($sp)
lw $t1, 8($sp)
sll $t1, $t1, 2
add $t0, $t0, $t1
lw $t0, 0($t0)
sw $t0, 12($sp)
# print_int, temp_33
li $v0, 1
lw $a0, 12($sp)
syscall 
# print_str, , array[0][1] = 
li $v0, 4
la $a0, str6
syscall 
# temp_34 = 2*0
li $t1, 2
li $t2, 0
mult $t1, $t2
mflo $t1
sw $t1, 16($sp)
# temp_35 = temp_34+1
lw $t1, 16($sp)
addi $t0, $t1, 1
sw $t0, 20($sp)
# temp_36 = _arr_Ay_2[temp_35]
lw $t0, 0($sp)
lw $t1, 20($sp)
sll $t1, $t1, 2
add $t0, $t0, $t1
lw $t0, 0($t0)
sw $t0, 24($sp)
# print_int, temp_36
li $v0, 1
lw $a0, 24($sp)
syscall 
# print_str, \n\n
li $v0, 4
la $a0, str7
syscall 
# ret 
addiu $sp, $sp, 28
jr $ra
_add_fake: 
addi $sp, $sp, -100
sw $a0, 0($sp)
sw $a1, 4($sp)
if1: 
# temp_37 = a_1
lw $t0, 0($sp)
sw $t0, 8($sp)
# temp_38 = b_1
lw $t0, 4($sp)
sw $t0, 12($sp)
# temp_39 = temp_37+temp_38
lw $t1, 8($sp)
lw $t2, 12($sp)
add $t0, $t1, $t2
sw $t0, 16($sp)
lw $s0, 16($sp)
li $s1, 10186
bge $s0 $s1 if1stmt
j ELSE1stmt
if1stmt: 
# temp_40 = a_1
lw $t0, 0($sp)
sw $t0, 20($sp)
# temp_41 = b_1
lw $t0, 4($sp)
sw $t0, 24($sp)
# temp_42 = 203*temp_41
li $t1, 203
lw $t2, 24($sp)
mult $t1, $t2
mflo $t1
sw $t1, 28($sp)
# temp_43 = temp_42/201
lw $t1, 28($sp)
li $t2, 201
div $t1, $t2
mflo $t1
sw $t1, 32($sp)
# temp_44 = temp_40+temp_43
lw $t1, 20($sp)
lw $t2, 32($sp)
add $t0, $t1, $t2
sw $t0, 36($sp)
# ret temp_44
lw $v0, 36($sp)
addiu $sp, $sp, 100
jr $ra
j if1_END
ELSE1stmt: 
if2: 
# temp_45 = a_1
lw $t0, 0($sp)
sw $t0, 40($sp)
# temp_46 = b_1
lw $t0, 4($sp)
sw $t0, 44($sp)
# temp_47 = temp_45+temp_46
lw $t1, 40($sp)
lw $t2, 44($sp)
add $t0, $t1, $t2
sw $t0, 48($sp)
# temp_48 = temp_47-10086
lw $t1, 48($sp)
addi $t0, $t1, -10086
sw $t0, 52($sp)
lw $s0, 52($sp)
li $s1, 12345678
blt $s0 $s1 if2stmt
j ELSE2stmt
if2stmt: 
# temp_49 = a_1
lw $t0, 0($sp)
sw $t0, 56($sp)
# temp_50 = 1003*temp_49
li $t1, 1003
lw $t2, 56($sp)
mult $t1, $t2
mflo $t1
sw $t1, 60($sp)
# temp_51 = temp_50/1009
lw $t1, 60($sp)
li $t2, 1009
div $t1, $t2
mflo $t1
sw $t1, 64($sp)
# temp_52 = b_1
lw $t0, 4($sp)
sw $t0, 68($sp)
# temp_53 = 2022*temp_52
li $t1, 2022
lw $t2, 68($sp)
mult $t1, $t2
mflo $t1
sw $t1, 72($sp)
# temp_54 = temp_53/2019
lw $t1, 72($sp)
li $t2, 2019
div $t1, $t2
mflo $t1
sw $t1, 76($sp)
# temp_55 = temp_51+temp_54
lw $t1, 64($sp)
lw $t2, 76($sp)
add $t0, $t1, $t2
sw $t0, 80($sp)
# ret temp_55
lw $v0, 80($sp)
addiu $sp, $sp, 100
jr $ra
j if2_END
ELSE2stmt: 
# temp_56 = a_1
lw $t0, 0($sp)
sw $t0, 84($sp)
# temp_57 = b_1
lw $t0, 4($sp)
sw $t0, 88($sp)
# temp_58 = temp_56+temp_57
lw $t1, 84($sp)
lw $t2, 88($sp)
add $t0, $t1, $t2
sw $t0, 92($sp)
# temp_59 = temp_58-233
lw $t1, 92($sp)
addi $t0, $t1, -233
sw $t0, 96($sp)
# ret temp_59
lw $v0, 96($sp)
addiu $sp, $sp, 100
jr $ra
if2_END: 
if1_END: 
# ret -10086
li $v0, -10086
addiu $sp, $sp, 100
jr $ra
_add: 
addi $sp, $sp, -32
sw $a0, 0($sp)
sw $a1, 4($sp)
sw $a2, 8($sp)
# temp_60 = a_2
lw $t0, 0($sp)
sw $t0, 12($sp)
# temp_61 = b_2
lw $t0, 4($sp)
sw $t0, 16($sp)
# temp_62 = temp_60+temp_61
lw $t1, 12($sp)
lw $t2, 16($sp)
add $t0, $t1, $t2
sw $t0, 20($sp)
# temp_63 = c_1
lw $t0, 8($sp)
sw $t0, 24($sp)
# temp_64 = temp_62+temp_63
lw $t1, 20($sp)
lw $t2, 24($sp)
add $t0, $t1, $t2
sw $t0, 28($sp)
# ret temp_64
lw $v0, 28($sp)
addiu $sp, $sp, 32
jr $ra
_get_id: 
addi $sp, $sp, -20
# int _2_wj5qhidw__1
# _2_wj5qhidw__1 = 76
li $t0, 76
sw $t0, 0($sp)
# print_str, 
li $v0, 4
la $a0, str8
syscall 
# temp_65 = _2_wj5qhidw__1
lw $t0, 0($sp)
sw $t0, 4($sp)
# print_int, temp_65
li $v0, 1
lw $a0, 4($sp)
syscall 
# print_str, \n
li $v0, 4
la $a0, str9
syscall 
# _2_wj5qhidw__1 = 10086
li $t0, 10086
sw $t0, 0($sp)
# print_str, 
li $v0, 4
la $a0, str10
syscall 
# temp_66 = _2_wj5qhidw__1
lw $t0, 0($sp)
sw $t0, 8($sp)
# print_int, temp_66
li $v0, 1
lw $a0, 8($sp)
syscall 
# print_str, \n
li $v0, 4
la $a0, str11
syscall 
# int _2_wj5qhidw__2
# _2_wj5qhidw__2 = -10
li $t0, -10
sw $t0, 12($sp)
# print_str, 
li $v0, 4
la $a0, str12
syscall 
# temp_67 = _2_wj5qhidw__2
lw $t0, 12($sp)
sw $t0, 16($sp)
# print_int, temp_67
li $v0, 1
lw $a0, 16($sp)
syscall 
# print_str, \n
li $v0, 4
la $a0, str13
syscall 
# ret 20373381
li $v0, 20373381
addiu $sp, $sp, 20
jr $ra
_M__a_G_I___c: 
addi $sp, $sp, -28
sw $a0, 0($sp)
# temp_68 = num_1
lw $t0, 0($sp)
sw $t0, 4($sp)
# temp_69 = temp_68+-763835168
lw $t1, 4($sp)
addi $t0, $t1, -763835168
sw $t0, 8($sp)
# temp_70 = temp_69%1996325548
lw $t1, 8($sp)
li $t2, 1996325548
div $t1, $t2
mfhi $t1
sw $t1, 12($sp)
# temp_71 = temp_70*1881457024
lw $t1, 12($sp)
li $t2, 1881457024
mult $t1, $t2
mflo $t1
sw $t1, 16($sp)
# temp_72 = temp_71/11
lw $t1, 16($sp)
li $t2, 11
div $t1, $t2
mflo $t1
sw $t1, 20($sp)
# temp_73 = temp_72*540488577
lw $t1, 20($sp)
li $t2, 540488577
mult $t1, $t2
mflo $t1
sw $t1, 24($sp)
# ret temp_73
lw $v0, 24($sp)
addiu $sp, $sp, 28
jr $ra