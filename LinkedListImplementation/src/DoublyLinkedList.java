
import java.time.Period;

public class DoublyLinkedList <T>  {
    public int size = 0;
    private Node<T> head = null;
    private Node<T> tail = null;
    public class Node<T>
    {
        T data;
        Node<T> prev = null;
        Node<T> next = null;
        public Node (T data, Node<T> prev, Node<T> next)
        {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }
        public String toString()
        {
            return data.toString();
        }
    }
    public int getSize()
    {
        return size;
    }
    public boolean isEmpty()
    {
        return size==0;
    }
    public void clear()
    {
        Node<T> trav = head;
        while(trav.next!=null)
        {
            Node<T> travNext = trav.next;
            trav.data = null;
            trav.prev = null;
            trav.next = null;
            trav = travNext;
        }
        head = tail = trav = null;
        size = 0;
    }
    public void add(T elem)
    {
        if(isEmpty())
        {
            addFirst(elem);
        }
        else
        {
            addLast(elem)l;
        }
    }
    public void addFirst(T elem)
    {
        if(isEmpty())
        {
            head = tail = new Node<T>(elem,null,null);
        }
        else
        {
            head.prev = new Node<T>(elem,null,head);
            head = head.prev;
        }
        size++;
    }
    public void addLast(T elem)
    {
        if(isEmpty())
        {
            head = tail = new Node<T>(elem,null,null);
        }
        else
        {
            tail.next = new Node<T>(elem,head,null);
            tail = tail.next;
        }
        size++;
    }
    public T getFirst()
    {
        if(isEmpty()) throw  new RuntimeException("DoublyLinkedList is Empty");
        return head.data;
    }
    public T getLast()
    {
        if(isEmpty()) throw  new RuntimeException("DoublyLinkedList is Empty");
        return tail.data;
    }
    public T removeFirst()
    {
        if(isEmpty()) throw  new RuntimeException("DoublyLinkedList is Empty");
        T thrownData = head.data;
        head = head.next;
        size--;
        if(isEmpty()) tail = null;
        else head.prev = null;
        return thrownData;
    }
    public T removeLast()
    {
        if(isEmpty()) throw  new RuntimeException("DoublyLinkedList is Empty");
        T thrownData = tail.data;
        tail = tail.prev;
        size--;
        if(isEmpty())
        {
            head = null;
        }
        else
        {
            tail.next = null;
        }
        return thrownData;

    }
    private T removeNode(Node<T> node)
    {
        if(node.prev ==null)
        {
            return removeFirst();
        }
        else if (node.next ==null)
        {
            return removeLast();
        }
        else
        {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            T thrownData = node.data;
            node.data = null;
            node.prev = null;
            node.next = null;
            size--;
            return thrownData;
        }
    }
    public T removeAt(int index)
    {
        if(index<0||index>=size)
        {
            throw new RuntimeException("The index shouldn't be zero or be more than size");
        }
        Node<T> trav = head;
        for(int i=0;i<index;i++)
        {
            trav = trav.next;
        }
        return removeNode(trav);
    }
    public T remove(Object obj)
    {
        if(obj==null)
        {
            for(Node<T>trav = head;trav.next!=null;trav=trav.next)
            {
                if(trav.data==null)
                {
                    return removeNode(trav);
                }
            }
        }
        else
        {
            for(Node<T>trav = head;trav.next!=null;trav=trav.next)
            {
                if(obj.equals(trav.data))
                {
                    return removeNode(trav);
                }
            }
        }

        throw new RuntimeException("This object is not detected in linkedlist");
    }
    public int indexOf(Object obj)
    {
        int index=0;
        if(obj==null)
        {
            for(Node<T>trav = head;trav.next!=null;trav=trav.next)
            {
                index++;
                if(trav.data==null)
                {
                    return index;
                }
            }
        }
        else
        {
            for(Node<T>trav = head;trav.next!=null;trav=trav.next)
            {
                index++;
                if(obj.equals(trav.data))
                {
                    return index;
                }
            }
        }

        return -1;
    }
    public String toString()
    {
        String ret = "[ ";
        for(Node<T> trav = head;trav.next!=null;trav=trav.next)
        {
            ret+=String.valueOf(trav.data)+" ";
        }
        ret+=" ]";
        return ret;

    }


}
