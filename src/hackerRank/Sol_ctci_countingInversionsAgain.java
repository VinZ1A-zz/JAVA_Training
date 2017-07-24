package hackerRank;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

public class Sol_ctci_countingInversionsAgain {

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

		aInput.add("2");
		aInput.add("5");
		aInput.add("1 1 1 2 2");
		aInput.add("5");
		aInput.add("2 1 3 1 2");

		return aInput;
	}

	private static void doIt() {
		Scanner in = new Scanner(System.in);
		int t = in.nextInt();
		for (int a0 = 0; a0 < t; a0++) {
			int n = in.nextInt();
			// int arr[] = new int[n];
			List<Integer> aList = new ArrayList<Integer>();
			for (int arr_i = 0; arr_i < n; arr_i++) {
				aList.add(in.nextInt());
			}
			long nbOfInversions = countInvAndSort(aList, 0, aList.size() - 1);
			System.out.println(nbOfInversions);
		}

		in.close();

	}

	static long countInvAndSort(List<Integer> ioList, int begPoint, int endPoint) {
		long[] nbOfInversions = new long[1];
		nbOfInversions[0] = 0;
		countInvAndSort(ioList, begPoint, endPoint, nbOfInversions);
		return nbOfInversions[0];
	}

	static void countInvAndSort(List<Integer> ioList, int begPoint, int endPoint, long[] nbOfInversions) {
		if (endPoint - begPoint < 1) // 1 or none elem
			return;
		int aMidPoint = begPoint + ((endPoint - begPoint) / 2);
		countInvAndSort(ioList, begPoint, aMidPoint, nbOfInversions);
		countInvAndSort(ioList, aMidPoint + 1, endPoint, nbOfInversions);
		mergeTwoHalves(ioList, begPoint, aMidPoint, endPoint, nbOfInversions);
	}

	private static void mergeTwoHalves(List<Integer> ioList, int iBegPoint, int iMidPoint, int iEndPoint,
			long[] nbOfInversions) {

		int n = iEndPoint - iBegPoint;
		List<Integer> aTemp = new ArrayList<Integer>();
		int i = iBegPoint, j = iMidPoint + 1;
		int iNum = -1, jNum = -1;
		for (int k = 0; k <= n; k++) {
			iNum = ioList.get(i);
			jNum = ioList.get(j);
			if (iNum < jNum) {
				aTemp.add(iNum);
				if (i < iMidPoint)
					i++;
				else if (j <= iEndPoint) {
					for (int k0 = j; k0 <= iEndPoint; k0++) {
						aTemp.add(ioList.get(k0));
					}
					break;
				}

			} else if (iNum > jNum) {
				// also add all the previous ones from
				// the second half
				nbOfInversions[0] = nbOfInversions[0] + iMidPoint + 1 - i;
				aTemp.add(jNum);
				if (j < iEndPoint)
					j++;
				else if (i <= iMidPoint) {
					for (int k0 = i; k0 <= iMidPoint; k0++) {
						aTemp.add(ioList.get(k0));
					}
					break;
				}

			} else { // same numbers

				aTemp.add(jNum);
				for (int k0 = i; k0 <= iMidPoint; k0++) {
					if (ioList.get(k0) > jNum) {
						nbOfInversions[0]++;
					}
				}
				if (j < iEndPoint) {
					j++;
				} else if (i <= iMidPoint) {
					for (int k0 = i; k0 <= iMidPoint; k0++) {
						aTemp.add(ioList.get(k0));
					}
					break;
				}
			}
		}
		i = 0;
		for (int k = iBegPoint; k <= iEndPoint; k++) {
			ioList.set(k, aTemp.get(i));
			i++;
		}

	}
}
