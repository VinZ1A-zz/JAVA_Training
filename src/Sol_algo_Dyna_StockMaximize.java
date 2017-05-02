import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Vector;

public class Sol_algo_Dyna_StockMaximize {

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
		// aInput.add("3");
		// aInput.add("5 3 2");
		// aInput.add("3");
		// aInput.add("1 2 100");
		aInput.add("11");
		aInput.add("1 5 7 2 3 4 1 2 1 1 2");
		// aInput.add("4");
		// aInput.add("1 3 1 2");

		return aInput;
	}

	private static void doIt() {
		Scanner scan = new Scanner(System.in);

		int nbTestCases = scan.nextInt();

		for (int testCase = 0; testCase < nbTestCases; testCase++) {
			int n = scan.nextInt();
			// int[] arr = new int[n];
			List<Pair> arr = new ArrayList<>();

			for (int i = 0; i < n; i++) {
				arr.add(new Pair(i, scan.nextInt()));
			}

			arr.sort(Collections.reverseOrder(new compByVal()));

			debugln(arr);

			// isolate maximums
			int curMaxIdx = -1;
			Pair lastPeak = null;
			Map<Integer, Pair> localPeaks = new HashMap<>();
			for (Pair pair : arr) {
				if (curMaxIdx == -1) {
					curMaxIdx = pair.idx;
					debugln("local peak " + pair);
					localPeaks.put(pair.idx, pair);
				} else {
					// skip other maximums which are prior to the last one
					if (pair.idx <= curMaxIdx) {
						continue;
					} else if (lastPeak == null || pair.val < lastPeak.val) {
						curMaxIdx = pair.idx;
						debugln("local peak " + pair);
						localPeaks.put(pair.idx, pair);
						lastPeak = pair;
					}
				}
			}

			// buy before the peaks, sell at peaks

			arr.sort(new compByIdx());
			debugln(arr);
			debugln("lastPeak idx : " + lastPeak);

			long amount = 0;
			int itemsBought = 0;
			for (Pair pair : arr) {
				// after last peak, do nothing
				if (lastPeak != null && pair.idx > lastPeak.idx) {
					continue;
				}
				if (localPeaks.containsKey(pair.idx)) {
					amount = amount + ((long) pair.val * itemsBought);
					debugln("At idx " + pair.idx + " , selling " + itemsBought + " item(s) at val " + pair.val + " , RES = "
							+ amount);
					// sell

					itemsBought = 0;
				} else {
					amount = amount - pair.val;
					debugln("At idx " + pair.idx + " , buying val " + pair.val + " , RES = " + amount);

					itemsBought++;
				}
			}

			println(amount);

		}

		scan.close();

	}

	static class Pair {
		int idx;
		int val;

		Pair(int idx, int val) {
			this.idx = idx;
			this.val = val;
		}

		@Override
		public String toString() {
			return val + "(" + idx + ")";
		}

	}

	static class compByIdx implements Comparator<Pair> {
		@Override
		public int compare(Pair o1, Pair o2) {
			if (o1.idx < o2.idx)
				return -1;
			else if (o1.idx == o2.idx)
				return 0;
			else
				return 1;
		}
	}

	// val first then idx
	static class compByVal implements Comparator<Pair> {
		@Override
		public int compare(Pair o1, Pair o2) {
			if (o1.val < o2.val)
				return -1;
			else if (o1.val == o2.val) {
				if (o1.idx < o2.idx) {
					return -1;
				} else if (o1.idx == o2.idx)
					return 0;
				else
					return 1;
			} else
				return 1;
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
