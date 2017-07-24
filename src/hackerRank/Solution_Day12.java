package hackerRank;
import java.io.ByteArrayInputStream;
import java.util.Scanner;
import java.util.Vector;

public class Solution_Day12 {

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

	static class Person {
		protected String firstName;
		protected String lastName;
		protected int idNumber;

		// Constructor
		Person(String firstName, String lastName, int identification) {
			this.firstName = firstName;
			this.lastName = lastName;
			this.idNumber = identification;
		}

		// Print person data
		public void printPerson() {
			System.out.println("Name: " + lastName + ", " + firstName + "\nID: " + idNumber);
		}

	}

	static class Student extends Person {

		public Student(String firstName, String lastName, int id, int[] iTestScores) {
			super(firstName, lastName, id);
			testScores = iTestScores; // not very safe, yea. ;)
		}

		private int[] testScores;

		public String calculate() {

			int avg = 0;
			for (int i = 0; i < testScores.length; i++) {
				avg += testScores[i];
			}
			avg = avg / testScores.length;
			if (avg < 40) {
				return "T";
			} else if (avg < 55) {
				return "D";
			} else if (avg < 70) {
				return "P";
			} else if (avg < 80) {
				return "A";
			} else if (avg < 90) {
				return "E";
			} else {
				return "O";
			}
		}

	}

	// debug method - discard!
	static private Vector<String> getData() {
		Vector<String> aInput = new Vector<String>();

		aInput.add("Heraldo Memelli 8135627");
		aInput.add("2");
		aInput.add("100 80");

		return aInput;
	}

	private static void doIt() {
		Scanner scan = new Scanner(System.in);
		String firstName = scan.next();
		String lastName = scan.next();
		int id = scan.nextInt();
		int numScores = scan.nextInt();
		int[] testScores = new int[numScores];
		for (int i = 0; i < numScores; i++) {
			testScores[i] = scan.nextInt();
		}
		scan.close();

		Student s = new Student(firstName, lastName, id, testScores);
		s.printPerson();
		System.out.println("Grade: " + s.calculate());

	}
}
