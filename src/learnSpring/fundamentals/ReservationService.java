package learnSpring.fundamentals;

import org.springframework.stereotype.Component;

// annotation will ensure this class is picked up by framework and will get instanciated
// using component-scan in the Beans definition file
@Component
public class ReservationService {
	public void doReserve(ReservationMessage msg) {
		System.out.println("reserving ... " + msg.toString());
	}

	// quite a boring message we got there.
	public static class ReservationMessage {
	}
}
