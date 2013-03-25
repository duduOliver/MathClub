
public class RandomPolygon {
	private Vector dot[];
	private Vector quadrant1[]; // xPostive-axis(not include) to yPostive-axis(not include)
	private Vector quadrant2[]; // yPostive-axis(not include) to xNegative-axis(not include)
	private Vector quadrant3[]; // xNegative-axis(not include) to yNegative-axis(not include)
	private Vector quadrant4[]; // yNegative-axis(not include) to xPostive-axis(not include)
	//private int counter1 = 0;
	//private int counter2 = 0;
	//private int counter3 = 0;
	//private int counter4 = 0;
	
	private Vector centerMass;
	
	public RandomPolygon(int n)
	{
		boolean flag = true;
		dot = new Vector[n];
		quadrant1 = new Vector[n+1];
		quadrant2 = new Vector[n+1];
		quadrant3 = new Vector[n+1];
		quadrant4 = new Vector[n+1];
		//it's used to count the number of elements in the array
		quadrant1[0] = new Vector(0, 0);
		quadrant2[0] = new Vector(0, 0);
		quadrant3[0] = new Vector(0, 0);
		quadrant4[0] = new Vector(0, 0);
		
		centerMass = new Vector(0, 0);
		
		//get n random points
		while(flag){
		for(int i = 0; i < n; i++)
		{
			dot[i] = new Vector(Math.random()*100, Math.random()*100);
			while (dot[i].getX() != 0 && dot[i].getY() != 0){
				dot[i] = new Vector(Math.random()*100, Math.random()*100);
			}
			centerMass.setX(dot[i].getX()+centerMass.getX());
			centerMass.setY(dot[i].getY()+centerMass.getY());
		}
		
		//compute the center mass 
		centerMass.setX(centerMass.getX()/n);
		centerMass.setY(centerMass.getY()/n);
		
		//update all the points due to the new centerMass 
		//and distribute all the n Points into 4 quadrants
		for (int i = 0; i < n; i++){
			dot[i].setX(dot[i].getX() - centerMass.getX());
			dot[i].setY(dot[i].getY() - centerMass.getY());
			
			if(dot[i].getX()>0){
				
				if(dot[i].getY() > 0){
					quadrant1[0].setX(quadrant1[0].getX() + 1);
					quadrant1[(int)quadrant1[0].getX()] = dot[i];
				}
				else{
					quadrant4[0].setX(quadrant4[0].getX() + 1);
					quadrant4[(int)quadrant4[0].getX()] = dot[i];
				}
			}
			else{
				if(dot[i].getY() > 0){
					quadrant2[0].setX(quadrant2[0].getX() + 1);
					quadrant2[(int)quadrant2[0].getX()] = dot[i];
				}
				else{
					quadrant3[0].setX(quadrant3[0].getX() + 1);
					quadrant3[(int)quadrant3[0].getX()] = dot[i];
				}
			}
		}
		
		//order n points according to the counter-clockwise 		
		quadrant1 = copyOfRange(quadrant1, 1, (int)quadrant1[1].getX());
		quadrant2 = copyOfRange(quadrant2, 1, (int)quadrant2[1].getX());
		quadrant3 = copyOfRange(quadrant3, 1, (int)quadrant3[1].getX());
		quadrant4 = copyOfRange(quadrant4, 1, (int)quadrant4[1].getX());
		
		if(!quickSort(quadrant1)&&!quickSort(quadrant2)&&!quickSort(quadrant3)&&!quickSort(quadrant4)){
			flag = false;
		}
		
		}
		dot = merge(quadrant1, quadrant2, quadrant3, quadrant4);
	}
	
	public boolean quickSort(Vector array[]) 
	// pre: array is full, all elements are non-null integers
	// post: the array is sorted in ascending order
	{
		boolean flag = false;
		
		flag = quickSort(array, 0, array.length - 1); // quicksort all the elements in the array
		return flag;
	}


	public boolean quickSort(Vector array[], int start, int end)
	{
		boolean flag = false;
	    int i = start;                          // index of left-to-right scan
	    int k = end;                            // index of right-to-left scan
	    Search:
	    if (end - start >= 1)                   // check that there are at least two elements to sort
	    {	        
	    	Vector pivot = array[start];       // set the pivot as the first element in the partition
		        while (k > i){                  // while the scan indices from left and right have not met,
		        	while (array[i].getTan() <= pivot.getTan() && i <= end && k > i){	// from the left, look for the first
		                  if (array[i].getTan() == pivot.getTan()){
		                    flag = true;
		                    break Search;
		                   }
		        	}
		        	i++;  // element greater than the pivot
		        	while (array[k].getTan() >= pivot.getTan() && k >= start && k >= i){	// from the right, look for the first
		        		if (array[i].getTan() == pivot.getTan()){
		        			flag = true;
		        			break Search;
		        		}
		        	}
		        	k--;                          // element not greater than the pivot
		       		if (k > i)                    // if the left seekindex is still smaller than
		       			swap(array, i, k);                      // the right index, swap the corresponding elements
		       	}
	    	swap(array, start, k);          // after the indices have crossed, swap the last element in
	                                      // the left partition with the pivot 
	    	flag = quickSort(array, start, k - 1); // quicksort the left partition
	    	flag = quickSort(array, k + 1, end);   // quicksort the right partition
	    }
	    else{    // if there is only one element in the partition, do not do any sorting
	    	return flag;                     // the array is sorted, so exit
	    }
	    return flag;
	    
	}
	        
	public void swap(Vector array[], int index1, int index2) 
	// pre: array is full and index1, index2 < array.length
	// post: the values at indices 1 and 2 have been swapped
	{
		Vector temp = array[index1];           // store the first value in a temp
		array[index1] = array[index2];      // copy the value of the second into the first
		array[index2] = temp;               // copy the value of the temp into the second
	}
	
	//cope
	public Vector[] copyOfRange(Vector[] original, int from, int to){
		int tempCounter = to - from + 1 ;
		Vector temp[] = new Vector[tempCounter];
		for(int i = 0; i < tempCounter; i++){
			temp[i] = original[from + i];
		}
		return temp;
	}
	
	//merge 4 quadrants, a for 1, b for 2, c for 3, d for 4 quadrants
	public Vector[] merge(Vector[] q1, Vector[] q2, Vector[] q3, Vector[] q4){
		Vector[] comb = new Vector[q1.length + q2.length + q3.length + q4.length];
		int index = 0;

		for(int i = 0; i < q1.length - 1; i++){
				comb[index] = q1[i];
				index++;
			}
			for(int i = 0; i < q2.length - 1; i++){
				comb[index] = q2[i];
				index++;
			}
			for(int i = 0; i < q3.length - 1; i++){
				comb[index] = q3[i];
				index++;
			}
			for(int i = 0; i < q4.length - 1; i++){
				comb[index] = q4[i];
				index++;
			}
		
		return comb;
	}
}
