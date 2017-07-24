package hackerRank;
import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Vector;

public class Sol_algo_Impl_BetweenTwoSets {

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

		aInput.add("2 3");
		aInput.add("2 4");
		aInput.add("16 32 96");

		return aInput;
	}

	@SuppressWarnings("resource")
	private static void doIt() {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int m = in.nextInt();
		int[] a = new int[n];
		for (int a_i = 0; a_i < n; a_i++) {
			a[a_i] = in.nextInt();
		}
		int[] b = new int[m];
		for (int b_i = 0; b_i < m; b_i++) {
			b[b_i] = in.nextInt();
		}
		Arrays.sort(a);
		Arrays.sort(b);
		// absolutely and shamefully non optimized.
		int total = 0;
		OUTER_LOOP: for (int i = a[n - 1]; i <= b[0]; i++) {
			for (int xa : a) {
				if (i % xa != 0)
					continue OUTER_LOOP;
			}
			for (int xb : b) {
				if (xb % i != 0)
					continue OUTER_LOOP;
			}
			// debug("" + i);
			total++;
		}

		System.out.println(total);

	}

	@SuppressWarnings("unused")
	private static void debug(String iStr) {
		if (_debug) {
			System.err.println(iStr);
		}
	}
}
