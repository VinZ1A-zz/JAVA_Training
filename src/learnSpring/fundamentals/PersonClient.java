package learnSpring.fundamentals;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PersonClient {
	private ApplicationContext context = null;

	public PersonClient() {
		// instanciate bucket of beans (could be split in different files)
		context = new ClassPathXmlApplicationContext("learnSpring/ch2-spring-beans.xml");
	}

	public String getPersonDetails() {
		Person person = (Person) context.getBean("person");
		return person.getDetails();
	}

	public static void main(String... args) {
		PersonClient client = new PersonClient();
		System.out.println(client.getPersonDetails());
	}
}
