import java.util.ArrayList;

//	This class generates subsets of a set represented as a string in Java

public class SubsetGen {

	//empty constructor
	public SubsetGen() {}

	public ArrayList < String > getSubsets(String word) {
		ArrayList < String >
			subsets = new ArrayList < String > (); //   Put your algorithm here
		ArrayList < String > temp = new ArrayList < String > ();
		if (word.length() > 0) {
			temp = getSubsets(word.substring(0, word.length() - 1));
			String snippedCharacter = word.substring(word.length()-1);
			//loop over temp and create subsets with and without the
			//last character of the original string
			for (int i = 0; i < temp.size(); i++) {
				subsets.add(temp.get(i)); //add subsets without the last character
			}
			for (int i = 0; i < temp.size(); i++) {
				subsets.add(temp.get(i) + snippedCharacter);
				//add subsets with last character
			}
			return subsets;
		} else {
			//the empty set is the only subset of the empty set
			subsets.add("");
			//empty arraylist
			return subsets;
		}
	}

	public ArrayList<String> getGrayCode(int n){
		ArrayList<String> grayCode = new ArrayList<String>();
		ArrayList<String> temp = new ArrayList<String>();
		if(n>1){
			//recursive
			temp=getGrayCode(--n);
			//prepend a 0
			for(int i=0;i<temp.size();i++){
				grayCode.add("0"+temp.get(i));
			}
			//prepend 1
			for(int i=temp.size();i-- > 0;){
				grayCode.add("1"+temp.get(i));
			}
			return grayCode;
		}
		else{
			//graycode for n=1 is just {0,1}, it's the base case
			grayCode.add("0");
			grayCode.add("1");
			return grayCode;
		}
	}
}
