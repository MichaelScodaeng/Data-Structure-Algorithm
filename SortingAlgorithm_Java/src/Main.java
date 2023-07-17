import java.util.Arrays;

public class Main {
    public static int[] insertion_sort(int[] ar,boolean greater)
    {
        int[] ret = new int[ar.length];
        for(int i=0;i<ar.length;i++)
        {
            ret[i] = ar[i];
        }

        if(greater)
        {
            for(int i=0;i<ret.length;i++)
            {
                int j = i-1;
                while(j>=0 &&ret[j+1]>ret[j])
                {
                    int tmp = ret[j+1];
                    ret[j+1] = ret[j];
                    ret[j] = tmp;
                    j-=1;
                }
            }

        }

        else
        {
            for(int i=0;i<ret.length;i++)
            {
                int j = i-1;
                while(j>=0 &&ret[j+1]<ret[j])
                {
                    int tmp = ret[j+1];
                    ret[j+1] = ret[j];
                    ret[j] = tmp;
                    j-=1;
                }
            }
        }
        return ret;
    }
    public static void main(String[] args)
    {
        int[] ar = {1, 4, 6, 41, 3, 10, 36, 4, 8, 4, 3};
        System.out.println(Arrays.toString(insertion_sort(ar, false)));
        System.out.println(Arrays.toString(insertion_sort(ar, true)));
    }
}