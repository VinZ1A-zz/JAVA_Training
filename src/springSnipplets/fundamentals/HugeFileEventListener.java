package springSnipplets.fundamentals;

import org.springframework.context.ApplicationListener;

// Custom Listener - listens to published events 'HugeFileEvent'
// (need Bean declaration)
public class HugeFileEventListener implements ApplicationListener<HugeFileEvent> {

	@Override
	public void onApplicationEvent(HugeFileEvent event) {
		System.out.println("Received event: " + event.getSource());

	}

}
