import java.util.Arrays;
import java.io.FileNotFoundException;
import java.util.Random;
import java.lang.Math;

// Sample solution for Max Sum Contig Subseq Lab - still needs some clean up
// Separate methods for each algorithm and a few more comments
/**
   Simple program for max contig subseq sum for comparing the efficiency of different algorithms
   */

public class MaxSumContig  {     
   
   public static void main(String[] args) throws FileNotFoundException {   
      Random generator = new Random();
      int size = 100;
      int halfRange = 10;
      int range = 20;
      int[] b = new int[size];
     
      for (int i = 0; i < size; i++){
		   b[i] = generator.nextInt(range) - halfRange;    
		};
     
     // code for debugging
     //int [] b = { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 15, -1, -1, -1, -1, -1, -1 };
     
	  // Prints out array for debugging  
	  //System.out.println(Arrays.toString(b));
     System.out.println("size of array  " + size);
     long startTime;
     long endTime; 
     int maxSoFar;
     int thisSum;
     long duration;
        
     //Brute force n cubed 
     thisSum = 0;
     startTime = System.nanoTime();
     maxSoFar = 0;
     for (int low = 0; low < size; low++){ 
         for (int high = 0; high < size; high++){ 
      		thisSum = 0;
       		for (int k = low; k<= high; k++)
      			thisSum = thisSum + b[ k ];
   		if( thisSum > maxSoFar )
   			maxSoFar = thisSum;
         }
      }
      endTime = System.nanoTime();
      System.out.println("Brute force n-cubed solution  = " + maxSoFar);  
      duration = endTime-startTime;
      System.out.println("duration for brute force n- cubed  = " + duration);   
 
      //Brute force n squared 
      startTime = System.nanoTime();
      maxSoFar = 0;
      for (int low = 0; low < size; low++){ 
         thisSum = 0;
         for (int high = low; high < size; high++){ 
			   thisSum = thisSum + b[high];
		      if( thisSum > maxSoFar )
			       maxSoFar = thisSum;
         }
      }
      endTime = System.nanoTime();
      System.out.println("Brute force n-squared soln solution  = " + maxSoFar);
      duration = endTime-startTime;
      System.out.println("duration for Brute force n-squared  = " + duration); 



      //Divide and Conquer    n*log n   solution 
      startTime = System.nanoTime();
      maxSoFar = divideConqMaxSum(0,size-1, b);
      endTime = System.nanoTime();
      
      System.out.println("Divide and Conquer n*log n solution  = " + maxSoFar);
      duration = endTime-startTime;
      System.out.println("duration for Divide and Conquer n*log n solution  = " + duration);
      
      // A simple Dynamic Programming algorithm (Scanning solution)
      startTime = System.nanoTime();
      maxSoFar = 0;
      int maxEndingHere = 0;
      for (int i = 0; i < size; i++){ 
   	   maxEndingHere = Math.max(maxEndingHere + b[i], 0);
   	   maxSoFar = Math.max(maxEndingHere, maxSoFar);
      }

      endTime = System.nanoTime();
      System.out.println("Scanning solution  = " + maxSoFar);
      duration = endTime-startTime;
      System.out.println("duration for scanning algorithm  = " + duration);  
   
   }
 
   public static int divideConqMaxSum(int low,int high, int[] b){
      if (low > high) return 0;
      if (low == high) return b[low];
      int mid = (low+high)/2;
      int maxInLeft = divideConqMaxSum(low,mid, b);
      int maxInRight = divideConqMaxSum(mid+1,high, b);
      // now compute the max of any sequence crossing mid - this means it includes at least one entry on each side
      // first the left side 
      int sum = 0; int maxToLeft = 0;
      for (int i = mid; i>= low; i--) {
         sum = sum + b[i];
         maxToLeft = Math.max(maxToLeft, sum);
      }
      // now the right side
      sum = 0; int maxToRight = 0;
      for (int i = mid+1 ; i<= high; i++) {
         sum = sum + b[i];
         maxToRight = Math.max(maxToRight, sum);
      }
      int maxCross = maxToLeft + maxToRight;
      int maxAll = Math.max(maxCross, maxInRight);
      maxAll = Math.max(maxAll, maxInLeft);
      return maxAll ; 
   }// end divideConqMaxSum
 }

