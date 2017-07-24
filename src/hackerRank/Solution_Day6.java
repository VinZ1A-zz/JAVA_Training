package hackerRank;
import java.io.ByteArrayInputStream;
import java.util.Scanner;
import java.util.Vector;

public class Solution_Day6 {

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
		aInput.add("Hacker");
		aInput.add("Rank");

		return aInput;
	}

	private static void doIt() {
		Scanner scan = new Scanner(System.in);

		int n = scan.nextInt();
		// ArrayList<String> aStrs = new ArrayList<String>();
		scan.nextLine();
		for (int i = 0; i < n; i++) {
			String aStr = scan.nextLine();
			System.err.println(aStr);
			StringBuilder aStrEven = new StringBuilder();
			StringBuilder aStrOdd = new StringBuilder();
			for (int j = 0; j < aStr.length(); j++) {
				if (j % 2 == 0) {
					aStrEven.append(aStr.charAt(j));
				} else
					aStrOdd.append(aStr.charAt(j));
			}
			System.out.println(aStrEven + " " + aStrOdd);
		}

		scan.close();

	}
}
