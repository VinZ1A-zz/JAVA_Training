import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Vector;

public class Sol_CodeSprint8_PrimeDigitsSum {

	static boolean _debug = false;

	// was
	// https://www.hackerrank.com/contests/world-codesprint-8/challenges/prime-digit-sums/leaderboard
	// unachieved ....
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

		// expects 95 : nb of n-digit nbs (modulo 10exp9+7 ????) satisfying
		// rules (101101 to 902005)
		// aInput.add("1"); // Nb of queries: 1 to 20,000
		// aInput.add("6"); // nb of digits: 1 to 400,000

		// aInput.add("6");
		// aInput.add("7");
		// aInput.add("6");
		// aInput.add("3");
		// aInput.add("60");
		// aInput.add("45");
		// aInput.add("127");
		// aInput.add("12799");

		// aInput.add("4");
		// aInput.add("6");
		// aInput.add("1");
		// aInput.add("3");
		// aInput.add("9");

		aInput.add("42");
		aInput.add("60");
		aInput.add("61");
		aInput.add("62");
		aInput.add("63");
		aInput.add("64");
		aInput.add("65");
		aInput.add("66");
		aInput.add("120");
		aInput.add("121");
		aInput.add("19111");
		aInput.add("177779");
		aInput.add("147289");
		aInput.add("136266");
		aInput.add("193377");
		aInput.add("3691");
		aInput.add("82244");
		aInput.add("42446");
		aInput.add("157601");
		aInput.add("1591");
		aInput.add("114419");
		aInput.add("123282");
		aInput.add("129574");
		aInput.add("171545");
		aInput.add("40925");
		aInput.add("108222");
		aInput.add("34082");
		aInput.add("1871");
		aInput.add("9014");
		aInput.add("123926");
		aInput.add("15581");
		aInput.add("109337");
		aInput.add("108202");
		aInput.add("87613");
		aInput.add("23051");
		aInput.add("69395");
		aInput.add("43358");
		aInput.add("103255");
		aInput.add("189896");
		aInput.add("101261");
		aInput.add("153407");
		aInput.add("198251");
		aInput.add("54535");
		aInput.add("97041");
		aInput.add("146732");
		aInput.add("149491");
		aInput.add("103489");
		aInput.add("128173");
		aInput.add("68302");
		aInput.add("118380");
		aInput.add("34112");

		return aInput;
	}

	private static void doIt() {
		Scanner in = new Scanner(System.in);
		int q = in.nextInt(); // nb of queries
		ArrayList<Integer> listN = new ArrayList<Integer>();
		for (int i = 0; i < q; i++) {
			listN.add(in.nextInt());
		}
		in.close();

		// start with highest N to store results
		ArrayList<Integer> originalOrder = new ArrayList<Integer>();
		originalOrder.addAll(listN);
		Collections.sort(listN);
		Collections.reverse(listN);
		int highestN = listN.get(0);
		System.err.println("highest : " + highestN);

		Map<Integer, Long> answers = new TreeMap<Integer, Long>();
		answers.put(1, (long) 0);
		answers.put(2, (long) 0);

		// for (int n : originalOrder) {
		// System.err.println(n);
		// }

		// for (int nbOfDigits : listN) {

		// need to store the list of 3-digits prime numbers
		Set<Integer> listOfPrimes = null;
		Set<Integer> listOfSumOfPrimes = null;
		listOfPrimes = listOfPrimes(5 * 9); // list of primes up to
		// 9+9+9+9+9
		listOfSumOfPrimes = listOfSumToPrimes(5, listOfPrimes);

		// limit the 4 digits-ones so the first 3 & last 3 are in the list
		int removals = 0;
		for (Iterator<Integer> iter = listOfSumOfPrimes.iterator(); iter.hasNext();) {
			int i = iter.next();
			if (i <= 9999 && i >= 1000) { // 4 digits
				int firstThree = i / 10;
				if (!listOfSumOfPrimes.contains(firstThree)) {
					iter.remove(); // 3010 removals
					continue;
				}
				int lastThree = i % 1000;
				if (!listOfSumOfPrimes.contains(lastThree)) {
					iter.remove();
					removals++;
					continue;
				}
			}
			if (i >= 10000) { // 5 digits
				int firstFour = i / 10;
				if (!listOfSumOfPrimes.contains(firstFour)) {
					iter.remove();
					continue;
				}
				int lastFour = i % 10000;
				if (!listOfSumOfPrimes.contains(lastFour)) {
					iter.remove();
					removals++;
					continue;
				}
			}
		}

		System.err.println("listOfSumOfPrimes.size() = " + listOfSumOfPrimes.size());

		long count3 = 0, count4 = 0, count5 = 0;
		for (int i : listOfSumOfPrimes) {
			if (i < 999 && i > 100) {
				count3++;
			} else if (i < 9999 && i > 1000) {
				count4++;
			} else if (i < 99999 && i > 10000) {
				count5++;
			}
		}
		answers.put(3, count3);
		answers.put(4, count4);
		answers.put(5, count5);

		// first 3-digits nb is 101
		// first 4-digits nb is 1011
		// first 5-digits nb is 10110
		// int i = 10;
		// do {
		// if (listOfSumOfPrimes.contains(i)) {
		// break;
		// }
		// i++;
		// } while (i <= 9999);
		// System.err.println(i);

		// transform in Map
		Map<Integer, Set<String>> digToStr = new HashMap<Integer, Set<String>>();
		digToStr.put(3, new HashSet<String>());
		digToStr.put(4, new HashSet<String>());
		digToStr.put(5, new HashSet<String>());
		for (Integer prime : listOfSumOfPrimes) {
			String valueOfPrime = String.valueOf(prime);
			if (prime <= 999 /* && prime >= 100 */) {
				if (prime <= 9) {
					valueOfPrime = "0" + valueOfPrime;
				}
				if (prime <= 99) {
					valueOfPrime = "0" + valueOfPrime;
				}
				digToStr.get(3).add(valueOfPrime);
				// continue;
			}
			if (prime <= 9999) { // NOT 'real' 4 digits (can start with 0)
				if (prime <= 999) {
					valueOfPrime = "0" + valueOfPrime;
				}
				digToStr.get(4).add(valueOfPrime);
				// continue;
			}
			if (prime <= 99999) { // NOT 'real' 5 digits
				if (prime <= 9999) {
					valueOfPrime = "0" + valueOfPrime;
				}
				digToStr.get(5).add(valueOfPrime);
				// continue;
			}
		}

		// do this ONLY for x = 6
		int x = 6;
		int nextI;
		for (int i = 5; i <= x; i++) { // nb of digits -- remove loop (useless)
			nextI = i + 1;
			digToStr.put(nextI, new HashSet<String>());
			for (String firstI : digToStr.get(i)) {
				if (i == 5 && firstI.startsWith("0")) {
					continue;
				}
				// for each nb, take last 5 digits, add 0-9 as last digit
				// and see if last i
				// digits are in list
				for (int j = 0; j <= 9; j++) {
					String wholeNb = firstI + j;
					String last5AndJ = firstI.substring(i - 5) + j;
					int lastFive = Integer.parseInt(last5AndJ.substring(1));
					if (!listOfSumOfPrimes.contains(lastFive)) {
						continue;
					} else {
						digToStr.get(nextI).add(wholeNb);
					}
				}
			}
		}

		// System.err.println(digToStr.get(6).size());
		// for (int i = 3; i <= 7; i++) {
		// System.err.println(digToStr.get(i).size());
		// }

		// *********** optim attempt *********************

		// init for 6
		x = 6;
		HashMap<String, Long> aCounterOfLastFourDigits = new HashMap<String, Long>();
		HashMap<String, Long> aCounterOfLastFourDigits2 = new HashMap<String, Long>();
		for (String fiveDig : digToStr.get(x)) {
			String last4 = fiveDig.substring(x - 4);
			Long curCounter = aCounterOfLastFourDigits.get(last4);
			if (curCounter == null) {
				aCounterOfLastFourDigits.put(last4, (long) 1);
			} else {
				aCounterOfLastFourDigits.put(last4, curCounter + 1);
			}
		}

		// sum of all known lastFour digits when n = 6
		long test = 0;
		for (Entry<String, Long> bla : aCounterOfLastFourDigits.entrySet()) {
			test = test + bla.getValue();
		}
		answers.put(6, test);
		System.err.println("INIT : " + test);

		// use it until x = 60 and handle counter
		HashMap<String, Long> aCounterOfLastFourDigitsFor60 = null;
		while (x < Math.min(121, highestN)) { // should be 67
			x++;
			for (Entry<String, Long> lastFourDigitsCounter : aCounterOfLastFourDigits.entrySet()) {
				for (int j = 0; j <= 9; j++) {
					String last5 = lastFourDigitsCounter.getKey() + j;
					if (!listOfSumOfPrimes.contains(Integer.parseInt(last5))) {
						continue;
					} else {
						String last4 = last5.substring(1);
						Long curCounter = aCounterOfLastFourDigits2.get(last4);
						if (curCounter == null) {
							aCounterOfLastFourDigits2.put(last4, lastFourDigitsCounter.getValue());
						} else {
							aCounterOfLastFourDigits2.put(last4, (long) ((curCounter + lastFourDigitsCounter.getValue()))); // %
																																																							// (Math.pow(10,
																																																							// 9)
																																																							// +
																																																							// 7)
						}
					}
				}
			}
			// DEBUG loop ----- (answer is total)
			test = 0;
			for (Entry<String, Long> bla : aCounterOfLastFourDigits2.entrySet()) {
				test = test + bla.getValue();
			}
			test = (long) (test % (Math.pow(10, 9) + 7));
			answers.put(x, test);

			// if (x >= 60 && x <= 65) {
			// System.err.println("aCounterOfLastFourDigits with x=" + x + " : "
			// + test);
			// }

			// keep results for 60 (should be in aCounterOfLastFourDigits2
			// anyway)
			if (x == 60) {
				aCounterOfLastFourDigitsFor60 = new HashMap<String, Long>();
				aCounterOfLastFourDigitsFor60.putAll(aCounterOfLastFourDigits2);
			}

			// swap maps
			HashMap<String, Long> aCounterOfLastFourDigitsTemp = new HashMap<String, Long>();
			aCounterOfLastFourDigitsTemp.putAll(aCounterOfLastFourDigits2);
			aCounterOfLastFourDigits2.clear();
			aCounterOfLastFourDigits2.putAll(aCounterOfLastFourDigits);
			aCounterOfLastFourDigits.clear();
			aCounterOfLastFourDigits.putAll(aCounterOfLastFourDigitsTemp);
		}
		// ***********************************************

		Map<Integer, Long> listOfModulos = new TreeMap<Integer, Long>();
		if (highestN > 60) {
			long moduloFor60 = answers.get(60);
			listOfModulos.put(1, moduloFor60);
			double maxModulo = highestN / 60; // with highestN <= Math.pow(10,
			// 4)
			for (int i = 2; i <= maxModulo; i++) {
				listOfModulos.put(i, (long) ((listOfModulos.get(i - 1) * moduloFor60) % (Math.pow(10, 9) + 7)));
			}
		}

		// System.out.println("max modulo for chunk 2 : " + listOfModulos.get(2));

		for (int i : originalOrder) {

			long solution = 0;
			if (i > 60) {
				int nbOfChunks = i / 60;
				int remainder = i % 60;
				// try {
				// marche PO when remainder is between 0 and 4 included
				// !!!!!??!! TODO
				long answerForRemainder = 1;
				if (remainder > 0) {
					if (remainder <= 4) {
						answerForRemainder = answers.get(remainder + 60); // 0 becomes 60, 4
																															// becomes 64, ect
					} else {
						answerForRemainder = answers.get(remainder);
					}
				}
				solution = (long) ((listOfModulos.get(nbOfChunks) * answerForRemainder) % (Math.pow(10, 9) + 7));
				// } catch (NullPointerException e) {
				// System.out.println("ouch " + i);
				// }

				// System.out.println(solution);
			} else {
				solution = answers.get(i);

			}

			// System.out.println(solution);
			System.err.println(i + " --> " + solution);
		}

		// System.err.println(" ### 60 man ### " + answers.get(60));
		// System.err.println(" ### 61 man ### " + answers.get(61));
		// System.err.println(" ### 62 man ### " + answers.get(62));
		// System.err.println(" ### 63 man ### " + answers.get(63));
		// System.err.println(" ### 64 man ### " + answers.get(64));
		// System.err.println(" ### 65 man ### " + answers.get(65));
		// System.err.println(" ### 66 man ### " + answers.get(66));
		// System.err.println(" ### 120 man ### " + answers.get(120));
		// System.err.println(" ### 121 man ### " + answers.get(121));

	}

	// }

	// List of nbs whom the sum of digits is prime, which have <= n digits
	private static Set<Integer> listOfSumToPrimes(int nbDigits, Set<Integer> listOfPrimes) {

		Set<Integer> res = new TreeSet<Integer>();

		double upTo = Math.pow(10, nbDigits) - 1;
		for (int i = 2; i <= upTo; i++) {
			int num = i;
			int sum = 0;
			while (num > 0) {
				sum = sum + num % 10;
				num = num / 10;
			}
			if (listOfPrimes.contains(sum)) {
				res.add(i);
			}
		}
		return res;
	}

	private static Set<Integer> listOfPrimes(int n) {

		Set<Integer> res = new TreeSet<Integer>();
		// double upTo = Math.pow(10, n) - 1;
		double upTo = n;

		res.add(2);
		res.add(3);
		for (int i = 3; i <= upTo; i = i + 2) {
			if (i % 3 == 0) {
				continue;
			}
			double maxSearch = Math.sqrt(i);
			boolean isPrime = true;
			for (double nb : res) {
				if (nb > maxSearch)
					break;
				if (i % nb == 0) {
					isPrime = false;
					break;
				}
			}
			if (isPrime) {
				res.add(i);
				// System.err.println(i);
			}
		}

		return res;
	}

}

// Solution (indic)

// import java.io.*;
// import java.util.*;
// import java.text.*;
// import java.math.*;
// import java.util.regex.*;
//
// public class Solution {
//
// public static boolean[] sieve(int n) {
// boolean[] isPrime = new boolean[n];
// Arrays.fill(isPrime, true);
// isPrime[0] = isPrime[1] = false;
//
// for (int i = 2; i * i < n; i++) {
// if (!isPrime[i]) {
// continue;
// }
//
// for (int k = 2; k * i < n; k++) {
// isPrime[k * i] = false;
// }
// }
//
// return isPrime;
// }
//
// public static void main(String[] args) {
// Scanner in = new Scanner(System.in);
// int q = in.nextInt();
// int N = 0;
// List<Integer> queries = new ArrayList<>();
// for(int a0 = 0; a0 < q; a0++){
// int n = in.nextInt();
// N = Math.max(n + 1, N);
// queries.add(n);
// }
//
// boolean[] isPrime = sieve(100);
// long[] results = new long[Math.max(N + 1, 10)];
// List<String> seq5 = new ArrayList<>();
//
// for (int i = 0; i < 10; i++) {
// if (i > 0) {
// results[1]++;
// }
// for (int j = 0; j < 10; j++) {
// if (i != 0) {
// results[2]++;
// }
// for (int k = 0; k < 10; k++) {
// if (isPrime[i + j + k]) {
// if (i != 0) {
// results[3]++;
// }
// for (int l = 0; l < 10; l++) {
// if (isPrime[j + k + l] && isPrime[i + j + k + l]) {
// if (i != 0) {
// results[4]++;
// }
// for (int m = 0; m < 10; m++) {
// if (isPrime[k + l + m] && isPrime[j + k + l + m] && isPrime[i + j + k + l +
// m]) {
// String s = String.format("%d%d%d%d%d", i, j, k, l, m);
//// int x = Integer.parseInt(s);
// seq5.add(s);
// }
// }
// }
// }
// }
// }
// }
// }
//
// int m = seq5.size();
// int MOD = (int)(1e9 + 7);
// long[][] suffixes = new long[2][m];
// Map<Integer, List<Integer>> g = new HashMap<>();
// for (int i = 0; i < m; i++) {
// g.put(i, new ArrayList<>());
// String x = seq5.get(i);
// if (!x.startsWith("0")) {
// suffixes[1][i] = 1;
// }
//
// for (int j = 0; j < m; j++) {
// String y = seq5.get(j);
// if (x.substring(1, 5).equals(y.substring(0, 4))) {
// g.get(i).add(j);
// }
// }
// }
//
// for (int i = 6; i <= N; i++) {
// Arrays.fill(suffixes[i % 2], 0);
// for (int suf = 0; suf < m; suf++) {
// if (suffixes[(i - 1) % 2][suf] > 0) {
// results[i - 1] += suffixes[(i - 1) % 2][suf];
// results[i - 1] %= MOD;
// for (int next : g.get(suf)) {
// suffixes[i % 2][next] += suffixes[(i - 1) % 2][suf];
// suffixes[i % 2][next] %= MOD;
// }
// }
// }
// }
//
// for (int query : queries) {
// System.out.println(results[query]);
// }
// }
// }