package com.example.untitled.JMS;

import javax.jms.JMSDestinationDefinition;
import javax.jms.JMSDestinationDefinitions;

//is not permitted in the Java EE or EJB
//@JMSDestinationDefinitions({
//		@JMSDestinationDefinition(
//				name = ExpiryQueueDefinition.EXPIRY_QUEUE,
//				interfaceName = "javax.jms.Queue")}
//)
public class ExpiryQueueDefinition {
	public static final String EXPIRY_QUEUE = "java:/jms/queue/ExpiryQueue";
}