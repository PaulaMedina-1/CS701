import com.sun.source.tree.Tree;

import java.util.TreeSet;

public class CS701 {
    public static void main(String[] args) {
        Point a = new Point(2,3);
        Point b = new Point (2,2);
        System.out.println(a.compareTo(b));

        TreeSet<Point> poinntTreeSet = new TreeSet<>();

        poinntTreeSet.lower(1);


    }
}
