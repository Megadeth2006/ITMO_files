ORG 0x0b6
ADR: WORD 0x5f7

START: CLA

S1: IN 5
    AND #0x40
    BEQ S1

    IN 4
    CMP #0x0D
    BEQ END1
    SWAB
    ST (ADR)

S2: IN 5
    AND #0x40
    BEQ S2

    IN 4
    CMP #0x0D
    BEQ END2
    ADD (ADR)
    ST (ADR)+
    JUMP S1
    
END1:  SWAB
      JUMP SAVE
END2:  ADD (ADR)
SAVE:    ST (ADR)
 		HLT 
ORG 0x5f7
RES: WORD ?

