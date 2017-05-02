import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Vector;

public class Sol_algo_Impl_SockMerchant {

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

		// expects 3
		// aInput.add("9");
		// aInput.add("10 20 20 10 10 30 50 10 20");

		// expects 30
		aInput.add("100");
		aInput.add(
				"44 55 11 15 4 72 26 91 80 14 43 78 70 75 36 83 78 91 17 17 54 65 60 21 58 98 87 45 75 97 81 18 51 43 84 54 66 10 44 45 23 38 22 44 65 9 78 42 100 94 58 5 11 69 26 20 19 64 64 93 60 96 10 10 39 94 15 4 3 10 1 77 48 74 20 12 83 97 5 82 43 15 86 5 35 63 24 53 27 87 45 38 34 7 48 24 100 14 80 54");

		return aInput;
	}

	@SuppressWarnings("resource")
	private static void doIt() {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int c[] = new int[n];
		for (int c_i = 0; c_i < n; c_i++) {
			c[c_i] = in.nextInt();
		}
		Arrays.sort(c); // deja.
		int prevColor = 0;
		int nbOfSimSocks = 0;
		int nbOfOrphans = 0;
		for (int i = 0; i < n; i++) {
			// isolate odd nb of socks of same color
			if (prevColor != 0 && prevColor != c[i]) {
				if (nbOfSimSocks % 2 == 1) {
					// debug("unique sock found for color " + prevColor);
					nbOfOrphans++;
				}
				nbOfSimSocks = 0;
				prevColor = 0;
			}
			if (prevColor == 0 || prevColor == c[i]) {
				nbOfSimSocks++;
			}

			prevColor = c[i];
		}
		if (nbOfSimSocks % 2 == 1) {
			// debug("unique sock found for color " + prevColor);
			nbOfOrphans++;
		}

		debug(" orphans " + nbOfOrphans);

		// how many pairs can be sold
		System.out.println((n - nbOfOrphans) / 2);

	}

	@SuppressWarnings("unused")
	private static void debug(String iStr) {
		if (_debug) {
			System.err.println(iStr);
		}
	}
}
