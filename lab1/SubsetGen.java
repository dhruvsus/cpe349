import java.util.ArrayList;

//	This class generates subsets of a set represented as a string in Java

public class SubsetGen {

    public SubsetGen() {}

    public ArrayList < String > getSubsets(String word) {
        ArrayList < String >
            subsets = new ArrayList < String > (); //   Put your algorithm here
        ArrayList < String > temp = new ArrayList < String > ();
        if (word.length() > 0) {
            temp = getSubsets(word.substring(0, word.length() - 1));
            String snippedCharacter = word.substring(word.length()-1);
            for (int i = 0; i < temp.size(); i++) {
                subsets.add(temp.get(i));
            }
            for (int i = 0; i < temp.size(); i++) {
                subsets.add(temp.get(i) + snippedCharacter);
            }
            return subsets;
        } else {
            subsets.add("");
            return subsets;
        }
    }
    public static void main(String[] args)   {
      SubsetGen generator = new SubsetGen();
      ArrayList<String> subsets = generator.getSubsets("abc");
      System.out.println(subsets);
    }
}
