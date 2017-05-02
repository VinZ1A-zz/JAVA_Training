import java.util.Arrays;
import java.util.Comparator;
import java.util.function.BiFunction;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

// TODO https://github.com/snowdream/115-Java-Interview-Questions-and-Answers/blob/master/en/SUMMARY.md
// https://github.com/snowdream/115-Java-Interview-Questions-and-Answers/blob/master/en/oop.md

@SuppressWarnings("restriction")
public class Main {

	public static void main(String[] args) {
		System.out.println("hey");

		// inheritanceTests();
		// sortStrings();
		lambdaExp();
		otherLambdas();

	}

	static void sortStrings() {
		// compare Strings by length rather than alphaOrder
		final class LengthComparator implements Comparator<String> {

			@Override
			public int compare(String o1, String o2) {
				// 0 if equal, neg if a<b, po if a>b (should not do a-b!)
				return Integer.compare(o1.length(), o2.length());
			}

		}

		// ArrayList<String> aStrList = new ArrayList<String>();
		final String[] aStrList = new String[5];
		aStrList[0] = "bbbb";
		aStrList[1] = "c";
		aStrList[2] = "aaa";
		aStrList[3] = "dd";
		aStrList[4] = "e";

		Arrays.sort(aStrList, new LengthComparator());
		System.out.println(Arrays.toString(aStrList));
	}

	@SuppressWarnings("unused")
	static void lambdaExp() {
		// ArrayList<String> aStrList = new ArrayList<String>();
		final String[] aStrList = new String[5];
		aStrList[0] = "bbbb";
		aStrList[1] = "c";
		aStrList[2] = "aaa";
		aStrList[3] = "dd";
		aStrList[4] = "e";

		// could write (String first, String second)
		// lambda exp is a Comparator<String> (aka. any piece of code).
		// second arg is instance of Comparator

		BiFunction<String, String, Integer> someSavedLambda = (first, second) -> Integer.compare(first.length(),
				second.length());

		// only works because Comparator is a functional interface ie. it has
		// only a single abstract method
		Arrays.sort(aStrList, (first, second) -> Integer.compare(first.length(), second.length()));
		// Arrays.sort(aStrList, someSavedLambda); // does not work!!
		// (BiFunction
		// is not a comparator)
		System.out.println(Arrays.toString(aStrList));
	}

	@SuppressWarnings("unused")
	static void otherLambdas() {
		// conversion to functional interface
		EventHandler<ActionEvent> listener = (event -> System.out.println("clicked!"));
		EventHandler<ActionEvent> oldSchoolListener = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				System.out.println("clicked!");

			}
		};

		// http://docs.oracle.com/javafx/2/ui_controls/ButtonSample.java.html
		// Button aButton = new Button("Decline");
		// aButton.setOnAction(event -> System.out.println("clicked!")); // or
		// // listener

		// one way of saving Lambda (can't use Object)
		BiFunction<String, String, Integer> someSavedLambda = (first, second) -> Integer.compare(first.length(),
				second.length());

		// note: event -> System.out.println(event) can also be written
		// System.out::println
		// could work as well woth Math::pow (with two arguments) or
		// String::compareToIgnoreCase
		String[] strings = new String[3];
		strings[0] = "aAa";
		strings[1] = "Bba";
		strings[2] = "cCc";
		Arrays.sort(strings, String::compareToIgnoreCase);
		System.out.println(Arrays.toString(strings));

	}

	// FX overview
	// https://docs.oracle.com/javase/8/javafx/get-started-tutorial/jfx-overview.htm#BABGBBDH

}
