import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Vector;

public class Sol_ctci_lonelyInteger {

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

		aInput.add("5");
		aInput.add("0 0 1 2 1");

		return aInput;
	}

	private static void doIt() {
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int a[] = new int[n];
		for (int a_i = 0; a_i < n; a_i++) {
			a[a_i] = in.nextInt();
		}

		// bruteForce(a);
		usingBitComp(a);
	}

	// bit comp, does not require sorting
	private static void usingBitComp(int[] a) {
		int res = 0;
		// complexity is n
		for (int nb : a) {
			// XOR'ing pairs will cancel each other out at the end
			// and the orphan will remain
			// (regardless of the order since XOR is associative and
			// commutative!)
			res = res ^ nb;
		}
		System.out.println(res);
	}

	// not using bit comparison
	@SuppressWarnings("unused")
	private static void bruteForce(int[] a) {
		Arrays.sort(a); // n log n and then n --> n log n
		int lonely = -1;
		for (int i = 0; i < a.length - 2; i = i + 2) {
			int cur = a[i];
			int nxt = a[i + 1];
			if (cur != nxt) {
				lonely = cur;
				break;
			}
		}
		if (lonely == -1) {
			lonely = a[a.length - 1];
		}
		System.out.println(lonely);
	}
}
