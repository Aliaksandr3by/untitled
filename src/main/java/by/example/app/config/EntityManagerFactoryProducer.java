package by.example.app.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.inject.Default;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Named
@Singleton
public class EntityManagerFactoryProducer {

	@Produces
	@Default
	public EntityManagerFactory produceLogger(InjectionPoint injectionPoint) {
		return Persistence.createEntityManagerFactory("CRM");
	}

}