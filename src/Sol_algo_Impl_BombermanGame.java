import java.io.ByteArrayInputStream;
import java.util.Scanner;
import java.util.Vector;

// Points: 1046.00 Rank: 21918
public class Sol_algo_Impl_BombermanGame {

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

		// expects :
		// OOO.OOO
		// OO...OO
		// OOO...O
		// ..OO.OO
		// ...OOOO
		// ...OOOO
		aInput.add("6 7 5");
		aInput.add(".......");
		aInput.add("...O...");
		aInput.add("....O..");
		aInput.add(".......");
		aInput.add("OO.....");
		aInput.add("OO.....");

		return aInput;
	}

	private static void doIt() {
		someImpl();
	}

	static int R;
	static int C;

	private static void someImpl() {
		Scanner scan = new Scanner(System.in);

		R = scan.nextInt();
		C = scan.nextInt();
		int N = scan.nextInt();

		// init (t = 0)
		int[][] arr = new int[C][R]; // COL first
		for (int i = 0; i < R; i++) {
			String rowStr = scan.next();
			for (int j = 0; j < C; j++) {
				arr[j][i] = rowStr.charAt(j) == 'O' ? 2 : -1;
			}
		}

		int t = 1;
		display(arr, t);

		// t = 2 (bombs 2 ->1)
		nextSec(arr);
		t++;
		display(arr, t);

		// adjust t so we don't loop for ages ;)
		if (N >= 5) {// from t=6, can infer
			debug("sec init " + N + ", ");
			// PFFIOOUUU!!!!!
			N = (N % 4) + 4; // eg. N=5 is t=6, eq to t=2, N=6 is t=7, eq to t=3, ect
			debugln("adjusted to " + N);
		}

		while (t < N + 1) {
			// t = 3 (new bombs 2, bombs 1 -> 0)
			nextSec(arr);
			plantNewBombs(arr);
			t++;
			display(arr, t);
			if (t == N + 1)
				break;

			// t = 4 (bombs 0 explode, bombs 2 -> 1)
			nextSec(arr);
			t++;
			display(arr, t);
		}

		display(arr, t, false);
	}

	private static void plantNewBombs(int[][] arr) {
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (arr[j][i] == -1) {
					arr[j][i] = 2;
				}
			}
		}
	}

	private static void nextSec(int[][] arr) {
		// all bombs ready to explode (0) explode
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (arr[j][i] == 0) {
					explode(arr, j, i);
				}
			}
		}

		// bombs 1 -> 0
		// bombs 2 -> 1
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (arr[j][i] > 0) {
					arr[j][i] = arr[j][i] - 1;
				}
			}
		}

	}

	static int[][] dirs = { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };

	private static void explode(int[][] arr, int col, int row) {
		// look for surrounding immature bombs (>0)
		for (int[] dir : dirs) {
			int nxtCol = col + dir[0], nxtRow = row + dir[1];
			if (isValid(arr, nxtCol, nxtRow) && arr[nxtCol][nxtRow] > 0) {
				arr[nxtCol][nxtRow] = -1;
			}
		}
		arr[col][row] = -1; // explode main bomb
	}

	private static boolean isValid(int[][] arr, int col, int row) {
		if (col < 0 || col >= C || row < 0 || row >= R) {
			return false;
		}
		return true;
	}

	private static void display(int[][] arr, int t, boolean debug) {
		debugln("---- AT T = " + t + " (N=" + (t - 1) + ") -----");
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (arr[j][i] >= 0) {
					if (debug)
						debug(arr[j][i]);
					else
						System.out.print('O');
				} else {
					if (debug)
						debug(".");
					else
						System.out.print(".");
				}
			}
			if (debug)
				debugln("");
			else
				System.out.println();
		}
	}

	private static void display(int[][] arr, int t) {
		display(arr, t, true); // debug = yes by default
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
}
