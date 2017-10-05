import java.util.Comparator;
import java.util.List;

/**
 * Created by Daniel on 10/4/2016.
 */
public class InsertionSorter implements ISorter{
   /* private long[] a;
    private int nElems;

    public InsertionSorter(int max){
        a = new long[max];
        nElems = 0;
    }

    public void insert(long value){
        a[nElems] = value;
        nElems++;
    }

    public void display(){
        for(int j=0; j<nElems; j++)
            System.out.print(a[j] + " ");
        System.out.println(" ");
    }

    public void InsertionSort(){
        int in, out;

        for(out=1; out<nElems; out++){
            long temp = a[out];
            in = out;
            while(in>0 && a[in-1] >= temp){
                a[in] = a[in-1];
                --in;
            }
            a[in] = temp;
        }
    }
*/
    @Override
    public <T extends Comparable<T>> int sort(List<T> list) {
        int in, out, numswap = 0;
        int nElems = list.size();
        for(out=1; out<nElems; out++){
            T temp = list.get(out);
            in = out;
            while(in>0 && list.get(in-1).compareTo(temp) == 1){
                list.set(in,list.get(in-1));
                --in;
                numswap++;
            }
            list.set(in, temp);
        }
        return numswap;
    }

    @Override
    public <T> int sort(List<T> list, Comparator<T> comparator) {
        int in, out, numswap = 0;
        int nElems = list.size();
        for(out=1; out<nElems; out++){
            T temp = list.get(out);
            in = out;
            while(in>0 && comparator.compare(list.get(in-1),(temp)) == 1){
                list.set(in,list.get(in-1));
                --in;
                numswap++;
            }
            list.set(in, temp);
        }
        return numswap;
    }
}
