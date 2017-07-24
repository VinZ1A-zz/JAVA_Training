package hackerRank;
import java.io.ByteArrayInputStream;
import java.util.Scanner;
import java.util.Vector;

public class Sol_algo_DynaProg_Equal {

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

		aInput.add("1");
		// aInput.add("4");
		// aInput.add("2 2 7 3"); // expects 2

		// aInput.add("51");
		// aInput.add(
		// "512 125 928 381 890 90 512 789 469 473 908 990 195 763 102 643 458 366
		// 684 857 126 534 974 875 459 892 686 373 127 297 576 991 774 856 372 664
		// 946 237 806 767 62 714 758 258 477 860 253 287 579 289 496");

		// aInput.add("6"); // expects 6
		// aInput.add("2 5 5 5 5 5");

		aInput.add("5"); // expects 7
		aInput.add("1 5 5 10 10");

		// aInput.add("5"); // expects 7
		// aInput.add("1 1 1 1 1");

		return aInput;
	}

	private static void doIt() {
		otherImpl();

	}

	private static void otherImpl() {
		Scanner scan = new Scanner(System.in);
		int nbUseCases = scan.nextInt();

		// node: adding a number for each of the values except one
		// is equivalent of adding a number on the chosen one
		for (int t = 0; t < nbUseCases; t++) {
			int n = scan.nextInt();
			int[] vals = new int[n];
			int nbOfChanges = 0;

			// read all values and get minimum val
			int min = Integer.MAX_VALUE;
			for (int i = 0; i < n; i++) {
				int val = scan.nextInt();
				vals[i] = val;
				if (val < min) {
					min = val;
				}
			}

			// reduces nbs which are >= than 5 in one shot
			// and also
			// get the nb of elements which are greater than min
			// (from n to n+4)
			int[] a = new int[5];
			for (int i = 0; i < n; i++) {
				int val = vals[i];
				int diff = val - min;
				if (diff >= 5) {
					int willRemove = (diff / 5) * 5;
					int incrementNbOfChanges = diff / 5;
					val -= willRemove;
					vals[i] = val;
					nbOfChanges += incrementNbOfChanges;
				}

				for (int k = 0; k < 5; k++) {
					if (val == min + k)
						a[k]++;
				}
			}

			// cost of aiming for n
			int costN = a[1] + a[2] + 2 * a[3] + 2 * a[4];
			// cost of aiming for n - 1
			int costNMin1 = a[0] + a[1] + 2 * a[2] + 2 * a[3] + a[4];
			// cost of aiming for n - 2
			int costNMin2 = a[0] + 2 * a[1] + 2 * a[2] + a[3] + 2 * a[4];

			// get best aim (minimize cost)
			int remainingCost = 0;
			if (costN <= costNMin1 && costN <= costNMin2) {
				remainingCost = costN;
			} else {
				if (costNMin1 <= costNMin2) {
					remainingCost = costNMin1;
				} else if (costNMin2 < costNMin1) {
					remainingCost = costNMin2;
				}
			}
			nbOfChanges += remainingCost;

			debugln("nb of changes " + nbOfChanges);

			System.out.println(nbOfChanges);
		}

		scan.close();
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
