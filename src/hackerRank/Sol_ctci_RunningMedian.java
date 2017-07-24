package hackerRank;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

public class Sol_ctci_RunningMedian {

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

		aInput.add("6");
		aInput.add("12");
		aInput.add("4");
		aInput.add("5");
		aInput.add("3");
		aInput.add("8");
		aInput.add("7");

		return aInput;
	}

	private static void doIt() {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		MinIntHeap<Integer> left = new MinIntHeap<Integer>(false);
		MinIntHeap<Integer> right = new MinIntHeap<Integer>();
		int curInt;
		int nbOfInts = 0;
		int maxLeft = 0;
		int minRight = 0;
		double curMed = 0;
		for (int a_i = 0; a_i < n; a_i++) {
			nbOfInts++;
			curInt = in.nextInt();

			if (nbOfInts == 1) {
				left.add(curInt);
				curMed = curInt;
			} else if (nbOfInts == 2) {
				// add in appropriate heap
				if (curInt > left.peek())
					right.add(curInt);
				else
					left.add(curInt);
			} else {
				maxLeft = left.peek();
				minRight = right.peek();
				// add new nb in appropriate list
				// lower than max(left) or greater than min(right)
				if (curInt < maxLeft) {
					left.add(curInt);
				} else {
					right.add(curInt);
				}
			}
			// level up lists so there is no more than one elem diff
			// eg. pop out max(left) into right or min(right) into left
			if (left.getSize() - right.getSize() >= 2) {
				right.add(left.poll());
			} else if (right.getSize() - left.getSize() >= 2) {
				left.add(right.poll());
			}

			// if same nb of elems: avg max(left) / min(right)
			if (left.getSize() == right.getSize()) {
				curMed = ((double) (left.peek() + right.peek())) / 2;
			} else {// else: from which ever list has the highest nb of
				// elems
				if (left.getSize() > right.getSize()) {
					curMed = left.peek();
				} else {
					curMed = right.peek();
				}
			}

			System.out.format("%.1f\n", curMed);

		}

		in.close();

		// heap test
		// MinIntHeap<Integer> zeHeap = new MinIntHeap<Integer>();
		// zeHeap.add(2);
		// zeHeap.add(4);
		// zeHeap.add(3);
		// System.out.println("peek " + zeHeap.poll());
		// System.out.println("peek " + zeHeap.poll());
		// System.out.println("peek " + zeHeap.poll());
		// System.out.println("peek " + zeHeap.peek());
	}

	static void slowAndDumb() {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		// Integer[] a = new Integer[n];
		List<Integer> a = new ArrayList<Integer>();
		int curInt;
		int nbOfInts = 0;
		double curMed = 0;
		for (int a_i = 0; a_i < n; a_i++) {
			nbOfInts++;
			curInt = in.nextInt();
			// a[a_i] = curInt;
			a.add(curInt);
			Collections.sort(a); // in n log n hopefully
			if (_debug)
				System.out.println(a);

			if (nbOfInts == 1) {
				curMed = curInt;
			} else if (nbOfInts == 2) {
				curMed = (curMed + curInt) / 2;
			} else {
				if (nbOfInts % 2 == 0) {
					curMed = (double) (a.get((nbOfInts / 2) - 1) + a.get(nbOfInts / 2)) / 2;
				} else {
					curMed = a.get(nbOfInts / 2);
				}
			}

			System.out.format("%.1f\n", curMed);

		}

		in.close();
	}
}
