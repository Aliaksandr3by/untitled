package com.example.untitled.JMS;

import com.example.untitled.domain.Employee;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.jms.Message;
import javax.jms.MessageListener;


@MessageDriven(
		name = "MessageMDBSample",
		activationConfig = {
				@ActivationConfigProperty(
						propertyName = "destinationType",
						propertyValue = "javax.jms.Queue"),
				@ActivationConfigProperty(
						propertyName = "destination",
						propertyValue = "java:/jms/queue/ExpiryQueue"),
				@ActivationConfigProperty(
						propertyName = "acknowledgeMode",
						propertyValue = "Auto-acknowledge")})
public class ExampleMessageListener implements MessageListener {

	@Resource
	private MessageDrivenContext context;

	public ExampleMessageListener() {
	}

	@Override
	public void onMessage(Message message) {
		try {

			var tmp = message.getBody(Employee.class);

			System.out.println(tmp);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}