/*************************************************************************
 *
 *  Pace University
 *  Spring 2020
 *  Advanced Algorithms
 *
 *  Course: CS 701-801
 *  Name: PPaula Hernandez Medina
 *  Collaborators: None
 *  References: Introduction to Algorithms book. I also needed some guidance on how to start the tree so took a look at GeeksForGeeks.
 *  Assignment number 1
 *  Problem: Using a binary heap and a vEB tree to implement a priority queue. Comparing running times between both data structures
 *  as well as comparing assumptions with experimental results.
 *  Description: Implementing priority queues using binary heap and vEB. For the binary heap even though we discussed
 *  min heaps in class, I used max heap since the methods in the book were done for max heap. I also wrote assumptions of 
 *  running times and compared those with experimental values after running the code. 
 *
 *  Input: random values inserted into the priority queues
 *  Output: time taken to finish operations.
 *
 *  Visible data fields:
 *  COPY DECLARATION OF VISIBLE DATA FIELDS HERE
 *
 *  Visible methods:
 *  vEBtreePriorityQueue
 *  high
 *  low
 *  index
 *  minimum
 *  maximum
 *  emptyInsert
 *  ExtractMax
 *  ExtractMin
 *  Delete
 *  IncreaseKey
 *  
 *
 *
 *   Remarks
 *   -------
 *   
 *    
 *   
 *   
 *   
 *   
 *   
 *   
 *
 *
 *************************************************************************/


import java.util.Scanner;

public class vEBtreePriorityQueue {
	private vEBtreePriorityQueue summary;
	private vEBtreePriorityQueue [] cluster;
	private int u;
	private int max;
	private int min;
	int sqroot = (int) Math.ceil(Math.sqrt(u));
	
	private int NIL = -1;
	
	//Constructing the tree. Minimum and maximum values are set to Nil when the tree is empty. 
	//If universe is less than 2, summary is null and cluster [0].
	public vEBtreePriorityQueue (int u){
		this.u = u;
		max = NIL;
		min = NIL;
		if (u <= 2) {
			summary = null;
			cluster = new vEBtreePriorityQueue[0];
		}
		else {
			int root = (int) Math.ceil(Math.sqrt(u));
			cluster = new vEBtreePriorityQueue[root];
			for (int i=0; i< root; i++) {
				cluster[i] = new vEBtreePriorityQueue(root);
			}
			summary = new vEBtreePriorityQueue(root);
		}
		
	}
	
	
	// Next things I need to find would be high(gives the cluster) and low (gives the offset).
	public int high(int x) {
		return x/sqroot;
	}
	
	public int low(int x) {
		return x % sqroot;
	}
	
	public int Index(int i, int j) {
		return i * sqroot + j;
	}
	
	
	// To use the Insert method from the book I first need to code a method to get the minimum and a method to exchange
	//the inserting item with the minimum in the tree
	
	public int Minimum( vEBtreePriorityQueue myTree) {
		return myTree.min;
	}
	public int Maximum( vEBtreePriorityQueue myTree) {
		return myTree.max;
	}
	
	public void Exchange(int x, int min) {
		int temp =x;
		x = min;
		min = temp;
	}
	
	// In the book it is mentioned that the method to insert on an empty tree is different. You would just make the inserted
	//item the min and the max
	public void EmptyInsert(vEBtreePriorityQueue myTree, int x) {
		myTree.min = x;
		myTree.max = x;
	}
	
	public void Insert (vEBtreePriorityQueue myTree, int x) {
		if ( myTree.min == NIL ) {
			EmptyInsert(myTree, x);
		}
		else {
			if (x < myTree.min) {
				Exchange(x, myTree.min);
			}
			if(myTree.u > 2) {
				if (Minimum(myTree.cluster[high(x)])== NIL);
				myTree.Insert(myTree.summary, high(x));
				myTree.EmptyInsert(myTree.cluster[high(x)], low(x));
					
				}
			else {
				myTree.Insert(myTree.cluster[high(x)], low(x));
			}
			if (x > myTree.max) {
				myTree.max = x;
			}
		}
		
	}
	
	
	public int ExtractMax(vEBtreePriorityQueue myTree) {
		int extract = myTree.max;
		Delete( myTree, extract);
		return extract;
		
	}
	
	//Noticed I can extract max without the method delete ( is in the book)
	
	public void Delete( vEBtreePriorityQueue myTree, int x) {
		if (myTree.min == myTree.max) {
			myTree.min = NIL;
			myTree.max = NIL;
		}
		else if  (myTree.u == 2){
			if (x == 0) {
				myTree.min = 1;
				
			}
			else myTree.min = 0;
			myTree.max = myTree.min;
		}
		else if (x == myTree.min) {
			int firstCluster = Minimum(myTree.summary);
			x = Index(firstCluster, Minimum(myTree.cluster[firstCluster]));
			myTree.min = x;
			
			Delete(myTree.cluster[high(x)], low(x));
			if (Minimum(myTree.cluster[high(x)]) == NIL) {
				Delete(myTree.summary, high(x));
				if (x == max) {
					int summaryMax = Maximum(myTree.summary);
					if (summaryMax == NIL) {
						myTree.max = myTree.min;
					}
					else myTree.max = Index(summaryMax, Maximum( myTree.cluster[summaryMax]));
				}
			}
		}
		else if (x == myTree.max){
			myTree.max = Index(high(x), Maximum(myTree.cluster[high(x)]));
		}
		
		
	}
	
	
	
	public void IncreaseKey( vEBtreePriorityQueue myTree, int value, int priority) {
		if ( value < priority) {
			System.out.print("New key is smaller than current key");
		}
		else {
			myTree.Delete(myTree, value);
			myTree.Insert(myTree, priority);
		}
	}
	
	public static void main(String[] args){
		
		Scanner myScanner = new Scanner(System.in);
		System.out.println("how many items do you want to insert?");
		int n = myScanner.nextInt();
		int [] test1 = new int [n];
		
		vEBtreePriorityQueue  mytree = new vEBtreePriorityQueue(n);
		long StartTime1 = System.nanoTime();
		for (int i = 0; i < n; i++) {
			test1 [i] = (int) Math.random();
			mytree.Insert(mytree, test1[i]);
			
		}
		
		long EndTime1 = System.nanoTime();
		System.out.println("Added " + n + " objects in " + ((EndTime1 - StartTime1)) + " nanoseconds");
		
		long StartTime2 = System.nanoTime();
		for (int i = 0; i < n; i++) {
		mytree.ExtractMax(mytree);
	}
		long EndTime2 = System.nanoTime();
		System.out.println("Added " + n + " objects in " + ((EndTime2 - StartTime2)) + " nanoseconds");
		
	}
	
	
	

}
