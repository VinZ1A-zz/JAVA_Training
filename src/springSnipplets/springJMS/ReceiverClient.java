package springSnipplets.springJMS;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ReceiverClient {
	private ApplicationContext ctx = null;
	private JobsReceiver receiver = null;
	private MyMessageListener listener = null;

	public ReceiverClient() {
		ctx = new ClassPathXmlApplicationContext("springSnipplets/ch2-spring-beans.xml");
		receiver = (JobsReceiver) ctx.getBean("jobsReceiver");
		listener = (MyMessageListener) ctx.getBean("myMessageListener");
	}

	public void receiveStuffSync() {
		System.out.println("Preparing to receive..");
		receiver.receiveMessages();
		System.out.println("END Received task (SYNCHRONOUSLY)");
	}

	public void receiveStuffASync() {
		System.out.println("Listening for messages");
		// already listening
		System.out.println("END Listening (ASYNCHRONOUSLY)");
	}

	public static void main(String[] args) {
		ReceiverClient client = new ReceiverClient();
		// client.receiveStuffSync();
		client.receiveStuffASync();
	}
}
