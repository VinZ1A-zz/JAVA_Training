package hackerRank;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Vector;

public class Sol_algo_Recurs_PowerSum {

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

		// expects 1 (1e2 + 3e2)
		aInput.add("100");
		aInput.add("2");

		return aInput;
	}

	private static void doIt() {
		Scanner scan = new Scanner(System.in);

		double num = scan.nextDouble();
		int pow = scan.nextInt();

		int bound = (int) Math.ceil(root(num, pow));
		debugln("bound : " + bound);

		// init values used to get combinations
		double[] possibleValues = new double[bound];
		for (int i = 1; i <= bound; i++) {
			possibleValues[i - 1] = Math.pow(i, pow);
		}

		// usual way of getting combinations
		List<List<Double>> sums = getPoss(num, possibleValues, 0);

		int nbOfWays = 0;
		for (List<Double> sum : sums) {
			// debugln(sum);
			double total = 0;
			for (double k : sum) {
				total += k;
			}
			if (total == num)
				nbOfWays++;
		}

		// debugln("nbOfWays : " + nbOfWays);
		println(nbOfWays);

		scan.close();

	}

	private static List<List<Double>> getPoss(double num, double[] vals, int idx) {
		Map<Integer, List<List<Double>>> cache = new HashMap<>();
		return getPoss(num, vals, idx, cache);
	}

	private static List<List<Double>> getPoss(double num, double[] vals, int idx,
			Map<Integer, List<List<Double>>> cache) {
		// base case
		if (idx == vals.length) {
			List<List<Double>> ret = new ArrayList<>();
			ret.add(new ArrayList<>());
			return ret;
		}

		if (cache.containsKey(idx)) {
			return cache.get(idx);
		}

		// general case
		// add the same list of Doubles with the current val @idx
		// optimize : don't add in the list of vals if sum > num
		List<List<Double>> curPos = getPoss(num, vals, idx + 1);
		List<List<Double>> newPosList = new ArrayList<>();
		for (List<Double> pos : curPos) {
			double sum = 0;
			for (double k : pos) {
				sum += k;
			}
			if (sum > num)
				continue;
			// with #idx val
			List<Double> newPos = new ArrayList<>();
			newPos.addAll(pos);
			newPos.add(vals[idx]);
			newPosList.add(newPos);
		}
		newPosList.addAll(curPos);

		cache.put(idx, newPosList);
		return newPosList;

	}

	public static double root(double num, double root) {
		return Math.pow(Math.E, Math.log(num) / root);
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
