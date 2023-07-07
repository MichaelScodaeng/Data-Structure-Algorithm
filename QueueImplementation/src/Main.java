import java.util.LinkedList;

class QueueImplementation<T> {
    //changed to static array based queue
    LinkedList<T> queue;
    public int size= 0;
    public QueueImplementation()
    {
        queue = new LinkedList<T>();
    }
    public QueueImplementation(T elem)
    {
        queue = new LinkedList<T>();
        queue.addLast(elem);
        size++;
    }
    public int getSize()
    {
        return size;
    }
    public boolean isEmpty()
    {
        return size==0;
    }
    public void enqueue(T elem)
    {
        queue.addLast(elem);
        size++;
    }
    public T dequeue()
    {
        if(isEmpty()) throw new RuntimeException("Stack is already empty");
        size--;
        return queue.removeFirst();
    }
    public T top()
    {
        if(isEmpty()) throw new RuntimeException("Stack is already empty");
        return queue.getFirst();
    }



}
