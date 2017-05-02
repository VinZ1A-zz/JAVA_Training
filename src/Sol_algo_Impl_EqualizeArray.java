import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Vector;

// got up to Points: 741.00 Rank: 35791

public class Sol_algo_Impl_EqualizeArray {

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

		// 2
		aInput.add("5");
		aInput.add("3 3 2 1 3");

		return aInput;
	}

	private static void doIt() {
		Scanner scan = new Scanner(System.in);

		someImpl();

	}

	private static void someImpl() {
		Scanner scan = new Scanner(System.in);

		int size = scan.nextInt();
		Map<Integer, Integer> cnt = new HashMap<>();
		int maxCnt = 1;
		while (scan.hasNext()) {
			int num = scan.nextInt();
			Integer curCnt = cnt.get(num);
			if (curCnt == null) {
				cnt.put(num, 1);
			} else {
				cnt.put(num, ++curCnt);
				if (maxCnt < curCnt) {
					maxCnt = curCnt;
				}
			}
		}

		// debug("maxCnt " + maxCnt);

		// for (Entry<Integer, Integer> i : cnt.entrySet()) {
		// debug(i.getKey() + " : " + i.getValue());
		// }

		int total = 0;
		System.out.println(size - maxCnt);

	}

	private static void debug(Object obj) {
		if (_debug) {
			System.err.println(obj.toString());
		}
	}
}
