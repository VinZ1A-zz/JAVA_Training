package hackerRank;
import java.io.ByteArrayInputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

// http://www.maths.surrey.ac.uk/hosted-sites/R.Knott/Fibonacci/cfINTRO.html
// THIS IS AMAZING !!!!
public class Sol_WeekCode29_MinimalDistanceToPi {

	// sets _debug with VM argument -Ddebug=true
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

		// expects :
		// 1 10 -> 22/7
		// aInput.add("1 10");

		// try to reach 999999/318309 (actually 999969/318300 is better)
		// 318318 is multiple of 106 -- SHALL NOT BE RETURNED EVA as it is Sheite
		// aInput.add("318309 318313"); // returns 999997/318309 (probably ok)

		// 5.7789063439038188850577320019016075941377E-10
		// for 103993/33102
		// yes we do need to get rid of 106 and 113
		// aInput.add("33070 33103"); // 33072 is div by 106

		// aInput.add("20000000 20100000");

		aInput.add("1 1000000000000000"); // max range test

		// aInput.add("1 100");

		return aInput;
	}

	private static void doIt() {
		someImpl();
	}

	// source:
	// http://math.stackexchange.com/questions/716944/how-to-find-continued-fraction-of-pi
	private static void someImpl() {
		Scanner scan = new Scanner(System.in);

		long min = scan.nextLong(); // min bound
		long max = scan.nextLong(); // min bound
		scan.close();

		if (min > max) {
			print("hell, no.");
			return;
		}

		// init stuff
		String pi = "3.1415926535897932384626433832795028841971693993751";
		int precision = pi.length() - pi.indexOf(".");
		BigDecimal x0 = new BigDecimal(pi);

		// get initial continuedFracs
		List<Fraction> fracs = new LinkedList<>();
		// long start = System.nanoTime();
		// takes 0.016 ms so no point hard-coding it
		getFracs(x0, max, precision, fracs);
		// try {
		// Thread.sleep(1000);
		// } catch (InterruptedException e) {
		// }
		// 1_001_989_136 is 1000 ms
		// ___16_668_929 is 0.016 ms
		// long elapsedTime = System.nanoTime() - start;
		// debugln("elapsedTime : " + elapsedTime);

		// looking for best denom multiple of one of the fracs
		long resDenum = -1;
		int bottomIds = 0; // greater than 0: SKIPS 22/7 ect... as a "good
												// candidate"
		// debugln("************** skipping all denums before " +
		// fracs.get(bottomIds).denum);
		int i = fracs.size() - 1;
		long denum = 1;
		for (; i >= bottomIds; i--) {
			denum = fracs.get(i).denum;
			if (denum > max)
				continue;
			// 6_800_000_000 : skipped 6_701_487_259
			// 2_000_000_000 : skipped 1_963_319_607
			// 820_000_000 : skipped 811_528_438
			// 26_000_000 : skipping 25510582
			// 400_000: 11 -> 29, 33,34, 36 -> 39, 42, 43, 49, 50 fails (1st
			// submission)
			// 1_000_000 : skipping 364913, 265381 and below
			// 400_000 : skipping 364913, 265381, 99532 // OK (once simplified)
			// 300_000 : skipping 265381, 99532 // fails 1,4,5,8 --> 364913 EVIL
			// 100_000 : skipping 99532 and below
			// the higher the value the slower !!
			if (denum < 6_800_000_000L) { // ******** PRECISION TWEAK ***********
				debugln(" skipping all denums before " + denum);
				break;
			}
			long divMax = max / denum;
			long divMin = min / denum;
			if (divMax - divMin > 0 || min % denum == 0) {
				// debugln(denum + " : divMax = " + divMax);
				// debugln(denum + " : divMin = " + divMin);
				if (min % denum == 0) {
					resDenum = divMin;
				} else {
					resDenum = divMin + 1;
				}
				// debugln("let's use the smallest mult. of " + denum + " in the range
				// [" + min + "," + max + "] : " + resDenum);
				println(resDenum * fracs.get(i).num + "/" + resDenum * denum);
				break;
			}
		}

		// OR --- keep going anyway (aka. "can we do better") , but how far? XXXXXX
		// TODO
		BigDecimal bestDiff = null;
		if (resDenum == -1) { // not even a multiple of [fracs(bottomIds).denum] (7,
													// 106..) in that range
			// then use other 'cute' algo to get between the given range (from ze top)
			// source:
			// https://www.wired.com/2011/03/what-is-the-best-fractional-representation-of-pi/
			BigDecimal zero = BigDecimal.valueOf(0);
			long bestDenom = 0;
			long bestNum = 0;
			for (long denom = max; denom >= min; denom--) {
				// debugln("try with " + denom);
				BigDecimal numDec = x0.multiply(BigDecimal.valueOf(denom));
				String numStr = numDec.toPlainString();
				String longStr = numStr.substring(0, numStr.indexOf("."));
				// debugln("num " + longStr);
				Long num = Long.parseLong(longStr);
				BigDecimal diff = x0
						.subtract(BigDecimal.valueOf(num).divide(BigDecimal.valueOf(denom), precision, BigDecimal.ROUND_HALF_UP));
				boolean isInitiallyPositive = (diff.compareTo(zero) > 0);
				// debugln("num: " + num + " diff : " + diff + isInitiallyPositive);
				if (bestDiff == null || diff.abs().compareTo(bestDiff) == -1) {
					bestDiff = diff.abs();
					bestDenom = denom;
					bestNum = num;
				}

				boolean isPos = isInitiallyPositive;
				do {
					if (isInitiallyPositive) {
						num++;
					} else {
						num--;
					}
					diff = x0
							.subtract(BigDecimal.valueOf(num).divide(BigDecimal.valueOf(denom), precision, BigDecimal.ROUND_HALF_UP));
					isPos = (diff.compareTo(zero) > 0);
					// debugln("num: " + num + " diff recalc: " + diff + isPos);
					if (diff.abs().compareTo(bestDiff) == -1) {
						bestDiff = diff.abs();
						bestDenom = denom;
						bestNum = num;
					}

				} while (isPos == isInitiallyPositive);
				// debugln("bestDiff : " + bestDiff);
			}
			debugln("bestDiff overall : " + bestDiff + " with : " + bestNum + "/" + bestDenom);

			// simplify fraction
			Fraction simplified = asFraction(bestNum, bestDenom);
			if (simplified.denum >= min && simplified.denum <= max) {
				print(simplified);
			} else {
				// find fraction closest to min, from simplified
				long modulo = min % simplified.denum;
				long minDenom = min;
				if (modulo > 0) {
					minDenom = min - modulo + simplified.denum;
				}
				long minNum = (minDenom / simplified.denum) * simplified.num;

				// print(bestNum + "/" + bestDenom); // minimize it
				print(minNum + "/" + minDenom);
			}

		}

		// ********* TEST STUFF to remove ***************
		// if (bestDiff == null) { // found at first step
		// bestDiff = x0.subtract(BigDecimal.valueOf(resDenum * fracs.get(i).num)
		// .divide(BigDecimal.valueOf(resDenum * denum), precision,
		// BigDecimal.ROUND_HALF_UP));
		// debugln("bestDiff " + bestDiff);
		// }
		//
		// // can we beat it?
		// BigDecimal diff = x0
		// .subtract(BigDecimal.valueOf(103993).divide(BigDecimal.valueOf(33102),
		// precision, BigDecimal.ROUND_HALF_UP));
		// // -0.00000560470639065831690102168548396820097202923672
		// // for 999999/318309
		// // 6.7849640913685068770499205263930934027735844E-7
		// // for 999997/318309 (better)
		// // -2.6676418906242231236893288649633380405195233E-7
		// // for 355/113 (better)
		// // 5.7789063439038188850577320019016075941377E-10
		// // for 103993/33102 (super good)
		// debugln("manual diff " + diff);
		// if (diff.abs().compareTo(bestDiff) == -1) {
		// debugln("manual diff wins");
		// }

		// ************* TEST *********************
		// Fraction simplified = asFraction(500, 1000); // (462, 1071);
		// debug(simplified);
	}

	public static void getFracs(BigDecimal x0, long max, int precision, List<Fraction> fracs) {
		long maxEver = (long) Math.pow(10, 15);
		// debugln("maxEver " + maxEver);
		BigDecimal one = BigDecimal.valueOf(1);
		BigDecimal bx;
		BigDecimal xx;
		BigDecimal xPrev = x0;
		String x0Str = x0.toPlainString();
		String a0Str = x0Str.substring(0, x0Str.indexOf("."));
		// debugln("a0Str " + a0Str);
		Long a0Long = Long.parseLong(a0Str); // a0=3
		List<Long> CFparams = new ArrayList<>();
		CFparams.add(a0Long);
		Long axLong = a0Long;
		Long aNextLong;
		Fraction frac = null;

		do {// for (int i = 0; i < 200; i++) {
			bx = xPrev.subtract(BigDecimal.valueOf(axLong));
			// debugln("bx " + bx);

			xx = one.divide(bx, precision, BigDecimal.ROUND_HALF_UP);
			// debugln("xx " + xx);
			String xxStr = xx.toPlainString();
			String axStr = xxStr.substring(0, xxStr.indexOf("."));
			// debugln("axStr " + axStr);
			aNextLong = Long.parseLong(axStr); // to retain !!!
			CFparams.add(aNextLong);
			// long oldDenum = frac != null ? frac.denum : 0;
			frac = getFracFromContinuedFraction(CFparams);
			debugln("frac for " + CFparams + ": " + frac);
			fracs.add(frac);

			BigDecimal diffWithPi = BigDecimal.valueOf(frac.num);
			// diffWithPi = diffWithPi.divide(BigDecimal.valueOf(frac.denum),
			// precision, BigDecimal.ROUND_HALF_UP);
			diffWithPi = diffWithPi.subtract(x0);
			// debugln("difference with Pi " + diffWithPi);

			// debugln("frac denum diff " + (frac.denum - oldDenum));
			// if (frac.denum > Math.min(max, maxEver)) {
			// debugln("iteration : " + frac.denum + "is over max (" + max + ")");
			// }

			axLong = aNextLong;
			xPrev = xx;
		} while (frac.denum < Math.min(max, maxEver));
	}

	public static long gcm(long a, long b) {
		if (b == 0) {
			return a;
		} else {
			long ret = gcm(b, a % b);
			return ret;
		}
	}

	public static Fraction asFraction(long a, long b) {
		long gcm = gcm(a, b);
		return new Fraction(a / gcm, b / gcm);
	}

	static Fraction getFracFromContinuedFraction(List<Long> array) {
		int len = array.size();
		long denum = array.get(len - 1);
		long num = array.get(len - 2) * array.get(len - 1) + 1;
		long prevDenum;
		// debugln("num" + num);
		// debugln("denum " + denum);
		for (int i = len - 2; i > 0; i--) {
			prevDenum = denum;
			denum = num;
			// debugln("calculating num : " + array.get(i - 1) + "(idx" + (i - 1) + ")
			// * " + denum + " + " + prevDenum);
			num = array.get(i - 1) * denum + prevDenum;
			// debugln("num" + num);
			// debugln("denum " + denum);
		}
		return new Fraction(num, denum);
	}

	static class Fraction {
		long num; // n
		long denum; // d

		public Fraction(long iNum, long iDenum) {
			num = iNum;
			denum = iDenum;
		}

		@Override
		public String toString() {
			return num + "/" + denum;
		}
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

	// **************** some manual experiments **********************
	// // set a0 to be 3 (integer value of x0, aka. pi)
	// BigDecimal b0 = x0.subtract(BigDecimal.valueOf(a0Long));
	// debugln("b0 " + b0);
	//
	// BigDecimal x1 = one.divide(b0, precision, BigDecimal.ROUND_HALF_UP);
	// debugln("x1 " + x1); //
	// 7.06251330593104576979300515257055804273431002514582
	// String x1Str = x1.toPlainString();
	// String a1Str = x1Str.substring(0, x1Str.indexOf("."));
	// debugln("a1Str " + a1Str);
	// Long a1Long = Long.parseLong(a1Str); // a1=7
	// // BigDecimal a1 = BigDecimal.valueOf(a1Long); // needed ??
	//
	// // what about the num/denominator now? (need to get 22/7)
	// // num0 = 3*7 +1
	// long num0 = a0Long * a1Long + 1;
	// debugln("num0 " + num0); // 22
	// // n is already 7 in this case (GENERALIZE!)
	// long denom0 = a1Long; // c'est tout.
	// debugln("denom0 " + denom0); //7
	//
	// // **************** SECOND PART ******* 333/106 using loop (ou pas)
	// BigDecimal b1 = x1.subtract(BigDecimal.valueOf(a1Long));
	// debugln("b1 " + b1); //
	// 0.06251330593104576979300515257055804273431002514582
	//
	// BigDecimal x2 = one.divide(b1, precision, BigDecimal.ROUND_HALF_UP);
	// debugln("x2 " + x2); //
	// x2=15.99659440668571988892306040475527460567160503148725
	// String x2Str = x2.toPlainString();
	// String a2Str = x2Str.substring(0, x2Str.indexOf("."));
	// debugln("a2Str " + a2Str);
	// Long a2Long = Long.parseLong(a2Str); // a2=15
	//
	// // aiming for 333/106
	// // d1 = a1*a2+ 1
	// long num1 = a1Long * a2Long + 1;
	// debugln("num1 " + num1); // 106 ??? reversed ??? this is denum
	// long denom1 = a0Long * num1 + a2Long;
	// debugln("denom1 " + denom1); // 333
	//
	// // ****************** next 355/113 ***************************
	// BigDecimal b2 = x2.subtract(BigDecimal.valueOf(a2Long));
	// debugln("b2 " + b2); //
	// 0.99659440668571988892306040475527460567160503148725
	//
	// BigDecimal x3 = one.divide(b2, precision, BigDecimal.ROUND_HALF_UP);
	// debugln("x3 " + x3); //
	// x3=1.00341723101337260346414717528795359173645793107104
	// String x3Str = x3.toPlainString();
	// String a3Str = x3Str.substring(0, x3Str.indexOf("."));
	// debugln("a3Str " + a3Str);
	// Long a3Long = Long.parseLong(a3Str); // a3=1
	//
	// // aiming for 355/113
	// long num2 = a2Long * a3Long + 1;
	// debugln("num2 " + num2); // 16 which is... ???
	// long denom2 = num2 * a1Long + a3Long;
	// debugln("denom2 " + denom2); // 113 OK
	// num2 = a0Long * denom2 + num2;
	// debugln("new num2 " + num2); // 355 ok but WTF ?
	//
	// // ****************** then 103993/33102 ***********************
	// // one more manual AHAHAHAHAHA (shame, shame.)
	//
	// BigDecimal b3 = x3.subtract(BigDecimal.valueOf(a3Long));
	// debugln("b3 " + b3); //
	// 0.00341723101337260346414717528795359173645793107104
	//
	// BigDecimal x4 = one.divide(b3, precision, BigDecimal.ROUND_HALF_UP);
	// debugln("x4 " + x4); //
	// x4=292.63459101439547237854369576041100301006256669091838
	// String x4Str = x4.toPlainString();
	// String a4Str = x4Str.substring(0, x4Str.indexOf("."));
	// debugln("a4Str " + a4Str);
	// Long a4Long = Long.parseLong(a4Str); // a4= 292

	// dreadful num/denom calculus --- not going anywhere
	// long num3 = a3Long * a4Long + 1;
	// debugln("num3 " + num3); // XXX
	// long denom3 = num3 * a2Long + a4Long;
	// debugln("denom3 " + denom3); // XXX
	// // num2 = a0Long * denom2 + num2;
	// // debugln("new num2 " + num2); // 355 ok but WTF ?
}
