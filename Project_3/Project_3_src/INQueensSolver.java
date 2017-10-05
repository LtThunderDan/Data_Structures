public interface INQueensSolver
{
    ////////////////////////////////////////////////////////////////////////////
    // Public interface

    // Solves the nQueens problem for a provided n.  You need only return the
    // first solution you find.
    // Returns a boolean array in which the NxN board is represented as a
    //   multidimensional array and where True represents the precesne of a
    //   Queen.  If no solution is possible return null.
    // Hint: call nQueensRecursive to perform the actual work.
    boolean[][] nQueens(int n);


    ////////////////////////////////////////////////////////////////////////////
    // Private interface
    //
    // Normally, everything past this point would be private.  For the sake of
    // testing in CS249, however, all of these must be provided as public.

    // Solve the nQueens problem for a provided n, assuming the board
    // configuration given as prior, which may already contain up to n-1 queens.
    // Takes an i and j as parameters for the next potential queen location.
    // Returns a boolean array in which the NxN board is represented linearly
    //   and where True represents the precesne of a Queen.  If no solution is
    //   possible, return null.
    // Hint: This method should call itself to break down the problem.
    // Hint: Consider what your 'base case' is?  On what condition do you no
    //   long recurse?
    // Hint: make sure not to change prior, to avoid destroying your
    //   intermediate solution.
    boolean[][] nQueensRecursive(int n, boolean[][] prior, int i, int j);


}
