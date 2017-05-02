import java.io.ByteArrayInputStream;
import java.util.Scanner;
import java.util.Vector;

// Points: 813.00 Rank: 31599

public class Sol_algo_Impl_TaumAndBDay {

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

		aInput.add("5");
		// 20
		aInput.add("10 10"); // B & W
		aInput.add("1 1 1"); // X,Y,Z
		// 37
		aInput.add("5 9");
		aInput.add("2 3 4");
		// 12
		aInput.add("3 6");
		aInput.add("9 1 1");
		// 35
		aInput.add("7 7");
		aInput.add("4 2 1");
		// 12
		aInput.add("3 3");
		aInput.add("1 9 2");

		return aInput;
	}

	private static void doIt() {
		Scanner scan = new Scanner(System.in);

		someImpl();

	}

	private static void someImpl() {
		Scanner scan = new Scanner(System.in);

		int nbCases = scan.nextInt();
		for (int k = 0; k < nbCases; k++) {
			long nbBlacks = scan.nextInt();
			long nbWhites = scan.nextInt();
			long costBlack = scan.nextInt();
			long costWhite = scan.nextInt();
			long costChange = scan.nextInt();

			long realCostWhite = Math.min(costWhite, costBlack + costChange);
			long realCostBlack = Math.min(costBlack, costWhite + costChange);
			// debug(nbBlacks + " Bs at " + realCostBlack + " ; " + nbWhites + " Ws at
			// " + realCostWhite);
			System.out.println(nbBlacks * realCostBlack + nbWhites * realCostWhite);
		}

		scan.close();

	}

	private static void debug(Object obj) {
		if (_debug) {
			System.err.println(obj.toString());
		}
	}
}
