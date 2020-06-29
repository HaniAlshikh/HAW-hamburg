/**
  ******************************************************************************
  * @file    	main.c 
  * @author  	Jannik Stuckstätte
	*						Hani Alshikh
  * @version V1.0
  * @date    11.05.2020
  * @brief   die Fibonacci Zahlen berechen
  ******************************************************************************
  */


/* Includes ------------------------------------------------------------------*/
#include <stdio.h>
#include "TI_Lib.h"
#include "tft.h"


/* Deklaration ---------------------------------------------------------------*/
int fibocr(int n);
int fiboci(int n);
int fibosr(int n);
int fibosi(int n);


/* main ----------------------------------------------------------------------*/
int main () { 
  
	char ostr[100];
	Init_TI_Board();
	
	
	// TODO: Füllen Sie nun das Ergebnis Array in einer doppelten For Schleife.
	int (*fp[4])(int)={fiboci,fibocr,fibosi,fibosr};
	int arg[4]={0,10,22,29};
	int resu[4][4];
	
	 for (int i = 0; i < 4; i++) {
	 	for (int j = 0; j < 4; j++) {
			resu[i][j] = fp[i](arg[j]);
		}
	}
	
	int cr = fibocr(2);
	int ci = fiboci(2);
	int si = fibosi(2);
	int sr = fibosr(2);
	sprintf(ostr, " cr = %d \r\n ci = %d \r\n si = %d \r\n sr = %d", cr,ci,si,sr);
	
	TFT_cls();
	TFT_puts(ostr);
	
	while(1);
}


/* Definition ----------------------------------------------------------------------*/

// Ein C Unterprogramm das die Zahlen rekursiv berechnet
int fibocr(int n) { 
   if (n <= 1) 
      return 1; 
   return fibocr(n-1) + fibocr(n-2); 
}


// Ein C Unterprogramm das die Zahlen iterativ berechnet
int fiboci(int n) { 

  int f0 = 0; 																	// fib(0) = 0
  int f1 = 1; 
	int temp = n;
	
	if (n <= 1) {
		temp = 1;
	}

  for (int i = 1; i <= n; i++) {
		temp = f0 + f1; 														// fib(n) = fib(n-1) + fib(n-2)
		f0 = f1;																		// fib(n-2)-von der nächsten = fib(n-1)-von der alten  ; 
    f1 = temp;      														// fib(n-1)-von der nächsten = fib(n)-von der alten
  } 
  
  return temp; 
}