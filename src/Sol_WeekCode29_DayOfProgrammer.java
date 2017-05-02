import java.io.ByteArrayInputStream;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.Vector;

// Points: 1046.00 Rank: 21918
public class Sol_WeekCode29_DayOfProgrammer {

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
		// 12.09.2016
		aInput.add("1918");

		return aInput;
	}

	private static void doIt() {
		// someImpl();
		simpleImpl();
	}

	private static void simpleImpl() {
		Scanner scan = new Scanner(System.in);

		int n = scan.nextInt();
		String suffix = ".09." + n;
		String solution;
		int dayInSep = 13;

		if (n == 1918) { // expected 26
			dayInSep += 14 - 1;
		} else if (n < 1918) {
			if (n % 4 == 0) {
				dayInSep--; // was leap year eg. one more day in Feb
			}
		} else { // from 1919 onwards
			if (n % 400 == 0 || (n % 4 == 0 && n % 100 > 0)) {
				dayInSep--;
			}
		}

		print(dayInSep + suffix);
	}

	// just. No. To. Java. Calendar
	private static void someImpl() {
		Scanner scan = new Scanner(System.in);

		int n = scan.nextInt();

		// general case
		Period p = Period.ZERO;
		if (n == 1918) {
			p = Period.ofDays(13); // DAMN!!!!
			// 31 to 14 : adding 13 days not 14 grrr
		}
		// julian calendar adjustment
		if (n < 1918) {
			// add back the day which would otherwise be removed (Gregorian calendar)
			// when year is divisible by 4, divisible by 100 AND not 400 : eg.
			// 100,200,300,500...
			// considered leap for Julian but not leap for Gregorian
			// !!! DODGY !!!
			// if (n % 4 == 0 && n % 100 == 0 && n % 400 > 0) {
			// p = Period.ofDays(+1);
			// }

			// %4 == 0 and % 100 > 0 --> would not be leap for Greg --> need + 1
			if (n % 4 == 0 && n % 100 == 0 && n % 400 > 0) {
				debugln("adjusted");
				p = Period.ofDays(+1);
			}

		}

		LocalDate zeDate = LocalDate.of(n, 1, 1);
		debugln("init : " + zeDate);
		zeDate = zeDate.plusDays(256 - 1).plus(p); // not counting 01/01
		// debugln(zeDate);

		DateTimeFormatter f = DateTimeFormatter.ofPattern("dd.MM.yyyy");
		System.out.print(f.format(zeDate));

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
