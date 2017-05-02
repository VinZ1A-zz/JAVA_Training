package OWN_Exercices;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

public class OWN_Recurs_8queens {

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

		aInput.add("8"); // 92 solutions

		return aInput;
	}

	static int n;

	private static void doIt() {
		Scanner scan = new Scanner(System.in);

		n = scan.nextInt();
		scan.close();

		int[] grid = new int[n];
		// init placement at row 0
		List<int[]> results = new ArrayList<>();
		int nbWays = getNbWays(grid, 0, results);

		print("nb ways : " + nbWays);

		// for (int i = 0; i < Math.min(10, nbWays); i++) {
		// displayGrid(results.get(i));
		// debugln("");
		// }
	}

	@SuppressWarnings("unused")
	private static void displayGrid(int[] grid) {
		for (int i = 0; i < n; i++) {
			for (int k = 0; k < grid[i]; k++) {
				debug(".");
			}
			debug("X");
			for (int k = grid[i] + 1; k < n; k++) {
				debug(".");
			}
			debugln("");
		}
	}

	private static int getNbWays(int[] grid, int fromRow, List<int[]> results) {
		// base case
		if (fromRow == n) {
			results.add(grid.clone());
			return 1;
		}

		int ways = 0;
		for (int i = 0; i < n; i++) {
			// can we place new queen there?
			if (isValid(grid, fromRow, i)) {
				grid[fromRow] = i;
				// get nb of ways with queen of row #0 at pos i
				// debugln("getting ways for + " (fromRow + 1));
				ways += getNbWays(grid, fromRow + 1, results);
			}
		}
		return ways;

	}

	private static boolean isValid(int[] grid, int row, int col) {
		// column check (upwards)
		for (int i = 0; i < row; i++) {
			if (grid[i] == col) {
				return false; // already a queen at this col on row #i
			}
		}

		// no need for row check (only one queen by row)

		// diag check (upwards)
		for (int i = 0; i < row; i++) {
			if (grid[i] == col - row + i || grid[i] == col + row - i) {
				return false;
			}
		}

		return true;
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
