package OWN_Exercices;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.Vector;

public class OWN_WaterFlowOverDiagonal {

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

		// expects 1
		// aInput.add("2"); // dimension
		// aInput.add("5 1");
		// aInput.add("3 2");

		// expects 5
		aInput.add("4"); // dimension
		aInput.add("11 6 7 8");
		aInput.add("4 5 1 7");
		aInput.add("5 4 3 2");
		aInput.add("3 1 2 1");

		return aInput;
	}

	static int[][] grid;
	static boolean[][] visitedGrid;
	static int n;

	// Given an N by N grid of positive integers which
	// represent terrain heights, determine how many
	// grid locations will have water flow over the
	// continental divide (NxN diagonal, 1-1, 2-2, etc)
	// when it rains, not including the divide itself.
	private static void doIt() {
		Scanner scan = new Scanner(System.in);

		{
			n = scan.nextInt();
			scan.nextLine();
			grid = new int[n][n];
			visitedGrid = new boolean[n][n];
			int col = 0, row = 0;
			while (scan.hasNext()) {
				String line = scan.nextLine();
				col = 0;
				for (String nb : line.split(" ")) {
					grid[row][col] = Integer.parseInt(nb);
					col++;
				}
				row++;
			}
			scan.close();
		}

		displayGrid();

		// identify water entry points
		Set<Coord> entryPts = new HashSet<>();
		for (int idx = 0; idx < n; idx++) {
			Coord diagPt = new Coord(idx, idx);
			List<Coord> adjPts = diagPt.getAdj();
			boolean hasLowerValOnSouth = false;
			boolean hasLowerValOnNorth = false;
			// on either side of the diagonal, do we have a lower point?
			for (Coord adj : adjPts) {
				if (adj.val < diagPt.val) {
					if (adj.isNorth())
						hasLowerValOnNorth = true;
					if (adj.isSouth())
						hasLowerValOnSouth = true;
				}
			}
			for (Coord adj : adjPts) {
				// higher point exists on one side of diagonal
				if (adj.val > diagPt.val) {
					// check if there is a point where the water can flow
					if ((adj.isNorth() && hasLowerValOnSouth) || (adj.isSouth() && hasLowerValOnNorth))
						entryPts.add(adj);
				}
			}
		}

		debugln("entry points: " + entryPts);

		// need to do DFS on these points and count
		for (Coord root : entryPts) {
			DFS(root);
		}

		// count nb of visited
		int count = 0;
		for (boolean[] visitedRow : visitedGrid) {
			for (boolean vis : visitedRow) {
				debug((vis ? "1" : "0") + " ");
				if (vis)
					count++;
			}
			debugln("");
		}

		debugln("nb of overflowing spaces : " + count);

	}

	// only visit higher points not on diagonal
	static private void DFS(Coord root) {
		if (root == null)
			return;
		root.setVisited(true);
		for (Coord pt : root.getAdj()) {
			if (pt.val > root.val && !pt.isDiag() && !pt.isVisited()) {
				DFS(pt);
			}
		}

	}

	static class Coord {
		int row = -1;
		int col = -1;
		int val = -1;

		Coord(int row, int col, int val) {
			this(row, col);
			this.val = val; // override val
		}

		Coord(int row, int col) {
			this.row = row;
			this.col = col;
			if (isValid()) {
				this.val = grid[row][col];
			}
		}

		static int[][] dirs = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

		public boolean isValid() {
			return (row >= 0 && row < n && col >= 0 && col < n);
		}

		public boolean isNorth() {
			return (col < row);
		}

		public boolean isSouth() {
			return (col > row);
		}

		public boolean isDiag() {
			return (!isNorth() && !isSouth());
		}

		public int setVisited(boolean val) {
			if (isValid()) {
				visitedGrid[row][col] = val;
				return 0;
			}
			return -1;
		}

		public boolean isVisited() {
			if (isValid()) {
				return visitedGrid[row][col];
			}
			return false;
		}

		public List<Coord> getAdj() {
			List<Coord> adj = new ArrayList<>();

			for (int[] dir : dirs) {
				Coord adjPt = new Coord(row + dir[0], col + dir[1]);
				if (adjPt.isValid()) {
					adj.add(adjPt);
				}
			}

			return adj;
		}

		@Override
		public String toString() {
			return row + "," + col + "(" + val + ")";
		}

		@Override
		public boolean equals(Object o) {
			if (!(o instanceof Coord))
				return false;
			Coord other = (Coord) o;
			if (other.row == this.row && other.col == this.col)
				return true;
			return false;
		}

		@Override
		public int hashCode() {
			int result = row;
			result = 31 * result + col;
			return result;
		}

	}

	private static void displayGrid() {
		for (int[] rowNbs : grid) {
			for (int val : rowNbs) {
				debug(val + " ");
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
