package com.example.untitled.JMS;

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

	@Resource(mappedName = "java:/ConnectionFactory")
	private ConnectionFactory cf;

	@Resource(mappedName = "java:/jms/queue/ExpiryQueue")
	private Queue queue;

	public MessageQueueBrowser() {
	}

	public void browseMessages() throws JMSException {
		try {

			Enumeration messageEnumeration;
			TextMessage textMessage = null;
			JMSContext jmsContext = cf.createContext();
			QueueBrowser browser = jmsContext.createBrowser(queue);
			messageEnumeration = browser.getEnumeration();

			if (messageEnumeration != null) {
				if (!messageEnumeration.hasMoreElements()) {

					logger.info("There are no messages " + "in the queue.");

				} else {

					logger.info("There are messages " + "in the queue.");

					while (messageEnumeration.hasMoreElements()) {
						textMessage = (TextMessage) messageEnumeration.nextElement();
						logger.info(textMessage.getText());
					}

				}
			}

		} catch (JMSException e) {
			e.printStackTrace();
			throw  e;
		}
	}
}
