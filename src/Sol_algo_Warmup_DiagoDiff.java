import java.io.ByteArrayInputStream;
import java.util.Scanner;
import java.util.Vector;

public class Sol_algo_Warmup_DiagoDiff {

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

		aInput.add("3");
		aInput.add("11 2 4");
		aInput.add("4 5 6");
		aInput.add("10 8 -12");

		return aInput;
	}

	private static void doIt() {
		Scanner scan = new Scanner(System.in);

		int dim = scan.nextInt();
		// int priDiag = 0;
		// int secDiag = 0;
		int diff = 0;
		int row = 1, col = 0;
		while (scan.hasNext()) {
			col++;
			int curVal = scan.nextInt();
			// debug("at row" + row + "/col" + col + ":" + curVal);
			if (row == col) {
				debug("adding " + curVal);
				diff += curVal;
			}
			if (row == dim - col + 1) {
				debug("substracting " + curVal);
				diff -= curVal;
			}

			if (col % dim == 0) {
				row++;
				col = 0;
			}
		}

		System.out.println(Math.abs(diff));

		scan.close();

	}

	private static void debug(String iStr) {
		if (_debug) {
			System.err.println(iStr);
		}
	}
}
