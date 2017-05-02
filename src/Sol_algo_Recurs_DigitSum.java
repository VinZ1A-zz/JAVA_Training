import java.io.ByteArrayInputStream;
import java.util.Scanner;
import java.util.Vector;

public class Sol_algo_Recurs_DigitSum {

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

		aInput.add("148 3");

		return aInput;
	}

	private static void doIt() {
		Scanner scan = new Scanner(System.in);

		String n = scan.next();
		int k = scan.nextInt();

		// char c = '5';
		// int test = c - '0';
		// debugln(test);

		int sup = getSuperDigit(n, n.length() - 1);
		debugln("sup 1 : " + sup);
		String supStr = String.valueOf(sup);

		String concatSimpl = "";
		// eg. repeating 100003 is having sup with a bunch of zeros, then 3 sups
		if (k > 10) {
			k = k % 10;
			concatSimpl = supStr;
		}
		for (int i = 1; i <= k; i++) {
			concatSimpl += supStr;
		}

		debugln("concatSimpl : " + concatSimpl);
		sup = getSuperDigit(concatSimpl, concatSimpl.length() - 1);
		debugln("sup 2 : " + sup);

		print(sup);

		scan.close();

	}

	private static int getSuperDigit(String n, int idx) {
		if (idx == 0) {
			return getVal(n.charAt(0));
		}

		int prevSup = getSuperDigit(n, idx - 1);
		int sup = prevSup + getVal(n.charAt(idx));
		if (sup >= 10) {
			sup = sup % 10 + 1;
		}
		return sup;

	}

	private static int getVal(char c) {
		return (c - '0');
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
