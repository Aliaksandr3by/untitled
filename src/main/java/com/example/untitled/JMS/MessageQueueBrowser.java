package com.example.untitled.JMS;

import com.example.untitled.domain.Employee;
import org.apache.logging.log4j.Logger;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jms.*;
import java.util.Enumeration;

@Named
@ApplicationScoped
public class MessageQueueBrowser {

	@Inject
	private Logger logger;

	@Inject
	@JMSConnectionFactory("java:/ConnectionFactory")
	private JMSContext context;

	@Resource(mappedName = ExpiryQueueDefinition.EXPIRY_QUEUE)
	private Queue queue;

	public MessageQueueBrowser() {
	}

	public void browseMessages() throws JMSException {
		try {

			var messageEnumeration = context.createBrowser(queue).getEnumeration();

			if (messageEnumeration != null) {
				if (!messageEnumeration.hasMoreElements()) {

					logger.info("There are no messages " + "in the queue.");

				} else {

					logger.info("There are messages " + "in the queue.");

					while (messageEnumeration.hasMoreElements()) {

						var message = (Employee)messageEnumeration.nextElement();

						logger.info(message.toString());

					}

				}
			}

		} catch (JMSException e) {
			e.printStackTrace();
			throw  e;
		}
	}
}
