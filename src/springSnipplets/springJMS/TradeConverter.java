package springSnipplets.springJMS;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;

public class TradeConverter implements MessageConverter {

	@Override
	public Object fromMessage(Message message) throws JMSException, MessageConversionException {
		Trade t = (Trade) ((ObjectMessage) message).getObject();
		System.out.println("TradeConverter::from msg " + message.toString());
		return t;
	}

	@Override
	public Message toMessage(Object object, Session session) throws JMSException, MessageConversionException {
		if (!(object instanceof Trade)) {
			System.out.println("conversion toMessage failed");
			return null;
		}
		ObjectMessage objMsg = session.createObjectMessage();
		objMsg.setObject((Trade) object);
		System.out.println("TradeConverter::to msg " + objMsg.toString());
		return objMsg;
	}

}
