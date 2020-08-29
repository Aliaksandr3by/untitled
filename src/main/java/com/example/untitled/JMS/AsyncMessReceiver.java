package com.example.untitled.JMS;

import com.example.untitled.domain.Employee;
import jakarta.annotation.Resource;
import jakarta.jms.ConnectionFactory;
import jakarta.jms.JMSConnectionFactory;
import jakarta.jms.JMSContext;
import jakarta.jms.Queue;
import org.apache.logging.log4j.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Deprecated
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