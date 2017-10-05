import java.util.List;
import java.util.Comparator;

public interface IMergeSorter extends ISorter
{
    ////////////////////////////////////////////////////////////////////////////
    // Public interface

    // Performs the mergesort operation on a list, modifying the list passed in.
    // Hint: build a simple, nested comparator and call
    //   sort(List<T>,Comparator<T>) to do the real work.
    // Returns the number of steps required to perform the sort using the
    //   algorithm.  Steps are defined differently for different algorithms,
    //   see the assignment for details.
    <T extends Comparable<T>> int sort(List<T> list);

    // Performs the mergesort operation on array, modifying the array passed in.
    // Items are compared based on the given comparator.
    // Hint: do the work in mergeSortRecursive and call that method.
    // Returns the number of steps required to perform the sort using the
    //   algorithm.  Steps are defined differently for different algorithms,
    //   see the assignment for details.
    <T> int sort(List<T> list, Comparator<T> comparator);


    ////////////////////////////////////////////////////////////////////////////
    // Private interface
    //
    // Normally, everything past this point would be private.  For the sake of
    // testing in CS249, however, all of these must be provided as public.

    // A recursive call that sorts an array within the index range [from, to).
    // Hint: this method will need to call itself to sort the entire array.
    // Hint: remember what the 'base case' is and if you encounter it, do not
    //   continue the recursion.
    // Returns the number of steps required to perform the sort using the
    //   algorithm.  Steps are defined differently for different algorithms,
    //   see the assignment for details.
    <T> int mergeSortRecursive(List<T> list, int from, int to, Comparator<T> comparator);

}
