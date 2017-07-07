package learnSpring.fundamentals;

public class Person {
	private int age = 0;
	private String firstName = null;
	private String lastName = null;
	private Address address = null;

	public Person(String fName, String lName) {
		firstName = fName;
		lastName = lName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address; // hmmmhmmmhmm..
	}

	public String getDetails() {
		return firstName + " " + lastName + " is " + getAge() + " old and lives at " + getAddress();
	}

	// need to be static to be accessible through Bean
	public static class Address {
		// public Address() {
		// }

		private int doorNumber = 0;
		private String firstLine = null;
		private String secondLine = null;
		private String zipCode = null;

		// getters and setters (needed by Spring)
		public int getDoorNumber() {
			return doorNumber;
		}

		public void setDoorNumber(int doorNumber) {
			this.doorNumber = doorNumber;
		}

		public String getFirstLine() {
			return firstLine;
		}

		public void setFirstLine(String firstLine) {
			this.firstLine = firstLine;
		}

		public String getSecondLine() {
			return secondLine;
		}

		public void setSecondLine(String secondLine) {
			this.secondLine = secondLine;
		}

		public String getZipCode() {
			return zipCode;
		}

		public void setZipCode(String zipCode) {
			this.zipCode = zipCode;
		}

		@Override
		public String toString() {
			return getDoorNumber() + ", " + getFirstLine() + ", " + getSecondLine() + ", " + getZipCode();
		}
	}
}
