import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Vector;

public class Sol_algo_Recurs_CrossWordPuzzle {

	static final boolean _debug = "true".equals(System.getProperties().get("debug"));

	public static void main(String[] args) {

		if (args.length != 0) {
			System.err.println("in debug with debug = " + System.getProperties().get("debug"));
			// _debug = true; // set as final
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

		// aInput.add("+-++++++++");
		// aInput.add("+-++++++++");
		// aInput.add("+-++++++++");
		// aInput.add("+-----++++");
		// aInput.add("+-+++-++++");
		// aInput.add("+-+++-++++");
		// aInput.add("+++++-++++");
		// aInput.add("++------++");
		// aInput.add("+++++-++++");
		// aInput.add("+++++-++++");
		// aInput.add("LONDON;DELHI;ICELAND;ANKARA");

		// expects:
		// +C++++++++
		// +A++T+++++
		// +NIGERIA++
		// +A++L+++++
		// +D++A+++++
		// +A++V+++++
		// ++++I+++++
		// ++++V+++++
		// ++++++++++
		// CALIFORNIA
		aInput.add("+-++++++++");
		aInput.add("+-++-+++++");
		aInput.add("+-------++");
		aInput.add("+-++-+++++");
		aInput.add("+-++-+++++");
		aInput.add("+-++-+++++");
		aInput.add("++++-+++++");
		aInput.add("++++-+++++");
		aInput.add("++++++++++");
		aInput.add("----------");
		aInput.add("CALIFORNIA;NIGERIA;CANADA;TELAVIV");

		return aInput;
	}

	static int n = 10;
	static boolean[][] grid;

	private static void doIt() {
		Scanner scan = new Scanner(System.in);

		grid = new boolean[n][n]; // true when '-'
		for (int line = 0; line < n; line++) {
			String str = scan.nextLine();
			int i = 0;
			for (char c : str.toCharArray()) {
				grid[line][i] = (c == '+') ? false : true;
				i++;
			}
		}

		// display empty grid
		// for (boolean[] bools : grid) {
		// for (boolean b : bools) {
		// debug(b ? '-' : '+');
		// }
		// debugln("");
		// }

		String wordsStr = scan.nextLine();
		String[] dic = wordsStr.split(";");
		scan.close();

		debugln(Arrays.toString(dic));

		// get intersections
		List<Intersect> intersects = new ArrayList<>();
		for (int row = 0; row < n; row++) {
			for (int col = 0; col < n; col++) {

				Point p = new Point(row, col);
				// intersect = part of a horizontal AND vertical word
				if (p.partOfWordHoriz() && p.partOfWordVert()) {
					intersects.add(new Intersect(p));
				}

			}
		}

		// for each intersection, get the two words
		Map<Word, List<Intersect>> wordsToIntersect = new HashMap<>();
		for (Intersect p : intersects) {
			int colH = p.getFirstLetterH();
			int rowV = p.getFirstLetterV();
			// debugln("for p" + p + "colH : " + colH + " , rowV : " + rowV);
			Word wordH = new Word(new Point(p.row, colH), true);
			Word wordV = new Word(new Point(rowV, p.col), false);
			// debugln("wordH:" + wordH + " , wordV:" + wordV);
			p.words[0] = wordH; // add reference to those words in Intersect
			p.words[1] = wordV;
			debugln("intersect " + p);
			// add intersect to the list of intersects for this word
			List<Intersect> listForWordH = wordsToIntersect.get(wordH);
			if (listForWordH == null) {
				wordsToIntersect.put(wordH, new ArrayList<Intersect>());
			}
			wordsToIntersect.get(wordH).add(p);

			List<Intersect> listForWordV = wordsToIntersect.get(wordV);
			if (listForWordV == null) {
				wordsToIntersect.put(wordV, new ArrayList<Intersect>());
			}
			wordsToIntersect.get(wordV).add(p);

		}

		// isolated words (not intersecting with others)
		for (int row = 0; row < n; row++) {
			for (int col = 0; col < n; col++) {
				Point p = new Point(row, col);
				if (p.partOfWordHoriz()) {
					int colH = p.getFirstLetterH();
					Word wordH = new Word(new Point(p.row, colH), true);
					if (!wordsToIntersect.containsKey(wordH)) {
						debugln("isolated H Word : " + wordH);
						wordsToIntersect.put(wordH, new ArrayList<Intersect>());
					}
				}
				if (p.partOfWordVert()) {
					int rowV = p.getFirstLetterV();
					Word wordV = new Word(new Point(rowV, p.col), false);
					if (!wordsToIntersect.containsKey(wordV)) {
						debugln("isolated V Word : " + wordV);
						wordsToIntersect.put(wordV, new ArrayList<Intersect>());
					}
				}
			}
		}

		// debug stuff ;)
		for (Entry<Word, List<Intersect>> word : wordsToIntersect.entrySet()) {
			debug("Word " + word.getKey() + " --> pts: ");
			for (Intersect pt : word.getValue()) {
				debug(pt.toStringPt() + " ; ");
			}
			debugln("");
		}

		// will contain the solution(s) (solution = list of Words)
		List<List<Word>> result = new ArrayList<>();
		getSol(wordsToIntersect, 0, dic, result);

		// prepare a readable output
		char[][] finalizedGrid = new char[n][n];
		for (List<Word> oneRes : result) {
			debugln("one Solution:");
			for (Word word : oneRes) {
				debug(word + " ; ");
				if (word.beg.col == word.end.col) { // vertical word
					for (int i = word.beg.row; i <= word.end.row; i++) {
						finalizedGrid[i][word.beg.col] = word.content.charAt(i - word.beg.row);
					}
				} else { // horizontal word
					for (int i = word.beg.col; i <= word.end.col; i++) {
						finalizedGrid[word.beg.row][i] = word.content.charAt(i - word.beg.col);
					}
				}
			}
			break; // only one solution is needed
		}
		debugln("");

		// output finalized Grid
		for (char[] row : finalizedGrid) {
			for (char c : row) {
				if (c == 0) {
					print('+');
				} else
					print(c);
			}
			println("");
		}

	}

	// similar to 8-queen problem
	static void getSol(Map<Word, List<Intersect>> wordsToIntersect, int idx, String[] dic, List<List<Word>> result) {

		// base case
		if (idx == wordsToIntersect.size()) {
			// add current results in result
			List<Word> oneRes = new ArrayList<>();
			oneRes.addAll(wordsToIntersect.keySet());
			result.add(oneRes);
			return;
		}

		// general case
		// try all the known words @idx
		for (String candWord : dic) {
			if (isValid(wordsToIntersect, idx, candWord)) {
				// add candidate in wordsToIntersect
				Word word = new ArrayList<Word>(wordsToIntersect.keySet()).get(idx);
				word.content = candWord;
				// for this partial solution, try with other candidate strings in next
				// word
				getSol(wordsToIntersect, idx + 1, dic, result);
			}
		}
	}

	// checks whether candWord can be placed in wordsToIntersect.keys[idx]
	static boolean isValid(Map<Word, List<Intersect>> wordsToIntersect, int idx, String candWord) {
		Word word = new ArrayList<Word>(wordsToIntersect.keySet()).get(idx);
		// right length?
		if (candWord.length() != word.getLen())
			return false;

		// is compatible with existing words?
		boolean isComp = true;
		List<Intersect> intersects = wordsToIntersect.get(word);
		// for all intersects for this word
		for (Intersect pt : intersects) {
			int offsetIntersect1 = 0;
			// get the char of candWord intersecting with existing word
			if (word.beg.col == pt.col) { // vertical word
				offsetIntersect1 = pt.row - word.beg.row;
			} else {
				offsetIntersect1 = pt.col - word.beg.col;
			}
			char fromComp = candWord.charAt(offsetIntersect1);
			// for each of the two words at this intersection
			for (Word existingWord : pt.words) {
				// need to be defined, not be the current word, and be assigned to a
				// string
				if (existingWord == null || existingWord.equals(word) || existingWord.content == null)
					continue;
				// get the char of the existing word intersecting with current candidate
				int offsetIntersect2 = 0;
				if (existingWord.beg.col == pt.col) { // vertical word
					offsetIntersect2 = pt.row - existingWord.beg.row;
				} else {
					offsetIntersect2 = pt.col - existingWord.beg.col;
				}
				char toComp = existingWord.content.charAt(offsetIntersect2);
				// if chars don't match, bummer!
				if (toComp != fromComp)
					isComp = false;
			}
		}

		return isComp;
	}

	static class Word {
		Point beg;
		Point end;
		private int len = -1;
		private String content = null; // when solved

		Word(Point beg, Point end) {
			this.beg = new Point(beg.row, beg.col);
			this.end = new Point(end.row, end.col);
		}

		Word(Point beg, boolean isHoriz) {
			this.beg = new Point(beg.row, beg.col);
			this.end = new Point(beg.row, beg.col);
			if (isHoriz) {
				while (new Point(end.row, end.col).isLetter()) {
					this.end.col++;
				}
				this.end.col--;
			} else {
				while (new Point(end.row, end.col).isLetter()) {
					this.end.row++;
				}
				this.end.row--;
			}
		}

		int getLen() {
			if (len != -1)
				return len;
			if (beg.row == end.row) {
				len = Math.abs(beg.col - end.col) + 1;
			}
			if (beg.col == end.col) {
				len = Math.abs(beg.row - end.row) + 1;
			}
			return len;
		}

		@Override
		public String toString() {
			String ret = beg + " / " + end;
			if (content != null) {
				ret += " (" + content + ")";
			}
			return ret;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o)
				return true;
			if (!(o instanceof Word))
				return false;
			Word wd = (Word) o;
			return beg.equals(wd.beg) && end.equals(wd.end);
		}

		@Override
		public int hashCode() {
			int result = beg.hashCode();
			result = 31 * result + end.hashCode();
			return result;
		}
	}

	static class Intersect extends Point {

		Word[] words = new Word[2];

		Intersect(int row, int col) {
			super(row, col);
		}

		Intersect(Point p) {
			super(p.row, p.col);

		}

		@Override
		public String toString() {
			return super.toString() + " , w: " + Arrays.toString(words);
		}

		public String toStringPt() {
			return super.toString();
		}

	}

	static class Point {
		int row;
		int col;

		Point(int row, int col) {
			this.row = row;
			this.col = col;
		}

		boolean isValid() {
			return (row >= 0 && row < n && col >= 0 && col < n);
		}

		Boolean isLetter() {
			if (isValid()) {
				return grid[row][col];
			}
			return false;
		}

		int getFirstLetterH() {
			int curCol = col;
			while (new Point(row, curCol).isLetter()) {
				curCol--;
			}
			return curCol + 1;
		}

		int getFirstLetterV() {
			int curRow = row;
			while (new Point(curRow, col).isLetter()) {
				curRow--;
			}
			return curRow + 1;
		}

		// detect a line (another '-' before or after in same row)
		boolean partOfWordHoriz() {
			Point before = new Point(row, col - 1);
			Point after = new Point(row, col + 1);
			if (this.isLetter() && (before.isLetter() || after.isLetter()))
				return true;
			return false;
		}

		// a detect a '-' above or below
		boolean partOfWordVert() {
			Point above = new Point(row - 1, col);
			Point below = new Point(row + 1, col);
			if (this.isLetter() && (above.isLetter() || below.isLetter()))
				return true;
			return false;
		}

		@Override
		public String toString() {
			return row + "," + col;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o)
				return true;
			if (!(o instanceof Point))
				return false;
			Point pt = (Point) o;
			return row == pt.row && col == pt.col;
		}

		@Override
		public int hashCode() {
			int result = row;
			result = 31 * result + col;
			return result;
		}
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
