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
 Point()
 compareTo()
 direction()
 findRange()
 getX()
 getY()
 product()
 sameSegment()
 segmentsIntersect()
 set_x()
 set_y()
 subtract()
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
public class Point implements Comparable<Point> {
    public Segment parent;
    public int xcoord;
    public int ycoord;

    //Creating a point
    public Point(int x, int y) {
        this.xcoord = x;
        this.ycoord = y;
    }

    public static boolean sameSegment(Point pi, Point pj, Point pk) {

        if (Math.min(pi.getx(), pj.getx()) <= pk.getx()
                && Math.max(pi.getx(), pj.getx()) >= pk.getx()
                && Math.min(pi.gety(), pj.gety()) <= pk.gety()
                && Math.max(pi.gety(), pj.gety()) >= pk.gety()) {
            return true;
        }
        return false;
    }

    //methods to get x and y coordinates from a point

    public static int product(Point p1, Point p2) {
        return p1.getx() * p2.gety() - p2.getx() * p1.gety();
    }

    public static int direction(Point pi, Point pj, Point pk) {
        return product(pk.subtract(pi), pj.subtract(pi));
    }

    public static boolean segmentsIntersect(Point p1, Point p2, Point p3, Point p4) {
        int d1 = direction(p3, p4, p1);
        int d2 = direction(p3, p4, p2);
        int d3 = direction(p1, p2, p3);
        int d4 = direction(p1, p2, p4);

        if (((d1 > 0F && d2 < 0)
                || (d1 < 0 && d2 > 0))
                && ((d3 > 0 && d4 < 0)
                || (d3 < 0 && d4 > 0))
        ) {
            return true;
        } else if (Math.abs(d1) == 0 && sameSegment(p3, p4, p1)) {
            return true;
        } else if (Math.abs(d2) == 0 && sameSegment(p3, p4, p2)) {
            return true;
        } else if (Math.abs(d3) == 0 && sameSegment(p1, p2, p3)) {
            return true;
        } else if (Math.abs(d4) == 0 && sameSegment(p1, p2, p4)) {
            return true;
        }
        return false;
    }

    public int compareTo(Point p) {
        if (this.getx() == p.gety()) {
            return ((Integer) this.gety()).compareTo(p.gety());
        } else {
            return ((Integer) this.getx()).compareTo(p.getx());
        }
    }

    public int getx() {
        return this.xcoord;

    }

    public int gety() {
        return this.ycoord;
    }

    public void set_x(int new_x) {
        this.xcoord = new_x;
    }

    public void set_y(int new_y) {
        this.ycoord = new_y;
    }

    // PROBLEM 2
    public int findRange(Point[] Points) {
        int xMax = Integer.MIN_VALUE;
        int yMax = Integer.MIN_VALUE;
        int xMin = Integer.MAX_VALUE;
        int yMin = Integer.MAX_VALUE;

        for (Point point : Points) {
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

    public Point subtract(Point point) {
        return new Point(getx() - point.getx(), gety() - point.gety());
    }
}