/*************************************************************************
 *
 *  Pace University
 *  Spring 2020
 *  Advanced Algorithms
 *
 *  Course: CS 701-801
 *  Name: Paula Hernandez Medina, Daniel Berekdar
 *  Collaborators: Carmine gave us some steps to follow for the implementation.
 *  References: Introduction to Algorithms book.
 *  Problem: Implementing the Sweep line algorithm
 *  Description: IImplementing sweep line algorithm to find if in a set of segments there are any intersections.
 *  The implementation has t be done using a Priority Queue(heapsort) and a tree for the first part of the assignment and radix
 *  sort and VEBtree for the second part. The next part of the assignment was implementing a method to find the rage of x-values
 *  and y-values. Depending on this range implement the intersection using either heapsort and AVL or radix sort and VEBtree. Lastly, we had to implement
 *  two different sets (smooth and sparse and run the part two algorithm to compare running times between the sets.
 *
 *  Input: Set of segments defined by two endpoints
 *  Output: True if there is an intersection, false if segments don't intersect.
 *
 *  Visible data fields:
 *  COPY DECLARATION OF VISIBLE DATA FIELDS HERE
 *
 *  Visible methods:
 *
 extractMin()
 Heapsort()
 insert(0
 minHeapify()
 *
 *nanoseconds n = 102 n = 103 n = 104 n = 105 n = 106
 * Smooth set   n/a     n/a     n/a     n/a     n/a
 * Sparse set   n/a     n/a     n/a     n/a     n/a
 *
 *What is the best function of n and/or u fitting your
 * measurements? Notice that you are evaluating rate of growth, you are
 * not being asked which one is faster. Are the results consistent with
 * the query time of the data structures and algorithms used? Justify
 * explaining the running time of each.
 *
 * In relation to a smooth set and a sparse set, the expected results are such
 * that the sparse set will expect to perform better
 * due to the nature of  the smooth set, we  are storing based on the index of the element.
 * As a result of the bit array in the sparse set,
 * as the input size increases we expect to see that the rate of growth should
 * be dramatically reduced. The sparse set with the bit array will asymptotically
 * grow at a significantly slower rate than that of the smooth set.
 *
 *************************************************************************/

public class PQueue {

    int size;
    private Point[] binaryHeap;
    int n = binaryHeap.length;

    public static void MinHeapify(Point binaryHeap[], int n, int i) {
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

    public static void heapSort(Point binaryHeap[], int n) {
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

    public void insert(Point p) {
        if (size == n) {
            return;
        } else {
            size++;
            binaryHeap[size - 1] = p;
            MinHeapify(binaryHeap, n, n - 1);
        }
        size++;
    }


    public Point extractMin() {
        Point minimum = this.binaryHeap[0];
        ;
        this.binaryHeap[0] = this.binaryHeap[this.size - 1];
        --this.size;
        this.MinHeapify(binaryHeap, n, 0);

        return minimum;
    }


}
