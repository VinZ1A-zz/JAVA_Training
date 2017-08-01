package springSnipplets.springJMS;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PublisherClient {

	private ApplicationContext ctx = null;
	private TradePublisher publisher = null;

	public PublisherClient() {
		ctx = new ClassPathXmlApplicationContext("springSnipplets/ch2-spring-beans.xml");
		publisher = (TradePublisher) ctx.getBean("tradePublisher");
	}

	public void publishTrade(Trade t) {
		System.out.println("publishing Trade..");
		publisher.publishTrade(t);
	}

	public static void main(String[] args) {
		// Point-to-Point messaging
		// message delivered to single consumer (one destination)
		// only one consumer will get msg even when hundreds of consumers
		// connected to queue

		// Pub/Sub messaging
		// Several subscribers receives copy of the message, which is published
		// once. The destination (called a Topic in this model) is targeted by
		// all subscribers to consume the message.
		// the JMS provider ensures each of subscribers receives a copy

		// usage of JmsTemplate class (cannot be used for asynchronous msg
		// consumption)

		PublisherClient publisherClient = new PublisherClient();
		Trade t = new Trade("VINZ");
		publisherClient.publishTrade(t);
		// need to run the ActiveMQ server on port 61616
		// launch first:
		// D:\Programs\apache-activemq-5.15.0\bin\win64\activemq.bat
		// then check received msgs in
		// QUEUE:
		// http://localhost:8161/admin/browse.jsp?JMSDestination=springSnipplets.springJMS.trades
		// and TOPIC:
		// http://localhost:8161/admin/send.jsp?JMSDestination=topic.DEFAULT_TRADES&JMSDestinationType=topic

		// don't know how to consume , dequeue message... XXX

		// compare with REST APIs (separate package) - XXX
	}
}
