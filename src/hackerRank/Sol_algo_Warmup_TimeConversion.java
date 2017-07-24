package hackerRank;
import java.io.ByteArrayInputStream;
import java.util.Scanner;
import java.util.Vector;

// raised to 243452
public class Sol_algo_Warmup_TimeConversion {

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

		// aInput.add("07:05:45PM"); // 19:05:45
		aInput.add("12:40:22AM");

		return aInput;
	}

	private static void doIt() {
		Scanner scan = new Scanner(System.in);

		String aTime = scan.next();
		System.out.println(manually(aTime));

		scan.close();

	}

	private static String manually(String aTime) {
		String hour = aTime.substring(0, aTime.indexOf(':'));
		String amOrpm = aTime.substring(aTime.length() - 2);
		String remainder = aTime.substring(aTime.indexOf(':'), aTime.length() - 2);

		debug(hour + " then " + remainder + " am/pm:" + amOrpm);
		int hourVal = Integer.parseInt(hour);
		if (hourVal == 12 && amOrpm.equals("AM")) {
			hourVal = 0;
		} else if (hourVal == 12 && amOrpm.equals("PM")) {
			// hourVal = 12;
		} else if (amOrpm.equals("PM")) {
			hourVal = hourVal + 12;
		}

		String newHour = String.format("%02d", hourVal);

		return newHour + remainder;
	}

	private static void debug(String iStr) {
		if (_debug) {
			System.err.println(iStr);
		}
	}
}
