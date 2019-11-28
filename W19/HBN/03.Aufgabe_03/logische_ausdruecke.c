/* logische_ausdruecke.c
 * Aufgabe 3 – Grundlagen der Programmierung in C
 * Autor: Hani Alshikh
 */

#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>


int main () {

	// define variables
   const int cases_num = 4;
   int i = 34; int j = 23; int k = 1155;

   // test cases
   int tests[cases_num] = {
      // i, j und k sind alle ungleich 0
      i ^ 0 && j ^ 0 && k ^ 0,
      // i ist durch 17 teilbar und echt positiv.
      i > 0 && i % 17 == 0,
      // j ist ungerade und liegt zwischen 20 und 40.
      j > 20 && j < 40 && j % 2 > 0,
      // k ist Vielfaches von 3 und 5 oder Vielfaches von 5 und 7 oder Vielfaches von 5 und 11.
      (k % 3 == 0 && k % 5 == 0) || (k % 5 == 0 && k % 5 == 7) || (k % 7 == 0 && k % 5 == 11)
   };

   // output
   for (int i = 0; i < cases_num; i++){
      printf("%s\n", tests[i] ? "True" : "False");
   }
   
  	return EXIT_SUCCESS;
}

