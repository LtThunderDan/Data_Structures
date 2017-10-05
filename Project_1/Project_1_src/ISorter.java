import java.util.List;
import java.util.Comparator;

public interface ISorter
{
  /*
  *  Sorts the given list in place.  While sorting count all of the operations
  *  performed in the process and return that value at the end of the method.
  *  operations are defined differently for different types of sorts.  See the
  *  project description to see which sorts consider which operations.
  *
  *  Implement this method during step 1.
  */
  <T extends Comparable<T>> int sort(List<T> list);

  /*
  *  Sorts the given list in place.  Return the counted operation just as was
  *  done for the previous sort method.
  *
  *  Implement this method during step 4.
  */
  <T> int sort(List<T> list, Comparator<T> comparator);


}
