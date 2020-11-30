/*
* HAW shell wrapper helper functions
* Date: 22.11.2020
* Author: Dornhof, Alshikh
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>

double version = 1.0;
char hostname[10];
char *username;
char path[1024];

char *red = "\033[1;31m";
char *blue = "\033[1;34m";
char *green = "\033[1;32m";
char *normall = "\033[0m";

void prepVar() {
  gethostname(hostname, sizeof(hostname));
  username = getenv("USER");
  getcwd(path, sizeof(path));
}

void welcome(){
	printf("\n============= HAWSH =============\n\n");
	prepVar();
}

void getVersion(){
		printf("HAW-Shell Version %.2f\nAutor: Michael Dornhof, Hani Alshikh\n\n", version);
}

void showStatusLine(){
	printf("%s%s: %s%s ?> %s", red, path, green, username, normall);
}

void showHelp(){
	printf("Built in Commands:\n\n");
	printf("quit - use to exit out of the HAW shell\n");
	printf("version - returns the version of the HAW shell\n");
	printf("help - shows the (this) help text\n");
}

int endsWith(char string[], char character){
	return string[strlen(string)-1] == character;
}

void executeCommand(char cmd[]){
	int waitForChild = !endsWith(cmd, '&');
	
	if(!waitForChild){
    // remove the '&' to run the command normally and deal with it later
		cmd[strlen(cmd)-1] = 0;
	}

	pid_t pid;
	switch(pid = fork()){
			case -1: break; // something went wrong

			case 0: // child process running
				if (!waitForChild) { printf("\n"); }
				if(execlp(cmd,cmd,NULL)==-1){
				  printf("Unknown command\n");
				  exit(-1);
				}
  			break;
			
			default: // parent process running
				// 1.1.4. don't wait if command end with '&'
				if (waitForChild) {
					if (waitpid(pid, NULL, 0) == -1) {
						perror("waitpid() error:");
					}
				}
			break;
	}	
}
