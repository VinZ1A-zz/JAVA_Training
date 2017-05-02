package OWN_Exercices;
import java.io.ByteArrayInputStream;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

public class OWN_MeetingConflict {

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

		aInput.add("12:30 14:00");
		aInput.add("16:00 17:00");
		aInput.add("09:00 10:30");
		aInput.add("12:15 12:45");// conf
		aInput.add("10:30 11:00");
		aInput.add("11:30 12:25");
		aInput.add("16:00 17:30"); // conf
		aInput.add("12:25 12:30");
		aInput.add("07:00 17:00"); // conf

		return aInput;
	}

	private static void doIt() {
		Scanner scan = new Scanner(System.in);

		// assuming all in same time zone! (or converted to GMT).
		List<Meeting> meetings = new ArrayList<>();
		while (scan.hasNext()) {
			String str = scan.nextLine();
			debugln(str);
			String[] hours = str.split(" ");
			LocalTime beg = LocalTime.parse(hours[0]);
			LocalTime end = LocalTime.parse(hours[1]);
			meetings.add(new Meeting(beg, end));
		}

		Collections.sort(meetings);

		debugln(meetings);

		Meeting prevMeeting = null;
		for (Meeting meeting : meetings) {
			if (prevMeeting != null) {
				if (meeting.isInConflictWith(prevMeeting)) {
					debugln(meeting + " conflicts with " + prevMeeting);
				}
			}
			prevMeeting = meeting;
		}

		scan.close();

	}

	static class Meeting implements Comparable<Meeting> {
		// int day; // future use
		LocalTime start;
		LocalTime end;

		Meeting(LocalTime start, LocalTime end) {
			this.start = start;
			this.end = end;
		}

		boolean isInConflictWith(Meeting o) {
			if (this.start.compareTo(o.end) < 0 && this.end.compareTo(o.start) > 0) {
				return true;
			}
			return false;
		}

		@Override
		public int compareTo(Meeting o) {
			return (this.start.compareTo(o.start));
		}

		@Override
		public String toString() {
			return start + " " + end;
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
