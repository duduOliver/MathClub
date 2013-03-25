import java.text.DecimalFormat;


public class Triangle {
	private Point A;
	private Point B;
	private Point C;
	private Edge AB;
	private Edge AC;
	private Edge BC;
	private Line BAl;
	private Line CAl;
	private Line BCl;
	private Vector vAB;
	private Vector vAC;
	private Vector vBC;
	private Vector vBA;
	private Vector vCB;
	private Vector vCA;
	private Line bisectorA;
	private Line bisectorB;
	private Line bisectorC;
	private Point[] newBCplusA = new Point[3];

	public Triangle(double ax, double ay, double bx, double by, double cx, double cy){
		A = new Point (ax, ay);
		B = new Point (bx, by);
		C = new Point (cx, cy);
		AB = new Edge (A, B);
		AC = new Edge (A, C);
		BC = new Edge (B, C);	
		vAB = new Vector (A, B);
		vAC = new Vector (A, C);
		vBC = new Vector (B, C);
		vBA = new Vector (B, A);
		vCA = new Vector (C, A);
		vCB = new Vector (C, B);
		bisectorA = new Line (A, BC.getMidPoint());
		bisectorB = new Line (B, AC.getMidPoint());
		bisectorC = new Line (C, AB.getMidPoint());	
	}
	
	public Triangle(Point a, Point b, Point c){
		A = a;
		B = b;
		C = c;
		AB = new Edge (A, B);
		AC = new Edge (A, C);
		BC = new Edge (B, C);	
		vAB = new Vector (A, B);
		vAC = new Vector (A, C);
		vBC = new Vector (B, C);
		vBA = new Vector (B, A);
		vCA = new Vector (C, A);
		vCB = new Vector (C, B);
		bisectorA = new Line (A, BC.getMidPoint());
		bisectorB = new Line (B, AC.getMidPoint());
		bisectorC = new Line (C, AB.getMidPoint());	
	}
	
	public Triangle(double b, double c){
		B = new Point(-0.5,0);
		C = new Point(0.5,0);
		BAl = new Line(B, Math.tan(b));
    	CAl = new Line(C, -Math.tan(c));
    	BCl = new Line(B, C);    	
    	double tempX = ((BAl.getInterceptOnY() - CAl.getInterceptOnY())/(CAl.getSlope() - BAl.getSlope()));
    	A = new Point(tempX, BAl.getSlope()*tempX + BAl.getInterceptOnY());		
    	AB = new Edge (A, B);
		AC = new Edge (A, C);
		BC = new Edge (B, C);	
		vAB = new Vector (A, B);
		vAC = new Vector (A, C);
		vBC = new Vector (B, C);
		vBA = new Vector (B, A);
		vCA = new Vector (C, A);
		vCB = new Vector (C, B);
		bisectorA = new Line (A, BC.getMidPoint());
		bisectorB = new Line (B, AC.getMidPoint());
		bisectorC = new Line (C, AB.getMidPoint());	
	}
	
	public Point[] shortestEdgePoints(){
		Edge temp = null;
		if(AB.getLength() < BC.getLength()){
			temp = AB;
			if(temp.getLength() < AC.getLength()){
				newBCplusA[0] = temp.getLeftP();
				newBCplusA[1] = temp.getRightP();
				newBCplusA[2] = C;
			}
			else{
				newBCplusA[0] = AC.getLeftP();
				newBCplusA[1] = AC.getRightP();
				newBCplusA[2] = B;
			}
		}
		else {
			temp = BC;
			if(temp.getLength() < AC.getLength()){
				newBCplusA[0] = temp.getLeftP();
				newBCplusA[1] = temp.getRightP();
				newBCplusA[2] = A;
			}
			else{
				newBCplusA[0] = AC.getLeftP();
				newBCplusA[1] = AC.getRightP();
				newBCplusA[2] = B;
			}
		}
		
		return newBCplusA;
	}
	
	//Find the shortest bisector 
	public double shortestBisector(){
		double temp;
		if (bisectorA.getLength() < bisectorB.getLength()){
			if (bisectorA.getLength() < bisectorC.getLength()){
				temp = bisectorA.getLength();
			}
			else {
				temp = bisectorC.getLength();
			}
		}
		else{
			if (bisectorB.getLength() < bisectorC.getLength()){
				temp = bisectorB.getLength();
			}
			else {
				temp = bisectorC.getLength();
			}
		}		
		return temp;
	}
	
	public Edge getAB(){
		return AB;
	}
	
	public Edge getAC(){
		return AC;
	}
	
	public Edge getBC(){
		return BC;
	}
	
	public Point getA(){
		return A;
	}
	
	public Point getB(){
		return B;
	}
	
	public Point getC(){
		return C;
	}
	
	public Vector getVAB(){
		return vAB;
	}
	
	public Vector getVAC(){
		return vAC;
	}
	public Vector getVBA(){
		return vBA;
	}
	public Vector getVBC(){
		return vBC;
	}
	public Vector getVCA(){
		return vCA;
	}
	public Vector getVCB(){
		return vCB;
	}
	public Line getBisectorA(){
		return bisectorA;
	}
	public Line getBisectorB(){
		return bisectorB;
	}
	public Line getBisectorC(){
		return bisectorC;
	}
	
	
	public double cosOfVectors(Vector a, Vector b){
		return ((a.getX()*b.getX() + a.getY()*b.getY())/(a.getLength()*b.getLength()));
	}
	
	public double acosOfVectors(Vector a, Vector b){		
		return Math.toDegrees(Math.acos(cosOfVectors(a, b)));
	}
	
	public Point queryOutPoint(Point o, Point v, double s, Line k){
		
		double x1L;
		double x1R;
		double x2L;
		double x2R;
		Edge OV = new Edge(o, v);
		double outTolen = OV.getLength() + s;
		
        if (Double.isInfinite(k.getSlope()) ){
    		 x1L = 0;
    		 x1R = o.getPointY() + outTolen;
    		 x2L = 0;
    		 x2R = o.getPointY() - outTolen;
        }
        else{

		double a = 1 + k.getSlope()*k.getSlope();
		double b = (-2)*o.getPointX() + 2*k.getSlope()*(k.getInterceptOnY() - o.getPointY());
		double c = o.getPointX()*o.getPointX() + (k.getInterceptOnY() - o.getPointY())*(k.getInterceptOnY() - o.getPointY()) - outTolen*outTolen;
		double delta = b*b - 4*a*c;
		 x1L = (-b + Math.sqrt(delta))/(2*a);
		 x1R = k.getSlope()*x1L + k.getInterceptOnY();
		 x2L = (-b - Math.sqrt(delta))/(2*a);
		 x2R = k.getSlope()*x2L + k.getInterceptOnY();
        }
		Point p1 = new Point(fiveDecimals(x1L), fiveDecimals(x1R));
		Point p2 = new Point(fiveDecimals(x2L), fiveDecimals(x2R));
		
		
		//System.out.print("---"+Double.MAX_VALUE+"---");//debug
		//System.out.print("---"+fiveDecimals(234.999825462356)+"---");//debug
		//System.out.print("---"+p1.getPointX() + ", " + p1.getPointY() + ", " + p2.getPointX() + ", " + p2.getPointY()+"---");//debug
		
		
		Edge VP1 = new Edge(v, p1);
		Edge VP2 = new Edge(v, p2);
		if (-0.01 < (VP1.getLength() - s) && (VP1.getLength() - s)< 0.01){
			return p1;
		}
		else if (-0.01 < (VP2.getLength() - s) && (VP2.getLength() - s)< 0.01){
			return p2;
		}
		else{
			System.out.print("got wrong");
			return null;
		}	
	}
	
	public Point queryInPoint(Point o, Point v, double s, Line k){
		
		double x1L;
		double x1R;
		double x2L;
		double x2R;
		Edge OV = new Edge(o, v);
		double inTolen = OV.getLength() - s;
		
        if (Double.isInfinite(k.getSlope()) ){
    		 x1L = 0;
    		 x1R = o.getPointY() + inTolen;
    		 x2L = 0;
    		 x2R = o.getPointY() - inTolen;
        }
        else{

		double a = 1 + k.getSlope()*k.getSlope();
		double b = (-2)*o.getPointX() + 2*k.getSlope()*(k.getInterceptOnY() - o.getPointY());
		double c = o.getPointX()*o.getPointX() + (k.getInterceptOnY() - o.getPointY())*(k.getInterceptOnY() - o.getPointY()) - inTolen*inTolen;
		double delta = b*b - 4*a*c;
		 x1L = (-b + Math.sqrt(delta))/(2*a);
		 x1R = k.getSlope()*x1L + k.getInterceptOnY();
		 x2L = (-b - Math.sqrt(delta))/(2*a);
		 x2R = k.getSlope()*x2L + k.getInterceptOnY();
        }
		Point p1 = new Point(fiveDecimals(x1L), fiveDecimals(x1R));
		Point p2 = new Point(fiveDecimals(x2L), fiveDecimals(x2R));
		Edge VP1 = new Edge(v, p1);
		Edge VP2 = new Edge(v, p2);
		if (-0.0001 < (VP1.getLength() - s) && (VP1.getLength() - s)< 0.0001){
			return p1;
		}
		else if (-0.0001 < (VP2.getLength() - s) && (VP2.getLength() - s)< 0.0001){
			return p2;
		}
		else{
			System.out.print("got wrong");
			return null;
		}	
	}
	
	/*Round to 5 decimal*/
	double fiveDecimals(double d) {
        DecimalFormat fiveDForm = new DecimalFormat("#.#####");
    return Double.valueOf(fiveDForm.format(d));
}
	
	/*put the shortest edge of a triangle on the X-axis, in the same while, 
	 * put the opposite corner on Y-axis.(It's not be used.)*/
	public double[] newTriAngles(){
		double[] tri = new double [3];
		Vector nBC = new Vector(newBCplusA[0], newBCplusA[1]);
		Vector nBA = new Vector(newBCplusA[0], newBCplusA[2]);
		Vector nCA = new Vector(newBCplusA[1], newBCplusA[2]);
		double cosB = cosOfVectors(nBC, nBA);
		double cosC = cosOfVectors(nBC, nCA);
		double aY = nBA.getLength()*Math.sqrt(1 - cosB*cosB);
		double bX = -(Math.abs(cosB*nBA.getLength()));
		double cX = 0;
		if (Math.abs(bX + nBC.getLength()) == Math.abs(cosC*nCA.getLength())){
			cX = bX + nBC.getLength(); 
		}
		else if(Math.abs(bX - nBC.getLength()) == Math.abs(cosC*nCA.getLength())){
			cX = bX - nBC.getLength();
		}
		else{
			System.out.print("I got wrong!");
		}
		tri[0] = aY;
		tri[1] = bX;
		tri[2] = cX;
		
		return tri;
	}	
}
