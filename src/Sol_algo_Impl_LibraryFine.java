import java.io.ByteArrayInputStream;
import java.util.Scanner;
import java.util.Vector;

public class Sol_algo_Impl_LibraryFine {

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

		// 45
		aInput.add("9 6 2015"); // actually returned
		aInput.add("6 6 2015"); // expected

		return aInput;
	}

	private static void doIt() {
		Scanner scan = new Scanner(System.in);

		// scan.nextInt(); // rien a fout

		while (scan.hasNext()) {
			int dayRet = scan.nextInt();
			int monthRet = scan.nextInt();
			int yearRet = scan.nextInt();
			int dayDue = scan.nextInt();
			int monthDue = scan.nextInt();
			int yearDue = scan.nextInt();

			// if returned before return date,no fine
			if (dayDue >= dayRet && monthDue == monthRet && yearDue == yearRet || //
					(monthDue > monthRet && yearDue == yearRet) || //
					(yearDue > yearRet)) {
				System.out.println(0);
			} else if (monthDue == monthRet && yearDue == yearRet) {
				// if returned after but same month/year : fine = 15 x daysLate
				System.out.println(15 * (dayRet - dayDue));
			} else if (yearDue == yearRet) {
				// returned after month but same year: 500 x nb months late
				System.out.println(500 * (monthRet - monthDue));
			} else
				System.out.println(10_000);// or 10_000

		}

		scan.close();

	}

	private static void debug(Object obj) {
		if (_debug) {
			System.err.println(obj.toString());
		}
	}
}
