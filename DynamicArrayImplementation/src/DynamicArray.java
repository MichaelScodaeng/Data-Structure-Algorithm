
public class DynamicArray <T> implements Iterable {
    private T[] arr;
    private int size = 0;
    //Normal Constructor
    public DynamicArray()
    {
        this(16);
    }

    //Parameter Constructor
    public DynamicArray(int i)
    {
        if(i<=0)
        {
            throw new IllegalArgumentException("Array size should not be lower than zero. which " + i + " is inappropriate");

        }
        else
        {
            this.size = i;
            arr = (T[]) (new Object[i]);
        }
    }
    public int len()
    {
        return size;
    }
    public boolean isEmpty()
    {
        return this.size == 0;
    }
    public T get(int index)
    {
        return arr[index];
    }
    public void set(int index,T element)
    {
        arr[index] = element;
    }
    public void clear()
    {
        for(int i=0;i<size;i++)
        {
            arr[i] = null;
        }
        size = 0;
    }
    public void add(T element)
    {
        int oldSize = size;
        if(size ==0)
        {
            size=2;
        }
        else
        {
            size*=2;
        }
        T[] arr_temp = (T[]) new Object[size];
        for(int i=0;i<oldSize;i++)
        {
            arr_temp[i] = arr[i];
        }
        arr_temp[oldSize+1] = element;
        arr = arr_temp;
    }
    public void removeAt(int rm_index)
    {

    }


}