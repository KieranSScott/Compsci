#!/bin/bash

rm part4 s4 2>/dev/null
gcc part4.c -o part4 2>> s4
if [[ -s s4 ]]
then
    echo There is a compile warning/error.
fi
if [[ ! -x part4 ]] 
then 
    echo part4 executable cannot be generated
    echo part 4 mark is 0
    exit
fi
rm s4 2>/dev/null
./part4 p4.obj > s4 2>/dev/null
len=0
index=0
while read line
do
    solution[$index]=$line
    (( len=len+1))
    (( index=index+1 ))
done <answer4

index=0
success=0
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
done <s4
echo success $success 
if (( success == 13 ))
then
    echo part 4 passes the test
fi
