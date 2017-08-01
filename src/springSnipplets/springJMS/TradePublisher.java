package springSnipplets.springJMS;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

//note: JmsTemplate is thread-safe, can be shared accross different beans
public class TradePublisher {
	private String destinationName = null; // injected
	private JmsTemplate jmsTemplate = null; // will be injected

	// unused for now
	public void setConnectionFactory(ConnectionFactory connectionFactory) {
		// create the JmsTemplate using the injected connection factory ?
		// could be done programmatically or done vis Spring configuration
		// (Spring chosen here)
		// jmsTemplate = new JmsTemplate(connectionFactory);
	}

	// note: all messages can be of one of 5 types:
	// TextMessage, BytesMessage, ObjectMessage, StreamMessage, MapMessage
	public void publishTrade(final Trade t) {
		// Exp1: sending MSG to a queue (default behavior) using destinationName
		// will send to topic if pubSubDomain is true in JmsTemplate
		getJmsTemplate().send(destinationName, new MessageCreator() {

			@Override
			public Message createMessage(Session session) throws JMSException {

				// when sending object
				// ObjectMessage msg = session.createObjectMessage();
				// msg.setObject(t);

				// could be as well createTextMessage returning TextMessage
				// instance then we can do msg.setText(xxx)
				TextMessage msg = session.createTextMessage();
				msg.setText("hey from Trade " + t.getSomeProp());
				return msg;
			}

		});

		// Exp2: now sending OBJ to default TOPIC (see Spring config), not using
		// destination
		getJmsTemplate().send(new MessageCreator() {

			@Override
			public Message createMessage(Session session) throws JMSException {
				// sending object
				ObjectMessage msg = session.createObjectMessage();
				msg.setObject(t);
				return msg;

			}
		});

		// Exp3. other shorter way, needs a converter TradeConverter
		t.setSomeProp(t.getSomeProp() + "_CONV_");
		// still in Queue
		getJmsTemplate().convertAndSend(destinationName, t);
	}

	/* getters/setters */
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
