public class MinIndexBinaryHeap<T extends Comparable<T>> extends MinIndexDHeap<T>{
    public MinIndexBinaryHeap(int maxSize)
    {
        super(2,maxSize);
    }
}