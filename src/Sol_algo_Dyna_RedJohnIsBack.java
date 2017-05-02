import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

public class Sol_algo_Dyna_RedJohnIsBack {

	static final boolean _debug = "true".equals(System.getProperties().get("debug"));

	public static void main(String[] args) {

		if (args.length != 0) {
			System.err.println("in debug with debug = " + System.getProperties().get("debug"));
			// _debug = true; // set as final
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

		// aInput.add("1");
		// aInput.add("4");
		// aInput.add("7");
		// aInput.add("40");
		// aInput.add("40");
		// aInput.add("40");

		aInput.add("20");
		aInput.add("34");
		aInput.add("3");
		aInput.add("31");
		aInput.add("35");
		aInput.add("10");
		aInput.add("38");
		aInput.add("18");
		aInput.add("27");
		aInput.add("15");
		aInput.add("3");
		aInput.add("38");
		aInput.add("14");
		aInput.add("18");
		aInput.add("4");
		aInput.add("5");
		aInput.add("23");
		aInput.add("9");
		aInput.add("31");
		aInput.add("10");
		aInput.add("25");

		return aInput;
	}

	static int[] S = { 1, 4 }; // wall sizes
	static int maxN = 40;
	static int[] cache = null;

	static List<Integer> primes = new ArrayList<>();

	private static void doIt() {
		Scanner scan = new Scanner(System.in);

		int nbCases = scan.nextInt();
		int maxPoss = 0;
		List<Integer> possList = new ArrayList<>();
		// first get nb of possibilities for each n
		for (int caseNb = 0; caseNb < nbCases; caseNb++) {
			int n = scan.nextInt();
			int poss = getNbPoss(n);
			// debugln(poss);
			possList.add(poss);
			if (poss > maxPoss) {
				maxPoss = poss;
			}

		}
		scan.close();

		// get list of primes up to maxPoss (once)
		runEratosthenesSieve(maxPoss);

		// debugln(primes);

		// search closest match for each possibility
		for (int poss : possList) {
			int idx = Collections.binarySearch(primes, poss);
			if (idx >= 0) {
				println(idx + 1);
			} else {
				println(-idx - 1);
			}
		}

	}

	// get all primes up to upperBound efficiently and save them
	static public void runEratosthenesSieve(int upperBound) {
		int upperBoundSquareRoot = (int) Math.sqrt(upperBound);
		boolean[] isComposite = new boolean[upperBound + 1];
		for (int m = 2; m <= upperBoundSquareRoot; m++) {
			if (!isComposite[m]) {
				// System.out.print(m + " ");
				primes.add(m);
				for (int k = m * m; k <= upperBound; k += m)
					isComposite[k] = true;
			}
		}
		for (int m = upperBoundSquareRoot + 1; m <= upperBound; m++)
			if (!isComposite[m]) {
				// System.out.print(m + " ");
				primes.add(m);
			}
	}

	private static int getNbPoss(int n) {
		if (cache == null) {
			cache = new int[maxN + 1];
		}
		return getNbPoss(n, cache);
	}

	private static int getNbPoss(int n, int[] cache) {
		if (cache[n] != 0)
			return cache[n];

		if (n < 4) {
			cache[n] = 1;
			return 1;
		} else {
			int poss = 0;
			for (int s : S) {
				poss += getNbPoss(n - s);
			}
			cache[n] = poss;
			return cache[n];
		}
	}

	private static void debug(Object obj) {
		if (_debug) {
			System.err.print(obj.toString());
		}
	}

	private static void debugln(Object obj) {
		if (_debug) {
			System.err.println(obj.toString());
		}
	}

	private static void print(Object obj) {
		System.out.print(obj.toString());
	}

	private static void println(Object obj) {
		System.out.println(obj.toString());
	}

}
