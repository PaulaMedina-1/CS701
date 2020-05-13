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

 segment()
 calculate_value()
 endPoint()
 findU()
 get_value()
 getP1()
 getp2()
 set_value()
 startPoint()
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
public abstract class Segment implements Comparable<Segment> {
    public Point p1;
    public Point p2;
    public int value;


    public Segment(Point p1, Point p2) {
        this.p1 = p1;
        this.p1.parent = this;
        this.p2 = p2;
        this.p2.parent = this;
        this.calculate_value(this.startPoint().getx());
        if (p1.getx() > p2.getx()) {
            this.p1 = p2;
            this.p2 = p1;
        }
    }

    public int findU(Point[] points) {
        int xMax = Integer.MIN_VALUE;
        int yMax = Integer.MIN_VALUE;
        int xMin = Integer.MAX_VALUE;
        int yMin = Integer.MAX_VALUE;
        for (Point point : points) {
            if (point.getx() > xMax) {
                xMax = point.getx();
            }
            if (point.gety() > yMax) {
                yMax = point.gety();
            }
            if (point.getx() < xMin) {
                xMin = point.getx();
            }
            if (point.gety() < yMin) {
                yMin = point.gety();
            }
        }
        return Math.max(xMax - xMin, yMax - yMin);

    }

    public Point startPoint() {
        if (p1.getx() <= p2.getx()) {
            return p1;
        } else {
            return p2;
        }
    }

    public Point endPoint() {
        if (p1.getx() <= p2.getx()) {
            return p2;
        } else {
            return p1;
        }
    }


    public Point getp1() {
        return this.p1;
    }

    public Point getp2() {
        return this.p2;

    }

    public void calculate_value(int value) {
        double x1 = this.startPoint().getx();
        double x2 = this.endPoint().getx();
        double y1 = this.startPoint().gety();
        double y2 = this.endPoint().gety();
        this.value = (int) (y1 + (((y2 - y1) / (x2 - x1)) * (value - x1)));
    }

    public int get_value() {
        return this.value;
    }

    public void set_value(int value) {
        this.value = value;
    }

}
