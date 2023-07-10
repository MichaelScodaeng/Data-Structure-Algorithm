class UnionFind {
    private int wholeSize;
    private int[] sz;
    //id[i] is the parent of i. if id[i] = i then it is root node
    private int[] id;
    private int numGroups;
    public UnionFind(int size)
    {
        if(size<=0) throw new IllegalArgumentException();
        numGroups=wholeSize = size;
        sz = new int[wholeSize];
        id = new int[wholeSize];
        //Creating Id for each node and marks them to be their own root nodes.
        for(int i=0;i<wholeSize;i++)
        {
            id[i] = i;
            sz[i] = 1;
        }
    }
    public int find(int p)
    {
        int root = p;
        //finding roots
        while(id[root]!=root)
        {
            root = id[root];
        }
        //path compression
        while (p!=root)
        {
            int next = id[p];
            id[p] = root;
            p = next;
        }
        return root;
    }
    public boolean isConnected(int p,int q)
    {
        return find(p)==find(q);
    }
    public int groupSize(int p)
    {
        return sz[find(p)];
    }
    public int numberOfGroups()
    {
        return numGroups;
    }
    public int size()
    {
        return wholeSize;
    }
    public void union(int p,int q)
    {
        int root1 = find(p);
        int root2  = find(q);
        if(root1==root2)
        {
            return;
        }
        else
        {
            if(sz[root1]>=sz[root2])
            {
                sz[root1]+=sz[root2];
                id[root2]=root1;
            }
            else
            {
                sz[root2]+=sz[root1];
                id[root1]=root2;
            }
            numGroups--;
        }
    }

}