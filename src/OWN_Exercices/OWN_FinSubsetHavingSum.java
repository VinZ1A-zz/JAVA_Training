package OWN_Exercices;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

public class OWN_FinSubsetHavingSum {

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

		aInput.add("1 -8 9 7 5 -2 4 9 12 3 5 2");
		// aInput.add("1 3 5 7");

		return aInput;
	}

	private static void doIt() {
		Scanner scan = new Scanner(System.in);

		List<Integer> arr = new ArrayList<>();
		while (scan.hasNext()) {
			int val = scan.nextInt();
			arr.add(val);
		}

		// TODO: optimize when all nbs same sign
		// when all nbs same value

		// look from idx zero, sum=25
		List<Sum> solutions = new ArrayList<>();
		List<Sum> perms = getPerms(arr, 0, 10, solutions);

		// debugln(perms);
		println(solutions);

		scan.close();

	}

	static class Sum {
		List<Integer> ints = new ArrayList<>();
		private int sum = 0;

		Sum(List<Integer> ints, int oneMoreInt) {
			this.ints.addAll(ints);
			this.ints.add(oneMoreInt);
			refreshSum();
		}

		void refreshSum() {
			sum = 0;
			for (int i : ints) {
				sum += i;
			}
		}

		Sum() {
		}

		@Override
		public String toString() {
			return ints + "(" + sum + ")";
		}
	}

	private static List<Sum> getPerms(List<Integer> arr, int idx, int total, List<Sum> solutions) {
		// base case
		if (idx == arr.size()) {
			Sum baseSum = new Sum();
			List<Sum> perms = new ArrayList<>();
			perms.add(baseSum);
			return perms;
		}

		// general case
		List<Sum> subPerms = new ArrayList<>();
		subPerms = getPerms(arr, idx + 1, total, solutions);
		List<Sum> perms = new ArrayList<>();
		for (Sum subList : subPerms) {
			// idx-th number is either there or not
			Sum otherSubList = new Sum(subList.ints, arr.get(idx));
			if (otherSubList.sum == total) {
				solutions.add(otherSubList);
			}
			perms.add(otherSubList);
			perms.add(subList);
		}

		return perms;
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
