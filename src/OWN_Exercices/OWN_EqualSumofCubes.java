package OWN_Exercices;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Vector;

public class OWN_EqualSumofCubes {

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

		aInput.add("10000");

		return aInput;
	}

	private static void doIt() {
		Scanner scan = new Scanner(System.in);

		int max = scan.nextInt();

		scan.close();

		Map<Integer, List<Pair>> sumToPair = new HashMap<>();
		int maxLoop = (int) Math.cbrt(max) + 1;
		debugln("maxLoop : " + maxLoop);
		debugln("max : " + max);
		for (int a = 1; a < maxLoop; a++) {
			int powA3 = (int) Math.pow(a, 3);
			for (int b = a; b < maxLoop; b++) {
				// could add list of powers in hashTable
				int sum = powA3 + (int) Math.pow(b, 3);
				if (sum > max)
					break;
				if (!sumToPair.containsKey(sum)) {
					List<Pair> listPairs = new ArrayList<>();
					sumToPair.put(sum, listPairs);
				}
				sumToPair.get(sum).add(new Pair(a, b));
			}
		}

		for (Entry<Integer, List<Pair>> entry : sumToPair.entrySet()) {
			int compSum = max - entry.getKey();
			if (!sumToPair.containsKey(compSum) || compSum < max / 2) {
				continue;
			}

			debug("sum " + entry.getKey() + " : " + entry.getValue());

			debug(" \t########## complement with " + sumToPair.get(compSum));

			debugln("");
		}

	}

	static class Pair {
		int val1;
		int val2;

		Pair(int val1, int val2) {
			this.val1 = val1;
			this.val2 = val2;
		}

		@Override
		public String toString() {
			return val1 + "," + val2;
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
