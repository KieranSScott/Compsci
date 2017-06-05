def quickSort(list,first,last):

	##check that the length of the list is more than 1
	if first<last:
	
		##find the best point to spilt
		splitpoint = listSplit(list,first,last)
		
		##recurse each side of the split
		quickSort(list,first,splitpoint-1)
		quickSort(list,splitpoint+1,last)


def listSplit(list,first,last):
	pivotvalue = list[first]
	
	##set the two pointers up
	leftPointer = first + 1
	rightPointer = last

	finished = False
	while not finished:
	
		##move leftPointer along the list first, stopping when reaching 
		##something bigger than the pivot or hitting the rightPointer
		while leftPointer <= rightPointer and list[leftPointer] <= pivotvalue:
			leftPointer = leftPointer + 1
			
		##then move the rightPointer down the list  
		while list[rightPointer] >= pivotvalue and rightPointer >= leftPointer:
			rightPointer = rightPointer -1
			
		##if the rightPointer crosses the leftPointer stop looping
		if rightPointer < leftPointer:
			finished = True
			
		##else swap the leftPointer value and the rightPointer value
		else:
			list[leftPointer], list[rightPointer] = list[rightPointer], list[leftPointer]
			
	##finally swap the first value (the pivot point) 
	##and the right pointers value
	list[first], list[rightPointer] = list[rightPointer], list[first]
	
	return rightPointer










def main():
	n = int(input())
	output = int(input())
	unsortedList = []
	for i in range(n):
		unsortedList += [int(input())]
	quickSort(unsortedList,0,len(unsortedList)-1)
	for i in range(output):
		print(unsortedList[i], end = "\n")
		

main()