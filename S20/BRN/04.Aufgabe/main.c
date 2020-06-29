/**
  ******************************************************************************
  * @file    	main.c 
  * @author  	Jannik Stuckstätte
	*	@author		Hani Alshikh
  *        	  HAW-Hamburg
  *          	Labor für Angewandte Informatik
  *          	Berliner Tor  7
  *          	D-20099 Hamburg
  * @version  V1.0
  * @date     06.06.2020
  * @brief    Aufgabe 04
  ******************************************************************************
  */


/* Includes ------------------------------------------------------------------*/
#include <stdio.h>
#include "TI_Lib.h"
#include "tft.h"

//--- For Timer -----------------------------
#include "timer.h"

//	1.2 counter, next, current state
int counter = 0, next = 0, cur = 0, oldVelocityCounter = 0, velocityCounterDelta, velocityLamps, velocityLampsSum, velocityLampsToTurnOff, powerOfTwo;
// Used for calculation result of impulses per msec
double impulsePerMsec = 0.0;
// action methodes depending on the rotary state
void cuntup(void);void cuntdn(void); void err(void); void noact(void);
//	1.2. Counter Reset button & LED Output
void resetCounter(void); void updateLED(void);

// Used for TFT output
char ostr[100];
// 1.2. Automate
struct EvReact {int NextState;void (*DoIt)(void);};
struct EvReact ERTab[4][4]= {
	// cur = 0, next ?= 0 - 3
	{{0,noact},{1,cuntdn} ,{2,cuntup} ,{3,err}},
	// cur = 1, next ?= 0 - 3
	{{0,cuntup},{1,noact} ,{2,err} ,{3,cuntdn}},
	// cur = 2, next ?= 0 - 3
	{{0,cuntdn},{1,err} ,{2,noact} ,{3,cuntup}},
	// cur = 3, next ?= 0 - 3
	{{0,err},{1,cuntup} ,{2,cuntdn} ,{3,noact}} 
};

int main(void) {

	// reset button
	int reset;
	int forever=1;

	// Additional tasks prep (need hardware to test??)
	timerinit();
	
	// Counter state auto reset time
	TIM2->ARR = 8400000*2; // 200ms
	
	// while loop runs counter every sec
	int runs = 0;
	// TIM2->ARR = 84000000; // 1sec
	
	
	TIM2->SR &= ~0x1; // reset
	// TIM3->SR &= ~0x1; // reset TIM3
	
  Init_TI_Board();
  TFT_cls();
    
	while(forever) {
		// get next state 
		// (0x00 - 0xFF)&0x3 will always give result between 0 - 3

		next = GPIOE->IDR;
		reset = next & 0x40;
		if (reset == 0){
			resetCounter();
		}
		else {
			next=next&0x3;
			ERTab[cur][next].DoIt();
			cur = next;
		}
		
		// counting runs of main loop
		runs++;
		// print counter every 200ms		
		if ((TIM2->SR & 0x1) == 1) {
			static int oldCounter = 0;
			static int loopRuns = 1;
			if (oldCounter != counter) {
				sprintf(ostr, "Counter: %d\n\r", counter);
				TFT_puts(ostr);
			}
			oldCounter = counter;
			// count loop to react after 5 runs (1sec)
			loopRuns++;
			if (loopRuns == 5) {
				sprintf(ostr, "Runs: %d\n\r", runs);
				TFT_puts(ostr);
				runs = 0;
				loopRuns = 0;
			}
			// TIM2->SR &= ~0x1;
		}
			
		
		

//		// count loop runs every sec		
//		runs++;
//		if ((TIM2->SR&0x1) == 1) {
//			sprintf(ostr, "Runs: %d\n\r", runs);
//			TFT_puts(ostr);
//			runs = 0;
//			// TIM2->SR &= ~0x1;
//		}


		//    measure velocity
		//		check every 200 ms
		if ((TIM2->SR&0x1) == 1) {
			// if (oldVelocityCounter != counter) {
				// Calculate counter delta 
				velocityCounterDelta = counter - oldVelocityCounter;
				// Calculate impulses per msec
				if (velocityCounterDelta < 0) {
					velocityCounterDelta = velocityCounterDelta * -1;
				}
				impulsePerMsec = (float) velocityCounterDelta / 200.0;
				// Calculating how many lamps to turn on
				velocityLamps = impulsePerMsec / 0.4;
				velocityLampsSum = 0;
				
				// Calculating what to write to GPIOG->BSRRL in order to turn the calculated lamps on
				powerOfTwo = 1;
				for (int i = 0; i < velocityLamps && i < 8; i++) {
					velocityLampsSum = velocityLampsSum + powerOfTwo;
					powerOfTwo = powerOfTwo * 2;
				}
				// Turning velocity lamps off
				velocityLampsToTurnOff = velocityLampsSum ^ 0xff;
				// Turning off not needed lamps
				GPIOG->BSRRH=velocityLampsToTurnOff;
				// Turning velocity lamps on
				GPIOG->BSRRL=velocityLampsSum;
				// Setting oldVelocityCounter in order to reset delta
				oldVelocityCounter = counter;
			// }
			TIM2->SR &= ~0x1;
		}
	}
		
  return 0;

}

																				// PG15 on positiv
void cuntup() { counter++; updateLED(); GPIOG->BSRRL=0x8000;}
																				// PG15 aus negativ
void cuntdn() { counter--; updateLED(); GPIOG->BSRRH=0x8000;}
void resetCounter() { counter = 0; oldVelocityCounter = 0; updateLED(); }
void updateLED() { }//GPIOG->ODR = counter; }
void err( ) {
	sprintf(ostr, "Fehler");
	TFT_puts(ostr);
} 
void noact() {}
