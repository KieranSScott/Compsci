#!/bin/bash

rm part1 s1 2>/dev/null
gcc part1.c -o part1 2>> s1
if [[ -s s1 ]]
then
    echo There is a compile warning/error.
fi
if [[ ! -x part1 ]] 
then 
    echo part1 executable cannot be generated
    exit
fi
rm s1 2>/dev/null
./part1 p1.obj > s1 2>/dev/null
len=0
index=0
while read line
do
    solution[$index]=$line
    (( len=len+1))
    (( index=index+1 ))
done <answer1

index=0
success=0;
while ((index<len))
do
    read line
    if [[ $line != ${solution[index]} ]] 
    then 
	temp=${solution[index]}
	echo line \""$temp"\" does not match to answer
    else 
	((success=success+1))
    fi
    ((index=index+1))
done <s1
echo success $success 
if (( success == 14 )) 
then
    echo part 1 passes the test
fi