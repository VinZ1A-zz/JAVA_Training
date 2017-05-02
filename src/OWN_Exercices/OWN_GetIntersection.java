package OWN_Exercices;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

public class OWN_GetIntersection {

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

		aInput.add("1 12 15 19 20 21");
		aInput.add("2 15 17 19 21 25 27");

		return aInput;
	}

	private static void doIt() {
		Scanner scan = new Scanner(System.in);

		int[] arr1 = toIntArray(scan.nextLine());
		int[] arr2 = toIntArray(scan.nextLine());

		List<Integer> intersect = new ArrayList<>();

		// should be already sorted
		Arrays.sort(arr1);
		Arrays.sort(arr2);

		int id1 = 0, id2 = 0;
		int n1 = arr1.length, n2 = arr2.length;

		do {

			if (arr1[id1] < arr2[id2]) {
				id1++;
			} else if (arr2[id2] < arr1[id1]) {
				id2++;
			} else { // they're equal
				intersect.add(arr1[id1]);
				debugln("adding " + arr1[id1] + "equal to " + arr2[id2]);
				id1++;
				id2++;
			}

		} while (id1 < n1 && id2 < n2);

		for (int i : intersect) {
			debug(i + " ");
		}

		scan.close();

	}

	private static int[] toIntArray(String str) {
		String[] nbStr = str.split(" ");
		int[] arr = new int[nbStr.length];
		for (int i = 0; i < nbStr.length; i++) {
			arr[i] = Integer.parseInt(nbStr[i]);
		}
		return arr;
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
