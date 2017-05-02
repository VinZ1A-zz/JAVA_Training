package OWN_Exercices;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

public class OWN_Recurs_PaintFill {

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

		// exp fill the two's (start at row=3, col=3)
		aInput.add("1 1 1 1 1 1 1 1");
		aInput.add("1 1 1 1 1 1 0 0");
		aInput.add("1 0 0 1 1 0 1 1");
		aInput.add("1 2 2 2 2 0 1 0");
		aInput.add("1 1 1 2 2 0 1 0");
		aInput.add("1 1 1 2 2 2 2 0");
		aInput.add("1 1 1 1 1 2 1 1");
		aInput.add("1 1 1 1 1 2 2 1");

		return aInput;
	}

	static int COL = 0, ROW = 0;

	private static void doIt() {
		Scanner scan = new Scanner(System.in);

		List<List<Integer>> grid = init(scan);

		int[][] array = transfToArray(grid);

		display(array);

		Point start = new Point(3, 3);
		paint(array, start, array[start.row][start.col], 3);

		debugln("----------- colored -----------");
		display(array);

		scan.close();

	}

	static int[][] dirs = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };

	private static void paint(int[][] array, Point pt, int fromColor, int toColor) {
		// base cases
		// offbound
		if (pt.row < 0 || pt.row == ROW || pt.col < 0 || pt.col == COL) {
			return;
		}
		// bounded by diff color (whether already colored or diff color than from)
		if (array[pt.row][pt.col] != fromColor) {
			return;
		}

		// general case:
		// paint current point
		array[pt.row][pt.col] = toColor;
		// paint in all directions
		for (int[] dir : dirs) {
			paint(array, new Point(pt.row + dir[0], pt.col + dir[1]), fromColor, toColor);
		}

	}

	static class Point {
		int row;
		int col;

		Point(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}

	// sets static vars ROW/COL
	private static List<List<Integer>> init(Scanner scan) {
		List<List<Integer>> grid = new ArrayList<>();

		while (scan.hasNext()) {
			String line = scan.nextLine();
			grid.add(new ArrayList<>());
			for (String elem : line.split(" ")) {
				int color = Integer.parseInt(elem);
				grid.get(ROW).add(color);
				if (ROW == 0)
					COL++;
			}
			ROW++;
		}
		return grid;
	}

	// uses static vars ROW/COL
	private static int[][] transfToArray(List<List<Integer>> list) {
		int[][] array = new int[ROW][COL];
		for (int row = 0; row < ROW; row++) {
			List<Integer> rowNbs = list.get(row);
			for (int col = 0; col < COL; col++) {
				array[row][col] = rowNbs.get(col);
			}
		}
		return array;
	}

	private static void display(int[][] array) {
		for (int[] rowNbs : array) {
			for (int nb : rowNbs) {
				debug(nb + " ");
			}
			debugln("");
		}
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
