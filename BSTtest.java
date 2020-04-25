
/*************************************************************************
 *
 *  Pace University
 *  Fall 2019
 *  Data Structures and Algorithms
 *
 *  Course: CS 241
 *  Team Authors: Maksym Karunos, Paula Hernandez
 *  External Collaborators: NONE
 *  References: 
 *
 *  Assignment: Assignment 4
 *  Problem: Running times for skewed and balanced BST
 *  Description: Difference between running times for the function search in a skewed and balanced tree.
 *  Creating two tables with at least five inputs and checking the running times for those in the different trees. 
 *  
 *
 *  Input: 
 *  Output: 
 *
 *  Visible data fields:
 *  COPY DECLARATION OF VISIBLE DATA FIELDS HERE
 *
 *  Visible methods:
 *  COPY SIGNATURE OF VISIBLE METHODS HERE
 *
 *
 *   Remarks
 *   -------
 * 
 *   1.        | skewed tree  | balanced tree
 *       search| O(n)         | O(log(n))
 *       
 *      1. O(n) because in the skewed tree you have to go through the entire tree in order to reach the very last element.
 *      2. The running time is O(log(n)) because for every search, the tree gets divided by 2. What this means is that first,
 *      you compare the value you are searching for with the root, if its lower then you know you only care about the right,
 *      discarding the whole left of the tree, if its greater, then you discard the right. You keep doing this search, until 
 *      you get to the bottom, or you find the value you are searching for.
 *      
 *   2. Time taken
 *      Search     |n =1      | n =10 	  | n= 10^2  | n = 10^3    | n = 10^4    |     
 *      SKEWED BST |2900 nano | 2900 nano | 8300 nano| 118600 nano | 687700 nano |
 *      
 *      We did a little bit of plotting the points on the graph
 *      where y value represents time in nanoseconds and x value represent the input size, 
 *      the equations that describes the best fit is, indeed, linear: y = 64.8715x+38615
 *     	I attached the plot to the assignment.
 *     	In other words, we see how time increases by a certain constant as the input size increases. This proves
 *      our theory of running time O(n).
 *   
 *   3. Time taken
 *      Search       |n =1       | n =10 	 | n= 10^2   | n = 10^3   | n=5000      | n=7000           
 *      BALANCED BST |11160 nano | 9185 nano | 48645 nano| 62950 nano | 166875 nano | 233748 nano |266321 nano |   
 *      
 *      We also used a plot and graph this result. We noticed that the data does not exactly match a log function but
 *      it is pretty close. One of the main issues that we have with this, is that for n=1 the running time
 *      is greater than for n=10. If we ignore this result, we can conclude that the running time is logarithmic
 *      as shown in the graph. As n gets bigger and bigger the time increases slower. If we could run this program for values 
 *      greater than n=10^4 then we would probably see this more clearly.
 *      The equation we got is y = 34455.1 log(0.0544395 x)
 *    
 *    
 *    4. Time taken Bonus
 *    
 *    Search	|	n=10^2	   |  n=10^3    |	n=10^4  |	n=10^5	    |	n=10^6    |	
 *    SKEWED	|90793 nano    | 92803 nano |524962 nano|12044356 nano  |37623256 nano|
 *    BALANCED	|   24296 nano | 25968 nano |32517 nano |43458 nano     | 42945 nano  | 
 *    
 *    Once you adapt the contains and the insert methods to being iterative, the running times decrease as we increased the input.
 *    They decrease to the point that now we are actually able to run 10^6 when before anything past 10^4 would give us an error. This proves that
 *    running codes that are iterative, take less time in the long run than recursion. The following two equations, are the linear and logarithmic 
 *    best fit functions obtained from the new running times.
 *    
 *    y = 36.2143 x + 2.0277×10^6 (Skewed)
 *    
 *    y = 2379.41 log(149.952 x)  (Balance)
 *    

 *    
 *    
 *    
 *    
 *************************************************************************/
import java.util.*;
public class BSTtest{

	// this method is responsible for balancing the tree.
	static void seq(int low, int high, BinarySearchTree T) {
		if (low<= high) {
			int mid = (int)(low+high)/2;
			T.insert(mid);
			seq(low,mid-1,T);
			seq(mid+1,high,T);
		}
	}
	static void seq(long low, long high, BinarySearchTree T) {
		if (low<= high) {
			long mid = (long)(low+high)/2;
			T.insert(mid);
			seq(low,mid-1,T);
			seq(mid+1,high,T);
		}
	}
	public static void main (String[] args){
		Scanner myScanner = new Scanner(System.in);
		System.out.println("Input integer n");
		int n = myScanner.nextInt();

		// creating first unbalanced tree

		BinarySearchTree<Integer> S = new BinarySearchTree<Integer>();
		for (int i = 0; i<n; i++){
			S.insert(i);
		}
		//Recording time
		long startTime = System.nanoTime();
		S.contains(n-1);
		long endTime = System.nanoTime();
		System.out.println("Time taken in the skewed binary search tree: " + (endTime-startTime) + " nanoseconds");

		// set it back to 0
		startTime =0; 
		endTime =0;

		System.out.println("Input integer n");
		n = myScanner.nextInt();


		// creating second balanced tree

		BinarySearchTree<Integer> B = new BinarySearchTree<Integer>();
		BSTtest.seq(0,n,B);
		startTime = System.nanoTime();
		S.contains(n+1);
		endTime = System.nanoTime();
		System.out.println("Time taken in the balanced binary search tree: " + (endTime-startTime) + " nanoseconds");


		// bonus point

		// set it back to 0
		startTime =0; 
		endTime =0;

		System.out.println("Input long m");
		long m = myScanner.nextLong();

		BinarySearchTree<Long> BonusB = new BinarySearchTree<Long>();
		BSTtest.seq(0,m,BonusB);
		//BonusB.printTree();
		startTime = System.nanoTime();
		BonusB.contains(m+1, true);
		endTime = System.nanoTime();
		System.out.println("[Overriden contains()] Time taken in the balanced binary search tree: " + (endTime-startTime) + " nanoseconds");
		// bonus point part 2

		// set it back to 0

		startTime =0; 
		endTime =0; 
		System.out.println("Input long m");
		myScanner.close();

		BinarySearchTree<Long> BonusS = new BinarySearchTree<Long>();
		for (long i = 0; i<m; i++){
			BonusS.insert(i, true);
		}
		startTime = System.nanoTime();
		BonusS.contains(m+1, true);
		endTime = System.nanoTime();
		System.out.println("[Overriden contains()] Time taken in the skewed binary search tree: " + (endTime-startTime) + " nanoseconds");

	}


}