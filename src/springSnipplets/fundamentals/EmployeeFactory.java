package springSnipplets.fundamentals;

public class EmployeeFactory {
	private static EmployeeFactory instance;

	// private constructor
	private EmployeeFactory() {
	}

	public static EmployeeFactory getEmployeeFactory() {
		System.out.println("EmployeeFactory::getEmployeeFactory : fetching instance");
		if (instance == null) {
			System.out.println("EmployeeFactory::getEmployeeFactory : new instance of EmployeeFactory created");
			instance = new EmployeeFactory();
		}
		return instance;
	}
}
