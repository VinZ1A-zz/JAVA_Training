package hackerRank;
import java.io.ByteArrayInputStream;
import java.util.Scanner;
import java.util.Vector;

//Points: 496.00 Rank: 56855
public class Sol_algo_Impl_UtopianTree {

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

		aInput.add("3");
		aInput.add("0");
		aInput.add("1");
		aInput.add("4");

		return aInput;
	}

	private static void doIt() {
		Scanner scan = new Scanner(System.in);

		int size = scan.nextInt();
		int[] testCases = new int[size];
		int max = -1;
		for (int i = 0; i < size; i++) {
			int val = scan.nextInt();
			testCases[i] = val;
			if (val > max)
				max = val;
		}

		long[] treeSizes = new long[max + 1];
		treeSizes[0] = 1;
		for (int i = 1; i <= max; i++) {
			// odd (spring) : doubles
			if (i % 2 > 0) {
				treeSizes[i] = treeSizes[i - 1] * 2;
			} else {
				// even (summer) : adds one meter
				treeSizes[i] = treeSizes[i - 1] + 1;
			}
		}

		for (int i = 0; i < size; i++) {
			System.out.println(treeSizes[testCases[i]]);
		}
	}

	private static void debug(String iStr) {
		if (_debug) {
			System.err.println(iStr);
		}
	}
}
