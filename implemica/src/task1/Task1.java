package task1;

import java.util.Scanner;

public class Task1 {
	int counter;

	/**
	 * Method recursively adds left and right braces as far as bracket sequence is
	 * correct. If there are any left brackets - add one, continue recursion If
	 * adding right bracket does not cause syntax error we add it. Error caused when
	 * there are more right brakets then left ones
	 * 
	 * Time needed increases in geometric progression
	 */
	public void addParen(int leftRem, int rightRem, int count, int currentCounter) {

		this.counter = currentCounter;

		if (leftRem < 0 || rightRem < leftRem)
			// syntax error
			return;
		if (leftRem == 0 && rightRem == 0) {
			// there are no left brakets left
			this.counter++;
		} else {
			// add left bracket, if there are any
			if (leftRem > 0) {
				addParen(leftRem - 1, rightRem, count + 1, this.counter);
			}
			// add right bracket if it wont cause syntax error
			if (rightRem > leftRem) {
				addParen(leftRem, rightRem - 1, count + 1, this.counter);
			}
		}
	}

	/**
	 * Catalan number is the number of ways to correctly match n pairs of brackets.
	 */

	public int catalan(int n) {
		int res = 0;
		if (n <= 1) {
			return 1;
		}
		for (int i = 0; i < n; i++) {
			res += catalan(i) * catalan(n - i - 1);
		}
		return res;
	}

	public int bruteForce(int count) {
		addParen(count, count, 0, 0);
		return this.counter;
	}

	/**
	 * Compares results and shows time
	 */
	public static void main(String[] args) {

		try (Scanner scan = new Scanner(System.in)) {
			System.out.println("Please enter an integer or type 'quit' to exit.");
			String input = scan.next();
			if (!input.equals("quit")) {
				int number = 1;
				try {
					long time = System.currentTimeMillis();
					number = Integer.parseInt(input);
					if (number < 0) {
						throw new NumberFormatException();
					}
					System.out.println(new Task1().bruteForce(number));
					System.out.println("Brute Forse time took " + (System.currentTimeMillis() - time) + " ms");

					time = System.currentTimeMillis();
					System.out.println(new Task1().catalan(number));
					System.out.println("Catalin approach took " + (System.currentTimeMillis() - time) + " ms");

				} catch (NumberFormatException e) {
					System.out.println("Sorry wrong input");

				} finally {
					System.out.println();
					Task1.main(args);
				}

			}

		}
	}

}
