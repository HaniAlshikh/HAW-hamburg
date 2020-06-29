;******************** (C) COPYRIGHT HAW-Hamburg ********************************
;* File Name          : fibsr.s
;* Author             : Jannik Stuckstätte
;* Author             : Hani Alshikh	
;* Version            : V1.0
;* Date               : 11.05.2020
;* Description        : die Fibonacci Zahlen rekursiv berechen
;
;*******************************************************************************


;********************************************
; Code section, aligned on 8-byte boundery
;********************************************

	AREA |.text|, CODE, READONLY, ALIGN = 3

;--------------------------------------------
; main subroutine
;--------------------------------------------
	EXPORT fibosr [CODE]
	
fibosr	PROC						; int fibosr(int n) {
	
		push {r4-r5,lr}				;   int i, tmp;     
		MOV R4, R0                  ;   i = n;
		CMP R0, #1					
		mov R0, #1					;   if (n <= 1)
		BLE finish                  ;     return n;
		SUB R0, R4, #2              ;   n = i-2;
		BL fibosr                   ;   n = fib(n);
		MOV R5, R0                  ;   tmp = n;
		SUB R0, R4, #1              ;   n = i-1;
		BL fibosr                   ;   n = fib(n);
		ADD R0, R0, R5              ;   n = n + tmp;

finish	pop {r4-r5,lr}				;   return n;
		bx lr						;  }
		ENDP

		END