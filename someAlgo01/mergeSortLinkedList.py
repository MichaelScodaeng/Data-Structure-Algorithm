from linkedList import SinglyLinkedList

def merge_sort(linkedList):
    if linkedList.size()==1:
        return linkedList
    elif linkedList.head == None:
        return linkedList
    leftHalf,rightHalf = split(linkedList)
    left = merge_sort(leftHalf)
    right = merge_sort(rightHalf)
    return merge(left,right)
def split(linkedList):
    if linkedList == None or linkedList.head ==None :
        leftHalf = linkedList
        rightHalf = None
        return leftHalf,rightHalf
    else:
        size = linkedList.size()
        mid = size//2
        midNode = linkedList.nodeAtIndex(mid-1)
        leftHalf = linkedList
        rightHalf = SinglyLinkedList()
        rightHalf.head = midNode.next
        midNode.next = None
        return leftHalf,rightHalf
def merge(left,right):
    mergeList = SinglyLinkedList()
    mergeList.addTop(0)
    current = mergeList.head
    leftHead = left.head
    rightHead = right.head
    while leftHead or rightHead:
        if leftHead == None:
            current.next = rightHead
            rightHead = rightHead.next
        elif rightHead ==None:
            current.next = leftHead
            leftHead = leftHead.next
        else:
            leftData = leftHead.data
            rightData = rightHead.data
            if leftData<rightData:
                current.next = leftHead
                leftHead = leftHead.next
            else:
                current.next = rightHead
                rightHead = rightHead.next
        current = current.next
    mergeList.head = mergeList.head.next
    return mergeList
if __name__ == "__main__":
    l = SinglyLinkedList()
    l.addTop(10)
    l.addTop(2)
    l.addTop(44)
    l.addTop(15)
    l.addTop(200)
    l.addTop(69)
    print(l)
    mergeList = merge_sort(l)
    print(mergeList)
