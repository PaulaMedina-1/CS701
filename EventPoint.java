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
 EventPoint()
 Add_point()
 add_segment()
 get_point()
 get_segment()
 get_type()
 set_type()
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

import java.util.ArrayList;
import java.util.Arrays;

public class EventPoint {
    //making two list, one with points, one with the segments.
    public ArrayList<Segment> segments;
    public Point currentpoint;
    public int Type; //is it start point, end?
    private double value;

    public EventPoint(Point p, Segment s, int type) {
        this.currentpoint = p;
        this.segments = new ArrayList<>(Arrays.asList(s));
        this.value = p.getx();
        this.Type = type;
    }

    public EventPoint(Point p, ArrayList<Segment> s, int type) {
        this.currentpoint = p;
        this.segments = s;
        this.value = p.getx();
        this.type = type;
    }


    public void add_segment(Segment s) {
        this.segments.add(s);
    }

    public ArrayList<Segment> get_segments() {
        return this.segments;
    }

    public void add_point(Point p) {
        this.currentpoint = p;
    }

    public Point get_point() {
        return this.currentpoint;
    }

    public int get_type() {
        return this.Type;
    }

    public void set_type(int type) {
        this.Type = type;
    }


}



	
