package com.example.untitled.infrastructure.config;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

public class LoggerProducer {

	@PostConstruct
	public void init() {

	}

	@PreDestroy
	public void destroy() {

	}

	/**
	 * Multiple calls to getLogger with the same name or class result in the exact same instance of
	 * Logger , not in multiple instances with the same name
	 * @param injectionPoint
	 * @return Logger object
	 */
	@Produces
	private Logger createLogger(InjectionPoint injectionPoint) {

		return LogManager.getLogger(injectionPoint.getBean().getBeanClass());
	}

}