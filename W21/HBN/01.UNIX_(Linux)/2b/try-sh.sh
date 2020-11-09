#!/bin/bash
# ping test a host for reachability
# Autor: Dornhof, Alshikh
# 08.11.2020


HOSTNAME="$1"


# Feedback & exit function if parameters are not sufficient
function quitApp_insufficientParams {
	echo "Insufficient Parameters! Usage: ./try-sh.sh <hostname>"
	exit
}

# Check if parameters are specified
[ -z "$HOSTNAME" ] && quitApp_insufficientParams

while sleep 1;  # Sleep a second after execution (Every second ...)
do
	# Execute ping and abort after max 1 second
	# Forward the output of ping into /dev/null to prevent unwanted output
	ping -q "$HOSTNAME" -c 1 -W 1 > /dev/null 2>&1
	
	# Verify the return code to display the corrosponding messages 
	# "$?" specifies the return code of the last executed function (in this case "ping")
	if [ "$?" -eq 0 ]; then
		echo "$HOSTNAME OK"
	else
		echo "$HOSTNAME FAILED"
	fi
done
