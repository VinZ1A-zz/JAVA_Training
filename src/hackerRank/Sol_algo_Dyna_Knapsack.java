package hackerRank;
import java.io.ByteArrayInputStream;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.Vector;

public class Sol_algo_Dyna_Knapsack {

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

		aInput.add("7"); // X test cases

		// expects 12 (6+6) -- OK
		// aInput.add("3 12"); // 3 ints, sum to reach = 12
		// aInput.add("1 6 9");

		// expects 9 (3+3+3) - OK
		// aInput.add("5 9"); // try to get 9
		// aInput.add("3 4 4 4 8");

		aInput.add("3 9"); // 9 OK
		aInput.add("3 2 4");
		aInput.add("3 12"); // 12 OK
		aInput.add("3 10 4");
		aInput.add("3 13"); // 13 - OK
		aInput.add("3 10 4");
		aInput.add("3 16"); // 16 - OK
		aInput.add("3 10 4");
		aInput.add("3 2000"); // 2000 - OK
		aInput.add("2000 2000 2000");
		aInput.add("3 9"); // 9 - ok
		aInput.add("9 9 9");
		aInput.add("3 8"); // 0
		aInput.add("9 9 9");

		return aInput;
	}

	private static void doIt() {
		Scanner scan = new Scanner(System.in);

		int nbTestCases = scan.nextInt();

		for (int t = 0; t < nbTestCases; t++) {
			int n = scan.nextInt();
			int sum = scan.nextInt();
			Set<Integer> sumFound = new HashSet<>();
			sumFound.add(0);
			int closestSum = 0;
			boolean eureka = false;
			for (int k = 0; k < n; k++) {
				int val = scan.nextInt();
				// if already found OR val greater than sum OR val already computed
				if (eureka || val > sum || sumFound.contains(val)) //
					continue;
				if (k == 0) {
					// add multiples of val up to sum in hashtable
					int mult = 1;
					do {
						closestSum = val * mult;
						sumFound.add(closestSum);
						mult++;
					} while (val * mult <= sum);
				} else { // k>1
					Set<Integer> newFound = new HashSet<>();
					for (int inTab : sumFound) {
						int mult = 1;
						int newVal = inTab + val * mult;
						while (newVal <= sum) {
							newFound.add(newVal);
							if (newVal > closestSum)
								closestSum = newVal;
							mult++;
							newVal = inTab + val * mult;
						}
					}
					sumFound.addAll(newFound);
				}
				if (sumFound.contains(sum)) { // was sum found?
					eureka = true;
				}

				if (eureka)
					continue; // need to read other ints from input
			} // end loop on Ints from input
			debugln("eureka : " + eureka + " ; closestSum = " + closestSum);
			println(closestSum);
		} // end test case

		scan.close();

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
