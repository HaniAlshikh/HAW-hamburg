#!/bin/bash
# append all files in a directory with user specific suffix
# Autor: Dornhof, Alshikh
# 08.11.2020

DIRECTORY=$PWD/$1
APPEND=$2

# Abort function if params are not set
function quitApp_insufficientParams {
        echo "Parameters are not set! Usage: freename.sh <dir> <suffix>";
        exit;
}

# check if params are set
# abort if not
[ -z "$1" ] && quitApp_insufficientParams
[ -z "$2" ] && quitApp_insufficientParams


# for every file in .... mv with new suffix
for f in "$DIRECTORY"/*
do
	mv "$f" "$f$APPEND"
done
