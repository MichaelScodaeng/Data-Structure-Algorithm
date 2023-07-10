import java.util.ArrayList;
import java.util.List;

public class HashTableQuadraticProbing <K,V>{
    private double loadFactor;
    private int capacity,threshold,modificationCount = 0;
    private int usedBuckets, keyCount = 0;
    private K[] keyTable;
    private V[] valueTable;
    private boolean containsFlag = false;
    private final K TOMBSTONE = (K)(new Object());
    private static final int DEFAULT_CAPACITY = 8;
    private static final double DEFAULT_LOAD_FACTOR = 0.45;
    public HashTableQuadraticProbing()
    {
        this(DEFAULT_CAPACITY,DEFAULT_LOAD_FACTOR);
    }
    public HashTableQuadraticProbing(int capacity)
    {
        this(capacity,DEFAULT_LOAD_FACTOR);
    }
    public HashTableQuadraticProbing(int capacity, double loadFactor)
    {
        if(capacity<0)
        {
            throw new IllegalArgumentException();
        }
        if(loadFactor<=0||Double.isNaN(loadFactor)||Double.isInfinite(loadFactor))
        {
            throw new IllegalArgumentException();
        }
        this.loadFactor = loadFactor;
        this.capacity = Math.max(next2Power(capacity),DEFAULT_CAPACITY);
        threshold = (int)(this.capacity*loadFactor);
        keyTable =(K[]) (new Object[capacity]);
        valueTable =(V[]) (new Object[capacity]);

    }
    private static int next2Power(int n)
    {
        return Integer.highestOneBit(n)<<1;

    }
    private static int P(int x)
    {
        return (x*x+x)>>1;
    }
    private int normalizeIndex(int keyHash)
    {
        return (keyHash&0x7FFFFFFF)% capacity;
    }
    public void clear()
    {
        for(int i=0;i<capacity;i++)
        {
            keyTable[i]=null;
            valueTable[i]=null;
        }
        keyCount = usedBuckets = 0;
        modificationCount++;
    }
    public int size()
    {
        return keyCount;
    }
    public boolean isEmpty()
    {
        return keyCount==0;
    }
    public V insert(K key,V val)
    {
        if(key==null) throw new IllegalArgumentException();
        if(usedBuckets>=threshold) resizeTable();
        int hash = normalizeIndex(key.hashCode());
        int i=hash;
        int j = -1;
        int x =1;
        do {
            if(keyTable[i] == TOMBSTONE)
            {
                if(j==-1) j=i;
            }
            else if(keyTable[i]!=null)
            {
                if(keyTable[i].equals(key))
                {
                    V oldValue = valueTable[i];
                    if(j==-1)
                    {
                        valueTable[i]=val;
                    }
                    else
                    {
                        keyTable[i] = TOMBSTONE;
                        valueTable[i] = null;
                        keyTable[j] = key;
                        valueTable[j] = val;
                    }
                    modificationCount++;
                    return oldValue;

                }
                else
                {
                    if(j==-1)
                    {
                        usedBuckets++;
                        keyCount++;
                        keyTable[i] = key;
                        valueTable[i] = val;
                    }
                    else
                    {
                        keyCount++;
                        keyTable[j] =key;
                        valueTable[j]= val;

                    }
                    modificationCount++;
                    return null;
                }
            }
            i = normalizeIndex(hash+P(x++));
        }while(true);
    }
    public boolean hasKey(K key)
    {
        get(key);
        return containsFlag;
    }
    public V get(K key) {
        if (key == null) throw new IllegalArgumentException();
        int hash = normalizeIndex(key.hashCode());
        int i = hash;
        int j = -1;
        int x = 1;
        do {
            if (keyTable[i] == TOMBSTONE) {
                if (j == -1) j = i;
            } else if (keyTable[i] != null) {
                if (keyTable[i].equals(key)) {
                    containsFlag = true;

                    if (j != -1) {
                        // Swap key-values pairs at indexes i and j.
                        keyTable[j] = keyTable[i];
                        valueTable[j] = valueTable[i];
                        keyTable[i] = TOMBSTONE;
                        valueTable[i] = null;
                        return valueTable[j];
                    } else {
                        return valueTable[i];
                    }

                } else {
                    containsFlag = false;
                    return null;
                }
            }
            i = normalizeIndex(hash + P(x++));
        } while (true);
    }
    public V remove(K key)
    {
        if(key==null) throw new IllegalArgumentException();
        int hash = normalizeIndex(key.hashCode());
        int i=hash;
        int x=1;
        for(;;i = normalizeIndex(hash+P(x++)))
        {
            if(keyTable[i]==TOMBSTONE) continue;
            if(keyTable[i]==null) return null;
            if(keyTable[i].equals(key))
            {
                keyCount--;
                modificationCount--;
                V oldValue = valueTable[i];
                keyTable[i] = TOMBSTONE;
                valueTable[i] = null;
                return oldValue;
            }
        }
    }
    private void resizeTable()
    {
        capacity*=2;
        threshold = (int) (capacity*loadFactor);
        K[] oldKeyTable = (K[]) new Object[capacity];
        V[] oldValueTable = (V[]) new Object[capacity];

        K[] keyTableTmp = keyTable;
        keyTable = oldKeyTable;
        oldKeyTable = keyTableTmp;

        V[] valueTableTmp = valueTable;
        valueTable = oldValueTable;
        oldValueTable = valueTableTmp;
        keyCount = usedBuckets = 0;
        for(int i=0;i<oldKeyTable.length;i++)
        {
            if(oldKeyTable[i]!=null && oldKeyTable[i]!=TOMBSTONE)
            {
                insert(oldKeyTable[i],oldValueTable[i]);
            }
            oldKeyTable[i] = null;
            oldValueTable[i] = null;
        }
    }
    public List<K> keys()
    {
        List<K> keys = new ArrayList<>();
        for(int i=0;i<capacity;i++)
        {
            if(keyTable[i]!=null && keyTable[i]!=TOMBSTONE)
            {
                keys.add(keyTable[i]);
            }
        }
        return keys;
    }
    public List<K> values()
    {
        List<K> values = new ArrayList<>();
        for(int i=0;i<capacity;i++)
        {
            if(keyTable[i]!=null && keyTable[i]!=TOMBSTONE)
            {
                values.add(keyTable[i]);
            }
        }
        return values;
    }

}