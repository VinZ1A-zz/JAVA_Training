package hackerRank;
import java.io.ByteArrayInputStream;
import java.util.Scanner;
import java.util.Vector;

public class Solution_Day26 {

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

		// actual day then due day

		// output 45
		// aInput.add("9 6 2015"); // D M YYY
		// aInput.add("6 6 2015");

		aInput.add("23 12 1234");
		aInput.add("19 9 2468");

		return aInput;
	}

	private static void doIt() {
		Scanner scan = new Scanner(System.in);

		int actualD = scan.nextInt();
		int actualM = scan.nextInt();
		int actualY = scan.nextInt();

		int dueD = scan.nextInt();
		int dueM = scan.nextInt();
		int dueY = scan.nextInt();

		int fine = 0;
		if (actualY > dueY)
			fine = 10000;
		else if (actualY == dueY && actualM > dueM)
			fine = 500 * (actualM - dueM);
		else if (actualY == dueY && actualM == dueM && actualD > dueD)
			fine = 15 * (actualD - dueD);

		System.out.println(fine);

		scan.close();

	}
}
