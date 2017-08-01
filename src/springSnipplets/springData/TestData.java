package springSnipplets.springData;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

// Note: MySQL installed on 3306 TCP Port (default) / root pwd for root / admin/admin for admin account
//Windows service name MySQL57, no X Protocol
// Use either MySQL Workbench or xxxx\MySQL Shell 1.0\bin\mysqlsh.exe

//Note: jdbc is thread Safe (can be injected in any nb of DAOs)
public class TestData {

	private ApplicationContext ctx = null;
	private MovieDAO movieDao = null;

	public TestData() {
		ctx = new ClassPathXmlApplicationContext("springSnipplets/ch2-spring-beans.xml");
		movieDao = (MovieDAO) ctx.getBean("movieDao");
		System.out.println("stars : " + movieDao.getStars("Roger Rabbit"));

		System.out.println("movie 2 is " + movieDao.getMovie(2).getTitle());

		System.out.println("there is a total of " + movieDao.getAllMovies().size() + " movies");

		Movie m = new Movie();
		m.setId(3);
		m.setTitle("Inception");
		m.setStars("5");
		movieDao.insertMovie(m);
		System.out.println("inserted movie 3 : " + movieDao.getMovie(3).getTitle());

		movieDao.deleteMovie(3);
		System.out.println("after deletion, there is now a total of " + movieDao.getAllMovies().size() + " movies");

		// Using Hibernate
		// 1. HibernateTemplate
		MovieDAO movieDao_HIB = (MovieDAO) ctx.getBean("movieDao_HIB");
		System.out.println("(from Hibernate) movie 2 is " + movieDao_HIB.getMovie_HIB(2).getTitle());

		// 2. using native API
		// Will do in deticated Hibernate package - using Session object
		// eg: beginTransaction(), save(someObj), getTransaction().commit() ...
	}

	public static void main(String... args) {
		TestData testData = new TestData();

		System.exit(0); // needed (forcefully close connections?)

	}
}
