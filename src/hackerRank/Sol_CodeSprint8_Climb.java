package hackerRank;
import java.io.ByteArrayInputStream;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.Vector;

public class Sol_CodeSprint8_Climb {

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

		// expected output 6 4 2 1
		aInput.add("7");
		aInput.add("100 100 50 40 40 20 10");
		aInput.add("4");
		aInput.add("5 25 50 120");

		return aInput;
	}

	static class ScoreRanked {
		int _score;
		int _rank;

		ScoreRanked(int score, int rank) {
			_score = score;
			_rank = rank;
		}
	}

	private static void doIt() {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		ScoreRanked[] scores = new ScoreRanked[n];
		int curRank = 0;
		int curScore = -1;
		int score = -1;
		for (int i = 0; i < n; i++) {
			score = in.nextInt();
			if (score != curScore) {
				curRank++;
			}
			curScore = score;
			scores[i] = new ScoreRanked(score, curRank);
		}

		int m = in.nextInt();
		int curIndexScore = n - 1;
		for (int i = 0; i < m; i++) {
			score = in.nextInt();
			while (curIndexScore >= 0 && scores[curIndexScore]._score < score) {
				--curIndexScore;
			}
			if (curIndexScore >= 0) {
				curRank = scores[curIndexScore]._rank;
				if (scores[curIndexScore]._score != score) {
					++curRank;
				}
				// System.err.println("score " + score + " is at rank " +
				// curRank);
				System.out.println(curRank);

			} else
				// System.err.println("score " + score + " is at rank 1");
				System.out.println(1);
		}

		in.close();

	}

	// not sure why this didn't work
	private void calculationInvalid(Scanner in, int score, TreeMap<Double, Integer> ranks) {
		int m = in.nextInt();
		double incrScore = -1;
		Entry<Double, Integer> zeEntry = null;
		for (int alice_i = 0; alice_i < m; alice_i++) {
			score = in.nextInt();
			// + 1 solves prob with duplicate
			incrScore = ((double) score) + 0.1;
			// System.err.println(incrScore);
			zeEntry = ranks.ceilingEntry(incrScore);
			if (zeEntry == null) {
				System.out.print(1);
			} else {
				System.out.println(zeEntry.getValue() + 1);
			}

		}

	}

	// way too slow
	private void calculationPasTop(int[] alice, TreeMap<Integer, Integer> ranks) {
		for (int score : alice) {
			int curBestRank = -1;
			TreeMap<Integer, Integer> newRanks = new TreeMap<Integer, Integer>(ranks);
			for (Entry<Integer, Integer> zeEntry : ranks.entrySet()) {
				// System.err.println("comparing " + score + " and " +
				// zeEntry.getKey());
				if (score < zeEntry.getKey()) {
					curBestRank = zeEntry.getValue();
					// System.err.println("giving " + zeEntry.getValue());
					System.out.println(zeEntry.getValue() + 1);
					break;
				} else {
					// System.err.println("skipping " + zeEntry.getKey());
					newRanks.remove(zeEntry.getKey());
				}
			}
			if (curBestRank == -1)
				System.out.println("1");
			ranks = newRanks;
		}

	}
}
