/*
* get user id and save it to a file specified from the user
* Date: 22.10.2020
* Author: Dornhof, Alshikh
*/

#include <stdio.h>
#include <unistd.h>
#include <string.h>


/**
 *	helper method to display file error 
 */
int error() {
	printf("File couldnt be created/modified");
  return 1;
}

/**
 *  [Entrypoint] 
 *  Requests a filename and writes the currently logged in user into that file 
 */
int main () {
	uid_t nUserId = getuid(); 																			// 01. read memory
	// max 30 + 1 for the c null terminator
	char szUserInput[31];																						//

	// Wait for Userinput
	printf("Name der neuen Datei: ");																// 02. I/O
	fgets(szUserInput, sizeof(szUserInput), stdin);									// 03. I/O

	// Find Newline character and replace it by 0 (0-terminated-c-string)
	szUserInput[strcspn(szUserInput, "\n")] = 0; 										//

	// Flag "w" = Creates File for writing. If file exists, content gets overwritten.
	// Open handle, write content, close handle
	FILE *pFile = fopen(szUserInput, "w");													// 04. open/create file

	// check if file can be created modified
	if (pFile == NULL) {
    error();
	}

	fprintf(pFile, "%u", nUserId);																	// 05. modify the file	
	fclose(pFile);																									// 06. close the file

	if (access(szUserInput, F_OK) == 0 ) {													// 07. check the file
    printf("User id is now saved to %s\n", szUserInput);					// 08. I/O
	} else {
		error();
	}

	return 0;																												// 09. close prog
}

/** System Calls
 * 
 *  at least 09 System calls are needed
 *  
 */ 