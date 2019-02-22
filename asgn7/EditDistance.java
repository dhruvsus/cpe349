import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class EditDistance {
    public static int dynamicEditDistance(String[] x, String[] y) {
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
        // print table
        for (int i = 0; i < ySize; i++) {
            for (int j = 0; j < xSize; j++) {
                System.out.printf("%d ", table[i][j]);
            }
            System.out.println();
        }
        return editDistance;
    }

    public static void main(String[] args) throws FileNotFoundException {
        // driver to test
        String x = "AACAGTTACC";
        String y = "TAAGGTCA";
        int editDistance = dynamicEditDistance(x.split(""), y.split(""));
        System.out.printf("Edit distance = %d\n", editDistance);
        // accept filename through args
        /* if (args.length < 1) {
            return;
        }
        String filename = args[0];
        // create scanner to scan in string
        Scanner scanner = new Scanner(new File(filename));
        String x = scanner.nextLine();
        String y = scanner.nextLine(); */
    }
}