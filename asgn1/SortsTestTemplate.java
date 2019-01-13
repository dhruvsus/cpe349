import java.util.Arrays;
import java.util.Collections;
/**
	This program is a	test driver for a programs that sorts 	
	an array of integers and return a long that contains the number of key comparisons
   done by the sorting algorithm.
*/
public class SortsTestTemplate	{		
	
	public static void main(String[]	args)	 {	
      int[] a= {10,1,2,3,4,5,6,7,8,9}; 
  		int[] temp	= a.clone();
		System.out.println("test array to sort:  " + Arrays.toString(a));
	   long numComp = Sorts.insertSort (temp);
		System.out.println("Sorted by insertion sort: ");
		System.out.println(Arrays.toString(temp));
      System.out.println("# of comparisons in insertion sort:  " + numComp);
      System.out.println();

	
		temp	= a.clone();
		System.out.println("test array to sort:  " + Arrays.toString(a));
	   numComp = Sorts.selectSort (temp);
		System.out.println("Sorted by selection sort: ");
		System.out.println(Arrays.toString(temp));
      System.out.println("# of comparisons in selection sort:  " + numComp);
      System.out.println();

		temp = a.clone();
		System.out.println("test array to sort:  " + Arrays.toString(a));
		numComp	= Sorts.mergeSort(temp);
		System.out.println("Sorted by merge sort: ");
      System.out.println(Arrays.toString(temp));
		System.out.println("# of comparisons by mergesort is = " +	numComp);
		System.out.println();	 
	}
}