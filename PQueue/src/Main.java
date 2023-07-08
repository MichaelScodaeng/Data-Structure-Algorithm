import java.util.ArrayList;
import java.util.HashMap;
import java.util.*;
class MinHeap<T extends Comparable<T>> {
    //number of internal elements
    private int heapSize = 0;
    //number of internal space
    private int heapCapacity = 0;
    private List<T> heap;
    private Map<T,TreeSet<Integer>> map = new HashMap<>();
    public MinHeap()
    {
        this(1);
    }
    public MinHeap(int size)
    {
        heap = new ArrayList<>(size);
    }
    public MinHeap(T[] elems) {
        heapCapacity = heapSize = elems.length;
        heap = new ArrayList<>(heapCapacity);
        for (int i = 0; i < heapSize; i++) {
            mapAdd(elems[i], i);
            heap.add(elems[i]);
        }
        //Heapify process
        for (int i = Math.max(0, (heapSize / 2) - 1); i >= 0; i--) sink(i);
    }
        public MinHeap(Collection<T> elems) {
        this(elems.size());
        for (T elem : elems) add(elem);
    }

        // Returns true/false depending on if the priority queue is empty
        public boolean isEmpty() {
        return heapSize == 0;
    }

        // Clears everything inside the heap, O(n)
        public void clear() {
        for (int i = 0; i < heapCapacity; i++) heap.set(i, null);
        heapSize = 0;
    }

        // Return the size of the heap
        public int size() {
        return heapSize;
    }
    public T peek()
    {
        if(isEmpty()) return null;
        return heap.get(0);
    }
    public T poll()
    {
        if(isEmpty()) return null;
        return removeAt(0);
    }
    public boolean contains(T elem)
    {
        if(elem ==null) return false;
        else
        {
            return map.containsKey(elem);
        }
    }
    public void add(T elem)
    {
        if(elem==null) throw new IllegalArgumentException();
        if(heapSize<heapCapacity)
        {
            heap.set(heapSize,elem);
        }
        else
        {
            heap.add(elem);
            heapCapacity++;
        }
        mapAdd(elem,heapSize);
        swim(heapSize);
        heapSize++;
    }
    private boolean less(int i,int j)
    {
        T node1 = heap.get(i);
        T node2 = heap.get(j);
        return node1.compareTo(node2)<=0;
    }
    //from bottom node to top O(log(n))
    private void swim(int k)
    {
        int parent = (k-1)/2;
        while(k>0&&less(k,parent))
        {
            swap(parent,k);
            k = parent;
            parent = (k-1)/2;
        }
    }
    private void sink(int k)
    {
        while(true)
        {
            int left = 2*k+1;
            int right = 2*k+2;
            int smallest = 0;
            if(right<heapSize&&less(right,left))
            {
                smallest = right;
            }
            else if(left<heapSize&&less(left,right))
            {
                smallest = right;
            }
            if(left>=heapSize||right>=heapSize||less(k,smallest))
            {
                break;
            }
            else
            {
                swap(k,smallest);
                k = smallest;

            }
        }
    }
    private void swap(int i,int j)
    {
        T temp1 = heap.get(i);
        T temp2 = heap.get(j);
        heap.set(i,temp2);
        heap.set(j,temp1);
        mapSwap(temp1,temp2,i,j);
    }
    public boolean remove(T elem)
    {
        if(isEmpty()) return false;
        if(elem==null) return false;

        Integer index = mapGet(elem);
        if(index!=null) removeAt(index);
        return index!=null;
    }
    public T removeAt(int index)
    {
        if(isEmpty()) return null;
        heapSize--;
        T removeData = heap.get(index);
        swap(index,heapSize);
        heap.set(heapSize,null);
        mapRemove(removeData,heapSize);
        if(index==heapSize)
        {
            return removeData;
        }
        else
        {
            T elem = heap.get(index);
            sink(index);
            if(heap.get(index) == elem)
            {
                swim(index);
            }
            return removeData;

        }

    }
    //recursively check if this is a minHeap
    public boolean isMinHeap(int k)
    {
        if(k>=heapSize) return true;
        int left = 2*k+1;
        int right = 2*k+2;
        if(left<heapSize&&!less(k,left))
        {
            return false;
        }
        if(right<heapSize&&!less(k,right))
        {
            return false;
        }
        return isMinHeap(left)&&isMinHeap(right);
    }
    private void mapAdd(T value,int index)
    {
        TreeSet<Integer> set = map.get(value);
        if(set==null)
        {
            set = new TreeSet<>();
            set.add(index);
            map.put(value,set);
        }
        else
        {
            set.add(index);
        }
    }
    //O(log(n))
    private void mapRemove(T value,int index)
    {
        TreeSet<Integer> set = map.get(value);
        set.remove(index);
        if(set.size()==0)
        {
            map.remove(value);
        }
    }
    private Integer mapGet(T value)
    {
        TreeSet<Integer> set = map.get(value);
        if(set!=null)
        {
            return set.last();
        }
        return null;
    }
    private void mapSwap(T elem1,T elem2,int i,int j)
    {
        TreeSet<Integer> set1 = map.get(elem1);
        TreeSet<Integer> set2 = map.get(elem2);
        set1.remove(i);
        set2.remove(j);
        set1.add(j);
        set2.add(i);

    }
    public String toString()
    {
        return heap.toString();
    }

}