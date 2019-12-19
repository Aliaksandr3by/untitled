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
public class AsynchMessReceiver {

	@Inject
	private Logger logger;

	@Resource(mappedName = Settings.ConnectionFactory)
	private ConnectionFactory connectionFactory;

	@Resource(mappedName = Settings.QueueExpiry)
	private Queue queue;

	public AsynchMessReceiver() {
	}

	public void getMessages() {
		try {

			JMSContext jmsContext = connectionFactory.createContext();
			JMSConsumer jMSConsumer = jmsContext.createConsumer(queue);
			jMSConsumer.setMessageListener(new ExampleMessageListener());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}