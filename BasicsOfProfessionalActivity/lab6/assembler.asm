ORG 0x0
V0: WORD $default, 0X180
V1: WORD $int1, 0X180
V2: WORD $default, 0x180
V3: WORD $int3, 0x180
V4: WORD $default, 0X180
V5: WORD $default, 0X180
V6: WORD $default, 0X180
V7: WORD $default, 0X180

default: iret ; обработка прерывания по умолчанию

ORG 0x2e
x: word 5
max: word 0x015
min: word 0xffeb
tmp: word 0x0
org 0x40
start:  di
        cla
        OUT 0x1 ; запрет прерываний
        OUT 0x5
        OUT 0xB
        OUT 0xD
        OUT 0x11
        OUT 0x15
        OUT 0x19
        OUT 0x1D ; последний запрет прерываний

        LD #0x9 ; Загрузка в аккумулятор MR (1000|0001=1001)
        OUT 0x3 ; Разрешение прерываний для 1 ВУ
        LD #0xB ; Загрузка в аккумулятор MR (1000|0011=1011)
        OUT 0x7 ; Разрешение прерываний для 3 ВУ
        ei

	   jump main
	   
main: di ; запрет прерываний
    ld $x
    sub #2 ; вычитание 2 из x
    call check
    st $x ; сохранение результата в x
    ei
   	
    jump main

check: 
	cmp $min
	bpl check_max
	bmi ld_max
	check_max:
	cmp $max
	beq return
	bmi return
	bpl ld_max
	ld_max: ld $max
	return: 
	ret
	
	
	
int1: ; обработка прерывания на ву-1
    ld $x
    nop
    asl
    asl
    add x
    add x
    dec 
    out 0x2
    nop
    iret
    
int3: ; обработка прерывания на ву-3

    in 0x6
    nop
    st $tmp
    add $tmp
    add $tmp ;умножение на 3
    neg
    add x
    call check ; проверка на переполнение
    st x
    nop
    iret

