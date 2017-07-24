package hackerRank;
import java.io.ByteArrayInputStream;
import java.util.Scanner;
import java.util.Vector;

public class Solution_Day16 {

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

		aInput.add("zo");

		return aInput;
	}

	private static void doIt() {
		Scanner scan = new Scanner(System.in);

		try {
			int n = scan.nextInt();
			System.out.println(n);
		} catch (java.util.InputMismatchException e) {
			System.out.println("Bad String");
		}

		scan.close();

	}
}
