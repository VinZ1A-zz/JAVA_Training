import java.io.ByteArrayInputStream;
import java.util.Scanner;
import java.util.Vector;

//Points: 496.00 Rank: 56855
public class Sol_algo_Impl_BeautifulDaytMovies {

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

		aInput.add("20 23 6"); // expects 2

		return aInput;
	}

	private static void doIt() {
		Scanner scan = new Scanner(System.in);

		int i = scan.nextInt();
		int j = scan.nextInt();
		int k = scan.nextInt();

		// brute force. Let's go.
		int nbOfBeaut = 0;
		for (int num = i; num <= j; num++) {
			StringBuilder val = new StringBuilder(String.valueOf(num));
			int numRev = Integer.valueOf(val.reverse().toString());
			if (Math.abs(num - numRev) % k == 0) {
				nbOfBeaut++;
			}
		}
		System.out.println(nbOfBeaut);
	}

	private static void debug(String iStr) {
		if (_debug) {
			System.err.println(iStr);
		}
	}
}
