import java.io.ByteArrayInputStream;
import java.util.Scanner;
import java.util.Vector;

// Points: 1046.00 Rank: 21918
public class Sol_WeekCode29_CircleAndSquare {

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

		aInput.add("20 26"); // 20 16
		aInput.add("9 6 5");
		aInput.add("16 14 8 14");
		// aInput.add("13 13 13 21"); // square parallel to axis
		// aInput.add("2 2 17 14");

		return aInput;
	}

	private static void doIt() {
		someImpl();
	}

	private static void someImpl() {
		Scanner scan = new Scanner(System.in);

		int w = scan.nextInt(); // from 10 to 100
		int h = scan.nextInt(); // from 10 to 100

		// circle def
		Point Pc = new Point(scan.nextInt(), scan.nextInt()); // from -100 to 200
		int rad = scan.nextInt();// from o INCLUDED to 100
		Circle circle = new Circle(Pc, rad);
		circle.setDim(w, h);

		// square def
		Point p1 = new Point(scan.nextInt(), scan.nextInt());// from -100 to 200
		Point p3 = new Point(scan.nextInt(), scan.nextInt());// from -100 to 200
		Square square = new Square(p1, p3);
		square.setDim(w, h);

		displayGrid(w, h, circle, square);

	}

	static void displayGrid(int w, int h, Circle c, Square s) {
		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				// ********** DEBUG ********* // remove s.areCorners
				Point cur = new Point(x, y);
				if (c.isInside(cur) || s.isInside(cur) /* || s.areCorners(cur) */) {
					print("#");
				} else {
					print(".");
				}
			}
			println("");
		}
	}

	static class Shape {
		int w;
		int h;

		void setDim(int w, int h) {
			this.w = w;
			this.h = h;
		}
	}

	static class Square extends Shape {
		Point p1; // A
		Point p2; // B
		Point p3; // C
		Point p4; // D

		double vABx, vABy, vABAB, vADx, vADy, vADAD;

		public Square(Point p1, Point p3) {
			this.p1 = p1;
			this.p3 = p3;

			double x2 = (p1.x + p3.x + p3.y - p1.y) / 2;
			double y2 = (p1.y + p3.y + p1.x - p3.x) / 2;

			double x4 = (p1.x + p3.x + p1.y - p3.y) / 2;
			double y4 = (p1.y + p3.y + p3.x - p1.x) / 2;

			p2 = new Point(x2, y2);
			// debugln(p2);
			p4 = new Point(x4, y4);
			// debugln(p4);

			// determine vector AB
			vABx = p2.x - p1.x;
			vABy = p2.y - p1.y;
			vABAB = vABx * vABx + vABy * vABy;

			// determine vector AB
			vADx = p4.x - p1.x;
			vADy = p4.y - p1.y;
			vADAD = vADx * vADx + vADy * vADy;
		}

		public boolean areCorners(Point pt) {
			if (pt.equals(p1) || pt.equals(p2) || pt.equals(p3) || pt.equals(p4)) {
				return true;
			}
			return false;
		}

		private boolean isParallel() {
			return (Math.abs(p3.x - p1.x) == Math.abs(p3.y - p1.y));
		}

		public boolean isInside(Point pt) { // point M
			// Note: XY.WZ : cartesian product (dot product)
			// 0 < AM.AB < AB.AB
			// 0 < AM.AD < AD.AD

			// square is parallel to axis
			if (isParallel()) {
				boolean betweenXs = false;
				boolean betweenYs = false;
				if (p1.x < p3.x) {
					betweenXs = (pt.x >= p1.x && pt.x <= p3.x);
				} else {
					betweenXs = (pt.x >= p3.x && pt.x <= p1.x);
				}

				if (p1.y < p3.y) {
					betweenYs = (pt.y >= p1.y && pt.y <= p3.y);
				} else {
					betweenYs = (pt.y >= p3.y && pt.y <= p1.y);
				}

				return betweenYs && betweenXs;
			}

			// determine vector AM
			double vAMx = pt.x - p1.x;
			double vAMy = pt.y - p1.y;
			double vAMAB = vAMx * vABx + vAMy * vABy;
			double vAMAD = vAMx * vADx + vAMy * vADy;

			// to use when weird stuff happens ;)
			// if (pt.x == 13 && pt.y == 11) {
			// debugln("vAMx " + vAMx);
			// debugln("vAMy " + vAMy);
			// debugln("vAMAB " + vAMAB);
			// debugln("vAMAD " + vAMAD);
			//
			// debugln("vABAB " + vABAB);
			// debugln("vADAD " + vADAD);
			// }

			if (vAMAB >= 0 && vAMAB <= vABAB && vAMAD >= 0 && vAMAD <= vADAD) {
				return true;
			}

			return false;
		}
	}

	static class Circle extends Shape {
		Point center;
		int rad;

		public Circle(Point pt, int rad) {
			this.center = pt;
			this.rad = rad;
		}

		public boolean isInside(Point pt) {
			double radSq = Math.pow(rad, 2);
			double distBetweenPix = Math.pow(pt.x - center.x, 2) + Math.pow(pt.y - center.y, 2);

			if (distBetweenPix <= radSq) {
				return true;
			}

			return false;
		}
	}

	static class Point {
		double x;
		double y;

		Point(double x, double y) {
			this.x = x;
			this.y = y;
		}

		public boolean equals(Point other) {
			return (other.x == x && other.y == y);

		}

		@Override
		public String toString() {
			return ("(" + x + "," + y + ")");
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
