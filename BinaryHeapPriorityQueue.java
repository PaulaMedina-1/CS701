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
 Exchange()
 ExtractMax()
 IncreaseKey()
 Insert()
 Left()
 MaxHeapify)
 Parent()
 Right()
 binaryHeap()
 maxSize()
 size()
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

public class BinaryHeapPriorityQueue {

    private int[] binaryHeap;
    private int size;
    private int maxSize;


    public BinaryHeapPriorityQueue(int maxSize) {

        this.maxSize = maxSize;
        this.size = 0;
        binaryHeap = new int[this.maxSize + 1];
        binaryHeap[0] = Integer.MAX_VALUE;
    }

    private int parent(int position) {
        return position / 2;
    }

    private int left(int position) {
        return position * 2;
    }

    private int right(int position) {
        return (2 * position) + 1;
    }

    private void exchange(int first, int second) {
        int temp = binaryHeap[first];
        binaryHeap[first] = binaryHeap[second];
        binaryHeap[second] = temp;
    }

    private void maxHeapify(int position) {
        int l = left(position);
        int r = right(position);
        int largest;
        if (l <= size && binaryHeap[l] > binaryHeap[position]) {
            largest = l;
        } else {
            largest = position;
        }
        if (r <= size && binaryHeap[r] > binaryHeap[largest]) {
            largest = r;
        }
        if (largest != position) {
            exchange(binaryHeap[position], binaryHeap[largest]);
        }
        maxHeapify(largest);
    }


    public void Insert(int key) {
        size = size + 1;
        binaryHeap[size] = Integer.MIN_VALUE;
        increaseKey(size - 1, key);
    }


    public int extractMax() {
        if (size < 1) {
            System.out.println("Heap is empty");
        }
        int max = binaryHeap[1];
        binaryHeap[1] = binaryHeap[size];
        size = size - 1;
        maxHeapify(1);
        return max;

    }

    public void increaseKey(int position, int key) {
        if (key < binaryHeap[position]) {
            System.out.print("New key is smaller than current key");
        }
        binaryHeap[position] = key;
        while (position > 1 && binaryHeap[parent(position)] < binaryHeap[position]) {
            exchange(binaryHeap[position], binaryHeap[parent(position)]);
            position = parent(position);
        }
    }


}



