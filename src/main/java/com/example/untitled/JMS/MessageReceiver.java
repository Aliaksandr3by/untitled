package com.example.untitled.JMS;

import com.example.untitled.domain.Employee;
import jakarta.annotation.Resource;
import jakarta.jms.JMSConnectionFactory;
import jakarta.jms.JMSContext;
import jakarta.jms.Queue;
import org.apache.logging.log4j.Logger;


import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

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
