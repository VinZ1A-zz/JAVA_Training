package hackerRank;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

// Points: 1046.00 Rank: 21918
public class Sol_algo_Impl_AlmostSorted {

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

		// expects yes then swap 1 2
		// aInput.add("2"); // do not read
		// aInput.add("1 2 4 3 5");

		// aInput.add("2"); // do not read
		// aInput.add("1 4 3 2 5 6");

		// yes reverse 2 5
		// aInput.add("6");
		// aInput.add("1 5 4 3 2 6");

		// yes then reverse 2 5
		// aInput.add("6");
		// aInput.add("1 5 4 3 2 6");

		// aInput.add("3");
		// aInput.add("3 1 2");

		// yes then swap 12 95
		aInput.add("100");
		aInput.add("4104 8529 49984 54956 63034 82534 84473 86411 92941 95929 108831 894947 "
				+ "125082 137123 137276 142534 149840 154703 174744 180537 207563 221088 "
				+ "223069 231982 249517 252211 255192 260283 261543 262406 270616 274600 "
				+ "274709 283838 289532 295589 310856 314991 322201 339198 343271 383392 "
				+ "385869 389367 403468 441925 444543 454300 455366 469896 478627 479055 "
				+ "484516 499114 512738 543943 552836 560153 578730 579688 591631 594436 "
				+ "606033 613146 621500 627475 631582 643754 658309 666435 667186 671190 "
				+ "674741 685292 702340 705383 722375 722776 726812 748441 790023 795574 "
				+ "797416 813164 813248 827778 839998 843708 851728 857147 860454 861956 "
				+ "864994 868755 116375 911042 912634 914500 920825 979477");

		return aInput;
	}

	private static void doIt() {
		someImpl();
	}

	// assuming distincts
	private static void someImpl() {
		Scanner scan = new Scanner(System.in);

		scan.nextInt(); // rien a fout

		List<Integer> nums = new ArrayList<>();
		while (scan.hasNext()) {
			nums.add(scan.nextInt());
		}

		List<Integer> cpy = new ArrayList<>(nums);
		Collections.sort(cpy);

		// for (int i : nums) {
		// debug(i);
		// }
		// debug("cpy:");
		// for (int i : cpy) {
		// debug(i);
		// }

		int swpFrom = -1;
		int stopAt = -1;
		for (int i = 0; i < nums.size(); i++) {
			if (cpy.get(i) != nums.get(i)) {
				swpFrom = i;
				stopAt = cpy.get(i);
				break;
			}
		}

		debug("swap from idx " + swpFrom);

		if (swpFrom == -1) { // already sorted
			System.out.println("yes");
			System.exit(0);
		}

		debug("looking for " + stopAt);
		int swpTo = -1;
		boolean canBeRev = true;
		for (int j = swpFrom + 1; j < nums.size(); j++) { // can't be last one
			if (nums.get(j - 1) < nums.get(j)) // can't be swapped
			{
				debug("found unordered nbs : " + nums.get(j - 1) + " < " + nums.get(j) + " at idx " + j);
				canBeRev = false; // keep looking (case of swap)
			}
			if (nums.get(j) == stopAt) {
				swpTo = j;
				break;
			}
		}

		if (swpTo == -1) {
			System.out.println("no");
		} else {
			boolean isRemSorted = true;
			if (!canBeRev) { // could be just a swap
				debug("swapping with idx " + swpTo);
				int temp = cpy.get(swpTo);
				cpy.set(swpTo, cpy.get(swpFrom));
				cpy.set(swpFrom, temp);
				// check that everything between swpFrom and swpTo is ordered
				for (int i = swpFrom + 1; i < swpTo + 1; i++) {
					if (cpy.get(i) != nums.get(i)) {
						isRemSorted = false;
						debug("not sorted : cpy(" + i + ") = " + cpy.get(i) + " ; nums(" + i + ") = " + nums.get(i));
						break;
					}
				}
			}

			// check that remaining is sorted
			for (int i = swpTo + 1; i < nums.size(); i++) {
				if (cpy.get(i) != nums.get(i)) {
					isRemSorted = false;
					debug("not sorted : cpy(" + i + ") = " + cpy.get(i) + " ; nums(" + i + ") = " + nums.get(i));
					break;
				}
			}

			if (!isRemSorted) {
				System.out.println("no");
			} else {
				System.out.println("yes");
				if (swpTo - swpFrom == 1) { // continuguous rev: is just a swap
					System.out.println("swap " + (swpFrom + 1) + " " + (swpTo + 1));
				} else { // a reverse
					System.out.println((canBeRev ? "reverse" : "swap") + " " + (swpFrom + 1) + " " + (swpTo + 1));
				}
			}
		}

	}

	private static void debug(Object obj) {
		if (_debug) {
			System.err.println(obj.toString());
		}
	}
}
