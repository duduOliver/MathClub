
public class Line {
	private double slope;
	private double interceptOnY; // <== b = y - ax
	private Point A;
	private Point B;	

	public Line(Point a, Point b){
		A = a;
		B = b;
		slope = (b.getPointY() - a.getPointY())/(b.getPointX() - a.getPointX());
		interceptOnY = a.getPointY() - slope * a.getPointX();		                 
	}
	
	public Line(Point a, double rate){
		A = a;
		slope = rate;
		interceptOnY = a.getPointY() - slope * a.getPointX();		                 

	}
	
	public double getInterceptOnY(){
		return interceptOnY;
	}
	
	public double getSlope(){
		return slope;
	}
	
	// return the distance between two input points
	public double getLength(){
		return Math.sqrt( (A.getPointX() - B.getPointX())*(A.getPointX() - B.getPointX()) + (A.getPointY() - B.getPointY())*(A.getPointY() - B.getPointY()) );
	}
}
