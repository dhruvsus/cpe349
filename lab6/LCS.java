public class LCS{
	public static String getLCS(String str1, String str2){
		String LCS="";
		int xSize=str1.length()+1;
		int ySize=str2.length()+1;
		int[][] table=new int[ySize][xSize];
		// zero initialization
		// even though java initializes int table to 0
		for(int i=0;i<ySize;i++){
			table[i][0]=0;
		}
		for(int i=0;i<xSize;i++){
			table[0][i]=0;
		}
		for(int i=1;i<ySize;i++){
			for(int j=1;j<xSize;j++){
				// check match
				if(str1.charAt(j-1)==str2.charAt(i-1)){
					table[i][j]=table[i-1][j-1]+1;
				}
				else{
					table[i][j]=Math.max(table[i][j-1],table[i-1][j]);
				}
			}
		}
		/* // print table
		for(int i=1;i<ySize;i++){
			for(int j=1;j<xSize;j++){
				System.out.printf("%d ",table[i][j]);
			}
			System.out.println();
		} */
		// backtrace
		int i=ySize-1;
		int j=xSize-1;
		while(table[i][j]!=0){
			// start from edge and check if matching
			if(str1.charAt(j-1)==str2.charAt(i-1)){
				// select character and move diagonally
				LCS=String.valueOf(str1.charAt(j-1))+LCS;
				j--;
				i--;
			}
			else{
				//move in the direction of max 
				if(table[i][j-1]>table[i-1][j]){
					j--;
				}
				else{
					i--;
				}
			}
		}
		return LCS;
	}
/* 
	public static void main(String[] args){
		//driver program
		//String x="AGCAGT";
		//String y="GACA";
		//String y = "thisisatest";
		//String x = "testing123testing";
		//String y = "ABCDGH";
		//String x = "AEDFHR";
		//String y = "AGGTAB";
		//String x = "GXTXAYB";
		String z=getLCS(x,y);
		System.out.printf("LCS is %s of length %d\n",z,z.length());
 	}
 */
}