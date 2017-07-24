package hackerRank;
import java.io.ByteArrayInputStream;
import java.math.BigInteger;
import java.util.Scanner;
import java.util.Vector;

public class Sol_algo_Dyna_Sam_SubStrings {

	static final boolean _debug = "true".equals(System.getProperties().get("debug"));

	public static void main(String[] args) {

		if (args.length != 0) {
			System.err.println("in debug with debug = " + System.getProperties().get("debug"));
			// _debug = true; // set as final
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

		// aInput.add("123"); // 164
		aInput.add("972698438521"); // 445677619

		return aInput;
	}

	private static void doIt() {
		Scanner scan = new Scanner(System.in);

		String str = scan.next();
		scan.close();

		usingInt(str);

	}

	// input: 972698438521 ; got 906070431 instead of 445677619 with int
	// okay with long
	private static void usingInt(String str) {
		long sum = 0;
		long nextPrepSum = 0;
		int modulo = 1_000_000_007; // 10exp9 + 7
		for (int i = 0; i < str.length(); i++) {
			int val = Integer.parseInt("" + str.charAt(i));
			if (i == 0) {
				sum = val;
				nextPrepSum = val * 10;
			} else { // based on prev val
				int valTimesIPlusOne = val * (i + 1);
				nextPrepSum += valTimesIPlusOne;
				sum += nextPrepSum;
				sum = sum % modulo;
				nextPrepSum = nextPrepSum * 10;
				nextPrepSum = nextPrepSum % modulo;
			}

			debugln("sum : " + sum);
		}

		println(sum);
	}

	// OK but kind of cheating ;)
	private static void usingBigInteger(String str) {
		BigInteger sum = BigInteger.ZERO;
		BigInteger ten = BigInteger.valueOf(10);
		// int nextDigMult = 0;
		BigInteger nextPrepSum = BigInteger.ZERO;
		BigInteger valTimesIPlusOne = null;
		BigInteger modulo = BigInteger.valueOf(1_000_000_000 + 7); // 10exp9 + 7
		for (int i = 0; i < str.length(); i++) {
			int val = Integer.parseInt("" + str.charAt(i));
			if (i == 0) {
				sum = BigInteger.valueOf(val);
				nextPrepSum = BigInteger.valueOf(val).multiply(ten);
				// nextDigMult = i + 1; // needed?
			} else { // based on prev val
				valTimesIPlusOne = BigInteger.valueOf(val * (i + 1));
				nextPrepSum = nextPrepSum.add(valTimesIPlusOne);
				sum = sum.add(nextPrepSum);
				sum = sum.mod(modulo);
				nextPrepSum = nextPrepSum.multiply(ten);
				nextPrepSum = nextPrepSum.mod(modulo);

			}
			debugln("sum : " + sum);
		}

		sum = sum.mod(modulo);

		println(sum);
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
