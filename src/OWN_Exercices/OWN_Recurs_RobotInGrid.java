package OWN_Exercices;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.Vector;

public class OWN_Recurs_RobotInGrid {

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

		aInput.add("---#----");
		aInput.add("-#-#----");
		aInput.add("-#---##-");
		aInput.add("-#-#-#--");
		aInput.add("-#---#-#");
		aInput.add("-#-#----");

		return aInput;
	}

	static Grid grid = new Grid();

	private static void doIt() {
		Scanner scan = new Scanner(System.in);

		while (scan.hasNext()) {
			grid.addInGrid(scan.nextLine());
		}
		scan.close();

		grid.display();
		debugln("dims: C=" + grid.C + ";R=" + grid.R);

		debugln("val : " + grid.get(4, 7));

		Pos init = new Pos(0, 0);
		List<Pos> path = new ArrayList<>();
		path = findPath(init);

		debugln("Path : " + path);

	}

	static List<Pos> findPath(Pos root) {
		// apply BFS
		int[][] visited = new int[grid.R][grid.C];

		Queue<Pos> queue = new LinkedList<>();
		queue.offer(root);
		visited[root.row][root.col] = 1;

		boolean foundExit = false;
		while (!queue.isEmpty() && !foundExit) {
			Pos curPos = queue.poll();
			List<Pos> adj = grid.getAdj(curPos.row, curPos.col);
			for (Pos adjPos : adj) {
				if (visited[adjPos.row][adjPos.col] == 0 && adjPos.val >= 0) {
					visited[adjPos.row][adjPos.col] = visited[curPos.row][curPos.col] + 1;
					queue.offer(adjPos);
				}
				if (adjPos.row == grid.R - 1 && adjPos.col == grid.C - 1) { // end Pt
					foundExit = true;
				}
			}
		}

		if (foundExit) {
			grid.display(visited);
			List<Pos> path = new LinkedList<Pos>();

			Pos curPos = grid.get(grid.R - 1, grid.C - 1);
			int curPathNb = visited[curPos.row][curPos.col];
			path.add(curPos);
			while (curPathNb > 1)
				// walk back, reverse dirs
				for (Pos adjPos : grid.getAdj(curPos.row, curPos.col, true)) {
					if (visited[adjPos.row][adjPos.col] == curPathNb - 1) {
						path.add(adjPos);
						curPos = adjPos;
						curPathNb = visited[curPos.row][curPos.col];
						break;
					}
				}
			Collections.reverse(path);
			return path;
		}

		return null;
	}

	static class Pos {
		int row;
		int col;
		Integer val = null;

		Pos(int row, int col) {
			this.row = row;
			this.col = col;
		}

		Pos(int row, int col, int val) {
			this(row, col);
			this.val = val;
		}

		@Override
		public String toString() {
			return row + "," + col + "(" + val + ")";
		}
	}

	static class Grid {
		List<List<Integer>> _arr = new ArrayList<>();
		int C = 0;
		int R = 0;

		int addInGrid(String str) {
			char[] chars = str.toCharArray();
			List<Integer> newRow = new ArrayList<>();
			int curC = 0;
			for (char c : chars) {
				// assuming can only have two choices (- and wall)
				newRow.add((c == '-') ? 0 : -1);
				curC++;
			}
			if (C != 0) {
				if (C != curC) {
					return -1; // should be same row length
				}
			}
			C = curC;
			R++;
			_arr.add(newRow);
			return 0;// ok
		}

		boolean isValid(int row, int col) {
			if (row < 0 || row > R - 1 || col < 0 || col > C - 1)
				return false;
			return true;
		}

		int[][] dirs = { { 1, 0 }, { 0, 1 } };

		List<Pos> getAdj(int row, int col, boolean reverse) {
			if (!isValid(row, col))
				return null;
			List<Pos> adj = new ArrayList<>();
			for (int[] dir : dirs) {
				int rev = reverse ? -1 : 1;
				int adjR = row + rev * dir[0];
				int adjC = col + rev * dir[1];
				if (isValid(adjR, adjC)) {
					adj.add(get(adjR, adjC));
				}
			}
			return adj;
		}

		List<Pos> getAdj(int row, int col) {
			return (getAdj(row, col, false)); // not reverse
		}

		Pos get(int row, int col) {
			if (!isValid(row, col))
				return null;
			return new Pos(row, col, _arr.get(row).get(col));

		}

		void display(int[][] visited) {
			int row = 0;
			for (List<Integer> rowVals : _arr) {
				int col = 0;
				for (int val : rowVals) {
					switch (val) {
					case 0:
						if (visited != null && visited[row][col] != 0)
							debug(visited[row][col]);
						else
							debug('-');
						break;
					case -1:
						debug('#');
						break;
					default:
						if (visited == null)
							debug(val);
						else
							debug(visited[row][col]);
						break;

					}
					col++;
				}
				row++;
				debugln("");
			}

		}

		void display() {
			display(null);
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
