def fibonacci_dp(num):
    if num == 0:
        return 0
    elif num == 1:
        return 1
    dp = [0] * (num + 1)
    dp[1] = 1
    for i in range(2, num + 1):
        dp[i] = dp[i - 1] + dp[i - 2]
    return dp[num - 1]


class Stack:
    def __init__(self):
        self.items = []

    def isEmpty(self):
        return self.items == []

    def size(self):
        return len(self.items)

    def pop(self):
        if (self.isEmpty()): raise Exception("Stack is already empty.")
        return self.items.pop()

    def top(self):
        return self.items[self.size() - 1]

    def get(self, num):
        return self.items[num]

    def clear(self):
        self.items = []

    def push(self, n):
        self.items.append(n)


class BaseNumberStack(Stack):
    def __init__(self, num, base):
        super().__init__()
        self.num = num
        self.base = base
        self.items = self.numToStack(self.num, self.base)

    def numToStack(self, n, base):
        numStack = Stack()
        while (n != 0):
            r = n % base
            numStack.push(r)
            n = n // base
            # print(r)
        if (numStack.isEmpty()):
            numStack.push(0)
        return numStack

    def __str__(self):
        ret = ""
        for i in range(self.items.size() - 1, -1, -1):
            ret += str((self.items).get(i))
        return ret


class Queue:
    def __init__(self):
        self.items = []

    def get(self, num):
        return self.items[num]

    def clear(self):
        self.items = []

    def isEmpty(self):
        return self.items == []

    def size(self):
        return len(self.items)

    def dequeue(self):
        if (self.isEmpty()): raise Exception("Queue is already empty.")
        return self.items.pop(0)

    def enqueue(self, n):
        self.items.append(n)


class CircularQueue:
    def __init__(self, size):
        self.size = size
        self.queue = [None for i in range(size)]
        self.front = self.rear = -1

    def get(self, num):
        return self.items[num]

    def clear(self):
        self.queue = [None for i in range(self.size)]
        self.front = -1
        self.rear = -1

    def isEmpty(self):
        for i in self.queue:
            if (i != None):
                return False
        return True

    def sizes(self):
        return self.size

    def dequeue(self):
        if (self.isEmpty()):
            raise Exception("Queue is already empty.")
        elif self.front == self.rear:
            temp = self.queue[self.front]
            self.front = -1
            self.rear = -1
            return temp
        else:
            temp = self.queue[self.front]
            self.front = (self.front + 1) % self.size
            return temp

    def enqueue(self, data):
        if (self.rear + 1) % self.size == self.front:
            raise Exception("Queue is already full.")
        elif (self.front == -1):
            self.front = 0
            self.rear = 0
            self.queue[self.rear] = data
        else:
            self.rear = (self.rear + 1) % self.size
            self.queue[self.rear] = data

    def toString(self):
        ret = ""
        if (self.isEmpty()):
            ret += "Circular Queue is empty"
        elif self.rear < self.front:
            ret += "Elements in Circular Queue: "
            for i in range(self.front, self.size):
                ret += str(self.queue[i]) + " -> "
            for i in range(0, self.rear + 1):
                ret += str(self.queue[i]) + " -> "
        else:
            ret += "Elements in Circular Queue: "
            for i in range(self.front, self.rear + 1):
                ret += str(self.queue[i]) + " -> "
        return ret

    def display(self):
        print(self.toString())


if __name__ == "__main__":
    # print(fibonacci_dp(5))
    # num = 29
    # stacknum01 = BaseNumberStack(num,2)
    # print(stacknum01)
    circularQueue01 = CircularQueue(5)
    circularQueue01.enqueue(4)
    circularQueue01.display()
    circularQueue01.enqueue(20)
    circularQueue01.display()
    circularQueue01.enqueue(5)
    circularQueue01.display()
    circularQueue01.enqueue(10)
    circularQueue01.display()
    circularQueue01.enqueue(0)
    circularQueue01.display()
    circularQueue01.dequeue()
    circularQueue01.display()
    circularQueue01.enqueue(0)
    circularQueue01.display()
    print(circularQueue01.sizes())
