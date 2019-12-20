package com.example.untitled.JMS;

import com.example.untitled.domain.Employee;
import org.apache.logging.log4j.Logger;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jms.*;

@Named
@ApplicationScoped
public class MessageSender {

	@Inject
	private Logger logger;

	@Inject
	@JMSConnectionFactory("java:/ConnectionFactory")
	private JMSContext context;

	@Resource(mappedName = ExpiryQueueDefinition.EXPIRY_QUEUE)
	private Queue myQueue;

	public MessageSender() {
	}

	public void produceMessages(Employee employee) {

		try {

			context.createProducer().send(myQueue, employee);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

}