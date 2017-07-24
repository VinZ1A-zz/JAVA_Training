package learnSpring.fundamentals;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;

// Custom Publisher - can be injected with a ApplicationEventPublisher object
// whenever the setApplicationEventPublisher is invoked
// (ApplicationEventPublisher has one method, publishEvent -- to publish an Event)
public class HugeFileEventPublisher implements ApplicationEventPublisherAware {

	private ApplicationEventPublisher pub = null;

	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher pub) {
		this.pub = pub;
	}

	public void publish(String fileName) {
		System.out.println("Publishing a HugeFileEvent, file is " + fileName);
		HugeFileEvent hugeFileEvent = new HugeFileEvent(this, fileName);
		pub.publishEvent(hugeFileEvent); // publish to the registered publisher
	}

}
