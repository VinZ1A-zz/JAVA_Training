package springSnipplets.springJMS;

import javax.jms.Message;

import org.springframework.jms.core.JmsTemplate;

// illustrates synchronous reception of message
public class JobsReceiver {
	private String destinationName = null; // injected
	private JmsTemplate jmsTemplate = null; // will be injected

	public void receiveMessages() {
		// will block thread until received or until timeout
		Message msg = getJmsTemplate().receive(destinationName);
		System.out.println("Message received : " + msg);

		// could use receiveAndConvert() instead + usage of TradeConverter
	}

	public String getDestinationName() {
		return destinationName;
	}

	public void setDestinationName(String destinationName) {
		this.destinationName = destinationName;
	}

	public JmsTemplate getJmsTemplate() {
		return jmsTemplate;
	}

	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}
}
