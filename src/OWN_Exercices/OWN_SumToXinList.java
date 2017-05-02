package OWN_Exercices;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

public class OWN_SumToXinList {

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

		// aInput.add("1 7 -2 3 4 0 8 2 5 6");
		aInput.add("2 3 4 ");

		return aInput;
	}

	private static void doIt() {
		Scanner scan = new Scanner(System.in);

		List<Integer> array = new ArrayList<Integer>();
		do {
			int n = scan.nextInt();
			array.add(n);
			// debug("reading " + n);
		} while (scan.hasNext());

		List<ArrayList<Integer>> subSets = new ArrayList<>();
		subSets = getListOfSubNums(array, subSets);

		for (ArrayList<Integer> list : subSets) {
			debug("list : ");
			for (int i : list) {
				debug(i + " ");
			}
			debugln("");
		}

		scan.close();

	}

	private static List<ArrayList<Integer>> getListOfSubNums(List<Integer> array, List<ArrayList<Integer>> subSets) {
		return getListOfSubNums(array, 0);
	}

	private static List<ArrayList<Integer>> getListOfSubNums(List<Integer> array, int idx) {
		// end cases
		if (idx == array.size()) {
			// add empty set
			List<ArrayList<Integer>> subSets = new ArrayList<>();
			subSets.add(new ArrayList<Integer>());
			return subSets;
		}

		int first = array.get(idx);
		List<ArrayList<Integer>> subSets = getListOfSubNums(array, idx + 1);
		List<ArrayList<Integer>> moreSubSets = new ArrayList<>();
		for (ArrayList<Integer> list : subSets) {
			// for all returned subsets
			// we add first to the subset while keeping other subsets
			// as we either have first or we don't
			ArrayList<Integer> newList = new ArrayList<>(list);
			newList.add(first);
			moreSubSets.add(newList);
		}
		subSets.addAll(moreSubSets);

		return subSets;

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
