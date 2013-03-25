import java.util.StringTokenizer;
import java.io.*;
import java.lang.Math;

/*Basis: Given two random angles, and corner B(-1, 0), corner C(1, 0)
 * Input format: <B, <C, <A, times*/

public class CoordinatesMethod {
	public static void main(String[] args)throws IOException
	{ 
		Point A;
		Point B = new Point(-0.5, 0);
		Point C = new Point(0.5, 0);
		Point[] tempAng;
		double tempX;
		Point[] newVertex = new Point[3];
		
		double shortestLength;
		double moveLength;
		
		Line BA;
		Line CA;
		Line BC;
		
		Triangle tri;
		
		BufferedReader inFileName;
	    StringTokenizer tokenizer;
	    String fileNameIn = null;
	    String fileNameOut = null;
	    double angleAd;
	    double angleBd;
	    double angleCd;
	    double num;
	    double angleAr;
	    double angleBr;
	    double angleCr;
	    
	    /*read and implement a triangle*/
	    System.out.print("Triangle Name: ");    	
    	inFileName = new BufferedReader(new InputStreamReader(System.in));
    	fileNameIn = inFileName.readLine();
    	FileInputStream inputFile = new FileInputStream(fileNameIn); 
    	InputStreamReader inStream = new InputStreamReader(inputFile);
    	BufferedReader stdin = new BufferedReader(inStream);
      	tokenizer = new StringTokenizer(stdin.readLine());
      	angleBd = Double.parseDouble(tokenizer.nextToken());
      	angleCd = Double.parseDouble(tokenizer.nextToken());
      	angleAd = Double.parseDouble(tokenizer.nextToken());
      	num = Double.parseDouble(tokenizer.nextToken());
      	stdin.close();
      	
      	/*initial a triangle */
      	angleBr = Math.toRadians(angleBd);
      	angleCr = Math.toRadians(angleCd);
      	tri = new Triangle(angleBr, angleCr);
      	/*angleBr = Math.toRadians(angleBd);
      	angleCr = Math.toRadians(angleCd);
      	angleAr = Math.toRadians(angleAd); (old method, could be thrown)
    	BA = new Line(B, Math.tan(angleBr));
    	CA = new Line(C, -Math.tan(angleCr));
    	BC = new Line(B, C);    	
    	tempX = ((BA.getInterceptOnY() - CA.getInterceptOnY())/(CA.getSlope() - BA.getSlope()));
    	A = new Point(tempX, BA.getSlope()*tempX + BA.getInterceptOnY());
        tri = new Triangle(A, B, C);
        */
        
        //tempAng = tri.shortestEdgePoints();
        //shortestLength = (new Edge(tempAng[0], tempAng[1])).getLength();
        //moveLength = shortestLength/10;
        fileNameOut = "ResultOf" + fileNameIn;
        PrintWriter out = new PrintWriter(new FileWriter(fileNameOut));
        out.println("A(deg)				B(deg)				C(deg)" +
        		"				A( , )				B( , )				C( , )");
        	
        while (num>0){	
        out.print(tri.acosOfVectors(tri.getVAB(), tri.getVAC())+"		");
        out.print(tri.acosOfVectors(tri.getVBC(), tri.getVBA())+"		");
        out.print(tri.acosOfVectors(tri.getVCA(), tri.getVCB())+"		");
        out.print("( "+tri.getA().getPointX()+" , "+ tri.getA().getPointY()+" )"+"		");
        out.print("( "+tri.getB().getPointX()+" , "+ tri.getB().getPointY()+" )"+"		");
        out.println("( "+tri.getC().getPointX()+" , "+ tri.getC().getPointY()+" )"+"		");
        
        
        /*Find the shortest length using edge
        tempAng = tri.shortestEdgePoints();
        shortestLength = (new Edge(tempAng[0], tempAng[1])).getLength();
        moveLength = shortestLength/10;
        */
        
        
        /*Find the shortest length using bisector*/
        shortestLength = tri.shortestBisector();
        moveLength = shortestLength/10;
        
        
        ///*For outward
        newVertex[0] = tri.queryOutPoint(tri.getBC().getMidPoint(), tri.getA(), moveLength, tri.getBisectorA());
        newVertex[1] = tri.queryOutPoint(tri.getAC().getMidPoint(), tri.getB(), moveLength, tri.getBisectorB());
        newVertex[2] = tri.queryOutPoint(tri.getAB().getMidPoint(), tri.getC(), moveLength, tri.getBisectorC());
        //*/
        
        /*//For inward
        newVertex[0] = tri.queryInPoint(tri.getBC().getMidPoint(), tri.getA(), moveLength, tri.getBisectorA());
        newVertex[1] = tri.queryInPoint(tri.getAC().getMidPoint(), tri.getB(), moveLength, tri.getBisectorB());
        newVertex[2] = tri.queryInPoint(tri.getAB().getMidPoint(), tri.getC(), moveLength, tri.getBisectorC());
        */
        
        
        /*//For inward using bisector's length
        newVertex[0] = tri.queryInPoint(tri.getBC().getMidPoint(), tri.getA(), moveLength, tri.getBisectorA());
        newVertex[1] = tri.queryInPoint(tri.getAC().getMidPoint(), tri.getB(), moveLength, tri.getBisectorB());
        newVertex[2] = tri.queryInPoint(tri.getAB().getMidPoint(), tri.getC(), moveLength, tri.getBisectorC());
        //*/
        
        tri = new Triangle(newVertex[0], newVertex[1], newVertex[2]);//initial a new tri with new vertices
        /* put the tri back to axis*/
        tri = new Triangle(tri.acosOfVectors(tri.getVBC(), tri.getVBA()), tri.acosOfVectors(tri.getVCA(), tri.getVCB()));
        
        num--;
        }
        out.close();
	}
}
