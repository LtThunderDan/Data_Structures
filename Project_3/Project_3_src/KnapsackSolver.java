import java.util.Random;

/**
 * Created by Daniel on 11/8/2016.
 */
public class KnapsackSolver implements IKnapsackSolver {

    //calls the recursive knapsack, to solve.
    @Override
    public boolean[] knapsack(double capacity, Item[] items) {
        boolean[] prior = new boolean[0];
        return knapsackRecursive(capacity, items, prior);
    }

    //knapsackRecursive does all the work. Will be be finding if a certain boolean array is the answer to the problem.
    @Override
    public boolean[] knapsackRecursive(double capacity, Item[] items, boolean[] prior) {
        //base case
        if(prior.length == items.length){return prior;}

        //creating two arrays that may or may not be a solution.
        boolean[] temp = new boolean[prior.length + 1];
        boolean[] temp1 = new boolean[prior.length + 1];

        //filling the arrays with whatever prior was before.
        for(int i = 0; i < prior.length; i++){
            temp[i] = prior[i];
            temp1[i] = prior[i];
        }
        temp[prior.length] = true;

        //create arrays to store the solution after the recursion.
        boolean[] tempSolution = null;
        boolean[] temp1Solution = null;

        //safegaurd in case something happens.
        double tempValue = -1.0;
        double temp1Value = -1.0;

        //check to see if we can put things in the knapsack
        if(getBagWeight(temp, items) <= capacity){
            tempSolution = knapsackRecursive(capacity, items, temp);
            tempValue = getBagValue(tempSolution, items);
        }
        if(getBagWeight(temp1, items) <= capacity){
            temp1Solution = knapsackRecursive(capacity, items, temp1);
            temp1Value = getBagValue(temp1Solution, items);
        }

        //if tempValue is greater, we know that this solution might be getting closer to the solution.
        if(tempValue >= temp1Value){
            return tempSolution;
        }
        else{return temp1Solution;}
    }

    //helper methods
    //grabs the total weight of the current sack.
    public double getBagWeight(boolean[] prior, Item[] items){
        double weight = 0.0;
        for(int i = 0; i < prior.length; i++){
            if(prior[i] == true){
                weight += items[i].getWeight();
            }
        }
        return weight;
    }

    //same thing as weight, just gets the value.
    public double getBagValue(boolean[] prior, Item[] items){
        double value = 0.0;
        for(int i = 0; i < prior.length; i++){
            if(prior[i] == true){
                value += items[i].getValue();
            }
        }
        return value;
    }

    public static void main(String[] args){
        //creation of random variable to create random stuff for us.
        Random r = new Random();

        //creating the solver to call
        KnapsackSolver solve = new KnapsackSolver();

        //creating the array to throw our values into.
        //for 10 / 15 / 20/ 25
        IKnapsackSolver.Item[] sackOf10 = new IKnapsackSolver.Item[10];
        IKnapsackSolver.Item[] sackOf15 = new IKnapsackSolver.Item[15];
        IKnapsackSolver.Item[] sackOf20 = new IKnapsackSolver.Item[20];
        IKnapsackSolver.Item[] sackOf25 = new IKnapsackSolver.Item[25];

        //creating the random numbers to throw into the array.
        //for 10 / 15 / 20/ 25
        for(int i = 0; i < 10; i++){
            sackOf10[i] = new IKnapsackSolver.Item(r.nextInt(10), r.nextInt(10));
        }

        for(int i = 0; i < 15; i++){
            sackOf15[i] = new IKnapsackSolver.Item(r.nextInt(10), r.nextInt(10));
        }

        for(int i = 0; i < 20; i++){
            sackOf20[i] = new IKnapsackSolver.Item(r.nextInt(10), r.nextInt(10));
        }

        for(int i = 0; i < 25; i++){
            sackOf25[i] = new IKnapsackSolver.Item(r.nextInt(10), r.nextInt(10));
        }

        //start the clock, sort the array, get ending time, print it out.
        //for 10 / 15 / 20/ 25
        long start = System.nanoTime();
        solve.knapsack(30.0, sackOf10);
        long end = System.nanoTime();
        System.out.println("Time taken for 10 items of capacity 30: " + (end - start) + " nanoseconds.");

        start = System.nanoTime();
        solve.knapsack(45.0, sackOf15);
        end = System.nanoTime();
        System.out.println("Time taken for 15 items of capacity 45: " + (end - start) + " nanoseconds.");

        start = System.nanoTime();
        solve.knapsack(60.0, sackOf20);
        end = System.nanoTime();
        System.out.println("Time taken for 20 items of capacity 60: " + (end - start) + " nanoseconds.");

        start = System.nanoTime();
        solve.knapsack(75.0, sackOf25);
        end = System.nanoTime();
        System.out.println("Time taken for 25 items of capacity 75: " + (end - start) + " nanoseconds.");
    }
}
