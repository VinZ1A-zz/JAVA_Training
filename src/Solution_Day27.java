import java.io.ByteArrayInputStream;
import java.util.Vector;

public class Solution_Day27 {

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

		doIt(args);

	}

	// debug method - discard!
	static private Vector<String> getData() {
		Vector<String> aInput = new Vector<String>();

		aInput.add("3");

		return aInput;
	}

	private static void doIt(String[] args) {
		// Scanner scan = new Scanner(System.in);

		// int n = scan.nextInt();
		//
		// for (String aStr : args) {
		// System.out.println(aStr);
		// }

		System.out.println("5"); // <=5
		// nb of students N between 1 and 200
		// threshold between 1 and n
		// YES, cancelled
		System.out.println("4 3"); // need 3 out of 4
		System.out.println("0 -3 4 2"); // 2/3 arrive on time --> cancelled
		// NO, class is not cancelled
		System.out.println("5 2"); // need 2
		System.out.println("0 -1 -2 1 4"); // 3/2 arrive on time --> OK
		// YES
		System.out.println("6 3"); // need 3
		System.out.println("2 4 20 -22 5 0"); // 2/3 arrive on time -->
												// cancelled
		// NO
		System.out.println("3 1"); // need 1
		System.out.println("0 1 -4"); // 2/1 arrive on time --> OK
		// YES
		System.out.println("7 3"); // need 3
		System.out.println("2 4 32 20 -22 5 0"); // 2/3 arrive on time -->
		// cancelled

		// scan.close();
	}
}
