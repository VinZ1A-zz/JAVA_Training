package hackerRank;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

// got up to Points: 741.00 Rank: 35791

public class Sol_algo_Impl_ACM_ICPC_team {

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

		// 5 then 2
		aInput.add("4 5");
		aInput.add("10101");
		aInput.add("11100");
		aInput.add("11010");
		aInput.add("00101");

		// aInput.add("4 48");
		// aInput.add("101011010110101101011110110101101011010101001101");
		// aInput.add("101011010110101101011010110101101011010101001101");
		// aInput.add("101011010110001101011010110101101011010101001101");
		// aInput.add("101011000110101101011010110101101010010101001101");

		return aInput;
	}

	private static void doIt() {
		Scanner scan = new Scanner(System.in);

		bruteForce();

	}

	private static void bruteForce() {
		Scanner scan = new Scanner(System.in);

		int n = scan.nextInt(); // nb people
		int m = scan.nextInt(); // nb topics

		List<ArrayList<Integer>> topics = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < n; i++) {
			String topicsPerPerson = scan.next();
			// debug(topicsPerPerson);
			ArrayList<Integer> chunks = new ArrayList<Integer>();

			int idx = 0, prevIdx = 0;
			do {
				prevIdx = idx;
				idx = Math.min(idx + 20, m);
				String subStr = topicsPerPerson.substring(prevIdx, idx);
				// debug("adding " + subStr);
				chunks.add(Integer.parseInt(subStr, 2));
			} while (idx < m);
			topics.add(chunks);
		}

		// ************ DEBUG **************
		// for (ArrayList<Integer> chunks : topics) {
		// for (int a : chunks) {
		// System.out.print(a + " - ");
		// }
		// System.out.println();
		// }

		int maxNbOfTopics = -1;
		int nbOfPairsSharingMax = 0;

		for (int i = 0; i < n - 1; i++) {
			for (int j = i + 1; j < n; j++) {
				int nbOfTopicsShared = 0;
				// gather chunks together
				for (int k = 0; k < topics.get(i).size(); k++) {

					nbOfTopicsShared += nbOfOnes((topics.get(i).get(k) | topics.get(j).get(k)));

					// debug("pair " + topics.get(i).get(k) + " / " +
					// topics.get(j).get(k)//
					// + " --> " + (topics.get(i).get(k) | topics.get(j).get(k)) //
					// + "(" + nbOfTopicsShared + ")");

				}
				if (maxNbOfTopics < nbOfTopicsShared) { // new maxSharedTopics found
					maxNbOfTopics = nbOfTopicsShared;
					nbOfPairsSharingMax = 0;
				}

				// counting nb of pairs when maxSharedTopics encountered
				if (nbOfTopicsShared == maxNbOfTopics) {
					nbOfPairsSharingMax++;
				}

				// debug("pair " + topics.get(i) + " / " + topics.get(j)//
				// + " --> " + (topics.get(i) | topics.get(j)) //
				// + "(" + nbOfTopicsShared + ")");
			}
		}

		// printBinaryform(Integer.MAX_VALUE); // 1111111111111111111111111111111

		System.out.println(maxNbOfTopics);
		System.out.println(nbOfPairsSharingMax);

	}

	static int nbOfOnes(int a) {
		int orig = a;
		int count = 0;
		while (a > 0) {
			a = a >> 1 << 1;
			if (orig - a == 1)
				count++;
			orig = a >> 1;
			a = orig;
		}

		return count;
	}

	private static void printBinaryform(int number) {
		int remainder;

		if (number <= 1) {
			System.out.print(number);
			return; // KICK OUT OF THE RECURSION
		}

		remainder = number % 2;
		printBinaryform(number >> 1);
		System.out.print(remainder);
	}

	private static void debug(Object obj) {
		if (_debug) {
			System.err.println(obj.toString());
		}
	}
}
