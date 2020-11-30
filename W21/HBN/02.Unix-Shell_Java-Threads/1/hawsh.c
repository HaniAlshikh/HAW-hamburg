/*
* basic HAW shell wrapper
* Date: 22.11.2020
* Author: Dornhof, Alshikh
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>

#include "functions.h"

int main(int argc, char **argv)
{
	welcome();
	getVersion();
	
  char userInput[31];

	// 1.1.5. keep the program running
	while (1){
		// 1.1.1. Prompt-String - current working directory and username
		showStatusLine();
		
		// 1.1.2. User input/command no options no arguments 
		fgets(userInput, sizeof(userInput), stdin);;
		// remove the enter symbole from the end
		userInput[strlen(userInput)-1] = 0;
		
		// 1.1.3. check if command is "built-in" or has to be delegated to the main shell
		if(strcmp(userInput, "quit")==0){
			return 0;
		}else if (strcmp(userInput, "version")==0){
			getVersion();
		}else if (strcmp(userInput, "help")==0){
			showHelp();
		}else if(strlen(userInput)>=1){
			executeCommand(userInput);
		}
	}
  
	return 0;
}

 


