import java.io.ByteArrayInputStream;
import java.util.Scanner;
import java.util.Vector;

// got up to Points: 741.00 Rank: 35791

public class Sol_algo_Impl_JumpingClouds {

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

		// 4
		aInput.add("7");
		aInput.add("0 0 1 0 0 1 0"); // 0=ord, 1=thunder

		// 3
		// aInput.add("6");
		// aInput.add("0 0 0 0 1 0");

		// 3
		// aInput.add("6");
		// aInput.add("0 0 0 1 0 0");

		return aInput;
	}

	private static void doIt() {
		Scanner scan = new Scanner(System.in);

		someImpl();

	}

	private static void someImpl() {
		Scanner scan = new Scanner(System.in);

		int n = scan.nextInt();
		int c[] = new int[n];
		for (int c_i = 0; c_i < n; c_i++) {
			c[c_i] = scan.nextInt();
		}
		scan.close();

		int total = 0;
		int i = 0;
		do {
			if (i + 2 < n && c[i + 2] == 0) {
				i = i + 2;
				// debug("jump 2");
			} else {
				i = i + 1;
				// debug("jump 1");
			}
			total++;

		} while (i < n - 1);

		System.out.println(total);

	}

	private static void debug(Object obj) {
		if (_debug) {
			System.err.println(obj.toString());
		}
	}
}
