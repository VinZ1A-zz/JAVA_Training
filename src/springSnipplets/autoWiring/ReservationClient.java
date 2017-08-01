package springSnipplets.autoWiring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import springSnipplets.autoWiring.ReservationService.ReservationMessage;

// illustrates auto-wiring (using @Autowired annotion)
public class ReservationClient {
	private ApplicationContext context = null;

	public ReservationClient() {
		context = new ClassPathXmlApplicationContext("springSnipplets/ch2-spring-beans.xml");
	}

	public void doReserve() {
		ReservationManager reservationManager = (ReservationManager) context.getBean("reservationManager");
		System.out.println("reservationManager bean instanciated");
		ReservationMessage msg = new ReservationMessage();
		// a reservationService is instanciated by the framework
		reservationManager.process(msg);
	}

	public static void main(String... args) {
		ReservationClient client = new ReservationClient();
		client.doReserve();
	}
}
