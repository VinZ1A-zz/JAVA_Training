package hackerRank;
import java.io.ByteArrayInputStream;
import java.util.Scanner;
import java.util.Vector;

public class Solution_Day17 {

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

		aInput.add("4");
		aInput.add("3 5");
		aInput.add("2 4");
		aInput.add("-1 -2");
		aInput.add("-1 3");

		return aInput;
	}

	private static void doIt() {
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		while (T-- > 0) {
			int n = in.nextInt();
			int p = in.nextInt();
			Calculator myCalculator = new Solution_Day17().new Calculator();
			try {
				int ans = myCalculator.power(n, p);
				System.out.println(ans);

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		in.close();
	}

	// ----- added code ------
	public class Calculator {

		public int power(int n, int p) throws Exception {

			if (n < 0 || p < 0) {
				throw new Exception("n and p should be non-negative");
			}

			return (int) Math.pow(n, p);
		}

	}
	// ----------
}
