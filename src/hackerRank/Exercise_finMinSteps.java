package hackerRank;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.Vector;

public class Exercise_finMinSteps {

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

		aInput.add("1 3"); // exit location (R,C) - 0 based
		aInput.add("0 # 0 0");
		aInput.add("0 # 0 0"); // # is a wall (conv. to -1)
		aInput.add("0 0 0 0");

		return aInput;
	}

	private static void doIt() {
		Scanner scan = new Scanner(System.in);

		int exitR = scan.nextInt();
		int exitC = scan.nextInt();
		Point to = new Point(exitR, exitC);
		scan.nextLine(); // pos to next line

		// array of Integers : NO
		List<List<Data>> array = new ArrayList<>();
		while (scan.hasNext()) {
			String line = scan.nextLine();
			List<Data> arrayRow = new ArrayList<>();
			for (String i : line.split(" ")) {
				Data data = new Data();
				if (i.equals("#")) {
					data.val = -1;
				} else
					data.val = Integer.parseInt(i);
				arrayRow.add(data);
			}
			array.add(arrayRow);
		}
		scan.close();

		displayGrid(array);

		Point from = new Point(0, 0);
		int minStepsToExit = findMinSteps(array, from, to);
		debugln("minStepsToExit : " + minStepsToExit);

	}

	static int[][] dirs = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };

	static int findMinSteps(List<List<Data>> array, Point from, Point exit) {
		// sanity checks : exit is within bounds -- not done
		int nbRows = array.size();
		int nbCols = array.get(0).size();

		Queue<Point> toProcess = new LinkedList<>(); // BFS style
		toProcess.offer(from);
		get(array, from).visited = true;
		while (!toProcess.isEmpty()) {
			Point cur = toProcess.poll();
			if (cur.equals(exit)) {
				return get(array, cur).val; // found it!
			}
			for (int[] dir : dirs) {
				Point nxt = new Point(cur.row + dir[0], cur.col + dir[1]);
				if (nxt.row >= 0 && nxt.row < nbRows && nxt.col >= 0 && nxt.col < nbCols && get(array, nxt).val != -1) {
					Data nxtData = get(array, nxt);
					if (!nxtData.visited) {
						nxtData.visited = true;
						nxtData.val = get(array, cur).val + 1;
						set(array, nxt, nxtData);
						displayGrid(array); // debug
						toProcess.offer(nxt);
					}
				}
			}
		}

		return -1;
	}

	static Data get(List<List<Data>> array, Point pt) {
		return get(array, pt.row, pt.col);
	}

	static Data get(List<List<Data>> array, int row, int col) {
		return array.get(row).get(col);
	}

	static void set(List<List<Data>> array, Point pt, Data data) {
		set(array, pt.row, pt.col, data);
	}

	static void set(List<List<Data>> array, int row, int col, Data data) {
		array.get(row).set(col, data);
	}

	static void displayGrid(List<List<Data>> array) {
		for (List<Data> row : array) {
			for (Data data : row) {
				if (data.val == -1) {
					debug("# ");
				} else
					debug(data.val + " ");
			}
			debugln(" ");
		}
		debugln("----");
	}

	static class Data {
		int val = 0;
		boolean visited = false;
	}

	// Point does not carry any additional data, ARRAY does.
	static class Point {
		int row;
		int col;

		Point(int iRow, int iCol) {
			row = iRow;
			col = iCol;
		}

		public boolean equals(Point other) {
			return (other.col == col && other.row == row);
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
