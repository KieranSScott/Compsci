#!/bin/bash

rm part7 s7 2>/dev/null
gcc part7.c -o part7 2>> s7
if [[ -s s7 ]]
then
    echo There is a compile warning/error.
fi
if [[ ! -x part7 ]] 
then 
    echo part7 executable cannot be generated
    echo part 7 mark is 0
    exit
fi
rm s7 2>/dev/null
./part7 p7.obj > s7 2>/dev/null
len=0
index=0
while read line
do
    solution[$index]=$line
    (( len=len+1))
    (( index=index+1 ))
done <answer7

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
done <s7
echo success $success 
if (( success == 13 ))
then
    echo part 7 passes the test
fi
