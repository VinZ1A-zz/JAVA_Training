package hackerRank;
import java.io.ByteArrayInputStream;
import java.util.Scanner;
import java.util.Vector;

public class Sol_algo_Impl_SherlockAndSquares {

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

		// 2 and 0
		// aInput.add("2"); // 2 test cases
		// aInput.add("3 9"); // A and B
		// aInput.add("17 24"); // A and B

		aInput.add("100");
		// aInput.add("11 734");
		// aInput.add("228 919");
		// aInput.add("71 188");
		// aInput.add("270 303");
		// aInput.add("312 701");
		aInput.add("4 16"); // 3
		// aInput.add("17 24"); // 3

		return aInput;
	}

	private static void doIt() {
		Scanner scan = new Scanner(System.in);

		scan.nextInt(); // rien a fout
		int a = 0, b = 0;
		int sqrtA = 0, sqrtB = 0;
		while (scan.hasNext()) {
			a = scan.nextInt();
			b = scan.nextInt();
			if (a > b) {
				int temp = b;
				b = a;
				a = temp;
			}
			sqrtA = (int) Math.floor(Math.sqrt(a));
			sqrtB = (int) Math.floor(Math.sqrt(b));
			debug(b + " " + sqrtB);
			debug(a + " " + sqrtA);
			if (sqrtA * sqrtA == a) {
				sqrtA--;
			}
			System.out.println(sqrtB - sqrtA);
		}

		scan.close();

	}

	private static void debug(Object obj) {
		if (_debug) {
			System.err.println(obj.toString());
		}
	}
}
