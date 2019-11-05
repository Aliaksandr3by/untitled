package by.example.app.infrastructure.config;

import org.apache.logging.log4j.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Deprecated
@ApplicationScoped
public class EntityManagerFactoryProducer {

	@Inject
	private Logger logger;

	@Produces
	public EntityManagerFactory create() {

		return Persistence.createEntityManagerFactory("CRM");
	}

	public void dispose(@Disposes EntityManagerFactory entityManagerFactory) {
		if (entityManagerFactory.isOpen()) {
			entityManagerFactory.close();
			logger.info("entityManagerFactory.close()");
		}
	}

}