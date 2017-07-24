package hackerRank;
import java.io.ByteArrayInputStream;
import java.util.Scanner;
import java.util.Vector;

public class Sol_WeekCode27_Drawing {

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

		// expects 1
		aInput.add("6"); // nb of pages: 1 to 10exp5
		aInput.add("2"); // page nb: 1 to n

		// other: 5 4 --> 0
		// aInput.add("5");
		// aInput.add("4");

		return aInput;
	}

	private static void doIt() {
		Scanner in = new Scanner(System.in);
		double n = in.nextInt();
		double p = in.nextInt();

		// nice to have : what if p is more or less around n/2 ? ;)

		// if turning from beginning:
		// floor(page /2) --> nb of turns
		double fromBeg = Math.floor(p / 2);

		// if turning from end:
		double fromEnd = -1;
		if (n % 2 == 0) { // if n odd
			fromEnd = Math.ceil((n - p) / 2);
		} else // n even
		{
			fromEnd = Math.floor((n - p) / 2);
		}

		System.out.println((int) Math.min(fromBeg, fromEnd));

		in.close();

	}
}
