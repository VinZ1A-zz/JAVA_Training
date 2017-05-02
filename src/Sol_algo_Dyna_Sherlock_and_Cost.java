import java.io.ByteArrayInputStream;
import java.util.Scanner;
import java.util.Vector;

public class Sol_algo_Dyna_Sherlock_and_Cost {

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

		aInput.add("1");
		// aInput.add("5"); // 36 ok
		// aInput.add("10 1 10 1 10");

		// aInput.add("11"); // 642 ok
		// aInput.add("79 6 40 68 68 16 40 63 93 49 91");

		aInput.add("10"); // 508 got 442
		aInput.add("55 68 31 80 57 18 34 28 76 55");

		return aInput;
	}

	private static void doIt() {
		Scanner scan = new Scanner(System.in);

		int testCases = scan.nextInt();

		for (int testCase = 0; testCase < testCases; testCase++) {
			int n = scan.nextInt();
			int[] a = new int[n];
			int curL = 0;
			int curH = 0;
			int prevL = 0;
			int prevH = 0;
			int prevVal = 0;
			for (int i = 0; i < n; i++) {
				int val = scan.nextInt();
				if (prevVal == 1 && val == 1)
					continue;
				a[i] = val;

				if (i == 0) {
					prevVal = val;
				} else {
					// case 1: we use a 1, get the max (using either curL or curH)
					curL = Math.max(prevL, prevH + Math.abs(prevVal - 1));
					// case 2: we use val, get the max
					curH = Math.max(prevL + Math.abs(val - 1), prevH + Math.abs(prevVal - val));
				}
				// debugln("curL: " + curL + " ; curH : " + curH);
				prevVal = val;
				prevL = curL;
				prevH = curH;
			}
			println(Math.max(curH, curL));

		} // end useCase

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
