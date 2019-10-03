package by.example.app.config;

import org.slf4j.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@ApplicationScoped
public class EntityManagerProducer {

	@Inject
	private Logger logger;

	//	@PersistenceUnit(unitName = "CRM")
	@Inject
	private EntityManagerFactory entityManagerFactory;

	@Produces
	@RequestScoped
	public EntityManager create() {

		return entityManagerFactory.createEntityManager();
	}

	public void dispose(@Disposes EntityManager entityManager) {
		if (entityManager.isOpen()) {
			entityManager.close();
			logger.info("entityManager.close()");
		}
	}

}
