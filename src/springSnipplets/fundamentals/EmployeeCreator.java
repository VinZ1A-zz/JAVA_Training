package springSnipplets.fundamentals;

public class EmployeeCreator {
	public Employee createEmployee() {
		return new Employee();
	}

	public Employee createExecutive() {
		Employee emp = new Employee();
		emp.setGrade("GRADE A");
		emp.setTitle("EXEC");
		return emp;
	}
}
