package com.example.untitled.JMS;

import com.example.untitled.domain.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import java.util.Objects;

@MessageDriven(
		name = "MessageMDBUntitled",
		activationConfig = {
				@ActivationConfigProperty(
						propertyName = "destinationType",
						propertyValue = "javax.jms.Queue"),
				@ActivationConfigProperty(
						propertyName = "destination",
						propertyValue = ExpiryQueueDefinition.EXPIRY_QUEUE),
				@ActivationConfigProperty(
						propertyName = "acknowledgeMode",
						propertyValue = "Auto-acknowledge")})
public class MessageMdbListener implements MessageListener {

	@Resource
	protected MessageDrivenContext context;

	//TODO
	private Logger logger;

	@PostConstruct
	private void postConstruct() {
		logger = LogManager.getLogger(this.getClass());
		if(Objects.nonNull(logger)) logger.debug("logger was constructed");
		logger.debug("was constructed");
	}

	public MessageMdbListener() {
	}

	@Override
	public void onMessage(Message message) {
		try {
			if (Objects.nonNull(message) && Objects.nonNull(context)) {

				Employee tmp = message.getBody(Employee.class);
				logger.info(tmp);

			} else {

				logger.error("message is null");

			}
		} catch (JMSException e) {
			logger.error(e.getMessage(), e);
		}
	}
}