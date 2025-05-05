org 0x60
num1: word 0x0 ; переменная для хранения первой цифры текущего числа
num2: word 0x0 ; переменная для хранения второй цифры текущего числа
num3: word 0x0 ; переменная для хранения третьей цифры текущего числа
num0: word 0x0 ; переменная для хранения нулевой цифры (если число четырехзначное)

sign: word 0x0 ;знак текущего числа: 0 - плюс, 1 - минус
tmp: word 0x0 ; переменная для хранения временного значения для реализации умножения на 10 (multi - процедуры)

result: word 0x0 ; переменная для хранения результата сложения/вычитания/хранения числа
value: word 0x0 ; переменная для хранения текущего числа (обновляется каждый раз, как вводится новая цифра)

n1000: word 0xfc18
p1000: word 0x3e8

cnt1000: word 0x0
cnt100: word 0x0
cnt10: word 0x0
start: ; начало работы
	cla
	jump clear_indicator
; - - - - - - - - - - - - - - - - - - - - - 

clear_indicator: ; очищаем индикатор перед работой калькулятора или после введения оператора
	ld #0x4b
	out 0x14
	ld #0x3b
	out 0x14
	ld #0x2b
	out 0x14
	ld #0x1b
	out 0x14
	ld #0x0b
	out 0x14
	
	jump read_first ; прыгаем к чтению данных с цифровой панели - ввод первого символа

read_first: ; читаю прям самый первый введенный символ - важно для установки знака самого первого числа
	in 0x1d
	and #0x40
	beq read_first

	in 0x1c
	cmp #0x0a ; если < 0a, то переходим к работе с первым символом
	blt first
	
	;проверки
	; - - - - - - - --  -- -  - - - - - - -- - - - -
	cmp #0x0f 
	beq show_result_support ; покажет 0 при любом раскладе
	cmp #0x0e
	beq read_first
	cmp #0x0d
	beq read_first
	cmp #0x0c
	beq read_first
	cmp #0x0b
	beq read_first
	cmp #0x0a
	beq set_minus
	; - - - - - - - - - - -- - -- - - - -- 

set_minus: ;ставим минус перед числом
	ld #0x1
	st $sign ; установили в переменную знак значение 1 (минус)
	
	ld #0x4a
	out 0x14
	jump $read_symb1

show_result_support:
	jump $show_result

read_symb1: ; читает 1 символ введенный в цифровую панель
	in 0x1d
	and #0x40
	beq read_symb1

	in 0x1c
	cmp #0x0a ; если < 0a, то переходим к работе с первым символом
	blt first
	cmp #0x0f ;показ результата
	beq show_result_support

	cmp #0x0e
	beq read_symb1
	cmp #0x0d
	beq read_symb1
	cmp #0x0c
	beq read_symb1
	cmp #0x0b
	beq read_symb1
	cmp #0x0a
	beq set_minus ; надо проверить работу
	
second_support:
	jump $second
	
substraction: ; вычитание
	ld #0x1
	st $sign
	ld $value
	add $result
	st $result
	ld #0x0
	st $value
	jump $clear_indicator	
sum_support:
	jump $sum
read_symb2: ; читает 2 символ введенный в цифровую панель не дописано
	in 0x1d
	and #0x40
	beq read_symb2
	in 0x1c
	cmp #0x0a
	blt second_support
	
	cmp #0x0b
	beq sum_support
	cmp #0x0f
	beq show_result_support

	cmp #0x0e ; проверить надо
	beq read_symb2
	cmp #0x0d
	beq read_symb2
	cmp #0x0c
	beq read_symb2
	
	cmp #0x0a ; вычитание
	beq substraction 
	
third_support:
	jump $third
read_symb3: ; читает 3 символ введенный в цифровую панель не дописано
	in 0x1d
	and #0x40
	beq read_symb3
	in 0x1c
	cmp #0x0a
	blt third_support
	cmp #0x0b
	beq sum
	cmp #0x0f
	beq show_result_support

	cmp #0x0e
	beq read_symb3
	cmp #0x0d
	beq read_symb3
	cmp #0x0c
	beq read_symb3
	
	cmp #0x0a ; вычитание
	beq substraction 

negative1:
	ld $num1
	neg
	
	st $value
	
	ld #0x4a
	out 0x14
	
	ld $num1
	out 0x14
	jump after_negative1
	
first: 
	st $num1 ; сохраняем введенную первую цифру в переменную
	ld $sign ; делаем проверку на знак
	cmp #0x1
	beq negative1
	
	ld $num1
	st $value
	out 0x14 ; выводим ее на индикаторе (положительная)
	
	after_negative1:
	ld $value
	jump read_symb2 ; прыгаем на ввод второй цифры


second_minus:
	ld $value
	neg

	st $value
	ld #0x4a
	out 0x14
	jump cont_second_minus

	


negative_result:
	neg
	st $result
	ld #0x1
	st $sign
	jump cont_negative_result
loop1000_support:
	jump $loop1000
show_negative:
	ld #0x4a
	out 0x14
	ld $num0
	cmp #0x1
	blt nlow_1000
	add #0x30
	out 0x14
	nlow_1000:
	ld $num1
	cmp #0x1
	blt nlow_100
	add #0x20
	out 0x14
	nlow_100:
	ld $num2
	cmp #0x1
	blt nlow_10
	add #0x10
	out 0x14
	nlow_10:
	ld $num3
	out 0x14
	hlt ; конец работы программы
loop100_support:
	jump $loop100
loop10_support:
	jump $loop10
show_result: ;показываем результат
	ld #0x4b
	ld #0x0
	st $num0
	st $num1
	st $num2
	st $num3
	
	ld $value
	add $result
	st $result
	
	ld #0x0
	st $sign
	
	
	ld #0x0b
	out 0x14
	ld #0x1b
	out 0x14
	ld #0x2b
	out 0x14
	ld $result
	cmp #0x0
	blt negative_result
	cont_negative_result:
	ld $result
	st $tmp
	cmp $p1000
	bge loop1000_support
	blt else1000 ; если число меньше 1000
	endloop1000:
		ld $cnt1000
		st $num0
		
	else1000:
	ld $tmp
	cmp #0x64
	bge loop100_support
	blt else100 ; если число меньше 100
	endloop100: 
		ld $cnt100
		st $num1
		
	
	else100: 
		ld $tmp
		cmp #0xA
		bge loop10_support
		blt else10 ; если число меньше 10
		
	
	endloop10:
		ld $cnt10
		st $num2
	else10:
		ld $tmp
		and #0xf
		st $num3

	ld $sign
	cmp #0x1
	beq show_negative

	ld $num0
	cmp #0x1
	blt lower_1000
	add #0x30
	out 0x14
	lower_1000:
	ld $num1
	cmp #0x1
	blt lower_100
	add #0x20
	out 0x14
	lower_100:
	ld $num2
	cmp #0x1
	blt lower_10
	add #0x10
	out 0x14
	lower_10:
	ld $num3
	out 0x14
	hlt ; конец работы программы

sum:
	ld #0x0
	st $sign
	ld $value
	add $result
	st $result
	ld #0x0
	st $value
	jump $clear_indicator	
second:
	st $num2
	ld #0x4b
	out 0x14
	ld #0x3b
	out 0x14
	ld #0x1b ;очищаем панель
	out 0x14
	ld #0x0b
	out 0x14
	
	ld $num1
	jump multi_num1_10
	
	
	cont: ; переход после умножения на 10 (num1)
		ld $tmp
		add $num2
		st $value
		
	ld $sign
	cmp #0x1
	beq second_minus ; переход, если число отрицательное
	
	cont_second_minus:
	ld $num1
	add #0x0010
	out 0x14
	ld $num2
	out 0x14
	
	jump $read_symb3

third:
	st $num3
	ld #0x4b
	out 0x14
	ld #0x3b
	out 0x14
	ld #0x2b
	out 0x14
	ld #0x1b
	out 0x14
	ld #0x0b
	out 0x14
	
	
	ld $num1
	jump multi_num1_100

	
	cont_1:
		add $num3
		st $value
		ld $num2
		jump multi_num2_10
	
	cont_2: 
		add $value
		st $value
		
	ld $sign
	cmp #0x1
	beq third_minus
	
	cont_third_minus:
	ld $num1
	add #0x20
	out 0x14
	ld $num2
	add #0x10
	out 0x14
	ld $num3
	out 0x14
	
	jump after_third
substraction_support:
	jump $substraction
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
	beq show_result_support1

	cmp #0x0e
	beq after_third
	cmp #0x0d
	beq after_third
	cmp #0x0c
	beq after_third
	
	cmp #0x0a ; вычитание
	beq substraction_support
	
show_result_support1:
	jump $show_result	
third_minus:
	ld $value
	neg
	st $value
	ld #0x4a
	out 0x14
	jump cont_third_minus


; - - - - - - - - - - - - - - - -  - - - - -  Вспомогательные процедуры
endloop1000_support:
	jump $endloop1000
loop1000: ; подсчет количества тысяч
	ld $tmp
	cmp $p1000
	blt endloop1000_support
	add $n1000
	st $tmp
	ld $cnt1000
	add #0x0001
	st $cnt1000

	beq endloop1000_support
	jump $loop1000
endloop10_support:
	jump $endloop10
loop10: ; подсчет количества десяток
	ld $tmp
	cmp #0x000A
	blt endloop10_support
	add #0xf6
	st $tmp
	ld $cnt10
	add #0x0001
	st $cnt10
	beq endloop10_support
	jump loop10
	
endloop100_support:
	jump $endloop100	
	
loop100: ; подсчет количества сотен
	ld $tmp
	cmp #0x64
	blt endloop100_support
	add #0x9c
	st $tmp
	ld $cnt100
	add #0x0001
	st $cnt100
	beq endloop100_support
	jump $loop100


multi_num2_10: ;умножение на 10 (10y) - верно
	asl
	asl 
	asl 
	st $tmp
	ld $num2
	asl
	add $tmp
	st $tmp
	jump cont_2
	
multi_num1_10: ;умножение на 10 (10x) - верно
	asl
	asl
	asl
	st $tmp
	ld $num1
	asl
	add $tmp
	st $tmp
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
	st $tmp
	jump cont_1	

