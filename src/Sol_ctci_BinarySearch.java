import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Vector;

public class Sol_ctci_BinarySearch {

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

		aInput.add("2"); // t queries: 1 to 50
		aInput.add("4"); // m money: 2 to 10e4
		aInput.add("5"); // n nb of flavors: 2 to 10 e5
		aInput.add("1 4 5 3 2"); // flavors price -- expect 1 4
		aInput.add("4");
		aInput.add("4");
		aInput.add("2 2 4 3"); // expected : 1 2

		// aInput.add("1");

		// aInput.add("100");
		// aInput.add("3");
		// aInput.add("5 75 25");

		// aInput.add("200");
		// aInput.add("7");
		// aInput.add("150 24 79 50 88 345 3");

		return aInput;
	}

	public static int binarySearch(int first, int last, IceCream[] arr, int search) {
		if (first <= last) {
			int mid = (int) (first + last) / 2;
			if (arr[mid].equals(search))
				return arr[mid].index;
			if (arr[mid].compareInt(search) > 0) {
				return binarySearch(first, mid - 1, arr, search);
			} else
				return binarySearch(mid + 1, last, arr, search);
		} else if (first == last && arr[first].equals(search))
			return arr[first].index;
		else
			return -1;

	}

	private static void doIt() {
		Scanner scan = new Scanner(System.in);

		int t = scan.nextInt(); // nb of queries
		for (int t0 = 0; t0 < t; t0++) {
			int money = scan.nextInt();
			int nbFlav = scan.nextInt();
			int[] flav = new int[nbFlav];
			for (int i = 0; i < nbFlav; i++) {
				flav[i] = scan.nextInt();
			}

			// do a binary search for each "complement" of each array item.
			// Arrays.sort(flav);
			// .... use binarySearch

			// OR use a hash table eg.
			int[] result = null;
			Map<Integer, Integer> map = new HashMap<>();
			for (int i = 0; i < flav.length; i++) {
				map.put(flav[i], i);
			}
			for (int i = 0; i < flav.length; i++) {
				int complement = money - flav[i];
				if (map.containsKey(complement) && map.get(complement) != i) {
					result = new int[] { i + 1, map.get(complement) + 1 };
				}
			}
			// throw new IllegalArgumentException("No two sum solution");

			// display result ... ( + 1)
			Arrays.sort(result);
			// System.err.println(Arrays.toString(result));
			System.out.println(result[0] + " " + result[1]);
		}

		scan.close();

	}
}

class IceCream implements Comparable<IceCream> {
	int flavor;
	int index;

	public IceCream(int flavor, int index) {
		this.flavor = flavor;
		this.index = index;
	}

	public int compareInt(int o) {
		return (int) (this.flavor - o);

	}

	@Override
	public int compareTo(IceCream o) {
		return (int) (this.flavor - o.flavor);

	}

	@Override
	public boolean equals(Object o) {
		return (this.flavor == (int) o ? true : false);

	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

}

// ************* BAD idea **********************
// // create array of pair of costs eg 1 2 3 4 5 --> 3 5 7 9
// int minIndexCost = -1;
// int maxIndexCost = -1;
// int[] pairOfCosts = new int[nbFlav - 1];
// for (int i = 1; i < nbFlav; i++) {
//
// int cost = flav[i] + flav[i - 1];
// pairOfCosts[i - 1] = cost;
// if (cost <= money) {
// minIndexCost = i - 1;
// }
// if (cost >= money && maxIndexCost == -1) {
// maxIndexCost = i;
// }
// }
//
// System.err.println(Arrays.toString(flav));
// System.err.println("minIndexCost : " + minIndexCost);
// System.err.println("maxIndexCost : " + maxIndexCost);
//
// int foundI = -1, foundJ = -1;
// for (int i = minIndexCost; i <= maxIndexCost - 1; i++) {
// for (int j = i + 1; j <= maxIndexCost; j++) {
// if (flav[i] + flav[j] == money) {
// foundI = flav[i];
// foundJ = flav[j];
// break;
// }
// }
// if (foundI >= 0)
// break;
// }
// System.err.println("foundI : " + foundI);
// System.err.println("foundJ : " + foundJ);
//
// int indexI = -1, indexJ = -1;
// for (int i = 0; i < nbFlav; i++) {
// if (indexI == -1 && origFlav[i] == foundI) {
// indexI = i;
// } else if (indexJ == -1 && origFlav[i] == foundJ) {
// indexJ = i;
// }
// if (indexI > 0 && indexJ > 0)
// break;
// }
// System.err.println("indexI : " + indexI);
// System.err.println("indexJ : " + indexJ);
// if (indexI < indexJ) {
// System.out.println(indexI + 1 + " " + (indexJ + 1));
// } else {
// System.out.println(indexJ + 1 + " " + (indexI + 1));
// }
//
// }
