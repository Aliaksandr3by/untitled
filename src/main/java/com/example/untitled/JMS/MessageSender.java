package com.example.untitled.JMS;

import com.example.untitled.domain.Employee;
import jakarta.annotation.Resource;
import jakarta.jms.JMSConnectionFactory;
import jakarta.jms.JMSContext;
import jakarta.jms.Queue;
import org.apache.logging.log4j.Logger;


import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;


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

			logger.debug(employee.toString());


		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

}