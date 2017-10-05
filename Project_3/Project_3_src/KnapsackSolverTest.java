import org.junit.*;

public class KnapsackSolverTest
{
  IKnapsackSolver solver;

  // helper method for determing the weight of a knapsack set
  static private double weighKnapsack(
      boolean[] knapsack,
      IKnapsackSolver.Item[] items)
  {
    // start a counter
    double weight = 0;
    // iterate through the knapsack set
    for(int i = 0; i < knapsack.length; i++)
    {
      // reference the items list to add the weight
      if(knapsack[i]) weight += items[i].getWeight();
    }
    // reutrn the result
    return weight;
  }

  // helper method for turning the array into a human readable string for
  // error messages.
  static public String stringifyKnapsack(
      boolean[] knapsack,
      IKnapsackSolver.Item[] items)
  {
    // give a null response to prevent empty knapsacks and null knapsacks from
    // returning the same value.
    if(knapsack == null) return "null";
    // start our result
    String result = "[";
    // iterate through the knapsack
    for(int i = 0; i < knapsack.length; i++)
    {
      // if the item is present...
      if(knapsack[i])
      {
        // build a representation for the result.
        result += "(" + items[i].getWeight() + " lbs, $" +
            items[i].getValue() + ")";
      }
    }
    result += "]";
    return result;
  }

  static public void assertKnapsacksEqual(
      boolean[] knapsack,
      boolean[] control,
      IKnapsackSolver.Item[] items)
  {
    // build an error message in case any of our asserts fail.
    String assertError = "Expected " + stringifyKnapsack(control, items) +
        " but recieved " + stringifyKnapsack(knapsack, items) + ".";
    // ensure we have a knapsack
    Assert.assertNotNull(assertError, knapsack);
    // ensure the knapsack covers every item
    Assert.assertEquals(
        assertError,
        items.length,
        knapsack.length);
    // ensure the weight is correct
    Assert.assertEquals(
        assertError,
        weighKnapsack(control, items),
        weighKnapsack(knapsack, items),
        0.0001);
    // make sure that it is the same result, item for item.
    for(int i = 0; i < items.length; i++)
    {
      Assert.assertEquals(assertError, control[i], knapsack[i]);
    }
  }

  // set up our knapsack item before each test.
  @Before
  public void setUp()
  {
    solver = new KnapsackSolver();
  }


  // make sure there are no constructor errors
  @Test
  public void testInitialization()
  {
    /* testing setUp */
  }

  // an empty knapsack is easy, but this ensures that we can put together a
  // basic response.
  @Test
  public void testEmptyKnapsack()
  {
    IKnapsackSolver.Item[] items = {};
    boolean[] solution = solver.knapsack(4.0,items);
    boolean[] control = {};
    assertKnapsacksEqual(solution, control, items);
  }

  // testing when we have just one item (no recursion nesssiary) and we should
  // take it.
  @Test
  public void testKnapsackOneItemTake()
  {
    IKnapsackSolver.Item[] items = {
      new IKnapsackSolver.Item(1.0, 5.0),
    };
    boolean[] solution = solver.knapsack(7.1,items);
    boolean[] control = {true};
    assertKnapsacksEqual(solution, control, items);
  }


  // testing when we have just one item (no recursion nesssiary) and we can not
  // fit it into the sack.
  @Test
  public void testKnapsackOneItemLeave()
  {
    IKnapsackSolver.Item[] items = {
      new IKnapsackSolver.Item(1.0, 5.0),
    };
    boolean[] solution = solver.knapsack(0.1,items);
    boolean[] control = {false};
    assertKnapsacksEqual(solution, control, items);
  }


  // Makes sure we can solve a two item solution requiring a single step of
  // recursion to solve.
  @Test
  public void testKnapsack2()
  {
    IKnapsackSolver.Item[] items = {
      new IKnapsackSolver.Item(1.0, 5.0),
      new IKnapsackSolver.Item(2.0, 6.0),
    };
    boolean[] solution = solver.knapsack(2.1,items);
    boolean[] control = {false, true};
    assertKnapsacksEqual(solution, control, items);
  }

  // Ensures that we are respecting the prior solution internally.  The solution
  // will be suboptimal because the prior forces it to ignore the first item.
  @Test
  public void testKnapsackInternal()
  {
    IKnapsackSolver.Item[] items = {
      new IKnapsackSolver.Item(1.0, 10.0),
      new IKnapsackSolver.Item(2.0, 2.0),
      new IKnapsackSolver.Item(3.0, 2.5)
    };
    boolean[] solution = solver.knapsackRecursive(3.1,items,new boolean[]{false});
    boolean[] control = {false, false, true};
    assertKnapsacksEqual(solution, control, items);
  }

  // First difficult test.
  @Test
  public void testKnapsack5()
  {
    IKnapsackSolver.Item[] items = {
      new IKnapsackSolver.Item(1.0, 5.0),
      new IKnapsackSolver.Item(2.0, 6.0),
      new IKnapsackSolver.Item(3.0, 7.0),
      new IKnapsackSolver.Item(4.0, 8.0),
      new IKnapsackSolver.Item(5.0, 9.0)
    };
    boolean[] solution = solver.knapsack(7.1,items);
    boolean[] control = {true, true, false, true, false};
    assertKnapsacksEqual(solution, control, items);
  }

  // full 10 item test.
  @Test
  public void testKnapsack10()
  {
    IKnapsackSolver.Item[] items = {
      new IKnapsackSolver.Item(1.0, 2.0),
      new IKnapsackSolver.Item(2.0, 3.0),
      new IKnapsackSolver.Item(3.0, 6.0),
      new IKnapsackSolver.Item(4.0, 2.0),
      new IKnapsackSolver.Item(5.0, 6.0),
      new IKnapsackSolver.Item(6.0, 5.0),
      new IKnapsackSolver.Item(7.0, 2.0),
      new IKnapsackSolver.Item(8.0, 9.0),
      new IKnapsackSolver.Item(9.0, 5.0),
      new IKnapsackSolver.Item(10.0, 2.0)
    };
    boolean[] solution = solver.knapsack(16.1,items);
    boolean[] control = {false, false, true, false, true, false, false, true, false, false};
    assertKnapsacksEqual(solution, control, items);
  }

}
