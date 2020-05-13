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
 vebPriorityQueue()
 delete()
 emptyInsert()
 exchange()
 extractMax()
 high()
 increaseKey()
 Index()
 Insert()
 low()
 maximum()
 Minimum()
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

public class vEBtreePriorityQueue {
    private vEBtreePriorityQueue summary;
    private vEBtreePriorityQueue[] cluster;
    private int u;
    int sqroot = (int) Math.ceil(Math.sqrt(u));
    private int max;
    private int min;
    private int NIL = -1;

    //Constructing the tree. Minimum and maximum values are set to Nil when the tree is empty.
    //If universe is less than 2, summary is null and cluster [0].
    public vEBtreePriorityQueue(int u) {
        this.u = u;
        max = NIL;
        min = NIL;
        if (u <= 2) {
            summary = null;
            cluster = new vEBtreePriorityQueue[0];
        } else {
            int root = (int) Math.ceil(Math.sqrt(u));
            cluster = new vEBtreePriorityQueue[root];
            for (int i = 0; i < root; i++) {
                cluster[i] = new vEBtreePriorityQueue(root);
            }
            summary = new vEBtreePriorityQueue(root);
        }

    }

    // Next things I need to find would be high(gives the cluster) and low (gives the offset).
    public int high(int x) {
        return x / sqroot;
    }

    public int low(int x) {
        return x % sqroot;
    }


    // To use the Insert method from the book I first need to code a method to get the minimum and a method to exchange
    //the inserting item with the minimum in the tree

    public int Index(int i, int j) {
        return i * sqroot + j;
    }

    public int Minimum(vEBtreePriorityQueue myTree) {
        return myTree.min;
    }

    public int Maximum(vEBtreePriorityQueue myTree) {
        return myTree.max;
    }

    public void Exchange(int x, int min) {
        int temp = x;
        x = min;
        min = temp;
    }

    // In the book it is mentioned that the method to insert on an empty tree is different. You would just make the inserted
    //item the min and the max
    public void EmptyInsert(vEBtreePriorityQueue myTree, int x) {
        myTree.min = x;
        myTree.max = x;
    }

    public void Insert(vEBtreePriorityQueue myTree, int x) {
        if (myTree.min == NIL) {
            EmptyInsert(myTree, x);
        } else {
            if (x < myTree.min) {
                Exchange(x, myTree.min);
            }
            if (myTree.u > 2) {
                if (Minimum(myTree.cluster[high(x)]) == NIL) ;
                myTree.Insert(myTree.summary, high(x));
                myTree.EmptyInsert(myTree.cluster[high(x)], low(x));

            } else {
                myTree.Insert(myTree.cluster[high(x)], low(x));
            }
            if (x > myTree.max) {
                myTree.max = x;
            }
        }

    }

    //Noticed I can extract max without the method delete ( is in the book)

    public int ExtractMax(vEBtreePriorityQueue myTree) {
        int extract = myTree.max;
        Delete(myTree, extract);
        return extract;

    }

    public void Delete(vEBtreePriorityQueue myTree, int x) {
        if (myTree.min == myTree.max) {
            myTree.min = NIL;
            myTree.max = NIL;
        } else if (myTree.u == 2) {
            if (x == 0) {
                myTree.min = 1;

            } else myTree.min = 0;
            myTree.max = myTree.min;
        } else if (x == myTree.min) {
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
                    } else myTree.max = Index(summaryMax, Maximum(myTree.cluster[summaryMax]));
                }
            }
        } else if (x == myTree.max) {
            myTree.max = Index(high(x), Maximum(myTree.cluster[high(x)]));
        }


    }

    public void IncreaseKey(vEBtreePriorityQueue myTree, int value, int priority) {
        if (value < priority) {
            System.out.print("New key is smaller than current key");
        } else {
            myTree.Delete(myTree, value);
            myTree.Insert(myTree, priority);
        }
    }


}
