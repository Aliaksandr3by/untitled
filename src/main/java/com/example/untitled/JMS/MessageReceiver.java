package com.example.untitled.JMS;

import com.example.untitled.domain.Employee;
import org.apache.logging.log4j.Logger;

import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;

@Named
@RequestScoped
public class MessageReceiver {

	@Inject
	private Logger logger;

	@Inject
	@JMSConnectionFactory("java:/ConnectionFactory")
	private JMSContext context;

	@Resource(mappedName = "java:/jms/queue/DLQ")
	private Queue myQueue;

	public MessageReceiver() {
	}

	public void getMessages() {

		Employee message = context.createConsumer(myQueue).receiveBody(Employee.class);

		logger.info(message);

	}

}
