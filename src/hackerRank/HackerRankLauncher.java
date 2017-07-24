package hackerRank;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class HackerRankLauncher {

	static final String className = "Sol_algo_Impl_ModifiedKaprekarNum";
	static final String fileName = "/Users/VinZ/Dropbox/Work/JAVA/Training/src/Sol_algo_Impl_ModifiedKaprekarNum_IN.txt";
	static final String results = "/Users/VinZ/Dropbox/Work/JAVA/Training/src/Sol_algo_Impl_ModifiedKaprekarNum_OUT.txt";

	public static void main(String... args) {

		try {
			getData();

			// to do: comparison of output with file and outputs difference
			PrintStream out = null;
			PrintStream original = new PrintStream(System.out);
			try {
				out = new PrintStream(new FileOutputStream(results));
				System.setOut(out);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

			Class<?> classDef = Class.forName(className);
			Method[] methods = classDef.getMethods();
			for (Method m : methods) {
				if (m.getName().equals("main")) {
					m.invoke(null, new Object[] { args });
					break;
				}
			}
			out.flush();
			out.close();
			// cancel System.setOut
			System.setOut(original);

			displayOut();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

	}

	private static void getData() {
		Vector<String> strList = new Vector<String>();

		List<String> aList = new ArrayList<String>();
		try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
			aList = stream.collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
		}
		strList.addAll(aList);

		StringBuffer aInput = new StringBuffer("");
		for (String data : strList) {
			aInput.append(data + "\r\n");
		}

		System.setIn(new ByteArrayInputStream(aInput.toString().getBytes()));

	}

	private static void debug(Object obj) {
		System.err.println(obj.toString());
	}

	static void displayOut() {
		Vector<String> strList = new Vector<String>();
		List<String> aList = new ArrayList<String>();
		try (Stream<String> stream = Files.lines(Paths.get(results))) {
			aList = stream.collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
		}
		strList.addAll(aList);

		StringBuffer aInput = new StringBuffer("");
		for (String data : strList) {
			// aInput.append(data + "\r\n");
			System.out.println(data);
		}
	}

}
