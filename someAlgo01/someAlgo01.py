# O(n)
def linear_search(list, target):
    for i in range(0, len(list)):
        if list[i] == target:
            return i


# O(logn) it has to be sorted
def binary_search(list, target):
    first = 0;
    last = len(list) - 1
    while (first <= last):
        mid = (last + first) // 2
        if (target == list[mid]):
            return mid
        elif (target < list[mid]):
            last = mid - 1
        else:
            first = mid + 1
    return None


def recursive_binary_search(list, target, first, last):
    mid = (first + last) // 2
    if (first >= last):
        if (mid>len(list)-1 or list[mid] != target):
            return None
    if target == list[mid]:
        return mid
    else:
        if target < list[mid]:
            return recursive_binary_search(list, target, first, mid - 1)
        else:
            return recursive_binary_search(list, target, mid + 1, last)


def verify(index):
    if index != None:
        print(f"Target is found at {index}")
    else:
        print("target not found")


numbers = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
result = binary_search(numbers, 12)
verify(result)
result = binary_search(numbers, 3)
verify(result)
for i in range(len(numbers)):
    result = recursive_binary_search(numbers, numbers[i], 0, len(numbers))
    verify(result)
result = recursive_binary_search(numbers, 11, 0, len(numbers))
verify(result)
