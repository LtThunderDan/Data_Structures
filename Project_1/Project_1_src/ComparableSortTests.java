import org.junit.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

// Tests for after part 1 of the assignment is completed.
public class ComparableSortTests
{

  // helper function used to check if two lists contain the same objects.
  public static <T> boolean assertListsEqual(List<T> a, List<T> b)
  {
    if(a.size() != b.size()) return false;
    for(int i = 0; i < a.size(); i++)
    {
      if(a.get(i) != b.get(i)) return false;
    }
    return true;
  }


  // helper function to sort one list using the student code, another with
  // java's built in sort, and to compare.  Checks to ensure the steps are
  // some hardcoded value.
  public static <T extends Comparable> void testComparableSorter(List<T> list, ISorter sorter, String sorter_name, int expected_steps)
  {
        List<T> original_list = new ArrayList(list);
        List<T> control_list = new ArrayList(list);
        Collections.sort(control_list);

        int steps = sorter.sort(list);

        Assert.assertTrue(sorter_name+" should accurately sort the list " + original_list + " into " + control_list +" but instead sorted it into " + list,  assertListsEqual(list,control_list));
        Assert.assertEquals(sorter_name+" should be able to sort the list " + original_list + " in " + expected_steps + " steps, but instead it took "+ steps +" steps.",expected_steps,steps);

  }

  // First off, a test that should always pass once you compile, just to catch
  // odd runtime errors.
  @Test
  public void testCanInstantiateBubbleSorter()
  {
    ISorter sorter = new BubbleSorter();
  }

  /**
    * The following sections build lists of Integers (which are comparable)
    * and then compares those lists in different ways, using each of the 3
    * sorting algorithms described in the assignment text.
    */

  @Test
  public void testCanBubbleSortIntegerListA()
  {
    ISorter sorter = new BubbleSorter();
    List<Integer> list = new ArrayList();
    list.add(1);
    list.add(2);
    list.add(4);
    list.add(3);
    list.add(5);

    testComparableSorter(list, sorter, "BubbleSorter", 1);
  }

  @Test
  public void testCanBubbleSortIntegerListB()
  {
    ISorter sorter = new BubbleSorter();
    List<Integer> list = new ArrayList();
    list.add(5);
    list.add(4);
    list.add(3);
    list.add(2);
    list.add(1);

    testComparableSorter(list, sorter, "BubbleSorter", 10);
  }

  @Test
  public void testCanBubbleSortIntegerListC()
  {
    ISorter sorter = new BubbleSorter();
    List<Integer> list = new ArrayList();
    list.add(5);
    list.add(1);
    list.add(1);
    list.add(1);
    list.add(1);

    testComparableSorter(list, sorter, "BubbleSorter", 4);
  }

  @Test
  public void testCanInsertionSortIntegerListA()
  {
    ISorter sorter = new InsertionSorter();
    List<Integer> list = new ArrayList();
    list.add(1);
    list.add(2);
    list.add(4);
    list.add(3);
    list.add(5);

    testComparableSorter(list, sorter, "InsertionSorter", 1);
  }

  @Test
  public void testCanInsertionSortIntegerListB()
  {
    ISorter sorter = new InsertionSorter();
    List<Integer> list = new ArrayList();
    list.add(5);
    list.add(4);
    list.add(3);
    list.add(2);
    list.add(1);

    testComparableSorter(list, sorter, "InsertionSorter", 10);
  }

  @Test
  public void testCanInsertionSortIntegerListC()
  {
    ISorter sorter = new InsertionSorter();
    List<Integer> list = new ArrayList();
    list.add(5);
    list.add(1);
    list.add(1);
    list.add(1);
    list.add(1);

    testComparableSorter(list, sorter, "InsertionSorter", 4);
  }

  @Test
  public void testCanSelectionSortIntegerListA()
  {
    ISorter sorter = new SelectionSorter();
    List<Integer> list = new ArrayList();
    list.add(1);
    list.add(2);
    list.add(4);
    list.add(3);
    list.add(5);

    testComparableSorter(list, sorter, "SelectionSorter", 1);
  }

  @Test
  public void testCanSelectionSortIntegerListB()
  {
    ISorter sorter = new SelectionSorter();
    List<Integer> list = new ArrayList();
    list.add(5);
    list.add(4);
    list.add(3);
    list.add(2);
    list.add(1);

    testComparableSorter(list, sorter, "SelectionSorter", 2);
  }

  @Test
  public void testCanSelectionSortIntegerListC()
  {
    ISorter sorter = new SelectionSorter();
    List<Integer> list = new ArrayList();
    list.add(5);
    list.add(1);
    list.add(1);
    list.add(1);
    list.add(1);

    testComparableSorter(list, sorter, "SelectionSorter", 4);
  }

}
