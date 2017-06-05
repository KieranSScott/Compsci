def insertionSort(unsortedList, n):
	moves = 0
	addistionalinversions = 0
	valueToInsert = 0
	currentIndex = 0
	index = 0
	while index < n:
		valueToInsert = unsortedList[index]
		currentIndex = index
		while unsortedList[currentIndex - 1] > valueToInsert and currentIndex >= 1:
			unsortedList[currentIndex] = unsortedList[currentIndex - 1]
			currentIndex -= 1
			moves += 1
		unsortedList[currentIndex] = valueToInsert
		index += 1
	for i in unsortedList:
		print(i, end = "\n")
	addistionalinversions = int((((n - 1) * n) / 2) - moves)
	return moves, addistionalinversions



def main():
	n = int(input())
	unsortedList = []
	for i in range(n):
		unsortedList += [int(input())]
	moves, addistionalinversions = insertionSort(unsortedList, n)
	print("moves:" + str(moves))
	print("additionalinversions:" + str(addistionalinversions))
	
	
main()