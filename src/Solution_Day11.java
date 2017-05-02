import java.io.ByteArrayInputStream;
import java.util.Scanner;
import java.util.Vector;

public class Solution_Day11 {

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

		// expects 19
		// aInput.add("1 1 1 0 0 0");
		// aInput.add("0 1 0 0 0 0");
		// aInput.add("1 1 1 0 0 0");
		// aInput.add("0 0 2 4 4 0");
		// aInput.add("0 0 0 2 0 0");
		// aInput.add("0 0 1 2 4 0");

		// expects -6
		aInput.add("-1 -1 0 -9 -2 -2");
		aInput.add("-2 -1 -6 -8 -2 -5");
		aInput.add("-1 -1 -1 -2 -3 -4");
		aInput.add("-1 -9 -2 -4 -4 -5");
		aInput.add("-7 -3 -3 -2 -9 -9");
		aInput.add("-1 -3 -1 -2 -4 -5");

		return aInput;
	}

	private static void doIt() {
		Scanner scan = new Scanner(System.in);

		int i = 0;
		int j = 0;
		int[][] matrix = new int[6][6];
		while (scan.hasNext()) {

			String nbStr = scan.next();

			// System.err.println("i/j" + i + ";" + j);
			matrix[i][j] = Integer.parseInt(nbStr);
			i++;
			if (i % 6 == 0) {
				j++;
				i = 0;
			}
		}

		scan.close();

		// hourglass detection : 4 per line, 4 per column, forget about
		// distincts
		Integer maxSum = null;
		for (i = 0; i < 4; i++) {
			for (j = 0; j < 4; j++) {
				int sumHg = matrix[i][j] + matrix[i + 1][j] + matrix[i + 2][j] + matrix[i + 1][j + 1] + matrix[i][j + 2]
						+ matrix[i + 1][j + 2] + matrix[i + 2][j + 2];
				// System.err.println(sumHg);
				if (maxSum == null || sumHg > maxSum) {
					maxSum = sumHg;
				}
			}
		}

		System.out.println(maxSum);

	}
}
