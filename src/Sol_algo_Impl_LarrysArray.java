import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Vector;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.JUnitCore;

// Points: 1046.00 Rank: 21918
// ************* CONTAINS JUNITS EXAMPLES *****************
public class Sol_algo_Impl_LarrysArray {

	static final boolean _debug = "true".equals(System.getProperties().get("debug"));
	static List<String> res = new ArrayList<>();
	static List<String> expected = new ArrayList<>();

	public static void main(String[] args) {

		if (args.length != 0) {
			System.err.println("in debug");
			// _debug = true;
			// _fromEclipse = true;
			String aInput = "";
			for (String data : getData()) {
				aInput += data + "\r\n";
			}
			System.setIn(new ByteArrayInputStream(aInput.getBytes()));
		}

		res = doIt();

		for (String str : res) {
			println(str);
		}

		// e.g "Sol_algo_Impl_LarrysArray"
		if (_debug) {
			String className = new Object() {
			}.getClass().getEnclosingClass().getName();

			JUnitCore.main(className);
		}
	}

	// debug method - discard!
	static private Vector<String> getData() {
		Vector<String> aInput = new Vector<String>();

		expected.addAll(Arrays.asList("YES", "YES", "NO", "YES"));
		aInput.add("4");
		aInput.add("3");
		aInput.add("3 1 2");
		aInput.add("4");
		aInput.add("1 3 4 2");
		aInput.add("5");
		aInput.add("1 2 3 5 4");
		aInput.add("6");
		aInput.add("1 6 5 2 4 3");

		return aInput;
	}

	// ****************** JUNIT EXAMPLE [kw] ******************
	@BeforeClass
	public static void toDoBeforeAllTest() { // need to be static
		debugln("preparing ALL tests");
		if (res.isEmpty()) {
			main(new String[] { "blabla" }); // params needed to use custom input
		}
		debugln("DONE preparing ALL tests");
	}

	@Before
	public void toDoBeforeEachTest() {
		debugln("preparing test");
	}

	// use @Ignore to skip test
	@Test // @Ignore
	public void testNoop() {
		Assert.assertTrue(true);
	}

	// other tests
	// assertNotNull / Null
	// assertSame / NotSame
	// assertArrayEquals

	@Test
	public void testResult() {
		if (expected != null && res != null) {
			Assert.assertEquals(res, expected);
		}
	}

	@AfterClass
	public static void toDoAfterAllTest() { // need to be static
		debugln("cleaning-up ALL tests");
	}

	@After
	public void toDoAfterEachTest() {
		debugln("cleaning-up test");
	}

	// ***************** End JUNIT section ************************

	private static List<String> doIt() {
		return someImpl();
	}

	private static List<String> someImpl() {
		Scanner scan = new Scanner(System.in);
		List<String> result = new ArrayList<>();

		int nbTestCases = scan.nextInt();
		for (int testCase = 0; testCase < nbTestCases; testCase++) {
			int n = scan.nextInt();
			int[] arr = new int[n];
			Map<Integer, Integer> valToIdx = new HashMap<>();
			for (int i = 0; i < n; i++) {
				arr[i] = scan.nextInt();
				valToIdx.put(arr[i], i); // (assuming distinct vals)
			}

			int[] arrSorted = Arrays.copyOf(arr, n);

			Arrays.sort(arrSorted);

			// debugln(Arrays.toString(arr));
			// debugln(Arrays.toString(arrSorted));

			boolean canRotate = true;
			for (int i = 0; i < n; i++) {
				while (arr[i] != arrSorted[i]) {
					// int curSpot = Arrays.binarySearch(arrSorted, arrSorted[i]);
					int fromIdx = valToIdx.get(arrSorted[i]);
					int toIdx = i;
					// debugln("placing val " + arrSorted[i] + " at #" + toIdx + " from #"
					// + fromIdx);

					int begChunk = -1;
					if (fromIdx - toIdx >= 2) { // rotating digit at end of chunk
						begChunk = fromIdx - 2;
						if (n - begChunk < 3) {
							// debugln("can't rotate!");
							canRotate = false;
							break;
						}
						// rotation ABC -> CAB
						// debugln("rotating(A) " + arr[begChunk] + arr[begChunk + 1] +
						// arr[begChunk + 2] + " -> " + arr[begChunk + 2]
						// + arr[begChunk] + arr[begChunk + 1]);
						int tmp = arr[begChunk];
						arr[begChunk] = arr[begChunk + 2];
						arr[begChunk + 2] = arr[begChunk + 1];
						arr[begChunk + 1] = tmp;
					} else {
						begChunk = fromIdx - 1;
						if (n - begChunk < 3) {
							// debugln("can't rotate!");
							canRotate = false;
							break;
						}
						if (fromIdx - i == 1) {
							// rotation ABC -> BCA
							// debugln("rotating(B) " + arr[begChunk] + arr[begChunk + 1] +
							// arr[begChunk + 2] + " -> "
							// + arr[begChunk + 1] + arr[begChunk + 2] + arr[begChunk]);
							int tmp = arr[begChunk];
							arr[begChunk] = arr[begChunk + 1];
							arr[begChunk + 1] = arr[begChunk + 2];
							arr[begChunk + 2] = tmp;
						} else { // NEEDED ???
							// rotation ABC -> CAB
							// debugln("rotating(C) " + arr[begChunk] + arr[begChunk + 1] +
							// arr[begChunk + 2] + " -> "
							// + arr[begChunk + 2] + arr[begChunk] + arr[begChunk + 1]);
							int tmp = arr[begChunk];
							arr[begChunk] = arr[begChunk + 2];
							arr[begChunk + 2] = arr[begChunk + 1];
							arr[begChunk + 1] = tmp;
						}
					}
					// fromIdx = begChunk; // new placement
					valToIdx.put(arr[begChunk], begChunk);
					valToIdx.put(arr[begChunk + 1], begChunk + 1);
					valToIdx.put(arr[begChunk + 2], begChunk + 2);
					// debugln("rotating from " + begChunk + " : " + Arrays.toString(arr)
					// + ", from # " + fromIdx);
				}
				if (!canRotate) {
					result.add("NO");
					break;
				}

			}
			if (canRotate) {
				result.add("YES");
			}

		}
		return result;

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
