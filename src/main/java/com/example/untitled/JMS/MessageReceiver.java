package com.example.untitled.JMS;

import org.apache.logging.log4j.Logger;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jms.ConnectionFactory;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.Queue;

@Named
@ApplicationScoped
public class MessageReceiver {

	@Inject
	private Logger logger;

	@Resource(mappedName = Settings.ConnectionFactory)
	private ConnectionFactory cf;

	@Resource(mappedName = Settings.QueueExpiry)
	private Queue queue;

	public MessageReceiver() {
	}

	public void getMessages() {

		String message = null;

		JMSContext jmsContext = cf.createContext();
		JMSConsumer jMSConsumer = jmsContext.createConsumer(queue);

		message = jMSConsumer.receiveBody(String.class);

		logger.info(message);

	}

}
