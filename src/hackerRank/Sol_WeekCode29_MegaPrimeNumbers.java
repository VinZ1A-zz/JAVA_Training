package hackerRank;
import java.io.ByteArrayInputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Vector;

public class Sol_WeekCode29_MegaPrimeNumbers {

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

		// expects :
		// 8 ( 2,3,5,7,23,37,53,73)
		// up to 10exp15 with diff <= 10exp9
		// aInput.add("1 100");
		// aInput.add("6555222333 7333888777");
		aInput.add("5333888777 5555222333"); // 5170
		// aInput.add("1000000 108000000");
		// aInput.add("500 2000000000"); // too big (1999999500)

		return aInput;
	}

	private static void doIt() {
		someImpl();
	}

	private static boolean isValid(String iStr) {
		for (int i = 0; i < iStr.length(); i++) {
			char a = iStr.charAt(i);
			if (a != '2' && a != '3' && a != '5' && a != '7') {
				return false;
			}
		}

		return true;
	}

	private static void someImpl() {
		Scanner scan = new Scanner(System.in);

		String first = scan.next(); // min bound
		String last = scan.next(); // max bound
		scan.close();
		BigInteger min = new BigInteger(first);
		BigInteger max = new BigInteger(last);

		// validity checks (when testing locally)
		// BigInteger diff = max.subtract(min);
		// BigInteger maxDiff = new BigInteger("1000000000");
		// debugln("DIFF : " + diff);
		// if (diff.compareTo(maxDiff) > 0) {
		// debugln(diff + " is bigger than " + maxDiff);
		// }

		String firstLast9 = first;
		String lastLast9 = last;
		String firstPrefix = "";
		String lastPrefix = "";
		String validPrefix = "";
		boolean hasLowerBound = true;
		boolean hasUpperBound = true;

		if (first.length() > 9) {
			firstLast9 = first.substring(first.length() - 9);
			debugln("firstLast9 " + firstLast9);
			firstPrefix = first.substring(0, first.length() - 9);
			debugln("firstPrefix " + firstPrefix);
			lastLast9 = last.substring(last.length() - 9);
			debugln("lastLast9 " + lastLast9);
			lastPrefix = last.substring(0, last.length() - 9);
			debugln("lastPrefix " + lastPrefix);

			if (!isValid(firstPrefix) && !isValid(lastPrefix)) {
				print(0);
				debugln("prefix not valid");
				return;
			}
			// prefixes are either equal,
			// or one of them is not valid as they would follow each other
			validPrefix = firstPrefix; // default
			if (!firstPrefix.equals(lastPrefix)) {
				if (isValid(firstPrefix)) {
					hasUpperBound = false;
				} else {
					hasLowerBound = false;
					validPrefix = lastPrefix;
				}
			}
			debugln("valid prefix : " + validPrefix);
			debugln("hasLowBound " + hasLowerBound + " ; hasUpBound " + hasUpperBound);
		}

		debugln("starting from " + firstLast9 + " to " + lastLast9);
		int lenMin = firstLast9.length();
		int lenMax = lastLast9.length();

		int counterPrimes = 0;
		for (int i = lenMin; i <= lenMax; i++) {
			debugln("****************** GO **********************");
			debugln("size " + i);
			// int counter = 0;
			ArrayList<String> list = getPerm(i);
			for (String item : list) {
				if (validPrefix.length() > 0) {
					item = validPrefix + item;
				}
				// skipping first/last numbers when applicable
				if (hasLowerBound && i == lenMin && item.compareTo(first) < 0) {
					continue;
				}
				// debugln("compare " + itemForLast + " and " + last);
				if (hasUpperBound && i == lenMax && item.compareTo(last) > 0) {
					continue;
				}
				BigInteger cur = new BigInteger(item);
				if (cur.isProbablePrime(20)) {
					counterPrimes++;
					// debugln(item);
				}

				// counter++;
			}
			debugln("counter of primes : " + counterPrimes);
			// debugln("total : " + counter);

		}
		print(counterPrimes);

	}

	// get all permutations using possible digits (facade)
	static ArrayList<String> getPerm(int size) {
		ArrayList<String> list = new ArrayList<>();
		if (size == 1) {
			list.addAll(Arrays.asList("2", "3", "5", "7"));
		} else {
			getPerm(0, new char[size], list);
		}
		return list;
	}

	// getPerm : recursive call (I can't believe I struggled there!)
	static void getPerm(int pos, char[] perm, ArrayList<String> list) {
		if (pos == perm.length) {
			list.add(new String(perm));
			return;
		}
		int begDigit = 0;
		if (pos == perm.length - 1) { // last digit
			begDigit = 2; // skip '2' and '5' (odd nb or divisible by 5)
		}
		for (int k = begDigit; k < 4; k++) {
			perm[pos] = getChar(k);
			getPerm(pos + 1, perm, list);
		}
	}

	static private char getChar(int idx) {
		switch (idx) {
		case 0:
			return '2';
		case 1:
			return '5';
		case 2:
			return '3';
		case 3:
			return '7';
		}
		return Character.UNASSIGNED;
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
