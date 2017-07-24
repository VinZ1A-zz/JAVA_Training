package hackerRank;
import java.io.ByteArrayInputStream;
import java.util.Scanner;
import java.util.Vector;

//Points: 496.00 Rank: 56855
public class Sol_algo_Impl_FindDigits {

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
		aInput.add("12");
		aInput.add("1012");
		aInput.add("9999");

		return aInput;
	}

	private static void doIt() {
		Scanner scan = new Scanner(System.in);

		scan.nextInt();// int nbOfQueries = scan.nextInt();
		while (scan.hasNext()) {
			// bourrin.
			int val = scan.nextInt();
			int result = 0;
			if (val == 0) {
				System.out.println(0);
				continue;
			}

			int initVal = val;
			int divisers = 0;
			do {
				int lastDig = val % 10;
				if (lastDig > 0) {
					if (initVal % lastDig == 0) {
						divisers++;
					}
				}
				val = (val - lastDig) / 10;
			} while (val > 0);
			System.out.println(divisers);
		}

	}

	private static void debug(String iStr) {
		if (_debug) {
			System.err.println(iStr);
		}
	}
}
