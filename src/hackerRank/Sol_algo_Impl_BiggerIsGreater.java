package hackerRank;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// Points: 813.00 Rank: 31599

public class Sol_algo_Impl_BiggerIsGreater {

	static boolean _debug = false;

	public static void main(String[] args) {

		if (args.length != 0) {
			System.err.println("in debug");
			_debug = false;
			// _fromEclipse = true;
			StringBuffer aInput = new StringBuffer("");
			for (String data : (getData())) {// getData()) { // getDataFromFile
				debug("..");
				aInput.append(data + "\r\n");
			}
			System.setIn(new ByteArrayInputStream(aInput.toString().getBytes()));
		}

		doIt();

	}

	// debug method - discard!
	static private Vector<String> getData() {
		Vector<String> aInput = new Vector<String>();

		aInput.add("1");// ba
		// aInput.add("ab");// no answer
		// aInput.add("bb");// hegf
		// aInput.add("hefg"); // hegf
		// aInput.add("dhck");// dhkc
		// aInput.add("dkhc"); // hcdk
		// aInput.add("zxydkhc"); // zxyhcdk
		// aInput.add("ocsmerkgidvddsazqxjbqlrrxcotrnfvtnlutlfcafdlwiismslaytqdbvlmcpapfbmzxmftrkkqvkpflxpezzapllerxyzlcf");
		// aInput.add("a");
		// aInput.add("zzzayybbaa"); // zzzbaaabyy
		// aInput.add("zzzabzzzba");
		// aInput.add("acfhomkba"); // zzzcaaacyy

		// inp dwqqfcbmmjtnozmeyfswxxbsrqjytnuvjtlkSol_algo_Impl_AppleOrange.javak
		// shd dwqqfcbmmjtnozmeyfswxxbsrqjytnuvkjklt
		// now dwqqfcbmmjtnozmeyfswxxbsrqjytnuvkjklt
		// got dwqqfcbmmjtnozmeyfswxxbsrqjytnuvkkjlt
		aInput.add("dwqqfcbmmjtnozmeyfswxxbsrqjytnuvjtlkk");

		return aInput;
	}

	static private Vector<String> getDataFromFile() {
		Vector<String> aInput = new Vector<String>();

		String fileName = "/Users/VinZ/Dropbox/Work/JAVA/Training/src/BiggerIsGreater_IN.txt";

		// read file into stream, try-with-resources
		List<String> aList = new ArrayList<String>();
		try (Stream<String> stream = Files.lines(Paths.get(fileName))) {

			// aList = stream.map(Integer::parseInt).collect(Collectors.toList());
			aList = stream.collect(Collectors.toList());

		} catch (IOException e) {
			e.printStackTrace();
		}

		// debug("done reading");
		aInput.addAll(aList);

		// for (String s : aInput) {
		// debug(s);
		// }

		return aInput;
	}

	private static void doIt() {
		// Scanner scan = new Scanner(System.in);

		someImpl();

	}

	// ************** Notes from discussions ****************
	// The Editorial solutions iterate forward from the start of the string. It
	// makes more sense to iterate backwards from the end. In most cases, this
	// will save a lot of time.
	// Since there cannot be an answer for any string smaller than two characters,
	// take the last character of the list and place it into a separate sequence
	// which is your sorted string. Now, working backwards along the rest of the
	// original string...
	// If a character is greather than or equal to the last character in the
	// sorted sequence, add it to the end of the sorted sequence (which keeps it
	// sorted with no complex algorithm needed). If you reach the beginning
	// without ever having encountered a character smaller than the last one in
	// the sorted sequence, there can be no answer.
	// The first time you encounter a character which is smaller than the last
	// (and biggest) character in the sorted sequence, you have found your
	// solution. All you have to do now is iterate forwards through the sorted
	// sequence until you find the first one in the sequence which is bigger. Move
	// that to the front of the sequence and put your character in its place.
	// Append your (nearly) sorted set to the part of the string that you haven't
	// yet traversed. Done, with minimum traversal and no complex sorting
	// algorithm
	private static void someImpl() {
		// Scanner scan = new Scanner(System.in);

		// int nbOfCases = scan.nextInt();

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int nbOfCases = 0;
		try {
			nbOfCases = Integer.parseInt(in.readLine());
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		BufferedWriter out = null;
		if (_debug) {
			File f = new File("/Users/VinZ/Dropbox/Work/JAVA/Training/src/BiggerIsGreater_OUT.txt");
			try {
				f.createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}

			FileWriter fstream = null;
			try {
				fstream = new FileWriter(f.getPath());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			out = new BufferedWriter(fstream);
		}

		for (int caseNb = 0; caseNb < nbOfCases; caseNb++) {

			String word = null;
			try {
				word = in.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}

			StringBuilder newWord = new StringBuilder("no answer");

			List<Character> suffix = new LinkedList<>(); // keep ordering after
																										// sorting
			char curMax = word.charAt(word.length() - 1);
			suffix.add(curMax);
			char curChar = Character.UNASSIGNED;// 'z' + 1;
			boolean found = false;
			int i = word.length() - 2;
			for (; i >= 0; i--) {
				curChar = word.charAt(i);
				if (curMax < curChar)
					curMax = curChar;
				else if (curChar < curMax) {
					debug("found curChar " + curChar + " less than " + curMax);
					found = true;
					break;
				}
				suffix.add(curChar);

			}

			// for (char c : suffix) {
			// debug("## " + c);
			// }

			if (found) { // curChar < curMax) {
				Collections.sort(suffix);
				// look for lesser match but not equal
				int searchIdx = Collections.binarySearch(suffix, (char) (curChar + 1));
				if (searchIdx <= -1) {
					searchIdx = -searchIdx - 1;
				}
				char toSwap = suffix.get(searchIdx);
				// char toSwap = suffix.ceiling(curChar);
				suffix.remove(searchIdx);
				suffix.add(searchIdx, curChar); // sometimes ok, sometimes need to be
																				// searchIdx + 1 ??
				Collections.sort(suffix); // solved in a cheap way
				debug("swapping " + curChar + " with " + toSwap);
				newWord = new StringBuilder(word.substring(0, i));
				newWord.append(toSwap);
				for (char c : suffix) {
					debug("append " + c);
					newWord.append(c);
				}
			}

			System.out.println(newWord);
			if (_debug) {
				try {
					out.write(newWord.toString() + "\n");
				} catch (IOException e1) {
				}
			}

		}
		// scan.close();
		if (_debug) {
			try {
				out.flush();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	private static void debug(Object obj) {
		if (_debug) {
			System.err.println(obj.toString());
		}
	}
}
