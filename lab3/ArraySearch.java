public class ArraySearch{
	public static int[] search(int[][] curArray, int target){
		int numRows=curArray.length;
		int numColums=curArray[0].length;
		//start from top right
		int[] position=new int[]{0,numColums-1};
		int comp=0;
		while(position[0]<curArray.length && position[1]>=0){
			comp++;
			if(curArray[position[0]][position[1]]==target){
				System.out.println(comp);
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
	int mat[][] = { {1, 2, 8, 9}, 
	{3, 6, 12, 13}, 
	{7, 10, 13, 19}, 
	{10, 11, 28, 30} };

	int[] result=search(mat,12);
	System.out.println(result[0]+", "+result[1]);
	}*/

} 

