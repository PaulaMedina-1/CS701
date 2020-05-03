public class Point implements Comparable<Point> {
    public Segment parent;
    public int xcoord;
    public int ycoord;

    //Creating a point
    public Point(int x, int y) {
        this.xcoord = x;
        this.ycoord = y;
    }

    public int compareTo(Point p) {
        if (this.getx() == p.gety()) {
            return ((Integer) this.gety()).compareTo(p.gety());
        } else {
            return ((Integer) this.getx()).compareTo(p.getx());
        }
    }

    //methods to get x and y coordinates from a point

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

    public boolean sameSegment(Point pi, Point pj, Point pk) {

        if (Math.min(pi.getx(), pj.getx()) <= pk.getx()
                && Math.max(pi.getx(), pj.getx()) >= pk.getx()
                && Math.min(pi.gety(), pj.gety()) <= pk.gety()
                && Math.max(pi.gety(), pj.gety()) >= pk.gety()) {
            return true;
        }
        return false;
    }

    public int product(Point p1, Point p2) {
        return p1.getx() * p2.gety() - p2.getx() * p1.gety();
    }

    public int direction(Point pi, Point pj, Point pk) {
        return product(pk.subtract(pi), pj.subtract(pi));
    }


    public boolean segmentsIntereesct(Point p1, Point p2, Point p3, Point p4) {
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
}