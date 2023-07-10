import java.util.*;
class Entry<K,V> {
    int hash;
    K key;
    V value;
    public Entry(K key,V value)
    {
        this.key =key;
        this.value = value;
        this.hash = key.hashCode();
    }


    public boolean equals(Entry<K,V> other) {
        if(hash!=other.hash) return false;
        return key.equals(other.key);
    }
    public String toString()
    {
        return key+" => "+value;
    }
}
public class HashTableSeperateChaining <K,V>
{
    private static final int DEFAULT_CAPACITY = 3;
    private static final double DEFAULT_LOAD_FACTOR = 0.75;
    private double maxLoadFactor;
    private int capacity,threshold, size =0;
    private LinkedList<Entry<K,V>>[] table;
    public HashTableSeperateChaining()
    {
        this(DEFAULT_CAPACITY,DEFAULT_LOAD_FACTOR);
    }
    public HashTableSeperateChaining(int capacity)
    {
        this(capacity,DEFAULT_LOAD_FACTOR);

    }
    public HashTableSeperateChaining(int capacity,double maxLoadFactor)
    {
        if(capacity<0) throw new IllegalArgumentException();
        else if(maxLoadFactor<=0||Double.isNaN(maxLoadFactor)||Double.isInfinite(maxLoadFactor))
            throw new IllegalArgumentException();
        this.maxLoadFactor = maxLoadFactor;
        this.capacity = Math.max(DEFAULT_CAPACITY,capacity);
        threshold = (int) (this.capacity*maxLoadFactor);
        table = new LinkedList[this.capacity];
    }
    public int getSize()
    {
        return size;
    }
    public boolean isEmpty()
    {
        return size==0;
    }
    public int normalizeIndex(int keyHash)
    {
        return (keyHash&0x7FFFFFFF)%capacity;
    }
    public void clear()
    {
        Arrays.fill(table,null);
        size=0;
    }
    public boolean hasKey(K key)
    {
        int bucketIndex = normalizeIndex(key.hashCode());
        return bucketSeekEntry(bucketIndex,key)!=null;
    }
    public V insert(K key,V value)
    {
        if(key==null)
        {
            throw new IllegalArgumentException();
        }
        Entry<K,V> newEntry = new Entry<>(key,value);
        int bucketIndex = normalizeIndex(newEntry.hash);
        return bucketInsertEntry(bucketIndex,newEntry);

    }
    public V get(K key)
    {
        if(key == null) throw new IllegalArgumentException();
        int bucketIndex = normalizeIndex(key.hashCode());
        Entry<K,V> entry = bucketSeekEntry(bucketIndex,key);
        if(entry!=null) return entry.value;
        else return null;
    }
    public V remove(K key)
    {
        if(key == null) throw new IllegalArgumentException();
        int bucketIndex = normalizeIndex(key.hashCode());
        return bucketRemoveEntry(bucketIndex,key);

    }
    private Entry<K,V> bucketSeekEntry(int bucketIndex,K key)
    {
        if(key ==null) return null;
        LinkedList<Entry<K,V>> bucket = table[bucketIndex];
        if(bucket==null) return null;
        for(Entry<K,V> entry: bucket)
        {
            if(entry.key.equals(key))
            {
                return entry;
            }
        }
        return null;
    }
    private V bucketInsertEntry(int bucketIndex, Entry<K,V> newEntry)
    {
        LinkedList<Entry<K,V>> bucket = table[bucketIndex];
        if(bucket==null)
        {
            table[bucketIndex] = bucket = new LinkedList<>();
        }
        Entry<K,V> existentEntry = bucketSeekEntry(bucketIndex, newEntry.key);
        if(existentEntry==null)
        {
            bucket.add(newEntry);
            size++;
            if(size>threshold) resizeTable();
            return null;
        }
        else
        {
            V oldVal = existentEntry.value;
            existentEntry.value = newEntry.value;
            return oldVal;
        }
    }
    private V bucketRemoveEntry(int bucketIndex, K key)
    {
        Entry<K,V> entry = bucketSeekEntry(bucketIndex,key);
        if(entry!=null)
        {
            LinkedList<Entry<K,V>> subLink = table[bucketIndex];
            subLink.remove(entry);
            --size;
            return entry.value;
        } else return null;
    }
    private void resizeTable()
    {
        capacity*=2;
        threshold =(int) (capacity*maxLoadFactor);
        LinkedList<Entry<K,V>>[] newTable = new LinkedList[capacity];
        for(int i=0;i< table.length;i++)
        {
            if(table[i]!=null)
            {
                for(Entry<K,V> entry:table[i])
                {
                    int bucketIndex = normalizeIndex(entry.hash);
                    LinkedList<Entry<K,V>> bucket = newTable[bucketIndex];
                    if(bucket==null) newTable[bucketIndex]= bucket = new LinkedList<>();
                    bucket.add(entry);
                }
                table[i].clear();
                table[i] = null;
            }
        }
        table = newTable;
    }
    public List <K>keys()
    {
        List<K> keys = new ArrayList<>(size);
        for(LinkedList<Entry<K,V>> subLink: table)
        {
            for(Entry<K,V> entry: subLink)
            {
                keys.add(entry.key);
            }
        }
        return keys;
    }
    public List <V>values()
    {
        List<V> values = new ArrayList<>(size);
        for(LinkedList<Entry<K,V>> subLink: table)
        {
            for(Entry<K,V> entry: subLink)
            {
                values.add(entry.value);
            }
        }
        return values;
    }


}