/* matrikelnrtext.c
 * Aufgabe 3 – Grundlagen der Programmierung in C
 * Autor: Hani Alshikh
 */

#include <stdio.h>
#include <stdlib.h>

int main () {

	// define variables
   const int numbers = 7;
   char* ausgabe[] = { "null", "eins", "zwei", "drei", "vier",
                       "fünf", "sechs", "sieben", "acht", "neun"};
   int eingabe[numbers];
   int num;

   printf("\nBiite Matrikelnummer eingeben:\n\n");

   // collect eingabe
   for (int i = 0; i < numbers; i++) {
      printf("%d. Number: ", i);
      scanf("%1d", &num);
      eingabe[i] = num;
   }

   printf("\n");

   // print ausgabe
   for (int i = 0; i < numbers; i++) {
      printf("%s ", ausgabe[eingabe[i]]);
   }
   
  	return EXIT_SUCCESS;
}

