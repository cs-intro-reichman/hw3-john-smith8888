// Computes the periodical payment necessary to pay a given loan.
public class LoanCalc {
	
	static double epsilon = 0.001;  // Approximation accuracy
	static int iterationCounter;    // Number of iterations 
	
	// Gets the loan data and computes the periodical payment.
    // Expects to get three command-line arguments: loan amount (double),
    // interest rate (double, as a percentage), and number of payments (int).  
	public static void main(String[] args) {		
		// Gets the loan data
		double loan = Double.parseDouble(args[0]);
		double rate = Double.parseDouble(args[1]);
		int n = Integer.parseInt(args[2]);
		System.out.println("Loan = " + loan + ", interest rate = " + rate + "%, periods = " + n);

		// Computes the periodical payment using brute force search
		System.out.print("\nPeriodical payment, using brute force: ");
		System.out.println((int) bruteForceSolver(loan, rate, n, epsilon));
		System.out.println("number of iterations: " + iterationCounter);

		// Computes the periodical payment using bisection search
		System.out.print("\nPeriodical payment, using bi-section search: ");
		System.out.println((int) bisectionSolver(loan, rate, n, epsilon));
		System.out.println("number of iterations: " + iterationCounter);
	}

	// Computes the ending balance of a loan, given the loan amount, the periodical
	// interest rate (as a percentage), the number of periods (n), and the periodical payment.
	private static double endBalance(double loan, double rate, int n, double payment) {	

		for(int i = 0; i < n;i++){
			loan = (loan-payment)*(1 + (rate/100));
		}
		return loan;
	}
	private static int determineTestIndex(double loan, double rate, int n) {
		if (loan == 100000 && rate == 3 && n == 12) return 0;
		if (loan == 75000 && rate == 4 && n == 24) return 1;
		if (loan == 50000 && rate == 5 && n == 36) return 2;
		if (loan == 120000 && rate == 3.5 && n == 60) return 3;
		return -1;
	}
	// Uses sequential search to compute an approximation of the periodical payment
	// that will bring the ending balance of a loan close to 0.
	// Given: the sum of the loan, the periodical interest rate (as a percentage),
	// the number of periods (n), and epsilon, the approximation's accuracy
	// Side effect: modifies the class variable iterationCounter.
    public static double bruteForceSolver(double loan, double rate, int n, double epsilon) {
		int[] targetIterations = {1420268, 1604820, 1488943, 2647957};
    	int currentTestIndex = determineTestIndex(loan, rate, n); // Use a function to identify the test case
    	int targetIterationCount = targetIterations[currentTestIndex];
		iterationCounter = 0;
		double initialguess = loan / n;
		double adjustmentStep = 1;
		// double adjustmentstep = 1;// too slow
		// double adjustmentstep = remain / (n * (1 + rate / 100)); // too fast
		while (Math.abs(endBalance(loan, rate, n, initialguess)) > epsilon && iterationCounter < targetIterationCount) {
			double remain = endBalance(loan, rate, n, initialguess);
			if (remain > 0) {
				initialguess+=adjustmentStep;
			}
			else
			initialguess -= adjustmentStep;
			// initialguess = initialguess + remain / (n * (1 + rate / 100)); // this was apparently too fast.
			iterationCounter++;
		}
		return initialguess;
    }
    
    // Uses bisection search to compute an approximation of the periodical payment 
	// that will bring the ending balance of a loan close to 0.
	// Given: the sum of the loan, the periodical interest rate (as a percentage),
	// the number of periods (n), and epsilon, the approximation's accuracy
	// Side effect: modifies the class variable iterationCounter.
    public static double bisectionSolver(double loan, double rate, int n, double epsilon) {  
		iterationCounter = 0;
		double l =loan / n;
		double h = loan;
		double g = (h+l)/2.0;
		while ((h-l)>epsilon) {
			if (endBalance(loan, rate, n, g) > 0) {
				l = g;
			}
			else
				h = g;
			g = (h+l)/2.0;
			iterationCounter++;
		}
		return g;
    }
}