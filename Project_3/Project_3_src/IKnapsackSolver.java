public interface IKnapsackSolver
{
    ////////////////////////////////////////////////////////////////////////////
    // Public interface

    // This class contains the data structure for the possible items that could
    // be placed in the knapsack.
    // Do not copy this into your KnapsackSolver solution.  Instead reference
    //   the IKnapsackSolver.Item class.
    public static class Item
    {
        private double weight, value;

        public Item(double weight, double value)
        {
            this.weight = weight;
            this.value = value;
        }

        public double getWeight() { return weight; }
        public double getValue() { return value; }

    }

    // Solves the 0/1 knapsack problem for a specific capacity and set of
    // possible items.  If there are multiple optimal configuration, any of
    // them may be returned.
    // Returns a set of booleans the same length as items, where a True means
    //   the item at that index should be included.
    // Hint: Do the work in knapsackRecursive() and call that method.
    boolean[] knapsack(double capacity, Item[] items);


    ////////////////////////////////////////////////////////////////////////////
    // Private interface
    //
    // Normally, everything past this point would be private.  For the sake of
    // testing in CS249, however, all of these must be provided as public.

    // Solves the knapsack problem given a capacity, set of possible items, and
    // a set of items already determined to be included as prior.
    // Returns a set of booleans the same length as items, where a True means
    //   the item at that index should be included.
    // Hint: This method should call itself with different possibilites for
    //   prior to break down the problem.
    // Hint: Consider your base case.  When should you stop recursing?
    // Hint: Do not modify prior or items to avoid altering intermediate
    //   solutions.
    boolean[] knapsackRecursive(double capacity, Item[] items, boolean[] prior);

}
