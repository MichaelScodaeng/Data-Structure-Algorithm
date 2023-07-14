class Node:
    def __init__(self,data=None,next=None):
        self.data = None
        self.next = None
        if(data!=None):
            self.data = data
        if(next !=None):
            self.next = next
    def getData(self):
        return self.data
class SinglyLinkedList:
    def __init__(self):
        self.head = Node()
        self.size = 0
    def getSize(self):
        return self.size
    def isEmpty(self):
        return self.size==0
    def add(self,data):
        if(self.head.data == None):
            self.head = Node(data)
            self.size+=1
        else:
            trav = self.head
            while(trav.next!=None):
                #print("Traveling: " + str(trav.data))
                trav = trav.next
            trav.next = Node(data)
            #self.display()
            self.size+=1
    def insert(self,data,position):
        cnt = 0
        trav = self.head
        prev = Node()
        if(position==self.size):
            self.add(data)
        elif (position>self.size or position<0):
            raise Exception("position error")
        while cnt != position and trav.next != None:
            prev = trav
            trav = trav.next
            cnt+=1
        newNode = Node(data)
        prev.next = newNode
        newNode.next = trav
        trav = None
    def search(self,value):
        trav = self.head
        while trav!=None:
            if trav.data == value:
                return True
            trav = trav.next
        return False

    def delete(self,value):
        trav = self.head
        prev = Node()
        if(not self.search(value)):
            raise Exception("Not in list")
        while(trav!=None):
            if(trav.data == value):

                tmp = trav.data
                prev.next = trav.next
                trav = None
                return tmp
            prev = trav
            trav = trav.next
        return None

    def reverse(self):
        trav = self.head
        prev = None
        nextNode = None
        while(trav!=None):
            nextNode = trav.next
            trav.next = prev
            prev = trav
            trav = nextNode
        self.head = prev
    def display(self):
        trav = self.head
        while(trav!=None):
            print(trav.data,end = "->")
            trav = trav.next
        print()
if __name__ == "__main__":
    sll = SinglyLinkedList()
    sll.add(1)
    sll.display()
    print("-------")
    sll.add(2)
    sll.display()
    print("-------")
    sll.add(3)
    sll.display()
    print("-------")
    sll.add(4)
    sll.display()
    print("-------")
    sll.insert(5,2)
    sll.display()
    print("-------")
    sll.delete(5)
    sll.display()
    print("-------")
    sll.reverse()
    sll.display()
    print("-------")

