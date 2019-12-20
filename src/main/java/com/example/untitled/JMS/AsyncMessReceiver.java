package com.example.untitled.JMS;

import com.example.untitled.domain.Employee;
import org.apache.logging.log4j.Logger;

import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jms.ConnectionFactory;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;

@Named
@RequestScoped
public class AsyncMessReceiver {

	@Inject
	private Logger logger;

	@Inject
	@JMSConnectionFactory("java:/ConnectionFactory")
	private JMSContext context;

	@Resource(mappedName = "java:/jms/queue/ExpiryQueue")
	private Queue myQueue;

	@Resource(mappedName = "java:/ConnectionFactory")
	private static ConnectionFactory connectionFactory;

	public AsyncMessReceiver() {
	}

	public void getMessagesContext() {

		connectionFactory.createContext().createConsumer(myQueue).setMessageListener(message -> {
			try {
				var tmp = message.getBody(Employee.class);
				logger.info(tmp);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		});
	}

}