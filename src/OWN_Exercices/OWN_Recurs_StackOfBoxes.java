package OWN_Exercices;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

public class OWN_Recurs_StackOfBoxes {

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

		// The maximum possible height of stack is 60
		// { {3, 1, 2}, {1, 2, 3}, {6, 4, 5}, {4, 5, 6}, {4, 6, 7}, {32, 10, 12},
		// {10, 12, 32}}
		// H * W D
		aInput.add("32  10  12");// X should be re-sorted after (cur) pos 2
		aInput.add("10  12  32");// X
		aInput.add("12  10  32");
		aInput.add("4  6  7");// X
		aInput.add("4  5  6");// X
		aInput.add("6  4  7");
		aInput.add("5  4  6");
		aInput.add("7  4  6");
		aInput.add("6  4  5");// X
		aInput.add("1  2  3");// X
		aInput.add("2  1  3");
		aInput.add("3  1  2");// X

		return aInput;
	}

	private static void doIt() {
		Scanner scan = new Scanner(System.in);

		List<Box> boxes = new ArrayList<>();
		while (scan.hasNext()) {
			boxes.add(new Box(scan.nextInt(), scan.nextInt(), scan.nextInt()));
		}

		scan.close();

		// sort
		Collections.sort(boxes);

		display(boxes);
		List<List<Box>> possibleStacks = new ArrayList<>();

		// use each box as bottom candidate
		int maxHeight = 0;
		for (int i = 0; i < boxes.size(); i++) {
			int height = getHeight(boxes, i);
			if (height > maxHeight)
				maxHeight = height;
		}

		debugln("max height : " + maxHeight);

		// getPossibleStacks(boxes, 0, possibleStacks);

		// int bestHeight = 0; // TODO
		// for (List<Box> stack : possibleStacks) {
		// debugln("stack:");
		// display(stack);
		// }

	}

	static int getHeight(List<Box> boxes, int bottomIdx) {
		int[] cacheHeights = new int[boxes.size()]; // cache similar calls
		return getHeight(boxes, bottomIdx, cacheHeights);
	}

	// Note: boxes are sorted
	// NICE-To-HAVE retrieve best stack
	static int getHeight(List<Box> boxes, int bottomIdx, int[] cacheHeights) {
		// base case
		if (bottomIdx > boxes.size())
			return 0;

		if (cacheHeights[bottomIdx] != 0) { // use cache if present
			return cacheHeights[bottomIdx];
		}

		// look for next stackable box
		Box bottom = boxes.get(bottomIdx);
		int maxHeight = 0;
		for (int i = bottomIdx + 1; i < boxes.size(); i++) {
			Box candidate = boxes.get(i);
			if (candidate.canBeOnTopOf(bottom)) {
				int height = getHeight(boxes, i);
				if (height > maxHeight)
					maxHeight = height;
			}
		}

		cacheHeights[bottomIdx] = bottom.h + maxHeight; // store result
		return cacheHeights[bottomIdx];

	}

	// DOES NOT WORK
	// static void getPossibleStacks(List<Box> boxes, int idxBox, List<List<Box>>
	// possibleStacks) {
	// // base case
	// if (idxBox == boxes.size()) {
	// debugln("adding empty list");
	// possibleStacks.add(new ArrayList<>());
	// return;
	// }
	//
	// // general case
	// // get a stack using n-1 boxes
	// getPossibleStacks(boxes, idxBox + 1, possibleStacks);
	// // attempt to add Box #idxBox at the highest point in existing stack
	// Box boxToAdd = boxes.get(idxBox);
	// List<List<Box>> newStacks = new ArrayList<>();
	// for (List<Box> subBoxes : possibleStacks) {
	// Box boxBelow = null;
	// for (Box box : subBoxes) {
	// if (boxToAdd.d < box.d && /* boxToAdd.h < box.h || */ boxToAdd.w < box.w) {
	// debugln("could add " + boxToAdd + " on top of " + box);
	// boxBelow = box;
	// break;
	// }
	// }
	//
	// if (boxBelow != null || subBoxes.isEmpty()) {
	// List<Box> newStack = new ArrayList<Box>();
	// newStack.addAll(subBoxes);
	// newStack.add(boxToAdd);
	// newStacks.add(newStack);
	// }
	// }
	// possibleStacks.addAll(newStacks);
	// }

	static void display(List<Box> boxes) {
		for (Box box : boxes) {
			debugln(box);
		}
	}

	static class Box implements Comparable<Box> {
		int h;
		int d;
		int w;

		Box(int h, int d, int w) {
			this.h = h;
			this.d = d;
			this.w = w;
		}

		// rotation allowed / height is not compared
		boolean canBeOnTopOf(Box btm) {
			return ((d < btm.d && /* h < btm.h && */ w < btm.w)
					|| (w < btm.d && /* h < btm.h && */ d < btm.w));
		}

		@Override
		public String toString() {
			return h + " x " + d + " x " + w;
		}

		// (reverse) sort arbitrarily by width
		@Override
		public int compareTo(Box o) {
			if (this.w < o.w)
				return 1;
			else if (this.w == o.w)
				return 0;
			else
				return -1;
		}
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
