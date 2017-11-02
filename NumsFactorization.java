import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;


public class NumsFactorization {

	static LinkedHashSet<Long> primeNums = new LinkedHashSet<Long>();

	// returns next prime number
	public static long getNextPrime(long cur) {
		long next = cur + 1;
		if (next % 2 == 0) {
			next++;
		}
		boolean isPrime = false;
		while (!isPrime) {
			for (long i : primeNums) {
				if ((i <= Math.sqrt(next)) && (next % i == 0)) {
					next += 2;
					break;

				} else if (i > Math.sqrt(next) || i == next) {
					isPrime = true;
					primeNums.add(next);
					break;

				}
			}
		}
		return next;
	}

	// main method
	public static List<? extends Number> doFactorization(long input) {
		long current = 2;
		long n = input;
		LinkedList<Long> factors = new LinkedList<Long>();
		while (n % current == 0) {
			factors.add(current);
			n /= current;
		}
		while (n != 1) {
			current = getNextPrime(current);
			while (n % current == 0) {
				factors.add(current);
				n /= current;
			}
			if ((Math.sqrt(input) < current) && (n != 1)) {
				factors.add(n);
				break;
			}
		}

		return factors;
	}

	public static String makeReadable(List<? extends Number> l) {
		String ans = "";
		Number prev = l.get(0);
		int counter = 1;
		ans += prev;
		for (int i = 1; i < l.size(); i++) {
			if (prev.equals(l.get(i))) {
				counter++;
			} else {
				if (counter > 1) {
					ans += "^" + counter;
					counter = 1;
					ans += "*" + (prev = l.get(i));
				} else {
					ans += "*" + (prev = l.get(i));
				}
			}
		}
		if (counter > 1) {
			ans += "^" + counter;
		}
		return ans;

	}

	public static void main(String[] args) {
		primeNums.add(2L);
		String answer = "";
		Scanner scn = new Scanner(System.in);
		try {
			long n = scn.nextLong();
			answer = makeReadable(doFactorization(n));

		} catch (Exception e) {
			answer = "Invalid input";
		} finally {
			scn.close();
			System.out.println(answer);
		}
	}

}
