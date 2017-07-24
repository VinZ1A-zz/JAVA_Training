package hackerRank;
import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.Vector;

//rank 160933 - Points 156.00
public class Sol_algo_Graph_JourneyMoon {

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

		// expects 4
		// size, nbOfPairs
		// aInput.add("4 2");
		// aInput.add("0 1");
		// aInput.add("2 3");

		// got 26, OK
		// aInput.add("9 0");
		// aInput.add("2 1");
		// aInput.add("5 6");
		// aInput.add("8 0");
		// aInput.add("4 5");
		// aInput.add("6 7");
		// aInput.add("2 3");

		// expects 23
		// aInput.add("10 7");
		// aInput.add("0 2");
		// aInput.add("1 8");
		// aInput.add("1 4");
		// aInput.add("2 8");
		// aInput.add("2 6");
		// aInput.add("3 5");
		// aInput.add("6 9");

		// aInput.add("100 -1");
		// aInput.add("1 2");
		// aInput.add("3 4");
		// aInput.add("3 2");
		// aInput.add("4 5");
		// aInput.add("4 6");
		// aInput.add("4 2");

		// expects 4999949998
		aInput.add("100000 2");
		aInput.add("1 2");
		aInput.add("3 4");

		// expects 4999949997
		// aInput.add("100000 3");
		// aInput.add("1 2");
		// aInput.add("3 4");
		// aInput.add("5 6");

		// 4999949997
		// aInput.add("100000 2");
		// aInput.add("1 2");
		// aInput.add("3 2");

		return aInput;
	}

	// find & set parent in graph !!! TO REMEMBER !!!
	private static final int find(int[] parent, int i) {
		int p = i;
		while (parent[p] != p) {
			p = parent[p];
		}
		return (parent[i] = p);
	}

	private static void doIt() {
		Scanner scan = new Scanner(System.in);

		int size = scan.nextInt(); // from 0 to n-1 !
		scan.nextInt(); // rien a fout
		int[] parent = new int[size];
		// init
		for (int i = 0; i < size; i++) {
			parent[i] = i; // parent is his own
		}

		// determine root parent of each node
		Set<Integer> intInLink = new HashSet<>();
		while (scan.hasNext()) {
			int pairA = scan.nextInt();
			int pairB = scan.nextInt();
			int parA = find(parent, pairA), parB = find(parent, pairB);
			// debug(pairA + "(" + parA + ") / " + pairB + "(" + parB + ")");
			if (parA < parB) {
				// debug("(" + pairA + "/" + pairB + ") : set parent of " + parB
				// + " to " + parA);
				parent[parB] = parA;
			} else if (parB < parA) {
				// debug("(" + pairA + "/" + pairB + ") : set parent of " + parA
				// + " to " + parB);
				parent[parA] = parB;
			}
			intInLink.add(pairA);
			intInLink.add(pairB);
		}

		// determine nb of group and its individual size
		Map<Integer, Integer> groupsToSize = new HashMap<>();
		// simplify calculus with single-formed groups
		int nbOfSingletons = size - intInLink.size();
		for (int i = 0; i < size; i++) {
			if (!intInLink.contains(i)) {
				continue;
			}
			int finalParent = find(parent, i);
			// debug("parent of " + i + " is " + finalParent);
			int curSize = groupsToSize.get(finalParent) == null ? 0 : groupsToSize.get(finalParent);
			groupsToSize.put(finalParent, ++curSize);
		}

		debug("nb of singletons " + nbOfSingletons);

		// determine nb of permutations except singletons
		long nbPermuts = 0;
		for (Map.Entry<Integer, Integer> groupToSizeA : groupsToSize.entrySet()) {
			// debug("group id " + groupToSizeA.getKey() + " is size " +
			// groupToSizeA.getValue());
			for (Map.Entry<Integer, Integer> groupToSizeB : groupsToSize.entrySet()) {
				if (groupToSizeB.getKey() > groupToSizeA.getKey()) {
					// debug("adding Grp" + groupToSizeB.getKey() + "(" +
					// groupToSizeB.getValue() + ") * Grp"
					// + groupToSizeA.getKey() + "(" + groupToSizeA.getValue() +
					// ")");
					nbPermuts = nbPermuts + groupToSizeB.getValue() * groupToSizeA.getValue();
				}
			}
		}

		// add singletons permuts with each other (famous n*(n+1) / 2)
		nbPermuts = nbPermuts + ((long) nbOfSingletons * (nbOfSingletons - 1)) / 2;
		// and nb combinations with non-singletons
		nbPermuts = nbPermuts + (long) nbOfSingletons * (size - nbOfSingletons);

		System.out.println(nbPermuts);

		scan.close();

	}

	private static void debug(String iStr) {
		if (_debug) {
			System.err.println(iStr);
		}
	}
}
