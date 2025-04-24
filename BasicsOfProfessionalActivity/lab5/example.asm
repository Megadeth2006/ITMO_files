ORG 0x10
START: CLA
S1: IN 5
    AND #0x40
    BEQ S1
    IN 4
    SWAB
    ST RES
S2: IN 5
    AND #0x40
    BEQ S2
    LD RES
    IN 4
    ST RES
HLT
RES: WORD ?