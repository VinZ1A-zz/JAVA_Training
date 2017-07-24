package hackerRank;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

// Points: 1046.00 Rank: 21918
public class Sol_WeekCode29_BigSorting {

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

		// expects :
		// 1
		// 3
		// 3
		// 5
		// 10
		// 31415926535897932384626433832795
		aInput.add(" 6");
		aInput.add("31415926535897932384626433832795");
		aInput.add("1");
		aInput.add("3");
		aInput.add("10");
		aInput.add("3");
		aInput.add("5");

		// aInput.add("12");
		// aInput.add("24");
		// aInput.add("36");
		// aInput.add("01");
		// aInput.add("9");
		// aInput.add("99");
		// aInput.add("1235");
		// aInput.add("364");

		return aInput;
	}

	private static void doIt() {
		someImpl();
	}

	static class StringNum implements Comparable<StringNum> {
		String _val;

		public StringNum(String iStr) {
			_val = iStr;
		}

		@Override
		public int compareTo(StringNum o) {
			if (o._val.length() != this._val.length()) {
				return (o._val.length() > this._val.length()) ? -1 : 1;
			} else
				return this._val.compareTo(o._val);
		}

		@Override
		public String toString() {
			return _val;
		}
	}

	private static void someImpl() {
		Scanner scan = new Scanner(System.in);
		scan.next(); // discard nb of items

		List<StringNum> arr = new ArrayList<>();
		while (scan.hasNext()) {
			StringNum str = new StringNum(scan.next());
			arr.add(str);
		}

		Collections.sort(arr);

		for (StringNum a : arr) {
			System.out.println(a);
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
}
