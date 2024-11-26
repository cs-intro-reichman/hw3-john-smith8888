// Implements algebraic operations and the square root function without using 
// the Java operations a + b, a - b, a * b, a / b, a % b, and without calling 
// Math.sqrt. All the functions in this class operate on int values and
// return int values.

public class Algebra {
	public static void main(String args[]) {
	    // Tests some of the operations
	    // System.out.println(plus(2,3));   // 2 + 3
	    // System.out.println(minus(7,2));  // 7 - 2
   		// System.out.println(minus(2,7));  // 2 - 7
 		// System.out.println(times(3,4));  // 3 * 4
   		// System.out.println(plus(2,times(4,2)));  // 2 + 4 * 2
   		// System.out.println(pow(5,3));      // 5^3
   		// System.out.println(pow(3,5));      // 3^5
   		// System.out.println(div(12,3));   // 12 / 3    
   		// System.out.println(div(5,5));    // 5 / 5  
   		// System.out.println(div(25,7));   // 25 / 7
   		// System.out.println(mod(25,7));   // 25 % 7
   		// System.out.println(mod(120,6));  // 120 % 6    
   		// System.out.println(sqrt(36));
		// System.out.println(sqrt(263169));
   		// System.out.println(sqrt(76123));
		// TESTS IVE FAILED
		System.out.println(Algebra.times(-2, 3));
		System.out.println(Algebra.times(-2, -3));
		System.out.println(Algebra.pow(2, 0));
		System.out.println(Algebra.pow(-2, 3));
		System.out.println(Algebra.div(-15, 3));
		System.out.println(Algebra.div(-15, -3));
		System.out.println(Algebra.sqrt(36));
		System.out.println(Algebra.sqrt(0));
		System.out.println(Algebra.sqrt(1));
		System.out.println(Algebra.sqrt(263169));
		System.out.println(Algebra.sqrt(10000));

		boolean test1 = Algebra.sqrt(36) == 6;
        System.out.println("Test 1 (perfect square): " + (test1 ? "PASS" : "FAIL"));
        
        boolean test2 = Algebra.sqrt(0) == 0;
        System.out.println("Test 2 (zero): " + (test2 ? "PASS" : "FAIL"));
        
        boolean test3 = Algebra.sqrt(1) == 1;
        System.out.println("Test 3 (one): " + (test3 ? "PASS" : "FAIL"));
        
        boolean test4 = Algebra.sqrt(263169) == 513;
        System.out.println("Test 4 (large perfect square): " + (test4 ? "PASS" : "FAIL"));
        
        boolean test5 = Algebra.sqrt(76123) == 275;
        System.out.println("Test 5 (floor value): " + (test5 ? "PASS" : "FAIL"));
        
        boolean test6 = Algebra.sqrt(10000) == 100;
        System.out.println("Test 6 (power of 10): " + (test6 ? "PASS" : "FAIL"));

	}  

	// Returns x1 + x2
	public static int plus(int x1, int x2) {
		int x2abs = x2;
		if (x2<0) {
		 x2abs = -x2;
		}
		for(int i=0;i<x2abs;i++){
			if (x2<0) {
				x1--;
			}
			else x1++;
		}
		return x1;
	}

	// Returns x1 - x2
	public static int minus(int x1, int x2) {
		if (x2<0) {
			return plus(x1, -x2);
		}
		else
		for(int i=0;i<x2;i++){
			x1--;
		}
		return x1;
	}

	// Returns x1 * x2
	public static int times(int x1, int x2) {
		int tmp = 0;
		boolean isnegative = (x1<0&&x2>0)||(x2<0&&x1>0) ? true : false;
		if (x1<0) {
			x1 = minus(0, x1);
		}
		if (x2<0) {
			x2 = minus(0, x2);
		}
        for(int i=0 ; i<x1 ; i++){
		tmp = plus(tmp, x2);
		}
		if (isnegative) {
			return minus(0, tmp);
		}
		else 
		return tmp;
	}

	// Returns x^n (for n >= 0)
	public static int pow(int x, int n) {
		int tmp = x;
		if (n==0) {
			return 1;
		}
        for(int i=0 ; i<n-1 ; i++){
		tmp = times(tmp, x);
		}
		return tmp;
	}

	// Returns the integer part of x1 / x2 
	public static int div(int x1, int x2) {
		// Might be one of the wonkiest code I've ever written :(
		boolean isnegative = (x1<0&&x2>0)||(x2<0&&x1>0) ? true : false;
		if (x1<0) {
			x1 = minus(0, x1);
		}
		if (x2<0) {
			x2 = minus(0, x2);
		}
		int count = 0;
		int temp = x1;
		while (temp>=0) {
			temp = minus(temp, x2);
			count++;
		}
		// I am insane
		 if (times(count, x2)>x1) {
			count =  minus(count, 1);
		}
		else
		count =  plus(count, 1);

		if (isnegative) {
			return minus(0, count);
		}
		else 
		return count;
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
		if (x==0) {
			return 0;
		}
		if (x==1) {
			return 1;
		}
        for(int i = 1;i<div(x, 2);i++){
			if (times(i, i)==x) {
				return i;
			}
			else if (times(i+1, i+1)>x&&times(i-1, i-1)<x) {
				return i;
			}
		}
		return -1;
	}	  	  
}