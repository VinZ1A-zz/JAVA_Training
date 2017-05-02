import java.io.ByteArrayInputStream;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.Vector;

// I totally screwed up this one
public class Sol_algo_Strings_RichieRich {

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

		// aInput.add("5 1"); // odd --> -1
		// aInput.add("13943");

		// aInput.add("4 1");
		// aInput.add("3943");

		// aInput.add("6 3"); // expects 992299
		// aInput.add("092282");

		// aInput.add("3 1");
		// aInput.add("173");

		// aInput.add("1 1"); // expects 9
		// aInput.add("5");

		// expects 992299
		// aInput.add("6 2");
		// aInput.add("932239");

		aInput.add("44 45");
		aInput.add("12345671890123456718901234567189012345671890");

		// expects 12921
		// aInput.add("5 1");
		// aInput.add("12321");

		// aInput.add("5 4"); // expects 99399
		// aInput.add("11331");

		return aInput;
	}

	private static void doIt() {
		Scanner scan = new Scanner(System.in);

		int len = scan.nextInt(); // length
		int k = scan.nextInt();

		String in = scan.next();
		String middle = "";
		scan.close();
		debugln(in);

		int offsetRight = 0;
		if (len % 2 != 0) { // odd, keep middle out
			middle = in.substring(len / 2, len / 2 + 1);
			debugln("middle " + middle);
			offsetRight++;
		}

		if (len == 1) {
			if (k >= 1) {
				print("9");
			} else {
				print(middle);
			}
			return;
		}

		String leftStr = in.substring(0, len / 2);
		debugln(leftStr);
		StringBuilder rightStr = new StringBuilder(in.substring(len / 2 + offsetRight));
		rightStr.reverse();
		debugln(rightStr);

		Set<Integer> needChanges = new TreeSet<>(); // need to be sorted
		for (int i = 0; i < leftStr.length(); i++) {
			char l = leftStr.charAt(i);
			char r = rightStr.charAt(i);
			if (l != r) {
				needChanges.add(i);
			}
		}
		debugln("need at least changes : " + needChanges.size());
		if (needChanges.size() > k) {
			print(-1);
			return;
		}

		// StringBuilder test = new StringBuilder("ABCD");
		// test.replace(1, 2, "X"); // AXCD : replace at index 1
		// debugln(test);

		// cheap palindrom, take best of both
		StringBuilder newLeftStr = new StringBuilder(leftStr);
		StringBuilder newRightStr = new StringBuilder(rightStr);
		for (int i : needChanges) {
			char l = leftStr.charAt(i);
			char r = rightStr.charAt(i);
			if (l > r) { // use left in right
				newRightStr.replace(i, i + 1, Character.toString(l));
			} else {
				newLeftStr.replace(i, i + 1, Character.toString(r));
			}
		}

		k = k - needChanges.size();
		debugln("now we have " + newLeftStr + "#" + newRightStr);
		debugln("we can still do " + k + " changes");

		// for (int i : needChanges) {
		for (int i = 0; i < len / 2; i++) {
			// let's make it a nine and make heaps of money!
			if (((k >= 1 && needChanges.contains(i)) || k >= 2) //
					&& newRightStr.charAt(i) != '9') {
				newRightStr.replace(i, i + 1, "9");
				newLeftStr.replace(i, i + 1, "9");
				if (needChanges.contains(i))
					k--; // already got credit
				else
					k = k - 2;
			}

			if (k == 0)
				break;// no more possible improvement
		}

		if (k > 0 && middle.length() > 0) {
			middle = "9";
			k--;
		}

		debugln("now we have " + newLeftStr + "#" + newRightStr);

		debugln("final : " + newLeftStr + "#" + middle + "#" + newRightStr.reverse());
		print("" + newLeftStr + middle + newRightStr);

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
