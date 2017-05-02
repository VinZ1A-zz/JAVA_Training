import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Vector;

public class Solution_Day8 {

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

		aInput.add("3");
		aInput.add("sam 99912222");
		aInput.add("tom 11122222");
		aInput.add("harry 12299933");
		aInput.add("sam");
		aInput.add("edward");
		aInput.add("harry");

		return aInput;
	}

	private static void doIt() {
		Scanner scan = new Scanner(System.in);

		int n = scan.nextInt();

		HashMap<String, String> aDic = new HashMap<String, String>();
		for (int i = 0; i < n; i++) {
			String name = scan.next();
			int phone = scan.nextInt();
			aDic.put(name, Integer.toString(phone));
		}
		while (scan.hasNext()) {
			String s = scan.next();
			String phone = aDic.get(s);
			if (phone == null) {
				System.out.println("Not found");
			} else
				System.out.println(s + "=" + phone);
		}

		scan.close();

	}
}
