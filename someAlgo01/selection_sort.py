def selection_sort(values):
    sortedList = []
    for i in range(len(values)):
        sortedList.append(values.pop(minIndex(values)))
    return sortedList
def minIndex(values):
    minIndex = 0
    for i in range(1,len(values)):
        if(values[i]<values[minIndex]):
            minIndex = i
    return minIndex
test = [1, 7, 58, 6, 4, 2, 3, 5, 9, 1, 100, 500, 61]
print(selection_sort(test))