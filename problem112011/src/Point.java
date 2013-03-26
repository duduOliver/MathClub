import java.lang.Math;

public class Point {
	private double[] d2Point = {0,0};
	private double[] midPoint = {0,0};
	private double ptop;
	
	public Point(double x, double y){
		d2Point[0] = x;
		d2Point[1] = y;
		
	}
	public double pointsDistance(double[] x, double[] y){
		ptop = Math.sqrt( (x[0] - y[0])*(x[0] - y[0]) + (x[1] - y[1])*(x[1] - y[1]) );
		return ptop;
	}
	
	public double[] midTwoPoints(double[] x, double[] y){
		midPoint[0] = (x[0] + y[0])/2;
		midPoint[1] = (x[1] + y[1])/2;
		return midPoint;
	}
	public double getPointX(){
		return d2Point[0];
	}
	
	public double getPointY(){
		return d2Point[1];
	}
	
	public void setPtoP(double distance){
		ptop = distance;
	}
}
