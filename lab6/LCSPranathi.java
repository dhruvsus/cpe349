public class LCSPranathi {
	
	public static String getLCS (String str1, String str2) {		
		int len1 = str1.length();
		int len2 = str2.length();
				
		// two dimensional array that will hold length of longest common subsequence
		int[][] L = new int[len1+1][len2+1];
		
		for (int i = 0; i <= len1; i++) {
			for (int j = 0; j <= len2; j++) {
			
				if ((i == 0) || j == 0) {
					L[i][j] = 0;
				}
				
				else if (str1.charAt(i-1) == str2.charAt(j-1)) {
					L[i][j] = L[i-1][j-1] + 1;
				}
				else
					L[i][j] = Math.max(L[i-1][j], L[i][j-1]); 
			}
		}
				
		int idx = L[len1][len2];
		int temp = idx; 
		   
		char []LCS = new char [idx + 1];
		
		int r = len1;
		int c = len2;
		
		while (r > 0 && c > 0) {
			if (str1.charAt(r-1) == str2.charAt(c-1)) {
				LCS[idx - 1] = str1.charAt(r - 1);
				idx--;
				r--;
				c--;
			}
			else if (L[r-1][c] > L[r][c-1]) {
				r--;
			}
			else
				c--;
		}
		// commented out because just checking output of char array and  string 
//		 System.out.print("LCS of "+str1+" and "+str2+" is "); 
//		    
//	        for(int k=0;k<=temp;k++) 
//	            System.out.print(LCS[k]); 
//	        
//	        System.out.println(" " + LCS.length);
//	        
		String strValofCharArray = String.valueOf(LCS);
		strValofCharArray.replaceAll("\\s+","");
//		System.out.println(strValofCharArray.length());
		return strValofCharArray;
	}
	
	public static void main(String[] args){
		//driver program
		String x="AGCAGT";
		String y="GACA";
		//String y = "thisisatest";
		//String x = "testing123testing";
		//String y = "ABCDGH";
		//String x = "AEDFHR";
		//String y = "AGGTAB";
		//String x = "GXTXAYB";
		String z=getLCS(x,y);
		System.out.printf("LCS is %s of length %d\n",z,z.length());
 	}
}
