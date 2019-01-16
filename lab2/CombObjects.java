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
		ArrayList<String> temp=new ArrayList<String>();
		if(str.isEmpty()){
			perm.add("");
			return perm;
		}
		//remove the last character
		char removed=str.charAt(str.length()-1);
		//debugging, print removed character and resulting string
		//System.out.println(removed);
		String tempStr=new String(str);
		tempStr=str.replace(Character.toString(removed),"");
		//System.out.println(tempStr);

		//recursively generate permuatations without removed character
		temp=getMinChgPerm(tempStr);
		for(int i=0;i<temp.size();i++){
			//if i is even add removed character right to left
			StringBuilder sb = new StringBuilder(temp.get(i));
			if(i%2==0){
				for(int j=temp.get(i).length();j>=0;j--){
					perm.add(temp.get(i).substring(0, j) + removed + temp.get(i).substring(j));
				}
			}
			else{
				for(int j=0;j<=temp.get(i).length();j++){
					perm.add(temp.get(i).substring(0,j)+removed+temp.get(i).substring(j));
				}
			}
			//System.out.println(perm);
		}
		return perm;
	}

	public static void main(String[] args){
		//driver program
		ArrayList<String> perm = new ArrayList<String>();
		perm=CombObjects.getLexPerm("abcd");
		ArrayList<String> permMinCh= new ArrayList<String>();
		permMinCh=CombObjects.getMinChgPerm("abcd");
		System.out.println(perm);
		System.out.println(permMinCh);
	}
}
