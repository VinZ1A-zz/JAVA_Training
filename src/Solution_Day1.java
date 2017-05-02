import java.io.ByteArrayInputStream;
import java.util.Scanner;
import java.util.Vector;

public class Solution_Day1 {
	static int i = 4;
	static double d = 4.0;
	static String s = "HackerRank ";

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

		Scanner scan = new Scanner(System.in);

		doIt(scan);

		scan.close();
	}

	// debug method - discard!
	static private Vector<String> getData() {
		Vector<String> aInput = new Vector<String>();

		// detect letter
		aInput.add("12");
		aInput.add("4.0");
		aInput.add("is the best place to learn and practice coding!");

		return aInput;
	}

	private static void doIt(Scanner scan) {
		/* Declare second integer, double, and String variables. */
		// sure.

		/* Read and save an integer, double, and String to your variables. */
		// sure.

		/* Print the sum of both integer variables on a new line. */
		System.out.println(scan.nextInt() + i);

		/* Print the sum of the double variables on a new line. */
		System.out.println(scan.nextDouble() + d);

		/*
		 * Concatenate and print the String variables on a new line; the 's'
		 * variable above should be printed first.
		 */
		scan.nextLine();
		System.out.println(s + scan.nextLine());
	}
}
