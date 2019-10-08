package by.example.app.infrastructure.config;

import org.apache.logging.log4j.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@ApplicationScoped
public class EntityManagerProducer {

	@Inject
	private Logger logger;

	@Inject
	private EntityManagerFactory entityManagerFactory;

	@Produces
	@RequestScoped
	@CRMMode
	public EntityManager create() {

		return entityManagerFactory.createEntityManager();
	}

	public void dispose(@Disposes @CRMMode EntityManager entityManager) {
		if (entityManager.isOpen()) {
			entityManager.close();
			logger.info("entityManager.close()");
		}
	}

}
