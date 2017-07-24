package hackerRank;
import java.io.ByteArrayInputStream;
import java.util.Scanner;
import java.util.Vector;

public class Sol_WeekCode28_GreatXOR {

	static boolean _debug = false;

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

		// 1 5
		aInput.add("2"); // 2 queries
		aInput.add("2"); // x=2 --> 1
		aInput.add("10"); // x=10 --> 5
		for (int i = 15; i <= 2000; i += 5) {
			aInput.add(String.valueOf(i));
		}
		aInput.add("128");
		aInput.add("256");
		aInput.add("512");
		aInput.add("1024");

		return aInput;
	}

	private static void doIt() {
		Scanner in = new Scanner(System.in);
		in.nextInt(); // rien a fout

		coolProp(in);

		// test(in);

		// bourrin(in);
	}

	// private static void test(Scanner in) {
	// // double logXOfTwo = Math.log(2);
	// long x = 128;
	// // long closestPowOfTwoPlusOne = (long) Math.pow(2, (int) ((Math.log(x) /
	// // logXOfTwo)) + 1);
	// // closestPowOfTwoPlusOne = (long) Math.pow(2, Math.ceil(Math.log(x) /
	// // Math.log(2)));
	//
	// System.out.println(getClosestPowOfTwoPlusOne(x));
	// }

	// bit twiddling hack - working but not actally faster than the log one
	private static long getClosestPowOfTwoPlusOne(long x) {
		long v = x;
		v |= v >> 1;
		v |= v >> 2;
		v |= v >> 4;
		v |= v >> 8;
		v |= v >> 16;
		return ++v;
	}

	// naive, costly(?) and might not even always work(?)
	private static long getClosestPowOfTwoPlusOne(long x, double logXOfTwo) {
		return (long) Math.pow(2, (int) ((Math.log(x) / logXOfTwo)) + 1);
	}

	// using cool prop observed after bourrin tests
	private static void coolProp(Scanner in) {
		double logXOfTwo = Math.log(2);
		long closestPowOfTwoPlusOne;
		long x, nbOfMatches;
		while (in.hasNext()) {
			x = in.nextLong();

			closestPowOfTwoPlusOne = (long) Math.pow(2, (int) ((Math.log(x) / logXOfTwo)) + 1);
			nbOfMatches = closestPowOfTwoPlusOne - x - 1;
			// nbOfMatches = getClosestPowOfTwoPlusOne(x) - x - 1;
			System.out.println(/* x + " --> " + */nbOfMatches);
		}
	}

	// private static void bourrin(Scanner in) {
	// while (in.hasNext()) {
	// long x = in.nextLong();
	//
	// int nbOfMatches = 0;
	// for (int i = 1; i < x; i++) {
	// long xOr = i ^ x;
	// if (xOr > x) {
	// nbOfMatches++;
	// }
	// }
	// System.out.println(x + " --> " + nbOfMatches);
	// }
	// }
}
