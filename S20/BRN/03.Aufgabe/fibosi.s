;******************** (C) COPYRIGHT HAW-Hamburg ********************************
;* File Name          : fibsi.s
;* Author             : Jannik Stuckstätte
;* Author             : Hani Alshikh	
;* Version            : V1.0
;* Date               : 11.05.2020
;* Description        : die Fibonacci Zahlen iterativ berechen
;
;*******************************************************************************


;********************************************
; Code section, aligned on 8-byte boundery
;********************************************

	AREA |.text|, CODE, READONLY, ALIGN = 3

;--------------------------------------------
; main subroutine
;--------------------------------------------
	EXPORT fibosi [CODE]
	
fibosi	PROC
	
		push {lr}
		
		mov r1, #0 			; fib(0) = 0
        mov r2, #1 			; fib(1) = 1
		

fib     					; n <= 1
		add r3, r1, r2 		; fib(n) = fib(n-1) + fib(n-2)
        mov r1, r2      	; fib(n-2)-von der nächsten = fib(n-1)-von der alten  ; 
        mov r2, r3        	; fib(n-1)-von der nächsten = fib(n)-von der alten
        sub r0, r0, #1   	; n--
        cmp r0, #1
		blt finish
		b fib           	; loop


finish  mov r0, r3        	; n = temp

		pop {lr}
		bx lr
		ENDP
		END