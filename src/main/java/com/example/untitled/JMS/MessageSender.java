package com.example.untitled.JMS;

import com.example.untitled.domain.Employee;
import org.apache.logging.log4j.Logger;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jms.*;

@Named
@ApplicationScoped
public class MessageSender {

	@Inject
	private Logger logger;

	@Resource(mappedName = Settings.ConnectionFactory)
	private ConnectionFactory cf;

	@Resource(mappedName = Settings.QueueExpiry)
	private Queue queue;

	public MessageSender() {
	}

	public void sendMessage(Employee employee) {

		try (Connection connection = cf.createConnection()) {

			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			MessageProducer publisher = session.createProducer(queue);
			connection.start();
			ObjectMessage message = session.createObjectMessage(employee);
			publisher.send(message);
			logger.info(message.getBody(Employee.class));

		} catch (Exception exc) {
			logger.error("Error ! " + exc);
		}

	}

	public void produceMessages() {

		JMSProducer jmsProducer;

		try (JMSContext jmsContext = cf.createContext()) {
			jmsProducer = jmsContext.createProducer();

			String msg1 = "Testing, 1, 2, 3. Can you hear me?";
			String msg2 = "Do you copy???";
			String msg3 = "Good bye!";

			jmsProducer.send(queue, msg1);
			jmsProducer.send(queue, msg2);
			jmsProducer.send(queue, msg3);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

}