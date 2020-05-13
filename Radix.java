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

 countingsort()
 generatePoint()
 maximum()
 radixSort()
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
public class Radix {
    private int maximum(int points[][], int length) {
        int max = points[0][0];
        for (int i = 1; i < length; i++) {
            if (points[i][0] > max) {
                max = points[i][0];
            }
        }
        return max;
    }

    private int[][] generatePointMatrix(Point[] points) {
        int[][] pointMatrix = new int[2][points.length];
        int[] xValues = new int[points.length];
        int[] yValues = new int[points.length];
        for (int i = 0; i < points.length; i++) {
            xValues[i] = points[i].getx();
            yValues[i] = points[i].gety();
        }
        for (int i = 0; i <= points.length; i++) {
            pointMatrix[i][0] = xValues[i];
            pointMatrix[i][1] = yValues[i];
        }
        return pointMatrix;
    }

    private void countingSort(Point[] points, int length, int xVal) {
        int[][] pointMatrix = generatePointMatrix(points);
        int output[] = new int[length];

        int countpointMatrix[] = new int[10];
        for (int j = 0; j < countpointMatrix.length; j++) {
            countpointMatrix[j] = 0;
        }

        for (int i = 0; i < length; i++) {
            countpointMatrix[(pointMatrix[i][0] / xVal) % 10]++;
        }

        for (int i = 1; i < 10; i++) {
            countpointMatrix[i] += countpointMatrix[i - 1];

        }

        for (int i = length - 1; i >= 0; i--) {
            output[countpointMatrix[(pointMatrix[i][0] / xVal) % 10] - 1] = pointMatrix[i][0];
            countpointMatrix[(pointMatrix[i][0] / xVal) % 10]--;
        }
        for (int i = 0; i < length; i++) {
            pointMatrix[i][0] = output[i];

        }
    }


    public void radixSort(Point[] points, int length) {
        int[][] pointMatrix = new int[points.length][];
        for (int i = 0; i < length; i++) {
            int maximum = maximum(pointMatrix, length);
        }
    }
}
