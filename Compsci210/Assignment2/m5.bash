#!/bin/bash

rm part5 s5 2>/dev/null
gcc part5.c -o part5 2>> s5
if [[ -s s5 ]]
then
    echo There is a compile warning/error.
fi
if [[ ! -x part5 ]] 
then 
    echo part5 executable cannot be generated
    echo part 5 mark is 0
    exit
fi
rm s5 2>/dev/null
./part5 p5.obj > s5 2>/dev/null
len=0
index=0
while read line
do
    solution[$index]=$line
    (( len=len+1))
    (( index=index+1 ))
done <answer5

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
done <s5
echo success $success
if (( success == 26 ))
then
    echo part 5 passes the test
fi
