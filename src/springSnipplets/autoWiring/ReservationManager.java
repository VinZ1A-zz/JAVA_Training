package springSnipplets.autoWiring;

import org.springframework.beans.factory.annotation.Autowired;

import springSnipplets.autoWiring.ReservationService.ReservationMessage;

//illustrates that Spring framework takes care entirely of instantiating components and their dependencies
// hence the expression 'dependency injection' as it takes care of the whole Object Graph

// Three types of autowiring: by Name, by Type and by Constructor
public class ReservationManager {
	// autowired: find the relevant dependency automatically by Type
	// can also support JSR-330's @Inject annotation
	// but: need to rebuild app when changing, and tie resource to config
	@Autowired
	private ReservationService reservationService = null;
	// note: autowiring does not work for primitives, only references

	public void process(ReservationMessage r) {
		reservationService.doReserve(r);
	}
}
