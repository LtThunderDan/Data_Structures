import org.junit.*;

public class NQueensSolverTest
{
  INQueensSolver solver;

  @Before
  public void setUp()
  {
    solver = new NQueensSolver();
  }

  // helper method ensures the given board is infact a nqueens solution
  public static void assertNQueenSolution(boolean[][] solution)
  {
    // ensure the board is non null and is square
    Assert.assertNotNull(solution);
    int n = solution.length;
    for(int i = 0; i < n; i++)
    {
      Assert.assertNotNull(solution[i]);
      Assert.assertEquals(solution[i].length, n);
    }
    // ensure we have the correct number of queens
    int queen_count = 0;
    for(int i = 0; i < n; i++)
    {
      for(int j = 0; j < n; j++)
      {
        if(solution[i][j])
        {
          queen_count += 1;
          // check to make sure no queens are threatening eachother.
          assertNQueenClear(i,j,solution,n);
        }
      }
    }
    String assertError = "Board " + stringifyBoard(solution) + " does not have "+n+" queens.";
    Assert.assertEquals(assertError, n, queen_count);
  }

  // helper method to make sure no queen is colliding
  private static void assertNQueenClear(int i, int j, boolean[][] solution, int n)
  {
    // set up our error message for when a queen does collide.
    String assertError = "Board " + stringifyBoard(solution) + " Fails NQueens due to the queen at "+i+", "+j+".";
    // check each row of the board...
    for(int oi = 0; oi < n; oi++)
    {
      // horiz
      // no queens should appear on the same row
      int oj = j;
      if(oi != i || oj != j) Assert.assertFalse(assertError, solution[oi][oj]);

      // diag 1
      // no queens should appear on the first diagonal
      oj = j + (oi - i);
      if(oj >= 0 && oj < n)
      {
        if(oi != i || oj != j) Assert.assertFalse(assertError, solution[oi][oj]);
      }

      // diag 2
      // no queens should appear on the second diagonal
      oj = j - (oi - i);
      if(oj >= 0 && oj < n)
      {
        if(oi != i || oj != j) Assert.assertFalse(assertError, solution[oi][oj]);
      }
    }
    // check each column...
    for(int oj = 0; oj < n; oj++)
    {
      // vert
      // no queens should appear on the same column.
      int oi = i;
      if(oi != i || oj != j) Assert.assertFalse(assertError, solution[oi][oj]);
    }
  }

  // helper method to turn a board into a user readable string for error
  // messages.
  public static String stringifyBoard(boolean[][] board)
  {
    String result = "\n";
    for(int i = 0; i < board.length; i++)
    {
      for(int j = 0; j < board[i].length; j++)
      {
        if(board[i][j]) result += "Q";
        else result += ".";
      }
      result += "\n";
    }
    return result;
  }

  @Test
  public void testInstantiation()
  {
    /* testing setUp */
  }

  // Test Testing
  // The logic for checking boards is complex enough to merit unit tests
  // just to ensure correctness of the helper methods.  These were also used
  // to develop those helper methods in the spirit of test-driven design.

  // can we recognise a complete board?
  @Test
  public void testAssertNQueensSolutionPass()
  {
    boolean [][] solution = {
        {false, true,  false, false},
        {false, false, false, true },
        {true,  false, false, false},
        {false, false, true,  false},
      };
    assertNQueenSolution(solution);
  }

  // can we recognise a queen collision horizontally?
  @Test(expected=AssertionError.class)
  public void testAssertNQueensSolutionCollision()
  {
    boolean [][] solution = {
        {false, true,  false, false},
        {false, false, false, false},
        {true,  false, false, true },
        {false, false, true,  false},
      };
    assertNQueenSolution(solution);
  }

  // can we recognise a queen collision vertically?
  @Test(expected=AssertionError.class)
  public void testAssertNQueensSolutionVertical()
  {
    boolean [][] solution = {
        {true , false, false, false},
        {true , false, false, false},
        {true , false, false, false},
        {true , false, false, false},
      };
    assertNQueenSolution(solution);
  }

  // can we recognise when a queen is missing?
  @Test(expected=AssertionError.class)
  public void testAssertNQueensSolutionFewQueens()
  {
    boolean [][] solution = {
        {false, true,  false, false},
        {false, false, false, false},
        {true,  false, false, false},
        {false, false, true,  false},
      };
    assertNQueenSolution(solution);
  }

  // Student tests

  // Start with making sure the recursive call can recognize a complete board
  // and return it as a base case.
  @Test
  public void testInternalBaseCase()
  {
    boolean [][] solution = {
        {false, true,  false, false},
        {false, false, false, true},
        {true,  false, false, false},
        {false, false, true,  false},
      };
    solution = solver.nQueensRecursive(4, solution, 0, 0);
    assertNQueenSolution(solution);
  }

  // Ensure that the recursive call can take a near complete solution with an
  // indicated final move.  This should be solveable with just one recursive
  // step (the baes case)
  @Test
  public void testInternalEasy()
  {
    boolean [][] solution = {
        {false, true,  false, false},
        {false, false, false, false},
        {true,  false, false, false},
        {false, false, true,  false},
      };
    solution = solver.nQueensRecursive(4, solution, 1, 3);
    assertNQueenSolution(solution);
  }

  // 3 queens has no solution.
  @Test
  public void test3Queens()
  {
    boolean[][] solution = solver.nQueens(3);
    Assert.assertNull(solution);
  }

  @Test
  public void test4Queens()
  {
    boolean[][] solution = solver.nQueens(4);
    assertNQueenSolution(solution);
  }

  @Test
  public void test5Queens()
  {
    boolean[][] solution = solver.nQueens(5);
    assertNQueenSolution(solution);
  }

  @Test
  public void test6Queens()
  {
    boolean[][] solution = solver.nQueens(6);
    assertNQueenSolution(solution);
  }

  @Test
  public void test7Queens()
  {
    boolean[][] solution = solver.nQueens(7);
    assertNQueenSolution(solution);
  }

  @Test
  public void test8Queens()
  {
    boolean[][] solution = solver.nQueens(8);
    //System.out.println(stringifyBoard(solution));
    assertNQueenSolution(solution);
  }

  // This tests provides a partial solution matching the image given in
  // the assignment.  You must get a valid response while respecting the
  // partial solution.
  @Test
  public void test8QueensInternal()
  {
    boolean[][] solution = {
      {false, false, false, false, true,  false, false, false},
      {false, false, false, false, false, false, false, false},
      {false, false, false, true,  false, false, false, false},
      {false, false, false, false, false, false, false, false},
      {false, false, false, false, false, false, false, false},
      {false, false, false, false, false, false, false, false},
      {false, false, false, false, false, false, false, false},
      {true,  false, false, false, false, false, false, false}
    };
    solution  = solver.nQueensRecursive(8, solution, 0, 0);

    //System.out.println(stringifyBoard(solution));

    Assert.assertTrue(solution[0][4]);
    Assert.assertTrue(solution[2][3]);
    Assert.assertTrue(solution[7][0]);
    assertNQueenSolution(solution);
  }

  @Test
  public void test9Queens()
  {
    boolean[][] solution = solver.nQueens(9);
    assertNQueenSolution(solution);
  }

  @Test
  public void test10Queens()
  {
    boolean[][] solution = solver.nQueens(10);
    assertNQueenSolution(solution);
  }

  @Test
  public void test11Queens()
  {
    boolean[][] solution = solver.nQueens(11);
    assertNQueenSolution(solution);
  }

  @Test
  public void test12Queens()
  {
    boolean[][] solution = solver.nQueens(12);
    assertNQueenSolution(solution);
  }

}
