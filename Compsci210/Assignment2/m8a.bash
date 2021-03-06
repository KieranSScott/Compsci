#!/bin/bash

rm part8 s8a 2>/dev/null
gcc part8.c -o part8 2>> s8a
if [[ -s s8a ]]
then
    echo There is a compile warning/error.
fi
if [[ ! -x part8 ]] 
then 
    echo part8 executable cannot be generated
    echo part 8 mark is 0
    exit
fi
rm s8a 2>/dev/null
./part8 p8a.obj > s8a 2>/dev/null
len=0
index=0
while read line
do
    solution[$index]=$line
    (( len=len+1))
    (( index=index+1 ))
done <answer8a

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
done <s8a
echo success $success 
if (( success == 13 ))
then
    echo part 8 passes the test
fi
