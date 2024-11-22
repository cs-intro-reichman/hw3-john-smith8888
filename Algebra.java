// Implements algebraic operations and the square root function without using 
// the Java operations a + b, a - b, a * b, a / b, a % b, and without calling 
// Math.sqrt. All the functions in this class operate on int values and
// return int values.

public class Algebra {
	public static void main(String args[]) {
	    // Tests some of the operations
	    System.out.println(plus(2,3));   // 2 + 3
	    System.out.println(minus(7,2));  // 7 - 2
   		System.out.println(minus(2,7));  // 2 - 7
 		System.out.println(times(3,4));  // 3 * 4
   		System.out.println(plus(2,times(4,2)));  // 2 + 4 * 2
   		System.out.println(pow(5,3));      // 5^3
   		System.out.println(pow(3,5));      // 3^5
   		System.out.println(div(12,3));   // 12 / 3    
   		System.out.println(div(5,5));    // 5 / 5  
   		System.out.println(div(25,7));   // 25 / 7
   		System.out.println(mod(25,7));   // 25 % 7
   		System.out.println(mod(120,6));  // 120 % 6    
   		System.out.println(sqrt(36));
		System.out.println(sqrt(263169));
   		System.out.println(sqrt(76123));
	}  

	// Returns x1 + x2
	public static int plus(int x1, int x2) {
		for(int i=0;i<x2;i++){
			if (x2<0) {
				x1--;
			}
			else x1++;
		}
		return x1;
	}

	// Returns x1 - x2
	public static int minus(int x1, int x2) {
		for(int i=0;i<x2;i++){
			if (x2<0) {
				x1++;
			}
			else x1--;
		}
		return x1;
	}

	// Returns x1 * x2
	public static int times(int x1, int x2) {
		int tmp = 0;
        for(int i=0 ; i<x1 ; i++){
		tmp = plus(tmp, x2);
		}
		return tmp;
	}

	// Returns x^n (for n >= 0)
	public static int pow(int x, int n) {
		int tmp = x;
        for(int i=0 ; i<n-1 ; i++){
		tmp = times(tmp, x);
		}
		return tmp;
	}

	// Returns the integer part of x1 / x2 
	public static int div(int x1, int x2) {
		// Might be one of the wonkiest code I've ever written :(
		int count = 0;
		int temp = x1;
		while (temp>0) {
			temp = minus(temp, x2);
			count++;
		}
		//sanity check
		if (x1==times(count, x2)) {
			return count;
		}
		// I am insane
		else if (times(count, x2)>x1) {
			return count-1;
		}
		else
		return count+1;
	}

	// Returns x1 % x2
	public static int mod(int x1, int x2) {
		int divisions = 0;
		int temp = x1;
		divisions = div(x1, x2);
		temp = minus(temp, times(x2, divisions));
		return temp;
	}	

	// Returns the integer part of sqrt(x) 
	public static int sqrt(int x) {
        for(int i = 1;i<div(x, 2);i++){
			if (times(i+1, i+1)==x) {
				return i;
			}
			else if (times(i+1, i+1)>=x&&times(i-1, i-1)<=x) {
				return i;
			}
		}
		return -1;
	}	  	  
}