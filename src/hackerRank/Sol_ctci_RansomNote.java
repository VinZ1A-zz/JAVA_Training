package hackerRank;
import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Vector;

public class Sol_ctci_RansomNote {

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

		aInput.add("6 4");
		aInput.add("give me one grand today night");
		aInput.add("give one grand today");

		return aInput;
	}

	private static void doIt() {
		Scanner in = new Scanner(System.in);
		int sizeMag = in.nextInt();
		int sizeRan = in.nextInt();
		String mag[] = new String[sizeMag];
		HashMap<String, Integer> wordsCount = new HashMap<String, Integer>();
		for (int i = 0; i < sizeMag; i++) {
			// mag[i] = in.next();
			String aWord = in.next();
			if (!wordsCount.containsKey(aWord)) {
				wordsCount.put(aWord, 1);
			} else {
				wordsCount.put(aWord, new Integer(wordsCount.get(aWord).intValue() + 1));
			}
		}
		String ran[] = new String[sizeRan];
		String answer = "Yes";
		for (int i = 0; i < sizeRan; i++) {
			// ran[i] = in.next();
			String aWord = in.next();
			if (!wordsCount.containsKey(aWord)) {
				answer = "No";
				break;
			} else {
				int curCount = wordsCount.get(aWord).intValue();
				if (curCount == 0) {
					answer = "No";
					break;
				}
				wordsCount.put(aWord, new Integer(curCount - 1));
			}
		}
		System.out.println(answer);
	}
}
