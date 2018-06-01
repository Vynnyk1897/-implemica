package task3;

import java.math.BigInteger;

public class Task3 {

	/**
	 * Finds factorial
	 * 
	 */

	private static BigInteger factorial(int n) {
		BigInteger fact = BigInteger.ONE;

		for (int i = 2; i <= n; i++) {
			fact = fact.multiply(BigInteger.valueOf(i));
		}
		return fact;
	}

	/**
	 * Gets sum of factorial number
	 * 
	 */
	public static long getSum(int n) {
		BigInteger fact = factorial(n);
		long sum = 0;
		while (fact.compareTo(BigInteger.ZERO) > 0) {
			sum += fact.mod(BigInteger.TEN).longValue();
			fact = fact.divide(BigInteger.TEN);
		}
		return sum;
	}

	public static void main(String[] args) {
		System.out.println(getSum(100));
	}

}