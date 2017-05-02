import java.io.ByteArrayInputStream;
import java.util.Scanner;
import java.util.Vector;

public class Sol_algo_Impl_DivisibleSumPairs {

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

		aInput.add("6 3"); // n, k
		aInput.add("1 3 2 6 1 2");

		return aInput;
	}

	@SuppressWarnings("resource")
	private static void doIt() {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int k = in.nextInt();
		int a[] = new int[n];
		for (int a_i = 0; a_i < n; a_i++) {
			a[a_i] = in.nextInt();
		}

		// brute force?
		int total = 0;
		for (int i = 0; i < n - 1; i++) {
			for (int j = i + 1; j < n; j++) {
				if ((a[i] + a[j]) % k == 0)
					total++;
			}
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
