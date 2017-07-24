package hackerRank;
import java.io.ByteArrayInputStream;
import java.util.Scanner;
import java.util.Vector;

//Points: 926.00 Rank: 26342
public class Sol_algo_Impl_ModifiedKaprekarNum {

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

		aInput.add("1");
		aInput.add("100");

		return aInput;
	}

	private static void doIt() {
		someImpl();

	}

	private static void someImpl() {
		Scanner scan = new Scanner(System.in);

		int beg = scan.nextInt();
		int end = scan.nextInt();

		scan.close();

		if (beg <= 1 && end >= 1)
			System.out.print("1 ");
		if (beg <= 9 && end >= 9)
			System.out.print("9 ");

		boolean oneNumFound = false;
		for (int i = Math.max(10, beg); i <= end; i++) {
			// String str = String.valueOf(i);

			long sq = (long) Math.pow(i, 2);
			String strSq = String.valueOf(sq);
			int strSqlen = strSq.length();
			int a = Integer.parseInt(strSq.substring(0, strSqlen / 2));
			int b = Integer.parseInt(strSq.substring(strSqlen / 2));
			// debug(i + " : " + a + " + " + b);
			if (b != 0 && i == a + b) {
				System.out.print(i + " ");
				oneNumFound = true;
				continue;
			}
			if (strSqlen % 2 == 1) { // extra calculus for odd length
				a = Integer.parseInt(strSq.substring(0, strSqlen / 2 + 1));
				b = Integer.parseInt(strSq.substring(strSqlen / 2 + 1));
				if (b != 0 && i == a + b) {
					System.out.print(i + " ");
					oneNumFound = true;
					continue;
				}
			}
		}

		if (!oneNumFound) {
			System.out.println("INVALID RANGE");
		}

	}

	private static void debug(Object obj) {
		if (_debug) {
			System.err.println(obj.toString());
		}
	}
}
