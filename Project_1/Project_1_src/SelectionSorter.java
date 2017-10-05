import java.util.Comparator;
import java.util.List;

/**
 * Created by Daniel on 10/4/2016.
 */
public class SelectionSorter implements ISorter{
  /*  private long[] a;
    private int nElems;

    public SelectionSorter(int max){
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

    public void SelectionSort(){
        int out, in, min;

        for(out=0; out<nElems -1; out++){
            min = out;
            for(in=out+1; in<nElems; in++)
                if(a[in]< a[min])
                    min = in;
            swap(out, min);
        }
    }

    private void swap(int one, int two){
        long temp = a[one];
        a[one] = a[two];
        a[two] = temp;
    }
*/
    @Override
    public <T extends Comparable<T>> int sort(List<T> list) {
        int out, in, min, numswap = 0;
        int nElems = list.size();
        for(out=0; out<nElems -1; out++) {
            min = out;
            for(in=out+1; in<nElems; in++) {
                if (list.get(in).compareTo(list.get(min)) == -1) {
                    min = in;
                }
            }
            if(list.get(out).compareTo(list.get(min)) != 0) {
                T temp = list.get(out);
                list.set(out, list.get(min));
                list.set(min, temp);
                numswap++;
            }
        }
        return numswap;
    }

    @Override
    public <T> int sort(List<T> list, Comparator<T> comparator) {
        int out, in, min, numswap = 0;
        int nElems = list.size();
        for(out=0; out<nElems -1; out++) {
            min = out;
            for(in=out+1; in<nElems; in++) {
                if (comparator.compare(list.get(in),(list.get(min))) == -1) {
                    min = in;
                }
            }
            if(comparator.compare(list.get(out),(list.get(min))) != 0) {
                T temp = list.get(out);
                list.set(out, list.get(min));
                list.set(min, temp);
                numswap++;
            }
        }
        return numswap;
    }
}
