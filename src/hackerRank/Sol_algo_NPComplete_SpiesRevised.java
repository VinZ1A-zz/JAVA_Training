package hackerRank;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

//Points: 496.00 Rank: 56855
public class Sol_algo_NPComplete_SpiesRevised {

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

		Sol_algo_NPComplete_SpiesRevised aaa = new Sol_algo_NPComplete_SpiesRevised();
		Scanner scan = new Scanner(System.in);
		aaa.n = scan.nextInt();
		scan.close();

		int nbOfAttempts = 100;
		int score = 0;
		for (int i = 0; i < nbOfAttempts; i++) {
			score += aaa.doIt();
		}
		score = score / nbOfAttempts; // should be around 15 maxß

		System.out.println("avg nb of permutations " + score);

	}

	// debug method - discard!
	static private Vector<String> getData() {
		Vector<String> aInput = new Vector<String>();

		aInput.add("8"); // size of board // up to 999

		return aInput;
	}

	int n = 0;
	// int maxNbOfClashesFactor = 1000; // Math.max(30, n / maxNbOfClashesFactor);
	// int maxNbOfClashes;
	Random rn = new Random();
	final int[][] dirs = { { 1, 1 }, { -1, -1 }, { 1, -1 }, { -1, 1 } };

	static boolean _debug = false;

	private int doIt() {
		_debug = false; // ****** DEBUG OVERRIDE ***********
		n = _debug ? 8 : n; // DEBUG override
		// maxNbOfClashes = Math.max(10, n / maxNbOfClashesFactor);

		// initial random placement
		int[] places = new int[n];// [i] --> placement on ith row
		// stores conflicts per Queen
		Pair[] confs = new Pair[n];// pairs index / values (compared by value)
		init(places);

		// displayGrid(places); // DEBUG

		// with simple chess- prob first (diags/cols/rows only)
		boolean isFound = assessConflicts(places, confs);

		// DEBUG
		int nbOfTries = _debug ? 3 : -1; // DEBUG (-1: unlimited)
		while (!isFound && (nbOfTries != 0)) {
			// displayConfs(confs); // debug
			improveGrid(places, confs);
			// displayGrid(places); // DEBUG
			isFound = assessConflicts(places, confs);
			nbOfTries--;
			if (nbOfTries % 300 == 0)
				System.out.println("attemps calculated : " + nbOfTries);
		}

		if (assessConflicts(places, confs)) // useless last check (DEBUG)
			System.out.println("FOUND after " + -nbOfTries + " attempts");
		else
			System.out.println("could not find, even after " + -nbOfTries + " attempts");

		// displayGrid(places);

		return (-nbOfTries);

	}

	public class Pair implements Comparable<Pair> {
		public final int row;
		public final int value;

		public Pair(int index, int value) {
			this.row = index;
			this.value = value;
		}

		@Override
		public int compareTo(Pair other) {
			// descending val
			return -1 * Integer.valueOf(this.value).compareTo(other.value);
		}
	}

	private void improveGrid(int[] places, Pair[] confs) {
		// TODO - better choice - XXXXXx
		// change pos of a threatened Queen within her row, to the least threatening
		// one (! there will be a conflict on the column)
		// re-assess better : removes previous conflicts and add new ones only

		// swap first and last pos, given by confs

		int heighestWeight = confs[0].value;
		int maxRangeL = 0;
		int maxRangeR = -1;
		// determine rowForMinConf
		// 1. get lowest conflict weight
		// 2. get rand of all of the candidates with this weight
		int lowestWeight = n;
		int minRangeL = 0;
		int minRangeR = -1;
		int conf;
		int prevConf = -1;
		for (int i = 0; i < n; i++) {

			conf = confs[i].value;
			if (conf < lowestWeight && conf > 0) {
				lowestWeight = conf;
				if (i > 0 && prevConf > conf)
					minRangeL = i;
			}
			if (conf == lowestWeight) {
				minRangeR = i;
			}
			prevConf = conf;

			if (maxRangeR < 0 && conf < heighestWeight) {
				maxRangeR = i - 1;
			}
		}
		// when all confs are the same
		if (maxRangeR == -1)
			maxRangeR = 0;

		// ensure rangeL is not the first candidate (when all non-null weights are
		// same - deadlock if only two pieces with conflict)
		if (minRangeL == 0) {
			minRangeL = 1;
			maxRangeR = 0;// avoid overlap
		}
		// debugln("MinRange is from " + minRangeL + " to " + minRangeR);
		// avoids deadlock (cheap) - when only one choice, make it two.
		if (minRangeL == 1 && minRangeR == 1) {
			minRangeR = 2; // use the (totally random) next row as well to re-shuffle
		}

		int rowForMinConf;
		if (minRangeR != minRangeL) {
			rowForMinConf = confs[rn.nextInt(minRangeR - minRangeL + 1) + minRangeL].row;
		} else
			rowForMinConf = confs[minRangeR].row;

		// debugln("MaxRange is from " + maxRangeL + " to " + maxRangeR);
		int largestConf;
		if (maxRangeR != maxRangeL) {
			largestConf = confs[rn.nextInt(maxRangeR - maxRangeL + 1) + maxRangeL].row;
		} else
			largestConf = confs[0].row;

		// debugln("swaping largestConf(row " + largestConf + ",col " +
		// places[largestConf] + ") and smallestConf(row"
		// + rowForMinConf + " ,col " + places[rowForMinConf] + ")");
		int tmp = places[rowForMinConf];
		places[rowForMinConf] = places[largestConf];
		places[largestConf] = tmp;
	}

	// returns minConflict rowNb
	private boolean assessConflicts(int[] places, Pair[] confs) {
		// queen by queen
		int clash;
		boolean foundSolution = true;
		for (int i = 0; i < n; i++) {
			// cannot have col or row conflict (one per col/row already)
			// assess diag conflict (one malus pt per clash)
			clash = getClash(i, places);
			if (foundSolution && clash > 0) {
				foundSolution = false;
			}
			confs[i] = new Pair(i, clash);
			// debugln("confs for (" + i + "," + places[i] + ") = " + clash);

			// could extract max/min value(s) while looping and do selection just
			// outside loop?
		}

		// sort conflicts
		Arrays.sort(confs);

		return foundSolution;

	}

	@SuppressWarnings("unused")
	private void displayConfs(Pair[] confs) {
		for (int i = 0; i < n; i++) {
			debugln("at row: " + confs[i].row + " , confs:" + confs[i].value);
		}
	}

	private int getClash(int i, int[] places) {
		int clash = 0;
		int placeI = places[i];
		for (int k = 0; k < n; k++) {
			if (k == i)
				continue;
			// same diag indeed
			if (Math.abs(k - i) == Math.abs(placeI - places[k])) {
				clash++;
			}
		}
		return clash;
	}

	private int getClash2(int i, int[] places) {
		int clash = 0;

		LOOP_DIRS: for (int[] dir : dirs) {
			int mov = 1;
			int row = i + (mov * dir[0]);
			int col = places[i] + (mov * dir[1]);
			while (isValid(row, col)) {
				if (col == places[row])
					clash++;
				mov++;
				row = i + (mov * dir[0]);
				col = places[i] + (mov * dir[1]);
				// if (clash > maxNbOfClashes) {
				// debugln("breaking");
				// break LOOP_DIRS;
				// }
			}
		}

		return clash;

	}

	private boolean isValid(int row, int col) {
		if (row < 0 || row >= n || col < 0 || col >= n)
			return false;
		return true;
	}

	private void init(int[] places) {
		List<Integer> placesTmp = new ArrayList<Integer>(n);
		for (int i = 0; i < n; i++) {
			placesTmp.add(i);
		}
		Collections.shuffle(placesTmp);
		for (int i = 0; i < n; i++) { // toArray doesnt work
			places[i] = placesTmp.get(i);
		}
	}

	@SuppressWarnings("unused")
	private void displayGrid(int[] places) {
		int j, val;
		for (int i = 0; i < n; i++) {
			val = places[i];
			for (j = 0; j < val; j++)
				debug("- ");
			debug("# ");
			for (j = val + 1; j < n; j++)
				debug("- ");
			debugln("");
		}
	}

	private void debugln(Object iStr) {
		if (_debug) {
			System.err.println(iStr.toString());
		}
	}

	private void debug(Object iStr) {
		if (_debug) {
			System.err.print(iStr.toString());
		}
	}
}
