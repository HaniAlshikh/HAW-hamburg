/* zahlenraten.c
 * Aufgabe 3 – Grundlagen der Programmierung in C
 * Autor: Hani Alshikh
 */

#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <stdbool.h>

int main() {      

	// define variables
  bool found = false;
  const int numbers = 10;
	srand(time(NULL));
	int random = rand() % numbers + 1;
  int num;

	printf("\nWillkommen! Bitte rate eine Zahl zwischen 1 und 10:\n\n");

  // get and check input for random
  for (int i = 1; i <= numbers && !found; i++) {
    printf("%d. Versuch: ", i);
    scanf("%2d", &num);

    if (num == random) { 
      printf("\nErgebnis: Glückwunsch! Die Zahl wurde im %d. Versuch korrekt geraten!\n", i);
      found = true;
      }
    else if (num > random) {
       printf("Ergebnis: Die Zahl ist leider zu groß!\n"); 
      }
    else {
       printf("Ergebnis: Die Zahl ist leider zu klein!\n"); 
      }
  }
	
	return EXIT_SUCCESS;
}