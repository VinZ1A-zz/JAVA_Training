package hackerRank;
import java.io.ByteArrayInputStream;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.Vector;

public class Sol_ctci_primality {

	static boolean _debug = false;
	static long _checks = 0;

	public static void main(String[] args) {

		if (args.length != 0) {
			System.err.println("in debug");
			_debug = true;
			// _fromEclipse = true;
			String aInput = "";
			for (String data : getData()) {
				aInput += data + "\r\n";
			}
			System.setIn(new ByteArrayInputStream(aInput.getBytes()));
		}

		doIt();

	}

	// debug method - discard!
	static private Vector<String> getData() {
		Vector<String> aInput = new Vector<String>();

		aInput.add("4");
		aInput.add("12");
		aInput.add("5");
		aInput.add("7");
		aInput.add("536870909");

		return aInput;
	}

	private static void doIt() {
		Scanner scan = new Scanner(System.in);

		int p = scan.nextInt();
		// int[] listInts = new int[p];
		// int max = Integer.MIN_VALUE;
		for (int a0 = 0; a0 < p; a0++) {
			int n = scan.nextInt();
			// listInts[a0] = n;
			// if (n > max) {
			// max = n;
			// }
			System.out.println(isPrimeOSqrtN(n) ? "Prime" : "Not prime");
			if (_debug)
				System.out.println("nb of checks " + _checks);
		}

		// System.err.println(Arrays.toString(listInts));

		scan.close();

		// Set<Integer> listOfSumOfPrimes = listOfPrimes(max);
		// if (_debug)
		// System.out.println("nb of checks " + _checks);
		// for (int candidate : listInts) {
		// if (listOfSumOfPrimes.contains(candidate)) {
		// System.out.println("Prime");
		// } else {
		// System.out.println("Not prime");
		// }
		// }

		// System.out.println(Integer.MAX_VALUE); //
		// https://en.wikipedia.org/wiki/2147483647_(number)

	}

	// O(n or n/2) ?

	// other attempt but useless
	private static Set<Integer> listOfPrimes(int n) {

		Set<Integer> res = new TreeSet<Integer>();
		// double upTo = Math.pow(10, n) - 1;
		double upTo = n;

		res.add(2);
		res.add(3);
		for (int i = 3; i <= upTo; i = i + 2) {
			_checks++;
			if (i % 3 == 0) {
				continue;
			}
			_checks++;
			double maxSearch = Math.sqrt(i);
			boolean isPrime = true;
			for (double nb : res) {
				if (nb > maxSearch)
					break;
				_checks++;
				if (i % nb == 0) {
					isPrime = false;
					break;
				}
			}
			if (isPrime) {
				res.add(i);
			}
		}

		return res;
	}

	// O(sqrt(n))
	private static boolean isPrimeOSqrtN(int n) {
		_checks = 0;
		if (n == 1) // 1 no prime
			return false;
		if (n == 2) // 2 prime
			return true;
		if (n % 2 == 0)
			return false; // odd nb can't be prime

		int maxTest = (int) Math.floor(Math.sqrt(n));
		for (int i = 3; i <= maxTest; i = i + 2) {
			_checks++;
			if (n % i == 0) {
				// System.err.println("divisible by " + i);
				return false;
			}
		}

		return true;
	}
}
