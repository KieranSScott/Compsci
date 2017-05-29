#!/bin/bash

rm part3 s3 2>/dev/null
gcc part3.c -o part3 2>> s3
if [[ -s s3 ]]
then
    echo There is a compile warning/error.
fi
if [[ ! -x part3 ]] 
then 
    echo part3 executable cannot be generated
    echo part 3 mark is 0
    exit
fi
rm s3 2>/dev/null
./part3 p3.obj > s3 2>/dev/null
len=0
index=0
while read line
do
    solution[$index]=$line
    (( len=len+1))
    (( index=index+1 ))
done <answer3

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
done <s3
echo success $success 
if (( success == 13 ))
then
    echo part 3 passes the test
fi
