package hackerRank;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

public class Sol_WeekCode28_LuckyNumber8 {

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

		// expects 3
		aInput.add("3");
		aInput.add("968");

		// expects 15 (all combinations)
		// aInput.add("4");
		// aInput.add("0808");

		return aInput;
	}

	private static void doIt() {
		usingDP();
		// bruteForce(10000); // -1 : from input

		// beforeChange();
	}

	// http://stackoverflow.com/questions/31086991/number-of-sub-sequences-of-a-given-array-that-are-divisible-by-n
	private static void usingDP() {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		String number = in.next();

		long moduloNb = (long) (Math.pow(10, 9) + 7);
		long[] prevFigs = new long[8];
		long[] nextFigs = new long[8];
		initArray(prevFigs);
		int newDigit, newRemainder;
		prevFigs[0] = 1; // one empty sequence with 0 remainder
		for (int n = 0; n < N; n++) {
			initArray(nextFigs);
			newDigit = Integer.parseInt(number.substring(n, n + 1));
			for (int r = 0; r < 8; r++) {
				newRemainder = (10 * r + newDigit) % 8;
				nextFigs[newRemainder] += prevFigs[r] % moduloNb;
				nextFigs[r] += prevFigs[r] % moduloNb;
			}
			// prevFigs = nextFigs; // hell, no
			// System.arraycopy(nextFigs, 0, prevFigs, 0, nextFigs.length); // not
			// quick enough (?)
			arrayCopy(nextFigs, prevFigs);
		}
		System.out.println(prevFigs[0] - 1);
	}

	private static void initArray(long[] x) {
		for (int i = 0; i < 8; i++) { // x.length
			x[i] = 0;
		}
	}

	private static void arrayCopy(long[] from, long[] to) {
		for (int i = 0; i < 8; i++) { // x.length
			to[i] = from[i];
		}
	}

	// brute force solution (tests up to a few thousands)
	private static void bruteForce(int forcedInp) {
		int n;
		String number;
		List<String> aaa = new ArrayList<>();
		if (forcedInp == -1) {
			Scanner in = new Scanner(System.in);
			n = in.nextInt();
			number = in.next();
			getAllCombs(number, n, aaa);

			long nBOfMult = 0;
			for (String elem : aaa) {
				// System.out.println(elem);
				if (Integer.parseInt(elem) % 8 == 0) {
					nBOfMult++;
				}
			}
			System.out.println("total size " + aaa.size());
			System.out.println("Mult of 8 for " + 1 + " : " + nBOfMult);
		} else {
			for (int i = 2; i <= forcedInp; i++) {
				aaa.clear();
				getAllCombs(Integer.toString(i), (int) Math.ceil(Math.log(i) / Math.log(10)), aaa);

				long nBOfMult = 0;
				for (String elem : aaa) {
					// System.out.println(elem);
					if (Integer.parseInt(elem) % 8 == 0) {
						nBOfMult++;
					}
				}
				// System.out.println("total size for " + i + ":" + aaa.size());
				System.out.println("Mult of 8 for " + i + " : " + nBOfMult);
			}
		}

	}

	static void getAllCombs(String nb, int n, List<String> list) {
		if (n == 1) {
			list.add(String.valueOf(nb));
			return;
		}
		getAllCombs(nb.substring(0, n - 1), n - 1, list);
		ArrayList<String> added = new ArrayList<>();
		added.add(nb.substring(n - 1, n));
		for (String elem : list) {
			added.add(elem + nb.substring(n - 1, n));
		}
		list.addAll(added);
	}

	// Solution with subsequences using only consecutive numbers
	private static void beforeChange() {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		String number = in.next();
		// your code goes here

		long total = 0;
		long moduloNb = (long) (Math.pow(10, 9) + 7);
		if (Integer.parseInt(number.substring(0, 1)) % 8 == 0)
			total++;
		for (int l = 2; l <= n; l++) {
			if (l == 2) {
				for (int k = 0; k <= 1; k++) // general case: l - k - 1 but check if
																			// result >=0 --- TODO
				{
					if (Integer.parseInt(number.substring(k, 2)) % 8 == 0) {
						total++;
					}
				}
			} else // 3 or more combinations added
			{
				// enough to look at last three digits only
				boolean lastThreeDivisible = false;
				for (int k = 1; k <= 3; k++) {
					if (Integer.parseInt(number.substring(l - k, l)) % 8 == 0) {
						total++;
						if (k == 3) {
							lastThreeDivisible = true;
						}
					}
				} // end check last three additions
				if (lastThreeDivisible) {
					// all remaining possibilities are also divisible by 8
					total = total + l - 3;
				}
				total = total % moduloNb;
			}
		} // end loop through number
		System.out.println(total);
	}
}
