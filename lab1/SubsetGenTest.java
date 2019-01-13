import java.util.ArrayList;

/**
  This program demonstrates the permutation generator.
  */
public class SubsetGenTest
{
	public static void main(String[] args)   {

		// SubsetGen generator = new SubsetGen();

		ArrayList<String> answer = new ArrayList<String>();
		answer.add("");
		answer.add("a");
		answer.add("b");
		answer.add("ab");
		answer.add("c");
		answer.add("ac");
		answer.add("bc");
		answer.add("abc");  

		SubsetGen generator = new SubsetGen();
		ArrayList<String> subsets = generator.getSubsets("abc");
		ArrayList<String> grayCode=generator.getGrayCode(4); 
		boolean match = true;
		int i = 0;
		while ((match == true)&&(i<8))   {
			if (!answer.get(i).equals(subsets.get(i)))
				match = false;
			i++;
		}
		if (match == true)
			System.out.println("Congratulations all the strings match." );
		else
			System.out.println("your string at position  " + i  + " should be -" + answer.get(i-1)+ "- you returned -" + subsets.get(i-1)+"-");
		System.out.println(grayCode);
	}
}
