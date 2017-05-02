import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution_Day28 {

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
		aInput.add("riya riya@gmail.com");
		aInput.add("julia julia@julia.me");
		aInput.add("julia sjulia@gmail.com");
		aInput.add("julia julia@gmail.com");
		aInput.add("samantha samantha@gmail.com");
		aInput.add("tanya tanya@gmail.com");

		return aInput;
	}

	private static void doIt() {
		Scanner scan = new Scanner(System.in);

		int n = scan.nextInt();
		// TreeMap<String, String> sortedNamesMap = new TreeMap<String,
		// String>();
		ArrayList<String> sortedNames = new ArrayList<String>();
		for (int i = 0; i < n; i++) {
			String fName = scan.next();
			String email = scan.next();
			// System.err.println("fn/em " + fName + "," + email);

			// validation
			// Each of the first names consists of lower case letters only.
			// Each of the email IDs consists of lower case letters , and only.
			// The length of the first name is no longer than 20.
			// The length of the email ID is no longer than 50.

			if (fName.length() > 20)
				continue;
			if (email.length() > 50)
				continue;

			Pattern p = Pattern.compile("(\\w+)@(\\w+\\.\\w+)");
			Matcher m = p.matcher(email);
			if (m.find()) {
				// System.err.println("Found value: " + m.group(0));
				// System.err.println("Found value: " + m.group(1));
				// System.err.println("Found value: " + m.group(2));
				if (m.group(2).trim().toLowerCase().equals("gmail.com")) {
					// sortedNamesMap.put(fName, m.group(0));
					sortedNames.add(fName);
				}
			} else {
				// System.err.println("NO MATCH");
			}

		}

		scan.close();

		// for (String iKey : sortedNamesMap.keySet()) {
		// System.out.println(iKey);
		// }

		Collections.sort(sortedNames);
		for (String iKey : sortedNames) {
			System.out.println(iKey);
		}

	}
}
