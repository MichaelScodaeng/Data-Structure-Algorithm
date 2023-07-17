class Node:
    def __init__(self,data=None,next=None):
        self.data = None
        self.next = None
        self.prev = None
        if(data!=None):
            self.data = data
    def getData(self):
        return self.data
class DoublyLinkedList:
    def __init__(self):
        self.head = None
        self.data = None
        self.tail = None
        self.size=0
    def add(self,value):
        #print(f"Size: {self.size}")
        self.insert(value,self.size)
    def isEmpty(self):
        return self.size==0
    def insert(self,value,index):

        if(index>self.size): raise Exception("index must not larger than size")
        if(self.head == None):
            #print("head")
            self.head = Node(value)
            self.tail = self.head
            self.size+=1
        elif (index ==0):
            self.head.prev = Node(value)
            self.head.prev.next = self.head
            self.head = self.head.prev
        elif (index==self.size):
            #print("tail")
            self.tail.next = Node(value)
            self.tail.next.prev = self.tail
            self.tail = self.tail.next
            self.size+=1
            #print("tail data:" +str(self.tail.data))
        else:
            #print("between")
            cnt = 0
            trav= self.head
            prev = None
            while(trav.next!=None):
                if(cnt == index):
                    break
                prev = trav
                trav = trav.next
                cnt+=1
            prev.next = Node(value)
            prev.next.next = trav
            trav.prev = prev.next
            self.size+=1
    def remove(self,value):
        if(self.isEmpty()):
            raise Exception("Linked List is Empty")
        if not self.search(value):
            raise Exception("Linked is Larger than actual size")
        else:
            trav = self.head
            prev = None
            while(trav.next!=None and trav.data != value):
                prev = trav
                trav=  trav.next
            tmp = trav.data

            prev.next = trav.next
            trav.next.prev = prev
            return tmp
    def search(self,value):
        trav = self.head
        while trav!=None:
            if trav.data == value:
                return True
            trav = trav.next
        return False
    def removeAt(self,index):
        if(self.isEmpty()):
            raise Exception("Linked List is Empty")
        if(index >=self.size):
            raise Exception("Linked is Larger than actual size")
        if(index ==0):
            tmp = self.head.data
            self.head = self.head.next
            self.head.prev = None
            self.size-=1
            return tmp
        elif(index == self.size-1):
            tmp = self.tail.data
            self.tail = self.tail.prev
            self.tail.next = None
            self.size-=1
            return tmp
        else:
            trav = self.head
            prev = None
            cnt=0
            while(trav.next!=None and index != cnt):
                cnt+=1
                prev = trav
                trav=  trav.next
            tmp =trav.data
            prev.next = trav.next
            trav.next.prev = prev
            self.size-=1
            return tmp
    def pop(self):
        return self.removeAt(self.size-1)
    def toString(self):
        ret = ""
        trav = self.head
        while(trav.next!=None):
            #print("current trav val: " + str(trav.data))
            #print("current tail val: " + str(self.tail.data))
            if(trav == self.head):
                ret+=f"[Head: {trav.data}] -> "
            else:
                ret+=f"[{trav.data}] -> "
            trav = trav.next

            #print("Iterated String: "+ ret)
        return ret + f"[Tail: {trav.data}]"
    def display(self):
        print(self.toString())

if __name__ =="__main__":
    dll = DoublyLinkedList()
    dll.add(10)
    dll.add(20)
    dll.add(30)
    dll.add(35)
    dll.display()
    dll.insert(15,0)
    dll.display()
    dll.insert(25,1)
    dll.display()
    dll.pop()
    dll.display()
    dll.removeAt(0)
    dll.display()
    dll.removeAt(2)
    dll.display()



