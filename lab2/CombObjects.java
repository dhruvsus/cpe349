import java.util.ArrayList;

public class CombObjects{
	public CombObjects(){}

	public static ArrayList<String> getLexPerm(String str){
		ArrayList<String> perm=new ArrayList<String>();
		ArrayList<String> temp=new ArrayList<String>();

		// base case if length of string is <=1
		if(str.length()<=1){
			perm.add(str);
			return perm;
		}
		for(int i=0;i<str.length();i++){
			//remove the character
			char removed=str.charAt(i);
			String tempStr=new String(str);
			tempStr=str.replace(Character.toString(removed),"");
			temp=getLexPerm(tempStr);
			//generate recursive permutations
			//by adding the removed character to the front
			for(int j=0;j<temp.size();j++){
				perm.add(removed+temp.get(j));
			}
		}
		return perm;
	}

	public static ArrayList<String> getMinChgPerm(String str){
		ArrayList<String> perm=new ArrayList<String>();
		if(str.isEmpty()){
			perm.add(str);
			return perm;
		}
		//remove the last character
		char removed=str.charAt(str.length()-1);
		String tempStr=new String(str);
		tempStr=str.replace(Character.toString(removed),"");
		return perm;
	}

	public static void main(String[] args){
		//driver program
		ArrayList<String> perm = new ArrayList<String>();
		perm=CombObjects.getLexPerm("abc");
		System.out.println(perm);
	}
}
