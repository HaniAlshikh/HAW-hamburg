;******************** (C) COPYRIGHT HAW-Hamburg ********************************
;* File Name          : main.s
;* Author             : Jannik Stückstätte
;* Author             : Hani Alshikh
;* Version            : V1.0
;* Date               : 20.04.2020
;* Description        : das Program zählt die Primzahlen
;					  : zwischen 20 und 30 sowie zwischen 50 und 80
;*******************************************************************************
	
		AREA MyData, DATA, align = 2

; Speicher für 80 Byte resavieren.
prims 		SPACE 80

;********************************************
; Code section, aligned on 8-byte boundery
;********************************************

	AREA |.text|, CODE, READONLY, ALIGN = 3

;--------------------------------------------
; main subroutine
;--------------------------------------------
	EXPORT main [CODE]
	
main 	PROC
		LDR r2,=prims
		; 1 ist Prim 0 ist nicht.
		mov r1,#1
		mov r0,#0
		; 0 und 1 sind keine Primzahlen
		strb r0,[r2]
		strb r0,[r2,#1]
		
		; Alle Zahlen die ziwschen den gesuchten
		; rang werden mit 1 initialisiert
		; dann wird bei Zahlen, 
		; die keine Primzahlen sind mit 0 getaucht
		; Forschleife zur Initialisierung Primzahlenarray
		mov r3,#2
lini	cmp r3,#80
		beq liniend
		cmp r3,#20
		bcc skip    ; if kleiner 20
		cmp r3,#30
		bcc pingo   ; Initialisiere if kleiner 30
		cmp r3,#50
		bcc skip    ; if kleiner 50
pingo	strb r1,[r2,r3]
skip	add r3,r3,#1 ;i++
		b lini
liniend ;ende Forschleife zur Initialisierung
		;aeussere Forschleife zur Besetzung Primzahlenarray
		mov r3,#2
lallez  cmp r3,#9
		bpl lallezend
		;Forschleife zum Loeschen von Vielfachen
		add r5, r3,r3
lallep  cmp r5,#80
		bpl lallepend
		strb r0,[r2,r5]
		add r5,r5,r3 ;j=j+i
		b lallep
lallepend ;ende Forschleife zu Loeschen

endifp ;ende if untersuchte Zahl == Primzahl
		add r3,r3,#1 ;i++
		b lallez
lallezend ; ende aeussere Forschleife zur Besetzung Primzahlenarray
		
forever	b	forever		; nowhere to retun if main ends		
		ENDP
	
		ALIGN
       
		END