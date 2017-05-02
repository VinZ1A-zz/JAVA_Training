package CodingGame;
import java.io.ByteArrayInputStream;
import java.util.Scanner;
import java.util.Vector;

/**
 * Auto-generated code below aims at helping you parse the standard input
 * according to the problem statement.
 **/
class SolutionBender {

	static private Vector<String> getData() {
		Vector<String> aInput = new Vector<String>();

		// aInput.add("10 10");
		// aInput.add("##########");
		// aInput.add("# #");
		// aInput.add("# S W #");
		// aInput.add("# #");
		// aInput.add("# $ #");
		// aInput.add("# #");
		// aInput.add("#@ #");
		// aInput.add("# #");
		// aInput.add("#E N #");
		// aInput.add("##########");

		aInput.add("8 8");
		aInput.add("########");
		aInput.add("# @    #");
		aInput.add("#     X#");
		aInput.add("# XXX  #");
		aInput.add("#   XX #");
		aInput.add("#   XX #");
		aInput.add("#     $#");
		aInput.add("########");

		return aInput;
	}

	static boolean _debug = true;

	public static void main(String args[]) {
		if (args.length != 0) {
			_debug = true;
			String aInput = "";
			for (String data : getData()) {
				aInput += data + "\r\n";
			}
			System.setIn(new ByteArrayInputStream(aInput.getBytes()));
		}
		doIt();

	}

	public static enum Dir {
		S("SOUTH"), E("EAST"), N("NORTH"), W("WEST");

		private static Dir[] _order = { Dir.S, Dir.E, Dir.N, Dir.W };
		String _val;

		Dir(String iVal) {
			_val = iVal;
		}

		@Override
		public String toString() {
			return _val;
		}

		public Dir getNextDir(boolean isInverted) {
			boolean willBeNext = false;
			if (isInverted) {
				for (int i = _order.length - 1; i > 0; i--) {
					Dir aDir = _order[i];
					if (willBeNext) {
						return aDir;
					}
					if (aDir == this) {
						willBeNext = true;
					}
				}
				return _order[_order.length - 1];
			} else { // non-inverted

				for (Dir aDir : _order) {
					if (willBeNext) {
						return aDir;
					}
					if (aDir == this) {
						willBeNext = true;
					}

				}
				return _order[0];
			}
		}

		public Point getMoveOffset() {
			if (this.equals(Dir.E)) {
				return new Point(+1, 0);
			} else if (this.equals(Dir.N)) {
				return new Point(0, -1);
			} else if (this.equals(Dir.S)) {
				return new Point(0, +1);
			} else if (this.equals(Dir.W)) {
				return new Point(-1, 0);
			}
			return null;
		}
	}

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
			return new String("(" + _col + "," + _row + ")");
		}

		@Override
		public int hashCode() {
			// TODO Auto-generated method stub
			return super.hashCode();
		}
	}

	public static class Map {

		private char[][] _map;
		private int _nbOfCols;
		private int _nbOfRows;

		Map(int nbOfCols, int nbOfRows) { // refers to map
			_map = new char[nbOfCols][nbOfRows];
			_nbOfCols = nbOfCols;
			_nbOfRows = nbOfRows;
		}

		void setPoint(Point iPt, char aChar) {
			_map[iPt._col][iPt._row] = aChar;
		}

		boolean isObstacle(Point iPt, boolean hasBoost) {
			if (getCharAt(iPt) == '#' || (getCharAt(iPt) == 'X' && !hasBoost)) {
				return true;
			}
			return false;
		}

		Vector<Point> getPosOf(char iChar) {
			Vector<Point> vecPts = new Vector<Point>();
			for (int i = 0; i < _nbOfRows; i++) {
				for (int j = 0; j < _nbOfCols; j++) {
					if (_map[j][i] == iChar) {
						vecPts.add(new Point(j, i));
					}
				}
			}
			return vecPts;
		}

		char getCharAt(Point iPt) {
			return _map[iPt._col][iPt._row];
		}
	}

	public static class Robot {

		private class State {

			private Point _loc;
			boolean _hasBoost = false;
			boolean _isInverted = false;
			Dir _dir = Dir.S; // initially South

			public State(Point iPt) {
				_loc = new Point(iPt._col, iPt._row);
			}

			public State(State iState) {
				_loc = new Point(iState._loc._col, iState._loc._row);
				_hasBoost = iState._hasBoost;
				_isInverted = iState._isInverted;
				_dir = iState._dir;
			}
		}

		private State _state;
		private Map _map;
		// remember last time map has changed (detect loop)
		public Vector<State> _tripSinceLastChange = new Vector<State>();

		Point getPt() {
			return _state._loc;
		}

		Robot(Map iMap) {
			// defines initial position
			_state = new State(iMap.getPosOf('@').firstElement());
			_map = iMap;
		}

		void move() {

			// remembers trip before moving on
			// debugln("adding " + _state._loc);
			_tripSinceLastChange.add(_state);

			Point newPt;
			while (true) {
				newPt = _state._loc.add(_state._dir.getMoveOffset());
				if (_map.isObstacle(newPt, _state._hasBoost)) {
					_state._dir = _state._dir.getNextDir(_state._isInverted);
				} else {
					break;
				}
			}

			Dir moveJustDone = _state._dir;
			_state = new State(_state);

			// is a B - change hasBoost / I - change invert / ENWS : change default
			// dir / T : teleport
			char charAtNewPt = _map.getCharAt(newPt);
			if (charAtNewPt == 'B') {
				_state._hasBoost = !_state._hasBoost;
			}
			if (charAtNewPt == 'I') {
				_state._isInverted = !_state._isInverted;
			}
			if (charAtNewPt == 'X') {
				// debugln(" destroy X");
				_map.setPoint(newPt, ' '); // destroy X
				_tripSinceLastChange.clear();
			}
			if (charAtNewPt == 'E' || charAtNewPt == 'N' || charAtNewPt == 'W' || charAtNewPt == 'S') {
				_state._dir = Dir.valueOf(Character.toString(charAtNewPt));
				// debugln("changed dir " + _dir);
			}
			if (charAtNewPt == 'T') {
				Vector<Point> listOfTs = _map.getPosOf('T');
				for (Point aPt : listOfTs) {
					if (!aPt.equals(newPt)) {
						// debugln("other T " + aPt);
						newPt = aPt;
						break;
					}
				}
			}

			// debugln("new Pt " + newPt + " after going " + moveJustDone);
			_state._loc = newPt;
		}

		boolean isInLoop() {
			for (State aState : _tripSinceLastChange) {
				// debugln(" in mem: " + aState._loc);
				if (aState._loc.equals(_state._loc) && aState._hasBoost == _state._hasBoost
						&& aState._isInverted == _state._isInverted && aState._dir == _state._dir) {
					// debugln("in loop! " + _state._loc);
					return true;
				}
			}
			return false;
		}
	}

	public static void doIt() {
		Scanner in = new Scanner(System.in);
		int L = in.nextInt(); // nb of lines
		int C = in.nextInt(); // nb of cols
		debugln("L,C " + L + "," + C);
		in.nextLine();
		Map aMap = new Map(C, L);
		for (int i = 0; i < L; i++) {
			String row = in.nextLine();
			debugln(row);
			for (int j = 0; j < row.length(); j++) {
				aMap.setPoint(new Point(j, i), row.charAt(j));
			}
		}

		Robot bender = new Robot(aMap);
		int maxIters = 600;
		while (true) {
			bender.move();
			maxIters--;
			if (aMap.getCharAt(bender.getPt()) == '$' || bender.isInLoop() || (_debug && maxIters == 0)) {
				// debugln("RIP");
				break;
			}
		}

		if (bender.isInLoop()) {
			System.out.println("LOOP");
		} else {
			for (Robot.State aState : bender._tripSinceLastChange) {
				System.out.println(aState._dir.toString());
			}
		}
	}

	static void debugln(String iStr) {
		if (_debug) {
			System.err.println(iStr);
		}
	}
}