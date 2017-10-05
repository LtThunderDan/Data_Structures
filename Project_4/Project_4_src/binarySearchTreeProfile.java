import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Created by Daniel on 11/22/2016.
 */
public class binarySearchTreeProfile {
        public static void main(String[] args){
            //creating a set to contain some random numbers.
            Set<Integer> randomNumbers = new HashSet<Integer>();
            //create a linear tree, ascending order.
            BinarySearchTree forLinearTree = new BinarySearchTree();
            //create a random tree, random order.
            BinarySearchTree forRandomTree = new BinarySearchTree();
            //create long ints for start and end times, in nano.
            long start;
            long end;
            //used for creating random numbers.
            Random rand = new Random();

            //insert numbers into linear tree, in ascending order.
            for(int i=1; i<=10000; i++){
                forLinearTree.put(i, ("ele" + i));
            }

            //insert numbers into random tree, in random order.
            while (forRandomTree.size() != 10000){
                //create a random number between 1-10,000.
                int randNum = rand.nextInt(10000) + 1;
                //checking for any repeats.
                if (!randomNumbers.contains(randNum)) {
                    //add the random num.
                    randomNumbers.add(randNum);
                    forRandomTree.put(randNum, ("ele" + randNum));
                }
            }

            //for loop used to print out all 10,000 elements and how long it takes.
            for(int i=1; i<=forLinearTree.size(); i++) {
                start = System.nanoTime();
                forLinearTree.get(i);
                end = System.nanoTime();
                System.out.print("key #: " + i + " took: " + (end - start) + " nanoseconds\n");
            }

            //for loop used to print out all 10,000 elements and how long it takes.
            for(int i=1; i<=forRandomTree.size(); i++) {
                start = System.nanoTime();
                forRandomTree.get(i);
                end = System.nanoTime();
                System.out.print("key #: " + i + " took: " + (end - start) + " nanoseconds\n");
            }
        }
    }

