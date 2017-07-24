package hackerRank;
import java.io.ByteArrayInputStream;
import java.util.Scanner;
import java.util.Vector;

public class Solution_Day29 {

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

		// gives 1, 4 and 0
		aInput.add("3"); // nb of test cases
		aInput.add("5 2"); // N & K eg. in {1,2,3,4,5} --> find A&B max but < 2
		aInput.add("8 5");
		aInput.add("2 2");

		return aInput;
	}

	private static void doIt() {
		Scanner scan = new Scanner(System.in);

		int nbCases = scan.nextInt();
		for (int bla = 0; bla < nbCases; bla++) {
			int n = scan.nextInt();
			int k = scan.nextInt();
			int maxAnd = Integer.MIN_VALUE;
			for (int i = 1; i < n; i++) {
				for (int j = i + 1; j <= n; j++) {
					int curAnd = i & j;
					if (curAnd > maxAnd && curAnd < k)
						maxAnd = curAnd;
				}
			}
			System.out.println(maxAnd);
		}
		// System.err.println(5 & 4);

		scan.close();

	}
}
