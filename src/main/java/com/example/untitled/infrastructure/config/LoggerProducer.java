package com.example.untitled.infrastructure.config;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Startup;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Named;
import javax.inject.Singleton;

@Startup
public class LoggerProducer {

	@PostConstruct
	public void init() {
		System.out.println("LoggerProducer init");
	}

	@PreDestroy
	public void destroy() {
		System.out.println("LoggerProducer destroy");
	}

	@Produces
	private Logger createLogger(InjectionPoint injectionPoint) {

		return LogManager.getLogger(injectionPoint.getBean().getBeanClass());
	}

}