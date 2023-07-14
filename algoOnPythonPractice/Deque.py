class Deque:
    def __init__(self, data=None):
        if (data == None):
            self.items = []
            self.size = 0
        else:
            self.items = []
            self.items.extend(data)
            self.size = len(data)

    def getSize(self):
        return self.size

    def isEmpty(self):
        return self.items == []

    def enqueueLeft(self, value):
        self.items.insert(value, 0)
        self.size += 1

    def enqueueRight(self, value):
        self.items.append(value)
        self.size += 1

    def dequeueLeft(self):
        if (self.isEmpty()):
            raise Exception("Deque is empty")
        self.size -= 1
        return self.items.pop(0)

    def dequeueRight(self):
        if (self.isEmpty()):
            raise Exception("Deque is empty")
        self.size -= 1
        return self.items.pop()

    def topLeft(self):
        if (self.isEmpty()):
            raise Exception("Deque is empty")
        return self.items[0]

    def topRight(self):
        if (self.isEmpty()):
            raise Exception("Deque is empty")
        return self.items[self.getSize() - 1]

    def toString(self):
        ret = []
        if (self.isEmpty()):
            ret.append("Deque is empty")
        return "->".join([str(i) for i in self.items])
    def display(self):
        print(self.toString())

if __name__ == "__main__":
    deque01 = Deque([1,2,3,4,5,6,7])
    deque01.display()
    deque01.enqueueLeft(0)
    deque01.display()
    deque01.enqueueRight(8)
    deque01.display()
    deque01.dequeueLeft()
    deque01.display()
    deque01.dequeueRight()
    deque01.display()

