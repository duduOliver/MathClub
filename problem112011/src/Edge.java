
public class Edge {
	private Point leftP;
	private Point rightP;
	private Point mid;
	private double length;
	
	
	public Edge(Point a, Point b){
		leftP = a;
		rightP = b;
		mid = new Point((a.getPointX() + b.getPointX())/2, (a.getPointY() + b.getPointY())/2);
		length = Math.sqrt( (a.getPointX() - b.getPointX())*(a.getPointX() - b.getPointX()) + (a.getPointY() - b.getPointY())*(a.getPointY() - b.getPointY()) );
	}
	
	public double getLength(){
		return length;
	}
	
	
	public Point getMidPoint(){
		return mid;
	}
	
	public Point getLeftP(){
		return leftP;
	}
	
	public Point getRightP(){
		return rightP;
	}
}
