package OWN_Exercices;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

public class OWN_NbWaysToGetChange {

	static final boolean _debug = "true".equals(System.getProperties().get("debug"));

	public static void main(String[] args) {

		if (args.length != 0) {
			System.err.println("in debug with debug = " + System.getProperties().get("debug"));
			// _debug = true; // set as final
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

		// expects 3
		// aInput.add("6"); // total value
		// aInput.add("1 5 20 50"); // values of bills and coins

		// with 100, expects 954515231698
		// 60 : 11592517
		aInput.add("60"); // total value
		aInput.add("1 5 20 50"); // values of bills and coins

		return aInput;
	}

	private static void doIt() {
		Scanner scan = new Scanner(System.in);

		int total = scan.nextInt();
		List<Integer> billValues = new ArrayList<>();
		while (scan.hasNext()) {
			billValues.add(scan.nextInt());
		}

		print("ways : " + getWays(total, billValues));

		scan.close();

	}

	private static int getWays(int total, List<Integer> billValues) {
		// end points
		if (total == 0) // only one way to give no bills ;)
			return 1;
		if (total < 0) // no way to return a neg nb of money
			return 0;
		int ways = 0;
		for (int billVal : billValues) {
			ways += getWays(total - billVal, billValues);
		}
		return ways;
	}

	private static void debug(Object obj) {
		if (_debug) {
			System.err.print(obj.toString());
		}
	}

	private static void debugln(Object obj) {
		if (_debug) {
			System.err.println(obj.toString());
		}
	}

	private static void print(Object obj) {
		System.out.print(obj.toString());
	}

	private static void println(Object obj) {
		System.out.println(obj.toString());
	}

}
