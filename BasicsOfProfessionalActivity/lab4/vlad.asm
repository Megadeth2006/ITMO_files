org 0x0544
addr1: word 0x0200
addrc: word 0x0200
len: word 0x0004
r: word 0xe000

start: cla
	st r
	ld #0x0004
	st len
	add addr1
	st addrc
	st -(addrc)
	bmi next 
	beq next
	clc
	cmc
	next: cla
	not
	and r
	rolorg 0x0544
addr1: word 0x0200
addrc: word 0x0200
len: word 0x0004
r: word 0xe000

start: cla
	st r
	ld #0x0004
	st len
	add addr1
	st addrc
	next1: st -(addrc)
	bmi next 
	beq next
	clc
	cmc
	next: cla
	not
	and r
	rol
	st r
	loop len
	jump next1
	hlt
	a1: word 0x0006
	a2: word 0xfed2
	a3: word 0x004d
	a4: word 0x0000
	
	

	st 
	
