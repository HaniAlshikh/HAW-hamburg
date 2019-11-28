
/* logische_ausdruecke.c
 * Aufgabe 3 – Grundlagen der Programmierung in C
 * Autor: Hani Alshikh
 */

#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>


int main () {

   const int numbers = 7;
   char* ausgabe[] = { "null", "eins", "zwei", "drei", "vier",
                       "fünf", "sechs", "sieben", "acht", "neun"};

   

   for (int i = 0; i < numbers; i++) {
      printf("%s", ausgabe[0]);
   }
   
  	return EXIT_SUCCESS;
}

