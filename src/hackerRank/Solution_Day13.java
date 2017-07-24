package hackerRank;
import java.io.ByteArrayInputStream;
import java.util.Scanner;
import java.util.Vector;

public class Solution_Day13 {

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

		aInput.add("The Alchemist");
		aInput.add("Paulo Coelho");
		aInput.add("248");

		return aInput;
	}

	abstract class Book {
		String title;
		String author;

		Book(String t, String a) {
			title = t;
			author = a;
		}

		abstract void display();

	}

	class MyBook extends Book {

		int _price = -1;

		MyBook(String t, String a) {
			super(t, a);
		}

		public MyBook(String title, String author, int price) {
			super(title, author);
			_price = price;
		}

		@Override
		void display() {
			System.out.println("Title: " + title);
			System.out.println("Author: " + author);
			System.out.println("Price: " + _price);

		}

	}

	private static void doIt() {
		Scanner sc = new Scanner(System.in);
		String title = sc.nextLine();
		String author = sc.nextLine();
		int price = sc.nextInt();
		Book new_novel = new Solution_Day13().new MyBook(title, author, price); // can't
																				// do
																				// static
		new_novel.display();
		sc.close();
	}
}
