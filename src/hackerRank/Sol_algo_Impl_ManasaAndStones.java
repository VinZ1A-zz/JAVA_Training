package hackerRank;
import java.io.ByteArrayInputStream;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.Vector;

// Points: 976.00 Rank: 24361
public class Sol_algo_Impl_ManasaAndStones {

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

		// 2 3 4
		// 30 120 210 300
		// aInput.add("2");
		// aInput.add("3");
		// aInput.add("1");
		// aInput.add("2");
		// aInput.add("4");
		// aInput.add("10");
		// aInput.add("100");

		aInput.add("4");
		aInput.add("58");
		aInput.add("69");
		aInput.add("24");
		aInput.add("83");
		aInput.add("86");
		aInput.add("81");
		aInput.add("73");
		aInput.add("25");
		aInput.add("25");
		aInput.add("12");// 803 812 821 830 839 848 857 866 875 884 893 902
		aInput.add("73");
		aInput.add("82");
		aInput.add("5");// 12 32 52 72 92
		aInput.add("3");
		aInput.add("23");

		return aInput;
	}

	private static void doIt() {
		someImpl();
	}

	private static void someImpl() {
		Scanner scan = new Scanner(System.in);

		int nbCases = scan.nextInt();
		for (int caseNb = 0; caseNb < nbCases; caseNb++) {
			Set<Integer> res = new TreeSet<>();
			int n = scan.nextInt();
			n--; // discard interval
			int a = scan.nextInt();
			int b = scan.nextInt();

			// use bigger interval
			if (a > b) {
				int temp = a;
				a = b;
				b = temp;
			}

			int total = n * a;
			res.add(total);
			for (int i = 0; i < n; i++) {
				total = total - a + b;
				res.add(total);
			}

			for (int i : res) {
				System.out.print(i + " ");
			}

			System.out.println();
		}

	}

	private static void debug(Object obj) {
		if (_debug) {
			System.err.println(obj.toString());
		}
	}
}
