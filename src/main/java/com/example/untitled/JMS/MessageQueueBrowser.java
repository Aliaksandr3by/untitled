package com.example.untitled.JMS;

import com.example.untitled.domain.Employee;
import jakarta.annotation.Resource;
import jakarta.jms.JMSConnectionFactory;
import jakarta.jms.JMSContext;
import jakarta.jms.JMSException;
import jakarta.jms.Queue;
import org.apache.logging.log4j.Logger;


import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;


@Named
@ApplicationScoped
public class MessageQueueBrowser {

    @Inject
    private Logger logger;

    @Inject
    @JMSConnectionFactory("java:/ConnectionFactory")
    private JMSContext context;

    @Resource(mappedName = ExpiryQueueDefinition.EXPIRY_QUEUE)
    private Queue queue;

    public MessageQueueBrowser() {
    }

    public void browseMessages() throws JMSException {
        try {

            var messageEnumeration = context.createBrowser(queue).getEnumeration();

            if (messageEnumeration != null) {

                if (!messageEnumeration.hasMoreElements()) {

                    logger.info("There are no messages " + "in the queue.");

                } else {

                    logger.info("There are messages " + "in the queue.");

                    while (messageEnumeration.hasMoreElements()) {

                        var message = (Employee) messageEnumeration.nextElement();

                        logger.info(message.toString());

                    }
                }

            }

        } catch (JMSException e) {
            logger.error(e.getMessage(), e);
            throw e;
        }
    }
}
