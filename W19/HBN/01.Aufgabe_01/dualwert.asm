; ---------- define constant ----------


01 LDK 02
02 STA 08
03 LDK 01
04 STA 07

; ---------- first round 0 or 1 input equals sta 02----------


05 INP 01
06 LDA 01
07 STA 02

; ---------- end if less 0 ----------


08 JLZ 21

; ---------- input ----------


09 INP 01
10 LDA 01

; ---------- end if less 0 ----------


11 JLZ 21

; ---------- keep track of the binary value ----------


12 LDA 07
13 MUL 08
14 STA 07

; ---------- skip if 0 ----------


15 LDA 01
16 JEZ 09

; ---------- or calcultae ----------


17 LDA 07
18 ADD 02
19 STA 02

; ---------- again ----------


20 JMP 09

; ---------- output ----------


21 OUT 02


; ---------- end ----------

22 HLT 99
