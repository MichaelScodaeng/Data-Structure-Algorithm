import java.util.LinkedList;

public class StackImplementation<T> {
    //change to static array
    LinkedList<T> stack;
    public int size= 0;
    public StackImplementation()
    {
        stack = new LinkedList<T>();
    }
    public StackImplementation(T elem)
    {
        stack = new LinkedList<T>();
        stack.addLast(elem);
        size++;
    }
    public int getSize()
    {
        return size;
    }
    public void push(T elem)
    {
        stack.addLast(elem);
        size++;
    }
    public boolean isEmpty()
    {
        return size==0;
    }
    public T pop()
    {
        if(isEmpty()) throw new RuntimeException("Stack is already empty");
        size--;
        return stack.removeLast();
    }
    public T top()
    {
        if(isEmpty()) throw new RuntimeException("Stack is already empty");
        return stack.getLast();
    }



}
