#!/bin/bash

rm part2 s2 2>/dev/null
gcc part2.c -o part2 2>> s2
if [[ -s s2 ]]
then
    echo There is a compile warning/error.
fi
if [[ ! -x part2 ]] 
then 
    echo part2 executable cannot be generated
    echo part 2 mark is 0
    exit
fi
rm s2 2>/dev/null
./part2 p2.obj > s2 2>/dev/null
len=0
index=0
while read line
do
    solution[$index]=$line
    (( len=len+1))
    (( index=index+1 ))
done <answer2

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
done <s2
echo success $success 
if (( success == 26 ))
then
    echo part 2 passes the test
fi
