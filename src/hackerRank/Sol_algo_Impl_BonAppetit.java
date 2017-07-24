package hackerRank;
import java.io.ByteArrayInputStream;
import java.util.Scanner;
import java.util.Vector;

public class Sol_algo_Impl_BonAppetit {

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

		// Expects 5
		aInput.add("4 1"); // n (nbItems), k(item not eaten)
		aInput.add("3 10 2 9");
		aInput.add("12"); // charged by Brian (7 would print Bon Appetit)

		return aInput;
	}

	@SuppressWarnings("resource")
	private static void doIt() {
		Scanner in = new Scanner(System.in);

		int n = in.nextInt();
		int notEaten = in.nextInt();
		int totalShared = 0;
		for (int i = 0; i < n; i++) {
			int valItem = in.nextInt();
			if (i != notEaten) {
				totalShared += valItem;
			}
		}

		// debug("total Shared " + totalShared);
		int charged = in.nextInt();
		int shouldBe = totalShared / 2;

		if (charged == shouldBe) {
			System.out.println("Bon Appetit");
		} else {
			System.out.println(charged - shouldBe);
		}

	}

	@SuppressWarnings("unused")
	private static void debug(String iStr) {
		if (_debug) {
			System.err.println(iStr);
		}
	}
}
