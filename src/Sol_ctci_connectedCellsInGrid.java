import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

public class Sol_ctci_connectedCellsInGrid {

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

		aInput.add("4"); // rows
		aInput.add("4"); // cols
		aInput.add("1 1 0 0");
		aInput.add("0 1 1 0");
		aInput.add("0 0 1 0");
		aInput.add("1 0 0 0");

		return aInput;
	}

	static class Point {
		int _row;
		int _col;

		Point(int row, int col) {
			_row = row;
			_col = col;
		}
	}

	private static void doIt() {
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		int rows = in.nextInt();
		int cols = in.nextInt();
		int grid[][] = new int[rows][cols];
		for (int grid_i = 0; grid_i < rows; grid_i++) {
			for (int grid_j = 0; grid_j < cols; grid_j++) {
				grid[grid_i][grid_j] = in.nextInt();
			}
		}
		// display(grid, rows, cols);
		// System.err.println("*********************");

		int grpNb = 1;
		int marked[][] = new int[rows][cols];
		int maxGrpSize = 0;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				// point in region and region not discovered yet
				if (grid[i][j] == 1 && marked[i][j] == 0) {
					Point pt = new Point(i, j);
					// run BFS on that promising point!
					int grpSize = getArea(pt, grid, marked, grpNb, rows, cols);
					// System.err.println("size of grp " + grpNb + " is " +
					// grpSize);
					if (grpSize > maxGrpSize)
						maxGrpSize = grpSize;
					grpNb++;
				}
			}
		}

		// display(marked, rows, cols);
		System.out.println(maxGrpSize);
	}

	@SuppressWarnings("unused")
	private static void display(int[][] grid, int rows, int cols) {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				System.err.print(grid[i][j] + " ");
			}
			System.err.println();
		}
	}

	static int getArea(Point iPt, int grid[][], int marked[][], int grpNb, int rows, int cols) {
		int grpSize = 0;
		LinkedList<Point> nextToVisit = new LinkedList<Point>();
		nextToVisit.add(iPt); // add initial point
		while (!nextToVisit.isEmpty()) {
			Point pt = nextToVisit.remove();
			if (marked[pt._row][pt._col] > 0) {
				continue;
			}
			// marked by current group ID
			marked[pt._row][pt._col] = grpNb;
			grpSize++;

			List<Point> adjPoints = getAdjacentPoints(pt, grid, rows, cols);
			for (Point adjPt : adjPoints) {
				nextToVisit.add(adjPt);
			}

		}
		return grpSize;
	}

	// offsets (row, then col)
	static int[][] _directions = { { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 }, { 0, -1 },
			{ -1, -1 } };

	static List<Point> getAdjacentPoints(Point iPt, int grid[][], int rows, int cols) {
		List<Point> adjPoints = new ArrayList<>();

		for (int[] dir : _directions) {
			int rowOff = dir[0], colOff = dir[1];
			int ptRow = iPt._row + rowOff, ptCol = iPt._col + colOff;
			// in grid?
			if (ptRow >= 0 && ptRow < rows && ptCol >= 0 && ptCol < cols) {
				if (grid[ptRow][ptCol] != 0) { // not out of region
					adjPoints.add(new Point(ptRow, ptCol));
				}
			}
		}
		return adjPoints;
	}

}
