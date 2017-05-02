package CodingGame;
import java.io.ByteArrayInputStream;
import java.util.Scanner;
import java.util.Vector;

/**
 * Auto-generated code below aims at helping you parse the standard input
 * according to the problem statement.
 **/
class PlayerIndy {

	public static class Point {

		int _col, _row;

		Point(int iCol, int iRow) {
			_col = iCol;
			_row = iRow;
		}

		Point add(Point iPt) {
			return new Point(_col + iPt._col, _row + iPt._row);
		}

		@Override
		public boolean equals(Object iPt) {
			if (iPt == null)
				return false;
			if (iPt == this)
				return true;
			if (!(iPt instanceof Point))
				return false;
			Point iPtType = (Point) iPt;
			if (iPtType._col == this._col && iPtType._row == this._row) {
				return true;
			}
			return false;
		}

		@Override
		public String toString() {
			return new String(_col + " " + _row);

		}

		@Override
		public int hashCode() {
			// TODO Auto-generated method stub
			return super.hashCode();
		}

		// public String asOutput() {
		// return new String("(" + _col + "," + _row + ")");
		// }
	}

	// path pair of Sides(S) = input (LEFT) + output (BOTTOM)
	enum S {
		T("TOP"), L("LEFT"), R("RIGHT"), B("BOTTOM");

		private String _val;

		private S(String iFullVal) {
			_val = iFullVal;
		}

		@Override
		public String toString() {
			return _val;
		}

		static Point newTile(Point iPt, S iOutSide) {
			if (iOutSide.equals(S.B)) {
				return iPt.add(new Point(0, +1));
			} else if (iOutSide.equals(S.T)) {
				return iPt.add(new Point(0, -1));
			} else if (iOutSide.equals(S.L)) {
				return iPt.add(new Point(-1, 0));
			} else if (iOutSide.equals(S.R)) {
				return iPt.add(new Point(+1, 0));
			}
			return null;
		}
	}

	// Each type contains several paths
	enum Type {
		A(new S[][] {}), // Type 0
		B(new S[][] { { S.L, S.B }, { S.T, S.B }, { S.R, S.B } }), // Type 1
		C(new S[][] { { S.R, S.L }, { S.L, S.R } }), // Type 2
		D(new S[][] { { S.T, S.B } }), // Type 3
		E(new S[][] { { S.T, S.L }, { S.R, S.B } }), // Type 4
		F(new S[][] { { S.T, S.R }, { S.L, S.B } }), // Type 5
		G(new S[][] { { S.R, S.L }, { S.L, S.R } }), // Type 6 ( = type 2)
		H(new S[][] { { S.T, S.B }, { S.R, S.B } }), // Type 7
		I(new S[][] { { S.L, S.B }, { S.R, S.B } }), // Type 8
		J(new S[][] { { S.L, S.B }, { S.T, S.B } }), // Type 9
		K(new S[][] { { S.T, S.L } }), // Type 10
		L(new S[][] { { S.T, S.R } }), // Type 11
		M(new S[][] { { S.R, S.B } }), // Type 12
		N(new S[][] { { S.L, S.B } }); // Type 13

		S[][] _sidePairs = new S[6][2];

		Type(S[][] iSides) {
			_sidePairs = iSides;
		}

		// get Type from letter (static)
		static Type getType(int iTypeNb) {
			return Type.values()[iTypeNb];
		}

		// get Side From Input Side ( current type )
		S getOutSide(S iFromSide) {
			// would be better with a Set but how to initialize?
			for (S[] aSidePairs : _sidePairs) {
				if (aSidePairs[0] == iFromSide) {
					return aSidePairs[1];
				}
			}
			return null;
		}
	}

	static boolean _debug = false;

	// static boolean _fromEclipse = false;

	public static void doIt() {
		Scanner in = new Scanner(System.in);
		int W = in.nextInt(); // number of columns.
		int H = in.nextInt(); // number of rows.
		in.nextLine();

		Type[][] aMap = new Type[W][H];
		for (int i = 0; i < H; i++) {
			// represents a line in the grid and contains W integers. Each integer
			// represents one room of
			// a given type.
			int j = 0;
			for (String iNb : in.nextLine().split(" ")) {
				aMap[j++][i] = Type.getType(Integer.parseInt(iNb));
			}
		}
		int EX = in.nextInt(); // the coordinate along the X axis of the exit (not
														// useful for this first mission, but must be read).

		// game loop
		while (true) {
			int XI = in.nextInt();
			int YI = in.nextInt();
			String POS = in.next();

			// (JUST) One line containing the X Y coordinates of the room in which you
			// believe Indy will be on the next turn.
			System.out.println((S.newTile(new Point(XI, YI), aMap[XI][YI].getOutSide(S.valueOf(POS.substring(0, 1))))));

			// if (_fromEclipse) {
			// break;
			// }
		}
	}

	// debug method - discard!
	static private Vector<String> getData() {
		Vector<String> aInput = new Vector<String>();

		aInput.add("3 3");
		aInput.add("0 3 0");
		aInput.add("0 3 0");
		aInput.add("0 3 0");
		aInput.add("1");
		aInput.add("1 0 TOP");

		// W/H : 13/10
		// 3 12 8 6 2 2 8 2 9 0 0 0 0
		// 11 5 10 0 0 0 3 0 3 0 0 0 0
		// 0 11 2 2 2 2 6 2 1 2 2 13 0
		// 0 0 0 0 0 12 8 2 1 2 2 9 0
		// 0 0 12 2 2 1 4 2 10 0 0 11 13
		// 0 0 3 0 0 7 9 0 0 0 0 0 3
		// 0 0 11 2 2 10 11 2 2 2 2 2 9
		// 0 12 8 2 2 2 2 8 2 2 2 2 10
		// 0 11 4 2 2 2 2 10 12 13 12 13 0
		// 0 0 3 12 8 8 13 12 4 5 5 10 0
		// EX:2
		// Xi/YI/TOP:0/0/TOP

		return aInput;
	}

	public static void main(String args[]) {
		if (args.length != 0) {
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

	static void debugln(String iStr) {
		if (_debug) {
			System.err.println(iStr);
		}
	}
}