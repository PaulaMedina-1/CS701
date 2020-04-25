
public class Segment {
public Point p1;
public Point p2;


public Segment () {
	this.p1 = p1;
	this.p2 = p2;
}

public Segment(double x0, double y0, double x1, double y1) {
    p1 = new Point(x0, y0);
    p2 = new Point(x1, y1);
    Segment s = new Segment(p1, p2);
    this.p1 = s.p1;
    this.p2 = s.p2;

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



Segment(Point p1, Point p2) {
    if (p1.getx() < p2.getx()) {
      this.p1 = p1;
      this.p2 = p2;
    }
    if (p2.getx() < p1.getx()) {
      this.p1 = p2;
      this.p2 = p1;
    }
    if (p1.getx() == p2.getx()) {
      if (p1.gety() < p2.gety()) {
        this.p1 = p1;
        this.p2 = p2;
      } else {
        this.p1 = p2;
        this.p2 = p1;
      }
    }
    
  }



public Point getp1() {
	return this.p1;
}
public Point getp2() {
	return this.p2;
	
}
}
