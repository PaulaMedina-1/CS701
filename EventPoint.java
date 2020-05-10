
import java.util.ArrayList;
import java.util.Arrays;

public class EventPoint {
	//making two list, one with points, one with the segments.
	public ArrayList<Segment> segments;
	private double value;
	public Point currentpoint;
	public int Type; //is it start point, end?

public EventPoint (Point p, Segment s, int type) {
	this.currentpoint = p;
	this.segments = new ArrayList<>(Arrays.asList(s));
	this.points = new ArrayList<>(Arrays.asList(s.p1, s.p2));
	this.Type = type;
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
public void set_type(int type) {
    this.Type = type;
}

public int get_type() {
    return this.Type;
}







}



	
