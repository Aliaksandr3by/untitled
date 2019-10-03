package by.example.app.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

@ApplicationScoped
public class LoggerProducer {

	@Produces
	private Logger createLogger(InjectionPoint injectionPoint) {

		return LoggerFactory.getLogger(injectionPoint.getBean().getBeanClass());
	}

}