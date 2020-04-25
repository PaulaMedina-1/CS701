
import java.io.*; 
 public class PQueue {
	
	private Point[] binaryHeap;
	
	public static void MinHeapify(Point binaryHeap[], int n, int i) 
    { 
        int smallest = i; // Smallest value as root 
        int left = 2 * i + 1; // left = 2*i + 1 
        int right = 2 * i + 2; // right = 2*i + 2 
  
         
        if (left < n && binaryHeap[left].compareTo(binaryHeap[smallest]) == -1) {  
            smallest = left; 
            }
  
        
        if (right < n && binaryHeap[right].compareTo(binaryHeap[smallest]) == -1) 
            smallest = right; 
  
        
        if (smallest != i) { 
            Point temp = binaryHeap[i]; 
            binaryHeap[i] = binaryHeap[smallest]; 
            binaryHeap[smallest] = temp; 
  
            //Recursive method
            MinHeapify(binaryHeap, n, smallest); 
        } 
    } 
	 public static void heapSort(Point binaryHeap[], int n) 
	    { 
	        // Build heap 
	        for (int i = n / 2 - 1; i >= 0; i--) 
	            MinHeapify(binaryHeap, n, i); 
	  
	        
	        for (int i = n - 1; i >= 0; i--) { 
	              
	            
	            Point temp = binaryHeap[0]; 
	            binaryHeap[0] = binaryHeap[i]; 
	            binaryHeap[i] = temp; 
	  
	            
	            MinHeapify(binaryHeap, i, 0); 
	        } 
	    } 
	
  
	

}