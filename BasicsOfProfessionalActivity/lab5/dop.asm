org 0x60
num1: word 0x0 ; переменная для хранения первой цифры текущего числа
num2: word 0x0 ; переменная для хранения второй цифры текущего числа
num3: word 0x0 ; переменная для хранения третьей цифры текущего числа

sign: word 0x0 ;знак текущего числа: 0 - плюс, 1 - минус
tmp: word 0x0 ; переменная для хранения временного значения для реализации умножения на 10 (multi - процедуры)

result: word 0x0 ; переменная для хранения результата сложения/вычитания/хранения числа
value: word 0x0 ; переменная для хранения текущего числа (обновляется каждый раз, как вводится новая цифра)

start: ; начало работы
	cla
	jump clear_indicator
; - - - - - - - - - - - - - - - - - - - - - 

clear_indicator: ; очищаем индикатор перед работой калькулятора или после введения оператора
	ld #0x0b
	out 0x14
	ld #0x1b
	out 0x14
	ld #0x2b
	out 0x14
	jump read_symb1 ; прыгаем к чтению данных с цифровой панели - ввод первого символа

read_symb1: ; читает 1 символ введенный в цифровую панель
	in 0x1d
	and #0x40
	beq read_symb1

	in 0x1c
	cmp #0x0a ; если < 0a, то переходим к работе с первым символом
	blt first
	; реализовать установку минуса и как оператор
	cmp #0x0f ;показ результата
	beq show_result

read_symb2: ; читает 2 символ введенный в цифровую панель не дописано
	in 0x1d
	and #0x40
	beq read_symb2
	in 0x1c
	cmp #0x0a
	blt second
	; дописать кейс для другого символа
	cmp #0x0b
	beq sum
	cmp #0x0f
	beq show_result
	

read_symb3: ; читает 3 символ введенный в цифровую панель не дописано
	in 0x1d
	and #0x40
	beq read_symb3
	in 0x1c
	cmp #0x0a
	blt third
	cmp #0x0b
	beq sum
	cmp #0x0f
	beq show_result
	
sum:
	ld $value
	add result
	st result
	ld #0x0
	st $value
	jump clear_indicator
	
first: 
	st num1 ; сохраняем введенную первую цифру в переменную
	out 0x14 ; выводим ее на индикаторе
	st $value
	jump read_symb2 ; прыгаем на ввод второй цифры
	
second:
	st num2
	ld #0x0b
	out 0x14
	ld #0x1b
	out 0x14
	ld num1
	add #0x0010
	out 0x14
	ld num2
	out 0x14
	st $value
	ld num1
	jump multi_num1_10
	cont: add $value
	st $value
	jump read_symb3
	
third:
	st num3
	ld #0x0b
	out 0x14
	ld #0x1b
	out 0x14
	ld #0x2b
	out 0x14
	ld num1
	add #0x20
	out 0x14
	ld num2
	add #0x10
	out 0x14
	ld num3
	out 0x14
	ld num1
	jump multi_num1_100
	
	cont_1: add num3
	st $value
	ld num2
	jump multi_num2_10
	
	cont_2: add $value
	st $value
	
	jump after_third
	
after_third:
	in 0x1d
	and #0x40
	beq after_third

	in 0x1c
	cmp #0x0a
	blt after_third
	cmp #0x0b
	beq sum
	cmp #0x0f
	jump show_result

show_result:
	ld $value
	hlt

multi_num2_10: ;умножение на 10 (10y) - верно
	asl
	asl 
	asl 
	st $tmp
	ld num2
	asl
	add $tmp
	jump cont_2
	
multi_num1_10: ;умножение на 10 (10x) - верно
	asl
	asl
	asl
	st $tmp
	ld $num1
	asl
	add $tmp
	jump cont
	
multi_num1_100: ; умножение на 100 (100x) -  верно
	asl
	asl
	asl
	asl
	asl
	asl
	st $tmp
	ld $num1
	asl
	asl
	asl
	asl
	asl
	add $tmp
	st $tmp
	ld $num1
	asl
	asl
	add $tmp
	jump cont_1	
	

