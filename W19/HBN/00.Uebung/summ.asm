; ---------- get input ----------

01 INP 01
; ---------- load input into akku and check it----------

02 LDA 01
03 JEZ 07

; ---------- add to akku ----------

04 ADD 02
05 STA 02

; ---------- jump for input or print result ----------

06 JMP 01
07 OUT 02

; ---------- finish ----------

08 HLT 99