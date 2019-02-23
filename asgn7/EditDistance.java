import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class EditDistance {
    public static void dynamicEditDistance(String[] x, String[] y, boolean printAlignment) {
        int editDistance = 0;
        int ySize = y.length + 1;
        int xSize = x.length + 1;
        int[][] table = new int[ySize][xSize];
        // initialize 0th row and column of the table to 2*|row-column|
        // because the cost of an empty gene against non empty gene
        // is 2* length of nonempty gene
        for (int i = 0; i < ySize; i++) {
            table[i][0] = 2 * i;
        }
        for (int j = 0; j < xSize; j++) {
            table[0][j] = 2 * j;
        }

        // print table before
        /*
         * System.out.printf("table before filling\n"); for (int i = 0; i < ySize; i++)
         * { for (int j = 0; j < xSize; j++) { System.out.printf("%d ", table[i][j]); }
         * System.out.println(); }
         */

        // fill table
        /*
         * recurrence relation: Ed(i,j)= Ed(i-1,j-1) if match, else
         * min((Ed(i,j-1)+2),(Ed(i-1,j)+2))
         */
        for (int i = 1; i < ySize; i++) {
            for (int j = 1; j < xSize; j++) {
                if (y[i - 1].equals(x[j - 1])) {
                    table[i][j] = table[i - 1][j - 1];
                } else {
                    table[i][j] = Math.min(table[i - 1][j - 1] + 1, Math.min(table[i - 1][j] + 2, table[i][j - 1] + 2));
                }
            }
        }

        /*
         * System.out.printf("table after filling\n"); for (int i = 0; i < ySize; i++) {
         * for (int j = 0; j < xSize; j++) { System.out.printf("%d ", table[i][j]); }
         * System.out.println(); }
         */
        // backtrace only if boolean set for printAlignment
        editDistance = table[ySize - 1][xSize - 1];
        System.out.printf("Edit distance = %d\n", editDistance);
        if (printAlignment) {
            // now backtrace
            // number of rows in alignment equals number of 
            // characters in x + characters in y (RIP memory)
            int maxLines = xSize + ySize -2; //-2 because xSize =x.length+1
            String[] backTrace = new String[maxLines];
            int backTraceIndex = maxLines - 1;
            int i = ySize - 1;
            int j = xSize - 1;
            // sanity check for index errors
            //System.out.printf("i = %d j = %d backTraceIndex = %d\n", i, j, backTraceIndex);
            //System.out.printf("dimensions of table: %d rows and %d columns\n", ySize, xSize);
            while (i + j > 0) {
                String temp = "";
                if (table[i][j] == table[i - 1][j - 1] && y[i - 1].equals(x[j - 1])) {
                    // the characters matched
                    // go diagonally up in the table
                    temp = x[j - 1] + " " + y[i - 1] + " " + "0";
                    //System.out.println(temp);
                    backTrace[backTraceIndex] = temp;
                    i--;
                    j--;
                    backTraceIndex--;
                } else if (table[i][j] == table[i - 1][j - 1] + 1) {
                    // the characters don't match
                    // go diagonally up the table
                    temp = x[j - 1] + " " + y[i - 1] + " " + "1";
                    //System.out.println(temp);
                    backTrace[backTraceIndex] = temp;
                    i--;
                    j--;
                    backTraceIndex--;
                } else if (table[i][j] == table[i - 1][j] + 2) {
                    // x blank
                    temp = "- " + y[i - 1] + " " + "2";
                    //System.out.println(temp);
                    backTrace[backTraceIndex] = temp;
                    i--;
                    backTraceIndex--;
                } else if (table[i][j] == table[i][j - 1] + 2) {
                    // y blank
                    temp = x[j - 1] + " -" + " " + "2";
                    //System.out.println(temp);
                    backTrace[backTraceIndex] = temp;
                    j--;
                    backTraceIndex--;
                }
            }
            // print out traceback
            for (backTraceIndex=backTraceIndex+1; backTraceIndex < maxLines; backTraceIndex++) {
                System.out.println(backTrace[backTraceIndex]);
            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        // accept filename through args
        if (args.length < 1) {
            return;
        }
        String filename = args[0];
        // create scanner to scan in string
        Scanner scanner = new Scanner(new File(filename));
        String x = scanner.nextLine();
        String y = scanner.nextLine();
        //y = "AACAGTTACC";
        //x = "TAAGGTCA";
        boolean printAlignment = true;
        dynamicEditDistance(x.split(""), y.split(""), printAlignment);

    }
}