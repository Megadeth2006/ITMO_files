org 0x60

cnt100: word 0x0
num: word 0x03e7


start:
	cla
	jump $loop100


loop100:

	ld #0x9C
	add $num
	st $num

	blt end100
	beq end100

	
	
	ld $cnt100
	add #0x0001
	st $cnt100
	
	jump $loop100

end100:
	ld $cnt100
	hlt