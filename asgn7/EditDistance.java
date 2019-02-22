import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class EditDistance{
    public static void main(String[] args) throws FileNotFoundException{
        // accept filename through args
        if(args.length<1){
            return;
        }
        String filename=args[0];
        // create scanner to scan in string
        Scanner scanner =new Scanner(new File(filename));
        String x=scanner.nextLine();
        String y=scanner.nextLine();
        System.out.println(x.split("").length);
        System.out.println(y.split("").length);
    }
}