import java.util.List;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Daniel on 10/4/2016.
 */
public class SortTimerClass {
    public static void main(String[] args){
        ISorter b = new BubbleSorter();
        ISorter s = new SelectionSorter();
        ISorter i = new InsertionSorter();

        List<Integer> list20 = new ArrayList<>();
        List<Integer> list100 = new ArrayList<>();
        List<Integer> list500 = new ArrayList<>();
        List<Integer> list2500 = new ArrayList<>();
        Random randomize = new Random();

        for(int lv=0; lv<20; lv++) {
            list20.add(randomize.nextInt(20));
        }
        for(int lv=0; lv<100; lv++) {
            list100.add(randomize.nextInt(100));
        }
        for(int lv=0; lv<500; lv++) {
            list500.add(randomize.nextInt(500));
        }
        for(int lv=0; lv<2500; lv++) {
            list2500.add(randomize.nextInt(2500));
        }

        double starttime = System.currentTimeMillis();
        s.sort(list20);
        double endtime = System.currentTimeMillis();
        System.out.println("Selection sort for 20");
        System.out.println(endtime-starttime);


        starttime = System.currentTimeMillis();
        s.sort(list100);
        endtime = System.currentTimeMillis();
        System.out.println("Selection sort for 100");
        System.out.println(endtime-starttime);


        starttime = System.currentTimeMillis();
        s.sort(list500);
        endtime = System.currentTimeMillis();
        System.out.println("Selection sort for 500");
        System.out.println(endtime-starttime);


        starttime = System.currentTimeMillis();
        s.sort(list2500);
        endtime = System.currentTimeMillis();
        System.out.println("Selection sort for 2500");
        System.out.println(endtime-starttime);


        starttime = System.currentTimeMillis();
        i.sort(list20);
        endtime = System.currentTimeMillis();
        System.out.println("Insertion sort for 20");
        System.out.println(endtime-starttime);


        starttime = System.currentTimeMillis();
        i.sort(list100);
        endtime = System.currentTimeMillis();
        System.out.println("Insertion sort for 100");
        System.out.println(endtime-starttime);


        starttime = System.currentTimeMillis();
        i.sort(list500);
        endtime = System.currentTimeMillis();
        System.out.println("Insertion sort for 500");
        System.out.println(endtime-starttime);


        starttime = System.currentTimeMillis();
        i.sort(list2500);
        endtime = System.currentTimeMillis();
        System.out.println("Insertion sort for 2500");
        System.out.println(endtime-starttime);


        starttime = System.currentTimeMillis();
        b.sort(list20);
        endtime = System.currentTimeMillis();
        System.out.println("Bubble sort for 20");
        System.out.println(endtime-starttime);


        starttime = System.currentTimeMillis();
        b.sort(list100);
        endtime = System.currentTimeMillis();
        System.out.println("Bubble sort for 100");
        System.out.println(endtime-starttime);

        starttime = System.currentTimeMillis();
        b.sort(list500);
        endtime = System.currentTimeMillis();
        System.out.println("Bubble sort for 500");
        System.out.println(endtime-starttime);

        starttime = System.currentTimeMillis();
        b.sort(list2500);
        endtime = System.currentTimeMillis();
        System.out.println("Bubble sort for 2500");
        System.out.println(endtime-starttime);


    }
}
