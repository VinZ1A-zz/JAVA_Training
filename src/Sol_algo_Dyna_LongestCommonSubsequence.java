import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Vector;

public class Sol_algo_Dyna_LongestCommonSubsequence {

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

		// aInput.add("5 6");
		// aInput.add("1 2 3 4 1");
		// aInput.add("3 4 1 2 1 3");

		// aInput.add("7 6");
		// aInput.add("1 2 3 2 4 1 2");
		// aInput.add("2 4 3 1 2 1");

		// expects 3 3 9 9 7 0
		aInput.add("9 10");
		aInput.add("3 9 8 3 9 7 9 7 0");
		aInput.add("3 3 9 9 9 1 7 2 0 6");

		return aInput;
	}

	private static void doIt() {
		Scanner scan = new Scanner(System.in);

		int n = scan.nextInt();
		int m = scan.nextInt();

		int[] A = new int[n];
		int[] B = new int[m];
		for (int i = 0; i < n; i++) {
			A[i] = scan.nextInt();
		}
		for (int i = 0; i < m; i++) {
			B[i] = scan.nextInt();
		}

		// recursion attempt - can get length easilty, content... ?
		List<Integer> longestSubSequence = getLongestSubsequence(A, B, A.length - 1, B.length - 1);

		// println(longestSubSequence.size());
		for (int i : longestSubSequence) {
			print(i + " ");
		}

		// get one of the subsequence knowing the length?

		scan.close();

	}

	private static List<Integer> getLongestSubsequence(int[] A, int[] B, int i, int j) {
		int[][] cache = new int[A.length][B.length];
		for (int aa = 0; aa < A.length; aa++) {
			for (int bb = 0; bb < B.length; bb++) {
				cache[aa][bb] = -1; // 0 could be a valid, calculated value, pick -1
														// instead
			}
		}
		Map<Pair, List<Integer>> subSequences = new HashMap<>();
		int length = getLongestSubsequence(A, B, i, j, cache, subSequences);
		debugln("length = " + length);
		return subSequences.get(new Pair(i, j));
	}

	// Recursion & Memoization (but not DP)
	private static int getLongestSubsequence(int[] A, int[] B, int i, int j, int[][] cache,
			Map<Pair, List<Integer>> subSequences) {
		// base case
		if (i < 0 || j < 0) {
			subSequences.put(new Pair(i, j), new ArrayList<>());
			return 0;
		}
		// general case
		if (cache[i][j] != -1) {
			return cache[i][j];
		}

		int longestSubSequence = 0;
		if (A[i] == B[j]) {
			longestSubSequence = getLongestSubsequence(A, B, i - 1, j - 1, cache, subSequences) + 1;
			List<Integer> listInts = subSequences.get(new Pair(i - 1, j - 1));
			List<Integer> newList = new ArrayList<>(); // DO NOT MODIFY ORIGINAL LIST
																									// (duh)
			if (listInts != null) {
				newList.addAll(listInts);
			}
			newList.add(A[i]);
			// debugln("longestSubSequence = " + longestSubSequence + " , adding " +
			// A[i] + " , step i=" + i + ",j=" + j);
			subSequences.put(new Pair(i, j), newList);
		} else {
			longestSubSequence = Math.max(getLongestSubsequence(A, B, i - 1, j, cache, subSequences), //
					getLongestSubsequence(A, B, i, j - 1, cache, subSequences));
			List<Integer> listInts1 = subSequences.get(new Pair(i - 1, j));
			List<Integer> listInts2 = subSequences.get(new Pair(i, j - 1));
			if (listInts1.size() > listInts2.size()) {
				subSequences.put(new Pair(i, j), listInts1);
			} else {
				subSequences.put(new Pair(i, j), listInts2);
			}

		}
		cache[i][j] = longestSubSequence;
		return longestSubSequence;
	}

	static class Pair {
		int i;
		int j;

		Pair(int i, int j) {
			this.i = i;
			this.j = j;
		}

		@Override
		public boolean equals(Object o) {
			if (!(o instanceof Pair))
				return false;
			Pair other = (Pair) o;
			if (other.i == i && other.j == j)
				return true;
			return false;
		}

		@Override
		public int hashCode() {
			int result = 17;
			result = result * 31 + i;
			result = result * 31 + j;
			return result;
		}
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
