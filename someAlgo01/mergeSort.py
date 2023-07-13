def mergeSort(list):
    if len(list)<=1:
        return list

#O(logn)
    mid = len(list)//2
    left = mergeSort(list[:mid])
    right = mergeSort(list[mid:])

    return merge(left,right)


def merge(left,right): #O(n)
    l = []
    i=0
    j=0
    while(i<len(left) and j<len(right)):
        if(left[i]<right[j]):
            l.append(left[i])
            i+=1
        else:
            l.append(right[j])
            j+=1
    while i<len(left):
        l.append(left[i])
        i+=1
    while j<len(right):
        l.append(right[j])
        j+=1
    return l
aList = [10,30,20,54,24,8,9,33]
print(mergeSort(aList))