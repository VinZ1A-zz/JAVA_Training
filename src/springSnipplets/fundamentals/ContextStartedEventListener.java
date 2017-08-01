package springSnipplets.fundamentals;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStartedEvent;

public class ContextStartedEventListener implements ApplicationListener<ContextStartedEvent> {

	@Override
	// invoked whenever a ContextStartedEvent is published (when
	// ApplicationContext is started)
	// application: can poll database, observe File when event is received
	public void onApplicationEvent(ContextStartedEvent event) {
		System.out.println("Received ContextStartedEvent application event : " + event.getSource());
	}

	// Also: ContextStoppedEvent, ContextRefreshedEvent
	// ContextClosedEvent, RequestHandledEvent (web request received)
}
