public class ArraySearch{
	public static int[] search(int[][] curArray, int target){
		int numRows=curArray.length;
		int numColums=curArray[0].length;
		//start from top right
		int[] position=new int[]{0,numColums-1};
		while(position[0]<curArray.length && position[1]>=0){
			if(curArray[position[0]][position[1]]==target){
				return position;
			}
			else if(curArray[position[0]][position[1]]>target){
				position[1]--;
			}
			else{
				position[0]++;
			}
		}
		position[0]=-1;
		position[1]=-1;
		return position;
	}/*
	public static void main(String[] args) { 
		int mat[][] = { {10, 20, 30, 40}, 
			{15, 25, 35, 45}, 
			{27, 29, 37, 48}, 
			{32, 33, 39, 50} };

		int[] result=search(mat,1);
		System.out.println(result[0]+", "+result[1]);
	} */

} 

