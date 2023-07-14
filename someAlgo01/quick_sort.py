def quick_sort(values):
    if len(values) <= 1:
        return values
    pivot = values[0]
    left = []
    right = []
    for i in range(1,len(values)):
        if values[i] <= pivot:
            left.append(values[i])
        else:
            right.append(values[i])

    return quick_sort(left) + [pivot] + quick_sort(right)


test = [1, 7, 58, 6, 4, 2, 3, 5, 9, 1, 100, 500, 61]
print(quick_sort(test))
