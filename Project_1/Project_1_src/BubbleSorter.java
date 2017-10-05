import java.util.Comparator;
import java.util.List;

/**
 * Created by Daniel on 10/4/2016.
 */
public class BubbleSorter implements ISorter{
  /*
    private long[] a;
    private int nElems;

    public BubbleSorter(int max){
        a = new long[max];
        nElems = 0;
    }

    public void insert(long value){
        a[nElems] = value;
        nElems++;
    }

    public void display(){
        for(int j=0; j<nElems; j++)
            System.out.println(a[j] + " ");
        System.out.println(" ");
    }
    public void BubbleSort(){
        int out, in;

        for(out=nElems-1; out>1; out--)
            for (in=0; in<out; in++)
                if( a[in] > a[in+1])
                    swap(in, in+1);
    }

    private void swap(int one, int two){
        long temp = a[one];
        a[one] = a[two];
        a[two] = temp;
    }
*/
    @Override
    public <T extends Comparable<T>> int sort(List<T> list)
    {
        int out, in, numswap = 0;
        int nElems = list.size();
        for(out=nElems-1; out>=1; out--) {
            for (in = 0; in < out; in++) {
                if (list.get(in).compareTo(list.get(in + 1))  == 1) {
                    T temp = list.get(in);
                    list.set(in,list.get(in+1));
                    list.set(in+1, temp);
                    numswap++;
                }
            }
        }
        return numswap;
    }

    @Override
    public <T> int sort(List<T> list, Comparator<T> comparator){
        int out, in, numswap = 0;
        int nElems = list.size();
        for(out=nElems-1; out>=1; out--) {
            for (in = 0; in < out; in++) {
                if (comparator.compare(list.get(in),(list.get(in + 1)))  == 1) {
                    T temp = list.get(in);
                    list.set(in,list.get(in+1));
                    list.set(in+1, temp);
                    numswap++;
                }
            }
        }
        return numswap;
    }
}
