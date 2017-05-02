import java.io.ByteArrayInputStream;
import java.util.Scanner;
import java.util.Vector;

public class Solution_Day3 {

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

		// detect letter
		aInput.add("3");
		// aInput.add("4.0");
		// aInput.add("is the best place to learn and practice coding!");

		return aInput;
	}

	private static void doIt() {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		scan.close();

		String ans = "";

		// if 'n' is NOT evenly divisible by 2 (i.e.: n is odd)
		if (n % 2 == 1) {
			ans = "Weird";
		} else {
			// Complete the code
			// Even
			if (n >= 2 && n <= 5) {
				ans = "Not Weird";
			} else if (n >= 6 && n <= 20) {
				ans = "Weird";
			} else
				ans = "Not Weird";
		}
		System.out.println(ans);
	}
}
