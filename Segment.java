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

    public int findU(Point [] points){
        int xMax = Integer.MIN_VALUE;
        int yMax = Integer.MIN_VALUE;
        int xMin = Integer.MAX_VALUE;
        int yMin = Integer.MAX_VALUE;
        for(Point point : points){
            if (point.getx() > xMax){
                xMax = point.getx();
            }if (point.gety()  > yMax){
                yMax = point.gety();
            }if(point.getx()< xMin ){
                xMin = point.getx();
            }if(point.gety() < yMin){
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
    public void set_value(int value) {
        this.value = value;
    }

    public int get_value() {
        return this.value;
    }

}
