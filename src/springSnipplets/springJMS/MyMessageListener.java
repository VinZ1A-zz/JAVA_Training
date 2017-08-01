package springSnipplets.springJMS;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

//illustrates async reception
//to be wired with a container through Spring
public class MyMessageListener implements MessageListener {

	@Override
	public void onMessage(Message message) {
		// just acknowledging reception
		System.out.println("Received (ASYNC) " + message.toString());

		if (message instanceof ObjectMessage) {
			ObjectMessage objMsg = (ObjectMessage) message;
			try {
				Trade t = (Trade) objMsg.getObject();
				System.out.println("Received (ASYNC) content of Trade : " + t.getSomeProp());
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}
	}

}
