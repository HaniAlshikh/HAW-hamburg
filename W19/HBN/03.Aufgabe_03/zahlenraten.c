

#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <stdbool.h>

int main() {       
	/* Variablen deklarieren */
  const int numbers = 10;
	srand(time(NULL));
	int random = rand() % numbers + 1;
  int num;

	printf("Willkommen! Bitte rate eine Zahl zwischen 1 und 10:\n");

  for (int i = 1; i <= numbers; i++) {
    printf("%d. Versuch: ", i);
    scanf("%2d", &num);

    if (num == random) { 
      printf("Ergebnis: Glückwunsch! Die Zahl wurde im %d. Versuch korrekt geraten!\n", i);
      break;
      }
      
    if (num > random) { printf("Ergebnis: Die Zahl ist leider zu groß!\n"); }
    if (num < random) { printf("Ergebnis: Die Zahl ist leider zu klein!\n"); }
  }
	
	return EXIT_SUCCESS;
}