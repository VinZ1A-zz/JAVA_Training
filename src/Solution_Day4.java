import java.io.ByteArrayInputStream;
import java.util.Scanner;
import java.util.Vector;

public class Solution_Day4 {

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

		aInput.add("4");
		aInput.add("-1");
		aInput.add("10");
		aInput.add("16");
		aInput.add("18");

		return aInput;
	}

	private static void doIt() {
		// Scanner scan = new Scanner(System.in);
		// int n = scan.nextInt();
		// scan.close();

		Person.main(null);

	}

	public static class Person {
		private int age = 0;

		public Person(int initialAge) {
			// Add some more code to run some checks on initialAge
			// not negative?
			if (initialAge < 0) {
				System.out.println("Age is not valid, setting age to 0.");
			} else {
				age = initialAge;
			}
		}

		public void amIOld() {
			// Write code determining if this person's age is old and print the
			// correct statement:
			if (age < 13) {
				System.out.println("You are young.");
			} else if (age < 18) {
				System.out.println("You are a teenager.");
			} else
				System.out.println("You are old.");
		}

		public void yearPasses() {
			// Increment this person's age.
			age++;
		}

		public static void main(String[] args) {
			Scanner sc = new Scanner(System.in);
			int T = sc.nextInt();
			for (int i = 0; i < T; i++) {
				int age = sc.nextInt();
				Person p = new Person(age);
				p.amIOld();
				for (int j = 0; j < 3; j++) {
					p.yearPasses();
				}
				p.amIOld();
				System.out.println();
			}
			sc.close();
		}
	}
}
