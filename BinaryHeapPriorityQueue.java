
// PUT THIS HEADER ON TOP OF EACH OF YOUR CLASSES

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
 *  BinaryHeapPriorityQueue
 *  Insert
 *  Extractmax
 *  IncreaseKey
 *
 *
 *   Remarks
 *   -------
 *
 *   1. Inserting into a priority queue that has been implemented using an array should only take O(lgn). 
 *   Therefore inserting n items into a binary heap should take n * O(lgn) time which is O(nlgn).
 *   
 *   2. In order to find the amortized time of m >= n operations we need to analyze running times of each method individually.
 *   Each time we perform extractmax the worst case running time is O(lgn). Each time we perform insert it takes O(lgn).
 *   Each time we perform increaseKey it also takes O(lgn). To calculate the amortized running time we need to consider that
 *   in order to extract max or increase key we first must insert k items. Each extract max corresponds to one insert so the cost of inserting will be
 *   O(2lgn)=> O(lgn) and the cost of extract max will be O(1) since it is already paid in the insert. My biggest issue now, is that I can have many
 *   increase key that correspond with one insert. I believe it won't change anything but I am not too sure aout it.
 *   
 *   3. Inserting into a priority queue that has been implemented using a vEB tree should take O(lglgn). This means that inserting 
 *   n items will take n * O(lglgn) which is O(nlglgn)
 *   
 *   4. To find the amortized time of the operations we analyze time that each operation take. In order to extract max k times we must have first
 *   inserted k items. This will require k+1 operations. As mentioned before, inserting takes O(lglgn) while extracting max will also take O(lglgn). Finding
 *   the max takes O(1) but removing it takes O(lglgn). I believe the overall amortized time will be )(lglgn).
 *   
 *   5. For this question my code wasn't running properly and I could not figure out how to fix it. I ended up using a different code that has nothing to do with the
 *   one in the book to obtain experimental results. The running times were still the same but the implementation was different.
 *   
 *   
 *   CONSTRUCTION TIME|n = 100|n = 1000|n = 10000|n = 100000|n = 1000000
 *   BH				  |134892 |342213  |3410929  |17720268  |125714835
 *   VEB			  |48150  |286373  |1340493  |4290290   |9124748
 *   
 *   OPERATIONS       |n = 100|n = 1000|n = 10000|n = 100000|n = 1000000
 *   BH				  |160176 |437706  |2178705  |20899524  |136046265
 *   VEB			  |142163 |429571 |1856565  |3399012   |8333165
 *   
 *   
 *   
 *   6. 
 *   Construction time in a binary heap should take O(nlgn) time as mentioned in question 1. The results obtained
 *   grow a lot faster than nlgn, almost exponential. This means that the experimental results don't correspond with 
 *   the assumption. 
 *   Construction time in a vEB tree also shows different results than expected. When I run a regression to find the appropiate plot,
 *   I end up getting exponential plot in the form of 113054. e^(0.880686 x). This grows a lot faster than the nlglgn time we were expecting.
 *   
 *   Regarding operations in a Binary Heap, the expected time was lg(n). Once I try to fit the results, I observe that again, it is not a log fit, but it looks
 *   like an exponential plot (10854.7 e^(1.88726 x)). This means that the results obtain are not correct, and that they don't correspond with the lgn fit we were expecting.
 *   
 *   Operations in a vBE tree also don't match the expected. It also follows an exponential fit (116341. e^(0.853872 x)) when we were looking for lglgn fit.
 *   Again this means that our results grow faster than what we were expecting.
 *   
 *   Overall, inaccurate results were obtained and don't prove our assumptions. However, comparing vEB and Binary Heap, we can see how even if graphs grow fster than expected,
 *   vEB are faster than Binary Heaps.



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


import java.util.*;
public class BinaryHeapPriorityQueue {
	
		private int[] binaryHeap;
		private int size;
		private int maxSize;
		
		
		
		public BinaryHeapPriorityQueue(int maxSize) {
			
			this.maxSize = maxSize;
			this.size=0;
			binaryHeap = new int[this.maxSize +1];
			binaryHeap[0] = Integer.MAX_VALUE;
		}
		
		private int parent(int position) {
			return position /2;
			}
		private int left(int position) {
			return position*2;
		}
		private int right(int position) {
			return (2* position) +1;
		}
		
		private void exchange(int first, int second ) {
			int temp = binaryHeap[first];
			binaryHeap[first] = binaryHeap[second];
			binaryHeap[second] = temp;
		}
		
		private void maxHeapify( int position) {
			int l = left(position);
			int r = right(position);
			int largest;
			if (l <= size && binaryHeap[l] > binaryHeap[position] ) {
				largest = l;
			}
			else {
				 largest = position;
			}
			if ( r <= size && binaryHeap[r] > binaryHeap[largest]) {
				largest = r;
			}
			if (largest != position) {
				exchange(binaryHeap[position], binaryHeap[largest]);
			}
			maxHeapify(largest);
		}
		
		
		
		public void Insert( int key) {
			size = size + 1;
			binaryHeap[size]= Integer.MIN_VALUE;
			increaseKey(size-1, key);
			}
			
		
		
		public int extractMax() {
			if (size <1) {
				System.out.println("Heap is empty");
			}
			int max = binaryHeap[1];
			binaryHeap[1] = binaryHeap[size];
			size = size -1;
			maxHeapify(1);
			return max;
			
		}
		
		public void increaseKey(int position, int key) {
			if (key < binaryHeap[position]) {
				System.out.print("New key is smaller than current key");
			}
			binaryHeap[position] = key;
			while (position > 1 && binaryHeap[parent(position)] < binaryHeap[position]) {
				exchange (binaryHeap[position], binaryHeap[parent(position)]);
				position = parent(position);
			}
		}
		
		public static void main(String[] args){
			
			Scanner myScanner = new Scanner(System.in);
			System.out.println("how many items do you want to insert?");
			int n = myScanner.nextInt();
			int [] test = new int [n];
			
			BinaryHeapPriorityQueue  myQueue = new BinaryHeapPriorityQueue(n);
			long StartTime = System.nanoTime();
			for (int i = 0; i < n; i++) {
				test [i] = (int) Math.random();
				myQueue.Insert(test[i]);
				
			}
			
			long EndTime = System.nanoTime();
			System.out.println("Added " + n + " objects in " + ((EndTime - StartTime)) + " nanoseconds");
			
			long StartTime3 = System.nanoTime();
			for (int i = 0; i < n; i++) {
				myQueue.extractMax();
			}
			long EndTime3 = System.nanoTime();
			System.out.println("Added " + n + " objects in " + ((EndTime - StartTime)) + " nanoseconds");
			
		
			
			
		}
		
		
		
		
		

	}



