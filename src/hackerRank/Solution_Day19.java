package hackerRank;
import java.io.ByteArrayInputStream;
import java.util.Scanner;
import java.util.Vector;

public class Solution_Day19 {

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

		aInput.add("6");

		return aInput;
	}

	private static void doIt() {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		scan.close();

		AdvancedArithmetic myCalculator = new Calculator();
		int sum = myCalculator.divisorSum(n);
		System.out.println("I implemented: " + myCalculator.getClass().getInterfaces()[0].getName());
		System.out.println(sum);

	}
}

interface AdvancedArithmetic {
	int divisorSum(int n);
}

class Calculator implements AdvancedArithmetic {

	@Override
	public int divisorSum(int n) {

		int sum = 0;
		for (int i = 1; i <= n; i++) {
			if (n % i == 0) {
				sum += i;
			}
		}

		return sum;
	}

}
