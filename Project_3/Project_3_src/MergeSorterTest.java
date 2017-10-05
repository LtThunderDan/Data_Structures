import org.junit.*;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MergeSorterTest
{
  IMergeSorter sorter;

  // helper method to help check if two lists are the same.
  // notice the generic method syntax of including <T> before the reutrn type.
  private static <T> void assertListsEqual(List<T> a, List<T> b)
  {
    String assertError = "Expected sorted list " + b + " but recieved list " + a +".";
    Assert.assertEquals(assertError, a.size(), b.size());
    for(int i = 0; i < a.size(); i++)
    {
      Assert.assertEquals(assertError, a.get(i),b.get(i));
    }
  }

  // Mosts tests just make sure the list is sorted with the correct number of
  // steps.  This helper method does that.
  private static <T extends Comparable<T>> void testSort(ISorter sorter, List<T> list, int expected_steps)
  {
    // copy the original list into a test list and a control list.  the test
    // list is sorted using student code, the control is sorted with the java
    // collections version.
    String sorter_name = sorter.getClass().getName();
    List<T> testList = new ArrayList<T>(list);
    List<T> controlList = new ArrayList<T>(list);
    int steps = sorter.sort(testList);
    Collections.sort(controlList);

    // ensure the test and controls are the same and that the steps is correct.
    assertListsEqual(testList, controlList);
    Assert.assertEquals(expected_steps, steps);
  }

  // Mosts tests just make sure the list is sorted with the correct number of
  // steps.  This helper method does that with a comparator.
  private static <T> void testSort(ISorter sorter, Comparator<T> comparator, List<T> list, int expected_steps)
  {
    // copy the original list into a test list and a control list.  the test
    // list is sorted using student code, the control is sorted with the java
    // collections version.
    String sorter_name = sorter.getClass().getName();
    List<T> testList = new ArrayList<T>(list);
    List<T> controlList = new ArrayList<T>(list);
    int steps = sorter.sort(testList, comparator);
    Collections.sort(controlList, comparator);

    // ensure the test and controls are the same and that the steps is correct.
    assertListsEqual(testList, controlList);
    Assert.assertEquals(expected_steps, steps);
  }

  @Before
  public void SetUp()
  {
    sorter = new MergeSorter();
  }

  @Test
  public void testInstantiation()
  { /* Test set up*/ }

  @Test
  public void testSortOneElementList()
  {
    testSort(sorter, Arrays.asList(1), 0);
  }

  @Test
  public void testSortThreeElementList()
  {
    testSort(sorter, Arrays.asList(3,1,2), 5);
  }

  @Test
  public void testSortTenElementList()
  {
    List<Integer>testList = Arrays.asList(8, 2, 10, 9, 6, 7, 3, 1, 4, 5);
    testSort(sorter, testList, 34);
  }

  private class ReverseComparator<T extends Comparable<T>> implements Comparator<T>
  {
    public int compare(T a, T b)
    {
      return -a.compareTo(b);
    }
  }

  @Test
  public void testSortOneElementListReverse()
  {
    testSort(sorter, new ReverseComparator<Integer>(), Arrays.asList(1), 0);
  }

  @Test
  public void testSortThreeElementListReverse()
  {
    testSort(sorter, new ReverseComparator<Integer>(), Arrays.asList(3,1,2), 5);
  }

  @Test
  public void testSortTenElementListReverse()
  {
    List<Integer>testList = Arrays.asList(8, 2, 10, 9, 6, 7, 3, 1, 4, 5);
    testSort(sorter, new ReverseComparator<Integer>(), testList, 34);
  }

  @Test
  public void testInternalCall()
  {
    List<Integer>testList = Arrays.asList(2, 3, 5, 9, 5, 8, 9, 2, 1, 3);
    int steps = sorter.mergeSortRecursive(testList, 4, 8, new ReverseComparator<Integer>());

    List<Integer>controlList = Arrays.asList(2, 3, 5, 9, 9, 8, 5, 2, 1, 3);
    assertListsEqual(testList, controlList);
    Assert.assertEquals(8, steps);
  }

  @Test
  public void testInternalCall2()
  {
    List<Integer>testList = Arrays.asList(2, 3, 5, 9, 5, 8, 9, 2, 1, 3);
    int steps = sorter.mergeSortRecursive(testList, 2, 8, new ReverseComparator<Integer>());

    List<Integer>controlList = Arrays.asList(2, 3, 9, 9, 8, 5, 5, 2, 1, 3);
    assertListsEqual(testList, controlList);
    Assert.assertEquals(16, steps);
  }


}
