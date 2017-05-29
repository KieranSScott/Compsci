#!/bin/bash

rm part6 s6 2>/dev/null
gcc part6.c -o part6 2>> s6
if [[ -s s6 ]]
then
    echo There is a compile warning/error.
fi
if [[ ! -x part6 ]] 
then 
    echo part6 executable cannot be generated
    echo part 6 mark is 0
    exit
fi
rm s6 2>/dev/null
./part6 p6.obj > s6 2>/dev/null
len=0
index=0
while read line
do
    solution[$index]=$line
    (( len=len+1))
    (( index=index+1 ))
done <answer6

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
done <s6
echo success $success 
if (( success == 13 ))
then
    echo part 6 passes the test
fi
