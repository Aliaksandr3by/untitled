package com.example.untitled.JMS;

import com.example.untitled.domain.Employee;

import javax.jms.Message;
import javax.jms.MessageListener;

public class ExampleMessageListener implements MessageListener {

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