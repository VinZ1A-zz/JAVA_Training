package hackerRank;
import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import java.util.Vector;

public class Sol_ctci_SortComparator {

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

		aInput.add("5");
		aInput.add("amy 100");
		aInput.add("david 100");
		aInput.add("heraldo 50");
		aInput.add("aakansha 75");
		aInput.add("aleksa 150");

		return aInput;
	}

	private static void doIt() {

		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();

		Player[] player = new Player[n];
		Checker checker = new Checker();

		for (int i = 0; i < n; i++) {
			player[i] = new Player(scan.next(), scan.nextInt());
		}
		scan.close();

		Arrays.sort(player, checker);
		for (int i = 0; i < player.length; i++) {
			System.out.printf("%s %s\n", player[i].name, player[i].score);
		}
	}

}

class Player {

	String name;
	int score;

	Player(String name, int score) {
		this.name = name;
		this.score = score;
	}

}

class Checker implements Comparator<Player> {

	@Override
	public int compare(Player o1, Player o2) {
		if (o1.score < o2.score)
			return 1;
		else if (o1.score > o2.score)
			return -1;
		else
			return (o1.name.compareTo(o2.name));

	}

}
