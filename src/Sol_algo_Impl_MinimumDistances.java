import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Vector;

// Points: 996.00 Rank: 23683
public class Sol_algo_Impl_MinimumDistances {

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

		// expects yes then swap 1 2
		aInput.add("2"); // do not read
		aInput.add("1 2 4 3 2 5");

		return aInput;
	}

	private static void doIt() {
		someImpl();
	}

	private static void someImpl() {
		Scanner scan = new Scanner(System.in);

		scan.nextInt(); // rien a fout
		Map<Integer, Integer> lastSeen = new HashMap<>();
		int i = 0;
		int minDist = Integer.MAX_VALUE;
		while (scan.hasNext()) {
			int num = scan.nextInt();
			if (lastSeen.containsKey(num)) {
				if (minDist > i - lastSeen.get(num)) {
					minDist = i - lastSeen.get(num);
				}
			}
			lastSeen.put(num, i++);

		}
		if (minDist == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else
			System.out.println(minDist);

	}

	private static void debug(Object obj) {
		if (_debug) {
			System.err.println(obj.toString());
		}
	}
}
