; ---------- get input and load input ----------

01 INP 01
02 LDA 01

; ---------- check and jump if zero ----------

03 JEZ 11

; ---------- add input to 02 ----------

04 ADD 02
05 STA 02

; ---------- def constant and add 1 for each input ----------

06 LDK 01
07 STA 04
08 ADD 03
09 STA 03


; ---------- jump for input or print result ----------

10 JMP 01

11 LDA 02
12 DIV 03
13 STA 05

14 OUT 05

; ---------- finish ----------

15 HLT 99