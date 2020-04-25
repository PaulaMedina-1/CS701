
public class Point implements Comparable<Point>{
	public Segment parent;
	public int xcoord;
	public int ycoord;
	
	//Creating a point
	public Point(int x, int y) {
		this.xcoord = x;
		this.ycoord = y;
	}
	public int compareTo(Point p){
	    if(this.getx() == p.gety()){
	        return ((Integer)this.gety()).compareTo(p.gety());
        }else{
            return ((Integer)this.getx()).compareTo(p.getx());
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


}
