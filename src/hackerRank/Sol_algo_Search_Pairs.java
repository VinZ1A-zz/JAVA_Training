package hackerRank;
import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Vector;

//was 202282 with 106 pts
public class Sol_algo_Search_Pairs {

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

		aInput.add("5 2");
		aInput.add("1 5 3 4 2");

		return aInput;
	}

	private static void doIt() {
		Scanner in = new Scanner(System.in);

		int size = in.nextInt(); // size of array
		int pair = in.nextInt(); // pair to look for
		int[] a = new int[size];
		Map<Integer, Integer> valForIndex = new HashMap<>();
		for (int i = 0; i < size; i++) {
			a[i] = in.nextInt();
			valForIndex.put(a[i], i);
		}
		int nbOfPairs = 0;
		for (int i = 0; i < size; i++) {
			int toLookFor = a[i] - pair;
			debug("got " + a[i] + " , looking for " + toLookFor);
			if (valForIndex.containsKey(toLookFor) && valForIndex.get(toLookFor) != i) {
				nbOfPairs++;
			}
		}

		System.out.println(nbOfPairs);

		in.close();

	}

	private static void debug(String iStr) {
		if (_debug) {
			System.err.println(iStr);
		}
	}
}
