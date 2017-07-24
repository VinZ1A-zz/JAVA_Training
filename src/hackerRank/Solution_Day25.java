package hackerRank;
import java.io.ByteArrayInputStream;
import java.util.Scanner;
import java.util.Vector;

public class Solution_Day25 {

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

		// aInput.add("3");
		aInput.add("12");
		aInput.add("5");
		aInput.add("7");

		// aInput.add("30");
		aInput.add("1");
		aInput.add("4");
		aInput.add("9");
		aInput.add("16");
		aInput.add("25");
		aInput.add("36");
		aInput.add("49");
		aInput.add("64");
		aInput.add("81");
		aInput.add("100");
		aInput.add("121");
		aInput.add("144");
		aInput.add("169");
		aInput.add("196");
		aInput.add("225");
		aInput.add("256");
		aInput.add("289");
		aInput.add("324");
		aInput.add("361");
		aInput.add("400");
		aInput.add("441");
		aInput.add("484");
		aInput.add("529");
		aInput.add("576");
		aInput.add("625");
		aInput.add("676");
		aInput.add("729");
		aInput.add("784");
		aInput.add("841");
		aInput.add("907");

		return aInput;
	}

	private static boolean isPrime(int n) {
		if (n == 1) // 1 no prime
			return false;
		if (n == 2) // 2 prime
			return true;
		if (n % 2 == 0)
			return false; // odd nb can't be prime

		int maxTest = (int) Math.floor(Math.sqrt(n));
		for (int i = 3; i <= maxTest; i = i + 2) {
			if (n % i == 0) {
				// System.err.println("divisible by " + i);
				return false;
			}
		}

		return true;
	}

	private static void doIt() {
		Scanner scan = new Scanner(System.in);

		scan.nextInt(); // trash first
		while (scan.hasNext()) {
			int n = scan.nextInt();
			// System.out.println("processing " + n);
			System.out.println(isPrime(n) ? "Prime" : "Not prime");

		}

		scan.close();

	}
}
