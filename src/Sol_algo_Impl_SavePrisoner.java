import java.io.ByteArrayInputStream;
import java.util.Scanner;
import java.util.Vector;

//Points: 496.00 Rank: 56855
public class Sol_algo_Impl_SavePrisoner {

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

		aInput.add("2");
		aInput.add("5 2 1"); // 2
		aInput.add("5 4 4");

		return aInput;
	}

	private static void doIt() {
		Scanner scan = new Scanner(System.in);

		int t = scan.nextInt();
		for (int q = 0; q < t; q++) {
			int nbPris = scan.nextInt();
			int nbSweets = (scan.nextInt() - 1) % nbPris;
			int start = scan.nextInt() - 1; // 0 based internally

			System.out.println(((start + nbSweets) % nbPris) + 1);
		}

	}

	private static void debug(String iStr) {
		if (_debug) {
			System.err.println(iStr);
		}
	}
}
