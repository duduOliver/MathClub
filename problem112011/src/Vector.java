import java.lang.Math;

public class Vector {
	private double x;
	private double y;
	private double tan; // tangent

	public Vector(Point a, Point b){
		x = b.getPointX() - a.getPointX();
		y = b.getPointY() - a.getPointY();
	}
	
	public Vector(double a, double b){
		x = a;
		y = b;
		tan = b/a;
	}
	
	
	public double getX(){
		return x;
	}
	public double getY(){
		return y;
	}
	public double getTan(){
		return tan;
	}
	public void setX(double a){
		x = a;
	}
	public void setY(double b){
		y = b;
	}
	
	
	public double getLength(){
		return Math.sqrt(x*x + y*y);
	}
	
}
