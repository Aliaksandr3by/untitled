package com.example.untitled.JMS;

import jakarta.jms.JMSDestinationDefinition;
import jakarta.jms.JMSDestinationDefinitions;

//TODO wildfly11 выдает ошибку
//is not permitted in the Java EE or EJB
//@JMSDestinationDefinitions({
//		@JMSDestinationDefinition(
//				name = ExpiryQueueDefinition.EXPIRY_QUEUE,
//				interfaceName = "javax.jms.Queue")}
//)
public class ExpiryQueueDefinition {
	public static final String EXPIRY_QUEUE = "java:/jms/queue/ExpiryQueue";
}