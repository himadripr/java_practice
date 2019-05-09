package org.himadri.practice.java_practice.java_examples;

public class Powerlogn {
	public static void main(String[] args) {
		System.out.println(powerloop(3, 6));
	}
	
	public static int powerloop(int x, int y){
		int res = 1;     // Initialize result 
		   
	    while (y > 0) 
	    { 
	        // If y is odd, multiply x with result 
	        if ((y & 1) == 1)
	            res = res*x; 
	   
	        // n must be even now 
	        y = y>>1; // y = y/2 
	        if (y>0){
	        	//to avoid unnecessary multiplication.
	        	x = x*x;  // Change x to x^2 
	        }
	        
	    } 
	    return res; 
	}
	
	public static int power(int x, int y){
		if (y==0){
			return 1;
		}
		int temp = power(x,  y/2);
		if ((y&1)==0){
			return temp*temp;
		} else {
			return x*temp*temp;
		}
	}
	
	static float power(float x, int y) 
    { 
        float temp; 
        if( y == 0) 
            return 1; 
        temp = power(x, y/2);  
          
        if (y%2 == 0) 
            return temp*temp; 
        else
        { 
            if(y > 0) 
                return x * temp * temp; 
            else
                return (temp * temp) / x; 
        } 
    } 
}
