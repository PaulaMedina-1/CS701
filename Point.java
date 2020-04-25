
public class Point {
	public double xcoord;
	public double ycoord;
	
	//Creating a point
	public Point(double x, double y) {
		this.xcoord = x;
		this.ycoord = y;
	}
	
	//methods to get x and y coordinates from a point
	
	public double getx() {
		return this.xcoord;
		
	}
	public double gety() {
		return this.ycoord;
	}
	
	public void set_x(double new_x) {
	    this.xcoord = new_x;
	  }
	  
	  public void set_y(double new_y) {
	    this.ycoord = new_y;
	  }
	
	
	

}
