import java.io.ByteArrayInputStream;
import java.util.Scanner;
import java.util.Vector;

// was 213248 with 96 pts
public class Sol_algo_Warmup_CircularArrayRot {

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

		// sizeArray, nbRot, nbQueries
		aInput.add("3 2 3");
		// array of ints
		aInput.add("1 2 3");
		// nbQueries, 1 per line
		aInput.add("0");
		aInput.add("1");
		aInput.add("2");

		return aInput;
	}

	private static void doIt() {
		Scanner in = new Scanner(System.in);
		int sizeArray = in.nextInt();
		int nbRot = in.nextInt();
		int nbQueries = in.nextInt();
		int[] a = new int[sizeArray];
		for (int a_i = 0; a_i < sizeArray; a_i++) {
			a[a_i] = in.nextInt();
		}

		// apply rotations
		int[] rotA = new int[sizeArray];
		nbRot = nbRot % sizeArray;
		int j = 0; // been lazy here.
		// copy last part from sizeArray - nbRot
		for (int i = sizeArray - nbRot; i < sizeArray; i++) {
			rotA[j] = a[i];
			j++;
		}
		// copy beginning up to sizeArray - nbRot - 1
		for (int i = 0; i < sizeArray - nbRot; i++) {
			rotA[j] = a[i];
			j++;
		}

		// for (int i = 0; i < sizeArray; i++) {
		// debug(rotA[i] + " ");
		// }

		for (int a0 = 0; a0 < nbQueries; a0++) {
			int m = in.nextInt();
			System.out.println(rotA[m]);
		}

		in.close();

	}

	private static void debug(String iStr) {
		if (_debug) {
			System.err.println(iStr);
		}
	}
}
