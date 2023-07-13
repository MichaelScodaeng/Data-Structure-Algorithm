import java.util.*;
public class MinIndexDHeap <T extends Comparable<T>>{
    private int sz; //actual size

    //constructing arrays
    private final int N; //final size of arrays;
    private final int D; // Degree of each nodes;
    public final Object[] values; // An array which stores value based on key-index;
    public final int[] pm; // An array which stores node location based on key-index;
    public final int[] im; //An array which stores key-index location based on node;
    public final int[] parents; //An array which stores key-index location based on node;
    public final int[] children; //An array which stores key-index location based on node;
    //pm[im[i]] = im[pm[i]] = i
    public MinIndexDHeap(int degreeSize,int maxSize)
    {
        D = Math.max(2,degreeSize);
        N = Math.max(D-1,maxSize);
        values = new Object[N] ;
        im = new int[N];
        pm = new int[N];
        parents = new int[N];
        children = new int[N];
        for(int i=0;i<N;i++)
        {
            parents[i] = (i-1)/D;
            children[i] = i*D+1;
            im[i] = pm[i] = -1;
        }
    }
    public int size() {
        return sz;
    }

    public boolean isEmpty() {
        return sz == 0;
    }
    public boolean contains(int ki) {
        keyInBoundsOrThrow(ki);
        return pm[ki] != -1;
    }
    public int peekMinKeyIndex() {
        isNotEmptyOrThrow();
        return im[0];
    }
    public int pollMinKeyIndex() {
        int minki = peekMinKeyIndex();
        delete(minki);
        return minki;
    }

    @SuppressWarnings("unchecked")
    public T peekMinValue() {
        isNotEmptyOrThrow();
        return (T) values[im[0]];
    }

    public T pollMinValue() {
        T minValue = peekMinValue();
        delete(peekMinKeyIndex());
        return minValue;
    }
    public void insert(int ki, T value) {
        if (contains(ki)) throw new IllegalArgumentException("index already exists");
        valueNotNullOrThrow(value);
        pm[ki] = sz;
        im[sz] = ki;
        values[ki] = value;
        swim(sz);
        sz++;
    }
    public T valueOf(int ki) {
        keyExistsOrThrow(ki);
        return (T) values[ki];
    }
    public T delete(int ki) {
        keyExistsOrThrow(ki);
        final int i = pm[ki];
        swap(i, sz);
        sink(i);
        swim(i);
        sz--;
        T value = (T) values[ki];
        values[ki] = null;
        pm[ki] = -1;
        im[sz] = -1;
        return value;
    }

    public T update(int ki, T value) {
        keyExistsAndValueNotNullOrThrow(ki, value);
        final int i = pm[ki];
        T oldValue = (T) values[ki];
        values[ki] = value;
        sink(i);
        swim(i);
        return oldValue;
    }
    public void decrease(int ki, T value) {
        keyExistsAndValueNotNullOrThrow(ki, value);
        if (less(value, values[ki])) {
            values[ki] = value;
            swim(pm[ki]);
        }
    }
    public void increase(int ki, T value) {
        keyExistsAndValueNotNullOrThrow(ki, value);
        if (less(values[ki], value)) {
            values[ki] = value;
            sink(pm[ki]);
        }
    }
    private void sink(int i) {
        for (int j = minChild(i); j != -1; ) {
            swap(i, j);
            i = j;
            j = minChild(i);
        }
    }

    private void swim(int i) {
        while (less(i, parents[i])) {
            swap(i, parents[i]);
            i = parents[i];
        }
    }
    private int minChild(int i) {
        int index = -1, from = children[i], to = Math.min(sz, from + D);
        for (int j = from; j < to; j++) if (less(j, i)) index = i = j;
        return index;
    }

    private void swap(int i, int j) {
        pm[im[j]] = i;
        pm[im[i]] = j;
        int tmp = im[i];
        im[i] = im[j];
        im[j] = tmp;
    }

    // Tests if the value of node i < node j
    @SuppressWarnings("unchecked")
    private boolean less(int i, int j) {
        return ((Comparable<? super T>) values[im[i]]).compareTo((T) values[im[j]]) < 0;
    }

    @SuppressWarnings("unchecked")
    private boolean less(Object obj1, Object obj2) {
        return ((Comparable<? super T>) obj1).compareTo((T) obj2) < 0;
    }

    @Override
    public String toString() {
        List<Integer> lst = new ArrayList<>(sz);
        for (int i = 0; i < sz; i++) lst.add(im[i]);
        return lst.toString();
    }

    /* Helper functions to make the code more readable. */

    private void isNotEmptyOrThrow() {
        if (isEmpty()) throw new NoSuchElementException("Priority queue underflow");
    }

    private void keyExistsAndValueNotNullOrThrow(int ki, Object value) {
        keyExistsOrThrow(ki);
        valueNotNullOrThrow(value);
    }

    private void keyExistsOrThrow(int ki) {
        if (!contains(ki)) throw new NoSuchElementException("Index does not exist; received: " + ki);
    }

    private void valueNotNullOrThrow(Object value) {
        if (value == null) throw new IllegalArgumentException("value cannot be null");
    }

    private void keyInBoundsOrThrow(int ki) {
        if (ki < 0 || ki >= N)
            throw new IllegalArgumentException("Key index out of bounds; received: " + ki);
    }
}
