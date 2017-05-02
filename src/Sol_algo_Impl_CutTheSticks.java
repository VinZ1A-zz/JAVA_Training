import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.Vector;

public class Sol_algo_Impl_CutTheSticks {

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

		// aInput.add("4 3"); // 4 ints, div by 3?
		// aInput.add("1 7 2 4"); // expects 3 (1,7,4)

		// expects 5
		// aInput.add("6 9");
		// aInput.add("422346306 940894801 696810740 862741861 85835055 313720373");

		// expects 11
		aInput.add("13 11");
		aInput.add(
				"582740017 954896345 590538156 298333230 859747706 155195851 331503493 799588305 164222042 563356693 80522822 432354938 652248359");

		return aInput;
	}

	private static void doIt() {
		Scanner scan = new Scanner(System.in);

		// bruteForce();
		betterImpl();

	}

	private static void betterImpl() {
		Scanner scan = new Scanner(System.in);

		scan.nextInt(); // rien a fout
		int k = scan.nextInt(); // divider
		Map<Integer, ArrayList<Integer>> remainderToNums = new HashMap<Integer, ArrayList<Integer>>();
		while (scan.hasNext()) {
			int num = scan.nextInt();
			int remainder = num % k;
			ArrayList<Integer> val = remainderToNums.get(remainder);
			if (val == null) {
				ArrayList<Integer> listNums = new ArrayList<Integer>();
				listNums.add(num);
				remainderToNums.put(remainder, listNums);
			} else {
				val.add(num);
			}

		}
		scan.close();

		// ************ DEBUG ONLY
		for (Entry<Integer, ArrayList<Integer>> nums : remainderToNums.entrySet()) {
			debug(nums.getKey() + " : ");
			for (Integer a : nums.getValue()) {
				debug(a + ",");
			}
			debug("");
		}
		debug("---------- DONE -----------");

		int total = 0;
		List<Integer> alreadyProcessedCompl = new ArrayList<>();
		for (Entry<Integer, ArrayList<Integer>> nums : remainderToNums.entrySet()) {
			if (alreadyProcessedCompl.contains(nums.getKey())) {
				continue;
			}
			debug(nums.getKey() + " : ");
			// size against size of complementary integer
			int compl = k - nums.getKey();
			alreadyProcessedCompl.add(compl);
			debug("compl=" + compl);
			if (nums.getKey() == 0 || compl == nums.getKey()) {
				// we can only have one num
				total++;
				debug("adding 1");
			} else {
				ArrayList<Integer> complSet = remainderToNums.get(compl);
				if (complSet != null) { // comp numbers exist
					// take largest set
					total += Math.max(complSet.size(), nums.getValue().size());
					debug("adding max of " + complSet.size() + " and " + nums.getValue().size());
				} else { // no counter-part, ok to board
					total += nums.getValue().size();
					debug("adding " + nums.getValue().size());
				}
			}

			for (Integer a : nums.getValue()) {
				debug(a + ",");
			}
			debug("");
		}

		System.out.println(total);

	}

	private static void bruteForce() {
		Scanner scan = new Scanner(System.in);

		scan.nextInt(); // rien a fout
		int k = scan.nextInt(); // divider
		List<Integer> nums = new ArrayList<>();
		while (scan.hasNext()) {
			nums.add(scan.nextInt());
		}
		scan.close();

		// list all pairs
		Set<Integer> selected = new HashSet<>();
		int sum = 0;
		// a bit brute-force. (n2 ..)
		for (int i = 0; i < nums.size() - 1; i++) {
			for (int j = i + 1; j < nums.size(); j++) {

				debug("pair " + nums.get(i) + "," + nums.get(j));
				sum = nums.get(i) + nums.get(j);
				// if pair not divisible by k, keep numbers
				if (sum % k > 0) {
					selected.add(nums.get(i));
					selected.add(nums.get(j));
				}
			}
		}

		for (int a : selected) {
			debug(a);
		}
		System.out.println(selected.size());
	}

	private static void debug(Object obj) {
		if (_debug) {
			System.err.println(obj.toString());
		}
	}
}
