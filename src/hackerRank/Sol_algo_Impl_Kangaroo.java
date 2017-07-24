package hackerRank;
import java.io.ByteArrayInputStream;
import java.util.Scanner;
import java.util.Vector;

public class Sol_algo_Impl_Kangaroo {

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

		// YES
		// aInput.add("0 3 4 2"); // x1, v1, x2, v2

		// NO
		// aInput.add("21 6 47 3");

		// YES
		aInput.add("14 4 98 2");

		return aInput;
	}

	@SuppressWarnings("resource")
	private static void doIt() {
		Scanner in = new Scanner(System.in);

		int x1 = in.nextInt();
		int v1 = in.nextInt();
		int x2 = in.nextInt();
		int v2 = in.nextInt();

		// x1 + x * v1 = x2 + y * v2 ?

		if (x1 == x2) {
			System.out.println("YES");
			return;
		}

		// simplify writing - assume x1 < x2
		if (x1 > x2) {
			swap(x1, x2);
			swap(v1, v2);
		}

		if (v1 <= v2) {
			System.out.println("NO");
			return;
		}

		int limit = Math.max(x2 - x1, v1 * v2);
		while (x2 - x1 <= limit) {
			x1 += v1;
			x2 += v2;
			if (x1 == x2) {
				System.out.println("YES");
				return;
			}
		}

		System.out.println("NO");

		in.close();

	}

	private static void swap(int x, int y) {
		int temp = x;
		x = y;
		y = temp;
	}

	@SuppressWarnings("unused")
	private static void debug(String iStr) {
		if (_debug) {
			System.err.println(iStr);
		}
	}
}
