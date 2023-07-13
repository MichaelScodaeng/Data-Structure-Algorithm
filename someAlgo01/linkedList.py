class Node:
    data = None
    next = None

    def __init__(self, data):
        self.data = data

    def __repr__(self):
        return f"Node data: {self.data}"


class DoublePointerNode:
    data = Node
    childNode = None
    parentNode = None

    def __init__(self, data):
        self.data = data

    def __repr__(self):
        return f"Node data: {self.data}"


class SinglyLinkedList:
    head = None
    _size = 0
    nodes = []

    def __init__(self):
        self.head = None

    def isEmpty(self):
        return self.head is None

    def size(self):
        current = self.head
        count = 0
        while current != None:
            count += 1
            current = current.next
        return count

    def addTop(self, data):
        new_node = Node(data)
        new_node.next = self.head
        self.head = new_node
        self._size += 1

    def search(self, data):
        trav = self.head
        while (trav != None):
            if (trav.data == data):
                return trav
            trav = trav.next
        return None

    def insert(self, data, index):
        if (index > self._size):
            return None
        if (index == 0):
            self.addTop(data)
        else:
            newNode = Node(data)
            position = 0
            trav = self.head
            prev = None
            while (trav != None):
                if position == index:
                    prev.next = newNode
                    newNode.next = trav
                    self._size += 1
                    break
                prev = trav
                trav = trav.next
                position += 1
            if trav == None and position == self._size:
                prev.next = newNode
                newNode.next = None

            return None

    def remove(self, data):
        trav = self.head
        prev = None
        while (trav != None):
            if (trav.data == data):
                data = trav.data
                if (prev == None):
                    self.head = trav.next
                else:
                    prev.next = trav.next
                self._size -= 1
                return data
            prev = trav
            trav = trav.next
        return None

    def nodeAtIndex(self, index):
        if (index == 0):
            return self.head
        else:
            trav = self.head
            pos = 0
            while (trav != None and pos < index):
                pos += 1
                trav = trav.next
            return trav

    def __repr__(self):
        nodes = []
        trav = self.head
        while (trav != None):
            if (trav == self.head):
                nodes.append(f"[Head: {trav.data}]")
            elif (trav.next == None):
                nodes.append(f"[Tail: {trav.data}]")
            else:
                nodes.append(f"[Node: {trav.data}]")
            trav = trav.next
        return " -> ".join(nodes)


if __name__ == "__main__":
    SinglyLinkedList01 = SinglyLinkedList()
    SinglyLinkedList01.addTop(1)
    SinglyLinkedList01.addTop(2)
    SinglyLinkedList01.addTop(3)
    print(SinglyLinkedList01)
    SinglyLinkedList01.insert(10, 1)
    print(SinglyLinkedList01)
    SinglyLinkedList01.insert(10, 3)
    print(SinglyLinkedList01)
    SinglyLinkedList01.insert(10, 5)
    print(SinglyLinkedList01)
