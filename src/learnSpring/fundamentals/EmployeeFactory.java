package learnSpring.fundamentals;

public class EmployeeFactory {
	private static EmployeeFactory instance;

	// private constructor
	private EmployeeFactory() {
	}

	public static EmployeeFactory getEmployeeFactory() {
		System.out.println("fetching instance");
		if (instance == null) {
			System.out.println("new instance created");
			instance = new EmployeeFactory();
		}
		return instance;
	}
}
