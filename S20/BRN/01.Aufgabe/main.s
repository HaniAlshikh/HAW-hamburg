;*******************************************
; Programm zum Kennenlernen des Entwicklungssystems und der 
; Datentransferbefehle
; Autor: A. Meisel
; Aenderungen : S. Behn  Anpassen an Cortex M3 mit Microvision Aug 2013
;               R. Baran MeinNumFeld global fuer Debugger Oct 2013               
;********************************************
	
	AREA MyData, DATA, align = 2
	GLOBAL MeinNumFeld	

	
MeinNumFeld		DCD       0x22,  2_00111110,  -52,  78,  0x60000000,  0x50000000

MeinWordFeld	DCW       0x1234, 0x5678, 0x9abc, 0xdef0

                
MeinTextFeld	DCB      "ABab0123"

                
MeinByteFeld	DCB       0xef, 0xdc, 0xba, 0x98

MeinBlock       SPACE     0x20

;********************************************
; Code section, aligned on 8-byte boundery
;********************************************

	AREA MyCode, CODE, readonly, align = 2

;--------------------------------------------
; main subroutine
;--------------------------------------------
	GLOBAL main
	
main	PROC
	
	
; ----------------------------------------------------------------------------------------
; Laden von Konstanten in Register
; ----------------------------------------------------------------------------------------
	mov  	R0, #0x12		;Anw-01 : Konstanten 0...255 (*4, *16, *64, ....)

	mov  	R1, #-2			;Anw-02 : negative Konstanten 0...255 (*4, *16, *64, ....)

	ldr		R2,=0xfe543210  ;Anw-03: beliebige 32-bit-Konstanten (Pseudobefehl !!!)


; -----------------------------------------------------------------------------------------
; Zugriff auf Variablen
; -----------------------------------------------------------------------------------------

    ; --- Byte-, Halbword- und Wortzugriffe ---
    ldr     r0, =MeinByteFeld    ; Anw-04: Lade die Startadresse des Bytefeldes
    ldrb    r1, [r0]             ; Anw-05: Achtung: auch bei Byte- und Halbwort-Zugriffen
    ldrh    r2, [r0]             ; Anw-06           wird immer das ganze Register verändert !!!!!
    ldr     r3, [r0]             ; Anw-07

    ; --- einfache Feldzugriffe ---
    ldr     r4, =MeinWordFeld    ; Anw-08: Lade die Adresse des Wortfeldes "MeinWortFeld"
    ldr     r5, [r4]             ; Anw-09
    ldr     r6, [r4, #4]         ; Anw-10: konstanter Offset

    ; --- Variablen (Worte) speichern ---
    ldr     r0, =0x123456ab      ; Anw-11: Lade die Konstante (>255)
    ldr     r1, =MeinBlock       ; Anw-12: Lade die Anfangsadresse von "MeinBlock"
    str     r0, [r1]             ; Anw-13 (word-Befehl) Speicheradresse muss aligned sein !
    str     r0, [r1, #4]         ; Anw-14 (word-Befehl) Speicheradresse muss aligned sein !
    
    ; --- Variablen (Byte) speichern ---    
    mov     r2, #0x1a            ; Anw-15
    strb    r2, [r1, #9]         ; Anw-16 (byte-Befehl) 
    strb    r2, [r1, #10]        ; Anw-17 (byte-Befehl)

; -------------------------------------------------------------------------------------------
; Ganzzahl-Addition und Flags
; -------------------------------------------------------------------------------------------

    ldr     r0,=MeinNumFeld      ; Anw-18
    ldr     r1, [r0]             ; Anw-19
    ldr     r2, [r0, #4]         ; Anw-20
    
    adds    r3, r1, r2           ; Anw-21
    
    ; ---------------------------------------------
    ldr     r0,=MeinNumFeld+8    ; Anw-22
    ldr     r1, [r0]             ; Anw-23
    ldr     r2, [r0, #4]         ; Anw-24
    
    adds    r3, r1, r2           ; Anw-25
    
    ; ---------------------------------------------    
    ldr     r0,=MeinNumFeld+16   ; Anw-26
    ldr     r1, [r0]             ; Anw-27
    ldr     r2, [r0, #4]         ; Anw-28
    
    adds    r3, r1, r2           ; Anw-29            
                        
							
; ------------------------------------------------------------------------------------------
; Indirekte Adressierungsarten mit update
; ------------------------------------------------------------------------------------------

    ldr     r0,=MeinTextFeld     ; Anw-30
    ldrb    r1, [r0, #1]!        ; Anw-31
    ldrb    r1, [r0, #1]!        ; Anw-32

    ldr     r0,=MeinWordFeld     ; Anw-33
    ldr     r2, [r0], #4         ; Anw-34
    ldr     r2, [r0], #4         ; Anw-35



		

		
forever	b		forever			; nowhere to retun if main ends		
	ENDP
	
	ALIGN 4
	END
		