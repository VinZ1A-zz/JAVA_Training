package OWN_Exercices;
import java.io.ByteArrayInputStream;
import java.util.Scanner;
import java.util.Vector;

public class OWN_Recurs_TripleStep {

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

		aInput.add("5");

		return aInput;
	}

	// a child is running a staircase with n steps
	// and can hop either 1,2 or 3 steps at a time
	// Count how many possible ways child can run up the stairs
	private static void doIt() {
		Scanner scan = new Scanner(System.in);

		int n = scan.nextInt();

		int res = countWays(n);

		println(res);

		scan.close();

	}

	private static int countWays(int n) {
		int[] memos = new int[n + 1]; // init to zeros
		return countWays(n, memos);
	}

	private static int countWays(int n, int[] memos) {
		if (n < 0) {
			return 0; // ze important trick too
		}
		if (memos[n] != 0) { // memoization
			return memos[n];
		}
		if (n == 0) {
			memos[0] = 1;
			return 1; // ze trick
		}
		memos[n] = countWays(n - 1, memos) + countWays(n - 2, memos) + countWays(n - 3, memos);
		debugln("n(" + n + ") calculated : " + memos[n]);
		return memos[n];

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
