/**
 * Created by Daniel on 11/3/2016.
 */
public class NQueensSolver implements INQueensSolver{

    @Override
    public boolean[][] nQueens(int n){
        return nQueensRecursive(n, new boolean[n][n], 0, 0);
    }

    @Override
    public boolean[][] nQueensRecursive(int n, boolean[][] prior, int col, int row) {
        int numOfQ = 0;
        //check for solved bored, if solved return the board (prior)
        for(int i=0; i < n; i++){
            for(int j=0 ; j < n; j++){
                if(prior[j][i]){
                    numOfQ++;
                }
            }
        }
        if(n == numOfQ){return prior;}

        //check for queen in column, if there's a queen in column, call function for next column.
        numOfQ = 0;
        for(int i=0; i<n; i++){
            if(prior[col][i]){numOfQ++;}
        }

        //create boolean array,
        boolean[][] priorSave = new boolean[n][n];
        //loop through priorSave to assign values of prior.
        for(int k=0; k < n; k++){
            for(int m=0; m < n; m++){
                priorSave[m][k] = prior[m][k];
            }
        }

        if(numOfQ == 1) {
            priorSave = nQueensRecursive(n, prior, col + 1, 0);
            if(priorSave != null){
                return priorSave;
            }
        }

        //loop through # of rows in this column
        for(int p = 0; p< n; p++){
            //check if this column and this row is valid
            if(canDo(col, p, prior)){
                priorSave = new boolean[n][n];
                for(int k=0; k < n; k++){
                    for(int m=0; m < n; m++){
                        priorSave[m][k] = prior[m][k];
                    }
                }
                //set position in priorSave to true
                priorSave[col][p] = true;
                //set priorSave to recursive call for next column
                priorSave = nQueensRecursive(n, priorSave, col+1, row);

                //return priorSave if it isn't null.;
                if(priorSave != null){
                    return priorSave;
                }
            }
        }
        return null;
    }

    //This code is modeled off of Stephen White's canDo method.
    public boolean canDo(int col, int row, boolean[][] prior){
        if (!rowCanDo(col, row, prior)) return false;

        if (!colCanDo(col, row, prior)) return false;

        if (!getAllDiagonals(col, row, prior)) return false;

        return true;
    }

    //this method checks the rows of the board for queens.
    public boolean rowCanDo(int col, int row, boolean[][] prior){
        try{
            for (int colVal = col; colVal<prior.length ; colVal++ ){
                if(prior[colVal][row] == true){
                    return false;
                }
            }
            for (int colVal = col; colVal>=0; colVal-- ){
                if(prior[colVal][row] == true){
                    return false;
                }
            }
        }
        catch (ArrayIndexOutOfBoundsException e){}
        return true;
    }

    //this method checks the columns of the board for queens.
    public boolean colCanDo(int col, int row, boolean[][] prior){
        try{
            for (int rowVal = row; rowVal<prior[0].length ; rowVal++ ){
                if(prior[col][rowVal] == true){
                    return false;
                }
            }
            for (int rowVal = row; rowVal>=0; rowVal-- ){
                if(prior[col][rowVal] == true){
                    return false;
                }
            }
        }
        catch (ArrayIndexOutOfBoundsException e){}
        return true;
    }

    //this method calls all the methods to check the diagonals.
    public boolean getAllDiagonals(int col, int row, boolean[][] prior){
        try{
            if (!upperLeft(col, row, prior)) return false;
            if (!upperRight(col, row, prior)) return false;
            if (!lowerLeft(col, row, prior)) return false;
            if (!lowerRight(col, row, prior)) return false;

        }
        catch (ArrayIndexOutOfBoundsException e){}
        return  true;
    }

    //checks upper left diag.
    public boolean upperLeft(int col, int row, boolean[][] prior){
        try{
            int colKeepTrack = col;
            int rowKeepTrack = row;
            for(int colVal = col; colVal>=0; col--){
                if(prior[colKeepTrack][rowKeepTrack] == true) return false;
                colKeepTrack-=1;
                rowKeepTrack-=1;
            }
        }
        catch (ArrayIndexOutOfBoundsException e){}
        return true;
    }

    //checks upper right diag.
    public boolean upperRight(int col, int row, boolean[][] prior){
        try {
            int colKeepTracker = col;
            int rowKeepTrack = row;
            for (int colVal = col; colVal < prior.length; col++) {
                if (prior[colKeepTracker][rowKeepTrack] == true) return false;
                colKeepTracker += 1;
                rowKeepTrack -= 1;
            }
        }
        catch (ArrayIndexOutOfBoundsException e){}
        return  true;
    }

    //checks lower left diag.
    public boolean lowerLeft(int col, int row, boolean[][] prior){
        try{
            int colKeepTracker = col;
            int rowKeepTrack = row;
            for (int colVal = col; colVal >= 0; col--) {
                if (prior[colKeepTracker][rowKeepTrack] == true) return false;
                colKeepTracker -= 1;
                rowKeepTrack += 1;
            }
        }
        catch (ArrayIndexOutOfBoundsException e){}
        return true;
    }

    //checks lower right diag.
    public boolean lowerRight(int col, int row, boolean[][] prior){
        try{
            int colKeepTracker = col;
            int rowKeepTrack = row;
            for (int colVal = col; colVal < prior.length; col++) {
                if (prior[colKeepTracker][rowKeepTrack] == true) return false;
                colKeepTracker += 1;
                rowKeepTrack += 1;
            }
        }
        catch (ArrayIndexOutOfBoundsException e){}
        return true;
    }

    public static void main(String[] args){
        long begin, end;
        NQueensSolver table = new NQueensSolver();
        begin = System.nanoTime();
        for(int i = 0; i <= 100; i++){
            table.nQueens(4);
        }
        end = System.nanoTime();

        System.out.println("Time in nanoseconds for 4: " + (end - begin));

        begin = System.nanoTime();
        for(int i = 0; i <= 100; i++){
            table.nQueens(5);
        }
        end = System.nanoTime();

        System.out.println("Time in nanoseconds for 5: " + (end - begin));

        begin = System.nanoTime();
        for(int i = 0; i <= 100; i++){
            table.nQueens(6);
        }
        end = System.nanoTime();

        System.out.println("Time in nanoseconds for 6: " + (end - begin));

        begin = System.nanoTime();
        for(int i = 0; i <= 100; i++){
            table.nQueens(7);
        }
        end = System.nanoTime();

        System.out.println("Time in nanoseconds for 7: " + (end - begin));

        begin = System.nanoTime();
        for(int i = 0; i <= 100; i++){
            table.nQueens(8);
        }
        end = System.nanoTime();

        System.out.println("Time in nanoseconds for 8: " + (end - begin));

        begin = System.nanoTime();
        for(int i = 0; i <= 100; i++){
            table.nQueens(9);
        }
        end = System.nanoTime();

        System.out.println("Time in nanoseconds for 9: " + (end - begin));

        begin = System.nanoTime();
        for(int i = 0; i <= 100; i++){
            table.nQueens(10);
        }
        end = System.nanoTime();

        System.out.println("Time in nanoseconds for 10: " + (end - begin));

        begin = System.nanoTime();
        for(int i = 0; i <= 100; i++){
            table.nQueens(11);
        }
        end = System.nanoTime();

        System.out.println("Time in nanoseconds for 11: " + (end - begin));

        begin = System.nanoTime();
        for(int i = 0; i <= 100; i++){
            table.nQueens(12);
        }
        end = System.nanoTime();

        System.out.println("Time in nanoseconds for 12: " + (end - begin));
    }
}


