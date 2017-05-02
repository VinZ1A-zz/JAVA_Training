package OWN_Exercices;
import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Vector;

public class OWN_Recurs_CoinChange {

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

		aInput.add("7 2");
		aInput.add("1 4");

		// aInput.add("12 4");
		// aInput.add("1 5 10 25");

		// expects 15685693751
		// aInput.add("250 24");
		// aInput.add("41 34 46 9 37 32 42 21 7 13 1 24 3 43 2 23 8 45 19 30 29 18
		// 35 11");

		return aInput;
	}

	// yes, second time I'm doing it
	// that's why it's called TRAINING
	// and that's the proof I suck in recursion. (for now)
	private static void doIt() {
		Scanner scan = new Scanner(System.in);

		int amount = scan.nextInt();
		int nbOfCoinValues = scan.nextInt();
		int[] coinValues = new int[nbOfCoinValues];
		for (int i = 0; i < nbOfCoinValues; i++) {
			coinValues[i] = scan.nextInt();
		}
		scan.close();

		Arrays.sort(coinValues); // has to be sorted!
		long[][] cache = new long[amount + 1][coinValues.length];
		long res = getWaysOfGettingChange(amount, coinValues, coinValues.length - 1, cache);

		println(res);

	}

	private static long getWaysOfGettingChange(int amount, int[] coinValues, int maxCoinValueIdx, long[][] cache) {
		// base cases
		if (amount == 0)
			return 1;
		if (amount < 0 || maxCoinValueIdx < 0)
			return 0;

		// general case
		if (cache[amount][maxCoinValueIdx] != 0) // memoize previous call stacks
			return cache[amount][maxCoinValueIdx];

		long ways = 0;
		// take highest value
		int highestCoinVal = coinValues[maxCoinValueIdx];
		int mult = 0;
		int amountWithLargestCoin = mult * highestCoinVal;
		while (amountWithLargestCoin <= amount) {
			if (amountWithLargestCoin == amount) {
				ways++; // could simplify (base case) but not as readable
			} else {
				int remainder = amount - amountWithLargestCoin;
				// debugln("getting remainder " + remainder + " with " +
				// (maxCoinValueIdx - 1));
				ways = ways + getWaysOfGettingChange(remainder, coinValues, maxCoinValueIdx - 1, cache);
			}
			mult++;
			amountWithLargestCoin = mult * highestCoinVal;
		}

		cache[amount][maxCoinValueIdx] = ways;
		return ways;

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
