package learnSpring.fundamentals;

import org.springframework.beans.factory.annotation.Autowired;

import learnSpring.fundamentals.ReservationService.ReservationMessage;

//illustrates that Spring framework takes care entirely of instantiating components and their dependencies
// hence the expression 'dependency injection' as it takes care of the whole Object Graph
public class ReservationManager {
	// autowired: find the relevant dependency automatically
	// can also support JSR-330's @Inject annotation
	// but: need to rebuild app when changing, and tie resource to config
	@Autowired
	private ReservationService reservationService = null;

	public void process(ReservationMessage r) {
		reservationService.doReserve(r);
	}
}
