import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

/**
 * Created by Daniel on 11/8/2016.
 */
public class MergeSorter <T extends Comparable> implements IMergeSorter {
    //for mergeSortRecursive
    private int step;
    //for internal calls
    public int ogTo = 0;
    public int ogFrom = 0;

    //ArrayList<T>
    //From - To(list.size)
    //helper function - Merge(Left List, Right List, Original List, Comparator)
    @Override
    public <T extends Comparable<T>> int sort(List<T> list) {
        //create a comparator, for other sort
        Comparator<T> temp = new Comparator<T>() {
            @Override
            public int compare(T o1, T o2) {
                if(o1.compareTo(o2) >= 1) {return 1;}
                else if(o1.compareTo(o2) <= -1) {return -1;}
                else {return 0;}
            }
        };
        return sort(list, temp);
    }

    @Override
    public <T> int sort(List<T> list, Comparator<T> comparator) {
        return mergeSortRecursive(list, 0, list.size(), comparator);
    }

    @Override
    public <T> int mergeSortRecursive(List<T> list, int from, int to, Comparator<T> comparator) {
        //base case, if list size is equal to 1, we have hit the end.
        if(list.size() == 1) {return 0;}

        //handling of the internal call, if nothing has been touched.
        if(step == 0 && from != 0 && to != list.size()){
            setterFrom(from);
            setterTo(to);
            List<T> copy = new ArrayList<T>();
            for(int i = ogFrom; i <= ogTo; i++){
                copy.add(list.get(i));
            }

            //divide the lists into 2, for the right and left lists
            int middle = copy.size()/2;

            //make your new left and right lists to hold objects.
            List<T> leftList = new ArrayList<T>();
            List<T> rightList = new ArrayList<T>();


            for(int j = 0; j < middle; j++){
                leftList.add(copy.get(j));
            }
            for(int k = middle; k < copy.size()-1; k++ ){
                rightList.add(copy.get(k));
            }

            step += leftList.size();
            step += rightList.size();

            mergeSortRecursive(leftList, 0, leftList.size(), comparator);
            mergeSortRecursive(rightList, 0, rightList.size(), comparator);

            Merge(leftList, rightList, copy, comparator);

            int counter = 0;
            for(int m = ogFrom; m <= ogTo; m++){
                list.set(m, copy.get(counter));
                counter++;
            }
            return step;
        }

        //merge sort recursive step.
        else{
            step += list.size();

            //make your new left and right lists to hold objects.
            List<T> leftList = new ArrayList<T>();
            List<T> rightList = new ArrayList<T>();

            //divide the lists into 2, for the right and left lists
            int middle = (list.size())/2;

            //filling the left lists with objects. starting at 0, ending at middle.
            for(int i = 0; i < middle; i++){
                leftList.add(list.get(i));
            }
            //filling the right lists with objects. starting at middle, ending at .size().
            for(int j = middle; j < list.size(); j++){
                rightList.add(list.get(j));
            }

            //call the recursion for all the left lists.
            mergeSortRecursive(leftList, from, to, comparator);
            //now call the recursion for all the right lists.
            mergeSortRecursive(rightList, from, to, comparator);
            //merge them
            Merge(leftList, rightList, list, comparator);
            //return each step taken.
            return step;
        }
    }

    public <T> void Merge(List<T> leftList, List<T> rightList, List<T> originalList, Comparator<T> comp){
        //pointers
        int left = 0;
        int right = 0;
        int merged = 0;

        //while pointers are still within bounds
        while(left < leftList.size() && right < rightList.size()){
            //if object to left is smaller then right
            if(comp.compare(leftList.get(left), rightList.get(right)) < 0){
                originalList.set(merged, leftList.get(left));
                left++;
            }
            //if object to left is larger or equal
            else{
                originalList.set(merged, rightList.get(right));
                right++;
            }
            merged++;
        }

        //if any objects are still remaining to the left, merge
        while(left < leftList.size()){
            originalList.set(merged, leftList.get(left));
            merged++;
            left++;
        }
        //if any objects are still remaining to the right, merge
        while(right < rightList.size()){
            originalList.set(merged, rightList.get(right));
            merged++;
            right++;
        }
    }

    //helper methods for internal calls.
    public void setterFrom(int from){this.ogFrom = from;}
    public void setterTo(int to){this.ogTo = to;}

    public static void main(String[] args){
        //to randomly create integers to throw into our arrays and sort.
        Random r = new Random();

        //creation of the mergeSorter to be used.
        MergeSorter<Integer> mergeSort = new MergeSorter<>();

        //creating the array to throw random numbers into and sort.
        //for 20,000 / 100,000 / 500,000 / 2,500,000
        ArrayList<Integer> arrOf20000 = new ArrayList<>();
        ArrayList<Integer> arrOf100000 = new ArrayList<>();
        ArrayList<Integer> arrOf500000 = new ArrayList<>();
        ArrayList<Integer> arrOf2500000 = new ArrayList<>();

        //lets populate that empty array with integers between 1 and 100.
        //for 20,000 / 100,000 / 500,000 / 2,500,000
        for(int i = 0; i < 20000; i++){
            arrOf20000.add(r.nextInt(100)+1);
        }

        for(int i = 0; i < 100000; i++){
            arrOf100000.add(r.nextInt(100)+1);
        }

        for(int i = 0; i < 500000; i++){
            arrOf500000.add(r.nextInt(100)+1);
        }

        for(int i = 0; i < 2500000; i++){
            arrOf2500000.add(r.nextInt(100)+1);
        }

        //start the clock, sort the array, get ending time, print it out.
        //for 20,000 / 100,000 / 500,000 / 2,500,000
        long start = System.nanoTime();
        mergeSort.sort(arrOf20000);
        long end = System.nanoTime();
        System.out.println("ArrayList of 20,000 took: " + (end - start) + " nano seconds.");

        start = System.nanoTime();
        mergeSort.sort(arrOf100000);
        end = System.nanoTime();
        System.out.println("ArrayList of 100,000 took: " + (end - start) + " nano seconds.");

        start = System.nanoTime();
        mergeSort.sort(arrOf500000);
        end = System.nanoTime();
        System.out.println("ArrayList of 500,000 took: " + (end - start) + " nano seconds.");

        start = System.nanoTime();
        mergeSort.sort(arrOf2500000);
        end = System.nanoTime();
        System.out.println("ArrayList of 2,500,000 took: " + (end - start) + " nano seconds.");

    }
}
