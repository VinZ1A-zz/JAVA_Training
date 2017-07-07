package learnSpring.fundamentals;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import learnSpring.fundamentals.ReservationService.ReservationMessage;

public class ReservationClient {
	private ApplicationContext context = null;

	public ReservationClient() {
		context = new ClassPathXmlApplicationContext("learnSpring/ch2-spring-beans.xml");
	}

	public void doReserve() {
		ReservationManager reservationManager = (ReservationManager) context.getBean("reservationManager");
		ReservationMessage msg = new ReservationMessage();
		reservationManager.process(msg);
	}

	public static void main(String... args) {
		ReservationClient client = new ReservationClient();
		client.doReserve();
	}
}
