package learnSpring.fundamentals;

import org.springframework.context.ApplicationEvent;

//Custom Event
public class HugeFileEvent extends ApplicationEvent {

	private String fileName = null;

	public HugeFileEvent(Object source, String fileName) {
		super(source);
		this.fileName = fileName;
	}

}
