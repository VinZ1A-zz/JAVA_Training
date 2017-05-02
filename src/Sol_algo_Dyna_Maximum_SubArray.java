import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Vector;

public class Sol_algo_Dyna_Maximum_SubArray {

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

		aInput.add("1 ");
		// aInput.add("4 ");
		// aInput.add("1 2 3 4");
		aInput.add("8");
		aInput.add("-13 2 -1 2 3 4 -5 -4");
		// aInput.add("4");
		// aInput.add("-3 -4 -1 -2");

		return aInput;
	}

	private static void doIt() {
		Scanner scan = new Scanner(System.in);

		// Note: debugln(Integer.MAX_VALUE); //2147483647 which is < 10exp4 * 10exp5
		// int should be enough for maxSum

		int nbTestCases = scan.nextInt();
		for (int testCase = 0; testCase < nbTestCases; testCase++) {
			int n = scan.nextInt(); // nb of ints
			List<PosNeg> chunks = new ArrayList<>();
			boolean posEncountered = false;
			int cumulPosVal = 0;
			int cumulNegVal = 0;
			int maximumNegVal = Integer.MIN_VALUE;
			for (int i = 0; i < n; i++) {
				int val = scan.nextInt();
				if (val > 0) {
					posEncountered = true;
				}
				if (posEncountered) {
					// when going from neg to pos, create new pair(pos/neg)
					if (cumulNegVal < 0 && val > 0) {
						// debugln("new start " + val);
						chunks.add(new PosNeg(cumulPosVal, cumulPosVal + cumulNegVal));
						cumulNegVal = 0;
						cumulPosVal = 0;
					}
					if (val > 0) {
						cumulPosVal += val;
					}
					if (val < 0) {
						cumulNegVal += val;
					}

				} else { // in case no pos value shows up
					// keep track of highest (negative) val
					if (val > maximumNegVal) {
						maximumNegVal = val;
					}
				}
			}
			chunks.add(new PosNeg(cumulPosVal, 0));

			debugln(chunks);
			// special case: only negative values
			if (chunks.size() == 1 && !posEncountered) {
				println(maximumNegVal + " " + maximumNegVal);
				continue;
			}

			// try with all possible beginnings
			int maxSumDisc = 0;
			int maxSumCont = 0;
			int curSumCont = 0;
			for (int idxBeg = 0; idxBeg < chunks.size(); idxBeg++) {
				maxSumDisc += chunks.get(idxBeg).pos;

				curSumCont += chunks.get(idxBeg).pos;
				if (maxSumCont < curSumCont) {
					maxSumCont = curSumCont;
				}
				// negative part of the chunk (pos - neg - pos)
				curSumCont += chunks.get(idxBeg).diffPosNeg - chunks.get(idxBeg).pos;
				if (curSumCont < 0) { // reset when big negative val found
					curSumCont = 0;
				}

				// attempt #1 - works but SLOW !...
				// int sum = getSum(chunks, idxBeg).pos;
				// if (sum > maxSumCont)
				// maxSumCont = sum;
			}

			println(maxSumCont + " " + maxSumDisc);
		}

		scan.close();

	}

	static PosNeg getSum(List<PosNeg> chunks, int idxBeg) {
		Map<Integer, PosNeg> cache = new HashMap<>();
		return getSum(chunks, idxBeg, cache);
	}

	static PosNeg getSum(List<PosNeg> chunks, int idxBeg, Map<Integer, PosNeg> cache) {
		// base case (should not happen anyway?)
		if (idxBeg >= chunks.size())
			return new PosNeg(0, 0);

		if (cache.containsKey(idxBeg)) {
			return cache.get(idxBeg);
		}

		// general case
		PosNeg beg = chunks.get(idxBeg);
		PosNeg maxSum = new PosNeg(0, 0);
		for (int i = idxBeg + 1; i < chunks.size(); i++) {
			// if (chunks.get(i).neg < 0) { //NO.
			// break;
			// }
			PosNeg newSum = getSum(chunks, i);
			if (newSum.pos > maxSum.pos)
				maxSum.pos = newSum.pos;
			maxSum.diffPosNeg = newSum.diffPosNeg;
		}

		PosNeg ret = new PosNeg(Math.max(beg.pos, beg.diffPosNeg + maxSum.pos), beg.diffPosNeg + maxSum.diffPosNeg);
		cache.put(idxBeg, ret);

		return ret;

	}

	static class PosNeg {
		int pos;
		int diffPosNeg;

		PosNeg(int pos, int diffPosNeg) {
			this.pos = pos;
			this.diffPosNeg = diffPosNeg;
		}

		@Override
		public String toString() {
			return pos + "/" + diffPosNeg;
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
