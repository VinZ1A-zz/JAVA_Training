package hackerRank;
import java.io.ByteArrayInputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.Vector;
import java.util.stream.Collectors;

// http://www.maths.surrey.ac.uk/hosted-sites/R.Knott/Fibonacci/cfINTRO.html
// THIS IS AMAZING !!!!
public class Sol_WeekCode29_AlmostIntegerRockGarden {

	// sets _debug with VM argument -Ddebug=true
	static final boolean _debug = "true".equals(System.getProperties().get("debug"));

	// Editorial
	// https://www.hackerrank.com/contests/w29/challenges/almost-integer-rock-garden/editorial
	// find nb of combinations wit repetitions
	// http://keisan.casio.com/exec/system/1223622559
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

		aInput.add("7 11"); // 7 11 already there
		// aInput.add("11 11");

		return aInput;
	}

	private static void doIt() {
		someImpl();
	}

	static final int n = 12; // half-size of grid
	static Map<Coord, Point> points = new HashMap<>();
	// Points per given dist
	static Map<Double, ArrayList<Point>> perDist = new TreeMap<>();
	static Map<BigDecimal, ArrayList<Point>> perDecimal = new TreeMap<>();

	private static void someImpl() {
		Scanner scan = new Scanner(System.in);

		int x = scan.nextInt();
		int y = scan.nextInt();
		scan.close();

		// list of points which do not have an integer distance to the origin
		int countNonInt = 0;
		int countTotal = 0;
		// only do an eight of the square (symetrical)
		for (int i = 0; i <= n; i++) {
			for (int j = i; j <= n; j++) { // half where j >= i
				Point pt = new Point(i, j);
				pt.calcDist();
				// debugln(pt.dist);
				// add in hashMap? and some counter 'used' (up to 8 usages)
				if (!pt.isDistInteger) {
					points.put(new Coord(i, j), pt); // Coord --> Pt
					ArrayList<Point> ptsInMap = perDist.get(pt.distEstim);
					if (ptsInMap == null) {
						ptsInMap = new ArrayList<Point>();
					}
					ptsInMap.add(pt);
					perDist.put(pt.distEstim, ptsInMap);
					perDecimal.put(pt.dist.subtract(BigDecimal.valueOf(pt.dist.intValue())), ptsInMap);

				}
				countNonInt = pt.isDistInteger ? countNonInt : countNonInt + 1; // debug
				countTotal++;
			}
		}
		debugln(countNonInt + " non-Int pts vs " + countTotal + " total");

		// *********** seed grid ***************
		// Point[] stones = new Point[12]; // not sure we even need this
		// int[][] initVals = { { 7, 11 }, { 11, 1 }, { -2, 12 }, { 5, 4 }, { 12, -3
		// }, //
		// { 10, 3 }, { 9, 6 }, { -12, -7 }, { 1, 11 }, { -6, 6 }, { 12, -4 }, { 4,
		// 12 } };
		// // int idx = 0;
		BigDecimal total = BigDecimal.valueOf(0);
		// for (int[] pair : initVals) {
		// Point pt = getPt(pair[0], pair[1]);
		// // stones[idx] = pt;
		// pt.stilAvailable--;
		// total = total.add(pt.dist);
		// // idx++;
		// }

		debugln("total : " + total);

		// NOW the game may begin.
		// x,y is a new (unmovable) stone replacing an existing one
		Point newPt = getPt(x, y);
		// flag as unmovable (can't be swapped)
		newPt.nonPermutablePoint = new Coord(x, y);
		newPt.calcDist();
		debugln("newPt (" + newPt + ") : " + newPt.dist);
		// if (newPt.isPresent()) { // if stone already there : DONE
		// displayResult();
		// return;
		// }

		// let's sort ALL points by distance. // Sum from (included) : 49 to 176
		// ************** DEBUG ********************
		Comparator<Map.Entry<Coord, Point>> distCmp = (a, b) -> a.getValue().dist.compareTo(b.getValue().dist);
		Map<Coord, Point> sortedByDist = sortByValue(points, distCmp);
		int count = 0;
		double minTotal = 0;
		for (Point pt : sortedByDist.values()) {
			debugln("#" + ++count + "\tdist : " + pt.dist + "\t(" + pt + ")" + //
					(pt.isDiago ? "d" : "") + "\t"
					+ (pt.isPresent() ? (pt.isPermutable() ? "#" + pt.stilAvailable : pt.stilAvailable) : ""));
			if (count <= 12) {
				minTotal += pt.distEstim;
			}
		}
		debugln("minTotal " + minTotal);
		// **************************** END DEBUG ***********

		// try to swap with existing other point with same distance, and DONE!
		// ArrayList<Point> ptsInMap = perDist.get(newPt.distEstim);
		// debugln("ptsInMap size " + ptsInMap.size());
		// for (Point pt : ptsInMap) {
		// if (!pt.equals(newPt)) {
		// debugln("pt " + pt + "avail : " + pt.isPresent());
		// if (pt.isPresent()) {
		// // remove pt
		// pt.stilAvailable++;
		// // add new Pt
		// newPt.stilAvailable--;
		// displayResult();
		// return; // DONE
		// }
		// }
		// }

		// otherwise (no avail point with same distance exists):
		// --- how do we know the total we are aiming for won't be reachable?
		// -- hash of current configuration seen twice (or multiple times if using
		// Rand)

		// give it a try :
		debugln("current total : " + total);
		List<BigDecimal> decimals = new ArrayList<>(perDecimal.keySet()); // 68
																																			// values
		for (BigDecimal dec : decimals) {
			debugln("dec : " + dec);
		}

		// add new Point now and correct the mess later (adjust decimals)
		newPt.stilAvailable--; // add new Pt
		// update total with added 13th point
		total = total.add(newPt.dist);
		debugln("total with 1 point : " + total);
		// decimal part
		BigDecimal totalDec = total.subtract(BigDecimal.valueOf(total.intValue()));
		debugln("totalDec : " + totalDec);

		// remove random other Pt

		// nextInt(max - min + 1) + min;
		// Point ptRemoved = null;
		// do {
		// int ptRemovedIdx = (new Random()).nextInt((points.keySet().size() - 1) +
		// 1) + 0;
		// ptRemoved = new ArrayList<>(points.values()).get(ptRemovedIdx);
		// } while (!ptRemoved.isPermutable() && ptRemoved.isPresent()); // do this
		// // until we
		// // have a non
		// // permutable point
		// debugln("removed " + ptRemoved);
		//
		// ptRemoved.stilAvailable++;

		// now we have 12 points.
		// let's try with a fixed target (from 49 to 176) sum and see how/why we
		// fail
		BigDecimal target = BigDecimal.valueOf(135).subtract(total); // 135 minus
																																	// current
																																	// length
		debugln(" targetting " + target);
		sum_up(target);

		// int pos = Collections.binarySearch(decimals, totalDec);
		// debugln("pos : " + pos);
		// if (pos < 0) { // can't happen (now)
		// debugln("best match " + decimals.get(-pos - 1));
		// // as binarySearch will always return pos of upper bound
		// // let's pick either this best match or the one before
		// BigDecimal diff;
		// } else {
		// debugln("exact match " + decimals.get(pos));
		// }

	}

	// static BigDecimal precision = new BigDecimal("0.000000000001"); // 10exp-12
	static BigDecimal precision = new BigDecimal("0.00000001"); // 10exp-12

	static void sum_up_recursive(Map<BigDecimal, ArrayList<Point>> expandedDists, BigDecimal target,
			Map<BigDecimal, ArrayList<Point>> partial) {
		if (partial.size() > 11) // no more than 11 elements
		{
			return;
		}
		BigDecimal s = BigDecimal.valueOf(0);
		for (BigDecimal x : partial.keySet()) // need to see Points ?
			s = s.add(x);
		// Is (s-target) within the precision range?
		if (partial.size() < 9) {
			// debugln("size of partial : " + partial.size() + " with s = " + s);
		}
		// was s.subtract(target)
		int sInt = s.intValue();
		if (sInt > 0 && s.subtract(BigDecimal.valueOf(sInt)).abs().compareTo(precision) < 1) {
			debugln("sum(" + Arrays.toString(partial.keySet().toArray()) + ")=" + sInt); // was
																																										// target
			debugln("points " + Arrays.toString(partial.values().toArray()));
			debugln("precision " + s.subtract(BigDecimal.valueOf(s.intValue())).abs());
			return;
		}
		// if (s.add(precision).compareTo(target) == 1) // s+precision >= target
		// (here
		// // it acts like a maximum)
		// {
		// debugln("I'm out wit s = " + s.add(precision));
		// return;
		// }
		// to do : add a limit in depth and change target ?
		for (int i = 0; i < expandedDists.size(); i++) { // numbers.size()
			Map<BigDecimal, ArrayList<Point>> remaining = new TreeMap<>();
			List<BigDecimal> decimals = new ArrayList<>(expandedDists.keySet());
			BigDecimal n = decimals.get(i);
			for (int j = i + 1; j < expandedDists.size(); j++) {
				remaining.put(decimals.get(j), expandedDists.get(decimals.get(j)));
			}
			Map<BigDecimal, ArrayList<Point>> partial_rec = new TreeMap<BigDecimal, ArrayList<Point>>(partial);
			partial_rec.put(n, expandedDists.get(n));
			sum_up_recursive(remaining, target, partial_rec);
		}
	}

	static void sum_up(BigDecimal target) {
		Map<BigDecimal, ArrayList<Point>> expandedDists = new TreeMap<BigDecimal, ArrayList<Point>>();
		for (Entry<Double, ArrayList<Point>> elem : perDist.entrySet()) {
			int countSame = 0;
			for (Point pt : elem.getValue()) {
				for (int i = 0; i < pt.stilAvailable; i++) {
					countSame++;
					if (countSame > 3) // limit to 4 of same kind (test) - 5 when 2 pts
						break;
				}

			}
			for (int k = 0; k < countSame; k++) { // it's a map!! useless
				// same list of points for each repeated big decimal...
				expandedDists.put(elem.getValue().get(0).dist, elem.getValue());
				// debugln("added " + elem + " , pt " + pt);
			}
			debugln("countSame " + countSame);
		}
		debugln("coin change with " + expandedDists.size()); // OUCH. Just. No.

		sum_up_recursive(expandedDists, target, new TreeMap<BigDecimal, ArrayList<Point>>());
	}

	private static void displayResult() {
		// display while dispatching similar stones (same avail) in grid
	}

	// normalize Pt in the right 8th of square and return ref from list
	private static Point getPt(int x, int y) {
		if (x < 0)
			x = -x;
		if (y < 0)
			y = -y;
		if (x > y) {
			int temp = x;
			x = y;
			y = temp;
		}
		return points.get(new Coord(x, y));
	}

	static class Coord {

		private final int x;
		private final int y;

		public Coord(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o)
				return true;
			if (!(o instanceof Coord))
				return false;
			Coord key = (Coord) o;
			return x == key.x && y == key.y;
		}

		@Override
		public int hashCode() {
			int result = x;
			result = 31 * result + y;
			return result;
		}

	}

	static class Point {
		int x; // for convenience (are in Coord)
		int y;
		BigDecimal dist = null;
		Double distEstim = null;
		boolean isDistInteger = true; // dist is an integer
		boolean isDiago = false;
		int stilAvailable; // counter of availability
		Coord nonPermutablePoint = null; // the point not to be removed

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
			// note: diags (3,3) has avail = 4, others have 8
			if (x == y) {
				stilAvailable = 4;
				isDiago = true;
			} else
				stilAvailable = 8;
		}

		public void calcDist() {
			int distSq = x * x + y * y;
			dist = getSqrt(distSq);
			distEstim = dist.doubleValue();
			if (distEstim != dist.intValue()) {
				isDistInteger = false;
			}
		}

		// get Sqrt with precision X in tenX (or at least, we tried)
		public static BigDecimal getSqrt(int x) {
			BigDecimal zero = BigDecimal.valueOf(0);
			BigDecimal hundred = BigDecimal.valueOf(100);
			BigDecimal ten18 = BigDecimal.valueOf(1000000000).multiply(BigDecimal.valueOf(1000000000));
			BigDecimal precision = BigDecimal.valueOf(1).divide(ten18, 20, BigDecimal.ROUND_HALF_UP);
			// double num = Math.pow(7, 2) + Math.pow(11, 2);
			BigDecimal numBD = BigDecimal.valueOf(x);
			// debugln(x);
			// initial seed
			BigDecimal numSq = BigDecimal.valueOf(Math.sqrt(x)).setScale(24, BigDecimal.ROUND_HALF_UP);
			// debugln("numSq : " + numSq);
			BigDecimal diff;
			int count = 200; // max nb of loops
			do {
				BigDecimal newNum = numSq.multiply(numSq).setScale(24, BigDecimal.ROUND_HALF_UP);
				// debugln("newNum : " + newNum);
				diff = newNum.subtract(numBD);
				// debugln("diff " + diff);
				if (diff.abs().compareTo(precision) == -1) {
					// debugln("precision reached! ");
					break;
				}
				if (diff.compareTo(zero) == 1) { // diff positive
					numSq = numSq.subtract(diff.divide(hundred));
				} else {
					numSq = numSq.subtract(diff.divide(hundred));
				}
				// debugln("new numSq " + numSq);
				count--;// debug
			} while (count > 0); // infinite-protected loop
			return numSq;
		}

		// at least one of those points on the grid
		public boolean isPresent() {
			if ((isDiago && stilAvailable < 4) || (!isDiago && stilAvailable < 8)) {
				return true;
			}
			return false;
		}

		// in practice, that means that stillAvailable must always stay <=7 or <=3
		// for Diag
		public boolean isPermutable() {
			return (nonPermutablePoint == null);
		}

		@Override
		public String toString() {
			return x + "," + y;
		}

		@Override
		public boolean equals(Object obj) {
			Point zePt = (Point) obj;
			return (x == zePt.x && y == zePt.y);
		}

		@Override
		public int hashCode() {
			// TODO Auto-generated method stub
			return super.hashCode();
		}

		// @Override
		// public int compareTo(Object o) {
		// return dist.compareTo(((Point) o).dist);
		// }

	}

	public static <K, V> Map<K, V> sortByValue(Map<K, V> map, Comparator<Map.Entry<K, V>> cmp, boolean isReverse) {

		if (isReverse) {
			// dropped Map.Entry.comparingByValue(Collections.reverseOrder())
			return map.entrySet().stream().sorted(cmp.reversed())
					.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
		} else {
			return map.entrySet().stream().sorted(cmp)
					.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
		}
	}

	// extends Comparable<? super V>
	public static <K, V> Map<K, V> sortByValue(Map<K, V> map, Comparator<Map.Entry<K, V>> cmp) {
		return sortByValue(map, cmp, false); // not reversed by default
	}

	@SuppressWarnings("unused")
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

	@SuppressWarnings("unused")
	private static void print(Object obj) {
		System.out.print(obj.toString());
	}

	@SuppressWarnings("unused")
	private static void println(Object obj) {
		System.out.println(obj.toString());
	}

}
