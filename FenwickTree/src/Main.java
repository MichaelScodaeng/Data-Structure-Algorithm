public class FenwickTree {
    private long[] fenwickTree;
    private int size;
    public FenwickTree(int sz)
    {
        fenwickTree = new long[sz+1];
        size = sz;
    }
    //values[0] is not used!!
    public FenwickTree(long[] values)
    {
        if(values==null) throw new IllegalArgumentException("Values should not be null");
        size = values.length;
        this.fenwickTree = values.clone();
        for(int i=1;i<size;i++)
        {
            int j = i+lsb(i);
            if(j<size)
            {
                fenwickTree[j]+=fenwickTree[i];
            }
        }
    }
    private int lsb (int i)
    {
        return i&-i;
    }

    public long prefixSum(int i)//[1,i]
    {
        long sum = 0L;
        while(i!=0)
        {
            sum+=fenwickTree[i];
            i&=~lsb(i); // i-=lsb(i)
        }
        return sum;
    }
    public long sum(int i,int j)
    {
        long sum = 0L;
        if(j<i) throw new IllegalArgumentException("j must not be lesser than i");
        return prefixSum(j)-prefixSum(i);
    }

    public void add(int i,long k) //add fenwickTree[i] = k
    {
        while(i<fenwickTree.length)
        {
            fenwickTree[i]+=k;
            i+=lsb(i);

        }
    }
    public void set(int i,long k) //add fenwickTree[i] = k
    {
        long value = sum(i,i);
        add(i,k-value);
    }

}